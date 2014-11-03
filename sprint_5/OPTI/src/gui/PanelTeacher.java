package gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import library.CtrlTeacher;
import data.Teacher;

/**
 * Builds the panel which displays the teachers' table.
 * @author S3G1B2
 *
 */
@SuppressWarnings("serial")
public class PanelTeacher extends JPanel {
	private Interface mother;
	private DefaultTableModel modelTeacher;
	private JTable tableTeacher;
	private Teacher[] dataTeacher;
	private String[] headerTeacher;
	private JButton butAddTeacher, butRemTeacher, butRestTeacher;
	private String teacherFilePath;
	
	/**
	 * Creates a PanelTeacher instance with the main frame as mother.
	 * @param pMother The main frame.
	 */
	public PanelTeacher(Interface pMother) {
		super();
		this.mother = pMother;
		this.teacherFilePath = "csv\\intervenants2014_2015.csv"; 
		this.headerTeacher = CtrlTeacher.getHeader();
		this.dataTeacher = CtrlTeacher.getTeachers(this.teacherFilePath);
		this.butAddTeacher = new JButton("Add");
		this.butRemTeacher = new JButton("Remove");
		this.butRestTeacher = new JButton("Restore");
		this.butAddTeacher.addActionListener(new Events(this.mother, this));
		this.butAddTeacher.setActionCommand("NEW");
		this.butAddTeacher.setPreferredSize(this.butRemTeacher.getPreferredSize());
		this.butRemTeacher.addActionListener(new Events(this.mother, this));
		this.butRemTeacher.setActionCommand("REM");
		this.butRestTeacher.addActionListener(new Events(this.mother, this));
		this.butRestTeacher.setActionCommand("REST");
		
		loadTable();
		JScrollPane scroll = new JScrollPane(this.tableTeacher);		
		
		this.add(scroll, BorderLayout.CENTER);		
		this.add(this.butAddTeacher, BorderLayout.SOUTH);
		this.add(this.butRemTeacher, BorderLayout.SOUTH);
		this.add(this.butRestTeacher, BorderLayout.SOUTH);
	}
		
	/**
	 * Loads the JTable containing the teachers.
	 */
	public void loadTable() {
		this.modelTeacher = new DefaultTableModel(this.headerTeacher, this.dataTeacher.length);
		this.tableTeacher = new JTable(this.modelTeacher);
		this.tableTeacher.setFont(new Font("Tahoma", Font.BOLD, 12));
	
		for (int i=0; i<dataTeacher.length; i++) {
			this.modelTeacher.setValueAt(this.dataTeacher[i].getFirstName(), i, 0);	
			this.modelTeacher.setValueAt(this.dataTeacher[i].getLastName(), i, 1);
		}
	}
	
	/**
	 * Adds a teacher to its JTable.
	 * @param pDialogTeacher The DialogTeacher instance as son.
	 * @see DialogTeacher
	 */
	public void addTeacher(DialogTeacher pDialogTeacher) {
		this.modelTeacher.addRow(new String[2]);
		this.modelTeacher.setValueAt(pDialogTeacher.getFieldFName().getText(), this.modelTeacher.getRowCount()-1, 0);
		this.modelTeacher.setValueAt(pDialogTeacher.getFieldLName().getText().toUpperCase(), this.modelTeacher.getRowCount()-1, 1);
	}
	
	/**
	 * Removes teachers from their JTable.
	 * @exception Caught and not returned.
	 */
	public void removeTeacher() {
		try {
			for (int i=0; i<this.tableTeacher.getRowCount(); i++) {
				while (this.tableTeacher.isRowSelected(i)) {
					this.modelTeacher.removeRow(i);
				}
			}
		} catch (Exception e) {}
	}
		
	/**
	 * Restores the teachers' JTable.
	 * @exception Caught and not returned.
	 */
	public void restoreTeacher() {
		this.modelTeacher.setRowCount(this.dataTeacher.length);
		try {
			for (int i=0; i<dataTeacher.length; i++) {
				this.modelTeacher.setValueAt(this.dataTeacher[i].getFirstName(), i, 0);	
				this.modelTeacher.setValueAt(this.dataTeacher[i].getLastName(), i, 1);
			}
		} catch (Exception e) {}
	}
	
	/**
	 * Created a DialogTeacher instance.
	 * @see DialogTeacher
	 */
	public void createDialogTeacher() {
		DialogTeacher dial = new DialogTeacher(this.mother, this);
		dial.setVisible(true);
	}
	
	/**
	 * Controls the data capture of the first name field.
	 * @param pDialTeacher The DialogTeacher instance as son.
	 * @return True if the data capture is good, False otherwise.
	 */
	public boolean isFNameFieldValid(DialogTeacher pDialTeacher) {
		if ( 	(pDialTeacher.getFieldFName().getText().length()>0) &&
				(pDialTeacher.getFieldFName().getText().length()<30) &&
				(pDialTeacher.getFieldFName().getText().matches("^[a-z]+[ |=\\-']?[[a-z]+[ \\-']?]*[a-z]+$"))
				) {
					return true;
		}
		else {
			return false;
		}	
	}
	
	/**
	 * Controls the data capture of the last name field.
	 * @param pDialogTeacher The DialogTeacher instance as son.
	 * @return True if the data capture is good, False otherwise.
	 */
	public boolean isLNameFieldValid(DialogTeacher pDialogTeacher) {
		if ( 	(pDialogTeacher.getFieldLName().getText().length()>0) &&
				(pDialogTeacher.getFieldLName().getText().length()<30) &&
				(pDialogTeacher.getFieldLName().getText().matches("^[a-z]+[ |=\\-']?[[a-z]+[ \\-']?]*[a-z]+$"))
				) {
					return true;
		}
		else {
			return false;
		}	
	}
	
	/**
	 * Exports teachers into a file.
	 * @see CtrlTeacher
	 */
	public void exportTeacher() {
		Teacher[] data = new Teacher[this.modelTeacher.getRowCount()];
		
		for (int i=0; i<data.length; i++) {
			data[i] = new Teacher(this.modelTeacher.getValueAt(i, 0).toString(), 
									this.modelTeacher.getValueAt(i, 1).toString());
		}
		
		try {
			CtrlTeacher.saveToCSV(data);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.mother, "An error has occured.", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Exports teachers into a chosen file.
	 * @param pPath The file's path.
	 * @see CtrlTeacher
	 */
	public void exportAsTeacher(String pPath) {
		Teacher[] data = new Teacher[this.modelTeacher.getRowCount()];
		
		for (int i=0; i<data.length; i++) {
			data[i] = new Teacher(this.modelTeacher.getValueAt(i, 0).toString(), 
									this.modelTeacher.getValueAt(i, 1).toString());
		}
		
		try {
			CtrlTeacher.saveToCSV(data, pPath);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.mother, "An error has occured.", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Returns the DefaultTableModel of the PanelTeacher's instance.
	 * @return The DefaultTableModel of the teachers.
	 */
	public DefaultTableModel getModelTeacher() {
		return this.modelTeacher;
	}
	
	/**
	 * Returns the JTable of the PanelTeacher's instance.
	 * @return The JTable of the teachers.
	 */
	public JTable getTableTeacher() {
		return this.tableTeacher;
	}
	
	/**
	 * Returns the filepath of the PanelTeacher's instance.
	 * @return The filepath of the teachers.
	 */
	public String getTeacherFilePath() {
		return this.teacherFilePath;
	}
	
	/**
	 * Sets the filepath of the PanelTeacher's instance.
	 * @param pTeacherFilePath The filepath of the teachers.
	 */
	public void setTeacherFilePath(String pTeacherFilePath) {
		this.teacherFilePath = pTeacherFilePath;
	}
	
	/**
	 * Returns the teachers.
	 * @return The teachers.
	 */
	public Teacher[] getTeachers() {
		return this.dataTeacher;
	}
	
	/**
	 * Sets the teachers.
	 * @param pDataTeacher The teachers.
	 */
	public void setTeachers(Teacher[] pDataTeacher) {
		this.dataTeacher = pDataTeacher;
	}
	
}

