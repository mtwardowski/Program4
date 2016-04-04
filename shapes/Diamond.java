package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

/**
 *  The <code>Diamond</code> class is used to draw the diamond suite on a card.
 *  
 *  @Author Michael Twardowski
 */
public class Diamond extends AShape{
	
	/**
	 * To hold the shape so it doesn't have to be recreated
	 * every time it's painted.
	 */
	private Polygon poly;
	
	/**
	 * Default Constructor
	 */
	public Diamond(){
		super();
	}//end default constructor
	
	/**
	 * Sets all of the properties
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Diamond(int x, int y, int width, int height){
		super(x, y, width, height);
		poly = new Polygon();
		poly.addPoint(x + width/2, y - height);//top
		poly.addPoint(x, y);//middle left
		poly.addPoint(x + width/2, y + height);//bottom
		poly.addPoint(x + width, y);//middle right
		
		this.color = Color.red;
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
		poly = new Polygon();
		poly.addPoint(x + width/2, y - height);//top
		poly.addPoint(x, y);//middle left
		poly.addPoint(x + width/2, y + height);//bottom
		poly.addPoint(x + width, y);//middle right
		color = Color.red;
	}
	
	/**
	 * paints a diamond
	 */
	@Override
	public void paint(Graphics pane) {
		pane.setColor(color);
		pane.fillPolygon(poly);
	}
}