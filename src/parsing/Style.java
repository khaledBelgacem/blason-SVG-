package parsing;

import java.util.ArrayList;

public class Style extends ArrayList<String> {
	private static final long serialVersionUID = 8442520473023012110L;
	String content;
	
	public Style(String style) {
		super();
		content = style;
		split(style);
	}
	
	private void split(String style) {
		String[] array = style.split("[;]");
		for (String s : array) {
			this.add(s);
		}
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		for (String s : this) {
			sb.append(s);
			sb.append(';');
		}
		
		return sb.toString();
	}
}
