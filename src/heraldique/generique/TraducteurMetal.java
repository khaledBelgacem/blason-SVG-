package heraldique.generique;

import heraldique.enumeration.Metal;

public class TraducteurMetal {

	public static String couleur(Metal metal) {
		switch (metal) {
		case ARGENT:
			return "white";
		case OR:
			return "yellow";
		default:
			break;
		}
		return "";
	}
}
