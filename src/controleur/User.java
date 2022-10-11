package controleur;

public class User {

	private int id;
	private String role,username, email, password, tel, adresse;
	public User(int id, String role, String username, String email, String password, String tel,
			String adresse) {
		super();
		this.id = id;
		this.role = role;
		this.username = username;
		this.email = email;
		this.password = password;
		this.tel = tel;
		this.adresse = adresse;
	}
	
	public User( String role, String username, String email, String password, String tel,
			String adresse) {
		super();
		this.id = 0;
		this.role = role;
		this.username = username;
		this.email = email;
		this.password = password;
		this.tel = tel;
		this.adresse = adresse;
	}
	
	public User() {
		super();
		this.id =0;
		this.role = "";
		this.username = "";
		this.email = "";
		this.password = "";
		this.tel = "";
		this.adresse = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

