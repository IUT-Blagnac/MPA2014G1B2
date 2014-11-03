package gui;

import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class PanelTab extends JTabbedPane {
	private Interface mother;
	private PanelStudent panelStudent;
	private PanelTeacher panelTeacher;
	private PanelSubject panelSubject;
	private PanelProject panelProject;
	
	public PanelTab(Interface pMother) {
		super();
		this.mother = pMother;
		this.panelStudent = new PanelStudent(this.mother);
		this.panelTeacher = new PanelTeacher(this.mother);
		this.panelSubject = new PanelSubject(this.mother);
		this.panelProject = new PanelProject(this.mother);
		
		this.addTab("About", new PanelAbout());
		this.addTab("Students", this.panelStudent);
		this.addTab("Teachers", this.panelTeacher);
		this.addTab("Subjects", this.panelSubject);
		this.addTab("Projects", this.panelProject);
		
		//this.addTab("Students", new PanelStudent(this.mother));
		//this.addTab("Teachers", new PanelTeacher(this.mother));
		//this.addTab("Subjects", new PanelSubject(this.mother));
		//this.addTab("Projects", new PanelProject(this.mother));	
	}

	public Interface getMother() {
		return this.mother;
	}

	public PanelStudent getPanelStudent() {
		return this.panelStudent;
	}

	public PanelTeacher getPanelTeacher() {
		return this.panelTeacher;
	}

	public PanelSubject getPanelSubject() {
		return this.panelSubject;
	}

	public PanelProject getPanelProject() {
		return this.panelProject;
	}	
	
}
