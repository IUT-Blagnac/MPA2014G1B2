package lib;

import java.io.IOException;

import data.Teacher;
import data.Topic;

public class CtrlTopic {
	/**
	* Gets the subjects listed in a CSV file.
	*@param pPath Path of the CSV file
	*@return list of the subjects
	*/
	public static Topic[] getTopics(String pPath) {
		Topic[] result;
		String[][] data = new String[3][];
		String id = null, name = null, title = null, path = pPath;//"csv\\sujets2014_2015.csv";

		try {
			data = Parser.parseCSV(path);
			result = new Topic[data.length];
			int nbTopics = 0;
			
			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[i].length; j++) {
					switch (j)
					{
					case 0:
						id = data[i][j];
						break;
					case 1:
						name = data[i][j];
						break;
					case 2:
						title = data[i][j];
						break;
					}	
				}
				result[i] = new Topic(id);
				result[i].setNom(name);
				result[i].setTitre(title);
				nbTopics++;
			}
			
			for (int i = 0; i< result.length; i++) {
				result[i].setNbreSujets(nbTopics);
			}

			return result;

		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}
	
	/**
	 * Finds a particular topic in the list
	 * @param pPath the path of the CSV file
	 * @param id the ID of the topic to get
	 * @return the matching topic
	 */
	public static Topic findTopic(String pPath, String id)
	{
		Topic topic = null;
		
		Topic[] allTopics = getTopics(pPath);
		for (Topic t : allTopics)
		{
			if (t.getId().equals(id))
			{
				topic = t;
			}
		}
		
		return topic;
	}
	
	/**
	* Gets the header listed in the csv\projets2014_2015.csv file.
	*@return String array of the headers
	*/
	public static String[] getHeader() {
		String[] header = new String[3];
		String path = "csv\\sujets2014_2015.csv";
		
		try {
			header = Parser.parseCSVHeader(path);
			
		} catch (Exception e) { System.out.println(e); }
		
		return header;
	}

	/**
	* Format and write the project list given by parameter into the csv/projets2014_2015.csv file.
	*@param allSubjects Array containing the projects
	*/
	public static void saveToCSV(Topic[] allSubjects) throws IOException {

		// On vide le fichier qui va recevoir la sauvegarde
		Parser.clearCSVfile("csv/sujets2014_2015.csv");

		// On écrit l'entête du fichier
		Parser.writeLineToCSV(getHeader()[0]+";"+getHeader()[1]+";"+getHeader()[2], "csv/sujets2014_2015.csv");

		for (int i = 0; i < allSubjects.length; i++) {
			Parser.writeLineToCSV(
					allSubjects[i].getId() + ";" + allSubjects[i].getNom()
							+ ";" + allSubjects[i].getTitre(),
					"csv/sujets2014_2015.csv");
		}
	}

	// La même, mais pour les tests
	/**
	* Format and write the subject list given by parameter into a specified csv file.
	*@param allSubjects Array containing the subjects
	*@param fileName File to write
	*/
	public static void saveToCSV(Topic[] allSubjects, String fileName)
			throws IOException {

		// On vide le fichier qui va recevoir la sauvegarde
		Parser.clearCSVfile(fileName);

		// On écrit l'entête du fichier
		Parser.writeLineToCSV(getHeader()[0]+";"+getHeader()[1]+";"+getHeader()[2], fileName);

		for (int i = 0; i < allSubjects.length; i++) {
			Parser.writeLineToCSV(
					allSubjects[i].getId() + ";" + allSubjects[i].getNom()
							+ ";" + allSubjects[i].getTitre(), fileName);
		}
	}
}
