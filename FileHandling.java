package ecosaveGui;
import java.io.*;
import javax.swing.*;


public class FileHandling {
	//read object from file
	public static Object readFromFile(javax.swing.JFrame parent){ 

		// get the file using the openFileChooser() method
		File file = openFileChooser(parent);
		Object theObject = null;
		FileInputStream fis= null;
		try {
			fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			theObject = ois.readObject();
			ois.close();
		}
		catch(Exception e) {   
			System.out.println(e.getMessage()); }
		finally {
			if (fis!=null)  
				try {      fis.close(); } 
			catch (IOException e) { 
				System.out.println(e.getMessage());
			}
			return theObject;
		}
	}


	private static File openFileChooser(javax.swing.JFrame parent) {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(parent);         // show the Open-File Dialog
		if (returnVal == JFileChooser.APPROVE_OPTION) 
		{
			File file = fc.getSelectedFile();
			return file;            // return the file that was selected
		}
		return null;

	}

	// methods to save the object to the file
	public static void saveToFile(javax.swing.JFrame parent, Object theObject) {
		JFileChooser fc = new JFileChooser();
		File file=null;
		int returnVal = fc.showSaveDialog(parent);
		if (returnVal == JFileChooser.APPROVE_OPTION)  {
			file = fc.getSelectedFile();
			saveObject(file, theObject);        // call the private method
			System.out.println("Saving: " + file.getName());
		}  else {
			System.out.println("Save command cancelled by user.");
		}

	}
	private static void saveObject(File file, Object theObject) { 
		FileOutputStream fos=null;
		try     {
			fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(theObject);
			oos.flush();
			oos.close();
		}
		catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		finally {
			if (fos!=null) 
				try { fos.close(); }
			catch (IOException ioe) { System.out.println(ioe.getMessage()); }
		}

	}

}



