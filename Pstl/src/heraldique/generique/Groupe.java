package heraldique.generique;

import java.util.ArrayList;

public class Groupe extends ArrayList<Support> {

	private static final long serialVersionUID = 414840398197792954L;
	
	public String svg() {
		StringBuffer sb =new StringBuffer(); 
		sb.append("<g transform=\"scale(10)\">");
		for (Support s : this) {
			sb.append(s.svg());
		}
		sb.append("</g>");
		return sb.toString();
	}
	
}
