package heraldique.generique;

import java.util.ArrayList;

import geometry.real.ConvexPolygon;
import geometry.real.Line;
import geometry.real.Point;
import geometry.real.Precision;
import geometry.real.Vector;
import heraldique.enumeration.Email;
import heraldique.enumeration.Metal;

public class Support extends ConvexPolygon {
	protected ArrayList<String> forme ; 
	protected Email email;
	protected Metal metal;
	protected Support dessous;
	protected Support dessus;
	protected int rotation ;
	public Support() {
		super(new ArrayList<Point>());
		this.email = Email.AUCUN;
		this.metal = Metal.AUCUN;
		this.dessous = null;
		this.dessus = null;
		this.rotation=0 ; 
		this.forme= new ArrayList<String>() ;
	}

	public Support(ArrayList<Point> points, Email email) {
		super(points);
		this.email = email;
		this.metal = Metal.AUCUN;
		this.dessous = null;
		this.dessus = null;
		this.rotation=0 ; 
		this.forme= new ArrayList<String>() ;
	}

	public Support(ArrayList<Point> points, Metal metal) {
		super(points);
		this.email = Email.AUCUN;
		this.metal = metal;
		this.dessous = null;
		this.dessus = null;
		this.rotation=0 ; 
		this.forme= new ArrayList<String>() ;
	}
	public Support(ArrayList<Point> points, String couleur) {
		super(points);
		if (couleur.equals("azur")){
			this.email = Email.AZUR;
			this.metal= Metal.AUCUN ;
		}
		else if(couleur.equals("argent")){
			this.email = Email.AUCUN;
			this.metal=Metal.ARGENT ; 
		}
		else if(couleur.equals("or")){
			this.email = Email.AUCUN;
			this.metal = Metal.OR;
		}
		else if(couleur.equals("sable")) {
			this.email = Email.SABLE ;
			this.metal= Metal.AUCUN ;
		}
		else if(couleur.equals("orange")){
			this.email =Email.ORANGE;
			this.metal=Metal.AUCUN;
		}
		else if(couleur.equals("gueules")){
			this.email =Email.GUEULES;
			this.metal=Metal.AUCUN;
		}
		else if(couleur.equals("pourpre")){
			this.email =Email.POURPRE;
			this.metal=Metal.AUCUN;
		}
		else if(couleur.equals("carnation")){
			this.email =Email.CARNATION;
			this.metal=Metal.AUCUN;
		}
		else if(couleur.equals("sinople")){
			this.email =Email.SINOPLE;
			this.metal=Metal.AUCUN;
		}
		else if(couleur.equals("transparent")) {
			this.email = Email.AUCUN ; 
			this.metal = Metal.AUCUN ; 
		}
		this.dessous = null;
		this.dessus = null;
		this.rotation=0; 
		this.forme= new ArrayList<String>() ;
	}
	public Support(ArrayList<Point> points, String couleur,String rotation) {
		super(points);
		if (couleur.equals("azur")){
			this.email = Email.AZUR;
			this.metal= Metal.AUCUN ;
		}
		else if(couleur.equals("argent")){
			this.email = Email.AUCUN;
			this.metal=Metal.ARGENT ; 
		}
		else if(couleur.equals("or")){
			this.email = Email.AUCUN;
			this.metal = Metal.OR;
		}
		else if(couleur.equals("sable")) {
			this.email = Email.SABLE ;
			this.metal= Metal.AUCUN ;
		}
		else if(couleur.equals("orange")){
			this.email =Email.ORANGE;
			this.metal=Metal.AUCUN;
		}
		else if(couleur.equals("gueules")){
			this.email =Email.GUEULES;
			this.metal=Metal.AUCUN;
		}
		else if(couleur.equals("pourpre")){
			this.email =Email.POURPRE;
			this.metal=Metal.AUCUN;
		}
		else if(couleur.equals("carnation")){
			this.email =Email.CARNATION;
			this.metal=Metal.AUCUN;
		}
		else if(couleur.equals("sinople")){
			this.email =Email.SINOPLE;
			this.metal=Metal.AUCUN;
		}
		else if(couleur.equals("transparent")) {
			this.email = Email.AUCUN ; 
			this.metal = Metal.AUCUN ; 
		}
		this.dessous = null;
		this.dessus = null;
		if(rotation.equals("bande")){
			this.rotation=-45 ; 
		}
		else if (rotation.equals("barre")){
			this.rotation=45 ; 
		}
		else if (rotation.equals("fasce")){
			this.rotation=90 ; 
		}
		else 
			this.rotation=0 ; 
		this.forme= new ArrayList<String>() ;
	}
	public Support(ArrayList<Point> points, String couleur,String rotation,ArrayList<String>forme) {
		super(points);
		if (couleur.equals("azur")){
			this.email = Email.AZUR;
			this.metal= Metal.AUCUN ;
		}
		else if(couleur.equals("argent")){
			this.email = Email.AUCUN;
			this.metal=Metal.ARGENT ; 
		}
		else if(couleur.equals("or")){
			this.email = Email.AUCUN;
			this.metal = Metal.OR;
		}
		else if(couleur.equals("sable")) {
			this.email = Email.SABLE ;
			this.metal= Metal.AUCUN ;
		}
		else if(couleur.equals("orange")){
			this.email =Email.ORANGE;
			this.metal=Metal.AUCUN;
		}
		else if(couleur.equals("gueules")){
			this.email =Email.GUEULES;
			this.metal=Metal.AUCUN;
		}
		else if(couleur.equals("pourpre")){
			this.email =Email.POURPRE;
			this.metal=Metal.AUCUN;
		}
		else if(couleur.equals("carnation")){
			this.email =Email.CARNATION;
			this.metal=Metal.AUCUN;
		}
		else if(couleur.equals("sinople")){
			this.email =Email.SINOPLE;
			this.metal=Metal.AUCUN;
		}
		else if(couleur.equals("transparent")) {
			this.email = Email.AUCUN ; 
			this.metal = Metal.AUCUN ; 
		}
		this.dessous = null;
		this.dessus = null;
		if(rotation.equals("bande")){
			this.rotation=-45 ; 
		}
		else if (rotation.equals("barre")){
			this.rotation=45 ; 
		}
		else if (rotation.equals("fasce")){
			this.rotation=90 ; 
		}
		else 
			this.rotation=0 ;
		
		
		this.forme=new ArrayList<String>(forme);
		
	}
	public Support(double largeur, double hauteur) {
		super();
		points.add(new Point(0, 0));
		points.add(new Point(largeur, 0));
		points.add(new Point(largeur, hauteur));
		points.add(new Point(0, hauteur));
		this.originalPoints = new ArrayList<Point>()  ; 
		for (Point p : points ) {
			this.originalPoints.add(new Point(p)) ; 
		}
		this.points = ConvexPolygon.jarvis(points);
		this.email = Email.AUCUN;
		this.metal = Metal.AUCUN;
		this.dessous = null;
		this.dessus = null;
		this.rotation=0 ; 
		this.forme=new ArrayList<String>() ;
	}

	public Support clone() {
		return new Support(this.points, this.email);
	}
	public ArrayList<Point> getPoints() {
		return super.points() ; 
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

	public double adapter() {
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
		homothetie(coefficient*0.8);
		return coefficient*0.8 ; 
	}
	public void charge(Support support,int flag) {
		double coeff ; 
		dessus = support;
		support.dessous = this;
		if (flag==1){ //une figure a  adapter
			centrer(support);
			coeff=support.adapter();
			support.points = support.originalPoints ; 
			centrer(support)  ;
			support.homothetie(coeff);
		}
		//else // un support on ne change pas ca taille 
		//support.points = support.originalPoints ; 

	}

	public void operation() {
	}

	public String svg() {
		StringBuffer sb = new StringBuffer();
		String couleur = TraducteurEmail.couleur(email) + TraducteurMetal.couleur(metal);
		if (email == Email.AUCUN && metal == Metal.AUCUN)
			couleur = TraducteurEmail.couleur(email);
		else {
			couleur = (email == Email.AUCUN) ?  TraducteurMetal.couleur(metal):TraducteurEmail.couleur(email) ; 
		}
		int debut = 0 ; 
		int i ; 
		sb.append("\n//nouvelle forme de couleur "+couleur+"\n"); 
		sb.append("<path d=");
		if(this.forme.size()==0){
			sb.append("\"M " + this.points.get(0).x() + " " + this.points.get(0).y() + " ");
			for (i = debut+1; i < this.points.size(); i++)
				sb.append(" L " + this.points.get(i).x() + " " + this.points.get(i).y() + " ");
		}
		else {
			int idf=0;  
			int idp=0 ;
			sb.append("\" ");
			while(idf<forme.size() && idp<points.size()){
				try
				{
					Double.parseDouble(forme.get(idf).split(",")[0]);
					sb.append(this.points.get(idp).x() + " " + this.points.get(idp).y() + " ");
					idp=idp+1; 
					idf=idf+1;
				}
				catch(NumberFormatException e)
				{
					sb.append(forme.get(idf)+" ");
					if (forme.get(idf).equals("Z")){
						if (this.rotation!=0){
							sb.append("\" stroke=\"" + "black" + "\" stroke-width =\"0.1px" + "\" transform=\"rotate("+ this.rotation+","+this.center().x()+","+ this.center().y()+")\" fill=\"" + couleur + "\"/>");
						}
						else 
							sb.append("\" stroke=\"" + "black" + "\" stroke-width =\"0.1px" + "\" fill=\"" + couleur + "\"/>");
						sb.append("\n");
						sb.append("<path d=\"");
					}
					idf=idf+1;
				}
			}
		}
		double stroke ; 
		if (couleur.equals("rgba(0,0,0,0)"))
			stroke=0.03 ; 
		else 
			stroke = 0.1 ;
		if (this.rotation!=0){
			sb.append("Z \" stroke=\"" + "black" + "\" stroke-width =\""+stroke+"px" + "\" transform=\"rotate("+ this.rotation+","+this.center().x()+","+ this.center().y()+")\" fill=\"" + couleur + "\"/>");
		}
		else 
			sb.append(" Z \" stroke=\"" + "black" + "\" stroke-width =\""+stroke+"px" + "\" fill=\"" + couleur + "\"/>");
		if (dessus != null)
			sb.append(dessus.svg());
		return sb.toString();
	}
}