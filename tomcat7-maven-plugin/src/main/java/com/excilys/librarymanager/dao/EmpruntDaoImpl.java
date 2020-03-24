package com.excilys.librarymanager.dao;

import com.excilys.librarymanager.model.Emprunt;
import com.excilys.librarymanager.model.Abonnement;
import com.excilys.librarymanager.model.Membre;
import com.excilys.librarymanager.model.Livre;
import com.excilys.librarymanager.exception.DaoException;

import java.sql.*;
import com.excilys.librarymanager.utils.FillDatabase;
import com.excilys.librarymanager.persistence.ConnectionManager;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
		List<Emprunt> liste = null;
		Connection connection = null;
		PreparedStatement getPreparedStatement = null ;
		ResultSet rs=null ;
		try {
			connection = ConnectionManager.getConnection();

			String SelectQuery = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email,telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre"; //ORDER BY dateRetour DESC;";

			getPreparedStatement = connection.prepareStatement(SelectQuery);
			rs = getPreparedStatement.executeQuery();

			liste = new ArrayList<Emprunt>();
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
				if (rs.getDate("dateRetour") == null) {
					liste.add(new Emprunt(rs.getInt("id"), membre, livre, rs.getDate("dateEmprunt").toLocalDate()));					
				}
				else {
					liste.add(new Emprunt(rs.getInt("id"), membre, livre, rs.getDate("dateEmprunt").toLocalDate(),
							(rs.getDate("dateRetour").toLocalDate())));
				}

			}

		} catch (SQLException e) {
			throw new DaoException("Erreur : liste des emprunts");
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

	}

	/**
	 * @brief Recupere la liste des emprunts en cours de la BDD
	 * @param none
	 * @return List<Emprunt> liste des emprunts en cours
	 */
	public List<Emprunt> getListCurrent() throws DaoException {
		List<Emprunt> liste = null;

		Connection connection = null;
		PreparedStatement getPreparedStatement = null ;
		ResultSet rs =null;
		try {
			connection = ConnectionManager.getConnection();

			String SelectQuery = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email,telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL;";

			getPreparedStatement = connection.prepareStatement(SelectQuery);
			rs = getPreparedStatement.executeQuery();

			liste = new ArrayList<Emprunt>();
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
				Membre membre = new Membre(rs.getInt("idMembre"), rs.getString("nom"), rs.getString("prenom"),rs.getString("adresse"), rs.getString("email"), rs.getString("telephone"), abonnement);
				Livre livre = new Livre(rs.getInt("idLivre"), rs.getString("titre"), rs.getString("auteur"),rs.getString("isbn"));
				liste.add(new Emprunt(rs.getInt("id"), membre, livre, rs.getDate("dateEmprunt").toLocalDate()));
			}

		} catch (SQLException e) {
			throw new DaoException("Erreur : liste des emprunts en cours ");
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

	}

	/**
	 * @brief Recupere la liste des emprunts en cours d'un membre
	 * @param int id du membre
	 * @return List<Emprunt> liste des emprunts
	 */
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException {
		List<Emprunt> liste = null;
		
		Connection connection = null;
		PreparedStatement getPreparedStatement = null ;
		ResultSet rs=null ;
		try {
			connection = ConnectionManager.getConnection();
			String SelectQuery = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email,telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL AND membre.id = ?;";

			getPreparedStatement = connection.prepareStatement(SelectQuery);
			getPreparedStatement.setInt(1, idMembre);
			rs = getPreparedStatement.executeQuery();

			liste = new ArrayList<Emprunt>();
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

				liste.add(new Emprunt(rs.getInt("id"), membre, livre, rs.getDate("dateEmprunt").toLocalDate()));

			}

		} catch (SQLException e) {
			throw new DaoException("Erreur : liste des emprunts du membre " + idMembre);
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

	}

	/**
	 * @brief Recupere la liste des emprunts en cours d'un livre
	 * @param int id du livre
	 * @return List<Emprunt> liste des emprunts
	 */
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException {
		List<Emprunt> liste = null;

		Connection connection = null;
		PreparedStatement getPreparedStatement = null ;
		ResultSet rs=null ;
		try {
			connection = ConnectionManager.getConnection();
			String SelectQuery = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email,telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL AND livre.id = ?;";

			getPreparedStatement = connection.prepareStatement(SelectQuery);
			getPreparedStatement.setInt(1, idLivre);
			rs = getPreparedStatement.executeQuery();

			liste = new ArrayList<Emprunt>();
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

				liste.add(new Emprunt(rs.getInt("id"), membre, livre, rs.getDate("dateEmprunt").toLocalDate()));

			}

		} catch (SQLException e) {
			throw new DaoException("Erreur : emprunts en cours du livre " + idLivre);
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
	}

	/**
	 * @brief Recupere un emprunt
	 * @param int id de l'emprunt
	 * @return Emprunt
	 */
	public Emprunt getById(int id) throws DaoException {
		Emprunt emprunt=null;

		Connection connection = null;
		PreparedStatement getPreparedStatement = null ;
		ResultSet rs =null;
		try {
			connection = ConnectionManager.getConnection();

			String SelectQuery = "SELECT e.id AS idEmprunt, idMembre, nom, prenom, adresse, email,telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,dateRetour, FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE e.id = ?;";

			getPreparedStatement = connection.prepareStatement(SelectQuery);
			getPreparedStatement.setInt(1, id);
			rs = getPreparedStatement.executeQuery();
			rs.next();
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
			Membre membre = new Membre(rs.getInt("idMembre"), rs.getString("nom"), rs.getString("prenom"),rs.getString("adresse"), rs.getString("email"), rs.getString("telephone"), abonnement);
			Livre livre = new Livre(rs.getInt("idLivre"), rs.getString("titre"), rs.getString("auteur"),rs.getString("isbn"));

			emprunt = new Emprunt(rs.getInt("idEmprunt"), membre, livre, rs.getDate("dateEmprunt").toLocalDate());
		} catch (SQLException e) {
			throw new DaoException("Erreur : Emprunt.getById !");
		}
		finally {
            try {
                rs.close();
            } catch (Exception e) {
                throw new DaoException("Erreur : Emprunt.getById rs");
            }

            try {
                getPreparedStatement.close();
            }
            catch (Exception e) {
                throw new DaoException("Erreur : Emprunt.getById getPS");
            }

            try {
                connection.close();
            }
            catch (Exception e) {
				System.out.println("Exception Message " + e.getLocalizedMessage());
				throw new DaoException("Erreur : Emprunt.getById co");
            }
        }
		return emprunt;
	}

	/**
	 * @brief Cree un emprunt
	 * @param int id de l'emprunt, int id du Livre, LocalDate date de l'emprunt
	 * @return void
	 */
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException {
		Connection connection = null;
		PreparedStatement getPreparedStatement = null ;
		try {
			connection = ConnectionManager.getConnection();

			String InsertQuery = "INSERT INTO emprunt(idMembre, idLivre, dateEmprunt, dateRetour) VALUES (?, ?, ?, ?);";

			getPreparedStatement = connection.prepareStatement(InsertQuery);
			getPreparedStatement.setInt(1, idMembre);
			getPreparedStatement.setInt(2, idLivre);
			getPreparedStatement.setDate(3, Date.valueOf(dateEmprunt));
/* D'apres la construction de getListCurrent(), dans la date retour on doit mettre null
			// Calcul de la date de retour : depend de l'abonnement du membre
			// LocalDate dateRetour;
			// MembreDaoImpl mDaoImpl = MembreDaoImpl.getInstance();
			// Membre membre = mDaoImpl.getById(idMembre);
			// switch (membre.getAbonnement()) {
			// 	case BASIC:
			// 		dateRetour = dateEmprunt.plus(15, ChronoUnit.DAYS);
			// 		break;
			// 	case PREMIUM:
			// 		dateRetour = dateEmprunt.plus(1, ChronoUnit.MONTHS);
			// 		break;
			// 	case VIP:
			// 		dateRetour = dateEmprunt.plus(3, ChronoUnit.MONTHS);
			// 		break;
			// 	default:
			// 		dateRetour = null;
			// }
*/
			getPreparedStatement.setDate(4, null);
			getPreparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(
					"Erreur : Creation de l'emprunt de " + idLivre + " par " + idMembre + " le " + dateEmprunt);
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
	 * @brief Met a jour un emprunt
	 * @param Emprunt emprunt
	 * @return void
	 */
	public void update(Emprunt emprunt) throws DaoException {
		Connection connection = null;
		PreparedStatement getPreparedStatement = null ;
		try {
			connection = ConnectionManager.getConnection();

			String InsertQuery = "UPDATE emprunt SET idMembre = ?, idLivre = ?, dateEmprunt = ?, dateRetour = ? WHERE id = ?;";

			getPreparedStatement = connection.prepareStatement(InsertQuery);
			getPreparedStatement.setInt(1, emprunt.getMembre().getKey());
			getPreparedStatement.setInt(2, emprunt.getLivre().getId());
			getPreparedStatement.setDate(3, Date.valueOf(emprunt.getDateEmprunt()));
			getPreparedStatement.setDate(4, Date.valueOf(emprunt.getDateRetour()));
			getPreparedStatement.setInt(5, emprunt.getId());
			getPreparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(
					"Erreur : Mise a jour de l'emprunt de " + emprunt.getLivre() + " par " + emprunt.getMembre());
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

	public int count() throws DaoException {
		int count = -1;
		Connection connection = null;
		PreparedStatement getPreparedStatement = null ;
		ResultSet rs =null;
		try {
			connection = ConnectionManager.getConnection();

			String CountQuery = "SELECT COUNT(id) AS count FROM emprunt;";

			getPreparedStatement = connection.prepareStatement(CountQuery);
			rs = getPreparedStatement.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			throw new DaoException("Erreur : Comptage des emprunts");
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
		return count;
	}

}