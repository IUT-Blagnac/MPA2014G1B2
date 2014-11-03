package data;

/**
 * Provides a storage for a teacher into the application.
 * @author Groupe 1B2
 *
 */
public class Teacher {
	private String lastName, firstName;
	
	/**
	 * Creates a Teacher instance.
	 * @param pFirstName The first name of the teacher.
	 * @param pLastName The last name of the project.
	 */
	public Teacher(String pFirstName, String pLastName) {
		this.firstName = pFirstName;
		this.lastName = pLastName;
		
	}

	/**
	 * Getter for the last name of the teacher.
	 * @return The last name of the teacher.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter for the last name of the teacher.
	 * @param lastName The last name of the teacher.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter for the first name of the teacher.
	 * @return The first name of the teacher.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter for the first name of the project.
	 * @param firstName The first name of the project.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
}
