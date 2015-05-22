/*
 *TCSS 305 - Spring 2015
 *Assignment 5 - PowerPaint
 */

package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *This class is the JMenuBar for the PowerPaint program. Contains four sub-menus: 
 *File, Options, Tools, and Help.
 *
 *@author Gabriel Houle
 *@version 5/20/15
 */
public class PowerPaintMenuBar extends JMenuBar implements PropertyChangeListener {
    
    /**
     *Stores the serial number.
     */
    private static final long serialVersionUID = 4900244138633393190L;

    /**
     *The minor tick spacing for the thickness slider bar.
     */
    private static final int MINOR_TICK_SPACING = 1;
    
    /**
     *The major tick spacing for the thickness slider bar.
     */
    private static final int MAJOR_TICK_SPACING = 5;
    
    /**
     *The maximum thickness that can be applied to the drawing tool.
     */
    private static final int MAX_THICKNESS = 20;
    
    /**
     *Stores the size of the imageIcon used in the about menu.
     */
    private static final int ICON_SIZE = 50;
    
    /**
     *String used by PropertyChangeListener.
     */
    private static final String EMPTY_STRING = "empty";
    
    /**
     *String used by PropertyChangeListener.
     */
    private static final String NOT_EMPTY_STRING = "notEmpty";

    /**
     *Stores the File sub-menu.
     */
    private final JMenu myFileMenu;
    
    /**
     *Stores the options sub-menu.
     */
    private final JMenu myOptionsMenu;
    
    /**
     *Stores the tools sub-menu.
     */
    private final JMenu myToolsMenu;
    
    /**
     *Stores the Help sub-menu.
     */
    private JMenu myHelpMenu;
    
    /**
     *Stores the undoAll button.
     */
    private JMenuItem myUndoAllButton;
    
    /**
     *Stores the undo button.
     */
    private JMenuItem myUndoButton;
    
    /**
     *Stores the re-do button.
     */
    private JMenuItem myRedoButton;
    
    /**
     *Stores the button group for the tool buttons.
     */
    private final ButtonGroup myButtonGroup;
    
    /**
     *This method will construct a JMenuBar.
     * 
     *@param theFrame Stores the frame this menu will be added to.
     *@param thePanel stores the drawing panel that this toolbar can affect.
     */
    public PowerPaintMenuBar(final JFrame theFrame, final JPanel thePanel) {
        super();
        
        myFileMenu = new JMenu("File");
        setupFileMenu(theFrame, thePanel);
        myOptionsMenu = new JMenu("Options");
        setupOptionsMenu(thePanel);
        myToolsMenu = new JMenu("Tools");
        myToolsMenu.setMnemonic(KeyEvent.VK_T);
        setupHelpMenu();
        myButtonGroup = new ButtonGroup();
        
        setup();
    }
    
    /**
     *This method will add the created tool bar to the given frame.
     */
    private void setup() {
        
        add(myFileMenu);
        add(myOptionsMenu);
        add(myToolsMenu);
        add(myHelpMenu);
        
    }
    
    /**
     *This method will create the various buttons found in the file sub-menu.
     *
     *@param theFrame is the frame this menu will be added to.
     *@param thePanel is the panel this menu can affect.
     */
    private void setupFileMenu(final JFrame theFrame, final JPanel thePanel) {
        myUndoAllButton = new JMenuItem("Undo all changes");
        myUndoButton = new JMenuItem("Undo");
        myRedoButton = new JMenuItem("Redo");
        final JMenuItem quitButton = new JMenuItem("Exit");
        
        myFileMenu.setMnemonic(KeyEvent.VK_F);
        myUndoAllButton.setMnemonic(KeyEvent.VK_U);
        quitButton.setMnemonic(KeyEvent.VK_X);
        quitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent theEvent) {
                    theFrame.dispatchEvent(new WindowEvent(theFrame,
                                                       WindowEvent.WINDOW_CLOSING));
                }
            });
        
        myUndoAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                ((PaintDrawPanel) thePanel).undoAll();
            }
        });
        
        myUndoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                ((PaintDrawPanel) thePanel).undo();
            }
        });
        
        myRedoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                ((PaintDrawPanel) thePanel).redo();
            }
        });
        
        myUndoAllButton.setEnabled(false);
        myUndoButton.setEnabled(false);
        myRedoButton.setEnabled(false);
        
        myUndoButton.setAccelerator(KeyStroke.getKeyStroke("ctrl Z"));
        myRedoButton.setAccelerator(KeyStroke.getKeyStroke("ctrl Y"));
        
        myFileMenu.add(myUndoAllButton);
        myFileMenu.addSeparator();
        myFileMenu.add(myUndoButton);
        myFileMenu.add(myRedoButton);
        myFileMenu.addSeparator();
        myFileMenu.add(quitButton);
    }
    
    /**
     *This helper method sets up the options sub-menu.
     *
     *@param thePanel the panel that this menu will affect.
     */
    private void setupOptionsMenu(final JPanel thePanel) {
        
        final JMenuItem gridButton = new JCheckBoxMenuItem("Grid");
        final JSlider thicknessSlider = new JSlider(SwingConstants.HORIZONTAL,
                                                    0, MAX_THICKNESS, 1);
        final JMenu thicknessMenu = new JMenu("Thickness");
        final JMenuItem colorButton = new JMenuItem("Color...", new ColorIcon(Color.BLACK));
        
        thicknessSlider.setMajorTickSpacing(MAJOR_TICK_SPACING);
        thicknessSlider.setMinorTickSpacing(MINOR_TICK_SPACING);
        thicknessSlider.setPaintLabels(true);
        thicknessSlider.setPaintTicks(true);
        thicknessSlider.addChangeListener(new ChangeListener() {
            
            @Override
            public void stateChanged(final ChangeEvent theEvent) {
                final int value = thicknessSlider.getValue();
                ((PaintDrawPanel) thePanel).setWidth(value);
            }
        });
        
        thicknessMenu.add(thicknessSlider);
        
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                final Color result = JColorChooser.showDialog(null, "Select a color", null);
                
                if (result != null) {
                    ((PaintDrawPanel) thePanel).setColor(result);
                    colorButton.setIcon(new ColorIcon(result));
                }
            }
        });
        
        gridButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                ((PaintDrawPanel) thePanel).setGrid(gridButton.isSelected());
            }
        });
        
        myOptionsMenu.setMnemonic(KeyEvent.VK_O);
        gridButton.setMnemonic(KeyEvent.VK_G);
        thicknessMenu.setMnemonic(KeyEvent.VK_T);
        colorButton.setMnemonic(KeyEvent.VK_C);
        
        myOptionsMenu.add(gridButton);
        myOptionsMenu.addSeparator();
        myOptionsMenu.add(thicknessMenu);
        myOptionsMenu.addSeparator();
        myOptionsMenu.add(colorButton);
    }
    
    /**
     *This method will allow the frame to add tool actions to the menu.
     *This class will allow you to select if this button is selected by default.
     * 
     *@param theToolAction This action will be added to this button.
     */
    public void createToolButtons(final Action theToolAction) {
        
        final JMenuItem newToolButton = new JRadioButtonMenuItem(theToolAction);
        newToolButton.setMnemonic(theToolAction.toString().charAt(0));
        
        myToolsMenu.add(newToolButton);
        myButtonGroup.add(newToolButton);
    }
    
    /**
     *This method sets up the help sub-menu.
     */
    private void setupHelpMenu() {
        myHelpMenu = new JMenu("Help"); 
        final JMenuItem aboutButton = new JMenuItem("About...");
        
        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                final Image img = new ImageIcon("./images/M51.gif").getImage();
                final Image sizedImg = img.getScaledInstance(ICON_SIZE, ICON_SIZE,
                                                             java.awt.Image.SCALE_SMOOTH);
                JOptionPane.showMessageDialog(null,
                                              "TCSS 305 PowerPaint\n Spring 2015",
                                              "About", JOptionPane.INFORMATION_MESSAGE,
                                              new ImageIcon(sizedImg));

            }
        });
        
        myHelpMenu.setMnemonic(KeyEvent.VK_H);
        aboutButton.setMnemonic(KeyEvent.VK_A);
        
        myHelpMenu.add(aboutButton);
    }
    
    /**
     *Allows the menu bar to enable or disable the undoall buttons based on
     *events in the drawing panel.
     *
     *@param theEvent is the event that was triggered.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if ("shapeArray".equals(theEvent.getPropertyName())) {
            myUndoAllButton.setEnabled(NOT_EMPTY_STRING.equals(theEvent.getNewValue()));
            myUndoButton.setEnabled(NOT_EMPTY_STRING.equals(theEvent.getNewValue()));
            
        } else if ("array".equals(theEvent.getPropertyName())) {
            myUndoAllButton.setEnabled(!EMPTY_STRING.equals(theEvent.getNewValue()));
            myUndoButton.setEnabled(!EMPTY_STRING.equals(theEvent.getNewValue()));
            myRedoButton.setEnabled(!EMPTY_STRING.equals(theEvent.getNewValue()));
            
        } else if ("stack".equals(theEvent.getPropertyName())) {
            myUndoButton.setEnabled(!EMPTY_STRING.equals(theEvent.getNewValue()));
            myUndoAllButton.setEnabled(!EMPTY_STRING.equals(theEvent.getNewValue()));
            
        } else if ("emptystack".equals(theEvent.getPropertyName())) {
            myRedoButton.setEnabled(!EMPTY_STRING.equals(theEvent.getNewValue()));
            
        } else if ("undo".equals(theEvent.getPropertyName())) {
            myRedoButton.setEnabled("enable".equals(theEvent.getNewValue()));
        }
    }
    
    /**
     *This class will stores the color icon displayed next to the Color... option
     *in the menus.
     *
     *@author Gabriel Houle
     *@version 5/20/15
     */
    private class ColorIcon implements Icon {
        
        /**
         *Stores the height of this icon.
         */
        private static final int HEIGHT = 14;
        
        /**
         *Stores the width of this icon.
         */
        private static final int WIDTH = 14;
        
        /**
         *Stores the color of this icon.
         */
        private final Color myColor;
        
        /**
         *Creates an icon with the specified color.
         *
         *@param theColor The color this icon will fill with.
         */
        public ColorIcon(final Color theColor) {
            myColor = theColor;
        }
        
        /**
         *Returns the height of the icon.
         *
         *@return the height.
         */
        public int getIconHeight() {
            return HEIGHT;
        }
        
        /**
         *Returns the width of this icon.
         *
         *@return the width.
         */
        public int getIconWidth() {
            return WIDTH;
        }
        
        /**
         *This method will paint the color box.
         *
         *@param theC The component being drawn.
         *@param theG theGraphics that will be drawn.
         *@param theX is the coordinates of this icon.
         *@param theY the y coordinate of this icon.
         */
        public void paintIcon(final Component theC, final Graphics theG,
                              final int theX, final int theY) {
            theG.setColor(myColor);
            theG.fillRect(theX,  theY, WIDTH - 1, HEIGHT - 1);
            theG.setColor(Color.BLACK);
            theG.drawRect(theX, theY, WIDTH - 1, HEIGHT - 1);
        }        
    }
}