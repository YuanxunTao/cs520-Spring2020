package controller;
import model.TIARBlock;
public class TIARControl{
	public TIARBlock[][] blocksData;
	public int row;
	public int column;

    /**
     * Creates a new game board with blocks that will be contained in the given game.
     *
     * @param row The row number of the game board
     * @param colume The column number of the game board
     * @throws IllegalArgumentException When the given int is zero
     */
    public TIARControl(int row, int column) {
    	super();

    	if (row == 0 || column == 0) {
    	    throw new IllegalArgumentException("The size must be non-zero.");
    	}
    	
    	this.blocksData = new TIARBlock[row][column];
    	this.row = row;
    	this.column = column;
    }

    /**
     * Check if the current move is a winning move.
     *
     * @param row The row value for current move.
     * @param column The column value for current move.
     * @return Return 1 when player 1 wins, 2 when player 2 wins, 0 when no one wins.
     */
    public int winningMove(int row, int column){
    	int counter = 0;
    	//check 3 in row
    	for(int i = 0; i < this.column; i++){
    		counter += this.blocksData[row][i].getBlockValue();
    	}
    	if(counter == 3)
			return 1;
		if(counter == -3)
			return 2;
    	counter = 0;
    	//check 3 in column
    	for(int i = 0; i < this.row; i++){
    		counter += this.blocksData[i][column].getBlockValue();
    	}
    	if(counter == 3)
			return 1;
		if(counter == -3)
			return 2;
    	counter = 0;
    	//check 3 in cross
    	if(row == column){
    		for(int i = 0; i < 3; i++){
    			counter += this.blocksData[i][i].getBlockValue();
    		}
    		if(counter == 3)
				return 1;
			if(counter == -3)
				return 2;
    	}
    	else if(row + column ==2){
    		for(int i = 0; i < 3; i++){
    			counter += this.blocksData[i][2-i].getBlockValue();
    		}
    		if(counter == 3)
				return 1;
			if(counter == -3)
				return 2;
    	}
    	return 0;
    }
}