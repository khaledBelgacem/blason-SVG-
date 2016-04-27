package geometry.real;

import java.util.ArrayList;

public class ConvexPolygon {

	/***
	 * Liste de points formant un polygone convexe
	 */
	
	protected ArrayList<Point> points;

	/***
	 * 
	 * Construteur de polygone convexe à partir d'un ensemble de points
	 * 
	 * @param points Une liste de points
	 */
	public ConvexPolygon(ArrayList<Point> points) {
		if (points != null)
			if (!points.isEmpty())
				this.points = jarvis(new ArrayList<Point>(points));
	}
	
	protected ConvexPolygon() {
		this.points = new ArrayList<>(); 
	}
	
	public ArrayList<Point> points() {
		return points;
	}
	
//	public Point centroid() {
//		double x = 0;
//		double y = 0;
//		for (Point p : points) {
//			x += p.x();
//			y += p.y();
//		}
//		return new Point(x/points.size(), y/points.size());
//	}
	
	public Point center() {
		double x = 0, y = 0;
		for (Point p : points) {
			x += p.x();
			y += p.y();
		}
		x /= points.size();
		y /= points.size();
		return new Point(x, y);
	}
	
	/***
	 * 
	 * Calcul du centre de masse du polygone.
	 * 
	 * @return Le point correspondant au centre de masse
	 */
	
	public Point centroid() {
		double x = 0;
		double y = 0;
		double a = 0;
		
		for (int i = 0; i < points.size()-1; i++) {
			Vector u = new Vector(points.get(i).x(), points.get(i).y());
			Vector v = new Vector(points.get(i+1).x(), points.get(i+1).y());
			a += Vector.cross(u, v);
			x += (points.get(i).x() + points.get(i+1).x()) * Vector.cross(u, v);
			y += (points.get(i).y() + points.get(i+1).y()) * Vector.cross(u, v);
		}
		a *= 0.5;
		double cst = 1.0/(6*a);
		
		return  new Point(cst * x, cst * y);
	}
	
	/***
	 * 
	 * Aire du polygone convexe.
	 * 
	 * @return Une aire
	 */
	
//	public double area() {
//		double a = 0;
//		
//		for (int i = 0; i < points.size()-1; i++) {
//			Vector u = new Vector(points.get(i).x(), points.get(i).y());
//			Vector v = new Vector(points.get(i+1).x(), points.get(i+1).y());
//			a += Vector.cross(u, v);
//		}
//		return a;
//	}
	
	public double area() {
		Point center = center();
		double s = 0;
		for (int i = 0; i < (points.size() -  1); i++) {
			double a = Point.distance(points.get(i), points.get(i+1));
			double b = Point.distance(center, points.get(i+1));
			double c = Point.distance(points.get(i), center);
			double p = (a + b + c) / 2;
			s += Math.sqrt(p * (p - a) * (p - b) * (p - c));
		}
		
		double a = Point.distance(points.get(0), points.get(points.size()-1));
		double b = Point.distance(center, points.get(0));
		double c = Point.distance(points.get(points.size()-1), center);
		double p = (a + b + c) / 2;
		s += Math.sqrt(p * (p - a) * (p - b) * (p - c));
		return s;
	}
	
	/***
	 * 
	 * Vérifie si deux double sont de même signe. 
	 * 
	 * @param d Un double
	 * @param c Un autre double
	 * @return Vrai si les dexu doubles sont de même signe, faux sinon
	 */
	
	private static boolean haveSameSign(double d, double c) {
		return ((d < 0 && c < 0) || (d >= 0 && c >= 0)) ? true : false;
	}
	
	/***
	 * 
	 * Algorithme de jarvis : Constitution d'un ensemble de points correspondant 
	 * à l'enveloppe convexe d'un autre ensemble de points.
	 * 
	 * @param points Liste des points à envelopper
	 * @return Une liste de points correspondant à l'enveloppe convexe
	 */
	
	public static ArrayList<Point> jarvis(ArrayList<Point> points) {
		ArrayList<Point> polygon = new ArrayList<Point>();
		
		// Recherche du point d'abscisse minimum comme point de depart
		Point begin = Point.minX(points);
		Point P = begin;		
		Point Q = null;
		Point R = null;
		
		// Ajout de P a l'enveloppe convexe
		polygon.add(new Point(P.x(), P.y()));
		
		// Recherche d'un point Q appartenant a l'enveloppe convexe
		for (Point q : points) {
			if (q.equals(P))
				continue;
			else {
				Vector PQ = new Vector(P, q);
				boolean estCote = true;
				
//				System.out.println("PASS");
//				int i = 1;
				
				for (Point r : points) {					
					Vector PR = new Vector(P, r);
					
//					System.out.println(PQ);
//					System.out.println(PR);
//					System.out.println(Vector.cross(PQ, PR));
//					System.out.println(i++ + "/" + points.size());
//					System.out.println();
					
					if (!haveSameSign(Vector.cross(PQ, PR), 1)) {
						estCote = false;
						break;
					}
				}
				
//				System.out.println(estCote);
				
				if (estCote) {
					Q = q;
					break;
				}
			}
		}
		
		// Ajout de Q a l'enveloppe convexe
		polygon.add(new Point(Q.x(), Q.y()));
			
		// Recherche des points suivants appartenant a l'enveloppe convexe
//		int i = 0;
		while (!begin.equals(R)) {
			
//			System.out.println(++i);
			
			Vector QP = new Vector(Q, P);
			Double pv = null;
			
			for (Point r : points) {
				Vector QR = new Vector(Q, r);
				if (Q.equals(r) || P.equals(r)) {
					continue;
				}	
				
				Double next = Math.acos(Vector.dot(QP, QR) / (QP.length() * QR.length()));

				if (pv == null || pv < next) {
					R = r;
					pv = next;	
				}
				
			}
			
			if (polygon.contains(R))
				break;
			
			if (R == null)
				System.out.println(R);
			
			polygon.add(new Point(R.x(), R.y()));
			
			P = Q;
			Q = R;
		
		}
		
		return polygon;		
	}
	
}
