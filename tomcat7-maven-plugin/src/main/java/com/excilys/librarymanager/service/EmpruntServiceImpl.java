package com.excilys.librarymanager.service;

import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.exception.DaoException;

import com.excilys.librarymanager.model.Emprunt;
import com.excilys.librarymanager.model.Livre;
import com.excilys.librarymanager.model.Membre;
import com.excilys.librarymanager.model.Abonnement;

import com.excilys.librarymanager.dao.EmpruntDaoImpl;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;

public class EmpruntServiceImpl implements EmpruntService
{
    private static EmpruntServiceImpl instance;

	private EmpruntServiceImpl() {}

	public static EmpruntServiceImpl getInstance() {
		if (instance == null) {
			instance = new EmpruntServiceImpl();
		}
		return instance;
    }
    
    /**
	 * @brief Service pour avoir la liste des emprunts
	 */
    public List<Emprunt> getList() throws ServiceException
    {
        List<Emprunt> liste=null;
        try{
            EmpruntDaoImpl dao = EmpruntDaoImpl.getInstance();
            liste = dao.getList();
        }
        catch(DaoException error)
        {
            throw new ServiceException("Erreur : Service emprunt getList");
        }
        return liste;
    }

    /**
	 * @brief Service pour avoir la liste des emprunts en cours
	 */
    public List<Emprunt> getListCurrent() throws ServiceException
    {
        List<Emprunt> liste=null;
        try{
            EmpruntDaoImpl dao = EmpruntDaoImpl.getInstance();
            liste =  dao.getListCurrent();
        } catch (DaoException error)
        {
            throw new ServiceException("Erreur : Service Emprunt liste emprunts en cours");
        }
        return liste;
    }

    /**
	 * @brief Service pour avoir la liste des emprunts en cours d'un membre
	 */
    public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException
    {
        List<Emprunt> liste=null;
        try{
            EmpruntDaoImpl dao = EmpruntDaoImpl.getInstance();
            liste = dao.getListCurrentByMembre(idMembre);
        } catch (DaoException error)
        {
            throw new ServiceException("Erreur : Service Emprunt liste emprunts en cours d'un membre "+idMembre);
        }
        if (liste==null)
        {
            throw new ServiceException("Erreur : Service emprunt getListCurrentByMembre LISTE VIDE");
        }
        return liste;
    }

    /**
	 * @brief Service pour avoir la liste des emprunts en cours d'un livre
	 */
    public List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException
    {
        List<Emprunt> liste=null;
        try{
            EmpruntDaoImpl dao = EmpruntDaoImpl.getInstance();
            liste = dao.getListCurrentByLivre(idLivre);
        } catch (DaoException error)
        {
            throw new ServiceException("Erreur : Service Emprunt liste emprunts en cours d'un livre "+idLivre);
        }
        return liste;
    }

    /**
	 * @brief Service pour avoir un emprunt par id du livre
	 */
    public Emprunt getById(int id) throws ServiceException
    {
        if(id<0)
        {
            throw new ServiceException("Erreur : Service recuperer un emprunt par id : ID INVALIDE");
        }
        EmpruntDaoImpl dao = EmpruntDaoImpl.getInstance();
        Emprunt emprunt = null;
        try{
            emprunt = dao.getById(id); //idLivre
        } catch (DaoException error)
        {
            throw new ServiceException("Erreur : Service Emprunt recuperer emprunt par id "+id);
        }
        if (emprunt == null)
            {
                throw new ServiceException("Erreur : Service recuperer un emprunt par id : EMRPUNT NON TROUVE");
            }
            return emprunt;
    }

    /**
	 * @brief Service pour creer un emprunt
	 */
    public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException {
        if(idMembre<0)
        {
            throw new ServiceException("Erreur : Service creer un emprunt : ID MEMBRE INVALIDE");
        }
        if(idLivre<0)
        {
            throw new ServiceException("Erreur : Service creer un emprunt : ID LIVRE INVALIDE");
        }
        if(dateEmprunt==null)
        {
            throw new ServiceException("Erreur : Service creer un emprunt : DATE EMPRUNT NULLE");
        }
        EmpruntDaoImpl dao = EmpruntDaoImpl.getInstance();
        try{
            dao.create(idMembre,idLivre,dateEmprunt);
        } catch (DaoException error)
        {
            throw new ServiceException("Erreur : Service Emprunt creation de l'emprunt du livre "+idLivre+ "par le membre "+idMembre+" le "+dateEmprunt);
        }
    }

    /**
	 * @brief Service pour rendre un livre
	 */
    public void returnBook(int idEmprunt) throws ServiceException
    {
        if(idEmprunt<0)
        {
            throw new ServiceException("Erreur : Service rendre un livre : ID EMPRUNT INVALIDE");
        }
        try{
            EmpruntDaoImpl dao = EmpruntDaoImpl.getInstance();
            Emprunt emprunt = dao.getById(idEmprunt);
            if(emprunt==null)
            {
                throw new ServiceException("Erreur : Service rendre un livre : EMPRUNT NON TROUVE");
            }
            emprunt.setDateRetour(LocalDate.now());
            dao.update(emprunt);
        } catch (DaoException error)
        {
            throw new ServiceException("Erreur : Service Emprunt rendre un livre, emprunt "+idEmprunt);
        }
    }

    /**
	 * @brief Service pour compter tous les emprunts
	 */
    public int count() throws ServiceException
    {
        EmpruntDaoImpl dao = EmpruntDaoImpl.getInstance();
        int i = -1;
        try{
            i = dao.count();
        } catch (DaoException error)
        {
            throw new ServiceException("Erreur : Service Emprunt compter les emprunts");
        }
        if(i==-1)
        {
            throw new ServiceException("Erreur : Service Emprunt compter les emprunts COMPTE INVALIDE");
        }
        return i;
    }


    /**
	 * @brief Service pour savoir si le livre est disponible
	 */
    // on suppose qu'il n'y a qu'un seul exemplaire de chaque livre
    public boolean isLivreDispo(int idLivre) throws ServiceException
    {
        boolean misAjour=false;//Verifie que la fonction a bien fait son job
        if(idLivre<0)
        {
            throw new ServiceException("Erreur : Service si livre dispo : ID LIVRE INVALIDE");
        }
        int result = -1;
        try{
            EmpruntDaoImpl dao = EmpruntDaoImpl.getInstance();
            // renvoie listes des emprunts en cours
            List<Emprunt> emprunts = dao.getListCurrentByLivre(idLivre);
            result = emprunts.size();
            misAjour = true;
        } catch (DaoException error)
        {
            throw new ServiceException("Erreur : Service Emprunt si livre dispo");
        }
        if(!misAjour)
        {
            throw new ServiceException("Erreur : Service Emprunt si livre dispo RESULTAT INVALIDE");
        }
        return (result == 0);
    }

    /**
	 * @brief Service pour savoir si l'emprunt est possible
	 */
    public boolean isEmpruntPossible(Membre membre) throws ServiceException
    {
        boolean result = false;
        boolean misAjour=false;//Verifie que la fonction a bien fait son job
        if(membre==null)
        {
            throw new ServiceException("Erreur : Service si emprunt possible : MEMBRE NUL");
        }
        try{
            int nbLivres ;
            switch(membre.getAbonnement())
            {
                case BASIC:
                    nbLivres = 2;
                    break;
                case PREMIUM:
                    nbLivres = 5;
                    break;
                case VIP:
                    nbLivres = 20;
                    break;
                default:
                    throw new ServiceException("Erreur : Service si emprunt possible : ABONNEMENT INVALIDE");
            }
            EmpruntDaoImpl dao = EmpruntDaoImpl.getInstance();
            int nbEmprunts = dao.getListCurrentByMembre(membre.getKey()).size();
            result = (nbEmprunts<nbLivres);
            misAjour=true;
        } catch (DaoException error)
        {
            throw new ServiceException("Erreur : Service Emprunt si emprunt possible");
        }
        if(!misAjour)
        {
            throw new ServiceException("Erreur : Service Emprunt si emprunt possible RESULTAT INVALIDE");
        }
        return result;
    }
}