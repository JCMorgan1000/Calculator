package calculator;

import javax.swing.JOptionPane;
import com.fathzer.soft.javaluator.DoubleEvaluator;

public class Calculator {
	
	public String evaluate(String expression, CalcGUI calcGUI) {
		
		Double result = null;
		String resultString = "";
		
		try {
			
			for (int i = 0; i < expression.length(); i++) {
    			if (expression.charAt(i) == 'π') {
    				expression = expression.replace("π", "3.14159265359") ;
    			}
    		}
			
    		for (int i = 0; i < expression.length(); i++) {
    			if (expression.charAt(i) == '(' && Character.isDigit(expression.charAt(i-1))) {
    				expression = new StringBuilder(expression).insert(i, "*").toString();
    			}
    		}		
    		result = new DoubleEvaluator().evaluate(expression);//Copyright (C) 2007 Free Software Foundation, Inc. <http://fsf.org/>
    	} catch(IllegalArgumentException e) {
    		JOptionPane.showMessageDialog(calcGUI, "Error In Expression!\nPlease Check And Try Again");
    		
    	}
		
		if (Double.isNaN(result)) {
            return "0.0";
        } else {
            resultString = Double.toString(result);
        }
		
		return resultString;
	}
}
