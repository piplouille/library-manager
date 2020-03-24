package com.excilys.librarymanager.service;

import com.excilys.librarymanager.model.Membre;
import com.excilys.librarymanager.model.Abonnement;

import com.excilys.librarymanager.service.MembreServiceImpl;

import com.excilys.librarymanager.exception.ServiceException;


import junit.framework.TestCase;

public class TestService extends TestCase {
    public void testMembre() {
        Membre madeleine = new Membre(1,"Debesse","Laetitia","1024 Bvd des Marechaux","debesse@ensta.fr","5462",Abonnement.PREMIUM);        
        MembreServiceImpl membre_service = MembreServiceImpl.getInstance();
        try {
            int id = membre_service.create(madeleine.getNom(), madeleine.getPrenom(), madeleine.getAdresse(), madeleine.getEmail(), madeleine.getTelephone());
            // public Membre getById(int id) throws ServiceException;
            // public void update();
            // public void delete(int id) throws ServiceException;
            // public int count() throws ServiceException;
        }
        catch (ServiceException e) {
            System.out.println("Erreur Membre Service");
        }
    }
}