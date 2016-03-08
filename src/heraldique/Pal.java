package heraldique;

import heraldique.enumeration.Email;
import heraldique.enumeration.Metal;
import heraldique.generique.Support;

public class Pal extends Support {

	public Pal(Email email) {
		super(2.33, 8);
		this.email = email;
	}
	
	public Pal(Metal metal) {
		super(2.33, 8);
		this.metal = metal;
	}
	
	public Pal(double hauteur, double largeur, Email email) {
		super(largeur, hauteur);
		this.email = email; 
	}
	
	public Pal(double hauteur, double largeur, Metal metal) {
		super(largeur, hauteur);
		this.metal = metal; 
	}
	
}
