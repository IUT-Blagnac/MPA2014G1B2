package test;

import gui.MakeOPTIWeb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class MakeOPTIWebTest extends TestCase {

	static String programmeATester = "OptiParser";
	Process executionProgrammeATester;
	BufferedReader ecranProgrammeATester;
	BufferedWriter clavierProgrammeATester;

	String finDeLigne = System.getProperty("line.separator");

	public static void main(String[] args) {
		if (args.length > 0)
			programmeATester = args[0];
		junit.textui.TestRunner.run(new TestSuite(MakeOPTIWebTest.class));
	}

	protected void setUp() throws IOException {
		executionProgrammeATester = Runtime.getRuntime().exec(
				"java.exe -cp .;bin " + programmeATester);
		ecranProgrammeATester = new BufferedReader(new InputStreamReader(
				executionProgrammeATester.getInputStream()));
		clavierProgrammeATester = new BufferedWriter(new OutputStreamWriter(
				executionProgrammeATester.getOutputStream()));
	}

	public void test_writeToFile() throws IOException {
		MakeOPTIWeb.writeToFile("../test/ourTests/contentToInsert.txt", "../test/ourTests/testWriteToFileOutput.txt", false);

		Scanner sc = new Scanner(new File("../test/ourTests/contentToInsert.txt"));
		String strContentToInsert = "";
		Scanner sc2 = new Scanner(new File("../test/ourTests/testWriteToFileOutput.txt"));
		String strContentInserted = "";
		while(sc.hasNextLine()||sc2.hasNextLine()){
			try {
				strContentToInsert = sc.nextLine();
			} catch (Exception e){}
			try {
				strContentInserted = sc2.nextLine();
			} catch (Exception e){}
		    assertEquals("Affiche : " + strContentInserted, strContentToInsert, strContentInserted);
		}
	}
	
	public void test_writeProjects() throws IOException {
		MakeOPTIWeb.writeProjects("../test/ourTests/testWriteProjectOutput.txt", false);
	}
	
	

}
