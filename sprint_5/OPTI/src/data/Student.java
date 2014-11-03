package data;

/**
 * Provides a storage for a student into the application
 * @author Groupe 1B2
 *
 */
public class Student {
	private String group, lastName, firstName;
		
	/**
	 * Creates a Student instance.
	 * @param pGroup The name of the student's group.
	 * @param pLastName The student's first name.
	 * @param pFirstName The student's last name.
	 */
	public Student(String pGroup, String pLastName, String pFirstName) {
		this.group = pGroup;
		this.lastName = pLastName;
		this.firstName = pFirstName;
	}

	/**
	 * Getter for the group number attribute.
	 * @return The group number.
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * Setter for the student's group name.
	 * @param group The name of the student's group.
	 */
	public void setGroup(String group) {
		this.group = group;
	}

	/**
	 * Getter for the student's last name.
	 * @return The last name of the student.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter for the student's last name.
	 * @param lastName The last name of the student.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * Getter for the student's first name.
	 * @return The first name of the student.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter for the student's first name.
	 * @param firstName The first name of the student.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
}
