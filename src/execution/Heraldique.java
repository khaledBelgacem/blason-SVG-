package execution;

import heraldique.Bande;
import heraldique.Ecu;
import heraldique.Fasce;
import heraldique.Pal;
import heraldique.enumeration.Email;
import heraldique.enumeration.Metal;

public class Heraldique {

	public static void main(String[] args) {
		Ecu e = new Ecu(700, 800, Metal.OR);
		Pal p = new Pal(Email.SABLE);
		Fasce f = new Fasce(Email.GUEULES);
//		Bande b = new Bande(Email.AZUR);
//		Bande b2 = new Bande(Email.CARNATION);
		Ecu e2 = new Ecu(700, 800, Metal.ARGENT);
		
		e.charge(p);
		p.charge(e2);
		e2.charge(f);

//		p.charge(f);
//		f.charge(b);
//		b.charge(b2);
//		f.charge(f2);
		
		DrawCanvas.saveAsSVG("heraldique.svg", e.svg());
	}
}
