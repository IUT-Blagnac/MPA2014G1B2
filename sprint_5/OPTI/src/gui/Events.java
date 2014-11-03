package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * Creates the Events' controler of panels and dialogs.
 * @author S3G1B2
 *
 */
public class Events implements ActionListener {
	private Interface mother;
	private PanelStudent panelStudent;
	private PanelTeacher panelTeacher;
	private PanelSubject panelSubject;
	private PanelProject panelProject;
	private DialogStudent dialogStudent;
	private DialogProject dialogProject;
	private DialogSubject dialogSubject;
	private DialogTeacher dialogTeacher;
	
	/**
	 * Creates an Events instance with the main frame.
	 * @param pMother The main frame.
	 */
	public Events(Interface pMother) {
		this.mother = pMother;		
	}
	
	/**
	 * Creates an Event instance with the main frame and a panel.
	 * @param pMother The main frame
	 * @param pPanel The panel attached to the event.
	 */
	public Events(Interface pMother, PanelStudent pPanel) {
		this.mother = pMother;
		this.panelStudent = pPanel;
	}
	
	/**
	 * Creates an Event instance with the main frame and a panel.
	 * @param pMother The main frame
	 * @param pPanel The panel attached to the event.
	 */
	public Events(Interface pMother, PanelTeacher pPanel) {
		this.mother = pMother;
		this.panelTeacher = pPanel;
	}
	
	/**
	 * Creates an Event instance with the main frame and a panel.
	 * @param pMother The main frame
	 * @param pPanel The panel attached to the event.
	 */
	public Events(Interface pMother, PanelSubject pPanel) {
		this.mother = pMother;
		this.panelSubject = pPanel;
	}
	
	/**
	 * Creates an Event instance with the main frame and a panel.
	 * @param pMother The main frame
	 * @param pPanel The panel attached to the event.
	 */
	public Events(Interface pMother, PanelProject pPanel) {
		this.mother = pMother;
		this.panelProject = pPanel;
	}
	
	/**
	 * Creates an Event instance with the panel father and a dialog.
	 * @param pPanelStudent The panel father.
	 * @param pDialog The dialog attached to the event.
	 */
	public Events(PanelStudent pPanelStudent, DialogStudent pDialog) {
		this.panelStudent = pPanelStudent;
		this.dialogStudent = pDialog;
	}
	
	/**
	 * Creates an Event instance with the panel father and a dialog.
	 * @param pPanelProject The panel father.
	 * @param pDialog The dialog attached to the event.
	 */
	public Events(PanelProject pPanelProject, DialogProject pDialog) {
		this.panelProject = pPanelProject;
		this.dialogProject = pDialog;
	}
	
	/**
	 * Creates an Event instance with the panel father and a dialog.
	 * @param pPanelSubject The panel father.
	 * @param pDialog The dialog attached to the event.
	 */
	public Events(PanelSubject pPanelSubject, DialogSubject pDialog) {
		this.panelSubject = pPanelSubject;
		this.dialogSubject = pDialog;
	}
	
	/**
	 * Creates an Event instance with the panel father and a dialog.
	 * @param pPanelTeacher The panel father.
	 * @param pDialog The dialog attached to the event.
	 */
	public Events(PanelTeacher pPanelTeacher, DialogTeacher pDialog) {
		this.panelTeacher = pPanelTeacher;
		this.dialogTeacher = pDialog;
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			
			if (e.getActionCommand().equals("QUIT")) {
				this.mother.dispose();
			}
			
			if (this.panelStudent != null) {
				if (e.getActionCommand().equals("NEW")) {
					this.panelStudent.createDialogStudent();
				}
				if (e.getActionCommand().equals("REM")) {
					this.panelStudent.removeStudent();
				}
				if (e.getActionCommand().equals("REST")) {
					this.panelStudent.restoreStudent();
				}
			}
			
			if (this.panelTeacher != null) {
				if (e.getActionCommand().equals("NEW")) {
					this.panelTeacher.createDialogTeacher();
				}
				if (e.getActionCommand().equals("REM")) {
					this.panelTeacher.removeTeacher();
				}
				if (e.getActionCommand().equals("REST")) {
					this.panelTeacher.restoreTeacher();
				}
			}
			
			if (this.panelProject != null) {
				if (e.getActionCommand().equals("NEW")) {
					this.panelProject.createDialogProject();
				}
				if (e.getActionCommand().equals("REM")) {
					this.panelProject.removeProject();
				}
				if (e.getActionCommand().equals("REST")) {
					this.panelProject.restoreProject();
				}
			}
			
			if (this.panelSubject != null) {
				if (e.getActionCommand().equals("NEW")) {
					this.panelSubject.createDialogSubject();
				}
				if (e.getActionCommand().equals("REM")) {
					this.panelSubject.removeSubject();
				}
				if (e.getActionCommand().equals("REST")) {
					this.panelSubject.restoreSubject();
				}
			}
			
			if (this.dialogStudent != null) {
				if (e.getActionCommand().equals("ADDSTUDENT")) {
					boolean validStudent=false;
					if (this.dialogStudent.getFather().isGroupFieldValid(this.dialogStudent)) {
						validStudent=true;
					}
					else {
						validStudent=false;
						JOptionPane.showMessageDialog(this.dialogStudent, "The group value is invalid.", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					
					if (this.dialogStudent.getFather().isLNameFieldValid(this.dialogStudent)) {
						validStudent=true;
					}
					else {
						validStudent=false;
						JOptionPane.showMessageDialog(this.dialogStudent, "The last name value is invalid.", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					
					if (this.dialogStudent.getFather().isFNameFieldValid(this.dialogStudent)) {
						validStudent=true;
					}
					else {
						validStudent=false;
						JOptionPane.showMessageDialog(this.dialogStudent, "The first name value is invalid.", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					if (validStudent) {
						this.dialogStudent.getFather().addStudent(this.dialogStudent);
						this.dialogStudent.dispose();
					}
					
				}
				if (e.getActionCommand().equals("CANCELSTUDENT")) {
					this.dialogStudent.dispose();
				}
			}
			
			if (this.dialogTeacher != null) {
				if (e.getActionCommand().equals("ADDTEACHER")) {
					boolean validTeacher=false;
					if (this.dialogTeacher.getFather().isFNameFieldValid(this.dialogTeacher)) {
						validTeacher=true;
					}
					else {
						validTeacher=false;
						JOptionPane.showMessageDialog(this.dialogTeacher, "The first name field is invalid.", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					if (this.dialogTeacher.getFather().isLNameFieldValid(this.dialogTeacher)) {
						validTeacher=true;
					}
					else {
						validTeacher=false;
						JOptionPane.showMessageDialog(this.dialogTeacher, "The last name field is invalid.", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					if (validTeacher) {
						this.dialogTeacher.getFather().addTeacher(this.dialogTeacher);
						this.dialogTeacher.dispose();
					}
				}
				
				if (e.getActionCommand().equals("CANCELTEACHER")) {
					this.dialogTeacher.dispose();
				}
			}
			
			if (this.dialogSubject != null) {
				if (e.getActionCommand().equals("ADDSUBJECT")) {
					boolean validSubject = false;
					if (this.dialogSubject.getFather().isIDFieldValid(this.dialogSubject)) {
						validSubject=true;
					}
					else {
						validSubject=false;
						JOptionPane.showMessageDialog(this.dialogSubject, "The ID field is invalid.", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					
					if (this.dialogSubject.getFather().isNameFieldValid(this.dialogSubject)) {
						validSubject=true;
					}
					else {
						validSubject=false;
						JOptionPane.showMessageDialog(this.dialogSubject, "The name field is invalid.", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					
					if (this.dialogSubject.getFather().isTitleFieldValid(this.dialogSubject)) {
						validSubject=true;
					}
					else {
						validSubject=false;
						JOptionPane.showMessageDialog(this.dialogSubject, "The title field is invalid.", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					
					if (validSubject) {
						this.dialogSubject.getFather().addSubject(this.dialogSubject);
						this.dialogSubject.dispose();
					}
				}
				
				if (e.getActionCommand().equals("CANCELSUBJECT")) {
					this.dialogSubject.dispose();
				}
			}
			
			if (this.dialogProject != null) {
				if (e.getActionCommand().equals("ADDPROJECT")) {
					boolean validProject = false;
					if (this.dialogProject.getFather().isGroupFieldValid(this.dialogProject)) {
						validProject=true;
					} 
					else {
						validProject=false;
						JOptionPane.showMessageDialog(this.dialogProject, "Group value is invalid.", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					
					if (this.dialogProject.getFather().isTitleFieldValid(this.dialogProject)) {
						validProject=true;
					}
					else {
						validProject=false;
						JOptionPane.showMessageDialog(this.dialogProject, "Title value is invalid.", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					
					if (validProject) {
						this.dialogProject.getFather().addProject(this.dialogProject);
						this.dialogProject.dispose();
					}
				}
				if (e.getActionCommand().equals("CANCELPROJECT")) {
					this.dialogProject.dispose();
				}
			}
			
		}
	}
	

}
