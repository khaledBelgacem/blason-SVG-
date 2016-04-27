package parsing;

import java.util.ArrayList;

public class Calque extends ArrayList<Superposable> implements Superposable {

	private static final long serialVersionUID = -8885490101967232986L;
	private Transformation transform;
	private Style style;

	public Calque(Transformation transform, Style style) {
		this.transform = transform;
		this.style = style;
	}

	public Transformation getTransform() {
		return transform;
	}

	public Style getStyle() {
		return style;
	}
	
	public void recursion() {
		for (Superposable s : this) {
			try {
				Calque c = (Calque)s;
			c.transform = Transformation.multiplication(transform, c.transform);
				c.recursion();
			} catch (ClassCastException e) {
				Forme f = (Forme)s;
				
				f.transform = Transformation.multiplication(transform, f.transform);
//				f.versAbsolu();
//				f.appliquerTransformation();	
//				f.versSystemeAbsolu();
				
				//f.appliquerTransformation();
//				f.transform = null;
			}
		}
		this.transform = null;
	}

	public String svg() {
		StringBuffer sb = new StringBuffer();

		sb.append("\n<g ");
		if (transform != null)
			sb.append("\ntransform = '" + transform + "' ");
		if (style != null)
			sb.append("\nstyle = '" + style + "' ");
		sb.append(">");
		for (Superposable s : this)
			sb.append(s.svg());
		sb.append("\n</g>");

		return sb.toString();
	}
}
