package ecosaveGui;

/**
 * Enum class that consists of the constants:
 * NEWBIE, ECOSAVER, ECHHERO, ECOWARRIOR
 * @author KokChyeHock
 *
 */
public enum EcoLevel {
	NEWBIE, ECOSAVER, ECOHERO, ECOWARRIOR;
	
	public String inWord() {
		switch (this) {
		case NEWBIE: return "Newbie";
		case ECOSAVER: return "Eco-Saver";
		case ECOHERO: return "Eco-Hero";
		default: return "Eco-Warrior";
		}
	} 
}
