
/**
*							  	Game Class
*						=============================
*	The <code>Game</code> Class extends the <code>Frame</code> Class.
*	<p>
*	The <code>paint</code> method will paint each <code>Card</code> on <code>Game</code>
*	after then have been initialized.
*	The <code>playTurn</code> method draws a <code>Card</code> from each
*	players deck and plays it. The player with a higher value card scores
*	a point.
*	The <code>compareCardValues</code> method compares the value of each
*	<code>Card</code> that is drawn and updates the game score based on the
*	winner
*	If the mouse click is in the Play Button, a turn will be played.  If it's 
*	within the reset button, the game resets to the beginning. If it's within
*	the quit button, the game window will close.
*
*	@Author Michael Twardowski
*/

import button.AButton;
import button.RegularButton;
import shapes.AShape;
import shapes.CardBack;
import shapes.Diamond;
import shapes.Heart;
import shapes.Spade;
import shapes.Club;

import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Graphics;

public class Game extends Frame implements MouseListener {
	/**
	 * Buttons to respond to mouse clicks
	 */
	private AButton[] buttons = new AButton[3];
	
	/**
	 * 4 Piles of Cards to play with
	 */
	private Card[] cards = new Card[4];
	
	/**
	 * Card locations on the game space
	 */
	private int[] cardX = {100, 575, 263, 413};
	private int cardY = 275;

	/**
	 * Bar graph to visual current game score
	 */
	private BarGraph bar;
	
	/**
	 * To window size of the size of the window
	 */
	private int windowHeight, windowWidth;
	
	/**
	 * Handle for the <code>Game</code> window.
	 */
	private CloseWindow myWindow;

	/**
	 * So the System doesn't try to paint the buttons or cards before they are initialized
	 */
	private boolean initializationComplete = false;
	private boolean[] areCardsVisible = {false, false, false, false};

	/**
	 * Instantiates a <code>Game</code> on which to play
	 */
	public static void main(String[] args) 
	{
		Game myGameTable = new Game();
		
		/*
		//useful for testing
		for(int i=0; i<50; i++){
			// delays playing turn
			myGameTable.playTurn();
			try {
			    Thread.sleep(1000);  //1000 milliseconds is one second.
			} catch(InterruptedException ex) {
			    //Thread.currentThread().interrupt();
			}
		}
		*/
	
	}

	/**
	 * The default constructor for the <code>Game</code>. 
	 */
	public Game() 
	{
		final int 	BUTTON_WIDTH = 100, // defines size of buttons
					BUTTON_HEIGHT = 40,
					BUTTON_X = 240,  // locate of first button
					BUTTON_Y = 600,
					SPACE = 10,  // space between buttons
					BAR_X = 400, // location of BarGraph
					BAR_Y = 500;

		setTitle("War");		//name, location and size of our frame
		setLocation(150,150);	

		windowWidth = 800;	//can be resized, but starts out reasonably large
		windowHeight = 800;
		setSize(windowWidth,windowHeight);

		/*
		 * Sets up three buttons to control the game
		 */
		buttons[0] = new RegularButton("Play", Color.red, BUTTON_X, BUTTON_Y, 
				BUTTON_WIDTH, BUTTON_HEIGHT);
		
		buttons[1] = new RegularButton("Reset", Color.green, BUTTON_X + BUTTON_WIDTH +
				SPACE, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);

		buttons[2] = new RegularButton("Quit", Color.cyan, BUTTON_X + (BUTTON_WIDTH +
				SPACE)*2, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
		
		/*
		 * sets up a Bargraph to display score
		 */
		bar = new BarGraph(BAR_X,BAR_Y);
		
		/*
		 * Sets up the decks of cards
		 */
		for(int i = 0; i < 2; i++){
			cards[i] = new Card(cardX[i], cardY, 0 , new CardBack());
			areCardsVisible[i] = true;
		}

		myWindow = new CloseWindow(); // allows for window closing
		addWindowListener(myWindow);
		
		addMouseListener(this); //detects mouse events in the frame
		
		System.out.println("This means War!");
		
		initializationComplete = true; // the game setup is complete
		
		setVisible(true);
	} // end default constructor

	/**
	 * The <code>paint</code> method will paint each <code>Card</code> on <code>Game</code>
	 * after then have been initialized.
	 */
	@Override
	public void paint(Graphics pane){
		if(initializationComplete){
			
			//paint our buttons
			for(AButton button: buttons){
				button.paint(pane);		
			}
			
			// paint bar graph
			bar.paint(pane);
			
			// paint cards that are played
			for(int i = 0; i < 4; i++){
				if (areCardsVisible[i]){
					cards[i].paint(pane);
				}
			}
		}
	}
	
	/**
	 * The <code>playTurn</code> method draws a <code>Card</code> from each
	 * players deck and plays it. The player with a higher value card scores
	 * a point.
	 */
	private void playTurn(){
		
		// generates two card values between 2 and 14 (inclusive)
		int[] cardValues = { (int)(Math.random()*13 + 2),
							 (int)(Math.random()*13 + 2)};
		
		// generate two random numbers to determine the suit of each card
		int[] suits = {(int)(Math.random()*4 +1),
					   (int)(Math.random()*4 +1)};
		
		AShape suit;
		
		// Assign a suit shape based on the suit value 
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
				 	suit = null; // should never happen
			}
			// creates new cards based on generated values and suit
			cards[i+2] = new Card(cardX[i+2], cardY, cardValues[i] , suit);
			areCardsVisible[i+2] = true; // allows them be displayed
		}
		
		compareCardValues();
		repaint();
	}
	
	/**
	 * The <code>compareCardValues</code> method compares the value of each
	 * <code>Card</code> that is drawn and updates the game score based on the
	 * winner
	 */
	private void compareCardValues(){
	
		int comparison = cards[2].compareTo(cards[3]),
			newScore = 0;
		
		// checks which player drew a higher card.
		// the gameScore is a net score of cards. If the drawn cards are equal a
		// war occurs. The round score tracks the running of all cards drawn until
		// a tie is broken.
		if(comparison == 0){
			 bar.setRoundScore((bar.getRoundScore())*2);
		}else if(comparison == 1){
			newScore = bar.getGameScore() - bar.getRoundScore();
			bar.setGameScore(newScore);
			bar.resetRoundScore();
		}else if(comparison == -1){
			newScore = bar.getGameScore() + bar.getRoundScore();
			bar.setGameScore(newScore);
			bar.resetRoundScore();
		}
	}
	
	/**
	 * If the mouse click is in the Play Button, a turn will be played.  If it's 
	 * within the reset button, the game resets to the beginning. If it's within
	 * the quit button, the game window will close.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		if(buttons[0].isInside(x, y)){			// Play
			playTurn();
			repaint();
		}else if(buttons[1].isInside(x, y)){	//Reset
			for(int i = 2; i < 4; i++){
				areCardsVisible[i] = false; // makes played cards invisible
			}
			bar.reset(); // reset game score display
			repaint();
		}else if(buttons[2].isInside(x, y)){	//Quit
			System.exit(0);
		}
	}

	/**
	 * Flips the button that has been pressed
	 */
	@Override
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
	@Override
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
}
