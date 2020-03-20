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
            throw new ServiceException("Erreur Service Livre : liste des livres");
        }
    }

    /**
	 * @brief Service pour avoir la liste des livres disponibles
	 */
    public List<Livre> getListDispo() throws ServiceException
    {
        List<Livre> liste_dispo = new ArrayList<Livre>();
        try{
            EmpruntServiceImpl emprunt = EmpruntServiceImpl.getInstance();
            List<Livre> liste_complete = getList();
            for(Livre livre : liste_complete)
            {
                if(emprunt.isLivreDispo(livre.getId()))
                {
                    liste_dispo.add(livre);
                }
            }
            
        }
        catch(ServiceException error)
        {
            throw new ServiceException("Erreur Service Livre : liste des livres disponibles");
        }
        return liste_dispo;
    }

    /**
	 * @brief Service pour obtenir un livre par id
	 */
    public Livre getById(int id) throws ServiceException
    {
        if(id<0)
        {
            throw new ServiceException("Erreur Service Livre : livre par id ID INVALIDE");
        }
        try{
            LivreDaoImpl dao = LivreDaoImpl.getInstance();
            return dao.getById(id);
        }
        catch(DaoException error)
        {
            throw new ServiceException("Erreur Service Livre : livre par id");
        }
    }

    /**
	 * @brief Service pour creer un livre
	 */
    public int create(String titre, String auteur, String isbn) throws ServiceException
    {
        if(titre == null)
        {
            throw new ServiceException("Erreur Service Livre : creation de livre TITRE NUL");
        }
        if(auteur == null)
        {
            throw new ServiceException("Erreur Service Livre : creation de livre AUTEUR NUL");
        }
        if(isbn == null)
        {
            throw new ServiceException("Erreur Service Livre : creation de livre ISBN NUL");
        }
        try{
            LivreDaoImpl dao = LivreDaoImpl.getInstance();
            return dao.create(titre,auteur,isbn);
        }
        catch(DaoException error)
        {
            throw new ServiceException("Erreur Service Livre : creation du livre "+titre+" par "+auteur+" isbn : "+isbn);
        }
    }

    /**
	 * @brief Service pour mettre un livre a jour
	 */
    public void update(Livre livre) throws ServiceException
    {
        if(livre == null)
        {
            throw new ServiceException("Erreur Service Livre : mise a jour de livre LIVRE NUL");
        }
        try{
            LivreDaoImpl dao = LivreDaoImpl.getInstance();
            dao.update(livre);
        }
        catch(DaoException error)
        {
            throw new ServiceException("Erreur Service Livre : mise a jour du livre "+livre.getTitre()+" par "+livre.getAuteur()+" isbn : "+livre.getIsbn()+" id : "+livre.getId());
        }
    }

    /**
	 * @brief Service pour supprimer un livre
	 */
    public void delete(int id) throws ServiceException
    {
        if(id<0)
        {
            throw new ServiceException("Erreur Service Livre : suppression de livre ID INVALIDE");
        }
        try{
            LivreDaoImpl dao = LivreDaoImpl.getInstance();
            dao.delete(id);
        }
        catch(DaoException error)
        {
            throw new ServiceException("Erreur Service Livre : suppression du livre "+id);
        }
    }

    /**
	 * @brief Service pour compter les livres
	 */
    public int count() throws ServiceException
    {
        try{
            LivreDaoImpl dao = LivreDaoImpl.getInstance();
            return dao.count();
        }
        catch(DaoException error)
        {
            throw new ServiceException("Erreur Service Livre : comptage des livres");
        }
    }
}