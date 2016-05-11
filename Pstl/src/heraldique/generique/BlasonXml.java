package heraldique.generique;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jdom2.JDOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import execution.DrawCanvas;
import geometry.real.Point;
import heraldique.Ecu;
import heraldique.enumeration.Email;
import heraldique.enumeration.Metal;
import parsing.Forme;
import parsing.Image;
import parsing.Parser;
import parsing.Superposable;

public class BlasonXml { 
	private ArrayList<String> elems ; 
	public BlasonXml() {
		elems = new ArrayList<String>() ; 
		try {	
			File inputFile = new File("samplesXML/blasonTest.xml");
			DocumentBuilderFactory dbFactory 
			= DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" 
					+ doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("charger");
			System.out.println("----------------------------");
			int blit ; 
			String champ ; 
			String emailChamp=""; 
			String emailPair="" ; 
			String emailImpair="";
			String figure="" ; 
			String emailFigure="" ; 
			String rotation=""; 
			String cible = "" ; 
			ArrayList<String>forme = new ArrayList<String>();
			boolean echequier=false ; 
			boolean svg=false ; 
			ArrayList<Point> champPoints = new ArrayList<Point>() ; 
			ArrayList<Point> figurePoints = new ArrayList<Point>() ; 
			Groupe grouper = new Groupe() ; 
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				System.out.println("\nCurrent Element :" 
						+ nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					if(eElement.getElementsByTagName("figure").getLength()==1){
						blit = 1 ;
						champ = eElement.getElementsByTagName("champ").item(0).getTextContent();
						figure  = eElement.getElementsByTagName("figure").item(0).getTextContent(); 
						for (int it=0 ; it<eElement.getElementsByTagName("champ").item(0).getAttributes().getLength();it++) {
							if( eElement.getElementsByTagName("champ").item(0).getAttributes().item(it).getNodeName()=="type"){
								String type = eElement.getElementsByTagName("champ").item(0).getAttributes().item(it).getTextContent();
								if (type.equals("echequier")){
									echequier=true ; 
								}
							}
							else if( eElement.getElementsByTagName("champ").item(0).getAttributes().item(it).getNodeName()=="email")
								emailChamp = eElement.getElementsByTagName("champ").item(0).getAttributes().item(it).getTextContent() ; 
							else if( eElement.getElementsByTagName("champ").item(0).getAttributes().item(it).getNodeName()=="emailImpair")
								emailImpair = eElement.getElementsByTagName("champ").item(0).getAttributes().item(it).getTextContent() ; 
							else if( eElement.getElementsByTagName("champ").item(0).getAttributes().item(it).getNodeName()=="emailPair")
								emailPair = eElement.getElementsByTagName("champ").item(0).getAttributes().item(it).getTextContent() ; 
						}
						for (int it=0 ; it<eElement.getElementsByTagName("figure").item(0).getAttributes().getLength();it++) {
							if( eElement.getElementsByTagName("figure").item(0).getAttributes().item(it).getNodeName()=="rotation") 
								rotation = eElement.getElementsByTagName("figure").item(0).getAttributes().item(it).getTextContent();
							else if(eElement.getElementsByTagName("figure").item(0).getAttributes().item(it).getNodeName()=="email")
								emailFigure = eElement.getElementsByTagName("figure").item(0).getAttributes().item(it).getTextContent();
							else if(eElement.getElementsByTagName("figure").item(0).getAttributes().item(it).getNodeName()=="type"){
								String type = eElement.getElementsByTagName("figure").item(0).getAttributes().item(it).getTextContent();
								if(type.equals("svg")){
									svg=true ; 	
									figurePoints =svgPoints("imagesSVG/"+figure,forme);
									System.out.println(figurePoints);
								}
							}
							else if(eElement.getElementsByTagName("figure").item(0).getAttributes().item(it).getNodeName()=="cible"){
								cible = eElement.getElementsByTagName("figure").item(0).getAttributes().item(it).getTextContent();
							}
						}
					}
					else {
						blit = 0 ; 
						champ = eElement.getElementsByTagName("champ").item(0).getTextContent();
						for (int it=0 ; it<eElement.getElementsByTagName("champ").item(0).getAttributes().getLength();it++) {
							if( eElement.getElementsByTagName("champ").item(0).getAttributes().item(it).getNodeName()=="type"){
								String type = eElement.getElementsByTagName("champ").item(0).getAttributes().item(it).getTextContent();
								if (type.equals("echequier")){
									echequier=true ; 
								}
							}
							else if( eElement.getElementsByTagName("champ").item(0).getAttributes().item(it).getNodeName()=="email")
								emailChamp = eElement.getElementsByTagName("champ").item(0).getAttributes().item(it).getTextContent() ; 
							else if( eElement.getElementsByTagName("champ").item(0).getAttributes().item(it).getNodeName()=="emailImpair")
								emailImpair = eElement.getElementsByTagName("champ").item(0).getAttributes().item(it).getTextContent() ; 
							else if( eElement.getElementsByTagName("champ").item(0).getAttributes().item(it).getNodeName()=="emailPair")
								emailPair = eElement.getElementsByTagName("champ").item(0).getAttributes().item(it).getTextContent() ; 
						}				
					}
					if (!echequier){
						Support schamp = null ; 
						Support sfigure = null; 
						String[] splitted = champ.split("\\)") ;  
						for (String s :splitted) { 
							String p1 = s.split(",")[0];
							p1=p1.replace('(', ' ');
							String p2=s.split(",")[1];
							champPoints.add(new Point(Double.parseDouble(p1),Double.parseDouble(p2))) ; 
						}
						schamp = new Support(champPoints,emailChamp);
						Ecu ecu= new Ecu(100,100,Metal.AUCUN) ; 
						ecu.charge(schamp,0);
						if (blit==1) {
							if (svg==false) {
								String[] fsplit = figure.split("\\)") ;  
								for (String s :fsplit) { 
									String p1 = s.split(",")[0];
									p1=p1.replace('(', ' ');
									String p2=s.split(",")[1];
									figurePoints.add(new Point(Double.parseDouble(p1),Double.parseDouble(p2))) ; 
								}
							}
							if(svg==true)
								sfigure = new Support(figurePoints,emailFigure,rotation,forme);
							else
								sfigure = new Support(figurePoints,emailFigure,rotation);
							schamp.charge(sfigure,1); 
						}
						grouper.add(ecu) ; 
						champPoints.clear();
						figurePoints.clear();
						figure = "" ; 
						rotation="";
					}
					else {
						ArrayList<Support> echec = new ArrayList<Support>() ; 
						String color=emailImpair;
						for(int nbci=0 ; nbci<100;nbci=nbci+20){
							for(int nbcj=0 ; nbcj<100;nbcj=nbcj+20){
								champPoints.add(new Point(nbci,nbcj)) ; 
								champPoints.add(new Point(nbci,nbcj+20)) ; 
								champPoints.add(new Point(nbci+20,nbcj)) ; 
								champPoints.add(new Point(nbci+20,nbcj+20)) ; 
								Support carre=new Support(champPoints,color);
								echec.add(carre);
								champPoints.clear();  
								Ecu ecu= new Ecu(100,100,Metal.AUCUN) ; 
								if (blit==1) {
									if (svg==false) {
										String[] fsplit = figure.split("\\)") ;  
										for (String s :fsplit) { 
											String p1 = s.split(",")[0];
											p1=p1.replace('(', ' ');
											String p2=s.split(",")[1];
											figurePoints.add(new Point(Double.parseDouble(p1),Double.parseDouble(p2))) ; 
										}
									}
								}
								Support	sfigure = new Support(figurePoints,emailFigure,rotation);
								ecu.charge(carre,0);
								if(cible.equals("impair") && color==emailImpair)
									carre.charge(sfigure,1);
								else if(cible.equals("pair") && color==emailPair)
									carre.charge(sfigure,1);
								grouper.add(ecu);  
								if (color.equals(emailImpair))
									color = emailPair ; 
								else if(color.equals(emailPair))
									color = emailImpair ; 
							}
						}
						
						System.out.println("taille de la liste "+echec.size());
						
					}
				}
			}
			DrawCanvas.saveAsSVG("testBlason.svg", grouper.svg());
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public static ArrayList<Point> svgPoints(String nomFichier,ArrayList<String>forme){
		ArrayList<Point> svgListPoints = new ArrayList<Point>() ; 
		Parser parser;
		try {
			parser = new Parser(nomFichier);
			Image image = parser.image();
			image.recursion();
			listePoints(image,svgListPoints,forme) ; 
			//image.sauvegarder("TestForme.svg");
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return svgListPoints;

	}
	public static void listePoints(ArrayList<Superposable> noeud, ArrayList<Point> points,ArrayList<String>lettresEtPoints) {
        try {
            Superposable superposable = (Superposable)noeud;
            Forme forme = (Forme)superposable;
            for (Object o : forme) {
            	lettresEtPoints.add(o.toString());
                try {
                    Point p = (Point)o;
                    points.add(p);
                } catch (ClassCastException e) {}
            }
        } catch (ClassCastException e) {
            for (Superposable o : noeud) {
                listePoints((ArrayList<Superposable>)o, points,lettresEtPoints);
            }
        }
    }
}