package data;

public class Topic {
	private String id;
	private String nom;
	private String titre;
	private int nbreSujets;
	
	public Topic(String id) {
		this.id = id;
	}

	public Topic(String id, String nom, String titre, int nbreSujets) {
		this.id = id;
		this.nom = nom;
		this.titre = titre;
		this.nbreSujets = nbreSujets;
	}

	public String getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public String getTitre() {
		return titre;
	}

	public int getNbreSujets() {
		return nbreSujets;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public void setNbreSujets(int nbreSujets) {
		this.nbreSujets = nbreSujets;
	}
}
