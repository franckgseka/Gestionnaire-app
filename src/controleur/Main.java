package controleur;

import java.util.ArrayList;

import modele.Modele;
import vue.vueConnexion;
import vue.vueUser;
import vue.vueArticles;
import vue.vueCommande;
import vue.vueClient;

public class Main {
	private static vueConnexion uneConnexion ;
	private static vueUser uneVueUser;
	private static vueCommande uneVueCommande; 
	private static vueArticles uneVueArticle; 
	private static vueClient uneVueClient; 
	
	public static void main(String[] args) {
		 
		uneConnexion = new vueConnexion();

	}

	public static void instanciervueCommande () {
		uneVueCommande = new vueCommande(); 
	}
	
	public static void instanciervueUser () {
		uneVueUser = new vueUser(); 
	}
	public static void instanciervueClients() {
		uneVueClient = new vueClient(); 
	}
	public static void rendreVisible (boolean action)
	{
		uneConnexion.setVisible(action);
	}
	/********************* Controleur de la classe User ************************/
	
	public static void insertUser (User unUser )
	{
		Modele.insertUser(unUser);
	}
	
	public static void deleteUser (int idUser )
	{
		Modele.deleteUser(idUser);
	}
	
	public static void deleteCommande (int idCommande )
	{
		Modele.deleteCommande(idCommande);
	}
	
	public static void updateUser (User unUser )
	{
		Modele.updateUser(unUser);	
	
	}
	
	public static void updateCommande (Commande uneCommande )
	{
		Modele.updateCommande(uneCommande);	
	
	}
	
	
	public static User selectWhereUser(String email, String password )
	{
		return Modele.selectWhereUser(email, password);
	}
	
	public static ArrayList<User> selectAllUsers ( String mot )
	{
		return Modele.selectAllUsers(mot);
		
	}
	 
	public static ArrayList<Commande> selectAllCommandes ( String mot )
	{
		return Modele.selectAllCommandes(mot);
		
	}
	
	public static Commande selectWhereCommande(int idclient, String date_commande )
	{
		return Modele.selectWhereCommande(idclient, date_commande);
	}
	
	 
	public static void insertCommande(Commande uneCommande) {
		Modele.insertcommande(uneCommande);
	}
	
	public static ArrayList<Articles> selectAllArticles ( String mot )
	{
		return Modele.selectAllArticles(mot);
		
	}
	
	public static Articles selectWhereArticles(float prix, String nom )
	{
		return Modele.selectWhereArticles( prix, nom);
	}
	public static Articles selectWhereArticles(int idarticle )
	{
		return Modele.selectWhereArticles( idarticle);
	}
	public static void insertArticles(Articles unarticle) {
		Modele.insertArticles(unarticle);
	}
	
	public static void updateArticles (Articles unArticle )
	{
		Modele.updateArticles(unArticle);	
	
	}

	public static void instanciervueArticles() {
		 
		uneVueArticle = new vueArticles();
	}
	
	public static void deleteClient (int idClient )
	{
		Modele.deleteClient(idClient);
	}
	
	public static void updateClient (Client unClient )
	{
		Modele.updateClient(unClient);	
	
	}
	
	public static ArrayList<Client> selectAllClients ( String mot )
	{
		return Modele.selectAllClients(mot);
		
	}
	
	public static Client selectWhereClient(String nom, String prenom )
	{
		return Modele.selectWhereClient( nom, prenom);
		
	}
	
	public static void insertClient (Client unClient )
	{
		Modele.insertClient(unClient);
	}

	
	/***********************************************************************/
}
