package geometry.real;

public class Edge {
	
	protected Point a;
	protected Point b;
	
	public Edge(Point a, Point b) {
		this.a = a;
		this.b = b;
	}
	
	public Vector atob() {
		return new Vector(a, b);
	}
	
	public Vector btoa() {
		return new Vector(b, a);
	}
	
}
