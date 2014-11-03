package lib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Parser
{
	/**
	 * Reads the CSV header (first line) which contains all column titles
	 * 
	 * @param filename
	 *            the name of the CSV file
	 * @return string array containing column titles
	 * @throws IOException
	 */
	public static String[] parseCSVHeader(String filename) throws IOException {
		File f = new File(filename);
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(f), "UTF-8"));
		String headerLine = null;

		headerLine = br.readLine();

		String[] header = headerLine.split(";");

		br.close();
		return header;
	}

	/**
	 * Parses the CSV file
	 * 
	 * @param filename
	 *            the name of the CSV file
	 * @return two-dimension string array with all extracted data from CSV file
	 * @throws IOException
	 */
	public static String[][] parseCSV(String filename) throws IOException
	{
		File f = new File(filename);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
		//String nl = System.getProperty("line.separator");
		String curLine = null;
		ArrayList<String> lines = new ArrayList<String>();
		
		while ((curLine = br.readLine()) != null)
		{
			lines.add(curLine);
		}
		
		String[] temp = lines.get(0).split(";");
		
		String[][] fileContents = new String[lines.size() - 1][temp.length];
		
		for (int i = 0; i < fileContents.length; i++)
		{
			String[] line = lines.get(i + 1).split(";");
			for (int j = 0; j < fileContents[i].length; j++)
			{
				try {
					fileContents[i][j] = line[j];
				} catch (ArrayIndexOutOfBoundsException e) { }
			}
		}
		
		br.close();
		
		return fileContents;
	}

	/**
	 * Writes an ArrayList to a CSV file
	 * 
	 * @param linesArray
	 *            the list containing lines to be written
	 * @param outputFilename
	 *            the name of the target file
	 * @throws IOException
	 */
	public static void saveCSV(String[] content, String outputFilename) throws IOException {
		File f = new File(outputFilename);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(f), "UTF-8"));
		String nl = System.getProperty("line.separator");

		String tempLine = null;
		for (int i = 0; i < content.length; i++) {
			tempLine = new String(content[i]);
			bw.write(tempLine);
			bw.write(nl);
		}

		bw.close();
	}

	/**
	 * Writes a line (pre-formatted) to a CSV file
	 * 
	 * @param input
	 *            the line to be written
	 * @param inputFilename
	 *            name of the target file
	 * @throws IOException
	 */
	public static void writeLineToCSV(String input, String inputFilename) throws IOException {
		File f = new File(inputFilename);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(f, true), "UTF-8"));
		String nl = System.getProperty("line.separator");

		bw.write(input);
		bw.write(nl);

		bw.close();
	}

	/**
	 * Deletes a line from a CSV file
	 * 
	 * @param lineToDelete
	 *            index of the line to delete (line number)
	 * @param fileName
	 *            target file name
	 * @throws IOException
	 */
	public static void deleteLineFromCSV(int lineToDelete, String fileName) throws IOException {
		String[][] currentContent = parseCSV(fileName);
		String[][] newContent = new String[currentContent.length - 1][currentContent[0].length];
		for (int i = 0; i < currentContent.length; i++) {
			for (int j = 0; j < currentContent[0].length; j++) {
				if (i != lineToDelete)
					newContent[i][j] = currentContent[i][j];
			}
		}
	}

	/**
	 * Clears the CSV file which its path is passed as parameter.
	 * 
	 * @param filePath
	 *            The path of the file to clear
	 */
	public static void clearCSVfile(String filePath) {
		File f = new File(filePath);
		PrintWriter writer;
		try {
			writer = new PrintWriter(f);
			writer.print("");
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
