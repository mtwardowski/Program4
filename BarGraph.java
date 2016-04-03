import java.awt.Color;						
import java.awt.Graphics;

import shapes.AShape;

public class BarGraph {

	/**
	 * The location, dimensions and label
	 */
	private int x, y, number,
			dividerHeight = 75,
			barHeight = 50,
			barWidht = 2;
	
	/**
	 * Its fill color
	 */
	private Color color;
	
	/**
	 * bar graph shapes
	 */
	private AShape[] barGraph;		
			
	public BarGraph(){
		number = 0;
	}
	
	/**
	 * Sets all of the properties
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public BarGraph(int x, int y){
		this.x = x;
		this.y = y;
		number = 0;
		color = Color.blue;
	}
	
	/**
	 * Sets the fill color
	 * @param someColor
	 */
	public void setColor(Color color){
		this.color = color;
	}
	
	/**
	 * Sets the fill color
	 * @param someColor
	 */
	public void setNumber(int number){
		this.number = number;
	}
	
	/**
	 * restarts the value of the barGraph to zero
	 */
	public void reset(){
		number = 0;
	}
	
	public void paint(Graphics pane){
		pane.setColor(Color.black);
		pane.drawLine(x, y, x, y + dividerHeight);
		pane.setColor(color);
		
		int j = 0;
		// repaints any shapes that have not been cleared
		while(j < Math.abs(number)){
			barGraph[j].paint(pane);
			j++;
		}
	};
	
}
