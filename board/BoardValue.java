package board;

/**
 * state of each grid displayed on the board
 *
 * @author Brandon Lu, Shaina Rossel, Betty Zhang, Charlene Madayag
 */
public enum BoardValue {
    /**
     * attacked an area containing a ship
     */
    HIT,

    /**
     * attacked an area that does not contain a ship
     */
    MISS,
    /**
     * default value of board, no action taken
     */
    EMPTY,
    /**
     * for the users to view their own game board only, displays
     * where they placed their ships
     */
    SHIP;
}
