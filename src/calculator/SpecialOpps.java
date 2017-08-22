package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpecialOpps {
	
	public String degToRad(String input) {
		Matcher m = Pattern.compile("(sin|cos|tan)\\(([^)]+)\\)").matcher(input);		
		while(m.find()) {
			input = input.replace(m.group(2), toRad(m.group(2)));
		}
		return input;
	}
	
	public String squareRoot(String input) {
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '√' && Character.isDigit(input.charAt(i-1))) {
				input = new StringBuilder(input).insert(i, "*").toString();
			}
		}
		Matcher m = Pattern.compile("(√)\\(([^)]+)\\)").matcher(input);
		while(m.find()) {
			input = input.replace(m.group(0), root(m.group(2)));
		}		
		return input;
	}
	
	private String toRad(String s) {
		Double num = Double.valueOf(s);
		num = Math.toRadians(num);
		s = Double.toString(num);
		
		return s;
	}
	
	private String root(String s) {
		Double num = Double.valueOf(s);
		num = Math.sqrt(num);
		s = Double.toString(num);
		
		return s;	
	}

}
