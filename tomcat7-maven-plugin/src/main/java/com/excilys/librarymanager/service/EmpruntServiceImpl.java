package com.excilys.librarymanager.service;

import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.exception.DaoException;

public class EmpruntServiceDaoImpl implements EmpruntService
{
    private static EmpruntServiceImpl instance;

	private EmpruntServiceImpl() {
	};

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
        try{
            EmpruntDaoImpl dao = EmpruntDaoImpl.getInstance();
            return dao.getList();
        }
        catch(DaoException error)
        {
            throw new ServiceException("Erreur : Service emprunt getList")
        }
    }

    /**
	 * @brief Service pour avoir la liste des emprunts en cours
	 */
    public List<Emprunt> getListCurrent() throws ServiceException
    {
        try{EmpruntDaoImpl dao = EmpruntDaoImpl.getInstance();
        return dao.getListCurrent();
        } catch (DaoException error)
        {
            throw new ServiceException("Erreur : Service Emprunt liste emprunts en cours");
        }

    }

    /**
	 * @brief Service pour avoir la liste des emprunts en cours d'un membre
	 */
    public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException
    {
        try{EmpruntDaoImpl dao = EmpruntDaoImpl.getInstance();
            return dao.getListCurrentByMembre(idMembre);
            } catch (DaoException error)
            {
                throw new ServiceException("Erreur : Service Emprunt liste emprunts en cours");
            }
    }

}