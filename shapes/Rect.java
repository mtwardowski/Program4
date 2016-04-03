package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Rect extends AShape{
	
	/**
	 * Assigns the label.
	 */
	public Rect(){
		super();
	}
	
	/**
	 * Sets all of the properties
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Rect(int x, int y, int width, int height){
		super(x, y, width, height);
	}
	
	/**
	 * draws a rectangle, filled with the given color and its numeric label
	 * @param pane
	 */
	@Override
	public void paint(Graphics pane) {
		pane.setColor(color);
		pane.fillRect(x, y, width, height);
		pane.setColor(Color.BLACK);
		pane.drawRect(x, y, width, height);
		int labelWidth = pane.getFontMetrics().stringWidth("" + number);//width of number
		int labelHeight = pane.getFontMetrics().getAscent();
		pane.drawString("" + number,						//centers the number in the
						x + (width - labelWidth)/2,			//shape
						y + (height + labelHeight)/2);

	}
}
