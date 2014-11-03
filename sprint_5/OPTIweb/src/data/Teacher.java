package data;


public class Teacher {
	private int id;
	private String pLName;
	private String pFName;
	
	public Teacher(int pID, String pFName, String pLName) {
		this.id = pID;
		this.pLName = pLName;
		this.pFName = pFName;
	}
	public String getpLName() {
		return pLName;
	}
	public String getpFName() {
		return pFName;
	}
	public void setpLName(String pLName) {
		this.pLName = pLName;
	}
	public void setpFName(String pFName) {
		this.pFName = pFName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
