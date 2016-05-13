package heraldry;

import java.util.ArrayList;

import geometry.real.ConvexPolygon;
import geometry.real.Line;
import geometry.real.Point;
import geometry.real.Precision;
import geometry.real.Vector;

public class Canvas extends ConvexPolygon {
	
	/***
	 * Parent du canvas actuel
	 */
	protected Canvas father;
	
	/***
	 * Constructeur de canvas
	 * @param points Une liste de points d�finisant la surface du canvas
	 */
	public Canvas(ArrayList<Point> points) {
		super(points);
		father = null;
	}
	
	/***
	 * Constructeur de canvas
	 * @param canvas Un canvas � copier
	 */
	public Canvas(Canvas canvas) {
		super(new ArrayList<Point>(canvas.points()));
	}
	
	/***
	 * Ajustement du canvas dans l'enveloppe de son parent.
	 */
	
//	public void fit() {
//		if (father == null)
//			return;
//		/**
//		 * Construction d'un rectangle qui contient le polygone convexe � adapter au p�re.
//		 * A - B
//		 * | T |
//		 * D - C
//		 */
//		ArrayList<Point> aklToussainPolygon = Point.aklToussainPolygon(points);
//		ArrayList<Point> boxContainerThis = new ArrayList<>();
//		boxContainerThis.add(new Point(aklToussainPolygon.get(0).x(), aklToussainPolygon.get(2).y())); // A : 0
//		boxContainerThis.add(new Point(aklToussainPolygon.get(1).x(), aklToussainPolygon.get(2).y())); // B : 1
//		boxContainerThis.add(new Point(aklToussainPolygon.get(1).x(), aklToussainPolygon.get(3).y())); // C : 2
//		boxContainerThis.add(new Point(aklToussainPolygon.get(0).x(), aklToussainPolygon.get(3).y())); // D : 3
//				
//		/**
//		 * Construction d'un rectangle qui contient le polygone convexe du p�re.
//		 * A - B
//		 * | F |
//		 * D - C
//		 */
//		aklToussainPolygon = Point.aklToussainPolygon(father.points);
//		ArrayList<Point> boxContainerFather = new ArrayList<>();
//		boxContainerFather.add(new Point(aklToussainPolygon.get(0).x(), aklToussainPolygon.get(2).y())); // A : 0
//		boxContainerFather.add(new Point(aklToussainPolygon.get(1).x(), aklToussainPolygon.get(2).y())); // B : 1
//		boxContainerFather.add(new Point(aklToussainPolygon.get(1).x(), aklToussainPolygon.get(3).y())); // C : 2
//		boxContainerFather.add(new Point(aklToussainPolygon.get(0).x(), aklToussainPolygon.get(3).y())); // D : 3
//		
//		Line TB = new Line(center(), boxContainerThis.get(1));
//		Line BC = new Line(boxContainerFather.get(1), boxContainerFather.get(2)); // Vecteur box du p�re.
//		Point I = Line.intersection(TB, BC);
//		
//		Vector TI = new Vector(center(), I);
//		double coefficient = TI.length() / TB.length();
//		scale(coefficient);
//	}
	
	public void vectorialFit() {
		ArrayList<Vector> centerToVertices = new ArrayList<>();
		ArrayList<Vector> fatherEdges = new ArrayList<>();
		Point center = center();
		
		for (int i = 0; i < points.size(); i++)
			centerToVertices.add(new Vector(center, points.get(i)));
		
		for (int i = 0; i < father.points.size()-1; i++)
			fatherEdges.add(new Vector(father.points.get(i), father.points.get(i+1)));
		
		Vector maximal = null;
		
		for (Vector v : centerToVertices) {
			if (maximal == null) {
				maximal = v;
				continue;
			} else
				maximal = (maximal.length() < v.length()) ? v : maximal;
		}
		
		Point i = null;
		for (Vector v : fatherEdges) {
			i = Vector.intersection(v, new Line(maximal.a(), maximal.b()));
			if (i != null)
				break;
		}
		
		if (i == null) {
			System.out.println("i is null");
			return;
		}
		
		Vector CI = new Vector(center(), i);
		
		double ratio = CI.length() / maximal.length();
		
		points.clear();
		for (Vector v : centerToVertices) {
			v.scale(ratio);
			points.add(v.b());
		}		
	}
	
	public void fit() {
		ArrayList<Vector> rays = new ArrayList<>();
		ArrayList<Vector> edges = new ArrayList<>();
		Point c = center();
		double ratio;
		Vector a = null, b = null, fa = null, fb = null;
		
		for (Point p : points)
			rays.add(new Vector(c.clone(), p.clone()));
		
		for (int i = 0; i < father.points.size(); i++)
			if (i != father.points.size()-1)
				edges.add(new Vector(father.points.get(i).clone(), father.points.get(i+1).clone()));
			else
				edges.add(new Vector(father.points.get(i).clone(), father.points.get(0).clone()));
		
//		ArrayList<Vector> A = new ArrayList<>();
//		ArrayList<Vector> B = new ArrayList<>();
		
		for (Vector r : rays) {
			for (Vector e : edges) {
				Line l1 = new Line(r.a(), r.b());
				Line l2 = new Line(e.a(), e.b());
				Point i =  Line.intersection(l1, l2);
				System.out.println(r.toString() + ':' + i);
				if (i != null) {
					Vector ci = new Vector(c, i);
					if (Precision.areApproximatelyEquals(Vector.cross(r, ci), 0, 9)) {
						// Stockage du plus petit vecteur ci de m�me sens que le rayon
						if (a == null && b == null) {
							a = r;
							b = ci;
						} else if (b.length() > ci.length()) {
							a = r;
							b = ci;
						}
					}
				}
			}
			
			// Choisir le r le plus grand parmi les couples de (r,ci) o� ci est le plus petit calcul�
			if (fa == null && fb == null) {
				fa = a;
				fb = b;
			} else if (fa.length() < a.length()) {
				fa = a;
				fb = b;
			}
			
//			A.add(a);
//			B.add(b);
		}
		
//		System.out.println(a.length());
//		System.out.println(b.length());

		
		
		
		ratio = fb.length()/fa.length();
		System.out.println(ratio);
		
		ArrayList<Point> newPoints = new ArrayList<>();
		
		for (Vector r : rays) {
			r.scale(ratio);
			newPoints.add(r.b());
		}
		
		points = newPoints;
	}

	
	/***
	 * Affecte un canvas dans le canvas courant en centrant son centre de masse.
	 * @param canvas Canevas � ajouter au courant. 
	 */
	public void charge(Canvas canvas) {
		Point A = center();
		Point B = canvas.center();	
		Vector V = new Vector(A, B);
		
		V.scale(-1);
		canvas.translate(V.x(), V.y());
		canvas.father = this;
		
		canvas.fit();
		//canvas.vectorialFit();
	}
	
	/***
	 * Affecte un canvas dans le canvas courant.
	 * @param canvas
	 */
	public void chargeSimple(Canvas canvas) {
		canvas.father = this;
	}
	
	/***
	 * Translation du canvas
	 * @param x D�placement en abscisse
	 * @param y D�placement en ordonn�e
	 */
	public void translate(double x, double y) {
//		System.out.println("x:" + x + "," + "y:" + y);
		Point centroid = centroid(); 
//		System.out.println("centroid : " + centroid);
		centroid.x(centroid.x() + x);
		centroid.y(centroid.y() + y);
//		System.out.println("translated centroid : "+ centroid);
		System.out.println("area before : " + this.area());
		
		for (Point p : points()) {
			System.out.println("p.x : " + Precision.truncate(p.x(), 0));
			System.out.println("p.x + x : " + Precision.truncate((p.x() + x), 0));
			p.x(p.x() + x);
			p.y(p.y() + y);
			
			
		}
		
		System.out.println("area after : " + this.area());

		
//		System.out.println("new centroid : "+ centroid());
	}
	
	/***
	 * Homoth�tie par rapport au point de masse du canvas.
	 * @param d Coefficient
	 */
	public void scale(double d) {
		Point centroid = centroid();
		for (Point p : points()) {
			Vector v = new Vector(centroid, p);
			v.scale(d);
			p.x(v.b().x());
			p.y(v.b().y());
		}
	}
	
	/***
	 * Chaine SVG traduisant le canvas.
	 * @return Un chaine de carat�res SVG
	 */
	public String toSVG() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("<circle cx=\"" + center().x() + "\" cy=\"" + center().y() +"\" r=\"5\" stroke=\"red\" stroke-width=\"3\" fill=\"red\" />");
		sb.append("<path d=");
		sb.append("\"M " + points().get(0).x() + " " + points().get(0).y());
		for (int i = 1; i < points().size(); i++)
			sb.append(" L " + points().get(i).x() + " " + points().get(i).y());
		sb.append(" Z\" stroke=\"black\" fill=\"rgba(0,0,0,0.5)\"/>");
		
		if (father != null)
			sb.append(father.toSVG());
		
		return sb.toString();
	}

}
