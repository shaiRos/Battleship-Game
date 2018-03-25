import static org.junit.Assert.*;
import org.junit.Test;
import java.util.InputMismatchException;

/**
*	JUNIT testing for the menu screen. Testing for user input for the menu.
*	@author Brandon Lu, Shaina Rossel, Betty Zhang, Charlene Madayag
*/


public class MenuScreenTest{

	@Test
	public void test_printMenu1(){
		MenuScreen m = new MenuScreen();
		try { 
			m.userChoice = Integer.parseInt("kahgakga");
		} catch (Exception e) {
			assertTrue("Wrong input. Cannot take random words/characters", m.getUserSelect());
		}
	}
	
	@Test
	public void test_printMenu2(){
		MenuScreen m = new MenuScreen();
		m.userChoice = (4);
		assertTrue("Wrong input. Cannot take any integers other than 1, 2, or 3", m.getUserSelect());
	}

	@Test
	public void test_printMenu3(){
		MenuScreen m = new MenuScreen();
		try { 
			m.userChoice = Integer.parseInt("}|:><:");

		} catch (Exception e) {
			assertTrue("Wrong input. Cannot take any special characters", m.getUserSelect());
		}
	}

	@Test
	public void test_printMenu4(){
		MenuScreen m = new MenuScreen();
		m.setInput(1);
		assertFalse("Chooses Player vs Player", m.getUserSelect());
	}

	@Test
	public void test_printMenu5(){
		MenuScreen n = new MenuScreen();
		n.setInput(2);
		assertFalse("Chooses Player vs AI", n.getUserSelect());
	}

	@Test
	public void test_printMenu6(){
		MenuScreen o = new MenuScreen();
		o.setInput(3);
		assertFalse("Chooses to exit", o.getUserSelect());
	}



}