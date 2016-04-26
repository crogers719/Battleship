/*
 * COSC 330: Battleship project
 * Colleen Rogers and Jon Gordy
 * March 8, 2016
 * 
 * Game.java
 * Description: This class contains the main method and GUI components
 */

import java.util.*;
import java.net.*;
import java.io.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.TextField;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;





@SuppressWarnings("serial")
public class Game extends JFrame {
	
	private Battleship bsGame = new Battleship();
	
	protected boolean allShipsPlaced = false;
	public static boolean playerReady;
	public static boolean opponentReady;
	public static boolean gameStarted = false;
	public static boolean isServer = false;
	public static boolean playerTurn = false;
	
	/*GUI Comoponent Variables*/
	private JToggleButton shipOrientButtonH;
	private JToggleButton shipOrientButtonV;
	private JButton connectButton;
	private JButton hostButton;
	
	private TextField ipField;
	
	private JPanel controlPanel;
	public static JPanel opponentPanel;
	private JLabel messageLabel;
	private JPanel messagePanel;
	private static JTextArea messageTextArea;
	
	private JLabel playerShips;
	private JLabel oppShips;
	
	private JLabel readyButtonInstLabel;
	private JLabel hostGameLabel;
	private JLabel hostIPAdressLabel9;
	private JPanel informationPanel;
	public static JPanel oppRemainingPanel;
	
	
	protected static JTextArea introMsg;	
	protected static JTextArea yourTurnMessage;
	protected static JTextArea opponentTurnMessage;
	protected static JTextArea winMessage;
	protected static JTextArea lossMessage;
	
	protected static JLabel battleshipImgLabelH;
	protected static JLabel battleshipImgLabelV;
	
	protected static JLabel cruiserImgLabelH;
	protected static JLabel cruiserImgLabelV;
	
	protected static JLabel submarineImgLabelH;
	protected static JLabel submarineImgLabelV;
	
	protected static JLabel carrierImgLabelH;
	protected static JLabel carrierImgLabelV;
	
	protected static JLabel destroyerImgLabelH;
	protected static JLabel destroyerImgLabelV;
	
	protected static JLabel oppBattleshipLabel;
	protected static JLabel oppCarrierLabel;
	protected static JLabel oppCruiserLabel;
	protected static JLabel oppDestroyerLabel;
	protected static JLabel oppSubmarineLabel;
	

	private JScrollPane jScroll;
	
	static JPanel playerPanel;
	public static JButton readyButton;
	public static JButton randomButton;
	private JPanel shipInventory;
	private JTextField userChatEnter;
	protected static Opponent opponentBoard; 
	protected static Board playerBoard; 

	Image image;
	final Sounds sound = new Sounds();
	
	
	/*Networking variables*/
	private ObjectOutputStream outputToClient; 
	private ObjectInputStream inputFromClient; 
	private ServerSocket serverSocket; 
	private Socket connectionToClient; 
	private int numOfConnections = 1; 
	private String ipAddress;
	private ObjectOutputStream outputToServer; 
	private ObjectInputStream inputFromServer; 
	private String messageFromServer = ""; 
	private String chatServer; 
	private Socket client; 
	
	
	//Ships
	private Ship battleship = new Ship("Battleship", Images.BATTLESHIP_H, 'H');
	private Ship battleshipV = new Ship("BattleshipV", Images.BATTLESHIP_V, 'V');
	
	private Ship carrier = new Ship("Carrier", Images.CARRIER_H,'H');
	private Ship carrierV = new Ship("CarrierV", Images.CARRIER_V, 'V');
	
	private Ship cruiser = new Ship("Cruiser", Images.CRUISER_H, 'H');
	private Ship cruiserV = new Ship("CruiserV", Images.CRUISER_V, 'V');
	
	private Ship submarine = new Ship("Submarine", Images.SUBMARINE_H, 'H');
	private Ship submarineV = new Ship("SubmarineV", Images.SUBMARINE_V, 'V');
	
	private Ship destroyer = new Ship("Destroyer", Images.DESTROYER_H, 'H');
	private Ship destroyerV = new Ship("DestroyerV", Images.DESTROYER_V, 'V');

	/*
	 *Game Constructor
	 * Creates new form game
	 */
	public Game() {
		initComponents();
	}

	

	@SuppressWarnings("serial") //Need to add to run
	private void initComponents() {
		
		
		//JLabels
		messageLabel = new JLabel();
		readyButtonInstLabel = new JLabel();
		hostGameLabel = new JLabel();
		hostIPAdressLabel9 = new JLabel();
		ipField = new java.awt.TextField();
		playerShips=new JLabel();
		oppShips=new JLabel();
		
		//Buttons
		shipOrientButtonH = new JRadioButton("Horizontal");
		shipOrientButtonV = new JRadioButton("Vertical");
		connectButton = new JButton();
		readyButton = new JButton();
		randomButton = new JButton();
		hostButton = new JButton();	
		
		//JTextFields
		userChatEnter = new JTextField();
		
		//JText Areas
		jScroll = new JScrollPane();
		messageTextArea = new JTextArea();
		yourTurnMessage = new JTextArea("     Your Turn");
		opponentTurnMessage = new JTextArea("   Opponents Turn");
		winMessage = new JTextArea("      You Win!!!!");
		lossMessage = new JTextArea("      You Lost!!!!");
		introMsg = new JTextArea("To start a game: \n\t(1) Connect with opponent \n\t(2) Initialize your board \n\t(3) Press begin.");
		
	
		//enemy panel ships
		oppBattleshipLabel = new JLabel();
		oppCarrierLabel = new JLabel();
		oppCruiserLabel = new JLabel();
		oppSubmarineLabel = new JLabel();
		oppDestroyerLabel = new JLabel();
				
		//Panels
		messagePanel = new JPanel();
		controlPanel = new JPanel();
		informationPanel = new JPanel();
		shipInventory = new JPanel();
		oppRemainingPanel = new JPanel();
		opponentPanel = new JPanel();
		
		//Grids
		playerBoard = new Board();
		opponentBoard = new Opponent();
		
	
		try {
			image = ImageIO.read(this.getClass().getResource("img/grid.png"));
		} catch (IOException e) {
			System.out.println("Error loading image");

		}

		//initializing basics
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Battleship!");
		setIconImage(image);
		setResizable(false);

		// Creates panel with gameBoard grid
		playerPanel = new JPanel() {
			
			@Override
			public void paintComponent(Graphics d) {
				super.paintComponent(d);
				setBackground(Color.black);
				d.drawImage(image, 20, 30, 300, 300, null);
			}
		};

		opponentPanel = new JPanel() {
			@Override
			public void paintComponent(Graphics d) {
				super.paintComponent(d);
				setBackground(Color.black);
				d.drawImage(image, 20, 30, 300, 300, null);
			}
		};
		
		new MyDropTargetListener(playerPanel);

		// adds mouse-listener opponent
		//waits for attack
		opponentPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (gameStarted) {

					String message;
					if (playerTurn == true) {
						message = Battleship.attack(e.getPoint());
						// Checks that square hasn't been chosen already
						if (!(message.equals("*****"))) {

							if (isServer && playerTurn) {
								playerTurn = false;
								sendData(message);
							}
							
							else if (isServer == false && playerTurn) {
								playerTurn = false;
								sendDataClient(message);
							} 
							
							else if (playerTurn == false) {
								displayMessage("\nIts not your turn!");
							}
						}
					}
				}
			}
		});
		
		/** GUI components**/
		
	messagePanel.setBackground(Color.black);
		messagePanel.setBorder(BorderFactory.createTitledBorder(null, "Communication", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, null, Color.white));

		messageLabel.setForeground(Color.white);
		messageLabel.setText("Message: ");
		

		userChatEnter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				userChatEnterActionPerformed(evt);
			}
		});
		jScroll.setBackground(Color.black);
		jScroll.setBorder(BorderFactory.createTitledBorder(null, "Chat", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, null, Color.white));

		
		messageTextArea.setColumns(20);
		messageTextArea.setLineWrap(true);
		messageTextArea.setRows(5);
		jScroll.setViewportView(messageTextArea);

		javax.swing.GroupLayout messagePanelLayout = new GroupLayout(messagePanel);
		messagePanel.setLayout(messagePanelLayout);
		messagePanelLayout.setHorizontalGroup(messagePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				messagePanelLayout.createSequentialGroup().addGap(16, 16, 16).addGroup(
						messagePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jScroll).addGroup(
								messagePanelLayout.createSequentialGroup().addComponent(messageLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(userChatEnter)))));
		messagePanelLayout.setVerticalGroup(messagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				messagePanelLayout.createSequentialGroup().addContainerGap().addComponent(jScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
								messagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(userChatEnter).addComponent(messageLabel,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))));

		
		controlPanel.setBackground(Color.black);
		controlPanel.setBorder(BorderFactory.createTitledBorder(null, "",TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, null, Color.white));
		
		readyButton.setEnabled(false);
		readyButton.setText("Begin Game");
		readyButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				readyButtonActionPerformed(evt);

			}
		});
		shipOrientButtonH.setBackground(Color.white);
		shipOrientButtonV.setBackground(Color.white);
		shipOrientButtonH.setActionCommand("Horizontal");
		shipOrientButtonH.setSelected(true);
		shipOrientButtonV.setActionCommand("Vertical");
		
		
		 // Group the radio buttons (can be selected or unselected).
	    ButtonGroup group = new ButtonGroup();
	    
	    group.add(shipOrientButtonH);
	    group.add(shipOrientButtonV);
		
	    RadioListener myListener = new RadioListener();
	    shipOrientButtonH.addActionListener(myListener);
	    shipOrientButtonH.addChangeListener(myListener);
	    shipOrientButtonH.addItemListener(myListener);
	    shipOrientButtonV.addActionListener(myListener);
	    shipOrientButtonV.addChangeListener(myListener);
	    shipOrientButtonV.addItemListener(myListener);
		
		
		randomButton.setText("Randomize Board");
		randomButton.setBackground(Color.blue);
		randomButton.setForeground(Color.white);
		randomButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				randomButtonActionPerformed(evt);

			}

		});
		randomButton.setEnabled(true);
		readyButtonInstLabel.setForeground(Color.white);
		readyButtonInstLabel.setText("Position your ships and push when ready");

		hostGameLabel.setForeground(Color.white);
		hostGameLabel.setText("Host Game:");
		hostIPAdressLabel9.setForeground(Color.white);
		hostIPAdressLabel9.setText("Host IP Address");

		ipField.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ipAddressFieldActionPerformed(evt);
			}
		});

		connectButton.setText("Connect");
		connectButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// if user enters ipaddress and clicks connect, the value in ipAddress
				// will be updated before a call to the connect function
				playerTurn = false;
				ipAddressFieldActionPerformed(evt);
				connectButtonActionPerformed(evt);
			}
		});

		hostButton.setText("Host");
		hostButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				isServer = true;
				playerTurn = true;
				hostButtonActionPerformed(evt);
			}
		});
		introMsg.setColumns(2);
		introMsg.setLineWrap(true);
		introMsg.setRows(5);
		introMsg.setFont(new Font("SansSerif", Font.BOLD, 15));
		introMsg.setForeground(Color.white);
		introMsg.setBackground(Color.black);
		
		opponentTurnMessage.setFont(new Font("SansSerif", Font.BOLD, 28));
		opponentTurnMessage.setForeground(Color.red);
		opponentTurnMessage.setBackground(Color.black);
		
		yourTurnMessage.setFont(new Font("SansSerif", Font.BOLD, 28));
		yourTurnMessage.setForeground(Color.green);
		yourTurnMessage.setBackground(Color.black);		
		
		
		
		winMessage.setFont(new Font("Serif", Font.BOLD, 36));
		winMessage.setForeground (Color.green);
		winMessage.setBackground(Color.black);
		
		lossMessage.setFont(new Font("Serif", Font.BOLD, 36));
		winMessage.setForeground (Color.red);
		winMessage.setBackground(Color.black);
		
		informationPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		
		//informationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Game Status"));
		informationPanel.setBackground(Color.black);
		informationPanel.setBorder(BorderFactory.createTitledBorder(null, "Game Status",TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, null, Color.white));
		
		javax.swing.GroupLayout informationPanelLayout = new javax.swing.GroupLayout(informationPanel);
		informationPanel.setLayout(informationPanelLayout);
		informationPanelLayout.setHorizontalGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(informationPanelLayout.createSequentialGroup()
    				.addComponent(introMsg, GroupLayout.PREFERRED_SIZE, 300,GroupLayout.PREFERRED_SIZE)	
    				.addComponent(yourTurnMessage, GroupLayout.PREFERRED_SIZE, 300,GroupLayout.PREFERRED_SIZE)
    				.addComponent(opponentTurnMessage, GroupLayout.PREFERRED_SIZE, 300,GroupLayout.PREFERRED_SIZE)
    				.addComponent(winMessage, GroupLayout.PREFERRED_SIZE, 300,GroupLayout.PREFERRED_SIZE)
    				.addComponent(lossMessage, GroupLayout.PREFERRED_SIZE, 300,GroupLayout.PREFERRED_SIZE)));
		informationPanelLayout.setVerticalGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(introMsg, GroupLayout.PREFERRED_SIZE, 100,GroupLayout.PREFERRED_SIZE)
				.addComponent(yourTurnMessage, GroupLayout.PREFERRED_SIZE, 100,GroupLayout.PREFERRED_SIZE)
				.addComponent(opponentTurnMessage, GroupLayout.PREFERRED_SIZE, 100,GroupLayout.PREFERRED_SIZE)
				.addComponent(winMessage, GroupLayout.PREFERRED_SIZE, 100,GroupLayout.PREFERRED_SIZE)
				.addComponent(lossMessage, GroupLayout.PREFERRED_SIZE, 100,GroupLayout.PREFERRED_SIZE));

		javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
		controlPanel.setLayout(controlPanelLayout);
		controlPanelLayout.setHorizontalGroup(controlPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
				controlPanelLayout.createSequentialGroup()
				.addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(informationPanel, javax.swing.GroupLayout.DEFAULT_SIZE,javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(controlPanelLayout.createSequentialGroup()
				.addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(controlPanelLayout.createSequentialGroup()
				.addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
    				.addComponent(hostIPAdressLabel9,javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    				.addComponent(hostGameLabel,javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    				.addGap(18, 18, 18)
    				.addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    				.addComponent(ipField,javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,controlPanelLayout.createSequentialGroup()
    				.addComponent(hostButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85,javax.swing.GroupLayout.PREFERRED_SIZE)
    				.addGap(15, 15, 15))).addGap(18, 18, 18).addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
    				.addComponent(connectButton,javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
    				.addGroup(controlPanelLayout.createSequentialGroup()
    						.addGap(142, 142, 142)
    				.addComponent(readyButton))	
				.addGroup(controlPanelLayout.createSequentialGroup()
					.addGap(0, 5, 75)
					.addComponent(readyButtonInstLabel)))
					.addGap(10, 35, 70)))
					.addContainerGap()));
		
		controlPanelLayout.setVerticalGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				controlPanelLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
				.addComponent(hostGameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23,javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
				.addComponent(hostButton)))
				.addGap(21, 21, 21)
				.addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
				.addComponent(ipField, javax.swing.GroupLayout.DEFAULT_SIZE,javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(hostIPAdressLabel9, javax.swing.GroupLayout.DEFAULT_SIZE,javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addComponent(connectButton))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(informationPanel, javax.swing.GroupLayout.PREFERRED_SIZE,150, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED,javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(readyButtonInstLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(readyButton)));

		
		opponentPanel.setBorder(BorderFactory.createTitledBorder(null, "Opponent's Board",TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, null, Color.white));
		opponentPanel.setLayout(null);

	
		playerPanel.setBorder(BorderFactory.createTitledBorder(null, "Your Board",TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, null, Color.white));
		

		// allows ships to be dropped 
		playerPanel.setLayout(null);

		// Ship Panel Layout
		shipInventory.setBorder(BorderFactory.createTitledBorder(null, "Your Ships",TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, null, Color.black));
		shipInventory.setBackground(Color.white);
		
		//player ships horizontal
		cruiserImgLabelH = new JLabel(cruiser.getIcon());
		submarineImgLabelH = new JLabel(submarine.getIcon());
		carrierImgLabelH = new JLabel(carrier.getIcon());
		destroyerImgLabelH = new JLabel(destroyer.getIcon());
		battleshipImgLabelH = new JLabel(battleship.getIcon());
	
		//player ships vertical
		cruiserImgLabelV = new JLabel(cruiserV.getIcon());
		submarineImgLabelV = new JLabel(submarineV.getIcon());
		carrierImgLabelV= new JLabel(carrierV.getIcon());
		destroyerImgLabelV = new JLabel(destroyerV.getIcon());
		battleshipImgLabelV = new JLabel(battleshipV.getIcon());
		
		//opponent
		oppCruiserLabel = new JLabel(cruiser.getIcon());
		oppSubmarineLabel = new JLabel(submarine.getIcon());
		oppCarrierLabel = new JLabel(carrier.getIcon());
		oppDestroyerLabel = new JLabel(destroyer.getIcon());
		oppBattleshipLabel = new JLabel(battleship.getIcon());
				
		//Setting ship dimensions
		cruiserImgLabelH.setPreferredSize(new java.awt.Dimension(120, 60));
		cruiserImgLabelV.setPreferredSize(new java.awt.Dimension(60, 120));
		
		submarineImgLabelH.setPreferredSize(new java.awt.Dimension(90, 60));
		submarineImgLabelV.setPreferredSize(new java.awt.Dimension(60, 90));
		
		carrierImgLabelH.setPreferredSize(new java.awt.Dimension(150, 60));
		carrierImgLabelV.setPreferredSize(new java.awt.Dimension(60, 150));
		
		destroyerImgLabelH.setPreferredSize(new java.awt.Dimension(60, 60));
		destroyerImgLabelV.setPreferredSize(new java.awt.Dimension(60, 60));
		
		battleshipImgLabelH.setPreferredSize(new java.awt.Dimension(120, 60));
		battleshipImgLabelV.setPreferredSize(new java.awt.Dimension(60, 120));
		
		oppBattleshipLabel.setPreferredSize(new java.awt.Dimension(120, 60));
		oppCarrierLabel.setPreferredSize(new java.awt.Dimension(150, 60));
		oppCruiserLabel.setPreferredSize(new java.awt.Dimension(120, 60));
		oppSubmarineLabel.setPreferredSize(new java.awt.Dimension(90, 60));
		oppDestroyerLabel.setPreferredSize(new java.awt.Dimension(60, 60));
		

		javax.swing.GroupLayout shipInventoryLayout = new javax.swing.GroupLayout(shipInventory);
		shipInventory.setLayout(shipInventoryLayout);
		
		shipInventoryLayout.setHorizontalGroup(shipInventoryLayout.createSequentialGroup()	
				.addGroup(shipInventoryLayout.createSequentialGroup()
				
					.addGroup(shipInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
							.addComponent(carrierImgLabelH, GroupLayout.PREFERRED_SIZE, 148,GroupLayout.PREFERRED_SIZE)
							.addComponent(battleshipImgLabelH,GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)							
							.addComponent(cruiserImgLabelH, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addComponent(submarineImgLabelH, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
							.addComponent(destroyerImgLabelH, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)	
							
							
					.addGroup(shipInventoryLayout.createSequentialGroup()
							.addComponent(carrierImgLabelV, GroupLayout.PREFERRED_SIZE, 28,GroupLayout.PREFERRED_SIZE)
							.addComponent(battleshipImgLabelV,GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addComponent(cruiserImgLabelV, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)				
							.addComponent(submarineImgLabelV, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addComponent(destroyerImgLabelV, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))	
							//next parallel entry
                    		.addComponent(shipOrientButtonH, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    		.addComponent(shipOrientButtonV, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)		
                    		.addComponent(randomButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))));

		shipInventoryLayout.setVerticalGroup(shipInventoryLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				
				.addGroup(shipInventoryLayout.createSequentialGroup()												
					.addComponent(carrierImgLabelH, GroupLayout.PREFERRED_SIZE,28, GroupLayout.PREFERRED_SIZE)					
					.addComponent(battleshipImgLabelH, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)					
					.addComponent(cruiserImgLabelH,GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)										
					.addComponent(submarineImgLabelH, GroupLayout.PREFERRED_SIZE, 28,GroupLayout.PREFERRED_SIZE)					
					.addComponent(destroyerImgLabelH, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
				.addGroup(shipInventoryLayout.createParallelGroup()
              		.addComponent(carrierImgLabelV, GroupLayout.PREFERRED_SIZE,148, GroupLayout.PREFERRED_SIZE)
                	.addComponent(battleshipImgLabelV, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)						
    				.addComponent(cruiserImgLabelV,javax.swing.GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
    				.addComponent(submarineImgLabelV, javax.swing.GroupLayout.PREFERRED_SIZE, 88,javax.swing.GroupLayout.PREFERRED_SIZE)                				
    				.addComponent(destroyerImgLabelV, javax.swing.GroupLayout.PREFERRED_SIZE, 58,GroupLayout.PREFERRED_SIZE))
    				
            		.addComponent(shipOrientButtonH, GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            		.addComponent(shipOrientButtonV, GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            		.addComponent(randomButton, GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)	)); 
		
		// Adds drag listener
		MyDragGestureListener dragListener = new MyDragGestureListener();
		
		DragSource srcBattleH = new DragSource();
		DragSource srcBattleV = new DragSource();
		
		DragSource srcCarrierH = new DragSource();
		DragSource srcCarrierV = new DragSource();
		
		DragSource srcCruiserH = new DragSource();
		DragSource srcCruiserV = new DragSource();
		
		DragSource srcDestroyerH = new DragSource();
		DragSource srcDestoyerV = new DragSource();
		
		DragSource srcSubH = new DragSource();
		DragSource srcSubV = new DragSource();
		
		 
		srcBattleH.createDefaultDragGestureRecognizer(battleshipImgLabelH, DnDConstants.ACTION_COPY, dragListener);
		srcBattleV.createDefaultDragGestureRecognizer(battleshipImgLabelV, DnDConstants.ACTION_COPY, dragListener);
		
		srcCarrierH.createDefaultDragGestureRecognizer(carrierImgLabelH, DnDConstants.ACTION_COPY, dragListener);
		srcCarrierV.createDefaultDragGestureRecognizer(carrierImgLabelV, DnDConstants.ACTION_COPY, dragListener);
		
		srcCruiserH.createDefaultDragGestureRecognizer(cruiserImgLabelH, DnDConstants.ACTION_COPY, dragListener);
		srcCruiserV.createDefaultDragGestureRecognizer(cruiserImgLabelV, DnDConstants.ACTION_COPY, dragListener);
		
		srcDestroyerH.createDefaultDragGestureRecognizer(destroyerImgLabelH, DnDConstants.ACTION_COPY, dragListener);
		srcDestoyerV.createDefaultDragGestureRecognizer(destroyerImgLabelV, DnDConstants.ACTION_COPY, dragListener);
		
		srcSubH.createDefaultDragGestureRecognizer(submarineImgLabelH, DnDConstants.ACTION_COPY, dragListener);
		srcSubV.createDefaultDragGestureRecognizer(submarineImgLabelV, DnDConstants.ACTION_COPY, dragListener);


		// Ship images; When ship sunk-becomes invisible
		oppRemainingPanel.setBorder(BorderFactory.createTitledBorder(null, "Opponent's Ships",TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, null, Color.black));
		oppRemainingPanel.setBackground(Color.white);
		
		

		javax.swing.GroupLayout enemyLeftPanelLayout = new javax.swing.GroupLayout(oppRemainingPanel);
		oppRemainingPanel.setLayout(enemyLeftPanelLayout);
		
		enemyLeftPanelLayout.setHorizontalGroup(enemyLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(enemyLeftPanelLayout.createSequentialGroup().addContainerGap()
				.addGroup(enemyLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(oppCarrierLabel, GroupLayout.PREFERRED_SIZE, 148,GroupLayout.PREFERRED_SIZE)
				.addComponent(oppBattleshipLabel,	GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
				.addComponent(oppCruiserLabel, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
				.addComponent(oppSubmarineLabel, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
				.addComponent(oppDestroyerLabel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))		
				.addContainerGap(18, Short.MAX_VALUE)));
		
		enemyLeftPanelLayout.setVerticalGroup(enemyLeftPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(enemyLeftPanelLayout.createSequentialGroup()
				.addGap(29, 29, 29)
				.addComponent(oppCarrierLabel, javax.swing.GroupLayout.PREFERRED_SIZE,28, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(oppBattleshipLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(oppCruiserLabel,javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(oppSubmarineLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28,javax.swing.GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(oppDestroyerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)		
				.addGap(124, 124, 124)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
				.addComponent(controlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
				.addComponent(messagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
				.addComponent(opponentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
				.addComponent(playerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE))
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(shipInventory, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
				.addComponent(oppRemainingPanel,GroupLayout.PREFERRED_SIZE, 200,GroupLayout.PREFERRED_SIZE))));
		
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
				.addGroup(javax.swing.GroupLayout.Alignment.LEADING,layout.createSequentialGroup()
				.addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addComponent(messagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
				.addGroup(javax.swing.GroupLayout.Alignment.LEADING,layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
				.addComponent(oppRemainingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 350,Short.MAX_VALUE)
				.addComponent(opponentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
				.addComponent(shipInventory, javax.swing.GroupLayout.DEFAULT_SIZE, 350,	Short.MAX_VALUE)
				.addComponent(playerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))))));
		Game.battleshipImgLabelV.setVisible(false);
        Game.carrierImgLabelV.setVisible(false);
        Game.cruiserImgLabelV.setVisible(false);
        Game.submarineImgLabelV.setVisible(false);
        Game.destroyerImgLabelV.setVisible(false);
        yourTurnMessage.setVisible(false);
        opponentTurnMessage.setVisible(false);
        winMessage.setVisible(false);
        lossMessage.setVisible(false);
		pack();
	}
/**Event Driven etc...*/	
	//Displays Horizontal or Vertical ships for player placement
	class RadioListener implements ActionListener, ChangeListener, ItemListener {  
        public void actionPerformed(ActionEvent e) {  
            
            if (e.getActionCommand() == "Horizontal") {
               //Displays any un-placed horizontal ships
                if(GetSquareDropped.droppedBattle == false){
                	Game.battleshipImgLabelH.setVisible(true);	
                }
                if(GetSquareDropped.droppedCarrier == false){
                	Game.carrierImgLabelH.setVisible(true); 	
                }
                if(GetSquareDropped.droppedCruiser == false){
                	Game.cruiserImgLabelH.setVisible(true);	
                }
                if(GetSquareDropped.droppedSub == false){
                	Game.submarineImgLabelH.setVisible(true);	
                }
                if(GetSquareDropped.droppedDestroyer == false){
                	Game.destroyerImgLabelH.setVisible(true);	
                }
                //makes vertical ships invisible
                Game.battleshipImgLabelV.setVisible(false);
                Game.carrierImgLabelV.setVisible(false);
                Game.cruiserImgLabelV.setVisible(false);
                Game.submarineImgLabelV.setVisible(false);
                Game.destroyerImgLabelV.setVisible(false);
                
            }
            else { 
            	//Display all un-placed vertical ships
                if(GetSquareDropped.droppedBattle == false){
                	Game.battleshipImgLabelV.setVisible(true);
                }
                if(GetSquareDropped.droppedCarrier == false){
                	Game.carrierImgLabelV.setVisible(true);
                }
                if(GetSquareDropped.droppedCruiser == false){
                	Game.cruiserImgLabelV.setVisible(true);
                }
                if(GetSquareDropped.droppedSub == false){
                	Game.submarineImgLabelV.setVisible(true);
                }
                if(GetSquareDropped.droppedDestroyer == false){
                	Game.destroyerImgLabelV.setVisible(true);
                }
                //make horizontal ships invisible
                Game.battleshipImgLabelH.setVisible(false);
                Game.carrierImgLabelH.setVisible(false);
                Game.cruiserImgLabelH.setVisible(false);
                Game.submarineImgLabelH.setVisible(false);
                Game.destroyerImgLabelH.setVisible(false);
            }
        }
        
		@Override
		public void itemStateChanged(ItemEvent e) {
			//super class requires this
		}
	
		@Override
		public void stateChanged(ChangeEvent e) {
			//super class requires this
		}
    
	}

	private void readyButtonActionPerformed(ActionEvent evt) {
		
		// check that all ships are placed
		if (GetSquareDropped.checkAllShips()) {
			playerReady = true;
			introMsg.setVisible(false);
			sound.play(2);
			readyButton.setEnabled(false);
			randomButton.setVisible(false);
			shipOrientButtonH.setVisible(false);
			shipOrientButtonV.setVisible(false);
			if (opponentReady == true) {
				gameStarted = true;
			}
			if (isServer) {
				yourTurnMessage.setVisible(true);
				sendData("#####");
			} else if (isServer == false) {
				sendDataClient("#####");
				opponentTurnMessage.setVisible(true);
			}
		}

	}

	private void randomButtonActionPerformed(ActionEvent evt) {

		Boolean placedBattleship = false, placedCarrier = false, placedCruiser = false, placedSub = false, placedDestroyer = false;

		randomButton.setEnabled(false);

		int randomX = randNum(21, 319);
		int randomY = randNum(30, 300);
		int randOrient = randNum(0,1);
		
		// set Battleship
		while (placedBattleship == false) {
			randOrient = randNum(0,1);	
		
	
			
			if(randOrient == 0){
				ShipInfo.setOrientation('H');
				JLabel randBSLabel = new JLabel();
				randBSLabel.setIcon(battleship.getIcon());
			
				randomX = randNum(21, 319);
				randomY = randNum(30, 300);	
				Point randPoint = new Point(randomX, randomY);
				GetSquareDropped dropSq = new GetSquareDropped(randPoint, "battleShip");
			
				if (GetSquareDropped.validatePlacement) {
					randBSLabel.setBounds(dropSq.getX(), dropSq.getY(), 118, 28);
					playerPanel.add(randBSLabel);
					playerPanel.repaint();
					playerPanel.revalidate();
					placedBattleship = true;
				}
			}
			
			else if (randOrient == 1){
				ShipInfo.setOrientation('V');
				JLabel randBSLabel = new JLabel();
				randBSLabel.setIcon(battleshipV.getIcon());
				randomX = randNum(21, 319);
				randomY = randNum(30, 300);
				Point randPoint = new Point(randomX, randomY);
				GetSquareDropped dropSq = new GetSquareDropped(randPoint, "battleShipV");
			
				if (GetSquareDropped.validatePlacement) {
					randBSLabel.setBounds(dropSq.getX(), dropSq.getY(), 28, 118);
					playerPanel.add(randBSLabel);
					playerPanel.repaint();
					playerPanel.revalidate();
					placedBattleship = true;
				}
			}
		}
		
		// Set Carrier
		while (placedCarrier == false) {
			randOrient = randNum(0,1);	
			
			if(randOrient == 0){
				ShipInfo.setOrientation('H');
				JLabel randBSLabel = new JLabel();
    			randBSLabel.setIcon(carrier.getIcon());
    			randomX = randNum(21, 319);
    			randomY = randNum(30, 300);	
    			Point randPoint = new Point(randomX, randomY);
    			GetSquareDropped dropSq = new GetSquareDropped(randPoint, "carrier");
			
    			if (GetSquareDropped.validatePlacement) {
    				randBSLabel.setBounds(dropSq.getX(), dropSq.getY(), 148, 28);
    				playerPanel.add(randBSLabel);
    				playerPanel.repaint();
    				playerPanel.revalidate();
    				placedCarrier = true;
    			}
			}
			else if (randOrient == 1){
				ShipInfo.setOrientation('V');
				JLabel randBSLabel = new JLabel();
				randBSLabel.setIcon(carrierV.getIcon());
				randomX = randNum(21, 319);
				randomY = randNum(30, 300);
				Point randPoint = new Point(randomX, randomY);
				GetSquareDropped dropSq = new GetSquareDropped(randPoint, "carrierV");
			
				if (GetSquareDropped.validatePlacement) {					
					randBSLabel.setBounds(dropSq.getX(), dropSq.getY(), 28, 148);
					playerPanel.add(randBSLabel);
					playerPanel.repaint();
					playerPanel.revalidate();
					placedCarrier = true;
				}
			}
		}

		// Set Cruiser
		while (placedCruiser == false) {
			
			randOrient = randNum(0,1);	
		
			if(randOrient == 0){
				ShipInfo.setOrientation('H');
				JLabel randBSLabel = new JLabel();
    			randBSLabel.setIcon(cruiser.getIcon());
    			randomX = randNum(21, 319);
    			randomY = randNum(30, 300);	
    			Point randPoint = new Point(randomX, randomY);
    			GetSquareDropped dropSq = new GetSquareDropped(randPoint, "cruiser");
			
    			if (GetSquareDropped.validatePlacement) {
    				randBSLabel.setBounds(dropSq.getX(), dropSq.getY(), 118, 28);
    				playerPanel.add(randBSLabel);
    				playerPanel.repaint();
    				playerPanel.revalidate();
    				placedCruiser = true;
    			}
			}
			else if (randOrient == 1){
				ShipInfo.setOrientation('V');
				JLabel randBSLabel = new JLabel();
				randBSLabel.setIcon(cruiserV.getIcon());
				randomX = randNum(21, 319);
				randomY = randNum(30, 300);
				Point randPoint = new Point(randomX, randomY);
				GetSquareDropped dropSq = new GetSquareDropped(randPoint, "cruiserV");
			
				if (GetSquareDropped.validatePlacement) {
					randBSLabel.setBounds(dropSq.getX(), dropSq.getY(), 28, 118);
					playerPanel.add(randBSLabel);
					playerPanel.repaint();
					playerPanel.revalidate();
					placedCruiser = true;
				}
			}
		}

		// Set Submarine
		while (placedSub == false) {
			randOrient = randNum(0,1);	
			
			if(randOrient == 0){
				ShipInfo.setOrientation('H');
				JLabel randBSLabel = new JLabel();
    			randBSLabel.setIcon(submarine.getIcon());
    			randomX = randNum(21, 319);
    			randomY = randNum(30, 300);	
    			Point randPoint = new Point(randomX, randomY);
    			GetSquareDropped dropSq = new GetSquareDropped(randPoint, "sub");
			
    			if (GetSquareDropped.validatePlacement) {
    				randBSLabel.setBounds(dropSq.getX(), dropSq.getY(), 88, 28);
    				playerPanel.add(randBSLabel);
    				playerPanel.repaint();
    				playerPanel.revalidate();
    				placedSub = true;
			}
			}
			else if (randOrient == 1){
				ShipInfo.setOrientation('V');
				JLabel randBSLabel = new JLabel();
				randBSLabel.setIcon(submarineV.getIcon());
				randomX = randNum(21, 319);
				randomY = randNum(30, 300);
				Point randPoint = new Point(randomX, randomY);
				GetSquareDropped dropSq = new GetSquareDropped(randPoint, "subV");
			
				if (GetSquareDropped.validatePlacement) {
    				randBSLabel.setBounds(dropSq.getX(), dropSq.getY(), 28, 88);
    				playerPanel.add(randBSLabel);
    				playerPanel.repaint();
    				playerPanel.revalidate();
    				placedSub = true;
				}
			}
		}

		// Set Destroyer
		while (placedDestroyer == false) {
			randOrient = randNum(0,1);	
	
			if(randOrient == 0){
				ShipInfo.setOrientation('H');
				JLabel randBSLabel = new JLabel();
    			randBSLabel.setIcon(destroyer.getIcon());
    			randomX = randNum(21, 319);
    			randomY = randNum(30, 300);	
    			Point randPoint = new Point(randomX, randomY);
    			GetSquareDropped dropSq = new GetSquareDropped(randPoint, "destroyer");
			
    			if (GetSquareDropped.validatePlacement) {
    				randBSLabel.setBounds(dropSq.getX(), dropSq.getY(), 58, 28);
    				playerPanel.add(randBSLabel);
    				playerPanel.repaint();
    				playerPanel.revalidate();
    				placedDestroyer = true;
    			}
			}
			else if (randOrient == 1){
				ShipInfo.setOrientation('V');
				JLabel randBSLabel = new JLabel();
				randBSLabel.setIcon(destroyerV.getIcon());
				randomX = randNum(21, 319);
				randomY = randNum(30, 300);
				Point randPoint = new Point(randomX, randomY);
				GetSquareDropped dropSq = new GetSquareDropped(randPoint, "destroyerV");
			
				if (GetSquareDropped.validatePlacement) {
					randBSLabel.setBounds(dropSq.getX(), dropSq.getY(), 28, 58);
					playerPanel.add(randBSLabel);
					playerPanel.repaint();
					playerPanel.revalidate();
					placedDestroyer = true;
				}
			}
		}
		randomButton.setVisible(false);
		shipOrientButtonH.setVisible(false);
		shipOrientButtonV.setVisible(false);
		readyButton.setEnabled(true);

	}

	private void hostButtonActionPerformed(ActionEvent evt) {
		hostButton.setEnabled(false);
		connectButton.setEnabled(false);		
		isServer = true;
		playerTurn = true;
		{
			Runnable serverRunnable = new Runnable() {
				@Override
				public void run() {
					runServer();
				}
			};
			Thread serverThread = new Thread(serverRunnable);
			serverThread.start(); 
		}
	}

	private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {
		connectButton.setEnabled(false);
		hostButton.setEnabled(false);
		{
			Runnable serverRunnable = new Runnable() {
				@Override
				public void run() {
				
					runClient(ipAddress);
				}
			};
			Thread serverThread = new Thread(serverRunnable);
			 // run server application
			serverThread.start();
		}
	}

	
	

	/******** SERVER *************/
	
	private void runServer() {
		// set up server to receive connections; process connections
		try 
		{
			serverSocket = new ServerSocket(12345, 100); 

			while (true) {
				try {
					
					waitForConnection(); 
					 // get input & output streams
					getStreams();
					processConnection(); 
				}  
				catch (EOFException eofException) {
					displayMessage("\nServer terminated connection");
				}  
				finally {
					closeConnection(); 
					numOfConnections++;
				}  
			}  
		} 
		catch (IOException ioException) {
			ioException.printStackTrace();
		} 
	}  

	// wait for connection to arrive, then display connection info
	private void waitForConnection() throws IOException {
		displayMessage("Waiting for connection\n");
		// allow server to accept connection
		connectionToClient = serverSocket.accept(); 
		displayMessage("\nConnection " + numOfConnections + " received from: " + connectionToClient.getInetAddress().getHostName());
	} 

	// get streams to send and receive data
	private void getStreams() throws IOException {
		// set up output stream for objects. flush output buffer to send header information
		outputToClient = new ObjectOutputStream(connectionToClient.getOutputStream());
	
		outputToClient.flush();

		inputFromClient = new ObjectInputStream(connectionToClient.getInputStream());

	}  

	// process connection with client
	private void processConnection() throws IOException {
		String message = "\nConnection successful, *sent from server* ";
		// send connection successful message
		sendData(message); 
		// enable enterField so server user can send messages
		setTextFieldEditable(true);

		// process messages sent from client
		do 
		{
	
			try 
			{
				message = (String) inputFromClient.readObject(); 

			/*
				 * Game Event messages
				 * !!x,y 	Indicates a hit at coordinate x,y
				 * ??x,y	Indicates a miss at coordinate x,y
				 * ^^p		Indicates a sunken ship,p.
				 * 				p=0  : battleship, 
				 * 				p=1	 : carrier
				 * 				p=2	 : cruiser
				 * 				p=3  : submarine
				 * 				p=4  : destroyer
				 * >>>		Indicates all opponent's ships are sunk; player won game  
			 */
				
				if (!message.equals("*Comment*")) {
					if ((message.length() == 5)) {
						char l = message.charAt(0);
						char m = message.charAt(1);

						if ((l == '@' && m == '@') || (l == '!' && m == '!') || (l == '?' && m == '?') || 
								(l == '^' && m == '^') || (l == '>' && m == '>') || (l == '#' && m == '#')) {
							// filters the string message--game event or message?

							message = bsGame.InterpretMsg(message);

							sendData(message);
							if (message.startsWith("^^")){
							//	displayMessage("\nClient client sunk ship ");
							}
						}
						
						else
							displayMessage("\nClient says--> " + message);
					}
					// message is not length 5
					else if (message != null)
						displayMessage("\nClient says--> " + message);
				}
	
			}  
			catch (ClassNotFoundException classNotFoundException) {
				displayMessage("\nUnknown object type received");
			} 

		} while (!message.equals("TERMINATE"));
	}  

	// close streams and socket
	private void closeConnection() {
		displayMessage("\nTerminating connection\n");
		// disable enterField
		setTextFieldEditable(false); 
		connectButton.setEnabled(true);
		hostButton.setEnabled(true);
		try {
			// close output stream
			outputToClient.close(); 
			// close input stream
			inputFromClient.close(); 
			 // close socket
			connectionToClient.close();
		} 
		catch (IOException ioException) {
			ioException.printStackTrace();
		} 

	} 

	// send message to client
	public void sendData(String message) {
		try // send object to client
		{
			outputToClient.writeObject(message);
			// flush output to client
			outputToClient.flush(); 
		} 
		catch (IOException ioException) {
			messageTextArea.append("\nError writing object");
		} 
	}  

	// manipulates displayArea in the event-dispatch thread
	public static void displayMessage(final String messageToDisplay) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			// updates displayArea
			public void run() 
			{
				messageTextArea.append(messageToDisplay);  
			}  
		}  
				);  
	} 

	// manipulates enterField in the event-dispatch thread
	private void setTextFieldEditable(final boolean editable) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run()  
			{
				userChatEnter.setEditable(editable);
			} 
		}  
				);  
	}  
	
	/******** CLIENT*************/
	// set up and run client
	private void runClient(String ipAddress) {
	
		chatServer = ipAddress;
		
		// connect to server, get streams, process connection
		try 
		{
			// create a Socket to make connection
			connectToServer();
			 // get the input and output streams
			getStreamsClient();
			// process connection
			processConnectionClient(); 
		} 
		catch (EOFException eofException) {
			displayMessage("\nClient terminated connection");
		} 
		catch (IOException ioException) {
			ioException.printStackTrace();
		} 

		finally {
			closeConnectionClient(); 
		} 
	}

	// connect to server
	private void connectToServer() throws IOException {
		displayMessage("Attempting connection\n");

		// create Socket to make connection to server
		client = new Socket(InetAddress.getByName(chatServer), 12345);

		// display connection information
		displayMessage("\nConnected to: " + client.getInetAddress().getHostName());
	} 

	// get streams to send and receive data
	private void getStreamsClient() throws IOException {
		// set up output stream for objects
		outputToServer = new ObjectOutputStream(client.getOutputStream());
		outputToServer.flush(); // flush output buffer to send header information

		// set up input stream for objects
		inputFromClient = new ObjectInputStream(client.getInputStream());


	} 

	
	// process connection with server
	private void processConnectionClient() throws IOException {
		
		// enable enterField-client can send message
		setTextFieldEditable(true);
		
		// process messages from server
		do 
		{
			// read message and display it
			try 
			{
				messageFromServer = (String) inputFromClient.readObject(); 
				if (!messageFromServer.equals("*Comment*")) {
					if ((messageFromServer.length() == 5)) {
						char l = messageFromServer.charAt(0);
						char m = messageFromServer.charAt(1);

						if ((l == '@' && m == '@') || (l == '!' && m == '!') || (l == '?' && m == '?') || 
								(l == '^' && m == '^') || (l == '>' && m == '>') || (l == '#' && m == '#')) {
							// filter the string message--game event or user message?

							messageFromServer = bsGame.InterpretMsg(messageFromServer);

							sendDataClient(messageFromServer);
						}
						// message is length 5 but not a game event
						else
							displayMessage("\nServer says --> " + messageFromServer);
					}
					
					else if (messageFromServer != null)
						displayMessage("\nServer says --> " + messageFromServer);
				}
			
			} 
			catch (ClassNotFoundException classNotFoundException) {
				displayMessage("\nUnknown object type received");
			} 

		} while (!messageFromServer.equals("TERMINATE"));
	}

	public void sendDataClient(String message) {
		try // send object to client
		{
			outputToServer.writeObject(message);
			outputToServer.flush(); 
			
		} 
		
		catch (IOException ioException) {
			messageTextArea.append("\nError writing object");
		} 
	} 
	// close streams and socket
	private void closeConnectionClient() {
		displayMessage("\nClosing connection");
		setTextFieldEditable(false); 

		try {
			outputToServer.close(); 
			inputFromServer.close();
			client.close(); 
		} 
		catch (IOException ioException) {
			ioException.printStackTrace();
		} 
	} 
	/**Utility Functions**/
	//generates a random integer
	public static int randNum(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	
	/**Networking Functions**/
	//gets user messages
	private void userChatEnterActionPerformed(java.awt.event.ActionEvent evt) {
		if (isServer == true) {
			displayMessage("\nServer says--> " + userChatEnter.getText());
			sendData(evt.getActionCommand());
			userChatEnter.setText("");
		} 
		else {
			displayMessage("\nClient says--> " + userChatEnter.getText());
			sendDataClient(evt.getActionCommand());
			userChatEnter.setText("");
		}

	}

	//takes ipAddress
	private void ipAddressFieldActionPerformed(java.awt.event.ActionEvent evt) {
		ipAddress = ipField.getText();	
	}
	
	
	/** MAIN METHOD*/
	public static void main(String args[]) {
		// Create and display the form 
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Game().setVisible(true);
			}
		});

	}	
}