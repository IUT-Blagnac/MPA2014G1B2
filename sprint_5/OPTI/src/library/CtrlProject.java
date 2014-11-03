package library;

import java.io.IOException;

import data.Project;

public class CtrlProject {
	
	/**
	* Gets the projects listed in a CSV file.
	*@param pPath Path of the CSV file
	*@return list of the projects
	*/
	public static Project[] getProjects(String pPath) {
		Project[] result;
		String[][] data = new String[2][];
		String group = null, title = null, path = pPath;//"csv\\projets2014_2015.csv";

		try {
			data = OptiParser.parseCSV(path);
			result = new Project[data.length];

			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[i].length; j++) {
					if (j == 0) {
						group = data[i][j];
					}
					if (j == 1) {
						title = data[i][j];
					}
				}
				result[i] = new Project(group, title);
			}

			return result;

		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}

	/**
	* Gets the header listed in the csv\projets2014_2015.csv file.
	*@return String array of the headers
	*/
	public static String[] getHeader() {
		String[] header = new String[2];
		String path = "csv\\projets2014_2015.csv";

		try {
			header = OptiParser.parseCSVHeader(path);
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
		OptiParser.clearCSVfile("csv/projets2014_2015.csv");

		// On écrit l'entête du fichier
		OptiParser.writeLineToCSV(getHeader()[0]+";"+getHeader()[1], "csv/projets2014_2015.csv");

		for (int i = 0; i < allProjects.length; i++) {
			OptiParser.writeLineToCSV(allProjects[i].getGroup() + ";"
					+ allProjects[i].getSubject(), "csv/projets2014_2015.csv");
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
		OptiParser.clearCSVfile(fileName);

		// On écrit l'entête du fichier
		OptiParser.writeLineToCSV(getHeader()[0]+";"+getHeader()[1], fileName);

		for (int i = 0; i < allProjects.length; i++) {
			OptiParser.writeLineToCSV(allProjects[i].getGroup() + ";"
					+ allProjects[i].getSubject(), fileName);

		}
	}

}
