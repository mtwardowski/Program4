
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
import shapes.Diamond;
import shapes.Heart;
import shapes.Rect;
import shapes.Spade;
import shapes.Club;

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
	 * Game score
	 */
	private int gameScore = 0,
				roundScore = 1;
	private BarGraph bar;
	
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
		buttons[0] = new RegularButton("Play", Color.red, SPACE, LABEL_HEIGHT + SPACE, 
				BUTTON_WIDTH, BUTTON_HEIGHT);
		
		buttons[1] = new RegularButton("Reset", Color.green, SPACE, 
				LABEL_HEIGHT + BUTTON_HEIGHT + SPACE * 2, BUTTON_WIDTH, BUTTON_HEIGHT);

		buttons[2] = new RegularButton("Quit", Color.cyan, SPACE, 
				LABEL_HEIGHT + BUTTON_HEIGHT * 2 + SPACE * 3, BUTTON_WIDTH, BUTTON_HEIGHT);
		
		bar = new BarGraph(500,400);
		
		/*
		 * Set up our two initial cards
		 */
		
		for(int i = 0; i < 4; i++){
			cards[i] = new Card(cardX[i], cardY, 0 , new Spade());
		}
		areCardsVisible[0] = true;
		areCardsVisible[3] = true;

		myWindow = new CloseWindow(); // allows for window closing
		addWindowListener(myWindow);
		addMouseListener(this);
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
			
			bar.paint(pane);
			
			//TODO: show all cards
			for(int i = 0; i < 4; i++){
				if (areCardsVisible[i]){
					cards[i].paint(pane);
				}
			}
		}
	}
	
	/**
	 * The <code>computersTurn</code> method creates a new <code>Card</code>
	 * with randomly generated value and location and places it on the
	 * <code>Game</code> on the game board.
	 */
	private void playTurn(){
		
		int[] cardValues = { (int)(Math.random()*14 +1),
							 (int)(Math.random()*14 +1)};
		int[] suits = {(int)(Math.random()*4 +1),
					   (int)(Math.random()*4 +1)};
		
		AShape suit;
			
		for(int i = 0; i < 2; i++){
			switch(suits[i]){
				case 1:
					suit = new Spade();
					break;
				case 2:
					suit = new Heart();
					break;
				case 3:
					suit = new Diamond();
					break;
				case 4:
					suit = new Club();
					break;
				default:
				 	suit = null;
			}
			cards[i+1] = new Card(cardX[i+1], cardY, cardValues[i] , suit);
		}
		
		areCardsVisible[1] = true;
		areCardsVisible[2] = true;
			
		// checks for war condition
		if(cardValues[0] == cardValues[1]){
			roundScore = roundScore*2;
		}else{
			gameScore += roundScore;
			roundScore = 1;
		}
		repaint();
	}
	
	/**
	 * If the mouse click is within one of the shape buttons, it makes a new
	 * instance of that shape, (replacing any previous instance).  If it's 
	 * within the clear button it clears all the shapes.
	 */
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		if(buttons[0].isInside(x, y)){			// Play
			playTurn();
			repaint();
		}else if(buttons[1].isInside(x, y)){	//Reset
			for(int i = 1; i < 3; i++){
				areCardsVisible[i] = false;
			}
			gameScore = 0;
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
