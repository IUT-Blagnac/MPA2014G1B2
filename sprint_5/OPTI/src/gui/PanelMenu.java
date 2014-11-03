package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import library.CtrlProject;
import library.CtrlStudent;
import library.CtrlSubject;
import library.CtrlTeacher;

/**
 * Builds the panel which displays the menu of OPTI's main frame.
 * @author S3G1B2
 *
 */
@SuppressWarnings("serial")
public class PanelMenu extends JMenuBar implements ActionListener {
	private Interface mother;
	
	/**
	 * Creates a PanelMenu instance with the main frame as mother. 
	 * @param pMother The main frame.
	 */
	public PanelMenu(Interface pMother) {
		super();
		this.mother = pMother;
		JMenu menuEdit = new JMenu("File");
		JMenu subMenuStudent = new JMenu("Students");
		JMenu subMenuTeacher = new JMenu("Teachers");
		JMenu subMenuSubject = new JMenu("Subjects");
		JMenu subMenuProject = new JMenu("Projects");
		JMenuItem itemStudentOpen = new JMenuItem("Open");
		JMenuItem itemStudentSave = new JMenuItem("Save");
		JMenuItem itemStudentSaveAs = new JMenuItem("Save as...");
		JMenuItem itemTeacherOpen = new JMenuItem("Open");
		JMenuItem itemTeacherSave = new JMenuItem("Save");
		JMenuItem itemTeacherSaveAs = new JMenuItem("Save as...");
		JMenuItem itemSubjectOpen = new JMenuItem("Open");
		JMenuItem itemSubjectSave = new JMenuItem("Save");
		JMenuItem itemSubjectSaveAs = new JMenuItem("Save as...");
		JMenuItem itemProjectOpen = new JMenuItem("Open");
		JMenuItem itemProjectSave = new JMenuItem("Save");
		JMenuItem itemProjectSaveAs = new JMenuItem("Save as...");
		
		itemStudentOpen.addActionListener(this);
		itemStudentOpen.setActionCommand("OPENSTUDENT");
		itemStudentSave.addActionListener(this);
		itemStudentSave.setActionCommand("SAVESTUDENT");
		itemStudentSaveAs.addActionListener(this);
		itemStudentSaveAs.setActionCommand("SAVEASSTUDENT");
		
		itemTeacherOpen.addActionListener(this);
		itemTeacherOpen.setActionCommand("OPENTEACHER");
		itemTeacherSave.addActionListener(this);
		itemTeacherSave.setActionCommand("SAVEADTEACHER");
		itemTeacherSaveAs.addActionListener(this);
		itemTeacherSaveAs.setActionCommand("SAVEASTEACHER");
		
		itemSubjectOpen.addActionListener(this);
		itemSubjectOpen.setActionCommand("OPENSUBJECT");
		itemSubjectSave.addActionListener(this);
		itemSubjectSave.setActionCommand("SAVEADSUBJECT");
		itemSubjectSaveAs.addActionListener(this);
		itemSubjectSaveAs.setActionCommand("SAVEASSUBJECT");
		
		itemProjectOpen.addActionListener(this);
		itemProjectOpen.setActionCommand("OPENPROJECT");
		itemProjectSave.addActionListener(this);
		itemProjectSave.setActionCommand("SAVEADPROJECT");
		itemProjectSaveAs.addActionListener(this);
		itemProjectSaveAs.setActionCommand("SAVEASPROJECT");
		
		subMenuStudent.add(itemStudentOpen);
		subMenuStudent.add(itemStudentSave);
		subMenuStudent.add(itemStudentSaveAs);
		
		subMenuTeacher.add(itemTeacherOpen);
		subMenuTeacher.add(itemTeacherSave);
		subMenuTeacher.add(itemTeacherSaveAs);
		
		subMenuSubject.add(itemSubjectOpen);
		subMenuSubject.add(itemSubjectSave);
		subMenuSubject.add(itemSubjectSaveAs);
		
		subMenuProject.add(itemProjectOpen);
		subMenuProject.add(itemProjectSave);
		subMenuProject.add(itemProjectSaveAs);
		
		menuEdit.add(subMenuStudent);
		menuEdit.add(subMenuTeacher);
		menuEdit.add(subMenuSubject);
		menuEdit.add(subMenuProject);
				
		this.add(menuEdit);
	}
	
	/**
	 * Allows to open a students' file, creating a JFileChooser instance.
	 */
	private void menuOpenStudent() {
		int choice;
		String path = "etudiants2014_2015.csv";
		File dir = new File("csv/");
		File file = new File(path);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Comma-separated values (.csv)", "csv");
		JFileChooser open = new JFileChooser();
		open.setCurrentDirectory(dir);
		open.setSelectedFile(file);
		open.setFileFilter(filter);
		choice = open.showOpenDialog(this);
		if (choice == JFileChooser.APPROVE_OPTION) {
			try {
	          this.mother.getPanelTab().getPanelStudent().setStudentFilePath(open.getSelectedFile().getPath());
	          System.out.println(this.mother.getPanelTab().getPanelStudent().getStudentFilePath());
	          this.mother.getPanelTab().getPanelStudent().setStudents(CtrlStudent.getStudents(this.mother.getPanelTab().getPanelStudent().getStudentFilePath()));
	          this.mother.getPanelTab().getPanelStudent().restoreStudent();
	        }	catch (Exception e) {
	        		JOptionPane.showMessageDialog(this.mother, e.toString(),
	                    "File cannot be opened", JOptionPane.ERROR_MESSAGE);
	        }
			JOptionPane.showMessageDialog(this.mother, "Open done",
					"Open students file", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * Allows to open a teachers' file, creating a JFileChooser instance.
	 */
	private void menuOpenTeacher() {
		int choice;
		String path = "intervenants2014_2015.csv";
		File dir = new File("csv/");
		File file = new File(path);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Comma-separated values (.csv)", "csv");
		JFileChooser open = new JFileChooser();
		open.setCurrentDirectory(dir);
		open.setSelectedFile(file);
		open.setFileFilter(filter);
		choice = open.showOpenDialog(this);
		if (choice == JFileChooser.APPROVE_OPTION) {
			try {
	          this.mother.getPanelTab().getPanelTeacher().setTeacherFilePath(open.getSelectedFile().getPath());
	          this.mother.getPanelTab().getPanelTeacher().setTeachers(CtrlTeacher.getTeachers((this.mother.getPanelTab().getPanelTeacher().getTeacherFilePath())));
	          this.mother.getPanelTab().getPanelTeacher().restoreTeacher();
	        }	catch (Exception e) {
	        		JOptionPane.showMessageDialog(this.mother, e.toString(),
	                    "File cannot be opened", JOptionPane.ERROR_MESSAGE);
	        }
			JOptionPane.showMessageDialog(this.mother, "Open done",
					"Open teacher file", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * Allows to open a subjects' file, creating a JFileChooser instance.
	 */
	private void menuOpenSubject() {
		int choice;
		String path = "sujets2014_2015.csv";
		File dir = new File("csv/");
		File file = new File(path);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Comma-separated values (.csv)", "csv");
		JFileChooser open = new JFileChooser();
		open.setCurrentDirectory(dir);
		open.setSelectedFile(file);
		open.setFileFilter(filter);
		choice = open.showOpenDialog(this);
		if (choice == JFileChooser.APPROVE_OPTION) {
			try {
	          this.mother.getPanelTab().getPanelSubject().setSubjectFilePath(open.getSelectedFile().getPath());
	          this.mother.getPanelTab().getPanelSubject().setSubjects(CtrlSubject.getSubjects((this.mother.getPanelTab().getPanelSubject().getSubjectFilePath())));
	          this.mother.getPanelTab().getPanelSubject().restoreSubject();
	        }	catch (Exception e) {
	        		JOptionPane.showMessageDialog(this.mother, e.toString(),
	                    "File cannot be opened", JOptionPane.ERROR_MESSAGE);
	        }
			JOptionPane.showMessageDialog(this.mother, "Open done",
					"Open subjects file", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * Allows to open a projects' file, creating a JFileChooser instance.
	 */
	private void menuOpenProject() {
		int choice;
		String path = "projets2014_2015.csv";
		File dir = new File("csv/");
		File file = new File(path);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Comma-separated values (.csv)", "csv");
		JFileChooser open = new JFileChooser();
		open.setCurrentDirectory(dir);
		open.setSelectedFile(file);
		open.setFileFilter(filter);
		choice = open.showOpenDialog(this);
		if (choice == JFileChooser.APPROVE_OPTION) {
			try {
	          this.mother.getPanelTab().getPanelProject().setProjectFilePath(open.getSelectedFile().getPath());
	          this.mother.getPanelTab().getPanelProject().setProjects(CtrlProject.getProjects((this.mother.getPanelTab().getPanelProject().getProjectFilePath())));
	          this.mother.getPanelTab().getPanelProject().restoreProject();
	        }	catch (Exception e) {
	        		JOptionPane.showMessageDialog(this.mother, e.toString(),
	                    "File cannot be opened", JOptionPane.ERROR_MESSAGE);
	        }
			JOptionPane.showMessageDialog(this.mother, "Open done",
					"Open subjects file", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * Allows to save a students' file, creating a JFileChooser instance.
	 */
	public void menuSaveAsStudent() {
		int choice;
		String path = "etudiants2014_2015.csv";
		File dir = new File("csv/");
		File file = new File(path);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Comma-separated values (.csv)", "csv");
		JFileChooser save = new JFileChooser();
		
		save.setCurrentDirectory(dir);
		save.setSelectedFile(file);
		save.setFileFilter(filter);
		choice = save.showSaveDialog(this.mother);
		if (choice == JFileChooser.APPROVE_OPTION) {
			file = save.getSelectedFile();
			try {
				this.mother.getPanelTab().getPanelStudent().exportAsStudent(file.getPath());
				this.mother.getPanelTab().getPanelStudent().setStudentFilePath(file.getPath());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this.mother, e.toString(),
	                    "File error", JOptionPane.ERROR_MESSAGE);
			}
			JOptionPane.showMessageDialog(this.mother, "Save done",
					"Save students", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * Allows to save a teachers' file, creating a JFileChooser instance.
	 */
	public void menuSaveAsTeacher() {
		int choice;
		String path = "intervenants2014_2015.csv";
		File dir = new File("csv/");
		File file = new File(path);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Comma-separated values (.csv)", "csv");
		JFileChooser save = new JFileChooser();
		
		save.setCurrentDirectory(dir);
		save.setSelectedFile(file);
		save.setFileFilter(filter);
		choice = save.showSaveDialog(this.mother);
		if (choice == JFileChooser.APPROVE_OPTION) {
			file = save.getSelectedFile();
			try {
				this.mother.getPanelTab().getPanelTeacher().exportAsTeacher(file.getPath());
				this.mother.getPanelTab().getPanelTeacher().setTeacherFilePath(file.getPath());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this.mother, e.toString(),
						"File error", JOptionPane.ERROR_MESSAGE);
			}
			JOptionPane.showMessageDialog(this.mother, "Save done",
					"Save teachers", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * Allows to save a subjects' file, creating a JFileChooser instance.
	 */
	public void menuSaveAsSubject() {
		int choice;
		String path = "sujets2014_2015.csv";
		File dir = new File("csv/");
		File file = new File(path);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Comma-separated values (.csv)", "csv");
		JFileChooser save = new JFileChooser();
		
		save.setCurrentDirectory(dir);
		save.setSelectedFile(file);
		save.setFileFilter(filter);
		choice = save.showSaveDialog(this.mother);
		if (choice == JFileChooser.APPROVE_OPTION) {
			file = save.getSelectedFile();
			try {
				this.mother.getPanelTab().getPanelSubject().exportAsSubject(file.getPath());
				this.mother.getPanelTab().getPanelSubject().setSubjectFilePath(file.getPath());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this.mother, e.toString(),
	                    "File error", JOptionPane.ERROR_MESSAGE);
			}
			JOptionPane.showMessageDialog(this.mother, "Save done",
					"Save subjects", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * Allows to save a projects' file, creating a JFileChooser instance.
	 */
	public void menuSaveAsProject() {
		int choice;
		String path = "projets2014_2015.csv";
		File dir = new File("csv/");
		File file = new File(path);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Comma-separated values (.csv)", "csv");
		JFileChooser save = new JFileChooser();
		
		save.setCurrentDirectory(dir);
		save.setSelectedFile(file);
		save.setFileFilter(filter);
		choice = save.showSaveDialog(this.mother);
		if (choice == JFileChooser.APPROVE_OPTION) {
			file = save.getSelectedFile();
			try {
				this.mother.getPanelTab().getPanelProject().exportAsProject(file.getPath());
				this.mother.getPanelTab().getPanelProject().setProjectFilePath(file.getPath());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this.mother, e.toString(),
	                    "File error", JOptionPane.ERROR_MESSAGE);
			}
			JOptionPane.showMessageDialog(this.mother, "Save done",
					"Save projects", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Controls the OPTI's menu events.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().contains("STUDENT")) {
			if (e.getActionCommand().equals("OPENSTUDENT")) {
				menuOpenStudent();
			}
			if (e.getActionCommand().equals("SAVESTUDENT")) {
				this.mother.getPanelTab().getPanelStudent().exportStudent();
			}
			if (e.getActionCommand().equals("SAVEASSTUDENT")) {
				menuSaveAsStudent();
			}
		}
		
		if (e.getActionCommand().contains("TEACHER")) {
			if (e.getActionCommand().equals("OPENTEACHER")) {
				menuOpenTeacher();
			}
			if (e.getActionCommand().equals("SAVETEACHER")) {
				this.mother.getPanelTab().getPanelTeacher().exportTeacher();
			}
			if (e.getActionCommand().equals("SAVEASTEACHER")) {
				menuSaveAsTeacher();
			}
		}
		if (e.getActionCommand().contains("SUBJECT")) {
			if (e.getActionCommand().equals("OPENSUBJECT")) {
				menuOpenSubject();
			}
			if (e.getActionCommand().equals("SAVESUBJECT")) {
				this.mother.getPanelTab().getPanelSubject().exportSubject();
			}
			if (e.getActionCommand().equals("SAVEASSUBJECT")) {
				menuSaveAsSubject();
			}
		}
		
		if (e.getActionCommand().contains("PROJECT")) {
			if (e.getActionCommand().equals("OPENPROJECT")) {
				menuOpenProject();
			}
			if (e.getActionCommand().equals("SAVEPROJECT")) {
				this.mother.getPanelTab().getPanelProject().exportProject();
			}
			if (e.getActionCommand().equals("SAVEASPROJECT")) {
				menuSaveAsProject();
			}
		}
	}
}
