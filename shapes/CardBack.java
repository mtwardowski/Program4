package shapes;

import java.awt.Color;
import java.awt.Graphics;

/**
 *  The <code>CardBack</code> class is used to draw back of card
 *  
 *  @Author Michael Twardowski
 */
public class CardBack extends AShape{
	
	private String cardLabel = "WAR!";
	
	/**
	 * Default constructor
	 */
	public CardBack(){
		super();
	}// end default constructor
	
	/**
	 * Sets all of the properties
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public CardBack(int x, int y ,int width, int height){
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
		color = Color.black;
	}
	/**
	 * Draws the CardBack
	 * @param pane
	 */
	@Override
	public void paint(Graphics pane) {
		pane.setColor(color);
		
		pane.drawString(cardLabel,
				x, y);
	}
}
