package com.excilys.librarymanager.dao;

import com.excilys.librarymanager.model.Membre;
import com.excilys.librarymanager.model.Abonnement;

import com.excilys.librarymanager.exception.DaoException;

import com.excilys.librarymanager.utils.EstablishConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class MembreDaoImpl implements MembreDao {
    private static MembreDaoImpl instance;

    private MembreDaoImpl() {
    };

    public static MembreDaoImpl getInstance() {
        if (instance == null) {
            instance = new MembreDaoImpl();
        }
        return instance;
    }

    /**
     * @brief Récupère la liste des membres de la BDD
     */
    public List<Membre> getList() throws DaoException {
        List<Membre> membres = null;
        try {
            Connection connection = EstablishConnection.getConnection();

            String SelectQuery = "SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre ORDER BY nom, prenom;";

            PreparedStatement getPreparedStatement = connection.prepareStatement(SelectQuery);
            ResultSet rs = getPreparedStatement.executeQuery();
            getPreparedStatement.close();

            /*
             * Il faut mettre les membres dans une liste : on crée une liste qu'on
             * initialise par tout
             */
            // On crée la liste
            membres = new ArrayList<Membre>();

            while (rs.next()) {
                // On ajoute le membre à la liste
                membres.add(new Membre(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
                        rs.getString("adresse"), rs.getString("email"), rs.getString("telephone"),
                        Abonnement.valueOf(rs.getString("abonnement"))));
            }
            connection.close();
            // On renvoie la liste

        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
            throw new DaoException("ERREUR : MembreDaoImpl.getList()");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return membres;
    }

    /**
     * @brief Récupère membre par son id
     */
    public Membre getById(int id) throws DaoException {
        Membre membre = null;
        try {
            Connection connection = EstablishConnection.getConnection();

            String SelectQuery = "SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre WHERE id = ?;";

            PreparedStatement getPreparedStatement = connection.prepareStatement(SelectQuery);
            getPreparedStatement.setInt(1, id);
            ResultSet rs = getPreparedStatement.executeQuery();
            getPreparedStatement.close();
            connection.close();
            membre = new Membre(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"),
                    rs.getString("email"), rs.getString("telephone"), Abonnement.valueOf(rs.getString("abonnement")));
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
            throw new DaoException("ERREUR : MembreDaoImpl.getById()");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return membre;
    }

    /**
     * @brief Insère un membre dans la BDD et renvoie son id
     */
    public int create(String nom, String prenom, String adresse, String email, String telephone) throws DaoException {
        int id = Integer.MAX_VALUE;
        try {
            Connection connection = EstablishConnection.getConnection();

            String SelectQuery = "INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement) VALUES (?, ?, ?, ?, ?, ?);";

            PreparedStatement getPreparedStatement = connection.prepareStatement(SelectQuery);
            getPreparedStatement.setString(1, nom);
            getPreparedStatement.setString(2, prenom);
            getPreparedStatement.setString(3, adresse);
            getPreparedStatement.setString(4, email);
            getPreparedStatement.setString(5, telephone);
            getPreparedStatement.setString(6, Abonnement.BASIC.name());
            ResultSet rs = getPreparedStatement.executeQuery();
            getPreparedStatement.close();
            connection.close();
            id = rs.getInt("id");
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
            throw new DaoException("ERREUR : MembreDaoImpl.create()");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * @brief Met à jour un membre de la BDD
     */
    public void update(Membre membre) throws DaoException {
        try {
            Connection connection = EstablishConnection.getConnection();

            String SelectQuery = "UPDATE membre SET nom = ?, prenom = ?, adresse = ?, email = ?, telephone = ?, abonnement = ? WHERE id = ?;";

            PreparedStatement getPreparedStatement = connection.prepareStatement(SelectQuery);
            getPreparedStatement.setString(1, membre.getNom());
            getPreparedStatement.setString(2, membre.getPrenom());
            getPreparedStatement.setString(3, membre.getAdresse());
            getPreparedStatement.setString(4, membre.getEmail());
            getPreparedStatement.setString(5, membre.getTelephone());
            getPreparedStatement.setString(6, membre.getAbonnement().name());
            getPreparedStatement.setInt(7, membre.getKey());
            getPreparedStatement.executeQuery();
            getPreparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
            throw new DaoException("ERREUR : MembreDaoImpl.getById()");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Supprime un membre de la BDD à partir de son id
     */
    public void delete(int id) throws DaoException {
        try {
            Connection connection = EstablishConnection.getConnection();

            String SelectQuery = "DELETE FROM membre WHERE id = ?;";

            PreparedStatement getPreparedStatement = connection.prepareStatement(SelectQuery);
            getPreparedStatement.setInt(1, id);
            getPreparedStatement.executeQuery();
            getPreparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
            throw new DaoException("ERREUR : MembreDaoImpl.getById()");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Retourne le nombre de membres de la BDD
     */
    public int count() throws DaoException {
        int count = Integer.MAX_VALUE;
        try {
            Connection connection = EstablishConnection.getConnection();

            String SelectQuery = "SELECT COUNT(id) AS count FROM membre;";

            PreparedStatement getPreparedStatement = connection.prepareStatement(SelectQuery);
            ResultSet rs = getPreparedStatement.executeQuery();
            getPreparedStatement.close();
            connection.close();
            count = rs.getInt("");
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
            throw new DaoException("ERREUR : MembreDaoImpl.getById()");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}