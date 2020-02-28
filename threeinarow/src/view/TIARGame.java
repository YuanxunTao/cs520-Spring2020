package view;
import controller.TIARControl;
import model.TIARBlock;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

/**
 * Java implementation of the 3 in a row game, using the Swing framework.
 *
 * This quick-and-dirty implementation violates a number of software engineering
 * principles and needs a thorough overhaul to improve readability,
 * extensibility, and testability.
 */
public class TIARGame {
    public static final String GAME_END_NOWINNER = "Game ends in a draw";
    public static final String PLAYER1_TEXT = "Player 1 to play 'X'";
    public static final String PLAYER2_TEXT = "Player 2 to play 'O'";
    public static final String PLAYER1_WINTXT = "Player 1 wins!!";
    public static final String PLAYER2_WINTXT = "Player 2 wins!!";
    public static final int ROW_NUM = 3;
    public static final int COLUMN_NUM = 3;
    public static final int PLAYER1 = 1;
    public static final int PLAYER2 = 2;

    public JFrame gui = new JFrame("Three in a Row");
    public TIARControl gameControl = new TIARControl(ROW_NUM, COLUMN_NUM);
    public JButton[][] blocks = new JButton[ROW_NUM][COLUMN_NUM];
    public JButton reset = new JButton("Reset");
    public JTextArea playerturn = new JTextArea();
    /**
     * The current player taking their turn
     */
    public int player = PLAYER1;
    public int movesLeft = ROW_NUM*COLUMN_NUM;

    /**
     * Starts a new game in the GUI.
     */
    public static void main(String[] args) {
        TIARGame game = new TIARGame();
        game.gui.setVisible(true);
    }

    /**
     * Creates a new game initializing the GUI.
     */
    public TIARGame() {
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(new Dimension(500, 350));
        gui.setResizable(true);

        JPanel gamePanel = new JPanel(new FlowLayout());
        JPanel game = new JPanel(new GridLayout(ROW_NUM,COLUMN_NUM));
        gamePanel.add(game, BorderLayout.CENTER);

        JPanel options = new JPanel(new FlowLayout());
        options.add(reset);
        JPanel messages = new JPanel(new FlowLayout());
        messages.setBackground(Color.white);

        gui.add(gamePanel, BorderLayout.NORTH);
        gui.add(options, BorderLayout.CENTER);
        gui.add(messages, BorderLayout.SOUTH);

        messages.add(playerturn);
        playerturn.setText(PLAYER1_TEXT);

        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        // Initialize a JButton for each cell of the 3x3 game board.
        for(int row = 0; row<ROW_NUM; row++) {
            for(int column = 0; column<COLUMN_NUM ;column++) {
				gameControl.blocksData[row][column] = new TIARBlock(this);
				// The last row contains the legal moves
				gameControl.blocksData[row][column].setContents("");
				gameControl.blocksData[row][column].setIsLegalMove(row == ROW_NUM-1);
		        blocks[row][column] = new JButton();
		        blocks[row][column].setPreferredSize(new Dimension(75,75));
				updateBlock(row,column);
		        game.add(blocks[row][column]);
		        blocks[row][column].addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
						move((JButton)e.getSource());
		            }
		        });
            }
        }
    }

    /**
     * Moves the current player into the given block.
     *
     * @param block The block to be moved to by the current player
     */
    protected void move(JButton block) {
    	int row=-1;
    	int column=-1;
    	for(int i = 0; i < ROW_NUM; i++) {
    		for(int j = 0; j < COLUMN_NUM; j++) {
    			if(blocks[i][j] == block) {
    				row = i;
    				column = j;
    			}
    		}
    	}
    	if(row == -1 || column == -1) {
    		System.out.println("ERROR: Cannot get current position");
    	}
		--movesLeft;
		if(movesLeft%2 == 1) {
		    playerturn.setText(PLAYER1_TEXT);
		} else{
		    playerturn.setText(PLAYER2_TEXT);
		}
	
		if(player == PLAYER1) {
		    // Check whether player 1 won
		    gameControl.blocksData[row][column].setContents("X");
		    gameControl.blocksData[row][column].setIsLegalMove(false);
		    gameControl.blocksData[row][column].setBlockValue(1);
		    updateBlock(row, column);
		    if(checkEndGame(row, column))
		    	endGame();
		    else {
			    player = PLAYER2;
			    if(row != 0) {
			    	gameControl.blocksData[row-1][column].setIsLegalMove(true);
			    	updateBlock(row-1, column);
			    }
		    }
		} else {
		    // Check whether player 2 won
		    gameControl.blocksData[row][column].setContents("O");
		    gameControl.blocksData[row][column].setIsLegalMove(false);
		    gameControl.blocksData[row][column].setBlockValue(-1);
		    updateBlock(row, column);
		    if(checkEndGame(row, column))
		    	endGame();
		    else {
			    player = PLAYER1;
			    if(row != 0) {
			    	gameControl.blocksData[row-1][column].setIsLegalMove(true);
			    	updateBlock(row-1, column);
			    }
		    }
		}
    }

    /**
     * Updates the block at the given row and column 
     * after one of the player's moves.
     *
     * @param row The row that contains the block
     * @param column The column that contains the block
     */
    protected void updateBlock(int row, int column) {
		blocks[row][column].setText(gameControl.blocksData[row][column].getContents());
		blocks[row][column].setEnabled(gameControl.blocksData[row][column].getIsLegalMove());
    }

    public boolean checkEndGame(int row, int column){
    	if(gameControl.winningMove(row, column) == 1){
			playerturn.setText(PLAYER1_WINTXT);
			return true;
			    
    	}
    	else if(gameControl.winningMove(row, column) == 2){
    		playerturn.setText(PLAYER2_WINTXT);
			return true;
    	}
    	else if(movesLeft==0){
    		playerturn.setText(GAME_END_NOWINNER);
    		return true;
    	}
    	else
    		return false;
    }

    /**
     * Ends the game disallowing further player turns.
     */
    public void endGame() {
		for(int row = 0;row<ROW_NUM;row++) {
		    for(int column = 0;column<COLUMN_NUM;column++) {
		    	gameControl.blocksData[row][column].setIsLegalMove(false);
		    	updateBlock(row, column);
		    }
		}
    }

    /**
     * Resets the game to be able to start playing again.
     */
    public void resetGame() {
        for(int row = 0;row<3;row++) {
            for(int column = 0;column<3;column++) {
                gameControl.blocksData[row][column].reset();
		// Enable the bottom row
				gameControl.blocksData[row][column].setIsLegalMove(row == 2);
				updateBlock(row,column);
            }
        }
        player = PLAYER1;
        movesLeft = 9;
        playerturn.setText(PLAYER1_TEXT);
    }
}
