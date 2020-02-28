package model;
import view.TIARGame;

/**
 * The ThreeInARowBlock class represents a given block in the game.
 */
public class TIARBlock
{
    /**
     * The game that contains this block
     */
    private TIARGame game;

    /**
     * The current value of the contents of this block
     */
    private String contents;

    /**
     * Whether or not it is currently legal to move into this block
     */
    private boolean isLegalMove;

    private int blockValue;

    /**
     * Creates a new block that will be contained in the given game.
     *
     * @param game The game that will contain the new block
     * @throws IllegalArgumentException When the given game is null
     */
    public TIARBlock(TIARGame game) {
    	super();

    	if (game == null) {
    	    throw new IllegalArgumentException("The game must be non-null.");
    	}
    	
    	this.game = game;
    	this.reset();
    }

    public TIARGame getGame() {
	   return this.game;
    }

    /**
     * Sets the contents of this block to the given value.
     *
     * @param value The new value for the contents of this block
     * @throws IllegalArgumentException When the given value is null
     */
    public void setContents(String value) {
    	if (value == null) {
    	    throw new IllegalArgumentException("Contents must be non-null.");
    	}
	   this.contents = value;
    }

    /**
     * Sets the block value of this block to the given value.
     *
     * @param value The new value for the block value of this block
     */
    public void setBlockValue(int value){
        this.blockValue = value;
    }

    /**
     * Returns the non-null String value of the contents of this block.
     *
     * @return The non-null String value
     */
    public String getContents() {
	return this.contents;
    }

    /**
     * Returns the non-null int value of the block value of this block.
     *
     * @return The non-null int value
     */
    public int getBlockValue(){
        return this.blockValue;
    }

    public void setIsLegalMove(boolean isLegalMove) {
	   this.isLegalMove = isLegalMove;
    }

    public boolean getIsLegalMove() {
	   return this.isLegalMove;
    }

    /**
     * Resets this block before starting a new game.
     */
    public void reset() {
    	this.contents = "";
        this.blockValue = 0;
    	this.isLegalMove = false;
    }
}
