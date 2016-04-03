package shapes;

import java.awt.Graphics;
import java.awt.Polygon;

public class Club extends AShape{
	
	/**
	 * Assigns the label.
	 */
	public Club(){
		super();
	}
	
	/**
	 * Sets all of the properties
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Club(int x, int y ,int width, int height){
		super(x, y, width, height);
	}
	
	/**
	 * Sets the location and dimensions
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	@Override
	public void setup(int x, int y, int width, int height){
		super.setup(x, y, width, height);
	}
	/**
	 * draws spade, filled with the given color and its numeric label
	 * @param pane
	 */
	@Override
	public void paint(Graphics pane) {
		pane.setColor(color);
		
		int leftOvalX =  x - width/2,
			rightOvalX = x + width/2 ,
			centerOvalY = y - height;
		Polygon poly = new Polygon();
		poly.addPoint(x + width/2, y);//top
		poly.addPoint(x, y + height);//lower left
		poly.addPoint(x + width, y + height);//lower right
		
		pane.fillOval(leftOvalX, y - height/2, width, height);
		pane.fillOval(rightOvalX, y - height/2, width , height);
		pane.fillOval(x, centerOvalY, width , height);
		pane.fillPolygon(poly);
	}
}
