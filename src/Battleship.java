/*
 * COSC 330: Battleship project
 * Colleen Rogers and Jon Gordy
 * March 8, 2016
 * 
 * Battleship.java
 * Description: This class contains all of the game logic for battleship
 */

import java.awt.Point;

import javax.swing.JLabel;

public class Battleship {
	final Sounds sound = new Sounds();
	
	public static int numShipsLeft = 5;
	int battleshipHoles = 4;
	int carrierHoles = 5;
	int cruiserHoles = 4;
	int subHoles = 3;
	int destroyerHoles = 2;
	char s;
	

	public String InterpretMsg(String message) {

		if (message.length() < 5) {
			return message;
		}

		int x = 200;
		int y = 200;
		int x2 = 200, y2 = 200;
		char l, m, n;

		l = message.charAt(0);
		m = message.charAt(1);
		n = message.charAt(2);
		// ship that is sunk, 0-4
		s = message.charAt(3);

		if (message.length() == 5) {
			char a = message.charAt(2);
			char b = message.charAt(4);

			x = Character.getNumericValue(a);
			y = Character.getNumericValue(b);
			x2 = (x * 30) + 21;
			y2 = (y * 30) + 32;
		}

		
		/*
		 * 		@@x,y 	indicates that an attack has been launched on a particular square
		 */

		if (l == '@' && m == '@') {
			String pGridContents = Game.playerBoard.getGridContents(x, y);
			// hit battleship
			if (pGridContents == "B") {
				JLabel hitLabel = new JLabel();
				JLabel missLabel = new JLabel();
				hitLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(Images.HIT_IMG), "Hit"));
				missLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(Images.MISS_IMG), "Miss"));

				battleshipHoles--;
				
				// displays on playerGrid enemy shots
				hitLabel.setBounds(x2, y2, 28, 28);
				Game.playerPanel.add(hitLabel, -1);
				Game.playerPanel.repaint();
				Game.playerPanel.revalidate();
				
				if(battleshipHoles == 0){
					numShipsLeft--;
				}
				// checks for sunk ship and endGame condition
				if (battleshipHoles == 0 && numShipsLeft != 0) {
					
					sound.play(5);
					Game.playerTurn = true;
					Game.yourTurnMessage.setVisible(true);
					Game.opponentTurnMessage.setVisible(false);
					return ("^^" + String.valueOf(x) + "0" + String.valueOf(y));
				}
				if (numShipsLeft == 0) {
					
					Game.displayMessage("\nYou Lost!");
					Game.yourTurnMessage.setVisible(false);
					Game.opponentTurnMessage.setVisible(false);
					Game.lossMessage.setVisible(true);
					sound.play(4);
					Game.gameStarted = false;
					Game.playerTurn = false;
					return (">>"+ String.valueOf(x) + "," + String.valueOf(y));
				}

				else {
				
					sound.play(1);
					Game.playerTurn = true;
					Game.yourTurnMessage.setVisible(true);
					Game.opponentTurnMessage.setVisible(false);
					return ("!!" + String.valueOf(x) + "," + String.valueOf(y));
				}

			}
			// Hit Carrier
			else if (pGridContents == "C") {
				JLabel hitLabel = new JLabel();
				JLabel missLabel = new JLabel();
				hitLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(Images.HIT_IMG),"Hit"));
				missLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(Images.MISS_IMG),"Miss"));

				carrierHoles--;

				// displays on playerGrid enemy shots
				hitLabel.setBounds(x2, y2, 28, 28);
				Game.playerPanel.add(hitLabel, -1);
				Game.playerPanel.repaint();
				Game.playerPanel.revalidate();
				
				if(carrierHoles == 0){
					numShipsLeft--;
				}

				// checks for sunk ship and endGame condition
				if (carrierHoles == 0 && numShipsLeft != 0) {
				
					sound.play(6);
					Game.playerTurn = true;
					Game.yourTurnMessage.setVisible(true);
					Game.opponentTurnMessage.setVisible(false);
					return ("^^" + String.valueOf(x) + "1" + String.valueOf(y));
				}
				if (numShipsLeft == 0) {
			
					sound.play(1);
					Game.displayMessage("\nYou Lost");
					Game.yourTurnMessage.setVisible(false);
					Game.opponentTurnMessage.setVisible(false);
					Game.lossMessage.setVisible(true);
					sound.play(4);
					Game.gameStarted = false;
					Game.playerTurn = false;
					return (">>"+ String.valueOf(x) + "," + String.valueOf(y));
				}

			
				sound.play(1);
				Game.playerTurn = true;
				Game.yourTurnMessage.setVisible(true);
				Game.opponentTurnMessage.setVisible(false);
				return ("!!" + String.valueOf(x) + "," + String.valueOf(y));

			}
			// Hit Cruiser
			else if (pGridContents == "Z") {
				JLabel hitLabel = new JLabel();
				JLabel missLabel = new JLabel();
				hitLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(Images.HIT_IMG),"Hit"));
				missLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(Images.MISS_IMG),"Miss"));

				cruiserHoles--;

				// displays on playerGrid enemy shots
				hitLabel.setBounds(x2, y2, 28, 28);
				Game.playerPanel.add(hitLabel, -1);
				Game.playerPanel.repaint();
				Game.playerPanel.revalidate();
				
				if(cruiserHoles == 0){
					numShipsLeft--;
				}
				// checks for sunk ship and endGame condition
				if (cruiserHoles == 0 && numShipsLeft != 0) {
				
					sound.play(6);
					Game.playerTurn = true;
					Game.yourTurnMessage.setVisible(true);
					Game.opponentTurnMessage.setVisible(false);
					return ("^^" + String.valueOf(x) + "2" + String.valueOf(y));
				}
				if (numShipsLeft == 0) {
					
					sound.play(1);
					//Game.displayMessage("\n !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					Game.displayMessage("\nYou Lost!");
					//Game.displayMessage("\n !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					Game.yourTurnMessage.setVisible(false);
					Game.opponentTurnMessage.setVisible(false);
					Game.lossMessage.setVisible(true);
					sound.play(4);
					Game.gameStarted = false;
					Game.playerTurn = false;
					return (">>"+ String.valueOf(x) + "," + String.valueOf(y));
				}

			
				sound.play(1);
				Game.playerTurn = true;
				Game.yourTurnMessage.setVisible(true);
				Game.opponentTurnMessage.setVisible(false);
				return ("!!" + String.valueOf(x) + "," + String.valueOf(y));
			}

			// Hit Submarine
			else if (pGridContents == "S") {
				JLabel hitLabel = new JLabel();
				JLabel missLabel = new JLabel();
				hitLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(Images.HIT_IMG), "Hit"));
				missLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(Images.MISS_IMG),"Miss"));
				subHoles--;

				// displays on playerGrid enemy shots
				hitLabel.setBounds(x2, y2, 28, 28);
				Game.playerPanel.add(hitLabel, -1);
				Game.playerPanel.repaint();
				Game.playerPanel.revalidate();
				if(subHoles == 0){
					numShipsLeft--;
				}
				// checks for sunk ship and endGame condition
				if (subHoles == 0 && numShipsLeft != 0) {
					
					sound.play(6);
					Game.playerTurn = true;
					Game.yourTurnMessage.setVisible(true);
					Game.opponentTurnMessage.setVisible(false);
					return ("^^" + String.valueOf(x) + "3" + String.valueOf(y));
				}
				if (numShipsLeft == 0) {
					
					sound.play(1);
					//Game.displayMessage("\n !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					Game.displayMessage("\nYou Lost!");
					//Game.displayMessage("\n !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					Game.yourTurnMessage.setVisible(false);
					Game.opponentTurnMessage.setVisible(false);
					Game.lossMessage.setVisible(true);
					sound.play(4);
					Game.gameStarted = false;
					Game.playerTurn = false;
					return (">>"+ String.valueOf(x) + "," + String.valueOf(y));
				}

				sound.play(1);
				Game.playerTurn = true;
				Game.yourTurnMessage.setVisible(true);
				Game.opponentTurnMessage.setVisible(false);
				return ("!!" + String.valueOf(x) + "," + String.valueOf(y));

			}
			// Hit Destroyer
			else if (pGridContents == "D") {
				JLabel hitLabel = new JLabel();
				JLabel missLabel = new JLabel();
				hitLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(Images.HIT_IMG), "Hit"));
				missLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(Images.MISS_IMG), "Miss"));

				destroyerHoles--;

				// displays on playerGrid enemy shots
				hitLabel.setBounds(x2, y2, 28, 28);
				Game.playerPanel.add(hitLabel, -1);
				Game.playerPanel.repaint();
				Game.playerPanel.revalidate();
				
				if(destroyerHoles == 0){
					numShipsLeft--;
				}
				// checks for sunk ship and endGame condition
				if (destroyerHoles == 0 && numShipsLeft != 0) {
					sound.play(6);
					Game.playerTurn = true;
					Game.yourTurnMessage.setVisible(true);
					Game.opponentTurnMessage.setVisible(false);
					return ("^^" + String.valueOf(x) + "4" + String.valueOf(y));
				}
				if (numShipsLeft == 0) {
			
					//Game.displayMessage("\n !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					Game.displayMessage("\nYou Lost!");
					//Game.displayMessage("\n !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					Game.yourTurnMessage.setVisible(false);
					Game.opponentTurnMessage.setVisible(false);
					Game.lossMessage.setVisible(true);
					sound.play(4);
					Game.gameStarted = false;
					Game.playerTurn = false;
					return (">>"+ String.valueOf(x) + "," + String.valueOf(y));
				}

			
				sound.play(1);
				Game.playerTurn = true;
				Game.yourTurnMessage.setVisible(true);
				Game.opponentTurnMessage.setVisible(false);
				return ("!!" + String.valueOf(x) + "," + String.valueOf(y));

			}
			// enemy missed
			else if (pGridContents == "~") {
				JLabel hitLabel = new JLabel();
				JLabel missLabel = new JLabel();
				hitLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(Images.HIT_IMG),"Hit"));
				missLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(Images.MISS_IMG),"Miss"));
				// displays on playerGrid enemy shots
				Game.playerBoard.setGridContents(x, y, "M");
				missLabel.setBounds(x2, y2, 28, 28);
				Game.playerPanel.add(missLabel, 0);
				Game.playerPanel.repaint();
				Game.playerPanel.revalidate();
				// returns message to enemy that they missed
			
				sound.play(0);
				Game.playerTurn = true;
				Game.yourTurnMessage.setVisible(true);
				Game.opponentTurnMessage.setVisible(false);
				return ("??" + String.valueOf(x) + "," + String.valueOf(y));

			}

		}

		/*
		 * Receiving a response after an attack message During the players turn,
		 * valid messages received from the enemy are as follows: "!!x,y" //hit
		 * "??x,y //miss "^^z" //sunk ship z corresponds to ship 0,1,2,3,4 where
		 * 0 = battleship, 1 = carrier, 2 = cruiser, 3 = sub, 4 = destroyer
		 * ">>>" //the player has sunk all 5 enemy ships and therefore won the
		 * game
		 */

		// Hit
		if (l == '!' && m == '!') {
			JLabel hitLabel = new JLabel();
			JLabel missLabel = new JLabel();
			hitLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(Images.HIT_IMG),"Hit"));
			missLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(Images.MISS_IMG),"Miss"));

			Game.opponentBoard.setGridContents(x, y, "H");
			hitLabel.setBounds(x2, y2, 28, 28);
			Game.opponentPanel.add(hitLabel, 0);
			Game.opponentPanel.repaint();
			Game.opponentPanel.revalidate();
		
			sound.play(1);
			Game.playerTurn = false;
			Game.yourTurnMessage.setVisible(false);
			Game.opponentTurnMessage.setVisible(true);
			return "*Comment*";
		}
		// miss
		else if (l == '?' && m == '?') {
			JLabel hitLabel = new JLabel();
			JLabel missLabel = new JLabel();
			hitLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(Images.HIT_IMG),"Hit"));
			missLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(Images.MISS_IMG),"Miss"));

			Game.opponentBoard.setGridContents(x, y, "M");

			missLabel.setBounds(x2, y2, 28, 28);
			Game.opponentPanel.add(missLabel, 0);
			Game.opponentPanel.repaint();
			Game.opponentPanel.revalidate();
		
			sound.play(0);
			Game.playerTurn = false;
			Game.yourTurnMessage.setVisible(false);
			Game.opponentTurnMessage.setVisible(true);
			return "*Comment*";
		}
		// sunk
		else if (l == '^' && m == '^') {
			JLabel hitLabel = new JLabel();
			JLabel missLabel = new JLabel();
			hitLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(Images.HIT_IMG),"Hit"));
			missLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(Images.MISS_IMG),"Miss"));
			
			
			System.out.println("S (the ship sunk ) = "+ s);
			//Game.displayMessage("/nS (the ship sunk ) = "+ s);
			if(s == '0'){
				sound.play(8);
				Game.oppBattleshipLabel.setVisible(false);
			}
			else if(s == '1'){
				sound.play(7);
				Game.oppCarrierLabel.setVisible(false);
			}
			else if(s == '2'){
				sound.play(8);
				Game.oppCruiserLabel.setVisible(false);
			}
			else if(s == '3'){
				sound.play(8);
				Game.oppSubmarineLabel.setVisible(false);
			}
			else if(s == '4'){
				sound.play(8);
				Game.oppDestroyerLabel.setVisible(false);
			}
			Game.oppRemainingPanel.repaint();
			Game.oppRemainingPanel.revalidate();
			
			Game.opponentBoard.setGridContents(x, y, "H");
			hitLabel.setBounds(x2, y2, 28, 28);
			Game.opponentPanel.add(hitLabel, 0);
			Game.opponentPanel.repaint();
			Game.opponentPanel.revalidate();
		
			Game.playerTurn = false;
			Game.yourTurnMessage.setVisible(false);
			Game.opponentTurnMessage.setVisible(true);
			return "*Comment*";
			
		//All enemy ships sunk	
		} else if (l == '>' && m == '>' ) {
			JLabel hitLabel = new JLabel();
			JLabel missLabel = new JLabel();
			hitLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(Images.HIT_IMG),"Hit"));
			missLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(Images.MISS_IMG),"Miss"));
			// add code here to deduct the right ship from the enemy
			// remaining panel
			Game.opponentBoard.setGridContents(x, y, "H");
			hitLabel.setBounds(x2, y2, 28, 28);
			Game.opponentPanel.add(hitLabel, 0);
			Game.opponentPanel.repaint();
			Game.opponentPanel.revalidate();
			
			//ensures all opponent panel ships are gone 
			Game.oppBattleshipLabel.setVisible(false);
			Game.oppCarrierLabel.setVisible(false);
			Game.oppCruiserLabel.setVisible(false);
			Game.oppSubmarineLabel.setVisible(false);
			Game.oppDestroyerLabel.setVisible(false);
			Game.displayMessage("\nYOU Win!");	
			Game.yourTurnMessage.setVisible(false);
			Game.opponentTurnMessage.setVisible(false);
			Game.winMessage.setVisible(true);
			sound.play(3);
			Game.playerTurn = false;
			Game.gameStarted = false;
			return "\n\nGame Over";
		}

		// }
		/*
		 * If the game has not started then the only Game Event Message (G.E.M.)
		 * is whether the enemy is ready or not. "###" //Received ready message
		 * from enemy
		 */
		if (l == '#' && m == '#' && n == '#') {
		
			Game.opponentReady = true;
			if (Game.playerReady == true) {
				Game.gameStarted = true;
			}
			Game.displayMessage("\nOpponent is Ready!");
			
			return " Lets play!";
		}

		return "\nCould not understand message";

	}

	// returns a string with the attack coordinates of players click on enemy
	// board
	public static String attack(Point pt) {
		int x = (int) pt.getX();
		int y = (int) pt.getY();

		// converts drop point to grid square
		int x2 = (x - 21) / 30;
		int y2 = (y - 32) / 30;

		// alters drop point to set ship in grid correctly
		x = (x2 * 30) + 21;
		y = (y2 * 30) + 32;

		if (Game.opponentBoard.getGridContents(x2, y2) != "~") {
			Game.displayMessage("\nYou already picked that square, try again");
			return "*****";
		} else if (Game.opponentBoard.getGridContents(x2, y2) == "~") {
			Game.opponentBoard.setGridContents(x2, y2, "T");
			return "@@" + String.valueOf(x2) + "," + String.valueOf(y2);
		} else {
			return "\nSomething went wrong in the attack() method of Gameplay.java";

		}
	}

}
//