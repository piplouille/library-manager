package com.excilys.librarymanager;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.excilys.librarymanager.service.MembreServiceImpl;
import com.excilys.librarymanager.model.Livre;
import com.excilys.librarymanager.model.Membre;
import com.excilys.librarymanager.model.Emprunt;
import com.excilys.librarymanager.model.Abonnement;
import com.excilys.librarymanager.exception.ServiceException;
import java.time.LocalDate;

@RunWith(Parameterized.class)
public class TestServiceM {

    @Parameters
    public static Collection<Object[]> val() {
        return Arrays.asList(
                new Object[][] { { "Debesse", "Laetitia", "1024 Bvd des Marechaux", "debesse@ensta.fr", "5462" },
                        { "Becker", "Madeleine", "1024 Bvd des Marechaux", "madeleine@ensta.fr", "0600" } });
    }

    String nom;
    String prenom;
    String adresse;
    String email;
    String telephone;

    MembreServiceImpl mSImpl;

    public TestServiceM(String nom, String prenom, String adresse, String email, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.telephone = telephone;
        this.mSImpl = MembreServiceImpl.getInstance();
    }

    @Test
    public void test() {
        int id = Integer.MAX_VALUE;
        try {
            id = mSImpl.create(nom, prenom, adresse, email, telephone);
            assertNotEquals((long) Integer.MAX_VALUE, (long) id);
        } catch (ServiceException e) {
            System.out.println("Ajout membre Ã©chouÃ© : " + e.getLocalizedMessage());
        }
        try {
            List<Membre> liste = mSImpl.getList();
            assertNotNull(liste);
        } catch (ServiceException e) {
            System.out.println("Liste membres Ã©chouÃ©e : " + e.getLocalizedMessage());
        }
        try {
            List<Membre> liste = mSImpl.getListMembreEmpruntPossible();
            assertNotNull(liste);
        } catch (ServiceException e) {
            System.out.println("Liste membres emprunts possibles Ã©chouÃ©e : " + e.getLocalizedMessage());
        }
        try {
            Membre membre = mSImpl.getById(id);
            assertNotNull(membre);
        } catch (ServiceException e) {
            System.out.println("Membre par id Ã©chouÃ©e : " + e.getLocalizedMessage());
        }
    }

}