package heraldique.generique;

import heraldique.enumeration.Email;

public class TraducteurEmail {

	public static String couleur(Email email) {
		switch (email) {
		case AZUR:
			return "blue";
		case ORANGE:
			return "orange";
		case SABLE:
			return "black";
		case GUEULES:
			return "red";
		case POURPRE:
			return "purple";
		case CARNATION:
			return "grey";
		default:
			break;
		}
		return "";
	}
	
}
