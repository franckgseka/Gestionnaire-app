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

import controleur.Main;
import controleur.User;
import controleur.Tableau;
 

public class vueUser extends JFrame implements ActionListener
{
	private JButton btRetour = new JButton("Retour au Menu");
	
	//panel de saisie d'un salarié 
	private JPanel panelAjout = new JPanel(); 
	private JTextField txtRole = new JTextField(); 
	private JTextField txtUsername = new JTextField(); 
	private JTextField txtEmail = new JTextField();
	private JPasswordField txtPassword = new JPasswordField();
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
	
	public vueUser()
	{
		this.setTitle("Gestion des Users" );
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
		
		
		JLabel lbRole = new JLabel("Role : ");
		lbRole.setForeground(Color.white);
		this.panelAjout.add(lbRole); 
		this.panelAjout.add(this.txtRole);
		JLabel lbUsername = new JLabel("Username : ");
		lbUsername.setForeground(Color.white);
		this.panelAjout.add(lbUsername);
		this.panelAjout.add(this.txtUsername);
		JLabel lbEmail = new JLabel("Email : ");
		lbEmail.setForeground(Color.white);
		this.panelAjout.add(lbEmail); 
		this.panelAjout.add(this.txtEmail);
		JLabel lbPassword = new JLabel("Password : ");
		lbPassword.setForeground(Color.white);
		this.panelAjout.add(lbPassword);
		this.panelAjout.add(this.txtPassword);
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
		
		String entetes [] = {"Id", "Role", "Username", "Email", "Password", "Tel", "Adresse"};
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
						int idUser = Integer.parseInt(unTableau.getValueAt(ligne,0).toString()) ;
						//on supprime dans la base données via le modele
						Main.deleteUser(idUser);
						//suppression de la ligne de l'affichage 
						unTableau.supprimerLigne(ligne);
					}
				}
				else if (e.getClickCount()==1) {
					int ligne = uneTable.getSelectedRow(); 
					txtRole.setText(unTableau.getValueAt(ligne,1).toString());
					txtUsername.setText(unTableau.getValueAt(ligne,2).toString());
					txtEmail.setText(unTableau.getValueAt(ligne,3).toString());
					txtPassword.setText(unTableau.getValueAt(ligne,4).toString());
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
		//cette méthode transforme l'ArrayList des Users en une matrice
		ArrayList<User> lesUsers = Main.selectAllUsers(mot);
		Object [][] matrice = new Object [lesUsers.size()][7];
		int i =0; 
		for (User unUser : lesUsers)
		{
			matrice [i][0] = unUser.getId();
			matrice [i][1] = unUser.getRole();
			matrice [i][2] = unUser.getUsername();
			matrice [i][3] = unUser.getEmail();
			matrice [i][4] = unUser.getPassword();
			matrice [i][5] = unUser.getTel();
			matrice [i][6] = unUser.getAdresse();
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
			 String role = this.txtRole.getText(); 
			 String username = this.txtUsername.getText(); 
			 String email = this.txtEmail.getText(); 
			 String password = new String (this.txtPassword.getPassword());
			 String tel = this.txtTel.getText(); 
			 String adresse = new String (this.txtAdresse.getText());
			 //instanciation du User 
			 User unUser = new User ( role, username, email, password, tel, adresse);
			 //insertion dans la base de données 
			 Main.insertUser(unUser);
			 
			 //recuperation de l'ID User affecte par le SGBD 
			 unUser = Main.selectWhereUser(email, password); 
			 
			 //actualiser l'affichage 
			 Object [] ligne = {unUser.getId(), role, username, email, password, tel, adresse};
			 this.unTableau.ajouterLigne(ligne);
			 
			 JOptionPane.showMessageDialog(this, "Insertion réussie dans la Base de données");
			 this.viderChamps();
			 
		 }
		 else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Modifier"))
		 {
			 String role = this.txtRole.getText(); 
			 String username = this.txtUsername.getText(); 
			 String email = this.txtEmail.getText(); 
			 String password = new String (this.txtPassword.getPassword());
			 String tel = this.txtTel.getText(); 
			 String adresse = new String (this.txtAdresse.getText());
			 //recuperer l'ID du User
			 User unUserId = Main.selectWhereUser(email, password);
			 //instanciation du User 
			 User unUser = new User (unUserId.getId(), role, username, email, password, tel, adresse);
			 //mise a jour dans la base de données 
			 Main.updateUser(unUser);
			 //recuperer numero ligne
			 int numero = this.uneTable.getSelectedRow();
			 
			 //actualiser l'affichage 
			 Object [] ligne = {unUserId.getId(), role, username, email, password, tel, adresse};
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
		this.txtRole.setText("");
		this.txtUsername.setText("");
		this.txtEmail.setText("");
		this.txtPassword.setText("");
		this.txtTel.setText("");
		this.txtAdresse.setText("");
		this.btEnregistrer.setText("Enregistrer");
	}
}