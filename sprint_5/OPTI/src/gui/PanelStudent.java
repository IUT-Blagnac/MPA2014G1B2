package gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import library.CtrlStudent;
import data.Student;

/**
 * Builds the panel which displays the students' table.
 * @author S3G1B2
 *
 */
@SuppressWarnings("serial")
public class PanelStudent extends JPanel {
	private Interface mother;
	private DefaultTableModel modelStudent;
	private JTable tableStudent;
	private Student[] dataStudent;
	private String[] headerStudent;
	private JButton butAddStudent, butRemStudent, butRestStudent;
	private String studentFilePath;
	
	/**
	 * Creates a PanelStudent instance with the main frame as mother.
	 * @param pMother The main frame.
	 */
	public PanelStudent(Interface pMother) {
		super();
		this.mother = pMother;
		this.studentFilePath = "csv\\etudiants2014_2015.csv";
		this.headerStudent = CtrlStudent.getHeader();
		this.dataStudent = CtrlStudent.getStudents(this.studentFilePath);
		this.butAddStudent = new JButton("Add");
		this.butRemStudent = new JButton("Remove");
		this.butRestStudent = new JButton("Restore");
		this.butAddStudent.addActionListener(new Events(this.mother,this));
		this.butAddStudent.setActionCommand("NEW");
		this.butAddStudent.setPreferredSize(this.butRemStudent.getPreferredSize());
		this.butRemStudent.addActionListener(new Events(this.mother,this));
		this.butRemStudent.setActionCommand("REM");
		this.butRestStudent.addActionListener(new Events(this.mother,this));
		this.butRestStudent.setActionCommand("REST");

		loadTable();
		JScrollPane scroll = new JScrollPane(this.tableStudent);		
		
		this.add(scroll, BorderLayout.CENTER);		
		this.add(this.butAddStudent, BorderLayout.SOUTH);
		this.add(this.butRemStudent, BorderLayout.SOUTH);
		this.add(this.butRestStudent, BorderLayout.SOUTH);
	}
	
	/**
	 * Loads the JTable containing the students.
	 */
	public void loadTable() {
		this.modelStudent = new DefaultTableModel(this.headerStudent, this.dataStudent.length);
		this.tableStudent = new JTable(this.modelStudent);
		this.tableStudent.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.tableStudent.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	
		for (int i=0; i<dataStudent.length; i++) {
			this.modelStudent.setValueAt(this.dataStudent[i].getGroup(), i, 0);
			this.modelStudent.setValueAt(this.dataStudent[i].getLastName(), i, 1);
			this.modelStudent.setValueAt(this.dataStudent[i].getFirstName(), i, 2);
		}
		
		TableColumn colGroup = this.tableStudent.getColumnModel().getColumn(0);  
		TableColumn colLName = this.tableStudent.getColumnModel().getColumn(1);
		TableColumn colFName = this.tableStudent.getColumnModel().getColumn(2);
		
		colGroup.setPreferredWidth(50);
		colLName.setPreferredWidth(200);
		colFName.setPreferredWidth(200);
	}
	
	/**
	 * Adds a student to its JTable.
	 * @param pDialogStudent The DialogStudent instance as son.
	 * @see DialogStudent
	 */
	public void addStudent(DialogStudent pDialogStudent) {
		this.modelStudent.addRow(new String[3]);
		this.modelStudent.setValueAt(pDialogStudent.getFieldGroup().getText().toUpperCase(), this.modelStudent.getRowCount()-1, 0);
		this.modelStudent.setValueAt(pDialogStudent.getFieldLName().getText().toUpperCase(), this.modelStudent.getRowCount()-1, 1);
		this.modelStudent.setValueAt(pDialogStudent.getFieldFName().getText(), this.modelStudent.getRowCount()-1, 2);
	}
	
	/**
	 * Removes students from their JTable.
	 * @exception Caught and not returned.
	 */
	public void removeStudent() {
		try {
			for (int i=0; i<this.tableStudent.getRowCount(); i++) {
				while (this.tableStudent.isRowSelected(i)) {
					this.modelStudent.removeRow(i);
				}
			}
		} catch (Exception e) {}
	}

	/**
	 * Restores the students' JTable.
	 * @exception Caught and not returned.
	 */
	public void restoreStudent() {
		this.modelStudent.setRowCount(this.dataStudent.length);
		try {
			for (int i=0; i<dataStudent.length; i++) {
				this.modelStudent.setValueAt(this.dataStudent[i].getGroup(), i, 0);
				this.modelStudent.setValueAt(this.dataStudent[i].getLastName(), i, 1);
				this.modelStudent.setValueAt(this.dataStudent[i].getFirstName(), i, 2);	
				
			}
		} catch (Exception e) {}
	}
	
	/**
	 * Created a DialogStudent instance.
	 * @see DialogStudent
	 */
	public void createDialogStudent() {
			DialogStudent dial = new DialogStudent(this.mother, this);
			dial.setVisible(true);
	}
	
	/**
	 * Controls the data capture of the group field.
	 * @param pDialogStudent The DialogStudent instance as son.
	 * @return True if the data capture is good, False otherwise.
	 */
	public boolean isGroupFieldValid(DialogStudent pDialogStudent) {
		if ( 	(pDialogStudent.getFieldGroup().getText().length()==1) &&
				(pDialogStudent.getFieldGroup().getText().matches("[a-z]")) 
			) {
					return true;
		}
		else {
			return false;
		}	
	}
	
	/**
	 * Controls the data capture of the last name field.
	 * @param pDialogStudent The DialogStudent instance as son.
	 * @return True if the data capture is good, False otherwise.
	 */
	public boolean isLNameFieldValid(DialogStudent pDialogStudent) {
		if ( 	(pDialogStudent.getFieldLName().getText().length()>0) &&
				(pDialogStudent.getFieldLName().getText().length()<30) &&
				(pDialogStudent.getFieldLName().getText().matches("^[a-z]+[ |=\\-']?[[a-z]+[ \\-']?]*[a-z]+$"))
				) {
					return true;
		}
		else {
			return false;
		}	
	}
	
	/**
	 * Controls the data capture of the first name field.
	 * @param pDialogStudent The DialogStudent instance as son.
	 * @return True if the data capture is good, False otherwise.
	 */
	public boolean isFNameFieldValid(DialogStudent pDialogStudent) {
		if ( 	(pDialogStudent.getFieldFName().getText().length()>0) &&
				(pDialogStudent.getFieldFName().getText().length()<30) &&
				(pDialogStudent.getFieldFName().getText().matches("^[a-z]+[ |=\\-']?[[a-z]+[ \\-']?]*[a-z]+$"))
				) { 
					return true;
		}
		else {
			return false;
		}	
	}
		
	/**
	 * Exports students into a file.
	 * @see CtrlStudent
	 */
	public void exportStudent() {
		Student[] data = new Student[this.modelStudent.getRowCount()];
			
		for (int i=0; i<data.length; i++) {
			data[i] = new Student(this.modelStudent.getValueAt(i, 0).toString(), 
									this.modelStudent.getValueAt(i, 1).toString(), 
									this.modelStudent.getValueAt(i, 2).toString());
		}
				
		try {
			CtrlStudent.saveToCSV(data);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.mother, "An error has occured.", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Exports students into a chosen file.
	 * @param pPath The file's path.
	 * @see CtrlStudent
	 */
	public void exportAsStudent(String pPath) {
		Student[] data = new Student[this.modelStudent.getRowCount()];
		
		for (int i=0; i<data.length; i++) {
			data[i] = new Student(this.modelStudent.getValueAt(i, 0).toString(), 
									this.modelStudent.getValueAt(i, 1).toString(), 
									this.modelStudent.getValueAt(i, 2).toString());
		}
				
		try {
			CtrlStudent.saveToCSV(data,pPath);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.mother, "An error has occured.", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Returns the DefaultTableModel of the PanelStudent's instance.
	 * @return The DefaultTableModel of the students.
	 */
	public DefaultTableModel getModelStudent() {
		return this.modelStudent;
	}
	
	/**
	 * Returns the JTable of the PanelStudents's instance.
	 * @return The JTable of the students.
	 */
	public JTable getTableStudent() {
		return this.tableStudent;
	}
	
	/**
	 * Returns the filepath of the PanelStudent's instance.
	 * @return The filepath of the students.
	 */
	public String getStudentFilePath() {
		return this.studentFilePath;
	}
	
	/**
	 * Sets the filepath of the PanelStudent's instance.
	 * @param pStudentFilePath The filepath of the students.
	 */
	public void setStudentFilePath(String pStudentFilePath) {
		this.studentFilePath = pStudentFilePath;
	}
	
	/**
	 * Returns the students.
	 * @return The students.
	 */
	public Student[] getStudents() {
		return this.dataStudent;
	}
	
	/**
	 * Sets the students.
	 * @param pDataStudent The students.
	 */
	public void setStudents(Student[] pDataStudent) {
		this.dataStudent = pDataStudent;
	}
	
}	