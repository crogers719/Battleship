/*
 * COSC 330: Battleship project
 * Colleen Rogers and Jon Gordy
 * March 8, 2016
 * 
 * Images.java
 * Description: This class uses a 2-d array to create a player board
 */

public class Board {
	private String[][] playerBoard;

	public Board() {

		playerBoard = new String[10][10];

		for (int row = 0; row < playerBoard.length; row++) {
			for (int col = 0; col < playerBoard[row].length; col++) {
				playerBoard[row][col] = "~";

			}
		}
	}

	public String getGridContents(int x, int y) {
		return playerBoard[x][y];
	}

	public void setGridContents(int x, int y, String str) {
		playerBoard[x][y] = str;
	}

}