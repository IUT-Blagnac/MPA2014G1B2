package data;

/**
 * Provides a storage into the application for a project.
 * @author Groupe 1B2
 * 
 */
public class Project {
	private String group, subject;
	
	/**
	 * Creates a Project instance.
	 * @param pGroup The name of the group.
	 * @param pSubject The id of the subject.
	 */
	public Project(String pGroup, String pSubject) {
		this.group = pGroup;
		this.subject = pSubject;
	}

	/**
	 * Getter for the group attribute.
	 * @return The name of the group.
	 */
	public String getGroup() {
		return this.group;
	}

	/**
	 * Setter for the group attribute.
	 * @return The name of the group.
	 */
	public void setGroup(String pGroup) {
		this.group = pGroup;
	}

	/**
	 * Getter for the subject attribute.
	 * @return The ID of the subject.
	 */
	public String getSubject() {
		return this.subject;
	}

	/**
	 * Setter for the subject attribute.
	 * @return The ID of the subject.
	 */
	public void setSubject(String pSubject) {
		this.subject = pSubject;
	}
}
