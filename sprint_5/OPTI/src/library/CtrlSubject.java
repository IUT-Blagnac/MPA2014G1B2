package library;

import java.io.IOException;

import data.Subject;

public class CtrlSubject {
	/**
	* Gets the subjects listed in a CSV file.
	*@param pPath Path of the CSV file
	*@return list of the subjects
	*/
	public static Subject[] getSubjects(String pPath) {
		Subject[] result;
		String[][] data = new String[3][];
		String id = null, name = null, title = null, path = pPath;//"csv\\sujets2014_2015.csv";

		try {
			data = OptiParser.parseCSV(path);
			result = new Subject[data.length];

			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[i].length; j++) {
					if (j == 0) {
						id = data[i][j];
					}
					if (j == 1) {
						name = data[i][j];
					}
					if (j == 2) {
						title = data[i][j];
					}
				}
				result[i] = new Subject(id, name, title);
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
		String[] header = new String[3];
		String path = "csv\\sujets2014_2015.csv";
		
		try {
			header = OptiParser.parseCSVHeader(path);
			
		} catch (Exception e) { System.out.println(e); }
		
		return header;
	}

	/**
	* Format and write the project list given by parameter into the csv/projets2014_2015.csv file.
	*@param allSubjects Array containing the projects
	*/
	public static void saveToCSV(Subject[] allSubjects) throws IOException {

		// On vide le fichier qui va recevoir la sauvegarde
		OptiParser.clearCSVfile("csv/sujets2014_2015.csv");

		// On écrit l'entête du fichier
		OptiParser.writeLineToCSV(getHeader()[0]+";"+getHeader()[1]+";"+getHeader()[2], "csv/sujets2014_2015.csv");

		for (int i = 0; i < allSubjects.length; i++) {
			OptiParser.writeLineToCSV(
					allSubjects[i].getId() + ";" + allSubjects[i].getName()
							+ ";" + allSubjects[i].getTitle(),
					"csv/sujets2014_2015.csv");
		}
	}

	// La même, mais pour les tests
	/**
	* Format and write the subject list given by parameter into a specified csv file.
	*@param allSubjects Array containing the subjects
	*@param fileName File to write
	*/
	public static void saveToCSV(Subject[] allSubjects, String fileName)
			throws IOException {

		// On vide le fichier qui va recevoir la sauvegarde
		OptiParser.clearCSVfile(fileName);

		// On écrit l'entête du fichier
		OptiParser.writeLineToCSV(getHeader()[0]+";"+getHeader()[1]+";"+getHeader()[2], fileName);

		for (int i = 0; i < allSubjects.length; i++) {
			OptiParser.writeLineToCSV(
					allSubjects[i].getId() + ";" + allSubjects[i].getName()
							+ ";" + allSubjects[i].getTitle(), fileName);
		}
	}
}
