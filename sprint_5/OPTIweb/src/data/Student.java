package data;

/**
 * Implements a Student object.
 * @author damien
 *
 */
public class Student {
	private String fname;	// The first name of the student
	private String lname;	// The last name of the student
	private Group studGroup;	// The group of the student 
	
	public Student() {
		this.fname = null;
		this.lname = null;
		this.studGroup = null;
	}
	
	public Student(String firstName, String lastName, Group studentSGroup) {
		this.fname = firstName;
		this.lname = lastName;
		this.studGroup = studentSGroup;
	}
	
	public String getFname() {
		return this.fname;
	}
	
	public String getLname() {
		return this.lname;
	}
	
	public Group getStudGroup() {
		return this.studGroup;
	}
	
	public void setFname(String firstName) {
		this.fname = firstName;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public void setStudGroup(Group studGroup) {
		this.studGroup = studGroup;
	}
	
	public String toString()
	{
		return this.getFname() + " " + this.getLname().toUpperCase() + " - ";
	}
}
