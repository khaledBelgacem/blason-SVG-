package geometry.real;

public class Precision {
	
	/***
	 * 
	 * Fonction d'approximation d'une égalité entre deux doubles où a et b sont comparés
	 * et n servant à définir la précision du résultat en terme de <em>"nombre de chiffres après
	 * la vigule"</em>. 
	 * 
	 * @param a : Un double.
	 * @param b : Un double.
	 * @param n Un entier, nombre de chiffre à prendre en compte après la virgule.
	 * @return Vrai si les deux nombres diffèrent de moins que la précision définie par n sinon faux. 
	 */
	
	public static boolean areApproximatelyEquals(double a, double b, int n) {
		return Math.abs(a-b) < (1.0 / Math.pow(10, n));
	}
	
	/***
	 * 
	 * Fonction d'approximation visant à tronquer un double en ne conservant que
	 * n chiffres après la vigule.
	 * 
	 * @param a : Un double à tronquer.
	 * @param n : Le nombre de chiffres après la vigule à conserver.
	 * @return Un double tronqué avec n chiffres après la virgule.
	 */
	
	public static double truncate(double a, int n) {
		return (double)((long)(a * Math.pow(10, n))) / Math.pow(10, n);
	}
}
