package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Articles;
import controleur.Client;
import controleur.Commande;
import controleur.User;

public class Modele {
	//private static BDD uneBdd = new BDD ("172.20.111.186", "restau", "seka", "seka"); //pour mac 
	  private static BDD uneBdd = new BDD ("localhost:8889", "restau", "root", "root"); //pour PC 
	
	/***************************************** Gestion des clients ************************/
	public static void insertClient (Client unClient)
	{
		
		String requete ="insert into client values (null, '" + 
				unClient.getNom()+"','" + unClient.getPrenom()+"','" + unClient.getEmail()
				+"','"+unClient.getDate_naissance()
				+"','"+unClient.getTel()+"','" + unClient.getAdresse()+"'); ";
				Modele.executer(requete);
	}
	
	
	public static void deleteClient (int idClient)
	{
		String requete ="delete from client where idClient = "+idClient+";";
		Modele.executer(requete);
	}
	
	
	public static void updateClient (Client unClient)
	{
		String requete ="update client set nom = '" + unClient.getNom()
		+"', prenom ='"+unClient.getPrenom()+"', email ='" + unClient.getEmail()
		+"', date_naissance = '" + unClient.getDate_naissance()
		+"', tel='"+unClient.getTel()+"', adresse='"+ unClient.getAdresse()
		+ "'  where idClient ="+ unClient.getIdclient ()+"; ";
		Modele.executer(requete);
	}
	
	public static Client selectWhereClient (String nom, String prenom)
	{
		String requete ="select * from client where nom = '"+nom +"' and prenom ='"+prenom+"' ;" ; 
		Client unClient = null;
		System.out.println(requete);
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);  
			if (unRes.next())
			{
				unClient = new Client(
						unRes.getInt("idclient"),unRes.getString("nom"), 
						unRes.getString("prenom"),unRes.getString("email"),unRes.getString("date_naissance"),unRes.getString("tel"),
						unRes.getString("adresse")
						);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur d'exécution de la requête : " + requete);
		}
		return unClient; 
	}
	
	
	public static ArrayList<Client> selectAllClients (String mot)
	{
		String requete =""; 
		if (mot.equals("")) {
			requete ="select * from client ;" ; 
		}else {
			requete ="select * from client where nom like '%"+mot+"%' or prenom like '%"+mot
					+ "%' or tel like '%"+mot+"%' or adresse like '%"+mot+"%' ;"; 
		}
		ArrayList<Client> lesClients = new ArrayList<Client>(); 
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desRes = unStat.executeQuery(requete);  
			while (desRes.next())
			{
				Client unClient = new Client(
						desRes.getInt("idclient"),desRes.getString("nom"), 
						desRes.getString("prenom"),desRes.getString("email"),desRes.getString("date_naissance"),desRes.getString("tel"),
						desRes.getString("adresse")
						);
				lesClients.add(unClient);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur d'exécution de la requête : " + requete);
		}
		return lesClients; 
	}
	
	/***************************************** Gestion des users ************************/
	public static void insertUser (User unUser)
	{
		String requete ="insert into users values (null, '" + 
		unUser.getRole()+"','" + unUser.getUsername()+"','" + unUser.getEmail()
		+"','"+unUser.getPassword()
		+"','"+unUser.getTel()+"','" + unUser.getAdresse()+"'); ";
		Modele.executer(requete);
	}
	
	
	public static void deleteUser (int idUser)
	{
		String requete ="delete from users where idUser = "+idUser+";";
		Modele.executer(requete);
	}
	
	public static void updateUser (User unUser)
	{
		String requete ="update users set role = '" + unUser.getRole()
		+"', username ='"+unUser.getUsername()+"', email ='" + unUser.getEmail()
		+"', password = '" + unUser.getPassword()
		+"', tel='"+unUser.getTel()+"', adresse='"+ unUser.getAdresse()
		+ "'  where idUser ="+ unUser.getId	()+ "; ";
		Modele.executer(requete);
	}
	
	
	public static User selectWhereUser (int idUser)
	{
		String requete ="select * from users where idUser = "+idUser +";" ; 
		User unUser = null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);  
			if (unRes.next())
			{
				unUser = new User(
						unRes.getInt("iduser"),unRes.getString("role"), unRes.getString("username"), 
						unRes.getString("email"),unRes.getString("password"),unRes.getString("tel"),
						unRes.getString("adresse")
						);
			}
			
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur d'exécution de la requête : " + requete);
		}
		return unUser; 
	}
	
	
	public static User selectWhereUser (String email, String mdp)
	{
		String requete ="select * from users where email = '"+email +"' and password ='"+mdp+"' ;" ; 
		User unUser = null;
		System.out.println(requete);
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);  
			if (unRes.next())
			{
				unUser = new User(
						unRes.getInt("iduser"),unRes.getString("role"),unRes.getString("username"), 
						unRes.getString("email"),unRes.getString("password"),unRes.getString("tel"),
						unRes.getString("adresse")
						);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur d'exécution de la requête : " + requete);
		}
		return unUser; 
	}
	
	
	public static ArrayList<User> selectAllUsers (String mot)
	{
		String requete =""; 
		if (mot.equals("")) {
			requete ="select * from users ;" ; 
		}else {
			requete ="select * from users where username like '%"+mot+"%' or role like '%"+mot
					+ "%' or tel like '%"+mot+"%' or adresse like '%"+mot+"%' ;"; 
		}
		ArrayList<User> lesUsers = new ArrayList<User>(); 
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desRes = unStat.executeQuery(requete);  
			while (desRes.next())
			{
				User unUser = new User(
						desRes.getInt("iduser"),desRes.getString("role"),desRes.getString("username"), 
						desRes.getString("email"),desRes.getString("password"),desRes.getString("tel"),
						desRes.getString("adresse")
						);
				lesUsers.add(unUser);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur d'exécution de la requête : " + requete);
		}
		return lesUsers; 
	}
	
	/***************************************** Gestion des commandes ************************/
	
	public static void deleteCommande (int idCommande)
	{
		String requete ="delete from commandes where idCommande = "+idCommande+";";
		Modele.executer(requete);
	}
	
	
	public static void updateCommande (Commande uneCommande)
	{
		String requete ="update commande set nom = '" + uneCommande.getIdcommande()
		+"', email ='" + uneCommande.getdate_commande()
		+ uneCommande.getidclient()+ "; ";
		Modele.executer(requete);
		
	}
	
	public static ArrayList<Commande> selectAllCommandes (String mot)
	{
		String requete =""; 
		if (mot.equals("")) {
			requete ="select * from commande ;" ; 
		}else {
			requete ="select * from commande where nom like '%"+mot+"%' or prenom like '%"+mot
					+ "%' or poste like '%"+mot+"%' or adresse like '%"+mot+"%' ;"; 
		}
		ArrayList<Commande> lesCommandes = new ArrayList<Commande>(); 
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desRes = unStat.executeQuery(requete);  
			while (desRes.next())
			{
				Commande uneCommande = new Commande(desRes.getInt("idcommande"),
						desRes.getString("date_commande"), 
						desRes.getInt("idclient")
						);
				lesCommandes.add(uneCommande);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur d'exécution de la requête : " + requete);
		}
		return lesCommandes; 
	}
	
	public static void insertcommande (Commande unecommande)
	{
	
			String requete ="insert into commande values (null,'"+unecommande.getdate_commande()
			+"','"+unecommande.getidclient()+"'); ";
			Modele.executer(requete);
		
	}
	
	
	public static Commande selectWhereCommande (int idclient, String date_commande)
	{
		String requete ="select * from commande where idclient = '"+idclient +"' and date_commande ='"+date_commande+"' ;" ; 
		Commande uneCommande = null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);  
			if (unRes.next())
			{
				uneCommande = new Commande(
						unRes.getString("date_commande"),
						unRes.getInt("idclient")
						);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur d'exécution de la requête : " + requete);
		}
		return uneCommande; 
	}
	
	 
	public static void executer (String requete)
	
	{
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur d'exécution de la requête : " + requete);
		}
	}
	
	
	//surcharge de la méthode selectWhereUser avec différents arguments
	
	
	

	/***************************************** Gestion des articles ************************/

	
	public static void insertArticles (Articles unarticle)
	{
	
			String requete ="insert into articles values (null,'" + unarticle.getNom()
			+"','"+unarticle.getPrix()+"'); ";
			Modele.executer(requete);
		
	}

	public static void updateArticles (Articles unArticles)
	{
		String requete ="update articles set username = '" + unArticles.getIdarticles()
		+"', role ='"+unArticles.getPrix()
		+ "'  where idArticles ="+ unArticles.getNom	()+ "; ";
		Modele.executer(requete);
	}
	
	public static ArrayList<Articles> selectAllArticles (String mot)
	{
		String requete =""; 
		if (mot.equals("")) {
			requete ="select * from articles ;" ; 
		}else {
			requete ="select * from articles where nom like '%"+mot+"%' or prenom like '%"+mot
					+ "%' or poste like '%"+mot+"%' or adresse like '%"+mot+"%' ;"; 
		}
		ArrayList<Articles> lesArticles = new ArrayList<Articles>(); 
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desRes = unStat.executeQuery(requete);  
			while (desRes.next())
			{
				Articles unArticle = new Articles(
						desRes.getInt("idarticle"),
						desRes.getFloat("prix"), 
						desRes.getString("nom")
						);
				lesArticles.add(unArticle);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur d'exécution de la requête : " + requete);
		}
		return lesArticles; 
	}
	
	public static Articles selectWhereArticles (float prix, String nom)
	{
		String requete ="select * from articles where prix = '"+prix +"' and nom = '"+nom +"' ; " ; 
		Articles unArticle = null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);  
			if (unRes.next())
			{
				unArticle = new Articles(
						unRes.getInt("idarticle"),
						unRes.getFloat("prix"),
						unRes.getString("nom")
						);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur d'exécution de la requête : " + requete);
		}
		return unArticle; 
	}
	
	public static Articles selectWhereArticles (int id)
	{
		String requete ="select * from articles where idarticle = "+id +" ; " ; 
		Articles unArticle = null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);  
			if (unRes.next())
			{
				unArticle = new Articles(
						unRes.getInt("idarticle"),
						unRes.getFloat("prix"),
						unRes.getString("nom")
						);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur d'exécution de la requête : " + requete);
		}
		return unArticle; 
	}
	
}