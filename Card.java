import java.awt.Color;						
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import shapes.AShape;

/**	
*					         Card Class
*					=============================
* The <code>Card</code> class creates a graphical object to be used in a
* <code>Game</game>
* The upper left posterior corner of the <code>Card</code>  is drawn at point.
* <p>
* The <code>paint</code> method receives a <code>Graphics</code> object
* and draws a card <code>Card</code> with a suit and value displayed.
* The <code>drawShape</code> method draws the suit shape on the card.
* The <code>drawCardValue</code> method draws the value of the card in its
* upper left hand corner.
* The <code>compareTo/code> receives <code>Card</code> and compares it to
* this <code>Card</code>.. Returns 0 if they are equal, 1 if this  
* <code>Card</code>. is greater than the argument, or -1 if the argument is
* greater than this <code>Card</code>. 
* 
* 
* @Author Michael Twardowski
*/
public class Card extends AShape
{
	/**
	 * Dimensions for the <code>Card</code> graphic
	 */
    private static final int HEIGHT = 200,
    				  		 WIDTH = 125,
    				  		 CORNER_ARCH = 10,
    				  		 CARD_FACE_WIDTH = 25, // size of suit icon
    				  		 CARD_FACE_HEIGHT = 25;
	
	/**
	 * Holds the card's suit shape
	 */
	private AShape cardFace;
	
	 /**
     * Dimensions location and drawing of the card's face
     */
	private final int CARD_FACE_X,
					  CARD_FACE_Y;
	
	/**
	 *	The constructor for a <code>Card</code> receives a <code>int</code> 
	 * for the X coordinate , Y coordinate, and a value of the card, and AShape
	 * for the suit of card.
	 * The upper left posterior corner of the <code>Card</code> 
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
		
		// setups the suit shape location
		cardFace.setup(CARD_FACE_X, CARD_FACE_Y, CARD_FACE_WIDTH, CARD_FACE_HEIGHT);
	} // end constructor
	
	/**   
	 * The <code>paint</code> method receives a <code>Graphics</code> object
	 * and draws a card <code>Card</code> with a suit and value displayed.
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
    	
        // draws suit shape
        drawShape(pane);
        // only draws the number if its value is valid
     	if((number > 1)&&(number < 15)){
     		drawCardValue(pane);
     	}
    }
    
	/**   
	 * The <code>drawShape</code> method draws the suit shape on the card.
	 * <p>
	 * @param pane is a <code>Graphics</code> object
     */ 
    private void drawShape(Graphics pane)
	{
    	cardFace.paint(pane);
	}
    
    /**   
	 * The <code>drawCardValue</code> method draws the value of the card in its
	 * upper left hand corner.
	 * <p>
	 * @param pane is a <code>Graphics</code> object
     */ 
    private void drawCardValue(Graphics pane)
	{
    	String cardValue = "";
    	int leftMargin = 15;
		int topMargin = 25;
		// if the value of the card is > 10 then it assigned a letter as its string
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
		//draws number the number in the top left corner
		pane.drawString(cardValue,			
					x + leftMargin,
					y + topMargin);
	}
    
    /**
     * The <code>compareTo/code> receives <code>Card</code> and compares it to
     * this <code>Card</code>.. Returns 0 if they are equal, 1 if this  
     * <code>Card</code>. is greater than the argument, or -1 if the argument is
     * greater than this <code>Card</code>. 
     * <p>
     * @param card to be compared to
     * @return value
     */
    public int compareTo(Card card){
    	
    	int value = 0;
    	
    	if (this.number > card.number){
    		value = 1;
    	}else if(this.number < card.number){
    		value = -1;
    	}
    		
    	return value;
    }
}