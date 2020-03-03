package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Film;

import utils.EstablishConnection;

public class FilmDao {
	public Film get(int id) throws SQLException {
		// Récupère un film à partir de l'id de la BDD
		Connection connection = EstablishConnection.getConnection();
		PreparedStatement getPreparedStatement = null;
		
		String SelectQuery = "SELECT * FROM film WHERE id=?";
		
		try {
			getPreparedStatement = connection.prepareStatement(SelectQuery);
			getPreparedStatement.setInt(1, id);
			ResultSet rs = getPreparedStatement.executeQuery();
            System.out.println("Id : " + rs.getInt("id") + " Titre : " + rs.getString("titre") + " Name : " + rs.getString("realisateur"));
			getPreparedStatement.close();
			return Film(rs.getInt("id"), rs.getString("titre"), rs.getString("realisateur"));
	    }
		catch (SQLException e) {
	        System.out.println("Exception Message " + e.getLocalizedMessage());
	    }
		catch (Exception e) {
	        e.printStackTrace();
	    }
		finally {
	        connection.close();
	    }
	}
	
	public void getAll() throws SQLException {
		// Récupère tous les films de la BDD
		Connection connection = EstablishConnection.getConnection();
		PreparedStatement getAllPreparedStatement = null;
		
		String SelectQuery = "SELECT * FROM film";
		
		try {
			getAllPreparedStatement = connection.prepareStatement(SelectQuery);
			ResultSet rs = getAllPreparedStatement.executeQuery();
			while (rs.next()) {
				System.out.println("Id : " + rs.getInt("id") + " Titre : " + rs.getString("titre") + " Name : " + rs.getString("realisateur"));
			}
			getAllPreparedStatement.close();		
	    }
		catch (SQLException e) {
	        System.out.println("Exception Message " + e.getLocalizedMessage());
	    }
		catch (Exception e) {
	        e.printStackTrace();
	    }
		finally {
	        connection.close();
	    }
	}

	
	public void create(Film film) throws SQLException {
		// Ajoute un film à la BDD à partir d'un objet film
		Connection connection = EstablishConnection.getConnection();
		PreparedStatement insertPreparedStatement = null;
		
		String InsertQuery = "INSERT INTO film" + "(titre, realisateur) values" + "(?,?)";
		
		try {
            insertPreparedStatement = connection.prepareStatement(InsertQuery);
            insertPreparedStatement.setString(1, film.get_titre());
            insertPreparedStatement.setString(2, film.get_realisateur());
            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.close();
	    }
		catch (SQLException e) {
	        System.out.println("Exception Message " + e.getLocalizedMessage());
	    }
		catch (Exception e) {
	        e.printStackTrace();
	    }
		finally {
	        connection.close();
	    }
	}

	
	public void update(Film film) throws SQLException {
		// Update un film à partir du film en argument
		Connection connection = EstablishConnection.getConnection();
		PreparedStatement updatePreparedStatement = null;
		
		String UpdateQuery = "UPDATE film SET titre=?, realisateur=? WHERE id=?";
		
		try {
			updatePreparedStatement = connection.prepareStatement(UpdateQuery);
			updatePreparedStatement.setString(1, film.get_titre());
			updatePreparedStatement.setString(2, film.get_realisateur());
			updatePreparedStatement.setInt(3, film.get_key());
			
			updatePreparedStatement.executeQuery();

			updatePreparedStatement.close();		
	    }
		catch (SQLException e) {
	        System.out.println("Exception Message " + e.getLocalizedMessage());
	    }
		catch (Exception e) {
	        e.printStackTrace();
	    }
		finally {
	        connection.close();
	    }
	}
	
	public void delete(int id) throws SQLException {
		// Récupère un film à partir de l'id de la BDD
		Connection connection = EstablishConnection.getConnection();
		PreparedStatement deletePreparedStatement = null;
		
		String DeleteQuery = "DELETE FROM film WHERE id=?";
		
		try {
			deletePreparedStatement = connection.prepareStatement(DeleteQuery);
			deletePreparedStatement.setInt(1, id);
			deletePreparedStatement.executeQuery();
			deletePreparedStatement.close();		
	    }
		catch (SQLException e) {
	        System.out.println("Exception Message " + e.getLocalizedMessage());
	    }
		catch (Exception e) {
	        e.printStackTrace();
	    }
		finally {
	        connection.close();
	    }
	}
	
}

