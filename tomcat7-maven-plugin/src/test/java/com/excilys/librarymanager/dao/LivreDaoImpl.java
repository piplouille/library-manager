package com.excilys.librarymanager.dao;

import com.excilys.librarymanager.model.*;
import com.excilys.librarymanager.exception.DaoException;

import java.sql.*;
import com.excilys.librarymanager.utils.*;
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
        try {
            Connection connection = EstablishConnection.getConnection();

            String SelectQuery = "SELECT id, titre, auteur, isbn FROM livre;";
            PreparedStatement getPreparedStatement = connection.prepareStatement(SelectQuery);
            ResultSet rs = getPreparedStatement.executeQuery();
            getPreparedStatement.close();
            while (rs.next()) {
                liste.add(new Livre(rs.getInt("id"), rs.getString("titre"), rs.getString("auteur"),
                        rs.getString("isbn")));
            }
            connection.close();

        } catch (SQLException e) {
            throw new DaoException("Erreur : liste des livres");
        } catch (Exception e) {
            e.printStackTrace();
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
        try {
            Connection connection = EstablishConnection.getConnection();

            String SelectQuery = "SELECT id, titre, auteur, isbn FROM livre WHERE id = ?;";
            PreparedStatement getPreparedStatement = connection.prepareStatement(SelectQuery);
            getPreparedStatement.setInt(1, id);
            ResultSet rs = getPreparedStatement.executeQuery();
            getPreparedStatement.close();

            livre = new Livre(rs.getInt("id"), rs.getString("titre"), rs.getString("auteur"), rs.getString("isbn"));

            connection.close();

        } catch (SQLException e) {
            throw new DaoException("Erreur : livre par id");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return livre;
    }

    /**
     * @brief Ajouter un livre ds la BDD
     * @param String titre, String auteur, String isbn
     * @return int id du livre
     */
    public int create(String titre, String auteur, String isbn) throws DaoException {
        int id = Integer.MAX_VALUE;
        try {
            Connection connection = EstablishConnection.getConnection();

            String InsertQuery = "INSERT INTO livre(titre, auteur, isbn) VALUES (?, ?, ?);";
            PreparedStatement getPreparedStatement = connection.prepareStatement(InsertQuery,
                    Statement.RETURN_GENERATED_KEYS);
            getPreparedStatement.setString(1, titre);
            getPreparedStatement.setString(2, auteur);
            getPreparedStatement.setString(3, isbn);
            getPreparedStatement.executeUpdate();
            ResultSet resultSet = getPreparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            getPreparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            throw new DaoException("Erreur : Ajout du livre " + titre + " par " + auteur + " isbn : " + isbn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * @brief Mettre a jour un livre ds la BDD
     * @param Livre livre
     * @return void
     */
    public void update(Livre livre) throws DaoException {
        try {
            Connection connection = EstablishConnection.getConnection();

            String UpdateQuery = "UPDATE livre SET titre = ?, auteur = ?, isbn = ? WHERE id = ?;";
            PreparedStatement getPreparedStatement = connection.prepareStatement(UpdateQuery);
            getPreparedStatement.setString(1, livre.getTitre());
            getPreparedStatement.setString(2, livre.getAuteur());
            getPreparedStatement.setString(3, livre.getIsbn());
            getPreparedStatement.setInt(4, livre.getId());
            getPreparedStatement.executeQuery();
            getPreparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            throw new DaoException("Erreur : Maj du livre " + livre.getTitre() + " par " + livre.getAuteur()
                    + " isbn : " + livre.getIsbn());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Suuprimer un livre de la BDD
     * @param int it
     * @return void
     */
    public void delete(int id) throws DaoException {
        try {
            Connection connection = EstablishConnection.getConnection();

            String UpdateQuery = "DELETE FROM livre WHERE id = ?;";
            PreparedStatement getPreparedStatement = connection.prepareStatement(UpdateQuery);
            getPreparedStatement.setInt(1, id);
            getPreparedStatement.executeQuery();
            getPreparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            throw new DaoException("Erreur : Suppression du livre d'id " + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Compter tous les livres de la BDD
     * @param none
     * @return int compte
     */
    public int count() throws DaoException {
        int compte = Integer.MAX_VALUE;
        try {
            Connection connection = EstablishConnection.getConnection();

            String UpdateQuery = "SELECT COUNT(id) AS count FROM livre;";
            PreparedStatement getPreparedStatement = connection.prepareStatement(UpdateQuery);
            ResultSet rs = getPreparedStatement.executeQuery();
            getPreparedStatement.close();
            connection.close();
            compte = rs.getInt(1);

        } catch (SQLException e) {
            throw new DaoException("Erreur : Comptage des livres");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return compte;
    }
}