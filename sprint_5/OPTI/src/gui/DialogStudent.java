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
public class DialogStudent extends JDialog {
	private Interface mother;
	private PanelStudent father;
	private JTextField fieldGroup, fieldLName, fieldFName; 
		
	/**
	 * Create a DialogStudent instance.
	 * @param pMother The main frame.
	 * @param pFather The panel as father.
	 */
	public DialogStudent(Interface pMother, PanelStudent pFather) {
		super(pMother, "New Student", true);
		this.father = pFather;
		this.setSize(200, 150);
		this.setLocationRelativeTo(null);
		
		this.add(createPanelDialogStudent());
	}
	
	/**
	 * Creates the main dialog.
	 * @return The main dialog.
	 */
	public JPanel createPanelDialogStudent() {
		JPanel panel = new JPanel(new BorderLayout());
		
		panel.add(createPanelDialogFieldStudent(), BorderLayout.CENTER);
		panel.add(createPanelDialogButtonStudent(), BorderLayout.SOUTH);
		
		return panel;
	}
	
	/**
	 * Creates the panel which contains the student's text fields.
	 * @return The panel which contains the student's text fields.
	 */
	public JPanel createPanelDialogFieldStudent() {
		JPanel panel = new JPanel();
		
		this.fieldGroup = new JTextField("");
		this.fieldLName = new JTextField("");
		this.fieldFName = new JTextField("");
		this.fieldGroup.setPreferredSize(new Dimension(100, 20));
		this.fieldFName.setPreferredSize(this.fieldGroup.getPreferredSize());
		this.fieldLName.setPreferredSize(this.fieldGroup.getPreferredSize());
		JLabel labelGroup = new JLabel("        Group : ");
		JLabel labelLName = new JLabel("Last name : ");
		JLabel labelFName = new JLabel("First name : ");
		
		panel.add(labelGroup);
		panel.add(fieldGroup);
		panel.add(labelLName);
		panel.add(fieldLName);
		panel.add(labelFName);
		panel.add(fieldFName);
		
		return panel;
	}
	
	/**
	 * Create the panel which contains the buttons.
	 * @return The panel which contains the buttons.
	 */
	public JPanel createPanelDialogButtonStudent() {
		JPanel panel = new JPanel();
		
		JButton butOk = new JButton("OK");
		JButton butCancel = new JButton("Cancel");
		butOk.setPreferredSize(butCancel.getPreferredSize());
		
		butOk.addActionListener(new Events(this.father, this));
		butOk.setActionCommand("ADDSTUDENT");
		butCancel.addActionListener(new Events(this.father, this));
		butCancel.setActionCommand("CANCELSTUDENT");
		
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
	public PanelStudent getFather() {
		return this.father;
	}
	
	/**
	 * Returns the student's group text field.
	 * @return The student's group text field.
	 */
	public JTextField getFieldGroup() {
		return this.fieldGroup;
	}
	
	/**
	 * Return the student's first name text field.
	 * @return The student's first name field.
	 */
	public JTextField getFieldLName() {
		return this.fieldLName;
	}
	
	/**
	 * Return the student's last name text field.
	 * @return The student's last name text field.
	 */
	public JTextField getFieldFName() {
		return this.fieldFName;
	}
	
}
