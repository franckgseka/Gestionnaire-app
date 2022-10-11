package controleur;

public class Client {

	private int idclient;
	private String nom, prenom, email, date_naissance, tel, adresse;
	public Client(int idclient, String nom, String prenom, String email, String date_naissance, String tel,
			String adresse) {
		super();
		this.idclient = idclient;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.date_naissance = date_naissance;
		this.tel = tel;
		this.adresse = adresse;
	}
	
	public Client( String nom, String prenom, String email, String date_naissance, String tel,
			String adresse) {
		super();
		this.idclient = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.date_naissance = date_naissance;
		this.tel = tel;
		this.adresse = adresse;
	}
	
	public Client() {
		super();
		this.idclient =0;
		this.nom = "";
		this.prenom = "";
		this.email = "";
		this.date_naissance = "";
		this.tel = "";
		this.adresse = "";
	}

	public int getIdclient() {
		return idclient;
	}

	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDate_naissance() {
		return date_naissance;
	}

	public void setDate_naissance(String date_naissance) {
		this.date_naissance = date_naissance;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	
}

