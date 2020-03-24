package com.excilys.librarymanager.dao;

import com.excilys.librarymanager.dao.MembreDaoImpl;
import com.excilys.librarymanager.model.Membre;
import com.excilys.librarymanager.model.Emprunt;
import com.excilys.librarymanager.model.Livre;
import com.excilys.librarymanager.model.Abonnement;
import com.excilys.librarymanager.exception.DaoException;

import junit.framework.TestCase;

import java.util.List;

public class TestDao extends TestCase {
    public void testMembre() {
        Membre madeleine = new Membre(1,"Debesse","Laetitia","1024 Bvd des Marechaux","debesse@ensta.fr","5462",Abonnement.PREMIUM);
        MembreDaoImpl mDaoImpl = MembreDaoImpl.getInstance();
        int i=-1;
        try {
            i = mDaoImpl.create(madeleine.getNom(), madeleine.getPrenom(), madeleine.getAdresse(), madeleine.getEmail(),
                    madeleine.getTelephone());
        }
        catch (DaoException e) {
            System.out.println("Test Membre : creation");
        }
        try {
            madeleine.setTelephone("0125453");
            mDaoImpl.update(madeleine);
        }
        catch (DaoException e) {
            System.out.println("Test Membre : update");
        }
        try {
            List<Membre> liste = mDaoImpl.getList();
        }
        catch (DaoException e) {
            System.out.println("Test Membre : getList");
        }
        try {
            Membre membre = mDaoImpl.getById(i);
        
        }
        catch (DaoException e) {
            System.out.println("Test Membre : getById");
        }
        try {
            int count = mDaoImpl.count();
        }
        catch (DaoException e) {
            System.out.println("Test Membre : count");
        }
        try {
            mDaoImpl.delete(i);
        }
        catch (DaoException e) {
            System.out.println("Test Membre : delete");
        }
    }

    public void testLivre() {
        Livre livre = new Livre(0, "Le Petit Prince", "Antoine de St Exupery", "9791187192596");
        LivreDaoImpl livre_dao = LivreDaoImpl.getInstance();









    }
    public void testEmprunt() {
        
    }
}