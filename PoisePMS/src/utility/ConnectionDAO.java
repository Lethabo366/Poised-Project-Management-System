package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Lethabo
 * @version 2024/10/01
 * 
 */
public class ConnectionDAO {
 
  /**
   * the method below creates a connection to the MySQL PoisePMS database and
   * returns it.
   * @return connection object to be used to connect to the database.
   * @throws SQLException the method creates a connection to the MySQL Database.
   */
  public static Connection connect() throws SQLException {

    Connection connection = null;
    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS", "otheruser", "swordfish");

    return connection;

  }

}
