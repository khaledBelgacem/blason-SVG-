package heraldique;

import geometry.real.Point;
import heraldique.enumeration.Email;
import heraldique.enumeration.Metal;

import java.util.ArrayList;

public class Etoile {

	private Email email ; 
	private Metal metal ;
	private ArrayList<Point> points ; 

	public Etoile(Email em){
		this.email=em ; 
		points =  new ArrayList<Point>() ; 
		this.points= new ArrayList<Point>() ; 
		this.points.add(new Point(5,1));
		this.points.add(new Point(6,4));
		this.points.add(new Point(9,4));
		this.points.add(new Point(7,6));
		this.points.add(new Point(8,9));
		this.points.add(new Point(5,7)); 
		this.points.add(new Point(2,9));
		this.points.add(new Point(3,6));
		this.points.add(new Point(1,4));
		this.points.add(new Point(4,4)); 
	}
	public Etoile(Metal met){
		this.metal=met ; 
		this.points= new ArrayList<Point>() ; 
		this.points.add(new Point(5,1));
		this.points.add(new Point(6,4));
		this.points.add(new Point(9,4));
		this.points.add(new Point(7,6));
		this.points.add(new Point(8,9));
		this.points.add(new Point(5,7)); 
		this.points.add(new Point(2,9));
		this.points.add(new Point(3,6));
		this.points.add(new Point(1,4));
		this.points.add(new Point(4,4)); 
	}
	public ArrayList<Point> getPoints() {
		return this.points ; 
	}
	public Email getEmail(){
		return this.email ; 
	}
	public Metal getMetal(){
		return this.metal ; 
	}
	public int getDefaultSize() {
		return 8 ; 
	}
}
