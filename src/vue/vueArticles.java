package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Articles;
import controleur.Commande;
import controleur.Main;
import controleur.User;
import controleur.Tableau;

public class vueArticles extends JFrame implements ActionListener
{
	private JButton btRetour = new JButton("Retour au menu");
	private JComboBox<String> cbxNom = new JComboBox<String>();
	
	//déclaration du panel ajout
	private JPanel panelAjout = new JPanel(); 
	private JPanel panelLister = new JPanel();
	private JTextField txtIdarticles = new JTextField(); 
	private JTextField txtPrix = new JTextField();
	private JTextField txtNom = new JTextField(); 
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
			
	//panel lister : gestion du tableau des evenements 
	private JTable uneTable; 
	private Tableau unTableau; 
	private JScrollPane uneScroll; 
	
	//rechercher par mot clé 
	private JTextField txtMot = new JTextField(); 
	private JButton btFiltrer = new JButton("Filtrer");
	
	public vueArticles()
	{
		this.setTitle("Gestion des Articles de Chez Nouchi" );
		this.setBounds(200, 200, 900, 500);
		this.setResizable(false); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color (0, 0, 100));
		this.setLayout(null);
		
		this.btRetour.setBounds(750, 450, 130, 20);
		this.add(this.btRetour);
		this.btRetour.addActionListener(this);
		
		//construction du panel Ajout 
		this.panelAjout.setBounds(40, 60,340, 360);
		this.panelAjout.setBackground(new Color (0, 0, 100));
		this.panelAjout.setLayout(new GridLayout(8,2));
		
	
		JLabel lbPrix = new JLabel("Prix : ");
		lbPrix.setForeground(Color.white);
		this.panelAjout.add(lbPrix); 
		this.panelAjout.add(this.txtPrix);
		JLabel lbNom = new JLabel("Nom : ");
		lbNom.setForeground(Color.white);
		this.panelAjout.add(lbNom);
		this.panelAjout.add(this.txtNom);
		this.panelAjout.add(this.btAnnuler); 
		this.panelAjout.add(this.btEnregistrer);
		this.add(this.panelAjout);
		//remplir le CBX Salarie 
		this.remplirCBXArticles();
		
		//rendre les deux boutons cliquables 
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		
		//installation du panel lister 
		this.panelLister.setBounds(420, 60,480, 400);
		this.panelLister.setBackground(new Color (0, 0, 100));
		this.panelLister.setLayout(null);
		
		String entetes [] = {"Idarticles", "Prix", "Nom"};
		//instanciation de la classe tableau avec les entetes et les données 
		this.unTableau = new Tableau (entetes,this.remplirDonnees("") );
		
		//intanciation de la JTable avec le Tableau construit
		this.uneTable = new JTable(this.unTableau); 
		
		//je mets ma table dans ma scroll 
		this.uneScroll = new JScrollPane(this.uneTable); 
		this.uneScroll.setBounds(20, 0, 440, 340);
		this.panelLister.add(this.uneScroll);
		
		this.add(this.panelLister);

		
		this.uneTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount()>=2) {
					int ligne = uneTable.getSelectedRow(); 
					int retour = JOptionPane.showConfirmDialog(null,  "Confirmez la suppression",
							"Suppression", JOptionPane.YES_NO_OPTION); 
					if(retour ==0)
					{
						//on récupère l'id du salarie à supprimer 
						int idArticles = Integer.parseInt(unTableau.getValueAt(ligne,0).toString()) ;
						//on supprime dans la base données via le modele
						Main.deleteCommande(idArticles);
						//suppression de la ligne de l'affichage 
						unTableau.supprimerLigne(ligne);
					}
				}
				else if (e.getClickCount()==1) {
					int ligne = uneTable.getSelectedRow(); 
					txtIdarticles.setText(unTableau.getValueAt(ligne,1).toString());
					txtPrix.setText(unTableau.getValueAt(ligne,3).toString());
					//pas de modif pour le combo statut et type et idsalarie 
					txtNom.setText(unTableau.getValueAt(ligne,6).toString());
					btEnregistrer.setText("Modifier");
				}
				
			}
		});
		
		
		//placement zone de recherche 
		this.txtMot.setBounds(500, 30, 140, 20);
		this.add(this.txtMot); 
		this.btFiltrer.setBounds(680, 30, 100, 20);
		this.add(this.btFiltrer);
		this.btFiltrer.addActionListener(this);
		
		this.setVisible(true);
		
	}
	public Object[][] remplirDonnees (String mot)
	{
		//cette méthode transforme l'ArrayList des Evenements en une matrice
		ArrayList<Articles> lesArticles = Main.selectAllArticles(mot);
		Object [][] matrice = new Object [lesArticles.size()][3];
		int i =0; 
		for (Articles unArticle : lesArticles)
		{
			matrice [i][0] = unArticle.getIdarticles();
			matrice [i][1] = unArticle.getPrix();
			matrice [i][2] = unArticle.getNom();
			i++ ; 
		}
		return matrice;  
	}
	
	public void remplirCBXArticles () {
		//cette méthode remplit le select (combo) des salaries 
		ArrayList<Articles> lesArticles = Main.selectAllArticles(""); 
		for (Articles unArticle : lesArticles)
		{
			this.cbxNom.addItem(unArticle.getIdarticles()+" - " +unArticle.getPrix()+" - "+unArticle.getNom());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == this.btRetour) {
			 this.dispose();   
			 Main.rendreVisible(true);
		 }
		 else if (e.getSource() == this.btAnnuler)
		 {
			 this.viderChamps (); 
		 }
		 else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Enregistrer"))
		 {
			 
			 float Prix = Integer.parseInt(this.txtPrix.getText()); 
			 String Nom = this.txtNom.getText();
			 
			 
			 Articles unArticle = new Articles ( Prix, Nom);
			 
			 Main.insertArticles(unArticle);
			 
			 unArticle = Main.selectWhereArticles( Prix, Nom); 
			 JOptionPane.showMessageDialog(this, "Insertion réussie de la commande");
			 Object ligne[] = {unArticle.getIdarticles(), Prix, Nom}; 
			 this.unTableau.ajouterLigne(ligne);
			 this.viderChamps(); 
		 }
		 else if (e.getSource() == this.btFiltrer)
		 {
			 String mot = this.txtMot.getText(); 
			 this.unTableau.setDonnees(this.remplirDonnees(mot));
		 }
		 else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Modifier"))
		 {
			 
			 float Prix = Integer.parseInt(this.txtPrix.getText()); 
			 String Nom = this.txtNom.getText();
			
			 int numero = this.uneTable.getSelectedRow();
			 
			 int Idarticles = Integer.parseInt(unTableau.getValueAt(numero,0).toString()) ;
				
			 Articles unArticleID = Main.selectWhereArticles( Idarticles);
			 
			 Articles unArticle = new Articles (unArticleID.getIdarticles(), Prix,
					 Nom);
			 
			 //mise a jour dans la base de données 
			 Main.updateArticles(unArticle);
			 //recuperer numero ligne
			 
			 
			 //actualiser l'affichage 
			 Object [] ligne = {unArticleID.getIdarticles(),Idarticles, Prix, Nom};
			 this.unTableau.modifierLigne(numero, ligne);
			 
			 JOptionPane.showMessageDialog(this, "Modification réussie dans la Base de données");
			 this.viderChamps();
			 this.btEnregistrer.setText("Enregistrer");
		 }
		 
	}
	
	public void viderChamps () {
		this.txtIdarticles.setText("");
		this.txtPrix.setText("");
		this.txtNom.setText("");
	}

}
