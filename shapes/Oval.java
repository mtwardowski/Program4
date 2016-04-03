package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Oval extends AShape{
	
	/**
	 * Assigns the label.
	 */
	public Oval(){
		super();
	}
	
	/**
	 * Sets all of the properties
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Oval(int x, int y, int width, int height){
		super(x, y, width, height);
	}
	
	/**
	 * draws an oval, filled with the given color and its numeric label
	 * @param pane
	 */
	@Override
	public void paint(Graphics pane) {
		pane.setColor(color);
		pane.fillOval(x, y, width, height);
		pane.setColor(Color.BLACK);
		pane.drawOval(x, y, width, height);
		int labelWidth = pane.getFontMetrics().stringWidth("" + number);//width of number
		int labelHeight = pane.getFontMetrics().getAscent();
		pane.drawString("" + number,						//centers the number in the
						x + (width - labelWidth)/2,			//shape
						y + (height + labelHeight)/2);
	}
}
