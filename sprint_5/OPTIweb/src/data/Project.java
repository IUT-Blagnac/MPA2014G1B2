package data;



public class Project {
	
	private Group group;
	private Topic topic;
	private Teacher supervisor, client;
	
	/**
	 * Contructeur de l'objet Projet (Project)
	 * @param pGroup le groupe associé au projet
	 * @param pTopic le sujet du projet
	 * @param hasClient true si un client a été associé au projet
	 * @param hasSupport true si un support technique a été associé au projet
	 * @param has Supervisor true si un superviseur a été associé au projet
	 * @return Un objet de type Project
	 */
	public Project(Group pGroup, Topic pTopic, Teacher pSupervisor, Teacher pClient) {
		this.group = pGroup;
		this.topic = pTopic;
		this.supervisor = pSupervisor;
		this.client = pClient;
	}
	
	/**
	 * Getter de la variable pGroup
	 * @return le groupe associé au projet
	 */
	public Group getpGroup() {
		return this.group;
	}
	
	/**
	 * Getter de la variable pTopic
	 * @return le sujet (Topic) du projet
	 */
	public Topic getpTopic() {
		return this.topic;
	}
	
	/**
	 * Setter du groupe associé au projet
	 * @param pGroup
	 */
	public void setpGroup(Group pGroup) {
		this.group = pGroup;
	}
	/**
	 * Setter du sujet du projet
	 * @param pTopic
	 */
	public void setpTopic(Topic pTopic) {
		this.topic = pTopic;
	}

	/**
	 * @return the supervisor
	 */
	public Teacher getSupervisor() {
		return supervisor;
	}

	/**
	 * @param supervisor the supervisor to set
	 */
	public void setSupervisor(Teacher supervisor) {
		this.supervisor = supervisor;
	}

	/**
	 * @return the client
	 */
	public Teacher getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(Teacher client) {
		this.client = client;
	}
}
