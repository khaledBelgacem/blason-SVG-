package execution;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import geometry.real.ConvexPolygon;
import geometry.real.Point;
import heraldry.Ecu;
import heraldry.Pal;

public class EcuTest {
	
	public static void main(String[] args) {
		double w = 630, h = 720;
		ArrayList<Point> points = new ArrayList<>();
		points.add(new Point(0, 0));
		points.add(new Point(w, 0));
		points.add(new Point(w, h));
		points.add(new Point(0, h));
		
		Ecu e = new Ecu (points, "yellow");
		System.out.println(e.center());
		System.out.println(e.center2());
		
		ConvexPolygon cp = new ConvexPolygon(e.points());
		System.out.println(cp.center());
		
		System.out.println(cp.area());
		
		
		
		ArrayList<Point> PalPoints = new ArrayList<>();
		PalPoints.add(new Point(w/3, 0));
		PalPoints.add(new Point(2*w/3, 0));
		PalPoints.add(new Point(2*w/3, h));
		PalPoints.add(new Point(w/3, h));
		
		Pal p = new Pal(PalPoints, "black");
		
		ArrayList<Point> pts = new ArrayList<>();
		pts.add(new Point(0, 0));
		pts.add(new Point(w/100, 0));
		pts.add(new Point(w/100, h/100));
		pts.add(new Point(0, h/100));
		
		Ecu d = new Ecu(pts, "red");
		e.charge(p);
		p.charge(d);
		
		saveAsSVG("ecu.svg", d.toSVG());
		
	}
	
	public static void saveAsSVG(String pathname, String svg) {
		File file = new File(pathname);
		try {
			FileWriter fw = new FileWriter(file);

			String str = "<?xml version='1.0' encoding='utf-8'?>\n<svg xmlns='http://www.w3.org/2000/svg' version='1.1' width='2000' height='2000'>" + '\n';
			str += '\t' + svg;
			str += "</svg>" + '\n';
			fw.write(str);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
