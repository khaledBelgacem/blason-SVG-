package geometry.real;

import java.util.ArrayList;

public class Shape {
	
	protected ArrayList<Point> points;
	
	public Shape() {
		this.points = new ArrayList<>();
	}
	
	public Shape(ArrayList<Point> points) {
		this.points = points;
	}
	
	public ArrayList<Point> points() {
		return points;
	}
	
	public ArrayList<Edge> path() {
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for (int i = 0; i < points.size(); i++) {
			if (i != points.size()-1)
				edges.add(new Edge(points.get(i), points.get(i+1)));
			else
				edges.add(new Edge(points.get(i), points.get(0)));
		}
		return edges;
	}
	
	public ConvexPolygon convexHull() {
		return new ConvexPolygon(points);
	}
	
}
