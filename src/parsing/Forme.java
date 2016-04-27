package parsing;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import geometry.real.Point;

public class Forme extends ArrayList<Object> implements Superposable {

	private static final long serialVersionUID = 6810470252709022505L;
	Transformation transform;
	Style style;
	Chemin path;
	
	public Forme(String d, Transformation transform, Style style) {
//		String[] tableau = d.split("[ ,]");
//		ArrayList<Double> coordonnees = new ArrayList<>();
//		
//		for (String s : tableau) {
//			try {
//				double coordonnee = Double.valueOf(s);
//				coordonnees.add(coordonnee);
//				if (coordonnees.size() == 2) {
//					this.add(new Point(coordonnees.get(0), coordonnees.get(1)));
//					coordonnees.clear();
//				}
//			} catch (NumberFormatException e) {
//				this.add(new Character(s.charAt(0)));
//			}
//		}
		path = new Chemin(d);
		try {
			path.parse();
			clear();
			addAll(path.getAbsolu());	
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			System.out.println("Erreur de parsing sur : " + d);
			e.printStackTrace();
			System.exit(-1000);
		}
		
		
		this.transform = transform;
		this.style = style;
	}
	
	public Forme(ArrayList<Object> objects, Style style) {
		this.addAll(objects);
		this.style = style;
	}
	
	public void calculerTransformation() {
		for (Object o : this) {
			try {
				Point p = (Point)o;
				if (this.transform != null)
					this.transform.apply(p);
			} catch (ClassCastException e) {}
		}
		this.transform = null;
	}
	
	public void appliquerTransformation() {
		if (transform != null) {
			for (Object o : this) {
				try {
					Point p = (Point)o;
					p.x(transform.a * p.x() + transform.c * p.y() + transform.e);
					p.y(transform.b * p.x() + transform.d * p.y() + transform.f);
				} catch (ClassCastException e) {}
			}
			transform = null;
		}
	}
	
	public void applyTranform() {
		if (transform != null) {
			for (Object o : this) {
				try {
					Point p = (Point)o;
					transform.apply(p);
				} catch (ClassCastException e) {
					
				}
			}
			transform = null;
		}
	}
	
	public String svg() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("\n<path ");
		if (transform != null)
			sb.append("\ntransform = '" + transform + "' ");
		if (style != null)
			sb.append("\nstyle = '" + style + "'");
		sb.append("\nd = ' ");
		sb.append(path.absolusVersSvg());
		sb.append("' />");
		
		return sb.toString();
	}
	
}
