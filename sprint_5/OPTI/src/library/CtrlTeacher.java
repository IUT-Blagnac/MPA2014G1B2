package library;

import java.io.IOException;

import data.Teacher;

public class CtrlTeacher {
	
	/**
	* Gets the teachers listed in a CSV file.
	*@param pPath Path of the CSV file
	*@return list of the teachers
	*/
	public static Teacher[] getTeachers(String pPath) {
		Teacher[] result;
		String[][] data = new String[2][];
		String prenom = null, nom = null, path = pPath;//"csv\\intervenants2014_2015.csv";

		try {
			data = OptiParser.parseCSV(path);
			result = new Teacher[data.length];

			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[i].length; j++) {
					if (j == 0) {
						prenom = data[i][j];
					}
					if (j == 1) {
						nom = data[i][j];
					}
				}
				result[i] = new Teacher(prenom, nom);
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
		String path = "csv\\intervenants2014_2015.csv";
		
		try {
			header = OptiParser.parseCSVHeader(path);
			
		} catch (Exception e) { System.out.println(e); }
		
		return header;
	}	
	/**
	* Format and write the teacher list given by parameter into the csv/projets2014_2015.csv file.
	*@param allProjects Array containing the teachers
	*/
	public static void saveToCSV(Teacher[] allTeachers) throws IOException {

		// On vide le fichier qui va recevoir la sauvegarde
		OptiParser.clearCSVfile("csv/intervenants2014_2015.csv");

		// On écrit l'entête du fichier
		OptiParser.writeLineToCSV(getHeader()[0]+";"+getHeader()[1], "csv/intervenants2014_2015.csv");

		for (int i = 0; i < allTeachers.length; i++) {
			OptiParser.writeLineToCSV(allTeachers[i].getFirstName() + ";"
					+ allTeachers[i].getLastName(),
					"csv/intervenants2014_2015.csv");
		}
	}

	// La même, mais pour les tests
	/**
	* Format and write the student list given by parameter into a specified csv file.
	*@param allTeachers Array containing the teachers
	*@param fileName File to write
	*/
	public static void saveToCSV(Teacher[] allTeachers, String fileName)
			throws IOException {

		// On vide le fichier qui va recevoir la sauvegarde
		OptiParser.clearCSVfile(fileName);

		// On écrit l'entête du fichier
		OptiParser.writeLineToCSV(getHeader()[0]+";"+getHeader()[1], fileName);

		for (int i = 0; i < allTeachers.length; i++) {
			OptiParser.writeLineToCSV(allTeachers[i].getFirstName() + ";"
					+ allTeachers[i].getLastName(), fileName);
		}
	}
}
