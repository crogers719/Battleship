/*
 * COSC 330: Battleship project
 * Colleen Rogers and Jon Gordy
 * March 8, 2016
 * 
 * Images.java
 * Description: This class listens to when mouse is un-clicked
 */

import java.awt.Component;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

class MyDropTargetListener extends DropTargetAdapter {

	private DropTarget dropTarget;
	private JPanel p;
	private static Point dropPoint;
	String draggedShip;
	int shipsPlaced = 0;
	

	public MyDropTargetListener(JPanel dropPanel) {

		p = dropPanel;
		dropTarget = new DropTarget(dropPanel, DnDConstants.ACTION_COPY, this, true, null);

	}

	@Override
	public void drop(DropTargetDropEvent event) {

		try {
			DropTarget shipDropped = (DropTarget) event.getSource();
			Component ca = (Component) shipDropped.getComponent();

			dropPoint = ca.getMousePosition();
			Transferable tr = event.getTransferable();
			

			if (event.isDataFlavorSupported(DataFlavor.imageFlavor)) {
				// resets validDrop to evaluate each move independently
				GetSquareDropped.validatePlacement = true;
				Icon img = (Icon) tr.getTransferData(DataFlavor.imageFlavor);
				
			
				draggedShip = img.toString();
				
				ShipInfo getShipInfo = new ShipInfo(draggedShip);

				if (img != null) {
					// determines and corrects square ship is dropped on
					GetSquareDropped getSquareDropped = new GetSquareDropped(dropPoint, draggedShip);
					// If a drop is valid(determined in GetSquareDropped Class) adds image to player panel
					if (GetSquareDropped.validatePlacement) {
						Game.randomButton.setEnabled(false);
						JLabel shipDragged = new JLabel(img);
						shipDragged.setBounds(getSquareDropped.getX(), getSquareDropped.getY(), getShipInfo.getShipLength(), getShipInfo.getShipWidth());
						p.add(shipDragged);
						p.repaint();
						p.revalidate();
						event.dropComplete(true);
						shipsPlaced++;

						if (shipsPlaced == 5) {
							Game.readyButton.setEnabled(true);
						}

					}
				}
			} else {

				event.rejectDrop();
			}
		} catch (Exception e) {
			e.printStackTrace();
			event.rejectDrop();
		}

	}

}