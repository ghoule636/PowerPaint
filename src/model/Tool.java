/*
 *TCSS 305 - Spring 2015
 *Assignment 5 - PowerPaint
 */

package model;

import javax.swing.event.MouseInputListener;

/**
 *This interface will define a drawing tool that will have different ways of
 *drawing.
 *
 *@author Gabriel Houle
 *@version 5/20/15
 */
public interface Tool extends MouseInputListener {
    
    /**
     *Returns the name of this tool instance.
     *
     *@return The name of the tool.
     */
    String getName();
    
    /**
     *Gives the file name for this tool.
     *
     *@return The string holding this tools image file names.
     */
    String getImageFile();    
}
