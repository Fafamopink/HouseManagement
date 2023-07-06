package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.HOUSESDATA;
import model.MeterReadModel;

public class MeterReadingDAO {
	String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
	String jdbcUsername = "system";
	String jdbcPassword = "dev1xd";

	public Connection getConnection() throws SQLException, SQLTimeoutException {

		Connection connection = null;
		try {
			// Class.forName("com.mysql.jdbc.Driver");
			///// for MySql connection
			Class.forName(DB_DRIVER);
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}

		catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		/*
		 * catch(Exception ex) { System.out.println(ex.getMessage()); }
		 */

		return connection;

	}
	
	
    public List<MeterReadModel> ViewHouseReading(int id)throws SQLException, SQLTimeoutException {
    	List<MeterReadModel> MeterReadinfo = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement("select * from meterreading where housenum =?");)
        {
        	preparedStatement.setInt(1, id);// set the "?" to the value of the 1st param
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("select track");

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
				int reading = rs.getInt("reading");
				Date date1 = rs.getDate("date1");
				String notes = rs.getString("notes");
				int housenum1 = rs.getInt("housenum");
				int entryIDMR = rs.getInt("entryIDMR");

				MeterReadinfo.add(new MeterReadModel(reading, date1, notes, housenum1, entryIDMR));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return MeterReadinfo;
    }

	
	 

	public List<MeterReadModel> ViewAllHouseReading() throws SQLException, SQLTimeoutException {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<MeterReadModel> MeterReadinfo = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement("select * from meterreading");) {
			System.out.println("ViewAllHouseReading track initial");
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int reading = rs.getInt("reading");
				Date date1 = rs.getDate("date1");
				String notes = rs.getString("notes");
				int housenum1 = rs.getInt("housenum");
				int entryIDMR = rs.getInt("entryIDMR");

				MeterReadinfo.add(new MeterReadModel(reading, date1, notes, housenum1, entryIDMR));
			}
			System.out.println("ViewAllHouseReading track final");
		} catch (SQLException e) {
			printSQLException(e);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return MeterReadinfo;
	}
    public void deleteReading (int id) throws SQLException {

        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement("delete from meterreading where entryIDMR = ?");) {
            statement.setInt(1, id);
            int rowdeleted =statement.executeUpdate();
            System.out.println("delete track for reading");
            if (rowdeleted>0)
            	System.out.println("row deleted for reading");        }

    }

	/*
	 * public void deleteHouse(int housenum) throws SQLException,
	 * SQLTimeoutException {
	 *
	 * try { Connection connection = getConnection(); PreparedStatement statement =
	 * connection.prepareStatement("delete from housenumber where id = ?",
	 * housenum);
	 *
	 *
	 * int rowsUpdated = statement.executeUpdate(); if (rowsUpdated > 0) {
	 * System.out.println("An existing user was updated successfully!"); }
	 *
	 * }catch (SQLException e) { printSQLException(e); }
	 *
	 * }
	 */

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}