/**
 *  The <code>Spade</code> class is used to draw the Spade suite on a card.
 */
package shapes;

import java.awt.Graphics;
import java.awt.Polygon;

public class Spade extends AShape{
	
	/**
	 * Default Constructor
	 */
	public Spade(){
		super();
	}
	
	/**
	 * Sets all of the properties
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Spade(int x, int y ,int width, int height){
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
	 * draws a spade
	 */
	@Override
	public void paint(Graphics pane) {
		pane.setColor(color);
		
		int leftOvalX =  x - width/3,
			rightOvalX = x + width/3;
			
		Polygon poly = new Polygon();
		poly.addPoint(x - width*3/8, y );//Top left
		poly.addPoint(x + width*11/8, y);//top right
		poly.addPoint(x + width/2, y - height);//top
		
		Polygon polyBottom = new Polygon();
		polyBottom.addPoint(x + width/2, y);//top
		polyBottom.addPoint(x, y + height);//lower left
		polyBottom.addPoint(x + width, y + height);//lower right
		
		pane.fillOval(leftOvalX, y - height/2, width, height);
		pane.fillOval(rightOvalX, y - height/2, width , height);
		pane.fillPolygon(poly);
		pane.fillPolygon(polyBottom);
	}
}