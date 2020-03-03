package model;

import java.time.LocalDate;

public class Emprunt {
    private int id;
    private Membre membre;
    private Livre livre;
    private LocalDate dateEmprunt;
    private LocalDate dateRetour;

    public Emprunt(int id, Membre membre, Livre livre, LocalDate dateEmprunt, LocalDate dateRetour) {
        this.id = id;
        this.membre = membre;
        this.livre = livre;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
    }

    public Emprunt(int id, Membre membre, Livre livre, LocalDate dateEmprunt) {
        this.id = id;
        this.membre = membre;
        this.livre = livre;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = null;
    }

    public int get_id() {
        return id;
    }

    public void set_id(int id) {
        this.id = id;
    }

    public Membre get_membre() {
        return membre;
    }

    public void set_membre(Membre membre) {
        this.membre = membre;
    }

    public Livre get_livre() {
        return livre;
    }

    public void set_livre(Livre livre)
        this.livre= livre;
    }

    public LocalDate get_dateEmprunt() {
        return dateEmprunt;
    }

    public void set_dateEmprunt(LocalDate date) {
        dateEmprunt = date;
    }

    public LocalDate get_dateRetour() {
        return dateRetour;
    }

    public void set_dateRetour(LocalDate date) {
        dateRetour = date;
    }

    @Override
    public String toString() {
        return "Emprunt " + id + " : livre " + livre.get_titre() + " par " + membre.get_prenom() + " "
                + membre.get_nom() + " le " + dateEmprunt + " a rendre le " + dateRetour + "\n";
    }
}
