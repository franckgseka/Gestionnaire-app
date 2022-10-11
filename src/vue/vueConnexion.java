package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controleur.Main;
import controleur.User;

public class vueConnexion extends JFrame implements ActionListener, KeyListener
{
	//panel Connexion 
	private JPanel panelCon = new JPanel() ; 
	private JTextField txtEmail = new JTextField();
	private JTextField txtConnexion = new JTextField(); 
	private JPasswordField txtMdp = new JPasswordField(); 
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btSeConnecter = new JButton("Se Connecter");

	
	//panel Menu 
	private JPanel panelMenu = new JPanel() ;
	private JButton btClients = new JButton("Gérer les Clients");
	private JButton btUsers = new JButton("Gérer les Utilisateurs");
	private JButton btCommandes = new JButton("Gérer les commandes");
	private JButton btArticles = new JButton("Gérer les Articles");
	private JButton btQuitter = new JButton("Quitter");
	
	
	
	public vueConnexion()
	{
		this.setTitle("Connexion à l'application du restaurant Chez Nouchi" );
		this.setBounds(200, 200, 850, 500);
		this.setResizable(false); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color (0, 0, 100));
		this.setLayout(null);
		
		
		//insertion du logo 
		ImageIcon uneImage = new ImageIcon("src/image/burgeraccueil.png"); 
		JLabel monLogo = new JLabel(uneImage); 
		monLogo.setBounds(30, 20, 220, 230);
		this.add(monLogo); 

		//insertion du pannel Connexion 
		
		this.panelCon.setBounds(280, 40, 520, 400);
		this.panelCon.setBackground(new Color (0, 0, 100));
		this.panelCon.setLayout(new GridLayout(3,2));
		

		JLabel lbEmail = new JLabel("Email : ");
		lbEmail.setForeground(Color.white);
		this.panelCon.add(lbEmail); 
		this.panelCon.add(this.txtEmail);
		JLabel lbMDP = new JLabel("MDP : ");
		lbMDP.setForeground(Color.white);
		this.panelCon.add(lbMDP);
		this.panelCon.add(this.txtMdp);
		this.panelCon.add(this.btAnnuler); 
		this.panelCon.add(this.btSeConnecter); 
		//insertion du panel dans la fenetre
		this.add(this.panelCon);
		
		//rendre les boutons ecoutables 
		this.btAnnuler.addActionListener(this);
		this.btSeConnecter.addActionListener(this);
		
		//rendre les TXT ecoutables sur frappe de touche 
		this.txtEmail.addKeyListener(this);
		this.txtMdp.addKeyListener(this);
		
		//construction du Panel Menu
		this.panelMenu.setBounds(280, 40, 520, 300);
		this.panelMenu.setBackground(new Color (0, 0, 100));
		this.panelMenu.setLayout(new GridLayout(2,2));
		this.panelMenu.add(this.btUsers);
		this.panelMenu.add(this.btCommandes);
		this.panelMenu.add(this.btArticles);
		this.panelMenu.add(this.btClients);
		this.panelMenu.setVisible(false);
		this.add(this.panelMenu);
		
		//rendre les boutons du panel Menu écoutables 
		this.btUsers.addActionListener(this);
		this.btCommandes.addActionListener(this);
		this.btArticles.addActionListener(this);
		this.btClients.addActionListener(this);
		this.btQuitter.setBounds(240, 340, 100, 30);
		this.add(this.btQuitter);
		this.btQuitter.setVisible(false);
		this.btQuitter.addActionListener(this);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 
		if (e.getSource() == this.btAnnuler)
		{
			this.txtEmail.setText("");
			this.txtMdp.setText("");
			
		}else if (e.getSource() == this.btSeConnecter)
		{
			this.traitement(); 
		}else if (e.getSource() == this.btQuitter) {
			int retour = JOptionPane.showConfirmDialog(this, "Voulez-Vous quitter l'application ?", 
					"Quitter l'application", JOptionPane.YES_NO_OPTION);
			if (retour ==0) {
					this.txtEmail.setText("");
					this.txtMdp.setText("");
					this.panelCon.setVisible(true);
					this.panelMenu.setVisible(false);
				}
		}
		else if (e.getSource() == this.btUsers)
		{
			//on cache la fenetre de connexion et on ouvre la fenetre des salariés 
			Main.rendreVisible(false);
			
			Main.instanciervueUser();
		}
		else if (e.getSource() == this.btCommandes)
		{
			//on cache la fenetre de connexion et on ouvre la fenetre des evenements 
			Main.rendreVisible(false);
			
			Main.instanciervueCommande();
		}
		else if (e.getSource() == this.btArticles)
		{
			//on cache la fenetre de connexion et on ouvre la fenetre des evenements 
			Main.rendreVisible(false);
			
			Main.instanciervueArticles();
		}
		else if (e.getSource() == this.btClients)
		{
			//on cache la fenetre de connexion et on ouvre la fenetre des evenements 
			Main.rendreVisible(false);
			this.btQuitter.setVisible(true);
			Main.instanciervueClients();
			
		}
	}
	public void traitement() {
		String email = this.txtEmail.getText(); 
		String mdp = new String (this.txtMdp.getPassword()); 
		//on verifie dans la base de données en passant par le controleur 
		User unUser = Main.selectWhereUser(email, mdp); 
		if (unUser ==null)
		{
			JOptionPane.showMessageDialog(this," Veuillez vérifier vos identifiants !", "Erreur de connexion", 
					JOptionPane.ERROR_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(this," Bienvenue M/Me "+unUser.getUsername(),
					"Connexion réussie à l'application Chez Nouchi", JOptionPane.INFORMATION_MESSAGE);
			this.panelCon.setVisible(false);
			this.panelMenu.setVisible(true);
			this.btQuitter.setVisible(true);
			
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		 if (e.getKeyCode() == KeyEvent.VK_ENTER)
		 {
			 this.traitement();
		 }
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}