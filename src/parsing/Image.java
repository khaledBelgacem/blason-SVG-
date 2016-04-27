package parsing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Image extends ArrayList<Superposable> implements Superposable {

	int largeur, hauteur;

	private static final long serialVersionUID = 7039488098500510177L;

	public Image(int largeur, int hauteur) {
		this.largeur = largeur;
		this.hauteur = hauteur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}
	
//	public void calculerTransformation() {
//		for (Superposable s : this) {
//			try {
//				Calque c = (Calque)s;
//				c.calculerTransformation();
//			} catch (ClassCastException e) {}
//		}
//	}
	
	public void recursion() {
		for (Superposable s : this) {
			try {
				Calque c = (Calque)s;
				c.recursion();
			} catch (ClassCastException e) {}
		}
	}
	
	@Override
	public String svg() {
		StringBuffer sb = new StringBuffer();

		sb.append("<?xml version='1.0' encoding='utf-8'?>\n<svg xmlns='http://www.w3.org/2000/svg' version='1.1'" + "\nwidth = '" + largeur + "'" + "\nheight = '" + hauteur + "'" + " >"
				);//+ "<g transform='translate(300,300)'>");
		for (Superposable s : this)
			sb.append(s.svg());
		sb.append("</svg>"); //sb.append("\n</g></svg>");

		return sb.toString();
	}

	public void sauvegarder(String nom) {
		File file = new File(nom);
		try {
			FileWriter fw = new FileWriter(file);
			fw.write(svg());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
