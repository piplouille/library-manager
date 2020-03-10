package com.excilys.librarymanager.dao;

import com.excilys.librarymanager.model.*;
import com.excilys.librarymanager.exception.*;

import java.sql.*;
import utils.EstablishConnection;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

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

	public List<Emprunt> getList() throws DaoException {
		Connection connection = EstablishConnection.getConnection();
		PreparedStatement getPreparedStatement = null;

		String SelectQuery = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email,telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre ORDER BY dateRetour DESC";

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

				liste.add(Emprunt(rs.getInt("idEmprunt"), membre, livre, toLocalDate(rs.getDate("dateEmprunt")),
						toLocalDate(rs.getDate("dateRetour"))));

			}
			return liste;

		} catch (SQLException e) {
			throw new DaoException("Erreur : Emprunt.getById !");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}


	public List<Emprunt> getListCurrent() throws DaoException
	{
		
	}

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

}