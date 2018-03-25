package game;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.InputMismatchException;
import game.*;

/**
 * JUNIT testing for set up for board
 * 
 * @author Brandon Lu, Shaina Rossel, Betty Zhang, Charlene Madayag
 */

public class GameTest {

	@Test
	public void test_enableAI() {
		Game g = new Game();
		g.enableAI();
		assertTrue("Enabled the AI", g.getAIStatus());
	}

//	@Test
//	public void test_startCheck() {
//		Game g = new Game();
//		g.startCheck();
//		assertEquals("Correct board size", 10, g.getUserBoardSize());
//	}
//
//	@Test
//	public void test_startCheck2() {
//		Game g = new Game();
//		g.startCheck();
//		assertEquals("Correct ship count", 2, g.getShipCount());
//
//	}

}