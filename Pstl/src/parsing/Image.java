package parsing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import geometry.real.Point;

public class Image extends ArrayList<Superposable> implements Superposable {

	public double largeur;

	public double hauteur;

	private static final long serialVersionUID = 7039488098500510177L;

	public Image(int largeur, int hauteur) {
		this.largeur = largeur;
		this.hauteur = hauteur;
	}

	public void setLargeur(double largeur) {
		this.largeur = largeur;
	}

	public void setHauteur(double hauteur) {
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

	public ArrayList<Point> recursion() {
		ArrayList<Point> formePoints = new ArrayList<Point>() ;
		for (Superposable s : this) {		
			try {
				Forme f = (Forme)s;	
				for (Object st : f){
					if(st.toString().startsWith("(")){
						String p1 = st.toString().split(",")[0];
						p1=p1.replace('(', ' ');
						String p2=st.toString().split(",")[1];
						p2=p2.replace(')',' ');
						formePoints.add(new Point(Double.parseDouble(p1),Double.parseDouble(p2))) ; 
					}
				}
			} catch (ClassCastException e) {
				System.out.println("Depuis image : \n\t" + e + "\n\tAttention l'image possede plusieurs niveau !");
			}
		}
		return formePoints ; 
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
