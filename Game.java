
/**
*							  	Game Class
*						=============================
*	The <code>Game</code> Class extends the <code>Frame</code> Class.
*	<p>
*	The <code>paint</code> method will paint game pieces on <code>Game</code>
*	after then have been initialized.
*	The <code>playersTurn</code> method creates a new <code>Card</code>
* 	and places it on the <code>Game</code> based on the users input 
*	for its value and location.
*	The <code>computersTurn</code> method creates a new <code>Card</code>
* 	with randomly generated value and location and places it on the
* 	<code>Game</code> on the game board.
* 	The <code>setDominoValue/code> method prompts a user to enter an 
* 	<code>int</code> between 1 and 5 for the face value of the <code>Card</code>.
* 	The <code>setCoordinate</code> method prompts a user for a coordinate for
* 	a (<code>Card</code>) and places it on the <code>Game</code> based on 
* 	that input.
* 	The <code>getIntegerValue</code> method prompts a user to enter an
*	<code>int</code> and returns a result (<code>int</code>).
*
*	@Author Michael Twardowski
*/
import java.util.Scanner;

import button.AButton;
import button.RegularButton;
import shapes.AShape;
import shapes.Rect;

import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Graphics;

public class Game extends Frame implements MouseListener, ComponentListener {
	/**
	 * Buttons to respond to mouse clicks
	 */
	private AButton[] buttons = new AButton[3];
	
	/**
	 * Cards to play with
	 */
	private Card[] cards = new Card[4];
	
	/**
	 * Card locations
	 */
	private int[] cardX = {200, 350 , 500, 650};
	private int cardY = 200;
	
	/**
	 * To keep track of the size of the window
	 */
	private int windowHeight, windowWidth;

	/**
	 * To make sure we don't draw the shapes too high or overlapping the buttons
	 */
	private static final int LABEL_HEIGHT = 50, BUTTON_PANEL = 115, PANEL_HEIGHT = 250;
	
	/**
	 * Handle for the <code>Game</code> window.
	 */
	private CloseWindow myWindow;

	/**
	 * So the System doesn't try to paint the buttons or cards before they are initialized
	 */
	private boolean initializationComplete = false;
	private boolean[] areCardsVisible = {false, false, false, false};
	
	
	//TODO: do I need this?
	/**
	 * Height, and Width for the <code>Game</code> window.
	 * The MAX_VALUE_X and MAX_VALUE_Y is playable area after the 
	 * <code>Game</code> window border and <code>Card</code> width is 
	 * accounted for.
	 */
	private final int WIDTH = 800, 
					  HEIGHT = 800,
					  MAX_VALUE_X,
					  MAX_VALUE_Y;
	
	/**
	 * Holds the insets for the <code>Game</code> window
	 */
	private Insets insets;

	/**
	 * Instantiates a <code>Game</code> on which to place <code>Dominos</code>.
	 */
	public static void main(String[] args) 
	{
		Game myGameTable;
		myGameTable = new Game();
		
		/* useful for testing
		*for(int i=0; i<50; i++){
		*	myGameTable.computersTurn();
		*}
		*/
	}

	/**
	 * The default constructor for the <code>Game</code>. 
	 */
	public Game() 
	{
		final int 	BUTTON_WIDTH = 100,
					BUTTON_HEIGHT = 40,//to define the size of our buttons
					SPACE = 10;//space between buttons

		setTitle("War");//name, location and size of our frame
		setLocation(150,150);	

		windowWidth = 800;//can be resized, but starts out reasonably large
		windowHeight = 800;
		setSize(windowWidth,windowHeight);

		/*
		 * Set up our three buttons in the top left of the drawing window
		 */
		buttons[0] = new RegularButton("Start", Color.red, SPACE, LABEL_HEIGHT + SPACE, 
				BUTTON_WIDTH, BUTTON_HEIGHT);
		
		buttons[1] = new RegularButton("Reset", Color.green, SPACE, 
				LABEL_HEIGHT + BUTTON_HEIGHT + SPACE * 2, BUTTON_WIDTH, BUTTON_HEIGHT);

		buttons[2] = new RegularButton("Quit", Color.cyan, SPACE, 
				LABEL_HEIGHT + BUTTON_HEIGHT * 2 + SPACE * 3, BUTTON_WIDTH, BUTTON_HEIGHT);
		
		/*
		 * Set up our two initial cards
		 */
		
		for(int i = 0; i < 4; i++){
			cards[i] = new Card(cardX[i], cardY, 0 , new Rect());
		}
		areCardsVisible[0] = true;
		areCardsVisible[3] = true;

		myWindow = new CloseWindow(); // allows for window closing
		addWindowListener(myWindow);
		addComponentListener(this);//to know when the window has been resized
		
		System.out.println("This means War!");
		initializationComplete = true;
		
		setVisible(true);
		
		
		//TODO: do I need this?
		insets = getInsets();
		
		int dominoWidth = 200,
			dominoHeight = 100,
			dominoDepth = 10;
		
		// determines the max possible location that a user can select for a
		// domino's position
		MAX_VALUE_X = WIDTH - insets.left - insets.right - dominoWidth - dominoDepth;
		MAX_VALUE_Y = HEIGHT - insets.top - insets.bottom - dominoHeight - dominoDepth;
		
	} // end default constructor

	/**
	 * The <code>paint</code> method will paint each <code>Card</code> on <code>Game</code>
	 * after then have been initialized.
	 */
	public void paint(Graphics pane){
		if(initializationComplete){
			
			/*
			 *  Note: we can use the "for each" syntax because we know all four buttons
			 *  have been instantiated.  We couldn't do that if any of the references
			 *  in the array were null.  Let's say we had an int variable numButtons that
			 *  held how many buttons had been instantiated.
			 *  We would need to use the syntax:
			 *  
			 *  for(int i = 0; i < numButtons; i++){
			 *  	buttons[i].paint(pane);
			 *  }
			 */
			for(AButton button: buttons){//makes it easy to paint when they are in 
				button.paint(pane);		//an array!
			}
			
			//TODO: show all cards
			for(int i = 0; i < 4; i++){
				if (areCardsVisible[i]){
					cards[i].drawCard(pane);
				}
			}
		}
	}
	
	/**
	 * The <code>computersTurn</code> method creates a new <code>Card</code>
	 * with randomly generated value and location and places it on the
	 * <code>Game</code> on the game board.
	 */
	private void computersTurn(){
		
		// generates random values from 1 to 5 for each face of the domino
		int leftFaceValue = (int)(Math.random()*5 +1);
		int rightFaceValue = (int)(Math.random()*5 +1);
		
		//generates a location for the domino that does not overlap myDomino
		// or lie within a border
		int x, y,
			xOffset = insets.left + 10,
			yOffset = insets.top + 10;
		
		do{
			x = (int)(Math.random()*(MAX_VALUE_X - xOffset) + xOffset);
			y = (int)(Math.random()*(MAX_VALUE_Y - yOffset) + yOffset);
		}while(myDomino.doDominosOverlap(x,y)); // checks for domino overlap
		
		theComputerDomino = new Card(x, y, leftFaceValue, rightFaceValue);
		
		// checks to see if the new domino matches myDomino
		myDomino.compareFaceValues(theComputerDomino);
		
		// delays display of domino
		try {
		    Thread.sleep(1500);  //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    //Thread.currentThread().interrupt();
		}
		
		isComputerDominoPlaced = true;
		
		repaint();
	}
	
	 /**   
	 * The <code>setDominoValue</code> method prompts a user to enter an 
	 * <code>int</code> between 1 and 5 for the face value of the <code>Card</code>.
	 * <p>
	 * @param isLeft is a <code>boolean</code> to determine what
	 * side of the domino is being set up.
	 * @return int of the selected value
	 */
	private int setDominoValue(boolean isLeft){
		String side;
		
		int minimumValue = 1,
		    maximumValue = 5;
		
		if(isLeft){
			side = "left";
		}
		else{
			side = "right";
		}
		
		System.out.println("Enter a value for the " + side + " face of a domino: ");
		int faceValue = getIntegerValue(minimumValue, maximumValue);
		
		return faceValue;
	}

	/**
	 * The <code>setCoordinate</code> method prompts a user for a coordinate for
	 * a (<code>Card</code>) and places it on the <code>Game</code> based on 
	 * that input.
	 * <p>
	 * @param isXCoordinate is a <code>boolean</code> to 
	 * what determine what dimension the coordinate belongs to.
	 * @return int of the selected value for the coordinate
	 */
	private int setCoordinate(boolean isXCoordinate){
		// holds the name of the dimension being setup
		String dimension;
		
		int maxValue,
			offset,
			dominoDepth = 10;
		
		/* prevents the user from entering a position that would clip of part
		* of domino the maximumWidth is:
		* windowWidth - leftBorder  - rightBorder - dominoWidth - depthInX
		* the maximumHeight is:
		* windowHeight - topBorder  - bottomBorder - dominoHeight - depthInY
		* sets the the tableTop origin for new game pieces to be 
		* (leftInset + depthInX, topInset + depthInY)
		* any value entered by the user will be offset by this amount. An entered value of
		* (0,0) will have the top left corner of the game piece in the very top left corner of the tableTop
		*/
		if(isXCoordinate){
			dimension = "x";
			maxValue = MAX_VALUE_X;
			offset = insets.left + dominoDepth;
		}
		else{
			dimension = "y";
			maxValue = MAX_VALUE_Y;
			offset = insets.top + dominoDepth;
		}

		System.out.println("Enter a " + dimension + " coordinate for the domino.");
		
		int coordinate = getIntegerValue(0, maxValue);
		
		return coordinate + offset;
	}
	
	/**
	 * The <code>getIntegerValue</code> method prompts a user to enter an
	 * <code>int</code> and returns a result (<code>int</code>).
	 * <p>
	 * It accepts two <code>int</code>, <code>min</code>  and <code>max</code>
	 * correspond to the range that this method will accept for user input.
	 * 
	 * @param min is the minimum value of that can be entered.
	 * @param max is the maximum value of that can be entered.
	 * @return result (int) is the coordinate input from the user.
	 */
	private int getIntegerValue(int min, int max){
		String prompt = "Please enter an integer from " + min + " to " + max + ".";
	    System.out.println(prompt);
	    while(true){
	        try 
	        {
	            int result = Integer.parseInt(new Scanner(System.in).next()); // checks if result is on integer
	            if((result < min)||(result > max)) // true if the integer is outside the Game playable area
				{
	            	System.out.println("The integer you entered is outside the range.\n" + prompt);
	            	continue; // continues to the next loop iteration
				}
	            return result; // returns if input is an integer and in the Game playable area
	            
	        } 
	        catch(NumberFormatException ne) 
	        {
	            System.out.println("That's not a integer.\n" + prompt);
	        }
	    }
	}
	
	/**
	 * If the mouse click is within one of the shape buttons, it makes a new
	 * instance of that shape, (replacing any previous instance).  If it's 
	 * within the clear button it clears all the shapes.
	 */
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		if(buttons[0].isInside(x, y)){	//Start button
			repaint();
		}else if(buttons[1].isInside(x, y)){	//Resetbutton
			repaint();
		}else if(buttons[2].isInside(x, y)){	//Quit
			System.exit(0);
		}
	}

	/**
	 * Flips the button that has been pressed
	 */
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		for(AButton b:buttons){
			if(b.isInside(x, y)){
				b.flip();
				repaint();
			}
		}

	}

	/**
	 * Flips the button that has been released
	 */
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		for(AButton b:buttons){
			if(b.isInside(x, y)){
				b.flip();
				repaint();
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	public void componentHidden(ComponentEvent event){}


	/**
	 * Makes sure shapes drawn after window is resized fit inside window.
	 */
	public void componentResized(ComponentEvent e) {
		windowWidth = getWidth();
		windowHeight = getHeight();	
	}


	@Override
	public void componentMoved(ComponentEvent e) {}


	@Override
	public void componentShown(ComponentEvent e) {}
}
