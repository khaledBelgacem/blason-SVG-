package heraldique.generique;

import java.lang.reflect.Array;
import java.util.ArrayList;

import geometry.real.ConvexPolygon;
import geometry.real.Line;
import geometry.real.Point;
import geometry.real.Precision;
import geometry.real.Vector;
import heraldique.Besant;
import heraldique.Tourteau;
import heraldique.enumeration.Email;
import heraldique.enumeration.Metal;

public class Support extends ConvexPolygon {

	protected Email email;
	protected Metal metal;
	protected Support dessous;
	protected Support dessus;
	
	public Support() {
		super(new ArrayList<Point>());
		this.email = Email.AUCUN;
		this.metal = Metal.AUCUN;
		this.dessous = null;
		this.dessus = null;
	}
	
	public Support(ArrayList<Point> points, Email email) {
		super(points);
		this.email = email;
		this.metal = Metal.AUCUN;
		this.dessous = null;
		this.dessus = null;
	}
	
	public Support(ArrayList<Point> points, Metal metal) {
		super(points);
		this.email = Email.AUCUN;
		this.metal = metal;
		this.dessous = null;
		this.dessus = null;
	}
	
	public Support(double largeur, double hauteur) {
		super();
		points.add(new Point(0, 0));
		points.add(new Point(largeur, 0));
		points.add(new Point(largeur, hauteur));
		points.add(new Point(0, hauteur));
		this.points = ConvexPolygon.jarvis(points);
		this.email = Email.AUCUN;
		this.metal = Metal.AUCUN;
		this.dessous = null;
		this.dessus = null;
	}
	
	public Support clone() {
		return new Support(this.points, this.email);
	}
	
	public Point isobarycentre() {
		double x = 0, y = 0, n = points.size();
		for (Point p : points) {
			x += p.x();
			y += p.y();
		}
		return new Point(x/n, y/n);
	}
	
	public void translation(double x, double y) {
		for (Point p : points) {
			p.x(p.x() + x);
			p.y(p.y() + y);
		}
	}
	
	public void homothetie(double k) {
		Point isobarycentre = isobarycentre();
		for (Point p : points) {
			Vector v = new Vector(isobarycentre, p);
			v.scale(k);
			p.x(v.b().x());
			p.y(v.b().y());
		}
	}
	
	public void rotation(double a) {
		Point isobarycentre = isobarycentre();
		for (Point p : points) {
			Vector v = new Vector(isobarycentre, p);
			double x = isobarycentre.x() + (v.b().x() - isobarycentre.x()) * Math.cos(a) - (v.b().y() - isobarycentre.y()) * Math.sin(a);
			double y = isobarycentre.y() + (v.b().x() - isobarycentre.x()) * Math.sin(a) + (v.b().y() - isobarycentre.y()) * Math.cos(a);
			p.x(x);
			p.y(y);
		}
	}
	
	public void centrer(Support support) {
		Point base = center();
		Point pointe = support.center();
		Vector v = new Vector(base, pointe);
		v.scale(-1);
		support.translation(v.x(), v.y());
	}
	
	public void adapter() {
		ArrayList<Vector> rayons = new ArrayList<>();
		ArrayList<Vector> aretes = new ArrayList<>();
		Point isobarycentre = isobarycentre();
		double coefficient = 1;
		Vector rayon = null, base = null;
		
		for (Point p : points)
			rayons.add(new Vector(isobarycentre.clone(), p.clone()));
		
		for (int i = 0; i < dessous.points.size(); i++)
			if (i != dessous.points.size()-1)
				aretes.add(new Vector(dessous.points.get(i).clone(), dessous.points.get(i+1).clone()));
			else
				aretes.add(new Vector(dessous.points.get(i).clone(), dessous.points.get(0).clone()));
		
		for (Vector r : rayons) {
			Vector lr = null, li = null;
			for (Vector a: aretes) {
				Point intersection = Line.intersection(new Line(r.a(), r.b()), new Line(a.a(), a.b()));
				if (intersection != null) {
					Vector ci = new Vector(isobarycentre, intersection);
					if (Precision.areApproximatelyEquals(Vector.cross(r, ci), 0, 9)) {
						if ((lr == null && li == null) || (li.length() > ci.length())) {
							lr = r;
							li = ci;
						}
					}
				}
			}
			if ((rayon == null && base == null) || (base.length() > li.length())) {
				rayon = lr;
				base = li;
			}
		}
		coefficient = base.length()/rayon.length();
		homothetie(coefficient);
	}
	public void charge(Besant b){
		ArrayList<Point>disk = new ArrayList<Point>() ; 
		disk.add(b.getCentre()) ; 
		disk.add(new Point(b.getCentre().x(),b.getCentre().y()+b.getRayon())) ; 
		disk.add(new Point(b.getCentre().x(),b.getCentre().y()-b.getRayon())) ; 
		disk.add(new Point(b.getCentre().x()+b.getRayon(),b.getCentre().y())) ; 
		disk.add(new Point(b.getCentre().x()-b.getRayon(),b.getCentre().y())) ; 
		Support besant = new Support(disk,b.getMetal())  ; 
		dessus = besant ; 
		besant.dessous = this ; 
		centrer(besant) ;
		besant.adapter(); 
	}
	public void charge(Tourteau b){
		ArrayList<Point>disk = new ArrayList<Point>() ; 
		disk.add(b.getCentre()) ; 
		disk.add(new Point(b.getCentre().x(),b.getCentre().y()+b.getRayon())) ; 
		disk.add(new Point(b.getCentre().x(),b.getCentre().y()-b.getRayon())) ; 
		disk.add(new Point(b.getCentre().x()+b.getRayon(),b.getCentre().y())) ; 
		disk.add(new Point(b.getCentre().x()-b.getRayon(),b.getCentre().y())) ; 
		Support tourt = new Support(disk,b.getEmail())  ; 
		dessus = tourt ; 
		tourt.dessous = this ; 
		centrer(tourt) ;
		tourt.adapter(); 
	}
	public void charge(Support support) {
		dessus = support;
		support.dessous = this;
		
		centrer(support);
		support.operation();
		support.adapter();
	}
	
	public void operation() {
	}
	
	public String svg() {
		StringBuffer sb = new StringBuffer();
		String couleur = TraducteurEmail.couleur(email) + TraducteurMetal.couleur(metal);
		
//		if (dessous != null)
//			sb.append(dessous.svg());
		sb.append("<path d=");
		sb.append("\"M " + points().get(0).x() + " " + points().get(0).y());
		for (int i = 1; i < points().size(); i++)
			sb.append(" L " + points().get(i).x() + " " + points().get(i).y());
		sb.append(" Z\" stroke=\"" + couleur + "\" fill=\"" + couleur + "\"/>");
		if (dessus != null)
			sb.append(dessus.svg());
		sb.append("<circle cx=\"" + isobarycentre().x() + "\" cy=\"" + isobarycentre().y() +"\" r=\"1\" stroke=\"red\" stroke-width=\"3\" fill=\"red\" />");

		return sb.toString();
	}
}
