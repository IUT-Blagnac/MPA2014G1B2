package lib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import data.Group;
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
			data = Parser.parseCSV(path);
			result = new Student[data.length];

			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[i].length; j++) {
					switch (j)
					{
					case 0:
						group = data[i][j];
						break;
					case 2:
						lName = data[i][j];
						break;
					case 3:
						fName = data[i][j];
						break;
					}
				}
				result[i] = new Student(fName, lName, new Group(group));
			}

			return result;

		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}
	
	/**
	 * Returns an array containing all students of a group
	 * @param pPath Path of the CSV file
	 * @param groupName Group name (sort criteria)
	 * @return List of the students of the group
	 */
	public static Student[] getStudentsOfGroup(String pPath, String groupName) {
		Student[] allStudents = getStudents(pPath);
		ArrayList<Student> groupStudentsList = new ArrayList<Student>();
		
		for (Student s : allStudents)
		{
			if (s.getStudGroup().getLibelle().equals(groupName))
				groupStudentsList.add(s);
		}
		
		return groupStudentsList.toArray(new Student[groupStudentsList.size()]);
	}
	
	/**
	 * Returns a ready-to-display string with a list of students of a certain group
	 * @param pPath The path of the CSV file
	 * @param groupName Group name (sort criteria)
	 * @return formatted string
	 */
	public static String groupStudentsString(String pPath, String groupName)
	{
		String outputString = new String();
		
		Student[] groupStudents = getStudentsOfGroup(pPath, groupName);
		for (Student s : groupStudents)
		{
			outputString += s.getFname() + " " + s.getLname().toUpperCase() + " - ";
		}
		
		return outputString;
	}

	/**
	* Gets the header listed in the csv\projets2014_2015.csv file.
	*@return String array of the headers
	*/
	public static String[] getHeader() {
		String[] header = new String[3];
		String path = "csv\\etudiants2014_2015.csv";
		
		try {
			header = Parser.parseCSVHeader(path);
			
		} catch (Exception e) { System.out.println(e); }
		
		return header;
	}
	
	/**
	* Format and write the student list given by parameter into the csv/projets2014_2015.csv file.
	*@param allProjects Array containing the students
	*/
	public static void saveToCSV(Student[] allStudents) throws IOException {

		// On vide le fichier qui va recevoir la sauvegarde
		Parser.clearCSVfile("csv/TESTetudiants2014_2015.csv");

		//On écrit l'entête du fichier
		Parser.writeLineToCSV(getHeader()[0]+";"+getHeader()[1]+";"+getHeader()[2], "csv/TESTetudiants2014_2015.csv");

		for (int i = 0; i < allStudents.length; i++) {
			Parser.writeLineToCSV(
					allStudents[i].getStudGroup() + ";"
							+ allStudents[i].getLname() + ";"
							+ allStudents[i].getFname(),
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
		Parser.clearCSVfile(fileName);

		// On écrit l'entête du fichier
		Parser.writeLineToCSV(getHeader()[0]+";"+getHeader()[1]+";"+getHeader()[2], fileName);

		for (int i = 0; i < allStudents.length; i++) {
			Parser.writeLineToCSV(
					allStudents[i].getStudGroup() + ";"
							+ allStudents[i].getLname() + ";"
							+ allStudents[i].getFname(), fileName);
		}
	}

}
