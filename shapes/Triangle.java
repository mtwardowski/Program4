package shapes;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Color;

public class Triangle extends AShape{
	
	/**
	 * To hold the shape so it doesn't have to be recreated
	 * every time it's painted.
	 */
	private Polygon poly;
	
	/**
	 * Assigns the label.
	 */
	public Triangle(){
		super();
	}
	
	/**
	 * Sets all of the properties
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Triangle(int x, int y, int width, int height){
		super(x, y, width, height);
		poly = new Polygon();
		poly.addPoint(x + width/2, y);//top
		poly.addPoint(x, y + height);//lower left
		poly.addPoint(x + width, y + height);//lower right
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
		poly.addPoint(x + width/2, y);//top
		poly.addPoint(x, y + height);//lower left
		poly.addPoint(x + width, y + height);//lower right
	}
	
	/**
	 * paints an isosceles triangle
	 */
	@Override
	public void paint(Graphics pane) {
		pane.setColor(color);
		pane.fillPolygon(poly);
		pane.setColor(Color.BLACK);
		pane.drawPolygon(poly);
		int labelWidth = pane.getFontMetrics().stringWidth("" + number);//width of number
		int labelHeight = pane.getFontMetrics().getAscent();
		pane.drawString("" + number,						//centers the number in the
						x + (width - labelWidth)/2,			//shape
						y + (height + labelHeight)/2);

	}
}
