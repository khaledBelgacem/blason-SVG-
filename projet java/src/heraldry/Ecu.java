package heraldry;

import java.util.ArrayList;

import geometry.real.Point;
import geometry.real.Vector;

public class Ecu extends Canvas {

	private String email;
	
	public Ecu(ArrayList<Point> points, String email) {
		super(points);
		this.email = email;
	}
	
//	public Ecu(double w, double h, String email) {
//		ArrayList<Point> points = new ArrayList<>();
//		points.add(new Point(0, 0));
//		points.add(new Point(w, 0));
//		points.add(new Point(w, h));
//		points.add(new Point(0, h));
//		this.email = email;
//		this(points, email);
//	}
	
	public ArrayList<Point> points() {
		return points;
	}
	
	public double width() {
		return points.get(2).x();
	}
	
	public double height() {
		return points.get(2).y();
	}
	
	public String email() {
		return email;
	}
	
	public Point center() {
		Vector v = new Vector(points.get(0), points.get(2));
		return v.middle();
	}
	
	public Point center2() {
		double x = 0, y = 0;
		for (Point p : points) {
			x += p.x();
			y += p.y();
		}
		x /= points.size();
		y /= points.size();
		return new Point(x, y);
	}
	
	public String toSVG() {
		StringBuffer sb = new StringBuffer();
		
		if (father != null)
			sb.append(father.toSVG());
		
		sb.append("<circle cx=\"" + center().x() + "\" cy=\"" + center().y() +"\" r=\"5\" stroke=\"red\" stroke-width=\"3\" fill=\"red\" />");
		sb.append("<path d=");
		sb.append("\"M " + points().get(0).x() + " " + points().get(0).y());
		for (int i = 1; i < points().size(); i++)
			sb.append(" L " + points().get(i).x() + " " + points().get(i).y());
		sb.append(" Z\" stroke=\""+ email + "\" fill=\""+ email +"\"/>");
		
		
		
		return sb.toString();
	}

}
