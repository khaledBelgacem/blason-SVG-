package execution;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

import org.jdom2.JDOMException;

import parsing.Forme;
import parsing.Image;
import parsing.Parser;
import parsing.Superposable;

public class Fragmentation {
	
	public static void main(String[] args) {
		testFragmentation("Lion.svg", "LION");
		testFragmentation("Merlette.svg", "MERLETTE");
		testFragmentation("Quintefeuille.svg", "QUINTEFEUILLE");
		testFragmentation("Fleur_de_lys.svg", "FLEUR DE LYS");
	}
	
	public static void testFragmentation(String fichier, String dossier) {
		File fragement = new File(dossier);

		if (fragement.exists()) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Voulez-vous vraiment supprimer " + dossier + " ainsi que tout son contenu ? (oui/non)");
			String reponse;
			try {
				reponse = br.readLine();
				switch (reponse) {
				case "oui" :
					
					File[] currList;
					Stack<File> stack = new Stack<File>();
					stack.push(fragement);
					while (! stack.isEmpty()) {
					    if (stack.lastElement().isDirectory()) {
					        currList = stack.lastElement().listFiles();
					        if (currList.length > 0) {
					            for (File curr: currList) {
					                stack.push(curr);
					            }
					        } else {
					            stack.pop().delete();
					        }
					    } else {
					        stack.pop().delete();
					    }
					}
					
					try {
						fragement.mkdir();
						System.out.println("Dossier " + fragement.getName() + " cr��");
					} catch (SecurityException e) {
						e.printStackTrace();
					}
					
					break;
				case "non":
					break;
				default:
					System.out.println(reponse + " : non reconnue, aucune action n'est faite");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				fragement.mkdir();
				System.out.println("Dossier " + fragement.getName() + " cr��");
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}		
		
		System.out.println("La fragmentation du Superposable <" + fichier + "> commence");
		
		try {
			Parser parser = new Parser(fichier);
			Image image = parser.image();
			fragmentation(image, image.largeur, image.hauteur, dossier);
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Methode pour fragmenter l'ensemble des formes d'un Superposable vers un dossier.
	 * Les champs largeur et hauteur servent � d�finir la surface sur laquelle les fragements seront dispos�s.
	 * 
	 * @param noeud Superposable � fragmenter
	 * @param largeur Largeur de l'image SVG
	 * @param hauteur Hauteur de l'image SVG
	 * @param dossier Dossier de destination
	 */
	
	@SuppressWarnings("unchecked")
	public static void fragmentation(ArrayList<Superposable> noeud, double largeur, double hauteur, String dossier) {
	
		try {
			Superposable superposable = (Superposable)noeud;
			Forme forme = (Forme)superposable;
			Image image = new Image((int)largeur, (int)hauteur);
			image.recursion();
			image.add(forme);
			double rand = Math.random();
			image.sauvegarder(dossier + '/' + rand + ".svg");
			System.out.println("Le fragment " + dossier + '/' + rand + ".svg � �t� sauvegard�");
		} catch (ClassCastException e) {
			for (Superposable o : noeud) {
				fragmentation((ArrayList<Superposable>)o, largeur, hauteur, dossier);
			}
		}
	}
}
