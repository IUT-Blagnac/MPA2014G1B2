package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Create the JDialog which allows to add a student.
 * @author S3G1B2
 *
 */
@SuppressWarnings("serial")
public class DialogSubject extends JDialog {
	private Interface mother;
	private PanelSubject father;
	private JTextField fieldID, fieldName, fieldTitle; 
		
	/**
	 * Create a DialogSubject instance.
	 * @param pMother The main frame.
	 * @param pFather The panel as father.
	 */
	public DialogSubject(Interface pMother, PanelSubject pFather) {
		super(pMother, "New Subject", true);
		this.father = pFather;
		this.setSize(200, 150);
		this.setLocationRelativeTo(null);
		
		this.add(createPanelDialogSubject());
	}
	
	/**
	 * Creates the main dialog.
	 * @return The main dialog.
	 */
	public JPanel createPanelDialogSubject() {
		JPanel panel = new JPanel(new BorderLayout());
		
		panel.add(createPanelDialogFieldSubject(), BorderLayout.CENTER);
		panel.add(createPanelDialogButtonSubject(), BorderLayout.SOUTH);
		
		return panel;
	}
	
	/**
	 * Creates the panel which contains the subject's text fields.
	 * @return The panel which contains the subject's text fields.
	 */
	public JPanel createPanelDialogFieldSubject() {
		JPanel panel = new JPanel();
		
		this.fieldID = new JTextField("");
		this.fieldName = new JTextField("");
		this.fieldTitle = new JTextField("");
		this.fieldID.setPreferredSize(new Dimension(100, 20));
		this.fieldName.setPreferredSize(this.fieldID.getPreferredSize());
		this.fieldTitle.setPreferredSize(this.fieldID.getPreferredSize());
		JLabel labelID = new JLabel("        ID : ");
		JLabel labelName = new JLabel("Name : ");
		JLabel labelTitle = new JLabel("Title : ");		
		
		panel.add(labelID);
		panel.add(fieldID);
		panel.add(labelName);
		panel.add(fieldName);
		panel.add(labelTitle);
		panel.add(fieldTitle);
		
		return panel;
	}
	
	/**
	 * Create the panel which contains the buttons.
	 * @return The panel which contains the buttons.
	 */
	public JPanel createPanelDialogButtonSubject() {
		JPanel panel = new JPanel();
		
		JButton butOk = new JButton("OK");
		JButton butCancel = new JButton("Cancel");
		butOk.setPreferredSize(butCancel.getPreferredSize());
		
		butOk.addActionListener(new Events(this.father, this));
		butOk.setActionCommand("ADDSUBJECT");
		butCancel.addActionListener(new Events(this.father, this));
		butCancel.setActionCommand("CANCELSUBJECT");
		
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
	public PanelSubject getFather() {
		return this.father;
	}
	
	/**
	 * Returns the subject's id text field.
	 * @return The subject's id text field.
	 */
	public JTextField getFieldID() {
		return this.fieldID;
	}
	
	/**
	 * Returns the subject's name text field.
	 * @return The subject's name text field.
	 */
	public JTextField getFieldName() {
		return this.fieldName;
	}
	
	/**
	 * Returns the subject's title text field.
	 * @return The subject's title text field.
	 */
	public JTextField getFieldTitle() {
		return this.fieldTitle;
	}
}
