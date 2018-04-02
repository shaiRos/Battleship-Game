package board;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.InputMismatchException;
import board.*;

/**
 * JUNIT testing for set up for board
 * 
 * @author Brandon Lu, Shaina Rossel, Betty Zhang, Charlene Madayag
 */

public class BoardTest {

	@Test
	public void test_setBoardSize() {
		Board b = new Board();
		try {
			b.setBoardSize(Integer.parseInt("k"));
		} catch (Exception e) {
			assertEquals("Invalid board size", 5, b.getBoardSize());
		}
	}


	// Possible change!
	@Test
	public void test_returnBoard1() {
		System.out.println("herE");
		Board b = new Board();
		b.setBoardType(1);
		assertFalse("Game board", b.getBoardType());
	}

	@Test
	public void test_returnBoard2() {
		System.out.println("there");
		Board b = new Board();
		b.setBoardType(2);
		assertTrue("It is not game board", b.getBoardType());
	}

}
