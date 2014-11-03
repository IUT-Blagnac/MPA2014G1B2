package library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import data.Project;
import data.Student;
import data.Subject;
import data.Teacher;

public class OptiParserTest extends TestCase {
	static String programmeATester = "OptiParser";
	Process executionProgrammeATester;
	BufferedReader ecranProgrammeATester;
	BufferedWriter clavierProgrammeATester;

	String finDeLigne = System.getProperty("line.separator");

	public static void main(String[] args) {
		if (args.length > 0)
			programmeATester = args[0];
		junit.textui.TestRunner.run(new TestSuite(OptiParserTest.class));
	}

	protected void setUp() throws IOException {
		executionProgrammeATester = Runtime.getRuntime().exec(
				"java.exe -cp .;bin " + programmeATester);
		ecranProgrammeATester = new BufferedReader(new InputStreamReader(
				executionProgrammeATester.getInputStream()));
		clavierProgrammeATester = new BufferedWriter(new OutputStreamWriter(
				executionProgrammeATester.getOutputStream()));
	}

	public void test_parseCSV() throws IOException {
		String[][] output = OptiParser.parseCSV("csv/etudiants2014_2015.csv");
		int i = 0;
		String[] expected = { "A", "LARROUY", "Etienne" };
		for (String s : expected) {
			assertEquals("Affiche : " + s, s, output[0][i]);
			i++;
		}

		i = 0;
		String[] expected2 = { "L", "VERON", "Vimel" };
		for (String s : expected2) {
			assertEquals("Affiche : " + s, s, output[47][i]);
			i++;
		}
	}

	public void test_writeLineToCSV() throws IOException {
		OptiParser.writeLineToCSV("dismiss;dismiss;dismiss", "csv/test.csv");
		OptiParser.writeLineToCSV("test;test;test", "csv/test.csv");
		String[][] output = OptiParser.parseCSV("csv/test.csv");
		String[] expected = { "test", "test", "test" };
		int i = 0;
		for (String s : expected) {
			assertEquals("Affiche : " + s, s, output[output.length - 1][i]);
			i++;
		}
	}
	
	public void test_saveToCSV_CtrlProject() throws IOException {
		// TEST DE CtrlProject
		
		Project[] allProj = new Project[2];
		allProj[0] = new Project("X", "42");
		allProj[1] = new Project("Y", "24");
		CtrlProject.saveToCSV(allProj, "csv/testProjects.csv");
		String[][] output = OptiParser.parseCSV("csv/testProjects.csv");
		int i = 0;
		String[] expected2 = { "X", "42" };
		for (String s : expected2) {
			assertEquals("Affiche : " + s, s, output[0][i]);
			i++;
		}
		i = 0;
		String[] expected3 = { "Y", "24" };
		for (String s : expected3) {
			assertEquals("Affiche : " + s, s, output[1][i]);
			i++;
		}
	}
	
	public void test_saveToCSV_CtrlStudent() throws IOException {
		// TEST DE CtrlStudent
		
		Student[] allEtu = new Student[2];
		allEtu[0] = new Student("Y", "NETTE", "Marion");
		allEtu[1] = new Student("Z", "COVERT", "Harry");
		CtrlStudent.saveToCSV(allEtu, "csv/testStudents.csv");
		String[][] output = OptiParser.parseCSV("csv/testStudents.csv");
		int i = 0;
		String[] expected2 = {"Y", "NETTE", "Marion"};
		for (String s : expected2) {
			assertEquals("Affiche : " + s, s, output[0][i]);
			i++;
		}
		i = 0;
		String[] expected3 = {"Z", "COVERT", "Harry"};
		for (String s : expected3) {
			assertEquals("Affiche : " + s, s, output[1][i]);
			i++;
		}
	}
	
	public void test_saveToCSV_CtrlTeacher() throws IOException {
		// TEST DE CtrlTeacher
		
		Teacher[] allProf = new Teacher[2];
		allProf[0] = new Teacher("Jean", "Beaunot");
		allProf[1] = new Teacher("Lara", "Clette");
		CtrlTeacher.saveToCSV(allProf, "csv/testTeachers.csv");
		String[][] output = OptiParser.parseCSV("csv/testTeachers.csv");
		int i = 0;
		String[] expected2 = {"Jean", "Beaunot"};
		for (String s : expected2) {
			assertEquals("Affiche : " + s, s, output[0][i]);
			i++;
		}
		i = 0;
		String[] expected3 = {"Lara", "Clette"};
		for (String s : expected3) {
			assertEquals("Affiche : " + s, s, output[1][i]);
			i++;
		}
	}
	
	public void test_saveToCSV_CtrlSubject() throws IOException {
		// TEST DE CtrlSubject
		
		Subject[] allSubj = new Subject[2];
		allSubj[0] = new Subject("42", "Duplicate", "Entreprise de clonage humain.");
		allSubj[1] = new Subject("24", "LikeIT", "Un jeu japonais en 3D.");
		CtrlSubject.saveToCSV(allSubj, "csv/testSubjects.csv");
		String[][] output = OptiParser.parseCSV("csv/testSubjects.csv");
		int i = 0;
		String[] expected2 = {"42", "Duplicate", "Entreprise de clonage humain."};
		for (String s : expected2) {
			assertEquals("Affiche : " + s, s, output[0][i]);
			i++;
		}
		i = 0;
		String[] expected3 = {"24", "LikeIT", "Un jeu japonais en 3D."};
		for (String s : expected3) {
			assertEquals("Affiche : " + s, s, output[1][i]);
			i++;
		}
	}
}
