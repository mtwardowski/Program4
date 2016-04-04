package shapes;

import java.awt.Color;
import java.awt.Graphics;

/**
 *  The <code>AShape</code> abstract class can be used to create shapes
 *  
 *  @Author Michael Twardowski
 */
public abstract class AShape {
	
		/**
		 * The location, dimensions, label, and number of shape
		 */
		protected int x, y, width, height, number;
		
		/**
		 * Its the fill color of the shape
		 */
		protected Color color;
		
		/**
		 * To keep track of how many have been instantiated - used for label
		 */
		protected static int count;
		
		/**
		 * Assigns the label.
		 */
		public AShape(){
			number = ++count;
		}
		
		/**
		 * Sets all of the properties
		 * @param x
		 * @param y
		 * @param width
		 * @param height
		 */
		public AShape(int x, int y, int width, int height){
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			number = ++count;
		}

		/**
		 * Sets the location and dimensions
		 * @param x
		 * @param y
		 * @param width
		 * @param height
		 */
		public void setup(int x, int y, int width, int height){
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}
		
		/**
		 * Sets the fill color
		 * @param someColor
		 */
		public void setColor(Color color){
			this.color = color;
		}
		
		/**
		 * restarts the count
		 */
		public static void reset(){
			count = 0;
		}
		
		public abstract void paint(Graphics pane);
}
