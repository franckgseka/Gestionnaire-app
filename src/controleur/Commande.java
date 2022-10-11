package controleur;

public class Commande {
	private int idcommande; 
	private String date_commande; 
	private int idclient;
	
	public Commande(int idcommande, String date_commande,
			int idclient) {
		super();
		this.idcommande = idcommande;
		this.date_commande = date_commande;
		this.idclient = idclient;
	} 
	
	public Commande( String date_commande, int idclient) {
		super();
		this.idcommande = 0;
		this.date_commande = date_commande;
		this.idclient = idclient;
	} 
	public Commande() {
		super();
		this.idcommande = 0;
		this.date_commande = "";
		this.idclient = 0;
	}

	public int getIdcommande() {
		return idcommande;
	}

	public void setIdcommande(int idcommande) {
		this.idcommande = idcommande;
	}

	public String getdate_commande() {
		return date_commande;
	}

	public void setdate_commande(String date_commande) {
		this.date_commande = date_commande;
	}

	public int getidclient() {
		return idclient;
	}

	public void setidclient(int idclient) {
		this.idclient = idclient;
	}
	
}