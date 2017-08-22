package calculator;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;

public class Graph extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private Dimension size;
	private int xBound;
	private int yBound;
	
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		// Set up a Cartesian coordinate system		
	    size = this.getSize();

	    // place the origin at the middle
	    g.translate(size.width / 2, size.height / 2);

	    // draw the x and y axes
	    drawXYAxes (g);
		
	}
	
	private void drawXYAxes (Graphics g) {
		int tic = size.width / 100;
	    size = this.getSize();
	    xBound = size.width / 2;
	    yBound = size.height / 2;
				
	    // draw the x-axis
	    g.drawLine (-xBound, 0, xBound, 0);

	    // draw the tic marks along the x axis
	    for (int i = -xBound; i <= xBound; i += 10)
	      g.drawLine (i, tic, i, -tic);
				
	    // draw the y-axis
	    g.drawLine (0, yBound, 0, -yBound);	

	    // draw the tic marks along the y axis
	    for (int i = -yBound; i <= yBound; i += 10)
	      g.drawLine (-tic , i, +tic, i);
	}
	
	public void drawPoints(String expression) {
		Graphics g = this.getGraphics();
    	Graphics2D g2 = (Graphics2D) g;
    	size = this.getSize();
    	xBound = size.width / 2;
	    yBound = size.height / 2;
    	Double result;
    	
    	for (int i = 0; i < expression.length(); i++) {
			if (expression.charAt(i) == '(' && Character.isDigit(expression.charAt(i-1))) {
				expression = new StringBuilder(expression).insert(i, "*").toString();
			}
		}		
		if(Character.isLetter(expression.charAt(0)))
			expression = new StringBuilder(expression).insert(0, "1").toString();
		for (int i = 0; i < expression.length(); i++) {
			if (Character.isLetter(expression.charAt(i)) && Character.isDigit(expression.charAt(i-1))) {
				expression = new StringBuilder(expression).insert(i, "*").toString();
			}
		}
    	ArrayList<Point2D.Double> points = new ArrayList<Point2D.Double>();
    	Point2D.Double point;
    	// Create the evaluator
        final DoubleEvaluator eval = new DoubleEvaluator();
        // Create a new empty variable set
        final StaticVariableSet<Double> variables = new StaticVariableSet<Double>();
        double x = -xBound;
        final double step = 1;
        try {
        	while (x<=xBound) {
            	// Set the value of x
            	variables.set("x", x);
            	// Evaluate the expression
            	result = eval.evaluate(expression, variables);
            	// store the result
            	point = new Point2D.Double(x, -result);
            	points.add(point);
            	x += step;
            }
    	} catch(IllegalArgumentException e) {
    		JOptionPane.showMessageDialog(this, "Error In Function!\nPlease Check And Try Again");
    	}
        Point2D.Double pointA = new Point2D.Double(); 
        Point2D.Double pointB = new Point2D.Double(-xBound, 0);
        g2.translate(size.width / 2, size.height / 2);
        for(Point2D.Double p: points) {
    		pointA = pointB;
    		pointB = p;
    		g2.draw(new Line2D.Double(pointA, pointB));
        }
	}
	
}
