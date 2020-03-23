package com.excilys.librarymanager.dao;

import com.excilys.librarymanager.model.*;
import com.excilys.librarymanager.exception.DaoException;

import java.sql.*;
import com.excilys.librarymanager.utils.FillDatabase;
import com.excilys.librarymanager.persistence.ConnectionManager;
import java.util.ArrayList;
import java.util.List;

public class LivreDaoImpl implements LivreDao {
    private static LivreDaoImpl instance;

    private LivreDaoImpl() {
    };

    public static LivreDaoImpl getInstance() {
        if (instance == null) {
            instance = new LivreDaoImpl();
        }
        return instance;
    }

    /**
     * @brief Lister tous les livres
     * @param none
     * @return List<Livre> liste des livres
     */
    public List<Livre> getList() throws DaoException {
        List<Livre> liste = new ArrayList<Livre>();
        Connection connection = null;
		PreparedStatement getPreparedStatement = null ;
		ResultSet rs =null;
        try {
            connection = ConnectionManager.getConnection();

            String SelectQuery = "SELECT id, titre, auteur, isbn FROM livre;";
            getPreparedStatement = connection.prepareStatement(SelectQuery);
            rs = getPreparedStatement.executeQuery();
            while (rs.next()) {
                liste.add(new Livre(rs.getInt("id"), rs.getString("titre"), rs.getString("auteur"),
                        rs.getString("isbn")));
            }

        } catch (SQLException e) {
            throw new DaoException("Erreur : liste des livres");
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                getPreparedStatement.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            try {
                connection.close();
            }
            catch (Exception e) {
                System.out.println("Exception Message " + e.getLocalizedMessage());
            }
        }
        return liste;
    };

    /**
     * @brief Recuperer un livre par son identifiant
     * @param int id
     * @return Livre le livre
     */
    public Livre getById(int id) throws DaoException {
        Livre livre = null;
        Connection connection = null;
		PreparedStatement getPreparedStatement = null ;
		ResultSet rs =null;
        try {
            connection = ConnectionManager.getConnection();

            String SelectQuery = "SELECT id, titre, auteur, isbn FROM livre WHERE id = ?;";
            getPreparedStatement = connection.prepareStatement(SelectQuery);
            getPreparedStatement.setInt(1, id);
            rs = getPreparedStatement.executeQuery();

            livre = new Livre(rs.getInt("id"), rs.getString("titre"), rs.getString("auteur"), rs.getString("isbn"));

        } catch (SQLException e) {
            throw new DaoException("Erreur : livre par id");
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                getPreparedStatement.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            try {
                connection.close();
            }
            catch (Exception e) {
                System.out.println("Exception Message " + e.getLocalizedMessage());
            }
        }
        return livre;
    }

    /**
     * @brief Ajouter un livre ds la BDD
     * @param String titre, String auteur, String isbn
     * @return int id du livre
     */
    public int create(String titre, String auteur, String isbn) throws DaoException {
        int id = -1;
        Connection connection = null;
		PreparedStatement getPreparedStatement = null ;
		ResultSet rs =null;
        try {
            connection = ConnectionManager.getConnection();

            String InsertQuery = "INSERT INTO livre(titre, auteur, isbn) VALUES (?, ?, ?);";
            getPreparedStatement = connection.prepareStatement(InsertQuery,
                    Statement.RETURN_GENERATED_KEYS);
            getPreparedStatement.setString(1, titre);
            getPreparedStatement.setString(2, auteur);
            getPreparedStatement.setString(3, isbn);
            getPreparedStatement.executeUpdate();
            rs = getPreparedStatement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException("Erreur : Ajout du livre " + titre + " par " + auteur + " isbn : " + isbn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                getPreparedStatement.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            try {
                connection.close();
            }
            catch (Exception e) {
                System.out.println("Exception Message " + e.getLocalizedMessage());
            }
        }
        return id;
    }

    /**
     * @brief Mettre a jour un livre ds la BDD
     * @param Livre livre
     * @return void
     */
    public void update(Livre livre) throws DaoException {
        Connection connection = null;
		PreparedStatement getPreparedStatement = null ;
        try {
            connection = ConnectionManager.getConnection();

            String UpdateQuery = "UPDATE livre SET titre = ?, auteur = ?, isbn = ? WHERE id = ?;";
            getPreparedStatement = connection.prepareStatement(UpdateQuery);
            getPreparedStatement.setString(1, livre.getTitre());
            getPreparedStatement.setString(2, livre.getAuteur());
            getPreparedStatement.setString(3, livre.getIsbn());
            getPreparedStatement.setInt(4, livre.getId());
            getPreparedStatement.executeQuery();

        } catch (SQLException e) {
            throw new DaoException("Erreur : Maj du livre " + livre.getTitre() + " par " + livre.getAuteur()
                    + " isbn : " + livre.getIsbn());
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {

            try {
                getPreparedStatement.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            try {
                connection.close();
            }
            catch (Exception e) {
                System.out.println("Exception Message " + e.getLocalizedMessage());
            }
        }
    }

    /**
     * @brief Suuprimer un livre de la BDD
     * @param int it
     * @return void
     */
    public void delete(int id) throws DaoException {
        Connection connection = null;
		PreparedStatement getPreparedStatement = null ;
        try {
            connection = ConnectionManager.getConnection();

            String UpdateQuery = "DELETE FROM livre WHERE id = ?;";
            getPreparedStatement = connection.prepareStatement(UpdateQuery);
            getPreparedStatement.setInt(1, id);
            getPreparedStatement.executeQuery();

        } catch (SQLException e) {
            throw new DaoException("Erreur : Suppression du livre d'id " + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {

            try {
                getPreparedStatement.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            try {
                connection.close();
            }
            catch (Exception e) {
                System.out.println("Exception Message " + e.getLocalizedMessage());
            }
        }
    }

    /**
     * @brief Compter tous les livres de la BDD
     * @param none
     * @return int compte
     */
    public int count() throws DaoException {
        int compte =-1;
        Connection connection = null;
		PreparedStatement getPreparedStatement = null ;
		ResultSet rs =null;
        try {
            connection = ConnectionManager.getConnection();

            String UpdateQuery = "SELECT COUNT(id) AS count FROM livre;";
            getPreparedStatement = connection.prepareStatement(UpdateQuery);
            rs = getPreparedStatement.executeQuery();
            rs.next();
            compte = rs.getInt(1);

        } catch (SQLException e) {
            throw new DaoException("Erreur : Comptage des livres");
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                getPreparedStatement.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            try {
                connection.close();
            }
            catch (Exception e) {
                System.out.println("Exception Message " + e.getLocalizedMessage());
            }
        }
        return compte;
    }
}