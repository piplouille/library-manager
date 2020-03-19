package com.excilys.librarymanager.dao;

import com.excilys.librarymanager.model.*;
import com.excilys.librarymanager.exception.DaoException;

import java.sql.*;
import com.excilys.librarymanager.utils.*;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class EmpruntDaoImpl implements EmpruntDao {
	private static EmpruntDaoImpl instance;

	private EmpruntDaoImpl() {
	};

	public static EmpruntDaoImpl getInstance() {
		if (instance == null) {
			instance = new EmpruntDaoImpl();
		}
		return instance;
	}

	/**
	 * @brief Recupere la liste des emprunts de la BDD
	 * @param none
	 * @return List<Emprunt> liste des emrpunts
	 */
	public List<Emprunt> getList() throws DaoException {
		Connection connection = EstablishConnection.getConnection();
		PreparedStatement getPreparedStatement = null;

		String SelectQuery = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email,telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre ORDER BY dateRetour DESC;";

		try {
			getPreparedStatement = connection.prepareStatement(SelectQuery);
			ResultSet rs = getPreparedStatement.executeQuery();
			getPreparedStatement.close();

			List<Emprunt> liste = new ArrayList<Emprunt>();
			while (rs.next()) {
				Abonnement abonnement = null;
				switch (rs.getString("abonnement")) {
					case "BASIC":
						abonnement = Abonnement.BASIC;
						break;
					case "PREMIUM":
						abonnement = Abonnement.PREMIUM;
						break;
					case "VIP":
						abonnement = Abonnement.VIP;
						break;
				}
				if (abonnement == null) {
					throw new SQLException();
				}
				Membre membre = new Membre(rs.getInt("idMembre"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("adresse"), rs.getString("email"), rs.getString("telephone"), abonnement);
				Livre livre = new Livre(rs.getInt("idLivre"), rs.getString("titre"), rs.getString("auteur"),
						rs.getString("isbn"));

				liste.add(Emprunt(rs.getInt("idEmprunt"), membre, livre, rs.getDate("dateEmprunt").toLocalDate(),
						rs.getDate("dateRetour").toLocalDate()));

			}
			return liste;

		} catch (SQLException e) {
			throw new DaoException("Erreur : liste des emprunts");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	/**
	 * @brief Recupere la liste des emprunts en cours de la BDD
	 * @param none
	 * @return List<Emprunt> liste des emprunts en cours
	 */
	public List<Emprunt> getListCurrent() throws DaoException {
		Connection connection = EstablishConnection.getConnection();
		PreparedStatement getPreparedStatement = null;

		String SelectQuery = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email,telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL;";

		try {
			getPreparedStatement = connection.prepareStatement(SelectQuery);
			ResultSet rs = getPreparedStatement.executeQuery();
			getPreparedStatement.close();

			List<Emprunt> liste = new ArrayList<Emprunt>();
			while (rs.next()) {
				Abonnement abonnement = null;
				switch (rs.getString("abonnement")) {
					case "BASIC":
						abonnement = Abonnement.BASIC;
						break;
					case "PREMIUM":
						abonnement = Abonnement.PREMIUM;
						break;
					case "VIP":
						abonnement = Abonnement.VIP;
						break;
				}
				if (abonnement == null) {
					throw new SQLException();
				}
				Membre membre = new Membre(rs.getInt("idMembre"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("adresse"), rs.getString("email"), rs.getString("telephone"), abonnement);
				Livre livre = new Livre(rs.getInt("idLivre"), rs.getString("titre"), rs.getString("auteur"),
						rs.getString("isbn"));

				liste.add(Emprunt(rs.getInt("idEmprunt"), membre, livre, rs.getDate("dateEmprunt").toLocalDate(),
						rs.getDate("dateRetour").toLocalDate()));

			}
			return liste;

		} catch (SQLException e) {
			throw new DaoException("Erreur : liste des emprunts en cours ");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	/**
	 * @brief Recupere la liste des emprunts en cours d'un membre
	 * @param int id du membre
	 * @return List<Emprunt> liste des emprunts
	 */
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException {
		Connection connection = EstablishConnection.getConnection();
		PreparedStatement getPreparedStatement = null;

		String SelectQuery = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email,telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL AND membre.id = ?;";
		try {
			getPreparedStatement = connection.prepareStatement(SelectQuery);
			getPreparedStatement.setInt(1, idMembre);
			ResultSet rs = getPreparedStatement.executeQuery();
			getPreparedStatement.close();

			List<Emprunt> liste = new ArrayList<Emprunt>();
			while (rs.next()) {
				Abonnement abonnement = null;
				switch (rs.getString("abonnement")) {
					case "BASIC":
						abonnement = Abonnement.BASIC;
						break;
					case "PREMIUM":
						abonnement = Abonnement.PREMIUM;
						break;
					case "VIP":
						abonnement = Abonnement.VIP;
						break;
				}
				if (abonnement == null) {
					throw new SQLException();
				}
				Membre membre = new Membre(rs.getInt("idMembre"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("adresse"), rs.getString("email"), rs.getString("telephone"), abonnement);
				Livre livre = new Livre(rs.getInt("idLivre"), rs.getString("titre"), rs.getString("auteur"),
						rs.getString("isbn"));

				liste.add(Emprunt(rs.getInt("idEmprunt"), membre, livre, rs.getDate("dateEmprunt").toLocalDate(),
						rs.getDate("dateRetour").toLocalDate()));

			}
			return liste;

		} catch (SQLException e) {
			throw new DaoException("Erreur : liste des emprunts du membre " + idMembre);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	/**
	 * @brief Recupere la liste des emprunts en cours d'un livre
	 * @param int id du livre
	 * @return List<Emprunt> liste des emprunts
	 */
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException {
		Connection connection = EstablishConnection.getConnection();
		PreparedStatement getPreparedStatement = null;

		String SelectQuery = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email,telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL AND livre.id = ?;";

		try {
			getPreparedStatement = connection.prepareStatement(SelectQuery);
			getPreparedStatement.setInt(1, idLivre);
			ResultSet rs = getPreparedStatement.executeQuery();
			getPreparedStatement.close();

			List<Emprunt> liste = new ArrayList<Emprunt>();
			while (rs.next()) {
				Abonnement abonnement = null;
				switch (rs.getString("abonnement")) {
					case "BASIC":
						abonnement = Abonnement.BASIC;
						break;
					case "PREMIUM":
						abonnement = Abonnement.PREMIUM;
						break;
					case "VIP":
						abonnement = Abonnement.VIP;
						break;
				}
				if (abonnement == null) {
					throw new SQLException();
				}
				Membre membre = new Membre(rs.getInt("idMembre"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("adresse"), rs.getString("email"), rs.getString("telephone"), abonnement);
				Livre livre = new Livre(rs.getInt("idLivre"), rs.getString("titre"), rs.getString("auteur"),
						rs.getString("isbn"));

				liste.add(Emprunt(rs.getInt("idEmprunt"), membre, livre, rs.getDate("dateEmprunt").toLocalDate(),
						rs.getDate("dateRetour").toLocalDate()));

			}
			return liste;

		} catch (SQLException e) {
			throw new DaoException("Erreur : emprunts en cours du livre " + idLivre);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	/**
	 * @brief Recupere un emprunt
	 * @param int id de l'emprunt
	 * @return Emprunt
	 */
	public Emprunt getById(int id) throws DaoException {
		Connection connection = EstablishConnection.getConnection();
		PreparedStatement getPreparedStatement = null;

		String SelectQuery = "SELECT e.id AS idEmprunt, idMembre, nom, prenom, adresse, email,telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,dateRetour, FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE e.id = ?;";

		try {
			getPreparedStatement = connection.prepareStatement(SelectQuery);
			getPreparedStatement.setInt(1, id);
			ResultSet rs = getPreparedStatement.executeQuery();
			getPreparedStatement.close();
			Abonnement abonnement = null;
			switch (rs.getString("abonnement")) {
				case "BASIC":
					abonnement = Abonnement.BASIC;
					break;
				case "PREMIUM":
					abonnement = Abonnement.PREMIUM;
					break;
				case "VIP":
					abonnement = Abonnement.VIP;
					break;
			}
			if (abonnement == null) {
				throw new SQLException();
			}
			Membre membre = new Membre(rs.getInt("idMembre"), rs.getString("nom"), rs.getString("prenom"),
					rs.getString("adresse"), rs.getString("email"), rs.getString("telephone"), abonnement);
			Livre livre = new Livre(rs.getInt("idLivre"), rs.getString("titre"), rs.getString("auteur"),
					rs.getString("isbn"));

			return Emprunt(rs.getInt("idEmprunt"), membre, livre, toLocalDate(rs.getDate("dateEmprunt")),
					toLocalDate(rs.getDate("dateRetour")));
		} catch (SQLException e) {
			throw new DaoException("Erreur : Emprunt.getById !");
		} finally {
			connection.close();
		}
	}

	/**
	 * @brief Cree un emprunt
	 * @param int id de l'emprunt, int id du Livre, LocalDate date de l'emprunt
	 * @return void
	 */
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException {
		Connection connection;
		try {
			connection = EstablishConnection.getConnection();
			PreparedStatement getPreparedStatement = null;

			String InsertQuery = "INSERT INTO emprunt(idMembre, idLivre, dateEmprunt, dateRetour) VALUES (?, ?, ?, ?);";

			getPreparedStatement = connection.prepareStatement(InsertQuery);
			getPreparedStatement.setInt(1, idMembre);
			getPreparedStatement.setInt(2, idLivre);
			getPreparedStatement.setDate(2, Date.valueOf(dateEmprunt));
			//Calcul de la date de retour : depend de l'abonnement du membre
			LocalDate dateRetour = null;
			Membre membre = MembreDaoImpl.getById(idMembre);
			switch()
			getPreparedStatement.executeQuery();
			getPreparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			throw new DaoException(
					"Erreur : Creation de l'emprunt de " + idLivre + " par " + idMembre + " le " + dateEmprunt);
		}
	}

	/**
	 * @brief Met a jour un emprunt
	 * @param Emprunt emprunt
	 * @return void
	 */
	public void update(Emprunt emprunt) throws DaoException {
		Connection connection;
		try {
			connection = EstablishConnection.getConnection();
			PreparedStatement getPreparedStatement = null;

			String InsertQuery = "UPDATE emprunt SET idMembre = ?, idLivre = ?, dateEmprunt = ?, dateRetour = ? WHERE id = ?;";

			getPreparedStatement = connection.prepareStatement(InsertQuery);
			getPreparedStatement.setInt(1, emprunt.getMembre().getKey());
			getPreparedStatement.setInt(2, emprunt.getLivre().getId());
			getPreparedStatement.setDate(2, Date.valueOf(emprunt.getDateEmprunt()));
			getPreparedStatement.setDate(3, Date.valueOf(emprunt.getDateRetour()));
			getPreparedStatement.setInt(4, emprunt.getId());
			getPreparedStatement.executeQuery();
			getPreparedStatement.close();
			connection.close();
		} catch (

		SQLException e) {
			throw new DaoException(
					"Erreur : Mise a jour de l'emprunt de " + emprunt.getLivre() + " par " + emprunt.getMembre());
		}
	}

	public int count() throws DaoException {
		Connection connection;
		try {
			connection = EstablishConnection.getConnection();
			PreparedStatement getPreparedStatement = null;

			String CountQuery = "SELECT COUNT(id) AS count FROM emprunt;";

			getPreparedStatement = connection.prepareStatement(CountQuery);
			ResultSet rs = getPreparedStatement.executeQuery();
			getPreparedStatement.close();
			connection.close();
			return rs.getInt(1);
		} catch (SQLException e) {
			throw new DaoException("Erreur : Comptage des emprunts");
		}
	}

}