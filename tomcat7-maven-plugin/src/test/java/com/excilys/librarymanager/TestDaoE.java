package com.excilys.librarymanager;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.excilys.librarymanager.dao.EmpruntDaoImpl;
import com.excilys.librarymanager.model.Livre;
import com.excilys.librarymanager.model.Membre;
import com.excilys.librarymanager.model.Emprunt;
import com.excilys.librarymanager.model.Abonnement;
import com.excilys.librarymanager.exception.DaoException;
import java.time.LocalDate;

@RunWith(Parameterized.class)
public class TestDaoE {

    @Parameters
    public static Collection<Object[]> val() {
        return Arrays.asList(new Object[][] { { 0, 0, LocalDate.now() }, { 1, 1, LocalDate.now() } });
    }

    int idMembre;
    int idLivre;
    LocalDate dateEmprunt;

    EmpruntDaoImpl eDaoImpl;

    public TestDaoE(int idMembre, int idLivre, LocalDate dateEmprunt) {
        this.idMembre=idMembre;
        this.idLivre=idLivre;
        this.dateEmprunt=dateEmprunt;
        this.eDaoImpl = EmpruntDaoImpl.getInstance();
    }

    @Test
    public void test() {
        int id = 0;
        try {
            eDaoImpl.create(idMembre, idLivre, dateEmprunt);
        } catch (DaoException e) {
            System.out.println("Ajout emprunt Ã©chouÃ© : " + e.getLocalizedMessage());
        }
        try {
            List<Emprunt> liste = eDaoImpl.getList();
            assertNotNull(liste);
        } catch (DaoException e) {
            System.out.println("Liste emprunts Ã©chouÃ©e : " + e.getLocalizedMessage());
        }
        try {
            List<Emprunt> liste = eDaoImpl.getListCurrent();
            assertNotNull(liste);
        } catch (DaoException e) {
            System.out.println("Liste emprunts en cours Ã©chouÃ©e : " + e.getLocalizedMessage());
        }
        try {
            List<Emprunt> liste = eDaoImpl.getListCurrentByMembre(id);
            assertNotNull(liste);
        } catch (DaoException e) {
            System.out.println("Liste emprunts en cours par id membre Ã©chouÃ©e : " + e.getLocalizedMessage());
        }
        try {
            List<Emprunt> liste = eDaoImpl.getListCurrentByLivre(id);
            assertNotNull(liste);
        } catch (DaoException e) {
            System.out.println("Liste emprunts courants par id livre Ã©chouÃ©e : " + e.getLocalizedMessage());
        }
        try {
            Emprunt emprunt = eDaoImpl.getById(id);
            assertNotNull(emprunt);
        } catch (DaoException e) {
            System.out.println("Emprunt par id Ã©chouÃ© : " + e.getLocalizedMessage());
        }
        try {
            Livre livre = new Livre(id,"Vol de nuit","Antoine de Saint Exupery","9791155515563");
            Membre membre = new Membre(id, "Debesse", "Laetitia", "1024 boulevard des Marechaux", "debesse@ensta.f", "5135", Abonnement.BASIC);
            eDaoImpl.update(new Emprunt(id, membre, livre, dateEmprunt));
        } catch (DaoException e) {
            System.out.println("Maj emprunt Ã©chouÃ©e : " + e.getLocalizedMessage());
        }
        try{
            int count = eDaoImpl.count();
            assertNotEquals((long) Integer.MAX_VALUE, (long)count);
        }
        catch (DaoException e){
            System.out.println("Comptage emprunts Ã©chouÃ©e : " + e.getLocalizedMessage());
        }

    }
}
