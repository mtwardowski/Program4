/**	
*					         Card Class
*					=============================
* The <code>Card</code> class creates a graphical object to be used in a
* <code>Game</game>
* The upper left posterior corner of the <code>Card</code>  is drawn at point.
* <p>
* The <code>paint</code> method receives a <code>Graphics</code> object and 
* draws a <code>Card</code>.
* The <code>drawBlank</code> method receives a <code>Graphics</code> object 
* and draws a blank <code>Card</code>.
* The <code>drawDot</code> method receives a <code>Graphics</code> object, 
* an <code>int</code> for the X coordinate,and an <code>int</code> for the Y 
* coordinate.
* The <code>drawOne, drawTwo, drawThree, drawFour, drawFive</code> methods 
* receive a <code>Graphics</code> object, and a <code>boolean</code>
* that determines the side of the face of the <code>Card</code> to be drawn on. 
* The <code>doDominosOverlap</code> method returns true if coordinates passed
* within the area of this <code>Card</code>.
* The <code>compareFaceValues</code> method checks to see the input parameter
* <code>Card</code> matches this <code>Card</code>. 
* The <code>flipDominoHorizontal</code> method will rotate the
<code>Card</code> 180 degrees.
* 
* 
* @Author Michael Twardowski
*/

import java.awt.Color;						
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.RoundRectangle2D;
import java.util.Scanner;

import shapes.AShape;

public class Card extends AShape
{
	/**
	 * Dimensions for the <code>Card</code> graphic
	 */
    private static final int HEIGHT = 200,
    				  		 WIDTH = 125,
    				  		 CORNER_ARCH = 10,
    				  		 CARD_FACE_WIDTH = 25,
    				  		 CARD_FACE_HEIGHT = 25;
	
	/**
	 * Holds the cards suit
	 */
	private AShape cardFace;
	
	 /**
     * Dimensions location and drawing of the card's face
     */
	
	private final int CARD_FACE_X,
					  CARD_FACE_Y;
	
	/**
	 *	The constructor for a <code>Card</code> receives a <code>int</code> 
	 * for the X coordinate , Y coordinate, left face value, and right face value
	 *  The upper left posterior corner of the <code>Card</code> 
	 * is drawn at point (x,y).
	 * 
	 * @param someX is a <code>int</code> for the X coordinate of the Card
	 * @param someY is a <code>int</code> for the Y coordinate of the Card
	 * @param leftValue is a <code>int</code> for the left face value.
	 * @param rightValue is a <code>int</code> for the right face value.
	 */
	public Card(int x, int y, int value, AShape cardFace)
	{
		this.x = x;
		this.y = y;
		number = value;
		this.cardFace = cardFace;
		CARD_FACE_X = x + WIDTH/2 - CARD_FACE_WIDTH/2;
		CARD_FACE_Y = y + HEIGHT/2;
		cardFace.setup(CARD_FACE_X, CARD_FACE_Y, CARD_FACE_WIDTH, CARD_FACE_HEIGHT);
		
	} // end constructor
	
	
	/**   
	 * The <code>drawCard</code> method receives a <code>Graphics</code> object
	 * and draws a blank <code>Card</code>.
	 *
	 * @param pane is a <code>Graphics</code> object
	 */
	@Override
    public void paint(Graphics pane)
    {
    	// Draw card outline
    	pane.setColor(Color.black);
    	Graphics2D pane2D = (Graphics2D) pane;
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(x, y, WIDTH, HEIGHT, CORNER_ARCH, CORNER_ARCH);
        pane2D.draw(roundedRectangle);
    	
        drawShape(pane);
     // only draws the number if its value is valid
     	if((number > 0)&&(number < 15)){
     		drawCardValue(pane);
     	}
    }
    
	/**   
	 * The <code>drawDot</code> method receives a <code>Graphics</code> object, 
	 * an <code>int</code> for the X coordinate, and an <code>int</code> for the
	 * Y coordinate.
	 * <p>
	 * It draws a dot, centered at the X, Y coordinates.
	 * <p>
	 * @param pane is a <code>Graphics</code> object
	 * @param centerX is an <code>int</code> corresponding to X coordinate of the dot.
	 * @param centerY is an <code>int</code> corresponding to Y coordinate of the dot.
     */ 
    private void drawShape(Graphics pane)
	{
    	cardFace.paint(pane);
	}
    
    /**   
	 * The <code>drawDot</code> method receives a <code>Graphics</code> object, 
	 * an <code>int</code> for the X coordinate, and an <code>int</code> for the
	 * Y coordinate.
	 * <p>
	 * It draws a dot, centered at the X, Y coordinates.
	 * <p>
	 * @param pane is a <code>Graphics</code> object
	 * @param centerX is an <code>int</code> corresponding to X coordinate of the dot.
	 * @param centerY is an <code>int</code> corresponding to Y coordinate of the dot.
     */ 
    private void drawCardValue(Graphics pane)
	{
    	String cardValue = "";
    	int labelWidth = pane.getFontMetrics().stringWidth("" + number);//width of number
		int labelHeight = pane.getFontMetrics().getAscent();
		if (number > 10){
			switch(number){
				case 11:
					cardValue = "J";
					break;
				case 12:
					cardValue = "Q";
					break;
				case 13:
					cardValue = "K";
					break;
				case 14:
					cardValue = "A";
					break;
				default:
					cardValue = "";
			}
		}else{
			cardValue = "" + number;
		}
		pane.drawString(cardValue,			//draws number the number in the
					x + labelWidth*2,  // top left corner
					y + labelHeight*2);
	}
    
    /**
     * The <code>compareFaceValues</code> method checks to see the input parameter
     * <code>Card</code> matches this <code>Card</code>. 
     * <p>
     * @param card to be compared to
     */
    public void compareFaceValues(Card card){
    	String prompt = "There's no match!";
    	
    	if(leftFaceValue == card.leftFaceValue){
			card.flipDominoHorizontal();
			card.x = x - WIDTH - DEPTH - SPACING;
			card.y = y;
			prompt = "It's a Match!";
    	}
    	else if(rightFaceValue == card.leftFaceValue){
    		card.x = x + WIDTH + DEPTH + SPACING;
			card.y = y;
			prompt = "It's a Match!";
    	}
    	else if(leftFaceValue == card.rightFaceValue){
    		card.x = x - WIDTH - DEPTH - SPACING;
			card.y = y;
			prompt = "It's a Match!";
    	}
    	else if(rightFaceValue == card.rightFaceValue){
			card.flipDominoHorizontal();
			card.x = x + WIDTH + DEPTH + SPACING;
			card.y = y;
			prompt = "It's a Match!";
    	}
    	
    	System.out.println(prompt);
    }
}
