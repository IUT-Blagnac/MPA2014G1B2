package library;

import java.io.IOException;

import data.Student;

public class CtrlStudent {
	
	/**
	* Gets the students listed in a CSV file.
	*@param pPath Path of the CSV file
	*@return list of the students
	*/
	public static Student[] getStudents(String pPath) {
		Student[] result;
		String[][] data = new String[3][];
		String group = null, lName = null, fName = null, path = pPath;//"csv\\etudiants2014_2015.csv";

		try {
			data = OptiParser.parseCSV(path);
			result = new Student[data.length];

			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[i].length; j++) {
					if (j == 0) {
						group = data[i][j];
					}
					if (j == 1) {
						lName = data[i][j];
					}
					if (j == 2) {
						fName = data[i][j];
					}
				}
				result[i] = new Student(group, lName, fName);
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
		String path = "csv\\etudiants2014_2015.csv";
		
		try {
			header = OptiParser.parseCSVHeader(path);
			
		} catch (Exception e) { System.out.println(e); }
		
		return header;
	}
	
	/**
	* Format and write the student list given by parameter into the csv/projets2014_2015.csv file.
	*@param allProjects Array containing the students
	*/
	public static void saveToCSV(Student[] allStudents) throws IOException {

		// On vide le fichier qui va recevoir la sauvegarde
		OptiParser.clearCSVfile("csv/TESTetudiants2014_2015.csv");

		//On écrit l'entête du fichier
		OptiParser.writeLineToCSV(getHeader()[0]+";"+getHeader()[1]+";"+getHeader()[2], "csv/TESTetudiants2014_2015.csv");

		for (int i = 0; i < allStudents.length; i++) {
			OptiParser.writeLineToCSV(
					allStudents[i].getGroup() + ";"
							+ allStudents[i].getLastName() + ";"
							+ allStudents[i].getFirstName(),
					"csv/TESTetudiants2014_2015.csv");

		}
	}

	// La même, mais pour les tests
	/**
	* Format and write the student list given by parameter into a specified csv file.
	*@param allStudents Array containing the students
	*@param fileName File to write
	*/
	public static void saveToCSV(Student[] allStudents, String fileName)
			throws IOException {

		// On vide le fichier qui va recevoir la sauvegarde
		OptiParser.clearCSVfile(fileName);

		// On écrit l'entête du fichier
		OptiParser.writeLineToCSV(getHeader()[0]+";"+getHeader()[1]+";"+getHeader()[2], fileName);

		for (int i = 0; i < allStudents.length; i++) {
			OptiParser.writeLineToCSV(
					allStudents[i].getGroup() + ";"
							+ allStudents[i].getLastName() + ";"
							+ allStudents[i].getFirstName(), fileName);
		}
	}

}
