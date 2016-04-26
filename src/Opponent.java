
public class Opponent {

	private String[][] oppBoard;

	public Opponent() {

		oppBoard = new String[10][10];

		for (int row = 0; row < oppBoard.length; row++) {
			for (int col = 0; col < oppBoard[row].length; col++) {
				oppBoard[row][col] = "~";

			}
		}

	}

	public String getGridContents(int x, int y) {
		return oppBoard[x][y];
	}

	public void setGridContents(int x, int y, String str) {
		oppBoard[x][y] = str;
	}

}