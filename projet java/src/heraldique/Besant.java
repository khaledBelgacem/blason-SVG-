package heraldique;

import geometry.real.Point;
import heraldique.enumeration.Metal;

public class Besant {
	Metal metal ; 
	int rayon ; 
	Point centre ;
	
	public Besant (Metal m , int r, Point p) {
		this.metal = m ; 
		this.rayon = r ; 
		this.centre = p  ; 
	}
	public Besant (){
		this.metal = Metal.AUCUN ; 
		this.rayon = 2 ;
		this.centre = new Point ( 0,0) ; 
	}
	
	public Besant (Metal m , int r){
		this.metal = Metal.AUCUN ; 
		this.rayon = 2 ;
	}
	public Point getCentre (){
		return this.centre ; 
	}
	public int getRayon(){
		return this.rayon ; 
	}
	public void setCentre(Point p){
		this.centre = p ; 
	}
	public void setRayon(int r) {
		this.rayon = r ; 
	}
	public Metal getMetal (){
		return this.metal ;  
	}
	public void setMetal (Metal m) {
		this.metal = m ; 
	}
}
