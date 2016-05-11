package heraldique;

import geometry.real.Point;
import heraldique.enumeration.Email;

public class Tourteau {
	Email email  ; 
	int rayon ; 
	Point centre ;
	
	public Tourteau (Email e , int r, Point p) {
		this.email = e ; 
		this.rayon = r ; 
		this.centre = p  ; 
	}
	public Tourteau (Email e , int r) {
		this.email = e ; 
		this.rayon = r ; 
	}
	public Tourteau(){
		this.email = Email.AUCUN ; 
		this.rayon = 2 ;
		this.centre = new Point ( 0,0) ; 
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
	public Email getEmail (){
		return this.email ;  
	}
	public void setMetal (Email e) {
		this.email = e; 
	}
}
