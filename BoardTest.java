import static org.junit.Assert.*;
import org.junit.Test;
import java.util.InputMismatchException;

/**
*	JUNIT testing for set up for board
*	@author Brandon Lu, Shaina Rossel, Betty Zhang, Charlene Madayag
*/

public class BoardTest{

	@Test
	public void test_setBoardSize1(){
		Board b = new Board();
		try { 
			b.setBoardSize(Integer.parseInt("k"));
		} catch (Exception e) {
			assertEquals("Invalid board size", 5 , b.getBoardSize());		
		}
	}

	@Test
	public void test_setBoardSize2(){
		Board b = new Board();
		try { 
			b.setBoardSize(-2);
		} catch (Exception e) {
			assertEquals("Invalid board size", 5 , b.getBoardSize());		
		}
	}

	@Test
	public void test_setMinShipSize1(){
		Board b = new Board();
		try { 
			b.setMinShipSize(Integer.parseInt("k"));
		} catch (Exception e) {
			assertEquals("Invalid board size", 2 , b.getMinShipSize());	
		}
	}

	@Test
	public void test_setMinShipSize2(){
		Board b = new Board();
		try { 
			b.setMinShipSize(-2);
		} catch (Exception e) {
			assertEquals("Invalid board size", 2 , b.getMinShipSize());	
		}
	}

	@Test
	public void test_setMaxShipSize1(){
		Board b = new Board();
		try { 
			b.setMaxShipSize(Integer.parseInt("k"));
		} catch (Exception e) {
			assertEquals("Invalid board size", 5 , b.getMaxShipSize());		
		}
	}
	@Test
	public void test_setMaxShipSize2(){
		Board b = new Board();
		try { 
			b.setMaxShipSize(-2);
		} catch (Exception e) {
			assertEquals("Invalid board size", 5 , b.getMaxShipSize());		
		}
	}

//Possible change!
	@Test
	public void test_returnBoard1(){
		//System.out.println("herE");
		Board b = new Board();
		b.setBoardType(1);
		assertFalse("Game board",b.getBoardType());
	}

	@Test
	public void test_returnBoard2(){
		//System.out.println("there");
		Board b = new Board();
		b.setBoardType(2);
		assertTrue("It is not game board",b.getBoardType());
	}




}




