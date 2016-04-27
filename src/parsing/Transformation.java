package parsing;

import java.util.ArrayList;

import geometry.real.Point;

public class Transformation {
	
	double a, b, c, d, e, f, g, h, i;
	
	public Transformation(String string) {
		String[] tableau = string.split("[ (,)]");
		
		switch (tableau[0]) {
		case "matrix" :
			a = Double.valueOf(tableau[1]);
			b = Double.valueOf(tableau[2]);
			c = Double.valueOf(tableau[3]);
			d = Double.valueOf(tableau[4]);
			e = Double.valueOf(tableau[5]);
			f = Double.valueOf(tableau[6]);
			g = 0;
			h = 0;
			i = 1;
			break;
		case "translate" :
			a = 1;
			b = 0;
			c = 0;
			d = 1;
			e = Double.valueOf(tableau[1]);
			f = Double.valueOf(tableau[2]);
			g = 0;
			h = 0;
			i = 1;
			break;
		case "scale" :
			a = Double.valueOf(tableau[1]);;
			b = 0;
			c = 0;
			d = Double.valueOf(tableau[2]);
			e = 0;
			f = 0;
			g = 0;
			h = 0;
			i = 1;
			break;
		case "rotate" :
			a = Math.cos(Double.valueOf(tableau[1]));
			b = Math.sin(Double.valueOf(tableau[1]));
			c = -Math.sin(Double.valueOf(tableau[1]));
			d = Math.cos(Double.valueOf(tableau[1]));
			e = 0;
			f = 0;
			g = 0;
			h = 0;
			i = 1;
			break;
		}
	}
	
	public Transformation(Transformation transform) {
		this.a = transform.a;
		this.b = transform.b;
		this.c = transform.c;
		this.d = transform.d;
		this.e = transform.e;
		this.f = transform.e;
		this.g = transform.g;
		this.h = transform.h;
		this.i = transform.i;
	}
	
	public Transformation(
		double a, double b,	double c,
		double d, double e,	double f,
		double g, double h, double i) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
		this.g = g;
		this.h = h;
		this.i = i;
	}
	
	public static Transformation multiplication(Transformation A, Transformation B) {
		double a, c, e, b, d, f, g, h, i;
		
		a = A.a * B.a + A.c * B.b + A.e * B.g;
		c = A.a * B.c + A.c * B.d + A.e * B.h;
		e = A.a * B.e + A.c * B.f + A.e * B.i;
		
		b = A.b * B.a + A.d * B.b + A.f * B.g;
		d = A.b * B.c + A.d * B.d + A.f * B.h;
		f = A.b * B.e + A.d * B.f + A.f * B.i;
		
		g = A.g * B.a + A.h * B.b + A.i * B.g;
		h = A.g * B.c + A.h * B.d + A.i * B.h;
		i = A.g * B.e + A.h * B.f + A.i * B.i;
		
		return new Transformation(a, b, c, d, e, f, g, h, i);
	}
	
	public static Transformation matrix(
			double a, double b,	double c,
			double d, double e,	double f) {
		return new Transformation(a, b, c, d, e, f, 0, 0, 1);
	}
	
	public static Transformation translate(double x, double y) {
		return new Transformation(1, 0, 0, 1, x, y, 0, 0, 1);
	}
	
	public static Transformation scale(double x, double y) {
		return new Transformation(x, 0, 0, y, 0, 0, 0, 0, 1);
	}
	
	public static Transformation rotate(double angle) {
		return new Transformation(Math.cos(angle), Math.sin(angle), -Math.sin(angle), Math.cos(angle), 0, 0, 0, 0, 1);
	}
	
	public Point apply(Point point) {
		Point p = new Point(0,0);
		Point t = new Point(point);
		p.x(a * t.x() + c * t.y() + e);
		p.y(b * t.x() + d * t.y() + f);
		return p;
	}
	
	public ArrayList<Point> apply(ArrayList<Point> points) {
		ArrayList<Point> list = new ArrayList<>();
		
		for (Point p : points) {
			list.add(apply(p));
		}
		
		return list;
	}
	
	public Point apply(Object object) {
		try {
			Point point = (Point)object;
			Point p = new Point(0,0);
			p.x(a * point.x() + c * point.y() + e);
			p.y(b * point.x() + d * point.y() + f);
			return p;
		} catch (ClassCastException e) {
			return null;
		}
	}
	
	public Forme apply(Forme forme) {
		ArrayList<Object> list = new ArrayList<>();
		
		for (Object o : forme) {
			Point p = apply(o);
			if (p != null)
				list.add(apply(p));
			else
				list.add(o);
		}
		
		return new Forme(list, forme.style);
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("matrix(" + a + "," + b + "," + c + "," + d + "," + e + "," + f + ")");
		return sb.toString();
	}
}
