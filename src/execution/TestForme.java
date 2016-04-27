package execution;

import java.io.IOException;

import org.jdom2.JDOMException;

import parsing.Image;
import parsing.Parser;

public class TestForme {

	public static void main(String[] args) {

		String nomFichier = new String("C:/Users/Daniel/Desktop/Blason_fam_de_Hohenlohe-Waldenburg.svg");
		String[] array = nomFichier.split("[.]");

		if (array[array.length-1].equals("svg") || array[array.length-1].equals("xml")) {
			Parser parser;
			try {
				parser = new Parser(nomFichier);
				Image image = parser.image();
				image.recursion();
				image.sauvegarder("TestForme.svg");
			} catch (JDOMException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			//			try {
			//				parser.lireFichier(nomFichier);
			////				Calque c = parser.calque();
			////				if (c != null) {
			////					System.out.println(c.formes.size());
			////					System.out.println("STRUCTURE DU CALQUE");
			////
			////					printCalque(c, 0);
			////					//DrawCanvas.saveAsSVG("testCalque.svg", c.svg());
			////				}
			////				else
			////					System.out.println("c is null");
			//				Calque principal = parser.calquePrincipal(parser.getRacine());
			////				principal.etiquette = "SVG";
			//				parser.parcours(parser.getRacine(), principal);
			//				printCalque(principal, 0);
			//				
			//			} catch (Exception e) {
			//				// TODO Auto-generated catch block
			//				e.printStackTrace();
			//			}
			//			
			//		}
		}

		//	public static void printCalque(Calque c, int i) {
		//		
		//		String s = "";
		//		int j = i;
		//		while (j != 0) {
		//			s += "\t";
		//			j--;
		//		}
		//		
		//		System.out.println(s + c);
		//				
		//		for (Forme f : c.formes) {
		//			
		//			System.out.println(s + f);
		//		}
		//		
		//		if (c.dessus == null)
		//			return;
		//		
		//		printCalque((Calque)c.dessus, ++i);
		//	}

	}
}
