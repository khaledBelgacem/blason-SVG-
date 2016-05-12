package execution;

import java.io.IOException;
import java.util.ArrayList;

import org.jdom2.JDOMException;

import geometry.real.Point;
import geometry.real.Vector;
import heraldique.Ecu;
import heraldique.enumeration.Metal;
import heraldique.generique.Support;
import parsing.Forme;
import parsing.Image;
import parsing.Parser;
import parsing.Superposable;

public class Operation {
	
	public static void main(String[] args) {
		testTranslation("Quintefeuille.svg", "QUINTEFEUILLE_TRANSLATION.svg");
		testHomothetie("Quintefeuille.svg", "QUINTEFEUILLE_HOMOTETHIE.svg");
		testRotation("Quintefeuille.svg", "QUINTEFEUILLE_ROTATION.svg");
		
		testTranslation("Merlette.svg", "MERLETTE_TRANSLATION.svg");
		testHomothetie("Merlette.svg", "MERLETTE_HOMOTETHIE.svg");
		testRotation("Merlette.svg", "MERLETTE_ROTATION.svg");
	}
	
	public static void testTranslation(String entree, String sortie) {
		ArrayList<Point> points = new ArrayList<>();

		try {
			Parser parser = new Parser(entree);
			Image image = parser.image();
			image.recursion();
			listePoints(image,  points);
			
			//beta
			  
			  Support support = new Support(points,"argent");
			  support.translation(50, 0);
			  Ecu ecu= new Ecu(100,100,Metal.OR) ; 
			  ecu.charge(support,1);
			  DrawCanvas.saveAsSVG("merletteSupportTranslation.svg", ecu.svg());
			//fin beta
			// Alternative
			for (Point p : points) {
				p.x(p.x() + 50);
			}
			
			image.sauvegarder(sortie);
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void testHomothetie(String entree, String sortie) {
		ArrayList<Point> points = new ArrayList<>();
		
		try {
			Parser parser = new Parser(entree);
			Image image = parser.image();
			image.recursion();
			listePoints(image,  points);
			
			/* Utilisation d'un support pour faire l'homotethie
			 * 
			 * Ne fonctionne pas mais devrait ...
			 * 
			 * Support support = new Support(points, Email.AUCUN);
			 * support.l'homotethie(0.5);
			 */
			
			// Alternative
			
			// isobarycentre de support
			double x = 0, y = 0, n = points.size();
			for (Point p : points) {
				x += p.x();
				y += p.y();
			}
			Point isobarycentre = new Point(x/n, y/n);
			
			//homotethie de support
			for (Point p : points) {
				Vector v = new Vector(isobarycentre, p);
				v.scale(0.5);
				p.x(v.b().x());
				p.y(v.b().y());
			}
			
			image.sauvegarder(sortie);
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void testRotation(String entree, String sortie) {
		ArrayList<Point> points = new ArrayList<>();
		
		try {
			Parser parser = new Parser(entree);
			Image image = parser.image();
			image.recursion();
			listePoints(image,  points);
			
			/* Utilisation d'un support pour faire la rotation
			 * 
			 * Ne fonctionne pas mais devrait ...
			 * 
			 * Support support = new Support(points, Email.AUCUN);
			 * support.rotation(Math.PI/2);
			 */
			
			// Alternative
			
			// isobarycentre de support
			double x = 0, y = 0, n = points.size();
			for (Point p : points) {
				x += p.x();
				y += p.y();
			}
			Point isobarycentre = new Point(x/n, y/n);
			
			//rotation de support
			double a = Math.PI/2;
			for (Point p : points) {
				Vector v = new Vector(isobarycentre, p);
				double x2 = isobarycentre.x() + (v.b().x() - isobarycentre.x()) * Math.cos(a) - (v.b().y() - isobarycentre.y()) * Math.sin(a);
				double y2 = isobarycentre.y() + (v.b().x() - isobarycentre.x()) * Math.sin(a) + (v.b().y() - isobarycentre.y()) * Math.cos(a);
				p.x(x2);
				p.y(y2);
			}
			
			image.sauvegarder(sortie);
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * Constitue la liste des tous les points appartenant au Superposable r�cursivement.
	 * 
	 * @param noeud, le Superposable en question
	 * @param points, la liste de tous ses points
	 */
	
	@SuppressWarnings("unchecked")
	public static void listePoints(ArrayList<Superposable> noeud, ArrayList<Point> points) {
		try {
			Superposable superposable = (Superposable)noeud;
			Forme forme = (Forme)superposable;
			for (Object o : forme) {
				try {
					Point p = (Point)o;
					points.add(p);
				} catch (ClassCastException e) {}
			}
		} catch (ClassCastException e) {
			for (Superposable o : noeud) {
				listePoints((ArrayList<Superposable>)o, points);
			}
		}
	}
}
