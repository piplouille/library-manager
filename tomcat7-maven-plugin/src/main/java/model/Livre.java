package model;

public class Livre {
    private int id;
    private String titre;
    private String auteur;
    private String isbn;

    public Livre(int id , String titre, String auteur, String isbn)
    {
        this.id=id;
        this.titre=titre;
        this.auteur=auteur;
        this.isbn=isbn;
    }

    public int get_id() {
        return id;
    }

    public void set_id(int id) {
        this.id = id;
    }

    public String get_titre() {
        return titre;
    }

    public void set_titre(String titre) {
        this.titre = titre;
    }

    public String get_auteur() {
        return auteur;
    }

    public void set_auteur(String auteur) {
        this.auteur = auteur;
    }

    public String get_isbn() {
        return isbn;
    }

    public void set_isbn(String isbn) {
        this.isbn = isbn;
    }

}