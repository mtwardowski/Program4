import java.awt.Color;						
import java.awt.Graphics;

import shapes.AShape;
import shapes.Rectangle;

/**	
*					         BarGraph Class
*					=============================
* The <code>BarGraph</code> class creates a graphical object to be used to
* visualize a value.
* <p>
* The <code>setGameScore</code> method sets the value of the Graph
* The <code>setRoundScore</code> method sets value of the tied round score
* The <code>getGameScore</code> returns the value of the Graph
* The <code>setRoundScore</code> returns the value of the tied round score
* The <code>reset</code> method resets the value of the barGraph to zero
* The <code>resetRoundScore </code> method resets the roundScore
* The <code>paint</code> method paints the barGraph
* 
* @Author Michael Twardowski
*/
public class BarGraph {

	/**
	 * The location, value of the barGraph and the dimensions of the bars
	 */
	private int x, y,
			gameScore = 0,
			roundScore = 1,
			dividerHeight = 75,
			barHeight = 50,
			barWidth = 5;
	
	/**
	 * Fill color for each side of the bar graph
	 */
	private Color colorLeft,
				  colorRight;
	
	/**
	 * Shapes of the bar graph.
	 */
	private AShape[] barsRight = new AShape[52],
					 barsLeft = new AShape[52];		
			
	public BarGraph(){
	} //end default constructor
	
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
		colorLeft = Color.blue;
		colorRight = Color.cyan;
		
		// creates rectangles for bar graphs on each side of the center point
		for(int i = 0; i < 52; i++){
			barsRight[i] = new Rectangle(x + barWidth*i, y + barHeight/4 , barWidth, barHeight);
			barsRight[i].setColor(colorRight);
			barsLeft[i] = new Rectangle(x - barWidth*(1+ i), y + barHeight/4 , barWidth, barHeight);
			barsLeft[i].setColor(colorLeft);
		}
	}
	
	/**
	 * The <code>setGameScore</code> method sets the value of the Graph
	 * @param value
	 */
	public void setGameScore(int value){
		gameScore = value;
	}
	/**
	 * The <code>setRoundScore</code> method sets value of the tied round score
	 * @param value
	 */
	public void setRoundScore(int value){
		roundScore = value;
	}
	
	/**
	 * The <code>getGameScore</code> returns the value of the Graph
	 * @param value
	 */
	public int getGameScore(){
		return gameScore;
	}
	/**
	 * The <code>setRoundScore</code> returns the value of the tied round score
	 * @param value
	 */
	public int getRoundScore(){
		return roundScore;
	}
	
	/**
	 * The <code>reset</code> method resets the value of the barGraph to zero
	 */
	public void reset(){
		gameScore = 0;
		resetRoundScore();
	}
	/**
	 * The <code>resetRoundScore </code> method resets the roundScore
	 */
	public void resetRoundScore(){
		roundScore = 1;
	}
	
	/**
	 * The <code>paint</code> method paints the barGraph
	 * @param pane
	 */
	public void paint(Graphics pane){
		
		// draw line at center point of barGraph
		pane.setColor(Color.black);
		pane.drawLine(x, y, x, y + dividerHeight);
		
		int j = 0;
		// repaints any shapes that have not been cleared
		while(j < Math.abs(gameScore)){
			// if value is negative this will only draw the left barGraph
			// if the value is positive this will only draw the right barGraph
			if(gameScore > 0){
				barsRight[j].paint(pane);
			}else{
				barsLeft[j].paint(pane);
			}
			j++;
		}
	}
}