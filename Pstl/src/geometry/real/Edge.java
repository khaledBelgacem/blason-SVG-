package geometry.real;

import java.util.ArrayList;

public class Edge {
	
	protected Point a;
	protected Point b;
	
	public Edge(Point a, Point b) {
		this.a = a;
		this.b = b;
	}
	
	public Edge clone() {
		return new Edge(a, b);
	}
	
	public Vector atob() {
		return new Vector(a, b);
	}
	
	public Vector btoa() {
		return new Vector(b, a);
	}
	
	public double size() {
		return Point.distance(a, b);
	}
	
	/**
	 * Retourne une liste d'ar�tes compos�e de toutes les paires de points possible.
	 * @param points Une liste de points.
	 * @return Une liste d'ar�tes.
	 */
	
	public ArrayList<Edge> edges(ArrayList<Point> points) {
		ArrayList<Edge> edges = new ArrayList<>();
		for (Point p : points) {
			for (Point q: points) {
				edges.add(new Edge(p, q));
			}
		}
		return edges;
	}
	
	public Edge shortest(ArrayList<Edge> edges) {
		Edge shortest = edges.get(0);
		for (Edge e : edges) {
			if (shortest.size() > e.size())
				shortest = e;
		}
		return shortest;
	}
	
	public Edge longest(ArrayList<Edge> edges) {
		Edge longest = edges.get(0);
		for (Edge e : edges) {
			if (longest.size() < e.size())
				longest = e;
		}
		return longest;
	}
	
	public ArrayList<Edge> sortedCopy(ArrayList<Edge> edges) {
		ArrayList<Edge> copy = new ArrayList<>(); 
		ArrayList<Edge> sorted = new ArrayList<>();
		
		for (Edge e : edges)
			copy.add(e.clone());
		
		while (!copy.isEmpty()) {
			Edge s = shortest(copy);
			sorted.add(s);
			copy.remove(s);
		}
		
		return sorted;
	}
	
	/**
	 * Test d'�galit� entre deux ar�tes, elle sont consid�r�es comme �gale si elle diff�rent apr�s le
	 * 9 �me chiffre apr�s la virgule.
	 * @param edge Une ar�te � comparer.
	 * @return Vrai si leur points sont tr�s proche d'�tre �gaux sinon faux.
	 */
	public boolean equals(Edge edge) {
		return (this.a.equals(edge.a) && this.b.equals(edge.b)) || (this.a.equals(edge.b) && this.b.equals(edge.a));
	}
	
	public ArrayList<Edge> kruskhal(ArrayList<Edge> edges) {
		ArrayList<Edge> sorted = sortedCopy(edges);
		ArrayList<Edge> solution = new ArrayList<>();
		
		for (Edge e : sorted) {
			if (solution.isEmpty())
				solution.add(e);
			else {
				ArrayList<Edge> tentative = new ArrayList<>(solution);
				tentative.add(e);
				if (tentative.containsAll(solution))
					continue;
				else
					solution.add(e);
			}
		}
		return solution;
	}
	
}
