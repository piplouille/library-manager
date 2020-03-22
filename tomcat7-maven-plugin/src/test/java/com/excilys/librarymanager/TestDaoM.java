package com.excilys.librarymanager;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


import com.excilys.librarymanager.dao.MembreDaoImpl;
import com.excilys.librarymanager.model.Membre;
import com.excilys.librarymanager.model.Abonnement;
import com.excilys.librarymanager.exception.DaoException;

@RunWith(Parameterized.class)
public class TestDaoM {
	
	@Parameters public static Collection<Object[]> val(){            
		return Arrays.asList(new Object [][] {{"Debesse","Laetitia","1024 Bvd des Marechaux","debesse@ensta.fr","5462"}, {"Becker", "Madeleine", "1024 Bvd des Marechaux", "madeleine@ensta.fr", "0600"}});
        }
        
        String nom;
        String prenom;
        String adresse;
        String email;
        String telephone;

        MembreDaoImpl mDaoImpl;

	public TestDaoM (String nom, String prenom, String adresse, String email, String telephone)
	{
                this.nom = nom;
                this.prenom = prenom;
                this.adresse = adresse;
                this.email = email;
                this.telephone = telephone;
                this.mDaoImpl = MembreDaoImpl.getInstance();
	}
	
	@Test
	public void test() {
                int id=Integer.MAX_VALUE;
                try {
                        id = mDaoImpl.create(nom, prenom, adresse, email, telephone);
                        assertNotEquals((long) Integer.MAX_VALUE, (long) id);
                }
                catch (DaoException e) {
                        System.out.println("Ajout membre échoué : " + e.getLocalizedMessage());
                }
                try {
                        List<Membre> liste = mDaoImpl.getList();
                        assertNotNull(liste);
                }
                catch (DaoException e) {
                        System.out.println("Liste des membres echouee : " + e.getLocalizedMessage());
                }
                try {
                        Membre membre = mDaoImpl.getById(id);
                        assertEquals(membre.getNom(),nom);
                }
                catch (DaoException e) {
                        System.out.println("Recuperation de membre par id echouee : " + e.getLocalizedMessage());
                }
                try {
                        Membre membre = new Membre(id,nom,prenom,adresse,email,telephone,Abonnement.BASIC);
                        mDaoImpl.update(membre);
                        assertEquals(membre.getAbonnement(),Abonnement.BASIC);
                }
                catch (DaoException e) {
                        System.out.println("Maj de membre echouee : " + e.getLocalizedMessage());
                }
                try {
                        mDaoImpl.delete(id);
                        
                }
                catch (DaoException e) {
                        System.out.println("Suppression de membre echouee : " + e.getLocalizedMessage());
                }
                try {
                        int count = mDaoImpl.count();
                        assertNotEquals((long) Integer.MAX_VALUE, (long) count);
                }
                catch (DaoException e) {
                        System.out.println("Comptage membres échoué : " + e.getLocalizedMessage());
                }
	}

}