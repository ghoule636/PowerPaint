/*
 *TCSS 305 - Spring 2015
 *Assignment 5 - PowerPaint
 */

package model;

import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

/**
 *This class will draw an ellipse.
 *
 *@author Gabriel Houle
 *@version 5/20/15
 */
public class Ellipse extends AbstractTool {
    
    /**
     *The ellipse that this tool will create.
     */
    private Ellipse2D myEllipse;
    
    /**
     *Indicates the starting Point for this shape.
     */
    private Point2D myStartPoint;

    /**
     *This creates an ellipse tool.
     *
     *@param thePanel The panel this tool will draw on.
     */
    public Ellipse(final JPanel thePanel) {
        super(thePanel);
    }
   
    @Override 
    public void mousePressed(final MouseEvent theEvent) {
        myEllipse = new Ellipse2D.Double(theEvent.getX(), theEvent.getY(),
                                             theEvent.getX() - theEvent.getX(),
                                             theEvent.getY() - theEvent.getY());
        myStartPoint = new Point2D.Double(theEvent.getX(), theEvent.getY());

        myPanel.renderShape(myEllipse);
    }
    
    @Override
    public void mouseDragged(final MouseEvent theEvent) {
        myEllipse.setFrameFromDiagonal(myStartPoint, theEvent.getPoint());        

        myPanel.renderShape(myEllipse);

    }
    
    @Override
    public void mouseReleased(final MouseEvent theEvent) {
        myEllipse.setFrameFromDiagonal(myStartPoint, theEvent.getPoint());
        
        myPanel.renderShape(myEllipse);
        myPanel.addShape(myEllipse); 
    }
}