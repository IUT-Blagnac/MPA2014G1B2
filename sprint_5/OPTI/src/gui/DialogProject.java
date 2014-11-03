package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Creates the JDialog which allows to add a project.
 * @author S3G1B2
 *
 */
@SuppressWarnings("serial")
public class DialogProject extends JDialog {
	private Interface mother;
	private PanelProject father;
	private JTextField fieldGroup, fieldTitle; 
		
	/**
	 * Create a DialogProject instance.
	 * @param pMother The main frame.
	 * @param pFather The panel as father.
	 */
	public DialogProject(Interface pMother, PanelProject pFather) {
		super(pMother, "New Project", true);
		this.father = pFather;
		this.setSize(200, 150);
		this.setLocationRelativeTo(null);
		
		this.add(createPanelDialogProject());
	}
	
	/**
	 * Creates the main dialog.
	 * @return The main dialog.
	 */
	public JPanel createPanelDialogProject() {
		JPanel panel = new JPanel(new BorderLayout());
		
		panel.add(createPanelDialogFieldProject(), BorderLayout.CENTER);
		panel.add(createPanelDialogButtonProject(), BorderLayout.SOUTH);
		
		return panel;
	}
	
	/**
	 * Creates the panel which contains the project's text fields.
	 * @return The panel which contains the project's text fields.
	 */
	public JPanel createPanelDialogFieldProject() {
		JPanel panel = new JPanel();
		
		this.fieldGroup = new JTextField("");
		this.fieldTitle = new JTextField("");
		this.fieldGroup.setPreferredSize(new Dimension(100, 20));
		this.fieldTitle.setPreferredSize(this.fieldGroup.getPreferredSize());
		JLabel labelGroup = new JLabel("Group : ");
		JLabel labelTitle = new JLabel("  Title : ");
				
		panel.add(labelGroup);
		panel.add(fieldGroup);
		panel.add(labelTitle);
		panel.add(fieldTitle);
		
		return panel;
	}
	
	/**
	 * Create the panel which contains the buttons.
	 * @return The panel which contains the buttons.
	 */
	public JPanel createPanelDialogButtonProject() {
		JPanel panel = new JPanel();
		
		JButton butOk = new JButton("OK");
		JButton butCancel = new JButton("Cancel");
		butOk.setPreferredSize(butCancel.getPreferredSize());
		
		butOk.addActionListener(new Events(this.father, this));
		butOk.setActionCommand("ADDPROJECT");
		butCancel.addActionListener(new Events(this.father, this));
		butCancel.setActionCommand("CANCELPROJECT");
		
		panel.add(butOk);
		panel.add(butCancel);
		
		return panel;
	}
	
	/**
	 * Returns the main frame.
	 * @return The OPTI's main frame.
	 */
	public Interface getMother() {
		return this.mother;
	}
	
	/**
	 * Returns the dialog's father.
	 * @return The dialog's father.
	 */
	public PanelProject getFather() {
		return this.father;
	}
	
	/**
	 * Returns the project's group text field.
	 * @return The project's group text field.
	 */
	public JTextField getFieldGroup() {
		return this.fieldGroup;
	}
	
	/**
	 * Return the project's title text field.
	 * @return The project's title text field.
	 */
	public JTextField getFieldTitle() {
		return this.fieldTitle;
	}
}
