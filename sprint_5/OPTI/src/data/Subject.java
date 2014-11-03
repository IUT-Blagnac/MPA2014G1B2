package data;

/**
 * Provides a storage for a subject into the application.
 * @author Groupe 1B2
 *
 */
public class Subject {
	private String id, name, title;
		
	/**
	 * Creates a Subject instance.
	 * @param pId
	 * @param pName
	 * @param pTitle
	 */
	public Subject(String pId, String pName, String pTitle) {
		this.id = pId;
		this.name = pName;
		this.title = pTitle;
	}

	/**
	 * Getter for the subject's ID.
	 * @return The ID of the project.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Setter for the subject's ID.
	 * @param id The ID of the project.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Getter for the name of the project.
	 * @return The name of the project.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for the name of the project.
	 * @param name The name of the project.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for the title (description) of the project.
	 * @return The title (description) of the project.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Setter for the title (description) of the project.
	 * @param title The title (description) of the project.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
}
