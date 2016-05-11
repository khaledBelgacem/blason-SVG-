package geometry.real;

import java.util.ArrayList;
import java.util.HashMap;

public class Point {

	/***
	 * Coordonn�e d'abscisse
	 */
	
	public double x;
	
	/***
	 * Coordonn�e d'ordonn�e
	 */
	
	public double y;
	
	public Point(Point p) {
		this.x = p.x();
		this.y = p.y();
	}
	
	/***
	 * Constructeur de point.
	 * 
	 * @param x Abscisse
	 * @param y Ordonn�e
	 */
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Point clone() {
		return new Point(this);
	}

	/***
	 * Retourne l'abscisse du point.
	 * 
	 * @return L'abscisse du point
	 */
	
	public double x() {
		return x;
	}
	
	/***
	 * Retourne l'ordonn�e du point.
	 * @return L'ordonn�e du point.
	 */
	
	public double y() {
		return y;
	}
	
	/***
	 * Red�fini l'abscisse du point
	 *  
	 * @param x Une nouvelle abscisse
	 */
	
	public void x(double x) {
		this.x = x;
	}
	
	/***
	 * Red�fini l'ordonn�e du point
	 * @param y
	 */
	
	public void y(double y) {
		this.y = y;
	}
	
	/***
	 * 
	 * Calcul de la distance entre deux points.
	 * 
	 * @param a Premier point
	 * @param b Second point
	 * @return Distance entre les deux points.
	 */
	
	public static double distance (Point a, Point b) {
		return Math.sqrt(Math.pow(b.x-a.x,2) + Math.pow(b.y-a.y, 2));
	}
	
	/***
	 * 
	 * Calcul de la distance au carr� entre deux points.
	 * Pas d'apprioximations.
	 * 
	 * @param a Premier point
	 * @param b Second points
	 * @return Distance au carr� entre les deux points
	 */
	
	public static double distanceSquare (Point a, Point b) {
		return (b.x - a.x) * (b.x - a.x) +  (b.y - a.y) * (b.y - a.y);
	}
	
	/***
	 * 
	 * Recherche de l'abscisse minimale dans une liste de points.
	 * 
	 * @param points Une liste de points
	 * @return Le point ayant la plus petite abscisse
	 */
	
	public static Point minX(ArrayList<Point> points) {
		Point minX = null;
		for (Point p : points) {
			if (minX == null)
				minX = p;
			if (minX.x > p.x)
				minX = p;
		}
		return minX;
	}
	
	/***
	 * Recherche de l'abscisse maximale dans une liste de points.
	 * 
	 * @param points Une liste de points
	 * @return Le point d'abscisse maximale
	 */
	
	public static Point maxX(ArrayList<Point> points) {
		Point maxX = null;
		for (Point p : points) {
			if (maxX == null)
				maxX = p;
			if (maxX.x < p.x)
				maxX = p;
		}
		return maxX;
	}
	
	/***
	 * Recherche de l'ordonn�e minimale dans une liste de points.
	 * 
	 * @param points Une liste de points
	 * @return Le point d'ordonn�e minimale
	 */
	
	public static Point minY(ArrayList<Point> points) {
		Point minY = null;
		for (Point p : points) {
			if (minY == null)
				minY = p;
			if (minY.y > p.y)
				minY = p;
		}
		return minY;
	}
	
	/***
	 * Recherche de l'ordonn�e maximale dans une liste de points.
	 * 
	 * @param points Une liste de points
	 * @return Le point d'ordonn�e maximale
	 */
	
	public static Point maxY(ArrayList<Point> points) {
		Point maxY = null;
		for (Point p : points) {
			if (maxY == null)
				maxY = p;
			if (maxY.y < p.y)
				maxY = p;
		}
		return maxY;
	}
	
	/***
	 * Recherche des points d'abscisses et d'ordonn�es extremums dans une liste de points.
	 * Les points sont tri�s dans l'ordre : minX, maxX, minY, maxY.
	 * 
	 * @param points Une liste de points
	 * @return Une liste de 4 points
	 */
	
	public static ArrayList<Point> aklToussainPolygon (ArrayList<Point> points) {
		ArrayList<Point> polygon = new ArrayList<Point>();
		Point minX = null;
		Point maxX = null;
		Point minY = null;
		Point maxY = null;
		
		for (Point p : points) {
			if (minX == null)
				minX = maxX = minY = maxY = p;
			if (minX.x > p.x)
				minX = p;
			if (maxX.x < p.x)
				maxX = p;
			if (minY.y > p.y)
				minY = p;
			if (maxY.y < p.y)
				maxY = p;
		}		
		polygon.add(minX);
		polygon.add(maxX);
		polygon.add(minY);
		polygon.add(maxY);
		return polygon;
	}
	
	/***
	 * 
	 * Savoir si un point � une abscisse inf�rieur � toute une liste de points.
	 * 
	 * @param point Un point � v�rifier
	 * @param points Liste de points � compar�r
	 * @return Vrai si tous les points de la liste sont sup�rieurs en abscisse, sinon faux
	 */
	
	public static boolean isMinX (Point point, ArrayList<Point> points) {
		Point minX = minX(points);
		if (point.x < minX.x)
			return true;
		return false;
	}
	
	/***
	 * 
	 * Savoir si un point � une abscisse sup�rieur � toute une liste de points.
	 * 
	 * @param point Un point � v�rifier
	 * @param points Liste de points � compar�r
	 * @return Vrai si tous les points de la liste sont inf�rieur en abscisse, sinon faux
	 */
	
	public static boolean isMaxX (Point point, ArrayList<Point> points) {
		Point maxX = maxX(points);
		if (point.x > maxX.x)
			return true;
		return false;
	}
	
	/***
	 * 
	 * Savoir si un point � une ordonn�e inf�rieur � toute une liste de points.
	 * 
	 * @param point Un point � v�rifier
	 * @param points Liste de points � comparer
	 * @return Vrai si tous les points de la liste sont sup�rieur en ordonn�e, sinon faux
	 */
	
	public static boolean isMinY (Point point, ArrayList<Point> points) {
		Point minY = minY(points);
		if (point.y < minY.y)
			return true;
		return false;
	}
	
	/***
	 * 
	 * Savoir si un point � une ordonn�e sup�rieur � toute une liste de points.
	 * 
	 * @param point Un point � v�rifier
	 * @param points Liste de points � comparer
	 * @return Vrai si tous les points de la liste sont inf�rieur en ordonn�e, sinon faux
	 */
	
	public static boolean isMaxY (Point point, ArrayList<Point> points) {
		Point maxY = maxY(points);
		if (point.y > maxY.y)
			return true;
		return false;
	}
	
	/***
	 * 
	 * Filtrage d'une liste de point pour ne consever que les ordonn�es min et max pour
	 * les diff�rentes abscisse pr�sentes dans la liste.
	 * Suppression des points entre deux autres align�s verticalament.
	 * 
	 * @param points Une liste de points
	 * @return Une liste de points r�duites aux extremums en ordonn�es
	 */
	
	public static ArrayList<Point> pixelSort(ArrayList<Point> points) {
		HashMap<Double, ArrayList<Point>> mmpba = new HashMap<Double, ArrayList<Point>>();
		ArrayList<Point> fmmp = new ArrayList<Point>();
		
		for (Point p : points) {
			ArrayList<Point> mmp = mmpba.get(p.x);
			Double d = new Double(p.x);
			if (mmp == null) {
				mmp = new ArrayList<Point>();
				mmp.add(p);
				mmpba.put(d, mmp);
			} else {
				if (isMinY(p, mmp) && mmp.size() > 1) {
					mmp.remove(minY(mmp));
					mmp.add(p);
				} else if (isMaxY(p, mmp) && mmp.size() > 1) {
					mmp.remove(maxY(mmp));
					mmp.add(p);
				}	
			}
		}
		
		for (Double d : mmpba.keySet()) {
			fmmp.addAll(mmpba.get(d));
		}
		
		return fmmp;
	}
	
	/***
	 * Filtrage d'une liste de points afin d'�limininer les points compris dans le polygone des extremums
	 * d'abscisses et d'ordonn�es.
	 * 
	 * @param points Une liste de points
	 * @return Une liste de points r�duite aux points ext�rieurs au polygon d'extremums
	 */
	
	public static ArrayList<Point> aklToussain(ArrayList<Point> points) {
		ArrayList<Point> polygon = aklToussainPolygon(points);
		ArrayList<Point> externs = new ArrayList<Point>(points);
		
		for (Point p : points) {
			if (isInABCD(p, polygon) && !polygon.contains(p))
				externs.remove(p);
		}				
		
		return externs;
	}
	
	/***
	 * 
	 * Savoir si deux doubles ont le m�me signe
	 * 
	 * @param d Premier double
	 * @param c Second double
	 * @return Vrai si les signes sont les m�me, sinon faux
	 */
	
	private static boolean haveSameSign(double d, double c) {
		return ((d < 0 && c < 0) || (d >= 0 && c >= 0)) ? true : false;
	}
	
	/***
	 * 
	 * Savoir si un point P est dans une polygone form� de 4 points.
	 * 
	 * @param P Point � v�rifier
	 * @param polygon Polygone conteneur
	 * @return Vrai si le point est dans le polygon, sinon faux
	 */
	
	public static boolean isInABCD(Point P, ArrayList<Point> polygon) {
		boolean result = false;
		Point A = polygon.get(0);
		Point B = polygon.get(2);
		Point C = polygon.get(1);
		Point D = polygon.get(3);
		
		Vector AC = new Vector(A, C);
		Vector AB = new Vector(A, B);
		Vector AP = new Vector(A, P);
		
		Vector BC = new Vector(B, C);
		Vector BA = new Vector(B, A);
		Vector BP = new Vector(B, P);
		
		result =	(haveSameSign(Vector.cross(AB, AP), Vector.cross(AB, AC))) ?
					(haveSameSign(Vector.cross(BC, BP), Vector.cross(BC, BA))) ?
					(haveSameSign(Vector.cross(AC, AP), Vector.cross(AC, AB))) ?
					true : false : false : false;
		
		Vector AD = new Vector(A, D);
		
		Vector DC = new Vector(D, C);
		Vector DA = new Vector(D, A);
		Vector DP = new Vector(D, P);
		
		result |= 	(haveSameSign(Vector.cross(AD, AP), Vector.cross(AD, AC))) ?
					(haveSameSign(Vector.cross(DC, DP), Vector.cross(DC, DA))) ?
					(haveSameSign(Vector.cross(AC, AP), Vector.cross(AC, AD))) ?
					true : false : false : false;
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		Point p = (Point)obj;
		if (p == null)
			return false;
		else {
			// Comparaisons avec arrondi � 1/(10^9)
			return		Precision.areApproximatelyEquals(x, p.x, 9) 
					&& 	Precision.areApproximatelyEquals(y, p.y, 9);
		}
	}
	
	@Override
	public String toString() {
		return x + "," + y;
	}
	
}
