/*
 * COSC 330: Battleship project
 * Colleen Rogers and Jon Gordy
 * March 8, 2016
 * 
 * ShipInfo.java
 * Description: This class contains contains information about each type of ship
 */

import javax.swing.ImageIcon;

public class ShipInfo {
	private int minX;
	private int maxX;
	
	private int minY;
	private int maxY;
	
	private int length;
	private int width;
	
	private static char orientation;
	private String shipName;
	
	//private Ship ship;
	private static ImageIcon battleshipIconH = new ImageIcon(Images.BATTLESHIP_H,"Battleship_H");
	private static ImageIcon battleshipIconV = new ImageIcon(Images.BATTLESHIP_V, "Battleship_V");
	
	private static ImageIcon carrierIconH = new ImageIcon(Images.CARRIER_H,"Carrier_H");
	private static ImageIcon carrierIconV = new ImageIcon(Images.CARRIER_V,"Carrier_V");
	
	private static ImageIcon cruiserIconH = new ImageIcon(Images.CRUISER_H, "Cruiser_H");
	private static ImageIcon cruiserIconV = new ImageIcon(Images.CRUISER_V,"Cruiser_V");
	
	private static ImageIcon subIconH = new ImageIcon(Images.SUBMARINE_H,"Submarine_H");
	private static ImageIcon subIconV = new ImageIcon(Images.SUBMARINE_V,"Submarine_V");
	
	private static ImageIcon destroyerIconH = new ImageIcon(Images.DESTROYER_H, "Destroyer_H");
	private static ImageIcon destroyerIconV = new ImageIcon(Images.DESTROYER_V,"Destroyer_V");


	public ShipInfo(String draggedShip) {
	
    		if (draggedShip.equals(Images.BATTLESHIP_H) || draggedShip.equals("battleShip")||draggedShip.equals("file:/C:/Users/crogers/Documents/COSC%20330/New%20folder/Battleship/bin/img/Battleship.png")) {
    			maxX = 229;
    			maxY = 320;
    			minY = 20;
    			minX = 8;
    			length = 118;
    			width = 28;
    			shipName = "Battleship";
    			orientation = 'H';    
    		}
    		else if (draggedShip.equals(Images.BATTLESHIP_V) ||draggedShip.equals("battleShipV")||draggedShip.equals("file:/C:/Users/crogers/Documents/COSC%20330/New%20folder/Battleship/bin/img/Battleship_r.png") ) {
				maxX = 320;
				maxY = 229;
				minY = 20;
				minX = 8;
				length = 28;
				width = 118;
				shipName = "BattleshipV";
				orientation = 'V';
			}
    
    		else if (draggedShip.equals(Images.CARRIER_H) || draggedShip.equals("carrier")||draggedShip.equals("file:/C:/Users/crogers/Documents/COSC%20330/New%20folder/Battleship/bin/img/Carrier.png")) {
    			maxX = 199;
    			maxY = 320;
    			minY = 20;
    			minX = 8;
    			length = 148;
    			width = 28;
    			shipName = "Carrier";
    			orientation = 'H';
    		}
    		else if (draggedShip.equals(Images.CARRIER_V) ||draggedShip.equals("carrierV")||draggedShip.equals("file:/C:/Users/crogers/Documents/COSC%20330/New%20folder/Battleship/bin/img/Carrier_r.png")) {
				maxX = 320;
				maxY = 199;
				minY = 20;
				minX = 8;
				length = 28;
				width = 148;
				shipName = "CarrierV";
				orientation = 'V';
			} 
    		
    		else if (draggedShip.equals(Images.CRUISER_H)||draggedShip.equals("cruiser")||draggedShip.equals("file:/C:/Users/crogers/Documents/COSC%20330/New%20folder/Battleship/bin/img/Cruiser.png")) {
    			maxX = 229;
    			maxY = 320;
    			minY = 20;
    			minX = 8;
    			length = 118;
    			width = 28;
    			shipName = "Cruiser";
    			orientation = 'H';
    		} 
    		else if (draggedShip.equals(Images.CRUISER_V)||draggedShip.equals("cruiserV")||draggedShip.equals("file:/C:/Users/crogers/Documents/COSC%20330/New%20folder/Battleship/bin/img/Cruiser_r.png")) {
				maxX = 320;
				maxY = 229;
				minY = 20;
				minX = 8;
				length = 28;
				width = 118;
				shipName = "CruiserV";
				orientation = 'V';
			}
    		
    		else if (draggedShip.equals(Images.DESTROYER_H) ||draggedShip.equals("destroyer")||draggedShip.equals("file:/C:/Users/crogers/Documents/COSC%20330/New%20folder/Battleship/bin/img/Destroyer.png")) {
    			maxX = 289;
    			maxY = 320;
    			minY = 20;
    			minX = 8;
    			length = 58;
    			width = 28;
    			shipName = "Destroyer";
    			orientation = 'H';
    		} 
    		else if (draggedShip.equals(Images.DESTROYER_V)||draggedShip.equals("destroyerV")||draggedShip.equals("file:/C:/Users/crogers/Documents/COSC%20330/New%20folder/Battleship/bin/img/Destroyer_r.png")) {
				maxX = 320;
				maxY = 289;
				minY = 20;
				minX = 8;
				length = 28;
				width = 58;
				shipName = "DestroyerV";
				orientation = 'V';
			}
    		
    		else if (draggedShip.equals(Images.SUBMARINE_H) ||draggedShip.equals("sub")||draggedShip.equals("file:/C:/Users/crogers/Documents/COSC%20330/New%20folder/Battleship/bin/img/Submarine.png")) {
    			maxX = 260;
    			maxY = 320;
    			minY = 20;
    			minX = 8;
    			length = 88;
    			width = 28;
    			shipName = "Submarine";
    			orientation = 'H';
    		} 
    		else if (draggedShip.equals(Images.SUBMARINE_V) ||draggedShip.equals("subV")||draggedShip.equals("file:/C:/Users/crogers/Documents/COSC%20330/New%20folder/Battleship/bin/img/Submarine_r.png")) {
				maxX = 320;
				maxY = 260;
				minY = 20;
				minX = 8;
				length = 28;
				width = 88;
				shipName = "SubmarineV";
				orientation = 'V';
			}
    		
    		else{
				System.out.println("The ship is not recognized and will not move correctly");
				System.out.println("Ship is " +draggedShip);
    		}
		
	}
	public static ImageIcon getIcon(String x){
		
		if(x.equals("carrier")){
    				return carrierIconH;
    				
		}	
		if(x.equals("battleship")){
			return battleshipIconH;
			
		}
		
		if (x.equals("cruiser")) {
			return cruiserIconH;
		}
		
		if (x.equals("sub")) {
			return subIconH;

		}
		if  (x.equals("destroyer")){
			return destroyerIconH;

		}	
		if(x.equals("carrierV")){
			return carrierIconV;
			
        }	
        if(x.equals("battleshipV")){
        	return battleshipIconV;
        	
        }
        
        if (x.equals("cruiserV")) {
        	return cruiserIconV;
        }
        
        if (x.equals("subV")) {
        	return subIconV;
        
        }
        else  {
        	return destroyerIconV;
        
        }	
	}

	public int getMinX() {
		return minX;
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	public int getMinY() {
		return minY;
	}

	public int getShipWidth() {
		return width;
	}

	public int getShipLength() {
		return length;
	}
	public String getShip() {
		return shipName;
	}

	public char getOrientation(){
		return orientation;
	}
	public static void setOrientation(char o){
		orientation = o;
		
	}
	
	
}