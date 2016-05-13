package parsing;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import geometry.real.Point;

public class Chemin {

	private String d;
	private ArrayList<Object> relatifs;
	private ArrayList<Object> absolus;
	private ArrayList<Object> originaux;
	private Point reference;
	private Point debut;

	private boolean debug = false;

	private Pattern pattern;
	private Matcher matcher;

	public Chemin(String d) {
		this.d = d;
		relatifs = new ArrayList<>();
		absolus = new ArrayList<>();
		originaux = new ArrayList<>();
		pattern = Pattern.compile(""
				+ "[mlcsqtaz]|"	// Symboles relatifs
				+ "[MLCSQTAZ]|" // Symboles absolus
				+ "[+\\-]?(?:0|[1-9]\\d*)(?:\\.\\d*)?(?:[eE][+\\-]?\\d+)?|" // Notations exponentielles
				+ "\\d+[.]\\d+|" // D�cimaux positifs
				+ "-\\d+[.]\\d+|" // D�cimaux n�gatifs
				+ "\\d+|" // Entiers positifs
				+ "-\\d+"); // Entiers n�gatifs
		matcher = pattern.matcher(this.d);
		reference = new Point(0,0);
		debut = new Point(0,0);
	}

	private ArrayList<Object> moveTo() {
		ArrayList<Object> objects = new ArrayList<>();
		if (matcher.group().charAt(0) == 'm' || matcher.group().charAt(0) == 'M') { 
			Character c = matcher.group().charAt(0);
			Double x = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Double y = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Point p = new Point(x,y);
			objects.add(c);
			objects.add(p);
		} else {
			Double x = Double.valueOf(matcher.group());
			Double y = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Point p = new Point(x,y);
			objects.add(p);
		}
		return objects;
	}

	private ArrayList<Object> closePath() {
		ArrayList<Object> objects = new ArrayList<>();
		Character c = matcher.group().charAt(0);
		objects.add(c);
		return objects;
	}

	private ArrayList<Object> lineTo() {
		ArrayList<Object> objects = new ArrayList<>();
		if (matcher.group().charAt(0) == 'l' || matcher.group().charAt(0) == 'L') { 
			Character c = matcher.group().charAt(0);
			Double x = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Double y = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Point p = new Point(x,y);
			objects.add(c);
			objects.add(p);
		} else {
			Double x = Double.valueOf(matcher.group());
			Double y = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Point p = new Point(x,y);
			objects.add(p);
		}
		return objects;
	}

	private ArrayList<Object> lineToH() {
		ArrayList<Object> objects = new ArrayList<>();
		if (matcher.group().charAt(0) == 'l' || matcher.group().charAt(0) == 'L') { 
			Character c = matcher.group().charAt(0);
			Double x = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			objects.add(c);
			objects.add(x);
		} else {
			Double x = Double.valueOf(matcher.group());
			objects.add(x);
		}
		return objects;
	}

	private ArrayList<Object> lineToV() {
		ArrayList<Object> objects = new ArrayList<>();
		if (matcher.group().charAt(0) == 'l' || matcher.group().charAt(0) == 'L') { 
			Character c = matcher.group().charAt(0);
			Double y = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			objects.add(c);
			objects.add(y);
		} else {
			Double y = Double.valueOf(matcher.group());
			objects.add(y);
		}
		return objects;
	}

	private ArrayList<Object> curveTo() {
		ArrayList<Object> objects = new ArrayList<>();
		if (matcher.group().charAt(0) == 'c' || matcher.group().charAt(0) == 'C') { 
			Character c = matcher.group().charAt(0);
			Double x1 = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Double y1 = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Double x2 = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Double y2 = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Double x = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Double y = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Point p1 = new Point(x1,y1);
			Point p2 = new Point(x2,y2);
			Point p = new Point(x,y);
			objects.add(c);
			objects.add(p1);
			objects.add(p2);
			objects.add(p);
		} else {
			Double x1 = Double.valueOf(matcher.group());
			Double y1 = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Double x2 = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Double y2 = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Double x = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Double y = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Point p1 = new Point(x1,y1);
			Point p2 = new Point(x2,y2);
			Point p = new Point(x,y);
			objects.add(p1);
			objects.add(p2);
			objects.add(p);
		}
		return objects;
	}

	private ArrayList<Object> smoothCurveTo() {
		ArrayList<Object> objects = new ArrayList<>();
		if (matcher.group().charAt(0) == 's' || matcher.group().charAt(0) == 'S') { 
			Character c = matcher.group().charAt(0);
			Double x2 = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Double y2 = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Double x = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Double y = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Point p2 = new Point(x2,y2);
			Point p = new Point(x,y);
			objects.add(c);
			objects.add(p2);
			objects.add(p);
		} else {
			Double x2 = Double.valueOf(matcher.group());
			Double y2 = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Double x = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Double y = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Point p2 = new Point(x2,y2);
			Point p = new Point(x,y);
			objects.add(p2);
			objects.add(p);
		}
		return objects;
	}

	private ArrayList<Object> quadraticBezierCurveTo() {
		ArrayList<Object> objects = new ArrayList<>();
		if (matcher.group().charAt(0) == 'q' || matcher.group().charAt(0) == 'Q') { 
			Character c = matcher.group().charAt(0);
			Double x1 = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Double y1 = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Double x = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Double y = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Point p1 = new Point(x1,y1);
			Point p = new Point(x,y);
			objects.add(c);
			objects.add(p1);
			objects.add(p);
		} else {
			Double x1 = Double.valueOf(matcher.group());
			Double y1 = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Double x = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Double y = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Point p1 = new Point(x1,y1);
			Point p = new Point(x,y);
			objects.add(p1);
			objects.add(p);
		}
		return objects;
	}

	private ArrayList<Object> smoothQuadraticBezierCurveTo() {
		ArrayList<Object> objects = new ArrayList<>();
		if (matcher.group().charAt(0) == 't' || matcher.group().charAt(0) == 'T') { 
			Character c = matcher.group().charAt(0);
			Double x = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Double y = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Point p = new Point(x,y);
			objects.add(c);
			objects.add(p);
		} else {
			Double x = Double.valueOf(matcher.group());
			Double y = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Point p = new Point(x,y);
			objects.add(p);
		}
		return objects;
	}

	private ArrayList<Object> ellipticalArc() {
		ArrayList<Object> objects = new ArrayList<>();
		if (matcher.group().charAt(0) == 'a' || matcher.group().charAt(0) == 'A') { 
			Character c = matcher.group().charAt(0);
			Double rx = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Double ry = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Double axisRotation = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Integer largeArcFlag = (matcher.find()) ? Integer.valueOf(matcher.group()) : null;
			Integer sweepFlag = (matcher.find()) ? Integer.valueOf(matcher.group()) : null;
			Double x = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Double y = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Point r = new Point(rx, ry);
			Point p = new Point(x,y);
			objects.add(c);
			objects.add(r);
			objects.add(axisRotation);
			objects.add(largeArcFlag);
			objects.add(sweepFlag);
			objects.add(p);
		} else {
			Double rx = Double.valueOf(matcher.group());
			Double ry = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Double axisRotation = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Integer largeArcFlag = (matcher.find()) ? Integer.valueOf(matcher.group()) : null;
			Integer sweepFlag = (matcher.find()) ? Integer.valueOf(matcher.group()) : null;
			Double x = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Double y = (matcher.find()) ? Double.valueOf(matcher.group()) : null;
			Point r = new Point(rx, ry);
			Point p = new Point(x,y);
			objects.add(r);
			objects.add(axisRotation);
			objects.add(largeArcFlag);
			objects.add(sweepFlag);
			objects.add(p);
		}
		return objects;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void parse() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = null;
		Class noparams[] = {};
		while (matcher.find()) {
			switch (matcher.group().charAt(0)) {
			case 'm' :
			case 'M' :
				originaux.addAll(moveTo());
				method = getClass().getDeclaredMethod("moveTo", noparams);
				break;
			case 'z' :
			case 'Z' :
				originaux.addAll(closePath());
				method = getClass().getDeclaredMethod("closePath", noparams);
				break;
			case 'l' :
			case 'L' :
				originaux.addAll(lineTo());
				method = getClass().getDeclaredMethod("lineTo", noparams);
				break;
			case 'h' :
			case 'H' :
				originaux.addAll(lineToH());
				method = getClass().getDeclaredMethod("lineToH", noparams);
				break;
			case 'v' :
			case 'V' :
				originaux.addAll(lineToV());
				method = getClass().getDeclaredMethod("lineToV", noparams);
				break;
			case 'c' :
			case 'C' :
				originaux.addAll(curveTo());
				method = getClass().getDeclaredMethod("curveTo", noparams);
				break;
			case 's' : 
			case 'S' :
				originaux.addAll(smoothCurveTo());
				method = getClass().getDeclaredMethod("smoothCurveTo", noparams);
				break;
			case 'q' :
			case 'Q' :
				originaux.addAll(quadraticBezierCurveTo());
				method = getClass().getDeclaredMethod("quadraticBezierCurveTo", noparams);
				break;
			case 't' :
			case 'T' :
				originaux.addAll(smoothQuadraticBezierCurveTo());
				method = getClass().getDeclaredMethod("smoothQuadraticBezierCurveTo", noparams);
				break;
			case 'a' :
			case 'A' :
				originaux.addAll(ellipticalArc());
				method = getClass().getDeclaredMethod("ellipticalArc", noparams);
				break;
			default :
				originaux.addAll((ArrayList<Object>)method.invoke((Object)this,(Object[])null));
			}
		}

		if (debug) {
			System.out.println();
			System.out.println("LISTE DES OBJETS OBTENUS PAR PARSING :");
			System.out.println();
			for (Object o : originaux)
				System.out.println("\t" + o.getClass().getName() + " : " + o);
		}
	}
	
	public ArrayList<Object> getOriginaux() {
		return originaux;
	}

	public ArrayList<Object> getAbsolu() {
		reference = new Point(0,0);
		debut = new Point(0,0);
		
		if (!absolus.isEmpty() && absolus.size() == originaux.size()) {
			return absolus;
		}

		boolean relatif = false;
		int parametres = 0;
		int compteur = 0;
		Character caractere = null;

		ArrayList<Object> objects = new ArrayList<>();

		if (debug) {
			System.out.println();
			System.out.println("LISTE DES OBJETS AVANT TRAITEMENT :");
			System.out.println();
		}

		for (Object o : originaux) {
			if (debug) {
				System.out.println();
				System.out.println("\t" + "OBJET : " + o);
				System.out.println("\t" + "TYPE : " + o.getClass().getName());
				System.out.println("\t" + "RELATIF : " + relatif);
				System.out.println("\t" + "COMPTEUR : " + compteur);
				System.out.println("\t" + "CARACTERE (PRE): " + caractere);
				System.out.println("\t" + "PARAMETRES : " + parametres);
			}
			try {
				Character c = (Character)o;

				if (compteur != 0)
					throw new Error("Les param�tres de la commande " + caractere +  " sont inf�rieurs au nombre attendu.");

				caractere = c;

				switch (c) {
				case 'm' :
					c = 'M';
					relatif = true;
					parametres = 1;
					compteur = parametres;
					break;
				case 'M' :
					relatif = false;
					parametres = 1;
					compteur = parametres;
					break;
				case 'l' :
					c = 'L';
					relatif = true;
					parametres = 1;
					compteur = parametres;
					break;
				case 'L' :
					relatif = false;
					parametres = 1;
					compteur = parametres;
					break;
				case 'h' :
					c = 'H';
					relatif = true;
					parametres = 1;
					compteur = parametres;
					break;
				case 'H' :
					relatif = false;
					parametres = 1;
					compteur = parametres;
					break;
				case 'v' :
					c = 'V';
					relatif = true;
					parametres = 1;
					compteur = parametres;
					break;
				case 'V' :
					relatif = false;
					parametres = 1;
					compteur = parametres;
					break;
				case 'c' :
					c = 'C';
					relatif = true;
					parametres = 3;
					compteur = parametres;
					break;
				case 'C' :
					relatif = false;
					parametres = 3;
					compteur = parametres;
					break;
				case 's' :
					c = 'S';
					relatif = true;
					parametres = 2;
					compteur = parametres;
					break;
				case 'S' :
					relatif = false;
					parametres = 2;
					compteur = parametres;
					break;
				case 'q' :
					c = 'Q';
					relatif = true;
					parametres = 2;
					compteur = parametres;
					break;
				case 'Q' :
					relatif = false;
					parametres = 2;
					compteur = parametres;
					break;
				case 't' :
					c = 'T';
					relatif = true;
					parametres = 1;
					compteur = parametres;
					break;
				case 'T' :
					relatif = false;
					parametres = 1;
					compteur = parametres;
					break;
				case 'a' :
					c = 'A';
					relatif = true;
					parametres = 5;
					compteur = parametres;
					break;
				case 'A' :
					relatif = false;
					parametres = 5;
					compteur = parametres;
					break;
				case 'z' :
					c = 'Z';
					relatif = true;
					parametres = 0;
					compteur = parametres;
					break;
				case 'Z' :
					relatif = false;
					parametres = 0;
					compteur = parametres;
					break;
				}
				objects.add(new Character(c));
			} catch (ClassCastException noc) {
				try {
					Point p = (Point)o;
					
					if (compteur == 0)
						compteur = parametres;
					if (relatif) {
						if (compteur != 5) { // Cas sp�cial du 'A' ou 'a'
							p.x(p.x() + reference.x());
							p.y(p.y() + reference.y());
						}
					} 
					if (compteur == 1) {
						reference = new Point(p);
						if (caractere == 'm' || caractere == 'M') // A v�rifier : cas de sous chemins multiples (m ... z) ... 
							debut = new Point(reference);
					}
					objects.add(new Point(p));
					compteur--;
				} catch (ClassCastException nop) {
					try {
						Double d = (Double)o;
						if (relatif) {
							if (compteur == 1) {
								switch (caractere) {
								case 'h' :
									d += reference.x();
									break;
								case 'v' :
									d += reference.y();
									break;
								}
							}
						}
						objects.add(new Double(d));
						compteur--;
					} catch (ClassCastException nod) {
						try {
							Integer i = (Integer)o;
							objects.add(new Integer(i));
							compteur--;
						} catch (ClassCastException noi) {
							throw new Error("L'objet en cours n'a pas pu �tre caster correctement.");
						}
					}
				}
			}
		}
		absolus.clear();
		absolus.addAll(objects);

		if (absolus.size() != originaux.size())
			throw new Error("La taille de la liste absolu n'est plus identique � celle de d'origine.");

		if (debug) {
			System.out.println();
			System.out.println("LISTE DES OBJETS OBTENUS PAR CONVERSION VERS COORDONNEES ABSOLUES :");
			System.out.println();
			for (Object o : absolus)
				System.out.println(o);
		}

		reference = new Point(0,0);
		debut = new Point(0,0);
		
		return absolus;
	}

	public ArrayList<Object> getRelatif() {
		
		if (!relatifs.isEmpty() && relatifs.size() == originaux.size()) {
			return relatifs;
		}

		boolean relatif = false;
		int parametres = 0;
		int compteur = 0;
		Character caractere = null;

		ArrayList<Object> objects = new ArrayList<>();
		
		if (debug) {
			System.out.println();
			System.out.println("LISTE DES OBJETS AVANT TRAITEMENT :");
			System.out.println();
		}
		
		getAbsolu();

		reference = new Point(0,0);
		debut = new Point(0,0);
		
		boolean premier = true;
		
		for (Object o : absolus) {
			if (debug) {
				System.out.println();
				System.out.println("\t" + "OBJET : " + o);
				System.out.println("\t" + "TYPE : " + o.getClass().getName());
				System.out.println("\t" + "RELATIF : " + relatif);
				System.out.println("\t" + "COMPTEUR : " + compteur);
				System.out.println("\t" + "CARACTERE (PRE): " + caractere);
				System.out.println("\t" + "PARAMETRES : " + parametres);
			}
			try {
				Character c = (Character)o;

				if (compteur != 0)
					throw new Error("Les param�tres de la commande " + caractere +  " sont inf�rieurs au nombre attendu.");

				caractere = c;

				switch (c) {
				case 'm' :
					relatif = true;
					parametres = 1;
					compteur = parametres;
					break;
				case 'M' :
					c = 'm';
					relatif = false;
					parametres = 1;
					compteur = parametres;
					break;
				case 'l' :
					relatif = true;
					parametres = 1;
					compteur = parametres;
					break;
				case 'L' :
					c = 'l';
					relatif = false;
					parametres = 1;
					compteur = parametres;
					break;
				case 'h' :
					relatif = true;
					parametres = 1;
					compteur = parametres;
					break;
				case 'H' :
					c = 'h';
					relatif = false;
					parametres = 1;
					compteur = parametres;
					break;
				case 'v' :
					relatif = true;
					parametres = 1;
					compteur = parametres;
					break;
				case 'V' :
					c = 'v';
					relatif = false;
					parametres = 1;
					compteur = parametres;
					break;
				case 'c' :
					relatif = true;
					parametres = 3;
					compteur = parametres;
					break;
				case 'C' :
					c = 'c';
					relatif = false;
					parametres = 3;
					compteur = parametres;
					break;
				case 's' :
					relatif = true;
					parametres = 2;
					compteur = parametres;
					break;
				case 'S' :
					c = 'S';
					relatif = false;
					parametres = 2;
					compteur = parametres;
					break;
				case 'q' :
					relatif = true;
					parametres = 2;
					compteur = parametres;
					break;
				case 'Q' :
					c = 'q';
					relatif = false;
					parametres = 2;
					compteur = parametres;
					break;
				case 't' :
					relatif = true;
					parametres = 1;
					compteur = parametres;
					break;
				case 'T' :
					c = 't';
					relatif = false;
					parametres = 1;
					compteur = parametres;
					break;
				case 'a' :
					relatif = true;
					parametres = 5;
					compteur = parametres;
					break;
				case 'A' :
					c = 'a';
					relatif = false;
					parametres = 5;
					compteur = parametres;
					break;
				case 'z' :
					relatif = true;
					parametres = 0;
					compteur = parametres;
					break;
				case 'Z' :
					c = 'z';
					relatif = false;
					parametres = 0;
					compteur = parametres;
					break;
				}
				objects.add(new Character(c));
			} catch (ClassCastException noc) {
				try {
					Point p = (Point)o;

					if (compteur == 0)
						compteur = parametres;
					if (!relatif) {
						p.x(p.x() - reference.x());
						p.y(p.y() - reference.y());
					}
					if (compteur == 1) {
						if (premier) {
							reference = new Point(p);
							premier = false;
						} else {
							if (compteur != 5) // Cas sp�cial du 'A' ou 'a'
								reference = new Point(reference.x() + p.x(), reference.y() + p.y());
						}
						if (caractere == 'm' || caractere == 'M') {// A v�rifier : cas de sous chemins multiples (m ... z) ...  
							debut = new Point(reference);
							objects.add(new Point(p));
							compteur--;
							continue;
						}
					}
					objects.add(new Point(p));
					compteur--;
				} catch (ClassCastException nop) {
					try {
						Double d = (Double)o;
						if (!relatif) {
							if (compteur == 1) {
								switch (caractere) {
								case 'h' :
									d -= reference.x();
									break;
								case 'v' :
									d -= reference.y();
									break;
								}
							}
						}
						objects.add(new Double(d));
						compteur--;
					} catch (ClassCastException nod) {
						try {
							Integer i = (Integer)o;
							objects.add(new Integer(i));
							compteur--;
						} catch (ClassCastException noi) {
							throw new Error("L'objet en cours n'a pas pu �tre caster correctement.");
						}
					}
				}
			}
		}
		this.relatifs.clear();
		this.relatifs.addAll(objects);

		if (relatifs.size() != originaux.size())
			throw new Error("La taille de la liste relatifs n'est plus identique � celle de d'origine.");

		if (debug) {
			System.out.println();
			System.out.println("LISTE DES OBJETS OBTENUS PAR CONVERSION VERS COORDONNEES RELATIVES :");
			System.out.println();
			for (Object o : relatifs)
				System.out.println(o);
		}
		
		reference = new Point(0,0);
		debut = new Point(0,0);

		return relatifs;
	}
	
	public String originalVersSvg() {
		return versSvg(originaux);
	}
	
	public String absolusVersSvg() {
		return versSvg(absolus);
	}
	
	public String relatifsVersSvg() {
		return versSvg(relatifs);
	}
	
	private String versSvg(ArrayList<Object> liste) {
		StringBuffer sb = new StringBuffer();
		
		for (Object o : liste) {
			try {
				Character c = (Character)o;
				sb.append('\n');
			} catch (ClassCastException e) {}
			sb.append(" " + o + " ");
		}
		
		return sb.toString();
	}

}
