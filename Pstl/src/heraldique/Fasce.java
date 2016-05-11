package heraldique;

import heraldique.enumeration.Email;
import heraldique.enumeration.Metal;
import heraldique.generique.Support;

public class Fasce extends Support {

	public Fasce(Email email) {
		super(7, 2.66);
		this.email = email;
	}

	public Fasce(Metal metal) {
		super(7, 2.66);
		this.metal = metal;
	}

	public Fasce(double hauteur, double largeur, Email email) {
		super(hauteur, largeur);
		this.email = email; 
	}

	public Fasce(double hauteur, double largeur, Metal metal) {
		super(hauteur, largeur);
		this.metal = metal; 
	}
}
