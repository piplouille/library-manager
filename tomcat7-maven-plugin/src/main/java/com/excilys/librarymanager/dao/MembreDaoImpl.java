package com.excilys.librarymanager.dao;

import com.excilys.librarymanager.model.Membre;

public class MembreDaoImpl implements MembreDao {
    /**
     * @brief Récupère la liste des membres de la BDD
     */
    public List<Membre> getList() throws DaoException {
        Connection connection = EstablishConnection.getConnection();
        PreparedStatement getPreparedStatement = null;
        
        String SelectQuery = "SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre ORDER BY nom, prenom";
        
        try {
            getPreparedStatement = connection.prepareStatement(SelectQuery);
            ResultSet rs = getPreparedStatement.executeQuery();
            getPreparedStatement.close();

            /*
            Il faut mettre les membres dans une liste : on crée une liste qu'on initialise par tout
            */
            // On crée la liste
            List<Membre> membres = new ArrayList<Membre>();

            while (rs.next()) {
                // On ajoute le membre à la liste
                membres.add(Membre(rs.getKey(), rs.getNom(), rs.getPrenom(), rs.getAdresse(), rs.getEmail(), rs.getTelephone(), rs.getAbonnement()));
            }

            // On renvoie la liste
            return membres;
        }
        catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
            throw new DaoException("ERREUR : MembreDaoImpl.getList()");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            connection.close();
        }
    }

    /**
     * @brief Récupère membre par son id
     */
    public Membre getById(int id) throws DaoException {
        Connection connection = EstablishConnection.getConnection();
        PreparedStatement getPreparedStatement = null;
        
        String SelectQuery = "SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre WHERE id = ?;";
        
        try {
            getPreparedStatement = connection.prepareStatement(SelectQuery);
            getPreparedStatement.setInt(1, id);
            ResultSet rs = getPreparedStatement.executeQuery();
            getPreparedStatement.close();

            return Membre((rs.getKey(), rs.getNom(), rs.getPrenom(), rs.getAdresse(), rs.getEmail(), rs.getTelephone(), rs.getAbonnement());
        }
        catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
            throw new DaoException("ERREUR : MembreDaoImpl.getById()");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            connection.close();
        }   
    }

    /**
     * @brief Insère un membre dans la BDD et renvoie son id
     */
    public int create(String nom, String prenom, String adresse, String email, String telephone) throws DaoException {
        Connection connection = EstablishConnection.getConnection();
        PreparedStatement getPreparedStatement = null;
        
        String SelectQuery = "INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            getPreparedStatement = connection.prepareStatement(SelectQuery);
            getPreparedStatement.setString(1, nom);
            getPreparedStatement.setString(2, prenom);
            getPreparedStatement.setString(3, adresse);
            getPreparedStatement.setString(4, email);
            getPreparedStatement.setString(5, telephone);
            getPreparedStatement.setString(6, Abonnement.name());
            ResultSet rs = getPreparedStatement.executeQuery();
            getPreparedStatement.close();

            return rs.getKey();
        }
        catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
            throw new DaoException("ERREUR : MembreDaoImpl.create()");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            connection.close();
        }
    }

    /**
     * @brief Met à jour un membre de la BDD
     */
    public void update(Membre membre) throws DaoException {

    }

}