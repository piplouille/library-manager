package model;

import java.time.LocalDate;

public class Emprunt {
    private int id;
    private int idMembre;
    private int idLivre;
    private LocalDate dateEmprunt;
    private LocalDate dateRetour;

    public Emprunt(int id, int idMembre, int idLivre, LocalDate dateEmprunt, LocalDate dateRetour) {
        this.id = id;
        this.idMembre = idMembre;
        this.idLivre = idLivre;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
    }

    public Emprunt(int id, int idMembre, int idLivre, LocalDate dateEmprunt) {
        this.id = id;
        this.idMembre = idMembre;
        this.idLivre = idLivre;
        this.dateEmprunt = dateEmprunt;
    }

    public int get_id() {
        return id;
    }

    public void set_id(int id) {
        this.id = id;
    }

    public int get_idMembre() {
        return idMembre;
    }

    public void set_idMembre(int id) {
        idMembre = id;
    }

    public int get_idLivre() {
        return idLivre;
    }

    public void set_idLivre(int id) {
        idLivre = id;
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
}
