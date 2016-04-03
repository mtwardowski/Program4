package shapes;

import java.awt.Graphics;
import java.awt.Polygon;

public class Heart extends AShape{
	
	/**
	 * Assigns the label.
	 */
	public Heart(){
		super();
	}
	
	/**
	 * Sets all of the properties
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Heart(int x, int y ,int width, int height){
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
	 * draws an oval, filled with the given color and its numeric label
	 * @param pane
	 */
	@Override
	public void paint(Graphics pane) {
		pane.setColor(color);
		
		int leftOvalX =  x - width/3,
			rightOvalX = x + width/3;
		
		Polygon poly = new Polygon();
		poly.addPoint(x - width*3/8, y );//Top left
		poly.addPoint(x + width*11/8, y);//top right
		poly.addPoint(x + width/2, y + height);//bottom
		
		pane.fillOval(leftOvalX, y - height/2, width, height);
		pane.fillOval(rightOvalX, y - height/2, width , height);
		pane.fillPolygon(poly);
	}
}
