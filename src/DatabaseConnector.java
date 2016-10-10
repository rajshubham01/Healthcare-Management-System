import java.sql.*;

public class DatabaseConnector {
	private static final String jdbcURL = "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";
	private static String user = "agarg12";
	private static String password = "200157990";
	private static Connection conn = null;
	private static Statement stmt = null;
	
	public DatabaseConnector(String user, String password) {
		DatabaseConnector.user = user;
		DatabaseConnector.password = password;
		
	}
	
	public static void connect() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch (Exception e) {
			System.out.println("Unable to load Oracle Driver");
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection(jdbcURL, user, password);
		}
		catch (Exception e) {
			System.out.println("Unable to connect to server");
		}
		
		try {
			stmt = conn.createStatement();
		}
		catch (Exception e) {
			System.out.println("Unable to create a statement for the connection");
		}
		
	}
	
	public static ResultSet runQuery (String query) {
		ResultSet res = null;
		
		if (conn == null) {
			System.err.println("Error: Can't runQuery before creating a connection");
			return null;
		}
		if (stmt == null) {
			try {
				stmt = conn.createStatement();
			}
			catch (Exception e) {
				System.out.println("Error: Unable to create a statement");
			}
		}
		try {
			res = stmt.executeQuery(query);
		}
		catch (Exception e) {
			System.out.println("Error executing query");
		}
		
		return res;
	}
	
	public static void updateDB (String sql) throws SQLException {
		if (conn == null) {
			System.err.println("Error: Can't updateDB before creating a connection");
			return;
		}
		if (stmt == null) {
			stmt = conn.createStatement();
		}
		stmt.executeUpdate(sql);
	}
	
	public static void disconnect() {
		/*
		if (conn != null) {
			try {
				conn.close();
			}
			catch (Exception e) {
				System.out.println("Unable to close the connection");
			}
		}
		
		if (stmt != null) {
			try {
				stmt.close();
			}
			catch (Exception e) {
				System.out.println("Unable to close the statement");
			}
		}
		*/
	}
}
