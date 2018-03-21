import static org.junit.Assert.*;
import org.junit.Test;

/**
*	JUNIT testing for the menu screen. Testing for user input for the menu.
*	UNFINISHED WORK, currently not working.
*	@author Brandon Lu, Shaina Rossel, Betty Zhang, Charlene Madayag
*/

public class MenuScreenTest{

	//Not sure how to check, userChoice when non-integer and getUserSelect() is not working?
	@Test
	public void test_printMenu1(){
		MenuScreen m = new MenuScreen();
		m.userChoice = "kahgakga";
		assertTrue("Wrong input. Cannot take random words/characters", m.getUserSelect());
	}

	//Works
	@Test
	public void test_printMenu2(){
		MenuScreen m = new MenuScreen();
		m.userChoice = (4);
		assertTrue("Wrong input. Cannot take any integers other than 1, 2, or 3", m.getUserSelect());
	}

	//Not sure how to check because userChoice takes int
	@Test
	public void test_printMenu3(){
		MenuScreen m = new MenuScreen();
		m.userChoice = ("}|:><:");
		assertTrue("Wrong input. Cannot take any special characters", m.getUserSelect());
	}

	@Test
	public void test_printMenu4(){
		MenuScreen m = new MenuScreen();
		m.userChoice = (1);
		assertFalse("Choosing Player vs Player", m.getUserSelect());
	}

	@Test
	public void test_printMenu5(){
		MenuScreen m = new MenuScreen();
		m.userChoice = (2);
		assertFalse("Choosing Player vs AI", m.getUserSelect());
	}

	@Test
	public void test_printMenu6(){
		MenuScreen m = new MenuScreen();
		m.userChoice = (3);
		assertFalse("Choosing to exit", m.getUserSelect());
	}



}