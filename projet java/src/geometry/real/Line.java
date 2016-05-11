package geometry.real;

public class Line extends Vector {

	/***
	 * 
	 * Constructeur de droite.
	 * 
	 * @param a Un point quelconque
	 * @param b Un autre point quelconque
	 */
	
	public Line(Point a, Point b) {
		super(a,b);
	}

	/***
	 * 
	 * Savoir si la droite est une fonction affine.
	 * 
	 * @return Vrai si c'est le cas, faux sinon
	 */
	
	public boolean isFunction() {
		return (b().y() - a().y()) != 0 && (b().x() - a().x()) != 0; 
	}
	
	/***
	 * 
	 * Retour du coefficient directeur de la droite
	 * 
	 * @return Un coefficient directeur non null, sinon la droite n'est pas une fonction.
	 */
	
	public Double slope() {
		if (isFunction())
			return (b().y() - a().y()) / (b().x() - a().x());
		return null;
	}
	
	/***
	 * 
	 * Retour de l'ordonnée à l'origine de la droite.
	 * 
	 * @return Une ordonnée à l'origine non null, sinon la droite n'est pas une fonction.
	 */
	
	public Double intercept() {
		if (isFunction())
			return a().y() - slope() * a().x();
		return null;
	}
	
	/***
	 * 
	 * Retourne l'abcisse en fonction de l'ordonnée.
	 * 
	 * @param y Une ordonnée quelconque
	 * @return L'abscisse au point d'ordonnée y
	 */
	
	public Double abscissa(double y) {
		if (isFunction())
			return (y - intercept()) / slope();
		return a().x();
	}
	
	/***
	 * 
	 * Retourne l'ordonnée en fonction de l'abscisse.
	 * 
	 * @param x Une abscisse quelconque
	 * @return L'ordonnée au point d'abscisse x.
	 */
	
	public Double ordinate(double x) {
		if (isFunction())
			return x * slope() + intercept();
		return a().y();
	}
	
	/***
	 * 
	 * Point d'intersection entre la droite courant et une autre droite.
	 * 
	 * @param l L'autre droite
	 * @return Le point d'intersection
	 */
	
	public Point intersect(Line l) {
		if (!isFunction() && !l.isFunction() && dot(this, l) == 0) {
			double x, y;
			if (l.a().x() == l.b().x())
				x = l.a().x();
			else
				x = a().x();
			if (l.a().y() == l.b().y())
				y = l.a().y();
			else
				y = a().y();
			return new Point(x, y);
		}
		if (!isFunction() && !l.isFunction())
			return null;
		if (slope() == l.slope())
			return null;
		if (!isFunction())
			return new Point(a().x(), l.ordinate(a().x()));
		if (!l.isFunction())
			return new Point(l.a().x(), ordinate(l.a().x()));
		Double x = (l.intercept() - intercept()) / (slope() - l.slope());
		return new Point(x, ordinate(x));		
	}
	
	/***
	 * 
	 * Le point d'intersection entre deux droites.
	 * 
	 * @param d Première droite
	 * @param l Seconde droite
	 * @return Le point d'intersection
	 */
	
	public static Point intersection(Line d, Line l) {
		if (d.slope() == l.slope() && d.slope() != null && l.slope() != null)
			return null;
		if (!d.isFunction() && !l.isFunction()) {
			if ((d.a().x() == d.b().x() && l.a().x() == l.b().x()) || (d.a().y() == d.b().y() && l.a().y() == l.b().y()))
				return null;
			else {
				if (d.a().x() == d.b().x())
					return new Point(d.a().x(), l.a().y());
				else
					return new Point(l.a().x(), d.a().y());
			}
		}
		if (!d.isFunction()) {
			if (d.a().y() == d.b().y()) {
				double x = (d.a().y()-l.intercept())/l.slope();
				return new Point(x, l.ordinate(x));
			} else {
				double y = l.ordinate(d.a().x());
				return new Point(d.a().x(), y);
			}
		}
		if (!l.isFunction()) {
			if (l.a().y() == l.b().y()) {
				double x = (l.a().y()-d.intercept())/d.slope();
				return new Point(x, d.ordinate(x));
			} else {
				double y = d.ordinate(l.a().x());
				return new Point(l.a().x(), y);
			}
		}
		Double x = (l.intercept() - d.intercept()) / (d.slope() - l.slope());
		return new Point(x, d.ordinate(x));		
	}
	
	@Override
	public String toString() {
		if (isFunction())
			return "y = " + slope() + "x + " + intercept();
		else
			return "y = " + a().y();
	}
}
