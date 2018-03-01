import java.util.Random;

public class ComputerPlayer extends Player {
	private Random move = new Random();

	public ComputerPlayer(String newName) {
		super(newName);
	}

	public int[] setShips(int shipID, int shipLength, int maxSize) {
		boolean validPlacement = false;
		int[] shipProperties = new int[5];
		// char orientation;

		while (validPlacement != true) {
			try {
				// super.addShip(shipID, shipLength, orientation, column, row);
				shipProperties[0] = shipID;
				shipProperties[1] = getOrientation();
				shipProperties[2] = shipLength;
				shipProperties[3] = getPosition(1, maxSize);
				shipProperties[4] = getPosition(1, maxSize);
				
				validPlacement = true;

			} catch (IllegalArgumentException e) {
				validPlacement = false;
			} catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
				// possible errors when doing the conversions of the string input
				validPlacement = false;
			}
		}
		return shipProperties;
	}

	public int[] attack(int maxSize) {
		boolean validFormat = false;
		int[] position = new int[2];
		while (validFormat != true) {
			try {
				int row = getPosition(1, maxSize);
				int column = getPosition(1, maxSize);
				System.out.println("Computer attacking" + row + "," + column);
				position[0] = row;
				position[1] = column;
				// super.makeAttack(row,column);
				validFormat = true;

			} catch (IllegalArgumentException e) {
				validFormat = false;
			}
		}
		return position;
	}

	private int getOrientation() {
		int min = 0;
		int max = 1;
		int randomNum = move.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	private int getPosition(int min, int max) {
		int randomNum = move.nextInt((max - min) + 1) + min;
		return randomNum;
	}

}