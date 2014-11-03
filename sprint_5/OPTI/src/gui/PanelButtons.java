package gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Builds the panel which displays the Quit button of OPTI's main frame.
 * @author S3G1B2
 *
 */
@SuppressWarnings("serial")
public class PanelButtons extends JPanel {
	Interface mother;
	JButton butQuit;
	
	/**
	 * Creates a PanelButtons instance with the main frame as mother.
	 * @param pMother The main frame.
	 */
	public PanelButtons(Interface pMother) {
		super();
		this.mother = pMother;
		this.butQuit = new JButton("Quit");
		this.butQuit.addActionListener(new Events(this.mother));
		this.butQuit.setActionCommand("QUIT");
		this.butQuit.setPreferredSize(new Dimension(80, 26));
		this.add(butQuit);
	}
}
