package heraldique;

import geometry.real.Vector;
import heraldique.enumeration.Email;
import heraldique.enumeration.Metal;
import heraldique.generique.Support;

public class Bande extends Support {

	public Bande(Email email) {
		super(2.33, 8);
		this.email = email;
	}
	
	public Bande(Metal metal) {
		super(2.33, 8);
		this.metal = metal;
	}
	
	public Bande(double hauteur, double largeur, Email email) {
		super(largeur, hauteur);
		this.email = email;
	}
	
	public Bande(double hauteur, double largeur, Metal metal) {
		super(largeur, hauteur);
		this.metal = metal;
	}
	
	@Override
	public void operation() {
		Vector u = new Vector(dessous.isobarycentre(), dessous.points().get(0));
		Vector v = new Vector(dessous.isobarycentre(), dessous.points().get(1));
		rotation(-Vector.angle(u, v)/2);
	}
	
	
}
