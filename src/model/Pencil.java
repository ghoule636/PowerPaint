/*
 *TCSS 305 - Spring 2015
 *Assignment 5 - PowerPaint
 */

package model;

import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;

import javax.swing.JPanel;

/**
 *This class will draw a line wherever the users mouse is dragged.
 *
 *@author Gabriel Houle
 *@version 5/20/15
 */
public class Pencil extends AbstractTool {
    
    /**
     *The path that this tool will draw.
     */
    private Path2D myPath;

    /**
     *This creates a pencil tool.
     *
     *@param thePanel The panel this tool will draw on.
     */
    public Pencil(final JPanel thePanel) {
        super(thePanel);
    }
    
    @Override 
    public void mousePressed(final MouseEvent theEvent) {
        myPath = new Path2D.Double();
        myPath.moveTo(theEvent.getX(), theEvent.getY());
        myPanel.renderShape(myPath);
    }
    
    @Override
    public void mouseDragged(final MouseEvent theEvent) {
        myPath.lineTo(theEvent.getX(), theEvent.getY());

        myPanel.renderShape(myPath);
    }
    
    @Override
    public void mouseReleased(final MouseEvent theEvent) {
        myPath.moveTo(theEvent.getX(), theEvent.getY());
        myPath.closePath();
        myPanel.addShape(myPath);
        
    }
}