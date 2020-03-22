package com.excilys.librarymanager;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.excilys.librarymanager.dao.LivreDaoImpl;
import com.excilys.librarymanager.model.Livre;
import com.excilys.librarymanager.exception.DaoException;

@RunWith(Parameterized.class)
public class TestDaoL {

    @Parameters
    public static Collection<Object[]> val() {
        return Arrays.asList(new Object[][] { { "Le Petit Prince", "Antoine de Saint Exupery", "9791187192596" },
                { "Les Fleurs du Mal", "Baudelaire", "9789985929131" } });
    }

    String titre;
    String auteur;
    String isbn;

    LivreDaoImpl lDaoImpl;

    public TestDaoL(String titre, String auteur, String isbn) {
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
        this.lDaoImpl = LivreDaoImpl.getInstance();
    }

    @Test
    public void test() {
        int id = Integer.MAX_VALUE;
        try {
            id = lDaoImpl.create(titre, auteur, isbn);
            assertNotEquals((long) Integer.MAX_VALUE, (long) id);
        } catch (DaoException e) {
            System.out.println("Ajout livre Ã©chouÃ© : " + e.getLocalizedMessage());
        }
        try {
            List<Livre> liste = lDaoImpl.getList();
            assertNotNull(liste);
        } catch (DaoException e) {
            System.out.println("Liste livres Ã©chouÃ© : " + e.getLocalizedMessage());
        }
        try {
            Livre livre = lDaoImpl.getById(id);
            assertEquals(livre.getTitre(),titre);
        } catch (DaoException e) {
            System.out.println("Livre par id Ã©chouÃ© : " + e.getLocalizedMessage());
        }
        try {
            Livre livre = new Livre(id,"Vol de nuit","Antoine de Saint Exupery","9791155515563");
            lDaoImpl.update(livre);
        } catch (DaoException e) {
            System.out.println("Maj de livre Ã©chouÃ© : " + e.getLocalizedMessage());
        }
        try {
            lDaoImpl.delete(id);
        } catch (DaoException e) {
            System.out.println("Suppression de livre Ã©chouÃ© : " + e.getLocalizedMessage());
        }
        try {
            int count = lDaoImpl.count();
            assertNotEquals((long) Integer.MAX_VALUE, (long) count);
        } catch (DaoException e) {
            System.out.println("Comptage livres Ã©chouÃ© : " + e.getLocalizedMessage());
        }
    }

}