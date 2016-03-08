package geometry.real;

import java.util.ArrayList;

public class Circle {

	/***
	 * <p> Point correspondant au centre du cercle </p>
	 */
	private Point center;
	
	/***
	 * <p> Valeur du rayon du cercle </p>
	 */
	private double radius;
	
	/***
	 * 
	 * Constructeur de cercle 
	 * 
	 * @param x La coordonnée en abscisse du centre du cercle
	 * @param y La coordonnée en ordonnée du centre du cercle
	 * @param radius Le rayon du cercle
	 */
	
	public Circle (double x, double y, double radius) {
		center = new Point(x, y);
		this.radius = radius;
	}
	
	/***
	 * Constructeur de cercle
	 * 
	 * @param center Point correspondant au centre du cercle
	 * @param radius Rayon du cercle
	 */
	
	public Circle (Point center, double radius) {
		this.center = new Point(center.x(), center.y());
		this.radius = radius;
	}
	
	/***
	 * 
	 * @return Le rayon du cercle
	 */
	
	public double radius() {
		return radius;
	}
	
	/***
	 * 
	 * @return Le point correspondant au centre du cercle
	 */
	
	public Point center() {
		return center;
	}
	
	/***
	 * 
	 * Permet de vérifier si un point est inclus dans le cercle (intérieur + bordure)
	 * Attention ! L'imprécision due à l'utilisation des doubles est conservée mais 
	 * dans le cas où le carré de la distance et le carré du rayon diffèrent de moins
	 * d'un milliardième alors on consière que le point est dans le cercle.  
	 * 
	 * @param p Le point à vérifier
	 * @return Vrai si le point est dans ou sur le cercle, sinon faux
	 */
		
	public boolean isIn(Point p) {
		double distanceSq = Point.distanceSquare(center(), p);
		double radiusSq = radius * radius;
		if (Math.abs(distanceSq - radiusSq) < 1.0/Math.pow(10, 9))
			return true;
		else if (distanceSq <= radiusSq)
			return true;
		else
			return false;
	}
	
	/***
	 * 
	 * Permet de vérifier si un point est sur le cercle
	 * 
	 * @param p
	 * @return Vrai si le point est sur le cercle, sinon faux
	 */
	
	public boolean isOn(Point p) {
		return Precision.areApproximatelyEquals(Point.distanceSquare(center, p), radius * radius, 9);
	}
	
	/***
	 * 
	 * Permet de vérifier si un ensemble est inclus dans la cercle (intérieur + bordure)
	 * 
	 * @param points Une liste de points
	 * @return Vrai si tous les points sont inclus dans le cercle, sinon faux
	 */
	
	public boolean areIn(ArrayList<Point> points) {
		for (Point p : points)
			if (!isIn(p))
				return false;
		return true;
	}

//	public boolean isIntersect(Vector v) {
//		return (isIn(v.a()) || isIn(v.b()));
//	}
	
	/***
	 * 
	 * Permet de déterminer les intersections en le cercle courrant et un autre cercle.
	 * 
	 * @param c Un cercle à tester
	 * @return Une liste de points d'intersection
	 * 
	 */
	
	public ArrayList<Point> intersect(Circle c) {
		ArrayList<Point> points = new ArrayList<Point>();
		double denominator = 2 * (c.center().y() - center().y());
		
		if ( denominator == 0 ) {
			
			double x = (	Math.pow(radius(), 2)
					-		Math.pow(c.radius(), 2)
					+		Math.pow(c.center().x(), 2)
					-		Math.pow(center().x(), 2)	)
					/	( 2 * c.center().x() - 2 * center().x() );
			double A = 	1;
			double B = 	-2 * center().y();
			double C =	Math.pow(center().x(), 2)
					+	Math.pow(x, 2)
					-	2 * center().x() * x
					+	Math.pow(center().y(), 2)
					- 	Math.pow(radius(), 2);
			double D = 	Math.sqrt(Math.pow(B, 2) - 4*A*C);
			double y1 = (-B + D) / (2 * A);
			double y2 = (-B - D) / (2 * A);
			
			points.add(new Point(x, y1));
			points.add(new Point(x, y2));
			
		} else {
		
			double N = (	Math.pow(radius(), 2) 
					- 		Math.pow(c.radius(), 2)
					+		Math.pow(c.center().x(), 2)
					-		Math.pow(center().x(), 2)
					+		Math.pow(c.center().y(), 2)
					-		Math.pow(center().y(), 2)	)
					/		( denominator );
			double cst = ( 2 * c.center().x() - 2 * center().x() )
					/	( denominator );
			double A = Math.pow(cst, 2) + 1;
			double B = 	2*center().y()*cst - 2*center().x() - 2*N*cst;
			double C = 	Math.pow(center().x(), 2) 
					+ 	Math.pow(center().y(), 2)
					+	Math.pow(N, 2)
					- 	2*center().y()*N 
					- 	Math.pow(radius(), 2);
			double D = 	Math.sqrt(Math.pow(B, 2) - 4*A*C);
			double x1 = (-B - D) / (2 * A);
			double x2 = (-B + D) / (2 * A);
			double y1 = N - x1 * cst;
			double y2 = N - x2 * cst;
			
			points.add(new Point(x1, y1));
			points.add(new Point(x2, y2));
		
		}
		
		return points;	
	}
	
	/***
	 * 
	 * Permet de déterminer les points d'intersection entre les le cercle courrant et une ligne.
	 * 
	 * @deprecated Dans le cas où la line n'est pas une fonction affine les résultats peuvent conduire à des erreurs. (A corriger)
	 * 
	 * @param l Une ligne à tester
	 * @return Une liste de points d'intersection
	 */
	
	@Deprecated
	public ArrayList<Point> intersect(Line l) {
		ArrayList<Point> points = new ArrayList<Point>();
		double a = l.slope() + 1;
		double b = 2 * (l.slope() * (l.intercept() + center.y() ) - center.x());
		double c = 	Math.pow(center().x(), 2) 
				+ 	Math.pow(center().y(), 2) 
				+ 	Math.pow(l.intercept(), 2) 
				- 	Math.pow(radius(), 2);
		double d =	Math.sqrt(Math.pow(b, 2) - 4*a*c);
		double x1 = (-b-d)/(2*a);
		double x2 = (-b+d)/(2*a);
		double y1 = l.slope()*x1 + l.intercept();
		double y2 = l.slope()*x2 + l.intercept();
		
		points.add(new Point(x1, y1));
		points.add(new Point(x2, y2));
		
		return points;	
	}
	
	/***
	 * 
	 * Calcul le cercle circonscrit à 3 points
	 * 
	 * @param a Un premier point
	 * @param b Un second point
	 * @param c Un troisème point
	 * @return Le cercle circonscrit aux 3 points
	 */

//	public static Circle circumscribed(Point a, Point b, Point c) {
//		
//		Circle Cab = new Circle(a, Point.distance(a, b));
//		Circle Cba = new Circle(b, Point.distance(b, a));
//		ArrayList<Point> abCircleIntersections = Cab.intersect(Cba);
//		Line abBisection = new Line(abCircleIntersections.get(0), abCircleIntersections.get(1));
//		
//		Circle Cac = new Circle(a, Point.distance(a, c));
//		Circle Cca = new Circle(c, Point.distance(c, a));
//		ArrayList<Point> acCircleIntersections = Cac.intersect(Cca);
//		Line acBisection = new Line(acCircleIntersections.get(0), acCircleIntersections.get(1));
//		
//		if (Vector.cross(abBisection, acBisection) != 0) // If points are not aligned
//		{
//			Point p = abBisection.intersect(acBisection);
//			Vector pa = new Vector(p, a);
//			return new Circle(p, pa.length());
//		} else {
//			Vector AB = new Vector(a, b);
//			Vector AC = new Vector(a, c);
//			Vector BC = new Vector(b, c);
//			Vector max = (AB.length() < AC.length()) ? (AC.length() < BC.length()) ? BC : AC : AB;
//			return new Circle(max.middle(), max.length()/2);
//		}		
//	}
	
	/***
	 * 
	 * Retourne le cercle minimal à un ensemble de 3 points.
	 * Ce cercle minimal est soit le cercle de diamètre le plus entre toutes les paires
	 * de points dans le cas d'un triangle obtus et rectangle ou sinon le cercle circonscrit à celui-ci.  
	 * 
	 * @param a Un premier point
	 * @param b Un second point
	 * @param c Un troisème point
	 * @return Un cercle minimal
	 */
	
	public static Circle minimal(Point a, Point b, Point c) {
		Vector ab = new Vector(a, b);
		Vector ac = new Vector(a, c);
		Vector bc = new Vector(b, c);
		
		Vector max = (ab.length() < ac.length()) ? (ac.length() < bc.length()) ? bc : ac : ab;
		
		if (Vector.isObtuse(ab, ac)) {
			//System.out.println("midline");
			return new Circle(max.middle(), max.length()/2);
		}
		else if (Vector.angle(ab, ac) < Math.PI/4) {
			//System.out.println("midline");
			return new Circle(max.middle(), max.length()/2);
		}
		else {
			//System.out.println("circumscribed");
			return circumscribed(a, b, c);
		}
			
	}
	
	public static Circle circumscribed(Point p1, Point p2, Point p3) {
		double p1x = p1.x();
        double p1y = p1.y();
        double p2x = p2.x();
        double p2y = p2.y();
        double p3x = p3.x();
        double p3y = p3.y();
        
        double a = p2x - p1x;
        double b = p2y - p1y;     
        double c = p3x - p1x;
        double d = p3y - p1y;
        double e = a * (p2x + p1x) * 0.5 + b * (p2y + p1y) * 0.5;
        double f = c * (p3x + p1x) * 0.5 + d * (p3y + p1y) * 0.5;
        double det = a*d - b*c;    

        double cx = (d*e - b*f)/det;
        double cy = (-c*e + a*f)/det;
        
       return new Circle(cx, cy, Math.sqrt( (p1x-cx)*(p1x-cx) + (p1y-cy)*(p1y-cy)));
	}
	
	@Override
	public String toString() {
		return center().toString() + " : " + radius();
	}
	
	@Override
	public boolean equals(Object obj) {
		Circle c = (Circle)obj;
		return center.equals(c.center) && Precision.areApproximatelyEquals(radius, c.radius, 9);
	}
}
