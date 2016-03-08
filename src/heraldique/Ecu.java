package heraldique;

import heraldique.enumeration.Email;
import heraldique.enumeration.Metal;
import heraldique.generique.Support;

public class Ecu extends Support {

	public Ecu(double largeur, double hauteur, Email email) {
		super(largeur, hauteur);
		this.email = email; 
	}
	
	public Ecu(double largeur, double hauteur, Metal metal) {
		super(largeur, hauteur);
		this.metal = metal; 
	}
	
}
