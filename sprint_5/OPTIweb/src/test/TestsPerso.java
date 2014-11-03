package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestsPerso {
	public static void main(String[] argv) throws FileNotFoundException {

		Scanner sc = new Scanner(new File("../test/ourTests/contentToInsert.txt"));
		String strContentToInsert = "";
		while(sc.hasNextLine()){
		    strContentToInsert += sc.nextLine();                     
		}
	}
}
