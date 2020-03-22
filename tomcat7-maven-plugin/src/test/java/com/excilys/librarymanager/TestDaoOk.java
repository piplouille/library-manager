package com.excilys.librarymanager.test;

import static org.junit.Assert.*;

import java.util.Arrays;
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
public class TestDaoOk {
	
	@Parameters public static Collection<Object[]> val(){            
		return Arrays.asList(new Object [][] {{"Debesse","Laetitia","1024 Bvd des Marechaux","debesse@ensta.fr","5462"}, {"Becker", "Madeleine", "1024 Bvd des Marechaux", "madeleine@ensta.fr", "0600"}});
        }
        
        String nom;
        String prenom;
        String adresse;
        String email;
        String telephone;

        MembreDaoImpl mDaoImpl;

	public TestDaoOk (String nom, String prenom, String adresse, String email, String telephone)
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
                try {
                        int id = mDaoImpl.create(nom, prenom, adresse, email, telephone);
                        assertNotEquals((long) Integer.MAX_VALUE, (long) id);
                }
                catch (DaoException e) {
                        System.out.println("Ajout membre échoué : " + e.getLocalizedMessage());
                }
	}

}