/*
 * COSC 330: Battleship project
 * Colleen Rogers and Jon Gordy
 * March 8, 2016
 * 
 * Images.java
 * Description: This class stores basic information about a ship (name, image, orientation)
 */

import javax.swing.ImageIcon;

public class Ship {

	private String shipName;
	private ImageIcon img;
	private char orientation;

	public Ship(String name, String fileLocation, char o) {
		shipName = name;
		img = new ImageIcon(getClass().getClassLoader().getResource(fileLocation));
		orientation=o;
	}
	

	public String getShipName() {
		return shipName;
	}

	public void setShipName(String n) {
		shipName = n;
	}

	public ImageIcon getIcon() {
		return img;
	}

	public void setImg(String x) {
		img = new ImageIcon(x);
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(char o) {
		orientation = o;
	}
	
}