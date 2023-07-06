package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.ArrayList;
import java.util.List;

import model.HOUSESDATA;// step 1 import the.java bean or the java of getters and setters

public class HOUSEDAO {
    String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
    String jdbcUsername = "system";
    String jdbcPassword = "dev1xd";

    public  Connection getConnection() throws SQLException, SQLTimeoutException {

    	Connection connection=null;
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            /////for MySql connection
            Class.forName(DB_DRIVER);
             connection= DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
 }

        catch(Exception e) {
       	 System.out.println(e.getStackTrace());
        }
		/*
		 * catch(Exception ex) { System.out.println(ex.getMessage()); }
		 */


		return connection;


    }

    public void insertHouse(HOUSESDATA housedata) throws SQLException, SQLTimeoutException {
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection();
        		PreparedStatement statement = connection.prepareStatement("INSERT INTO housenumber VALUES  (housenumseq.NEXTVAL, ? )");)
        {
        //	statement.setInt(1, housedata.getHousenum());
        	statement.setString(1, housedata.getHousename());
        	statement.executeQuery();
            System.out.println("insert track");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    
    

    public HOUSESDATA selectHouse(int id)throws SQLException, SQLTimeoutException {
    	HOUSESDATA houseinfo = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement("select * from housenumber where housenum =?");)
        {
        	preparedStatement.setInt(1, id);// set the "?" to the value of the 1st param
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("select track");

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int housenum = rs.getInt("housenum");
                String housename = rs.getString("housename");
                houseinfo = new HOUSESDATA(housenum, housename);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return houseinfo;
    }

    public List < HOUSESDATA > selectAllHouses() throws SQLException, SQLTimeoutException {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < HOUSESDATA > houseinfo = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement("select * from housenumber ");) {
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int housenum = rs.getInt("housenum");
                String housename = rs.getString("housename");
                houseinfo.add(new HOUSESDATA(housenum, housename));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        catch(Exception ex) {
       	 System.out.println(ex.getMessage());
       }
        return houseinfo;
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
    public void deleteHouse (int id) throws SQLException {

        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement("delete from housenumber where housenum = ?");) {
            statement.setInt(1, id);
            int rowdeleted =statement.executeUpdate();
            System.out.println("delete track");
            if (rowdeleted>0)
            	System.out.println("row deleted");        }

    }





    public void updateHouse(HOUSESDATA housedataupdate) throws SQLException, SQLTimeoutException {

        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement("update housenumber set housename = ? where housenum = ?");){


            statement.setString(1, housedataupdate.getHousename());
           	statement.setInt(2, housedataupdate.getHousenum());

            int rowsUpdated = statement.executeUpdate();


            System.out.println(housedataupdate.getHousenum() + ", " + housedataupdate.getHousename() );
            System.out.println("update track");

            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }
        }catch (SQLException e) {
            printSQLException(e);
        }
        catch(Exception ex) {
       	 System.out.println(ex.getMessage());
        }
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
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