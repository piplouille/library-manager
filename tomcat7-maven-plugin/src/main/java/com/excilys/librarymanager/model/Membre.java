package com.excilys.librarymanager.model;

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
	
    public int getKey() {
        return key;
    }
    public void setKey(int key) {
        this.key = key;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Abonnement getAbonnement() {
        return abonnement;
    }
    public void setAbonnement(Abonnement abonnement) {
        this.abonnement = abonnement;
    }

    @Override
    public String toString() {
        return "Le membre " + prenom + " " + nom + " " + adresse + " " + email + " " + telephone + " " + abonnement.toString() + "\n";
    }
}