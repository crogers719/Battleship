/*
 * COSC 330: Battleship project
 * Colleen Rogers and Jon Gordy
 * March 8, 2016
 * 
 * Images.java
 * Description: This class determines the location where a ship is dropped
 */

import java.awt.Point;

public class GetSquareDropped {
	int x, y, x2, y2, minX, maxX, minY, maxY;
	String ship;
	static char orientation = ' ';
	static Boolean validatePlacement = true;
	static Boolean droppedBattle = false;
	static Boolean droppedCarrier = false;
	static Boolean droppedDestroyer = false;
	static Boolean droppedCruiser = false;
	static Boolean droppedSub = false;

	public GetSquareDropped(Point point, String draggedShip) {
		ShipInfo getShipInfo = new ShipInfo(draggedShip);

		// valid drop is static--so reset
		validatePlacement = true;
		
		x = (int) point.getX();
		y = (int) point.getY();
		
		minX = getShipInfo.getMinX();
		maxX = getShipInfo.getMaxX();
		minY = getShipInfo.getMinY();
		maxY = getShipInfo.getMaxY();
		
		ship = getShipInfo.getShip();
		
		orientation = getShipInfo.getOrientation();
		String shipName = getShipInfo.getShip();

		// Check ship within grid
		if (x > maxX && orientation == 'H') {
			x = maxX;
		}
		if (x < minX && orientation == 'H') {
			x = minX;
		}
		if (y > maxY && orientation == 'H') {
			y = maxY;
		}
		if (y < minY && orientation == 'H') {
			y = minY;
		}

		if (x > maxX && orientation == 'V') {
			x = maxX;
		}
		if (x < minX && orientation == 'V') {
			x = minX;
		}
		if (y > maxY && orientation == 'V') {
			y = maxY;
		}
		if (y < minY && orientation == 'V') {
			y = minY;
		}

		// drop point in grid board
		x2 = (x - 21) / 30;
		y2 = (y - 32) / 30;

		// set ship in gird correctly
		x = (x2 * 30) + 21;
		y = (y2 * 30) + 32;

		// No Overlapping ships
		if (shipName.equals("Battleship") && orientation == 'H') {

			for (int i = 0; i < 4; i++) {
				if (Game.playerBoard.getGridContents(x2 + i, y2) != "~") {
					validatePlacement = false;
				}
			}

			if (validatePlacement) {
				for (int i = 0; i < 4; i++) {
					Game.playerBoard.setGridContents(x2 + i, y2, "B");
				}
				droppedBattle = true;
				//When placed remove battleship from panel
				Game.battleshipImgLabelV.setVisible(false);
				Game.battleshipImgLabelH.setVisible(false);
			
			}
		}

		if (shipName.equals("BattleshipV") && orientation == 'V') {

			for (int i = 0; i < 4; i++) {
				if (Game.playerBoard.getGridContents(x2, y2 + i) != "~") {
					validatePlacement = false;
				}

			}
			if (validatePlacement) {
				for (int i = 0; i < 4; i++) {
					Game.playerBoard.setGridContents(x2, y2 + i, "B");
				}
				droppedBattle = true;
				//When placed remove battleship from panel
				Game.battleshipImgLabelH.setVisible(false);
				Game.battleshipImgLabelV.setVisible(false);
			} 
		}
		if (shipName.equals("Submarine") && orientation == 'H') {

			for (int i = 0; i < 3; i++) {
				if (Game.playerBoard.getGridContents(x2 + i, y2) != "~") {
					validatePlacement = false;
				}
			}
			if (validatePlacement) {
				for (int i = 0; i < 3; i++) {
					Game.playerBoard.setGridContents(x2 + i, y2, "S");
				}
				droppedSub = true;
				//When placed remove sub from panel
				Game.submarineImgLabelH.setVisible(false);
				Game.submarineImgLabelV.setVisible(false);
			} 
		}

		if (shipName.equals("SubmarineV") && orientation == 'V') {

			for (int i = 0; i < 3; i++) {
				if (Game.playerBoard.getGridContents(x2, y2 + i) != "~") {
					validatePlacement = false;
				}

			}
			if (validatePlacement) {
				for (int i = 0; i < 3; i++) {
					Game.playerBoard.setGridContents(x2, y2 + i, "S");
				}
				droppedSub = true;
				//When placed remove sub from panel
				Game.submarineImgLabelH.setVisible(false);
				Game.submarineImgLabelV.setVisible(false);
			}
		}

		
		if (shipName.equals("Cruiser") && orientation == 'H') {

			for (int i = 0; i < 4; i++) {
				if (Game.playerBoard.getGridContents(x2 + i, y2) != "~") {
					validatePlacement = false;
				}
			}
			if (validatePlacement) {
				for (int i = 0; i < 4; i++) {
					Game.playerBoard.setGridContents(x2 + i, y2, "Z");
				}
				droppedCruiser = true;
				//When placed remove Cruiser from panel
				Game.cruiserImgLabelH.setVisible(false);
				Game.cruiserImgLabelV.setVisible(false);
			} 
		}
		if (shipName.equals("CruiserV") && orientation == 'V') {

			for (int i = 0; i < 4; i++) {
				if (Game.playerBoard.getGridContents(x2, y2 + i) != "~") {
					validatePlacement = false;
				}

			}
			if (validatePlacement) {
				for (int i = 0; i < 4; i++) {
					Game.playerBoard.setGridContents(x2, y2 + i, "Z");
				}
				droppedCruiser = true;
				//When placed remove cruiser from panel
				Game.cruiserImgLabelH.setVisible(false);
				Game.cruiserImgLabelV.setVisible(false);
			}  
		}
		if (shipName.equals("Carrier") && orientation == 'H') {

			for (int i = 0; i < 5; i++) {
				if (Game.playerBoard.getGridContents(x2 + i, y2) != "~") {
					validatePlacement = false;
				}
			}
			if (validatePlacement) {
				for (int i = 0; i < 5; i++) {
					Game.playerBoard.setGridContents(x2 + i, y2, "C");
				}
				//When placed remove carrier from panel
				droppedCarrier = true;
				Game.carrierImgLabelH.setVisible(false);
				Game.carrierImgLabelV.setVisible(false);
			} 
		}

		if (shipName.equals("CarrierV") && orientation == 'V') {

			for (int i = 0; i < 5; i++) {

				if (Game.playerBoard.getGridContents(x2, y2 + i) != "~") {
					validatePlacement = false;

				}
			}
			if (validatePlacement) {
				for (int i = 0; i < 5; i++) {
					Game.playerBoard.setGridContents(x2, y2 + i, "C");
				}
				droppedCarrier = true;
				//When placed remove carrier from panel
				Game.carrierImgLabelH.setVisible(false);
				Game.carrierImgLabelV.setVisible(false);
			} 
		}

		
		if (shipName.equals("Destroyer") && orientation == 'H') {

			for (int i = 0; i < 2; i++) {
				if (Game.playerBoard.getGridContents(x2 + i, y2) != "~") {
					validatePlacement = false;
				}
			}

			if (validatePlacement) {
				for (int i = 0; i < 2; i++) {
					Game.playerBoard.setGridContents(x2 + i, y2, "D");
				}
				droppedDestroyer = true;
				//When placed remove destroyer from panel
				Game.destroyerImgLabelH.setVisible(false);
				Game.destroyerImgLabelV.setVisible(false);
			} 
		}

		if (shipName.equals("DestroyerV") && orientation == 'V') {

			for (int i = 0; i < 2; i++) {
				if (Game.playerBoard.getGridContents(x2, y2 + i) != "~") {
					validatePlacement = false;
				}

			}
			if (validatePlacement) {
				for (int i = 0; i < 2; i++) {
					Game.playerBoard.setGridContents(x2, y2 + i, "D");
				}
				droppedDestroyer = true;
				//When placed remove destroyer from panel
				Game.destroyerImgLabelH.setVisible(false);
				Game.destroyerImgLabelV.setVisible(false);
			} 

		}
	}

	public static Boolean checkAllShips() {
		if (droppedBattle && droppedCruiser && droppedCarrier && droppedSub && droppedDestroyer)
			return true;
		else
			return false;

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}