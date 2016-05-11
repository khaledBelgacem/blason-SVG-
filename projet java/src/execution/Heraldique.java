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
		Bande b = new Bande(Email.AZUR);

		e.charge(p);
		p.charge(b);
		DrawCanvas.saveAsSVG("heraldique.svg", e.svg());
	}
}
