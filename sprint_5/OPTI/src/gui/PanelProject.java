package gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import library.CtrlProject;
import data.Project;

/**
 * Builds the panel which displays the projects' table.
 * @author S3G1B2
 *
 */
@SuppressWarnings("serial")
public class PanelProject extends JPanel {
	private Interface mother;
	private DefaultTableModel modelProject;
	private JTable tableProject;
	private Project[] dataProject;
	private String[] headerProject;
	private JButton butAddProject, butRemProject, butRestProject;
	private String projectFilePath;
	
	/**
	 * Creates a PanelProject instance with the main frame as mother.
	 * @param pMother The main frame.
	 */
	public PanelProject(Interface pMother) {
		super();
		this.mother = pMother;
		this.projectFilePath = "csv\\projets2014_2015.csv";
		this.headerProject = CtrlProject.getHeader();
		this.dataProject = CtrlProject.getProjects(this.projectFilePath);
		this.butAddProject = new JButton("Add");
		this.butRemProject = new JButton("Remove");
		this.butRestProject = new JButton("Restore");
		this.butAddProject.addActionListener(new Events(this.mother,this));
		this.butAddProject.setActionCommand("NEW");
		this.butAddProject.setPreferredSize(this.butRemProject.getPreferredSize());
		this.butRemProject.addActionListener(new Events(this.mother,this));
		this.butRemProject.setActionCommand("REM");
		this.butRestProject.addActionListener(new Events(this.mother,this));
		this.butRestProject.setActionCommand("REST");
		
		loadTable();
		JScrollPane scroll = new JScrollPane(this.tableProject);		
		
		this.add(scroll, BorderLayout.CENTER);		
		this.add(this.butAddProject, BorderLayout.SOUTH);
		this.add(this.butRemProject, BorderLayout.SOUTH);
		this.add(this.butRestProject, BorderLayout.SOUTH);
	}
	
	/**
	 * Loads the JTable containing the projects.
	 */
	public void loadTable() {
		this.modelProject = new DefaultTableModel(this.headerProject, this.dataProject.length);
		this.tableProject = new JTable(this.modelProject);
		this.tableProject.setFont(new Font("Tahoma", Font.BOLD, 12));
	
		for (int i=0; i<dataProject.length; i++) {
			this.modelProject.setValueAt(this.dataProject[i].getGroup(), i, 0);
			this.modelProject.setValueAt(this.dataProject[i].getSubject(), i, 1);	
		}
	}
	
	/**
	 * Adds a project to its JTable.
	 * @param pDialogProject The DialogProject instance as son.
	 * @see DialogProject
	 */
	public void addProject(DialogProject pDialogProject) {
		this.modelProject.addRow(new String[2]);
		this.modelProject.setValueAt(pDialogProject.getFieldGroup().getText().toUpperCase(), this.modelProject.getRowCount()-1, 0);
		this.modelProject.setValueAt(pDialogProject.getFieldTitle().getText(), this.modelProject.getRowCount()-1, 1);
	}
	
	/**
	 * Removes projects from their JTable.
	 * @exception Caught and not returned.
	 */
	public void removeProject() {
		try {
			for (int i=0; i<this.tableProject.getRowCount(); i++) {
				while (this.tableProject.isRowSelected(i)) {
					this.modelProject.removeRow(i);
				}
			}
		} catch (Exception e) {}
	}
	
	/**
	 * Restores the projects' JTable.
	 * @exception Caught and not returned.
	 */
	public void restoreProject() {
		this.modelProject.setRowCount(this.dataProject.length);
		try {
			for (int i=0; i<dataProject.length; i++) {
				this.modelProject.setValueAt(this.dataProject[i].getGroup(), i, 0);	
				this.modelProject.setValueAt(this.dataProject[i].getSubject(), i, 1);
			}
		} catch (Exception e) {}
	}

	/**
	 * Created a DialogProject instance.
	 * @see DialogProject
	 */
	public void createDialogProject() {
		DialogProject dial = new DialogProject(this.mother, this);
		dial.setVisible(true);
	}
	
	/**
	 * Controls the data capture of the group field.
	 * @param pDialProject The DialogProject instance as son.
	 * @return True if the data capture is good, False otherwise.
	 */
	public boolean isGroupFieldValid(DialogProject pDialogProject) {
		if ( 	(pDialogProject.getFieldGroup().getText().length()==1) &&
				(pDialogProject.getFieldGroup().getText().matches("[a-z]")) 
			){
					return true;
		}
		else {
			return false;
		}	
	}
	
	/**
	 * Controls the data capture of the title field.
	 * @param pDialProject The DialogProject instance as son.
	 * @return True if the data capture is good, False otherwise.
	 */
	public boolean isTitleFieldValid(DialogProject pDialogProject) {
		if ( 	(pDialogProject.getFieldTitle().getText().length()>0) &&  
				(pDialogProject.getFieldTitle().getText().length()<3) &&
				(pDialogProject.getFieldTitle().getText().matches("[0-9]+"))
			){
					return true;
		}
		else {
			return false;
		}	
	}
	
	/**
	 * Exports projects into a file.
	 * @see CtrlProject
	 */
	public void exportProject() {
		Project[] data = new Project[this.modelProject.getRowCount()];
		
		for (int i=0; i<data.length; i++) {
			data[i] = new Project(this.modelProject.getValueAt(i, 0).toString(), 
									this.modelProject.getValueAt(i, 1).toString());
		}
		
		try {
			CtrlProject.saveToCSV(data);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.mother, "An error has occured.", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Exports projects into a chosen file.
	 * @param pPath The file's path.
	 * @see CtrlProject
	 */
	public void exportAsProject(String pPath) {
		Project[] data = new Project[this.modelProject.getRowCount()];
		
		for (int i=0; i<data.length; i++) {
			data[i] = new Project(this.modelProject.getValueAt(i, 0).toString(), 
									this.modelProject.getValueAt(i, 1).toString());
		}
		
		try {
			CtrlProject.saveToCSV(data, pPath);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.mother, "An error has occured.", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Returns the DefaultTableModel of the PanelProject's instance.
	 * @return The DefaultTableModel of the projects.
	 */
	public DefaultTableModel getModelProject() {
		return this.modelProject;
	}
	
	/**
	 * Returns the JTable of the PanelProject's instance.
	 * @return The JTable of the projects.
	 */
	public JTable getTableProject() {
		return this.tableProject;
	}
	
	/**
	 * Returns the filepath of the PanelProject's instance.
	 * @return The filepath of the projects.
	 */
	public String getProjectFilePath() {
		return this.projectFilePath;
	}
	
	/**
	 * Sets the filepath of the PanelProject's instance.
	 * @param pProjectFilePath The filepath of the projects.
	 */
	public void setProjectFilePath(String pProjectFilePath) {
		this.projectFilePath = pProjectFilePath;
	}
	
	/**
	 * Returns the projects.
	 * @return The projects.
	 */
	public Project[] getProjects() {
		return this.dataProject;
	}
	
	/**
	 * Sets the projects.
	 * @param pDataProject The projects.
	 */
	public void setProjects(Project[] pDataProject) {
		this.dataProject = pDataProject;
	}
	
}