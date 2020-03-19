package com.excylis.librarymanager.service;

import java.sql.SQLException;

import com.excylis.librarymanager.dao.MembreDaoImpl;

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
        try {
            return dao.getList();
        }
        catch (DaoException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        }
    }

    public List<Membre> getListMembreEmpruntPossible() throws ServiceException {
        MembreDaoImpl dao = MembreDaoImpl.getInstance();
        try {
            List<Membre> membres = dao.getList();
            // On teste chaque membre selon abonnement
            for (int i = 0 ; i < membres.size() ; i++)
                switch (membres[i].getAbonnement()) {
                    case BASIC:
                        if (membres[i].get)
                        dateRetour = dateEmprunt.plus(15, ChronoUnit.DAYS);
                        break;
                    case PREMIUM:
                        dateRetour = dateEmprunt.plus(1, ChronoUnit.MONTHS);
                        break;
                    case VIP:
                        dateRetour = dateEmprunt.plus(3, ChronoUnit.MONTHS);
                        break;
                    default:
                        dateRetour = null;
                }
            }
            return dao.getList();
        }
        catch (DaoException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        }
    }
}
