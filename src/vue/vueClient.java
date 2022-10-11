package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Client;
import controleur.Main;
import controleur.User;
import controleur.Tableau;
 

public class vueClient extends JFrame implements ActionListener
{
	private JButton btRetour = new JButton("Retour au Menu");
	
	//panel de saisie d'un salarié 
	private JPanel panelAjout = new JPanel(); 
	private JTextField txtNom = new JTextField(); 
	private JTextField txtPrenom = new JTextField(); 
	private JTextField txtEmail = new JTextField();
	private JTextField txtDate_naissance = new JTextField();
	private JTextField txtTel = new JTextField();
	private JTextField txtAdresse = new JTextField();
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	//panel d'affichage des salariés 
	private JPanel panelLister = new JPanel(); 
	private JTable uneTable ; 
	private JScrollPane uneScroll ; 
	private Tableau unTableau ; 
	
	//rechercher par mot clé 
	private JTextField txtMot = new JTextField(); 
	private JButton btFiltrer = new JButton("Filtrer"); 
	
	public vueClient()
	{
		this.setTitle("Gestion des clients" );
		this.setBounds(200, 200, 900, 500);
		this.setResizable(false); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color (0, 0, 100));
		this.setLayout(null);
		
		this.btRetour.setBounds(750, 450, 130, 20);
		this.add(this.btRetour);
		this.btRetour.addActionListener(this);
		
		//construction du Panel Ajout 
		this.panelAjout.setBounds(40, 60,340, 360);
		this.panelAjout.setBackground(new Color (0, 0, 100));
		this.panelAjout.setLayout(new GridLayout(7,2));
		
		
		JLabel lbNom = new JLabel("Nom : ");
		lbNom.setForeground(Color.white);
		this.panelAjout.add(lbNom); 
		this.panelAjout.add(this.txtNom);
		JLabel lbPrenom = new JLabel("Prenom : ");
		lbPrenom.setForeground(Color.white);
		this.panelAjout.add(lbPrenom);
		this.panelAjout.add(this.txtPrenom);
		JLabel lbEmail = new JLabel("Email : ");
		lbEmail.setForeground(Color.white);
		this.panelAjout.add(lbEmail); 
		this.panelAjout.add(this.txtEmail);
		JLabel lbDate_naissance = new JLabel("Date de naissance : ");
		lbDate_naissance.setForeground(Color.white);
		this.panelAjout.add(lbDate_naissance);
		this.panelAjout.add(this.txtDate_naissance);
		JLabel lbTel = new JLabel("Tel : ");
		lbTel.setForeground(Color.white);
		this.panelAjout.add(lbTel); 
		this.panelAjout.add(this.txtTel);
		JLabel lbAdresse = new JLabel("Adresse : ");
		lbAdresse.setForeground(Color.white);
		this.panelAjout.add(lbAdresse);
		this.panelAjout.add(this.txtAdresse);
		
		this.panelAjout.add(this.btAnnuler);
		this.panelAjout.add(this.btEnregistrer);
		this.add(this.panelAjout);
		
		//Rendre les boutons cliquables 
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		
		//installation du panel lister 
		this.panelLister.setBounds(420, 60,480, 400);
		this.panelLister.setBackground(new Color (0, 0, 100));
		this.panelLister.setLayout(null);
		
		String entetes [] = {"Idclient", "Nom", "Prenom", "Email", "Date_naissance", "Tel", "Adresse"};
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
						//on récupère l'id du User à supprimer 
						int idClient = Integer.parseInt(unTableau.getValueAt(ligne,0).toString()) ;
						//on supprime dans la base données via le modele
						Main.deleteUser(idClient);
						//suppression de la ligne de l'affichage 
						unTableau.supprimerLigne(ligne);
					}
				}
				else if (e.getClickCount()==1) {
					int ligne = uneTable.getSelectedRow(); 
					txtNom.setText(unTableau.getValueAt(ligne,1).toString());
					txtPrenom.setText(unTableau.getValueAt(ligne,2).toString());
					txtEmail.setText(unTableau.getValueAt(ligne,3).toString());
					txtDate_naissance.setText(unTableau.getValueAt(ligne,4).toString());
					txtTel.setText(unTableau.getValueAt(ligne,5).toString());
					txtAdresse.setText(unTableau.getValueAt(ligne,6).toString());
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

	public Object [][] remplirDonnees (String mot)
	{
		//cette méthode transforme l'ArrayList des Clients en une matrice
		ArrayList<Client> lesClients = Main.selectAllClients(mot);
		Object [][] matrice = new Object [lesClients.size()][7];
		int i =0; 
		for (Client unClient : lesClients)
		{
			matrice [i][0] = unClient.getIdclient();
			matrice [i][1] = unClient.getNom();
			matrice [i][2] = unClient.getPrenom();
			matrice [i][3] = unClient.getEmail();
			matrice [i][4] = unClient.getDate_naissance();
			matrice [i][5] = unClient.getTel();
			matrice [i][6] = unClient.getAdresse();
			i++ ; 
		}
		return matrice; 
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == this.btRetour) {
			 this.dispose(); // je détruit la vue actuelle 
			 //et je rends visible la vue connexion avec son menu 
			 Main.rendreVisible(true);
		 }
		 else if (e.getSource() == this.btAnnuler)
		 {
			 this.viderChamps () ; 
		 } 
		 else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Enregistrer"))
		 {
			 String Nom = this.txtNom.getText(); 
			 String Prenom = this.txtPrenom.getText(); 
			 String Email = this.txtEmail.getText(); 
			 String Date_naissance = this.txtDate_naissance.getText(); 
			 String Tel = this.txtTel.getText(); 
			 String Adresse = new String (this.txtAdresse.getText());
			 //instanciation du User 
			 Client unClient = new Client (Nom, Prenom, Email, Date_naissance, Tel, Adresse);
			 //insertion dans la base de données 
			 Main.insertClient(unClient);
			 
			 //recuperation de l'ID User affecte par le SGBD 
			 unClient = Main.selectWhereClient( Nom, Prenom); 
			 
			 //actualiser l'affichage 
			 Object [] ligne = {unClient.getIdclient(), Nom, Prenom, Email, Date_naissance, Tel, Adresse};
			 this.unTableau.ajouterLigne(ligne);
			 
			 JOptionPane.showMessageDialog(this, "Insertion réussie dans la Base de données");
			 this.viderChamps();
			 
		 }
		 else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Modifier"))
		 {
			 String Nom = this.txtNom.getText(); 
			 String Prenom = this.txtPrenom.getText(); 
			 String Email = this.txtEmail.getText(); 
			 String Date_naissance = new String (this.txtDate_naissance.getText());
			 String Tel = this.txtTel.getText(); 
			 String Adresse = new String (this.txtAdresse.getText());
			 //recuperer l'ID du User
			 Client unClientId = Main.selectWhereClient( Nom, Prenom);
			 //instanciation du User 
			 Client unClient = new Client (unClientId.getIdclient(), Nom, Prenom, Email, Date_naissance, Tel, Adresse);
			 //mise a jour dans la base de données 
			 Main.updateClient(unClient);
			 //recuperer numero ligne
			 int numero = this.uneTable.getSelectedRow();
			 
			 //actualiser l'affichage 
			 Object [] ligne = {unClientId.getIdclient(), Nom, Prenom, Email, Date_naissance, Tel, Adresse};
			 this.unTableau.modifierLigne(numero, ligne);
			 
			 JOptionPane.showMessageDialog(this, "Modification réussie dans la Base de données");
			 this.viderChamps();
			 this.btEnregistrer.setText("Enregistrer");
		 }
		 else if (e.getSource() == this.btFiltrer)
		 {
			 String mot = this.txtMot.getText(); 
			 this.unTableau.setDonnees(this.remplirDonnees(mot));
		 }
		
	}
	public void viderChamps() {
		this.txtNom.setText("");
		this.txtPrenom.setText("");
		this.txtEmail.setText("");
		this.txtDate_naissance.setText("");
		this.txtTel.setText("");
		this.txtAdresse.setText("");
		this.btEnregistrer.setText("Enregistrer");
	}
}