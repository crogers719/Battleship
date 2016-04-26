/*
 * COSC 330: Battleship project
 * Colleen Rogers and Jon Gordy
 * March 8, 2016
 * 
 * Images.java
 * Description: This class listens to when dragging starts
 */

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;


import java.awt.dnd.DragGestureRecognizer;

import java.io.IOException;

import javax.swing.Icon;
import javax.swing.JLabel;

class MyDragGestureListener implements DragGestureListener {

    @Override
    public void dragGestureRecognized(DragGestureEvent event) {
    	
    	JLabel shipGrabbed = (JLabel) event.getComponent();
     
        final Icon img = shipGrabbed.getIcon();
   
			
        Transferable transferable = new Transferable() {
            @Override
            public DataFlavor[] getTransferDataFlavors() {
                return new DataFlavor[]{DataFlavor.imageFlavor};
            }

            @Override
            public boolean isDataFlavorSupported(DataFlavor flavor) {
                if (!isDataFlavorSupported(flavor)) {
                    return false;
                }
                return true;
            }

           // @Override
            public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
            	
                return img;
            }
        };
        event.startDrag(null, transferable);
       		
    }
}