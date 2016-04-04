/**
 *  The <code>Heart</code> class is used to draw the heart suite on a card.
 */
package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Heart extends AShape{
	
	/**
	 * Default Constructor
	 */
	public Heart(){
		super();
	}//end default constructor
	
	/**
	 * Sets all of the properties
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Heart(int x, int y ,int width, int height){
		super(x, y, width, height);
		color = Color.red;
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
		color = Color.red;
	}
	/**
	 * paints a heart
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