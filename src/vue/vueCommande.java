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

import controleur.Commande;
import controleur.Main;
import controleur.User;
import controleur.Tableau;

public class vueCommande extends JFrame implements ActionListener
{
	private JButton btRetour = new JButton("Retour au menu");
	private JComboBox<String> cbxIdclient = new JComboBox<String>();
	
	//déclaration du panel ajout
	private JPanel panelAjout = new JPanel(); 
	private JPanel panelLister = new JPanel();
	private JTextField txtIdcommande = new JTextField(); 
	private JTextField txtDate_commande = new JTextField();
	private JTextField txtIdclient = new JTextField(); 
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
			
	//panel lister : gestion du tableau des evenements 
	private JTable uneTable; 
	private Tableau unTableau; 
	private JScrollPane uneScroll; 
	
	//rechercher par mot clé 
	private JTextField txtMot = new JTextField(); 
	private JButton btFiltrer = new JButton("Filtrer");
	
	public vueCommande()
	{
		this.setTitle("Gestion des Commandes de Chez Nouchi" );
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
		
		
		JLabel lbDate_commande = new JLabel("Date_commande : ");
		lbDate_commande.setForeground(Color.white);
		this.panelAjout.add(lbDate_commande); 
		this.panelAjout.add(this.txtDate_commande);
		JLabel lbIdclient = new JLabel("Idclient : ");
		lbIdclient.setForeground(Color.white);
		this.panelAjout.add(lbIdclient);
		this.panelAjout.add(this.txtIdclient);
		this.panelAjout.add(this.btAnnuler); 
		this.panelAjout.add(this.btEnregistrer);
		this.add(this.panelAjout);
		//remplir le CBX Salarie 
		this.remplirCBXSalaries();
		
		//rendre les deux boutons cliquables 
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		
		//installation du panel lister 
		this.panelLister.setBounds(420, 60,480, 400);
		this.panelLister.setBackground(new Color (0, 0, 100));
		this.panelLister.setLayout(null);
		
		String entetes [] = {"Id_Commande", "Date_commande", "Idclient"};
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
						int idCommande = Integer.parseInt(unTableau.getValueAt(ligne,0).toString()) ;
						//on supprime dans la base données via le modele
						Main.deleteCommande(idCommande);
						//suppression de la ligne de l'affichage 
						unTableau.supprimerLigne(ligne);
					}
				}
				else if (e.getClickCount()==1) {
					int ligne = uneTable.getSelectedRow(); 
					txtIdcommande.setText(unTableau.getValueAt(ligne,1).toString());
					txtDate_commande.setText(unTableau.getValueAt(ligne,3).toString());
					//pas de modif pour le combo statut et type et idsalarie 
					txtIdclient.setText(unTableau.getValueAt(ligne,6).toString());
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
		ArrayList<Commande> lesCommandes = Main.selectAllCommandes(mot);
		Object [][] matrice = new Object [lesCommandes.size()][3];
		int i =0; 
		for (Commande uneCommande : lesCommandes)
		{
			matrice [i][0] = uneCommande.getIdcommande();
			matrice [i][1] = uneCommande.getdate_commande();
			matrice [i][2] = uneCommande.getidclient();
			i++ ; 
		}
		return matrice; 
	}
	
	public void remplirCBXSalaries () {
		//cette méthode remplit le select (combo) des salaries 
		ArrayList<Commande> lesCommandes = Main.selectAllCommandes(""); 
		for (Commande uneCommande : lesCommandes)
		{
			this.cbxIdclient.addItem(uneCommande.getIdcommande()+" - " +uneCommande.getdate_commande()+" - "+uneCommande.getidclient());
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
			 
			 String Date_Commande = this.txtDate_commande.getText();
			 int Idclient = Integer.parseInt(this.txtIdclient.getText()); 
			 //String chaine = this.cbxIdclient.getSelectedItem().toString(); 
			 //String tab [] = chaine.split(" - "); 
			 //int IdClient = Integer.parseInt(tab[0]); 
			 
			 Commande uneCommande = new Commande (Date_Commande, Idclient);
			 
			 Main.insertCommande(uneCommande);
			 
			 uneCommande = Main.selectWhereCommande(Idclient, Date_Commande); 
			 JOptionPane.showMessageDialog(this, "Insertion réussie de la commande");
			 Object ligne[] = {uneCommande.getIdcommande(), Date_Commande, Idclient}; 
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
			 String idcommande = this.txtIdcommande.getText(); 
			 String date_commande = this.txtDate_commande.getText();
			 int idclient = Integer.parseInt(this.txtIdclient.getText()); 
			 String chaine = this.cbxIdclient.getSelectedItem().toString(); 
			 String tab [] = chaine.split(" - ");
			 
			 int numero = this.uneTable.getSelectedRow();
			 
			 int idCommande = Integer.parseInt(unTableau.getValueAt(numero,0).toString()) ;
				
			 Commande uneCommandeID = Main.selectWhereCommande(idclient, date_commande);
			 
			 Commande uneCommande = new Commande (uneCommandeID.getIdcommande(), date_commande,
					 idclient);
			 
			 //mise a jour dans la base de données 
			 Main.updateCommande(uneCommande);
			 //recuperer numero ligne
			 
			 
			 //actualiser l'affichage 
			 Object [] ligne = {uneCommandeID.getIdcommande(),date_commande, idclient};
			 this.unTableau.modifierLigne(numero, ligne);
			 
			 JOptionPane.showMessageDialog(this, "Modification réussie dans la Base de données");
			 this.viderChamps();
			 this.btEnregistrer.setText("Enregistrer");
		 }
		 
	}
	public void viderChamps () {
		this.txtDate_commande.setText("");
		this.txtIdcommande.setText("");
		this.txtIdclient.setText("");
	}

}
