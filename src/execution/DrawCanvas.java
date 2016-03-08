package execution;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import geometry.real.Point;
import geometry.real.Precision;
import heraldry.Canvas;

public class DrawCanvas {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Canvas shape = generateRandomCanvas(4);
		Canvas father = generateRandomCanvas(4);
		father.charge(shape);
		saveAsSVG("forme.svg", father.toSVG() + shape.toSVG());
		
	}
	
	public static Canvas generateRandomCanvas(int pointsNumber) {
		Random r = new Random();
		ArrayList<Point> points = new ArrayList<>();
		
		for (int i = 0; i < pointsNumber; i++)
			points.add(new Point(Precision.truncate(r.nextDouble() * 1000, 0), Precision.truncate(r.nextDouble() * 1000, 0)));
		
		return new Canvas(points);
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
