package geometry.real;

public class Precision {
	
	/***
	 * 
	 * Fonction d'approximation d'une �galit� entre deux doubles o� a et b sont compar�s
	 * et n servant � d�finir la pr�cision du r�sultat en terme de <em>"nombre de chiffres apr�s
	 * la vigule"</em>. 
	 * 
	 * @param a : Un double.
	 * @param b : Un double.
	 * @param n Un entier, nombre de chiffre � prendre en compte apr�s la virgule.
	 * @return Vrai si les deux nombres diff�rent de moins que la pr�cision d�finie par n sinon faux. 
	 */
	
	public static boolean areApproximatelyEquals(double a, double b, int n) {
		return Math.abs(a-b) < (1.0 / Math.pow(10, n));
	}
	
	/***
	 * 
	 * Fonction d'approximation visant � tronquer un double en ne conservant que
	 * n chiffres apr�s la vigule.
	 * 
	 * @param a : Un double � tronquer.
	 * @param n : Le nombre de chiffres apr�s la vigule � conserver.
	 * @return Un double tronqu� avec n chiffres apr�s la virgule.
	 */
	
	public static double truncate(double a, int n) {
		return (double)((long)(a * Math.pow(10, n))) / Math.pow(10, n);
	}
}
