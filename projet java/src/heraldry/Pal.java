package heraldry;

import java.util.ArrayList;

import geometry.real.Point;
import geometry.real.Vector;

public class Pal extends Canvas {
	
	private String email;
	
	public Pal(ArrayList<Point> points, String email) {
		super(points);
		this.email = email;
	}
	
	public Point center() {
		Vector v = new Vector(points.get(0), points.get(2));
		return v.middle();
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
