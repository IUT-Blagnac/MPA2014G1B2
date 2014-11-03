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

import library.CtrlSubject;
import data.Subject;

/**
 * Builds the panel which displays the subjects' table.
 * @author S3G1B2
 *
 */
@SuppressWarnings("serial")
public class PanelSubject extends JPanel {
	private Interface mother;
	private DefaultTableModel modelSubject;
	private JTable tableSubject;
	private Subject[] dataSubject;
	private String[] headerSubject;
	private JButton butAddSubject, butRemSubject, butRestSubject;
	private String subjectFilePath;
		
	/**
	 * Creates a PanelSubjectr instance with the main frame as mother.
	 * @param pMother The main frame.
	 */
	public PanelSubject(Interface pMother) {
		super();
		this.mother = pMother;
		this.subjectFilePath = "csv\\sujets2014_2015.csv"; 
		this.headerSubject = CtrlSubject.getHeader();
		this.dataSubject = CtrlSubject.getSubjects(this.subjectFilePath);
		this.butAddSubject = new JButton("Add");
		this.butRemSubject = new JButton("Remove");
		this.butRestSubject = new JButton("Restore");
		this.butAddSubject.addActionListener(new Events(this.mother,this));
		this.butAddSubject.setActionCommand("NEW");
		this.butAddSubject.setPreferredSize(this.butRemSubject.getPreferredSize());
		this.butRemSubject.addActionListener(new Events(this.mother,this));
		this.butRemSubject.setActionCommand("REM");
		this.butRestSubject.addActionListener(new Events(this.mother,this));
		this.butRestSubject.setActionCommand("REST");

		loadTable();
		JScrollPane scroll = new JScrollPane(this.tableSubject);		
		
		this.add(scroll, BorderLayout.CENTER);		
		this.add(this.butAddSubject, BorderLayout.SOUTH);
		this.add(this.butRemSubject, BorderLayout.SOUTH);
		this.add(this.butRestSubject, BorderLayout.SOUTH);
	}
	
	/**
	 * Loads the JTable containing the subjects.
	 */
	public void loadTable() {
		this.modelSubject = new DefaultTableModel(this.headerSubject, this.dataSubject.length);
		this.tableSubject = new JTable(this.modelSubject);
		this.tableSubject.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.tableSubject.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	
		for (int i=0; i<dataSubject.length; i++) {
			this.modelSubject.setValueAt(this.dataSubject[i].getId(), i, 0);
			this.modelSubject.setValueAt(this.dataSubject[i].getName(), i, 1);
			this.modelSubject.setValueAt(this.dataSubject[i].getTitle(), i, 2);		
		}
		
		TableColumn colID = this.tableSubject.getColumnModel().getColumn(0);  
		TableColumn colNom = this.tableSubject.getColumnModel().getColumn(1);
		TableColumn colTitre = this.tableSubject.getColumnModel().getColumn(2);
		
	    colID.setPreferredWidth(30);
	    colNom.setPreferredWidth(100);
	    colTitre.setPreferredWidth(600);
	}
	
	/**
	 * Adds a subject to its JTable.
	 * @param pDialogSubject The DialogSubject instance as son.
	 * @see DialogSubject
	 */
	public void addSubject(DialogSubject pDialogSubject) {
		this.modelSubject.addRow(new String[3]);
		this.modelSubject.setValueAt(pDialogSubject.getFieldID().getText(), this.modelSubject.getRowCount()-1, 0);
		this.modelSubject.setValueAt(pDialogSubject.getFieldName().getText(), this.modelSubject.getRowCount()-1, 1);
		this.modelSubject.setValueAt(pDialogSubject.getFieldTitle().getText(), this.modelSubject.getRowCount()-1, 2);
	}
	
	/**
	 * Removes subjects from their JTable.
	 * @exception Caught and not returned.
	 */
	public void removeSubject() {
		try {
			for (int i=0; i<this.tableSubject.getRowCount(); i++) {
				while (this.tableSubject.isRowSelected(i)) {
					this.modelSubject.removeRow(i);
				}
			}
		} catch (Exception e) {}
	}
	
	/**
	 * Restores the subjects' JTable.
	 * @exception Caught and not returned.
	 */
	public void restoreSubject() {
		this.modelSubject.setRowCount(this.dataSubject.length);
		try {
			for (int i=0; i<dataSubject.length; i++) {
				this.modelSubject.setValueAt(this.dataSubject[i].getId(), i, 0);	
				this.modelSubject.setValueAt(this.dataSubject[i].getName(), i, 1);
				this.modelSubject.setValueAt(this.dataSubject[i].getTitle(), i, 2);		
			}
		} catch (Exception e) {}
	}
	
	/**
	 * Created a DialogSubject instance.
	 * @see DialogSubject
	 */
	public void createDialogSubject() {
		DialogSubject dial = new DialogSubject(this.mother, this);
		dial.setVisible(true);
	}

	/**
	 * Controls the data capture of the id name field.
	 * @param pDialogSubject The DialogSubject instance as son.
	 * @return True if the data capture is good, False otherwise.
	 */
	public boolean isIDFieldValid(DialogSubject pDialogSubject) {
		if ( 	(pDialogSubject.getFieldID().getText().length()>0) &&
				(pDialogSubject.getFieldID().getText().length()<4) &&
				(pDialogSubject.getFieldID().getText().matches("[0-9]*"))
				) {
					return true;
		}
		else {
			return false;
		}	
	}
	
	/**
	 * Controls the data capture of the name field.
	 * @param pDialogSubject The DialogSubject instance as son.
	 * @return True if the data capture is good, False otherwise.
	 */
	public boolean isNameFieldValid(DialogSubject pDialogSubject) {
		if ( 	(pDialogSubject.getFieldName().getText().length()>0) &&
				(pDialogSubject.getFieldName().getText().length()<30) &&
				(pDialogSubject.getFieldName().getText().matches("[a-z]+")) 
				) {
					return true;
		}
		else {
			return false;
		}	
	}
	
	/**
	 * Exports subjects into a file.
	 * @see CtrlSubject
	 */
	public boolean isTitleFieldValid(DialogSubject pDialogSubject) {
		if ( 	(pDialogSubject.getFieldTitle().getText().length()>0) ) {
					return true;
		}
		else {
			return false;
		}	
	}

	/**
	 * Exports subjects into a file.
	 * @see CtrlSubject
	 */
	public void exportSubject() {
		Subject[] data = new Subject[this.modelSubject.getRowCount()];
		
		for (int i=0; i<data.length; i++) {
			data[i] = new Subject(this.modelSubject.getValueAt(i, 0).toString(), 
									this.modelSubject.getValueAt(i, 1).toString(),
									this.modelSubject.getValueAt(i, 2).toString());
		}
		
		try {
			CtrlSubject.saveToCSV(data);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.mother, "An error has occured.", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	/**
	 * Exports subjects into a chosen file.
	 * @param pPath The file's path.
	 * @see CtrlSubject
	 */
	public void exportAsSubject(String pPath) {
		Subject[] data = new Subject[this.modelSubject.getRowCount()];
		
		for (int i=0; i<data.length; i++) {
			data[i] = new Subject(this.modelSubject.getValueAt(i, 0).toString(), 
									this.modelSubject.getValueAt(i, 1).toString(),
									this.modelSubject.getValueAt(i, 2).toString());
		}
		
		try {
			CtrlSubject.saveToCSV(data, pPath);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.mother, "An error has occured.", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Returns the DefaultTableModel of the PanelSubject's instance.
	 * @return The DefaultTableModel of the subjects.
	 */
	public DefaultTableModel getModelSubject() {
		return this.modelSubject;
	}
	
	/**
	 * Returns the JTable of the PanelSubject's instance.
	 * @return The JTable of the subjects.
	 */
	public JTable getTableSubject() {
		return this.tableSubject;
	}
	
	/**
	 * Returns the filepath of the PanelSubject's instance.
	 * @return The filepath of the subjects.
	 */
	public String getSubjectFilePath() {
		return this.subjectFilePath;
	}
	
	/**
	 * Sets the filepath of the PanelSubject's instance.
	 * @param pSubjectFilePath The filepath of the subjects.
	 */
	public void setSubjectFilePath(String pSubjectFilePath) {
		this.subjectFilePath = pSubjectFilePath;
	}
	
	/**
	 * Returns the subjects.
	 * @return The subjects.
	 */
	public Subject[] getSubjects() {
		return this.dataSubject;
	}
	
	/**
	 * Sets the subjects.
	 * @param pDataSubject The subjects.
	 */
	public void setSubjects(Subject[] pDataSubject) {
		this.dataSubject = pDataSubject;
	}
	
}
