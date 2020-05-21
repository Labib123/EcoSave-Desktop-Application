package ecosaveGui;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class MaterialsArrayTableModel extends AbstractTableModel{
	//Array to display in table
	private ArrayList<Material> materials;
	//Names of columns
	private String[] columnHeader = {"MaterialID", "Material Name", 
			"Description", "Points per Kg"};

	public MaterialsArrayTableModel(ArrayList<Material> materials) {
		this.materials = materials;
	}
	//Creating Table
	public int getRowCount() {
		return getMaterials().size();

	}
	public int getColumnCount() {
		return columnHeader.length;
	}
	public Object getValueAt(int row, int column) {
		Material v = getMaterials().get(row);

		switch (column)
		{
		case 0:
			return v.getMaterialID();
		case 1:
			return v.getMaterialName();
		case 2:
			return v.getDescription();
		case 3:
			return v.getPointsPerKg();
		default:
			return "";
		}
	}

	public String getColumnName(int column)
	{
		return columnHeader[column];
	}


	public ArrayList<Material> getMaterials() {
		return materials;
	}

	public void setMaterials(ArrayList<Material> materials) {
		this.materials = materials;
	}


	public void add(Material material) {
		getMaterials().add(material);
		fireTableDataChanged();
	}

	public void removeRow(int row) {
		materials.remove(row);
	}
}


