/*
 *TCSS 305 - Spring 2015
 *Assignment 5 - PowerPaint
 */

package view;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.Tool;

/**
 *This is an action that will select a tool to draw with on the drawing panel.
 *
 *@author Gabriel Houle
 *@version 5/20/15
 */
public class ToolAction extends AbstractAction {
    
    /**
     *Stores serial number for the ToolAction.
     */
    private static final long serialVersionUID = -5330151501304630964L;

    /**
     *This is the tool that this event will pass to the panel as its current drawing tool.
     */
    private final Tool myTool;
    
    /**
     *Stores the panel that this Action will effect.
     */
    private final PaintDrawPanel myPanel;

    /**
     *Creates an action that will set the panel to have the specified tool.
     *
     *@param theTool Represents the tool this action will create.
     *@param thePanel Is the panel that this action will change.
     *@param theSelection Will indicate if this button is selected by default.
     */
    public ToolAction(final Tool theTool, final JPanel thePanel, final boolean theSelection) {
        super(theTool.getName());
        myTool = theTool;
        myPanel = (PaintDrawPanel) thePanel;
        putValue(Action.SELECTED_KEY, theSelection);

        putValue(Action.SMALL_ICON, new ImageIcon("images//" + myTool.getImageFile()));

    }
    
    /**
     *This action will set the current tool of myPanel.
     *
     *@param theEvent is the event that triggers this.
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myPanel.setTool(myTool);

    }
    
    /**
     *This method will return the name of the tool this action represents.
     *
     *@return The name.
     */
    @Override
    public String toString() {
        return myTool.getName();
    }
}