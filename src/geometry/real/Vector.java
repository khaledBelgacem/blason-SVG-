package geometry.real;

public class Vector {

	/***
	 * Abscisse du vecteur.
	 */
	private double x;
	
	/***
	 * Ordonnée du vecteur.
	 */
	private double y;
	
	/***
	 * Base du vecteur.
	 */
	private Point a;
	
	/***
	 * Pointe du vecteur.
	 */
	private Point b;
	
	
	/***
	 * Constructeur de vecteur.
	 * 
	 * @param x Abscisse du vecteur
	 * @param y Ordonnée du vecteur
	 */
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
		this.a = new Point(0,0);
		this.b = new Point(x,y);
	}
	
	/***
	 * Constructeur de vecteur.
	 * 	
	 * @param a Base du vecteur
	 * @param b Pointe du vecteur
	 */
	public Vector(Point a, Point b) {
		if (a != null && b !=null) {
			this.x = b.x() - a.x();
			this.y = b.y() - a.y();
		}
		this.a = a;
		this.b = b;
	}
	
	/***
	 * Retourne l'abscisse du vecteur.
	 * @return l'abscisse du vecteur
	 */
	public double x() {
		return x;
	}
	
	/***
	 * Retourne l'ordonnée du vecteur.
	 * @return l'ordonnée du vecteur
	 */
	public double y() {
		return y;
	}
	
	/***
	 * Retourne la base du vecteur.
	 * @return Le point à la base du vecteur
	 */
	public Point a() {
		return a;
	}
	
	/***
	 * Retourne la pointe du vecteur.
	 * @return Le point à la pointe du vecteur
	 */
	public Point b() {
		return b;
	}
	
	/***
	 * Longueur du vecteur.
	 * @return la longueur du vecteur
	 */
	public double length() {
		return Math.sqrt(Point.distanceSquare(a, b));
	}
	
	/***
	 * Longueur du vecteur au carré.
	 * @return le carré de la longueur du vecteur
	 */
	
	public double lengthSquare() {
		return Point.distanceSquare(a, b);
	}
	
	/***
	 * Le milieu du vecteur.
	 * @return Le point au milieu du vecteur
	 */
	public Point middle() {
		return new Point((a.x() + b.x())/2, (a.y() + b.y())/2);
	}
	
	/***
	 * Translation du vecteur.
	 * @param x Translation en x
	 * @param y Translation en y
	 */
	public void translate(double x, double y) {
		this.x += x;
		this.y += y;
		this.a = new Point(this.a().x() + x, this.a().y() + y);
		this.b = new Point(this.b().x() + x, this.b().y() + y);

	}
	
	/***
	 * Homothétie sur le vecteur.
	 * Seul la pointe est déplacée lors de l'opération, la base reste inchangée.
	 * @param val Coefficient à appliquer sur le vercteur
	 */
	public void scale(double val) {
		this.x *= val;
		this.y *= val;
		double x = a.x();
		double y = a.y();
		translate(-x, -y);
		this.b = new Point(this.b().x() * val, this.b().y() * val);
		translate(x, y);
	}
	
	// Non implemente
	public void rotate(double angle) {
		
	}
	
	/***
	 * Produit vectoriel en deux vecteurs.
	 * @param u Un vecteur
	 * @param v Un autre vecteur
	 * @return Le produit vectoriel
	 */
	public static double cross(Vector u, Vector v) {
		return u.x() * v.y() - u.y() * v.x(); 
	}
	
	/***
	 * Produit scalaire entre deux vecteurs.
	 * @param u Un vecteur
	 * @param v Un autre vecteur
	 * @return Le produit scalaire
	 */
	public static double dot(Vector u, Vector v) {
		return u.x() * v.x() + u.y() * v.y();
	}
	
	/***
	 * Angle en radiant entre deux vecteur.
	 * @param u Un vecteur
	 * @param v Un autre vecteur
	 * @return Une angle en radiant
	 */
	public static double angle(Vector u, Vector v) {
		return Math.acos( dot(u,v) / ( u.length() * v.length() ) );
	}
	
	/***
	 * Savoir si un angle est obtus.
	 * @param u Un vecteur
	 * @param v Un autre vecteur
	 * @return Vrai si l'angle est obtus, sinon faux
	 */
	public static boolean isObtuse(Vector u, Vector v) {
		return angle(u, v) > Math.PI/2;
	}
	
	public static Point intersection(Vector u, Vector v) {
		Line a = new Line(u.a, u.b);
		Line b = new Line(v.a, v.b);
		Point i = Line.intersection(a, b);
		if (u.a.x() < i.x() || u.b.x() > i.x())
			return null;
		else
			return i;
	}
	
	public static Point intersection(Vector u, Line b) {
		Line a = new Line(u.a, u.b);
		Point i = Line.intersection(a, b);
		if (u.a.x() < i.x() || u.b.x() > i.x())
			return null;
		else
			return i;
	}
	
	@Override
	public String toString() {
		return "[" + a().toString() + ";" + b().toString() + "]";
	}

}
