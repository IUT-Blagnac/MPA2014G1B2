package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;

import data.Project;
import data.Student;
import data.Teacher;
import data.Topic;
import lib.CtrlProject;
import lib.CtrlStudent;
import lib.CtrlTeacher;
import lib.CtrlTopic;

public class MakeOPTIWeb
{
	static StringBuilder htmlSource = new StringBuilder();
	
	/**
	 * This method reads file content then writes it to an output file.
	 * @param inputFilePath The source file
	 * @param outputFilePath The target file
	 * @param append Should it erase file content or not ?
	 * @throws IOException
	 */
	public static void writeToFile(String inputFilePath, String outputFilePath, boolean append) throws IOException
	{
		BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(inputFilePath))));
		PrintStream fileWriter = new PrintStream(new FileOutputStream(new File(outputFilePath), append));
		String curLine = null;
		
		while ((curLine = fileReader.readLine()) != null)
		{
			fileWriter.println(curLine);
		}
		
		fileReader.close();
		fileWriter.close();
	}
	
	/**
	 * This method reads projects file content then writes it to an output file.
	 * @param outputFilePath The target file
	 * @param append Should it erase file content or not ?
	 * @throws IOException
	 */
	public static void writeProjects(String outputFilePath, boolean append) throws IOException
	{
		writeToFile("html_templates/projets/header.html", outputFilePath, true);
		PrintStream fileWriter = new PrintStream(new FileOutputStream(new File(outputFilePath), append), false, "UTF-8");
		
		Project[] allProjects = CtrlProject.getProjects("../test/projets2014_2015.csv");
		
		for (int i = 0; i < allProjects.length; i++)
		{
			// a faire...
			fileWriter.println("<li>");
			fileWriter.println("<p>");
			fileWriter.print("<b>[" + allProjects[i].getpTopic().getNom() + "]</b> " + allProjects[i].getpTopic().getTitre() + "\n");
			fileWriter.println("</p>");
			fileWriter.println("<p>");
			fileWriter.println("<b>Client :</b> " + allProjects[i].getClient().getpLName().toUpperCase() + " " + allProjects[i].getClient().getpFName());
			fileWriter.println("</p>");
			fileWriter.println("<p>");
			fileWriter.println("<b>Superviseur :</b> " + allProjects[i].getSupervisor().getpLName().toUpperCase() + " " + allProjects[i].getSupervisor().getpFName());
			fileWriter.println("</p>");
			fileWriter.println("<p>");
			fileWriter.println("<b>Groupe " + allProjects[i].getpGroup().getLibelle() + " :</b> " + CtrlStudent.groupStudentsString("../test/etudiants2014_2015.csv", allProjects[i].getpGroup().getLibelle()));
			fileWriter.println("</p>");
			fileWriter.println("</li>");
		}
		writeToFile("html_templates/projets/footer.html", "OPTIweb.html", true);
		
		fileWriter.close();
	}
	
	/**
	 * This method reads students file content then writes it to an output file.
	 * @param outputFilePath The target file
	 * @param append Should it erase file content or not ?
	 * @throws IOException
	 */
	public static void writeStudents(String outputFilePath, boolean append) throws IOException
	{
		writeToFile("html_templates/etudiants/header.html", outputFilePath, true);
		
		PrintStream fileWriter = new PrintStream(new FileOutputStream(new File(outputFilePath), append), false, "UTF-8");
		
		Student[] allStudents = CtrlStudent.getStudents("../test/etudiants2014_2015.csv");
		
		fileWriter.println("<li data-role=\"list-divider\">");
		fileWriter.println("Etudiant<span class=\"ui-li-count\" title=\"Groupe\" style=\"right: 40px !important;\">Groupe</span>");
		fileWriter.println("</li>");
		
		for (int i = 0; i < allStudents.length; i++)
		{
			fileWriter.println("<li data-find=\"" + allStudents[i].getFname() + " " + allStudents[i].getLname().toUpperCase() + "\">");
			fileWriter.print("<a href=\"#projets\">" + allStudents[i].getLname().toUpperCase() + " " + allStudents[i].getFname() + "<span class=\"ui-li-count\" title=\"Groupe\">Groupe " + allStudents[i].getStudGroup().getLibelle() + "</span>\n");
			fileWriter.println("</a>");
			fileWriter.println("</li>");
		}
		
		writeToFile("html_templates/etudiants/footer.html", "OPTIweb.html", true);
		
		fileWriter.close();
	}
	
	/**
	 * This method reads topics file content, then writes it to an output file
	 * @param outputFilePath The target file
	 * @param append Should it erase file content or not ?
	 * @throws IOException
	 */
	public static void writeTopics(String outputFilePath, boolean append) throws IOException
	{
		writeToFile("html_templates/sujets/header.html", outputFilePath, true);
		
		PrintStream fileWriter = new PrintStream(new FileOutputStream(new File(outputFilePath), append), false, "UTF-8");
		
		Topic[] allTopics = CtrlTopic.getTopics("../test/sujets2014_2015.csv");
		
		fileWriter.println("<li data-role=\"list-divider\">");
		fileWriter.println("Sujet<span class=\"ui-li-count\" title=\"Groupe\" style=\"right: 40px !important;\">Groupe</span>");
		fileWriter.println("</li>");
		
		for (int i = 0; i < allTopics.length; i++)
		{
			fileWriter.println("<li data-find=\"[" + allTopics[i].getNom() + "]\">");
			fileWriter.println("<a href=\"#projets\">[" + allTopics[i].getNom() + "]<br />");
			fileWriter.println("<div style=\"white-space: normal;\">");
			fileWriter.println("<span><b>" + allTopics[i].getTitre() + "</b></span>");
			fileWriter.println("<span class=\"ui-li-count\">" + CtrlProject.findGroupAssignedToProject("../test/projets2014_2015.csv", Integer.parseInt(allTopics[i].getId())) + "</span>");
			fileWriter.println("</div>");
			fileWriter.println("</a>");
			fileWriter.println("</li>");
		}
		
		writeToFile("html_templates/sujets/footer.html", outputFilePath, true);
		
		fileWriter.close();
	}
	
	/**
	 * This method reads teachers file content, then writes it to an output file
	 * @param outputFilePath The target file
	 * @param append Should it erase file content or not ?
	 * @throws IOException
	 */
	public static void writeTeachers(String outputFilePath, boolean append) throws IOException
	{
		writeToFile("html_templates/intervenants/header.html", outputFilePath, true);
		
		PrintStream fileWriter = new PrintStream(new FileOutputStream(new File(outputFilePath), append), false, "UTF-8");
		
		Teacher[] allTeachers = CtrlTeacher.getTeachers("../test/intervenants2014_2015.csv");
		
		fileWriter.println("<li data-role=\"list-divider\">");
		fileWriter.println("Intervenant<span class=\"ui-li-count\" style=\"right: 110px !important;\" title=\"Client\">Client</span><span class=\"ui-li-count\" title=\"Superviseur\">Superviseur</span>");
		fileWriter.println("</li>");
		
		for (int i = 0; i < allTeachers.length; i++)
		{
			fileWriter.println("<li data-find=\"" + allTeachers[i].getpLName().toUpperCase() + " " + allTeachers[i].getpFName() + "\">");
			fileWriter.println("<a href=\"#projets\">");
			fileWriter.println(allTeachers[i].getpLName().toUpperCase() + " " + allTeachers[i].getpFName());
			fileWriter.println("<span class=\"ui-li-count\" style=\"right: 120px !important;\" title=\"Client\">" + CtrlProject.getNumberOfProjectsWhereClient("../test/projets2014_2015.csv", allTeachers[i].getId()) + "</span>");
			fileWriter.println("<span class=\"ui-li-count\" title=\"Superviseur\">" + CtrlProject.getNumberOfProjectsWhereSupervisor("../test/projets2014_2015.csv", allTeachers[i].getId()) + "</span>");
			fileWriter.println("</a>");
			fileWriter.println("</li>");
		}
		
		writeToFile("html_templates/intervenants/footer.html", outputFilePath, true);
	}
	
	public static void main(String[] args) throws IOException
	{
		writeToFile("html_templates/header.html", "OPTIweb.html", false); // Header
		writeToFile("html_templates/index.html", "OPTIweb.html", true); // Home page
		
		writeProjects("OPTIweb.html", true);
		
		writeTopics("OPTIweb.html", true);
		
		writeStudents("OPTIweb.html", true);
		
		writeTeachers("OPTIweb.html", true);
		
		writeToFile("html_templates/credits.html", "OPTIweb.html", true);
		
		writeToFile("html_templates/footer.html", "OPTIweb.html", true); // Footer
	}
}
