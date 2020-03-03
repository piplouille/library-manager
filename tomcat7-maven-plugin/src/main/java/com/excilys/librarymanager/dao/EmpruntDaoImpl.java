package com.excilys.librarymanager.dao;

public class EmpruntDaoImpl implements EmpruntDao
{
    public Emprunt getById(int id) throws DaoException
    {
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
    
    public List<Emprunt> getList() throws DaoException;
}