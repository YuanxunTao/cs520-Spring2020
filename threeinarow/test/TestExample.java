import org.junit.After;
import view.TIARGame;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * An example test class, which merely shows how to write JUnit tests.
 */
public class TestExample {
    private TIARGame game;

    @Before
    public void setUp() {
	game = new TIARGame();
    }

    @After
    public void tearDown() {
	game = null;
    }

    @Test
    public void testNewGame() {
        assertEquals (1, game.player);
        assertEquals (9, game.movesLeft);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewBlockViolatesPrecondition() {
	ThreeInARowBlock block = new ThreeInARowBlock(null);
    }
    
    @Test
    public void testP1WinRow() {
    	game.blocks[2][0].doClick();
    	game.blocks[1][0].doClick();
    	game.blocks[2][1].doClick();
    	game.blocks[1][1].doClick();
    	game.blocks[2][2].doClick();
    	assertEquals(game.PLAYER1_WINTXT,game.playerturn.getText());
    	game.resetGame();
    }
    
    @Test
    public void testP2WinRow() {
    	game.blocks[2][0].doClick();
    	game.blocks[1][0].doClick();
    	game.blocks[2][1].doClick();
    	game.blocks[1][1].doClick();
    	game.blocks[0][0].doClick();
    	game.blocks[2][2].doClick();
    	game.blocks[0][1].doClick();
    	game.blocks[1][2].doClick();
    	assertEquals(game.PLAYER2_WINTXT,game.playerturn.getText());
    	game.resetGame();
    }
    
    @Test
    public void testP1WinCol() {
    	game.blocks[2][0].doClick();
    	game.blocks[2][1].doClick();
    	game.blocks[1][0].doClick();
    	game.blocks[1][1].doClick();
    	game.blocks[0][0].doClick();
    	assertEquals(game.PLAYER1_WINTXT,game.playerturn.getText());
    	game.resetGame();
    }
    
    @Test
    public void testP2WinCol() {
    	game.blocks[2][0].doClick();
    	game.blocks[2][1].doClick();
    	game.blocks[1][0].doClick();
    	game.blocks[1][1].doClick();
    	game.blocks[2][2].doClick();
    	game.blocks[0][1].doClick();
    	assertEquals(game.PLAYER2_WINTXT,game.playerturn.getText());
    	game.resetGame();
    }
    
    @Test
    public void testP1WinCross() {
    	game.blocks[2][0].doClick();
    	game.blocks[2][1].doClick();
    	game.blocks[1][1].doClick();
    	game.blocks[1][0].doClick();
    	game.blocks[2][2].doClick();
    	game.blocks[1][2].doClick();
    	game.blocks[0][2].doClick();
    	assertEquals(game.PLAYER1_WINTXT,game.playerturn.getText());
    	game.resetGame();
    }
    
    @Test
    public void testP2WinCross() {
    	game.blocks[2][1].doClick();
    	game.blocks[2][0].doClick();
    	game.blocks[2][2].doClick();
    	game.blocks[1][1].doClick();
    	game.blocks[1][2].doClick();
    	game.blocks[0][2].doClick();
    	assertEquals(game.PLAYER2_WINTXT,game.playerturn.getText());
    	game.resetGame();
    }
    
    @Test
    public void testDraw() {
    	game.blocks[2][0].doClick();
    	game.blocks[2][1].doClick();
    	game.blocks[1][1].doClick();
    	game.blocks[1][0].doClick();
    	game.blocks[0][0].doClick();
    	game.blocks[2][2].doClick();
    	game.blocks[1][2].doClick();
    	game.blocks[0][2].doClick();
    	game.blocks[0][1].doClick();
    	assertEquals(game.GAME_END_NOWINNER,game.playerturn.getText());
    	game.resetGame();
    }
}
