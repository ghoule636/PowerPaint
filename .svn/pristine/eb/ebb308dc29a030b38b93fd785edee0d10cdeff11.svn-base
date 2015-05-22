/*
 *TCSS 305 - Spring 2015
 *Assignment 5 - PowerPaint
 */

package model;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

/**
 *This class will draw a rectangle.
 *
 *@author Gabriel Houle
 *@version 5/20/15
 */
public class Rectangle extends AbstractTool {
    
    /**
     *The rectangle that this tool will create.
     */
    private Rectangle2D myRectangle;
    
    /**
     *Indicates the starting Point for this shape.
     */
    private Point2D myStartPoint;

    /**
     *Creates the rectangle tool.
     *
     *@param thePanel The panel this tool will draw on.
     */
    public Rectangle(final JPanel thePanel) {
        super(thePanel);
    }
    
    @Override 
    public void mousePressed(final MouseEvent theEvent) {
        myRectangle = new Rectangle2D.Double(theEvent.getX(), theEvent.getY(),
                                             theEvent.getX() - theEvent.getX(),
                                             theEvent.getY() - theEvent.getY());
        myStartPoint = new Point2D.Double(theEvent.getX(), theEvent.getY());

        myPanel.renderShape(myRectangle);
    }
    
    @Override
    public void mouseDragged(final MouseEvent theEvent) {
        myRectangle.setFrameFromDiagonal(myStartPoint, theEvent.getPoint());        

        myPanel.renderShape(myRectangle);

    }
    
    @Override
    public void mouseReleased(final MouseEvent theEvent) {
        myRectangle.setFrameFromDiagonal(myStartPoint, theEvent.getPoint());
        
        myPanel.renderShape(myRectangle);
        myPanel.addShape(myRectangle);
    }
}