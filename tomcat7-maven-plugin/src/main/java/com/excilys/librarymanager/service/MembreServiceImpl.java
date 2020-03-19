package com.excilys.librarymanager.service;

import java.sql.SQLException;

import com.excilys.librarymanager.model.Membre;
import com.excilys.librarymanager.model.Abonnement;

import com.excilys.librarymanager.dao.MembreDaoImpl;
import com.excilys.librarymanager.dao.EmpruntDaoImpl;

import java.util.ArrayList;
import java.util.List;

import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.exception.DaoException;

public class MembreServiceImpl implements MembreService {
    // Singleton
    private static MembreServiceImpl instance;

	private MembreServiceImpl() {
	};

	public static MembreServiceImpl getInstance() {
		if (instance == null) {
			instance = new MembreServiceImpl();
		}
		return instance;
    }

    public List<Membre> getList() throws ServiceException {
        MembreDaoImpl dao = MembreDaoImpl.getInstance();
        List<Membre> liste = null;
        try {
            liste = dao.getList();
        }
        catch (DaoException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        }
        return liste;
    }

    public List<Membre> getListMembreEmpruntPossible() throws ServiceException {
        MembreDaoImpl dao_membre = MembreDaoImpl.getInstance();
        EmpruntDaoImpl dao_emprunt = EmpruntDaoImpl.getInstance();
        List<Membre> possible = new ArrayList<Membre>();
        try {
            List<Membre> membres = dao_membre.getList();
            // On teste chaque membre selon abonnement
            int no;
            for (int i = 0 ; i < membres.size() ; i++) {
                no = dao_emprunt.getListCurrentByMembre(membres.get(i).getKey()).size();
                switch (membres.get(i).getAbonnement()) {
                    case BASIC:
                        if (no < 2) {
                            possible.add(membres.get(i));
                        }
                        break;
                    case PREMIUM:
                        if (no < 5) {
                            possible.add(membres.get(i));
                        }
                        break;
                    case VIP:
                        if (no < 20) {
                            possible.add(membres.get(i));
                        }
                        break;
                    default: break;
                }
            }
        }
        catch (DaoException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        }
        return possible;
    }
}
