package controleur;

public class Articles {
	private int idarticles; 
	private float prix ;
	private String nom ;

	public Articles(int idarticles, float prix, String nom) {
		super();
		this.idarticles = idarticles;
		this.prix = prix;
		this.nom = nom;
	}  
	public Articles( float prix, String nom) {
		super();
		this.idarticles = 0;
		this.prix = prix;
		this.nom = nom;
	}  
	public Articles() {
		super();
		this.idarticles = 0;
		this.prix = 0;
		this.nom = "";
	}

	public int getIdarticles() {
		return idarticles;
	}

	public void setIdarticles(int idarticles) {
		this.idarticles = idarticles;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	} 


}

 