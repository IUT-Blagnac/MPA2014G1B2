package lib;

import java.io.IOException;

import data.Group;
import data.Project;
import data.Teacher;
import data.Topic;

public class CtrlProject {
	
	/**
	* Gets the projects listed in a CSV file.
	*@param pPath Path of the CSV file
	*@return list of the projects
	*/
	public static Project[] getProjects(String pPath) {
		Project[] result;
		String[][] data = new String[2][];
		Group group = null;
		Topic topic = null;
		Teacher supervisor = null, client = null;
		String path = pPath;//"csv\\projets2014_2015.csv";

		try {
			data = Parser.parseCSV(path);
			result = new Project[data.length];

			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[i].length; j++) {
					switch (j) {
					case 1:
						group = new Group(data[i][j]);
						break;
					case 2:
						topic = CtrlTopic.findTopic("../test/sujets2014_2015.csv", data[i][j]);
						break;
					case 3:
						client = CtrlTeacher.findTeacher("../test/intervenants2014_2015.csv", Integer.parseInt(data[i][j]));
						break;
					case 4:
						supervisor = CtrlTeacher.findTeacher("../test/intervenants2014_2015.csv", Integer.parseInt(data[i][j]));
					}
				}
				result[i] = new Project(group, topic, supervisor, client);
			}

			return result;

		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}
	
	/**
	 * Returns the letter of the group assigned to a project (identified by its ID field)
	 * @param pPath Path of the CSV file
	 * @param pID Project ID
	 * @return the group letter
	 */
	public static String findGroupAssignedToProject(String pPath, int pID)
	{
		Project[] allProjects = getProjects(pPath);
		String groupName = new String();
		
		for (Project p : allProjects)
		{
			if (Integer.parseInt(p.getpTopic().getId()) == pID)
			{
				groupName += p.getpGroup().getLibelle() + " ";
			}
		}
		
		return groupName;
	}
	
	/**
	 * This method returns the number of projects where a teacher is the client
	 * @param pPath Path of the CSV file
	 * @param teacherID the identifier of the teacher
	 * @return the number of projects satisfying test
	 */
	public static int getNumberOfProjectsWhereClient(String pPath, int teacherID)
	{
		Project[] allProjects = getProjects(pPath);
		int number = 0;
		
		for (Project p : allProjects)
		{
			if (p.getClient().getId() == teacherID)
				number++;
		}
		
		return number;
	}
	
	/**
	 * This method returns the number of projects where a teacher is the supervisor
	 * @param pPath Path of the CSV file
	 * @param teacherID the identifier of the teacher
	 * @return the number of projects satisfying test
	 */
	public static int getNumberOfProjectsWhereSupervisor(String pPath, int teacherID)
	{
		Project[] allProjects = getProjects(pPath);
		int number = 0;
		
		for (Project p : allProjects)
		{
			if (p.getSupervisor().getId() == teacherID)
				number++;
		}
		
		return number;
	}

	/**
	* Gets the header listed in the csv\projets2014_2015.csv file.
	*@return String array of the headers
	*/
	public static String[] getHeader() {
		String[] header = new String[2];
		String path = "..\\csv\\projets2014_2015.csv";

		try {
			header = Parser.parseCSVHeader(path);
		} catch (Exception e) {
			System.out.println(e);
		}

		return header;
	}

	/**
	* Format and write the project list given by parameter into the csv/projets2014_2015.csv file.
	*@param allProjects Array containing the projects
	*@return list of the projects
	*/
	public static void saveToCSV(Project[] allProjects) throws IOException {

		// On vide le fichier qui va recevoir la sauvegarde
		Parser.clearCSVfile("csv/projets2014_2015.csv");

		// On écrit l'entête du fichier
		Parser.writeLineToCSV(getHeader()[0]+";"+getHeader()[1], "csv/projets2014_2015.csv");

		for (int i = 0; i < allProjects.length; i++) {
			Parser.writeLineToCSV(allProjects[i].getpGroup() + ";"
					+ allProjects[i].getpTopic(), "csv/projets2014_2015.csv");
		}
	}

	// La même, mais pour les tests
	/**
	* Format and write the project list given by parameter into a specified csv file.
	*@param allProjects Array containing the projects
	*/
	public static void saveToCSV(Project[] allProjects, String fileName)
			throws IOException {

		// On vide le fichier qui va recevoir la sauvegarde
		Parser.clearCSVfile(fileName);

		// On écrit l'entête du fichier
		Parser.writeLineToCSV(getHeader()[0]+";"+getHeader()[1], fileName);

		for (int i = 0; i < allProjects.length; i++) {
			Parser.writeLineToCSV(allProjects[i].getpGroup() + ";"
					+ allProjects[i].getpTopic(), fileName);

		}
	}

}
