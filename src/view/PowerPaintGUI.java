/*
 *TCSS 305 - Spring 2015
 *Assignment 5 - PowerPaint
 */

package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import model.Ellipse;
import model.Line;
import model.Pencil;
import model.Rectangle;

/**
 *This class will create the JFrame and add the DrawingPanel, Menubar, and toolbar.
 *Also adds the Icon.
 *
 *@author Gabriel Houle
 *@version 5/20/15
 */
public class PowerPaintGUI {
    
    /**
     *Toolkit used for getting screen size.
     */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    
    /**
     *Represents the size of the current users screen.
     */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    
    /**
     *The screen width.
     */
    private static final int SCREEN_WIDTH = SCREEN_SIZE.width;
    
    /**
     *The screen height.
     */
    private static final int SCREEN_HEIGHT = SCREEN_SIZE.height;
    
    /**
     *Stores the frame used to contain the GUI.
     */
    private final JFrame myJFrame;
    
    /**
     *Stores the panel used for drawing.
     */
    private final PaintDrawPanel myDrawingPanel;
    
    /**
     *Contains the menu for this Frame.
     */
    private final PowerPaintMenuBar myMenuBar;
    
    /**
     *Stores the tool bar on this frame.
     */
    private final PowerPaintToolBar myToolBar;

    /**
     *Create the frame.
     */
    public PowerPaintGUI() {
        myJFrame = new JFrame("PowerPaint");

        myJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        myDrawingPanel = new PaintDrawPanel();
        
        myMenuBar = new PowerPaintMenuBar(myJFrame, myDrawingPanel);
        
        myToolBar = new PowerPaintToolBar();

        setUpComponents();

        myJFrame.pack();

        myJFrame.setLocation(SCREEN_WIDTH / 2 - myJFrame.getWidth() / 2,
                             SCREEN_HEIGHT / 2 - myJFrame.getHeight() / 2);
        
        

        myJFrame.setVisible(true);

    }
    
    /**
     *Add the components to the frame.
     */
    private void setUpComponents() {
        
        final Action[] actions = {new ToolAction(new Pencil(myDrawingPanel),
                                                 myDrawingPanel, true),
                                  new ToolAction(new Line(myDrawingPanel),
                                                 myDrawingPanel, false),
                                  new ToolAction(new Rectangle(myDrawingPanel),
                                                 myDrawingPanel, false),
                                  new ToolAction(new Ellipse(myDrawingPanel),
                                                 myDrawingPanel, false) };
        
        for (int i = 0; i < actions.length; i++) {
            myMenuBar.createToolButtons(actions[i]);
            myToolBar.createToolBarButton(actions[i]);
            
        }
        myDrawingPanel.addPropertyChangeListener(myMenuBar);
        myJFrame.getContentPane().add(myDrawingPanel, BorderLayout.CENTER);
        myJFrame.setJMenuBar(myMenuBar);
        myJFrame.setIconImage(new ImageIcon("./images/M51.gif").getImage());
        myJFrame.add(myToolBar, BorderLayout.SOUTH);
    }
    
    /**
     *This method will clear the drawing panel of all graphics.
     */
    public void undoAll() {
        myDrawingPanel.undoAll();
    }
}