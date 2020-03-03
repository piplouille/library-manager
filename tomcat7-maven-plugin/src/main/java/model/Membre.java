package model;

public class Membre {
	private int key;
	private String nom;
	private String prenom;
    private String adresse;
    private String email;
    private String telephone;
    private Abonnement abonnement;
	
	public Membre(int key, String nom, String prenom, String adresse,
        String email, String telephone, Abonnement abonnement) {
		this.key = key;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.telephone = telephone;
        this.abonnement = abonnement;
	}
	
    public int get_key() {
        return key;
    }
    public void set_key(int key) {
        this.key = key;
    }

    public String get_nom() {
        return nom;
    }
    public void set_nom(String nom) {
        this.nom = nom;
    }

    public String get_prenom() {
        return prenom;
    }
    public void set_prenom(String prenom) {
        this.prenom = prenom;
    }
    
    public String get_adresse() {
        return adresse;
    }
    public void set_adresse(String adresse) {
        this.adresse = adresse;
    }
    
    public String get_email() {
        return email;
    }
    public void set_email(String email) {
        this.email = email;
    }

    public String get_telephone() {
        return telephone;
    }
    public void set_telephone(String telephone) {
        this.telephone = telephone;
    }

    public Abonnement get_abonnement() {
        return abonnement;
    }
    public void set_abonnement(Abonnement abonnement) {
        this.abonnement = abonnement;
    }
}