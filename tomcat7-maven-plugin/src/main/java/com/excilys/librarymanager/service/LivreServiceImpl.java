package com.excilys.librarymanager.service;

import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.exception.DaoException;

import com.excilys.librarymanager.model.Emprunt;
import com.excilys.librarymanager.model.Livre;
import com.excilys.librarymanager.model.Membre;
import com.excilys.librarymanager.model.Abonnement;

import com.excilys.librarymanager.dao.LivreDaoImpl;
import com.excilys.librarymanager.dao.EmpruntDaoImpl;

import java.util.ArrayList;
import java.util.List;

public class LivreServiceImpl implements LivreService
{
    private static LivreServiceImpl instance;

	private LivreServiceImpl() {}

	public static LivreServiceImpl getInstance() {
		if (instance == null) {
			instance = new LivreServiceImpl();
		}
		return instance;
    }

    /**
	 * @brief Service pour avoir la liste des livres
	 */
    public List<Livre> getList() throws ServiceException
    {
        try{
            LivreDaoImpl dao = LivreDaoImpl.getInstance();
            return dao.getList();
        }
        catch(DaoException error)
        {
            throw new ServiceException("Erreur : Service livre getList");
        }
    }

    /**
	 * @brief Service pour avoir la liste des livres disponibles
	 */
    public List<Livre> getListDispo() throws ServiceException
    {
        try{
            EmpruntServiceImpl emprunt = EmpruntServiceImpl.getInstance();
            List<Livre> liste_complete = getList();
            List<Livre> liste_dispo = new ArrayList<Livre>();
            for(Livre livre : liste_complete)
            {
                if(emprunt.isLivreDispo(livre.getId()))
                {
                    liste_dispo.add(livre);
                }
            }
            return liste_dispo;
        }
        catch(ServiceException error)
        {
            throw new ServiceException("Erreur : Service livre getList");
        }
    }
}