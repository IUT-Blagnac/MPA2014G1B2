package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Create the JDialog which allows to add a teacher.
 * @author S3G1B2
 *
 */
@SuppressWarnings("serial")
public class DialogTeacher extends JDialog {
	Interface mother;
	PanelTeacher father;
	JTextField fieldFName, fieldLName;
		
	/**
	 * Create a DialogTeacher instance.
	 * @param pMother The main frame.
	 * @param pFather The panel as father.
	 */
	public DialogTeacher(Interface pMother, PanelTeacher pFather) {
		super(pMother, "New Teacher", true);
		this.father = pFather;
		this.setSize(200, 150);
		this.setLocationRelativeTo(null);
		
		this.add(createPanelDialogTeacher());
	}
	
	/**
	 * Creates the main dialog.
	 * @return The main dialog.
	 */
	public JPanel createPanelDialogTeacher() {
		JPanel panel = new JPanel(new BorderLayout());
		
		panel.add(createPanelDialogFieldTeacher(), BorderLayout.CENTER);
		panel.add(createPanelDialogButtonTeacher(), BorderLayout.SOUTH);
		
		return panel;
	}
	
	/**
	 * Creates the panel which contains the teacher's text fields.
	 * @return The panel which contains the teacher's text fields.
	 */
	public JPanel createPanelDialogFieldTeacher() {
		JPanel panel = new JPanel();
		
		this.fieldFName = new JTextField("");
		this.fieldLName = new JTextField("");
		this.fieldFName.setPreferredSize(new Dimension(100, 20));
		this.fieldLName.setPreferredSize(this.fieldFName.getPreferredSize());
		
		JLabel labelFName = new JLabel("First name : ");
		JLabel labelLName = new JLabel("Last name : ");
	
		panel.add(labelFName);
		panel.add(fieldFName);
		panel.add(labelLName);
		panel.add(fieldLName);
	
		
		return panel;
	}
	
	/**
	 * Create the panel which contains the buttons.
	 * @return The panel which contains the buttons.
	 */
	public JPanel createPanelDialogButtonTeacher() {
		JPanel panel = new JPanel();
		
		JButton butOk = new JButton("OK");
		JButton butCancel = new JButton("Cancel");
		butOk.setPreferredSize(butCancel.getPreferredSize());
		
		butOk.addActionListener(new Events(this.father, this));
		butOk.setActionCommand("ADDTEACHER");
		butCancel.addActionListener(new Events(this.father, this));
		butCancel.setActionCommand("CANCELTEACHER");
		
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
	public PanelTeacher getFather() {
		return this.father;
	}
	
	/**
	 * Returns the teacher's first name text field.
	 * @return The teacher's first name text field.
	 */
	public JTextField getFieldFName() {
		return this.fieldFName;
	}
	
	/**
	 * Returns the teacher's last name text field.
	 * @return The teacher's last name text field.
	 */
	public JTextField getFieldLName() {
		return this.fieldLName;
	}	
}
