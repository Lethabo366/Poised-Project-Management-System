package dao;

/**
 * @author Lethabo
 * @version 2024/10/01
 * 
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import models.ProjManager;
import utility.ConnectionDAO;

/**
 * @author Lethabo
 * @version 2024/10/01
 * 
 */
public class ProjManagerDAO {

  ProjManager projManager;

  /**
   * Below this will create a projManagerDAO Object using the projManager object
   * 
   * @param projManager contains the object with that will be used for the
   *                    projManagerDAO object
   */
  public ProjManagerDAO(ProjManager projManager) {
    this.projManager = projManager;
  }

  /**
   * This method will insert the ProjManager Object within the ProjManagerDAO
   * object into the PoisePms Database Proj_Managers Table.
   * 
   * @throws SQLException because the method connects to the SQL Database.
   */
  public void insert() throws SQLException {
    PreparedStatement preparedStatement;
    int rowsAffected;

    Connection connection = ConnectionDAO.connect();

    String sqlInput = "INSERT INTO Proj_Managers (Surname,Name,Telephone_Number,Email_Address,Physical_Address) "
        + "VALUES(?,?,?,?,?)";
    try {
      preparedStatement = connection.prepareStatement(sqlInput);
      preparedStatement.setString(1, projManager.getSurname());
      preparedStatement.setString(2, projManager.getName());
      preparedStatement.setString(3, projManager.getTelephoneNumber());
      preparedStatement.setString(4, projManager.getEmailAddress());
      preparedStatement.setString(5, projManager.getPhysicalAddress());

      rowsAffected = preparedStatement.executeUpdate();

      System.out.println(rowsAffected + " Entry has been updated. ");

    } catch (SQLException e) {
      System.out.println("Failed insert new Entry in the database.");
    }

  }

  /**
   * This method updates an entry from the PoisePMS database from the
   * Proj_Managers table that has the projManagerID/primaryKey entered in the
   * parameters and updates it with the ProjManager Object in the ProjManagerDAO
   * Object.
   * 
   * @param projManagerID contains the projManagerID/primaryKey of the
   *                      Proj_Managers entry that will be updated.
   * @throws SQLException because the method is connecting to the SQL Database.
   */
  public void update(int projManagerID) throws SQLException {
    PreparedStatement preparedStatement;
    int rowsAffected;

    Connection connection = ConnectionDAO.connect();

    String sqlInput = "Update Proj_Managers  SET Surname = ? ,Name = ? ,Telephone_Number = ?,Email_Address = ?,Physical_Address = ? "
        + "WHERE Proj_Manager_id = ?";
    try {
      preparedStatement = connection.prepareStatement(sqlInput);
      preparedStatement.setString(1, projManager.getSurname());
      preparedStatement.setString(2, projManager.getName());
      preparedStatement.setString(3, projManager.getTelephoneNumber());
      preparedStatement.setString(4, projManager.getEmailAddress());
      preparedStatement.setString(5, projManager.getPhysicalAddress());
      preparedStatement.setInt(6, projManagerID);

      rowsAffected = preparedStatement.executeUpdate();

      System.out.println(rowsAffected + " Entry has been updated. ");

    } catch (SQLException e) {
      System.out.println("Failed insert new Entry in the database.");
    }

  }

  /**
   * The method below deletes the Proj_Managers entry in the database that has the
   * same id as the number contained under the variable integer id
   * 
   * @param id contains the id/primaryKey of the Proj_Managers entry that needs to
   *           be deleted.
   * @throws SQLException because it connects to the SQL Database.
   */
  public static void delete(int id) throws SQLException {
    PreparedStatement preparedStatement;
    int rowsAffected = 0;
    Connection connection = ConnectionDAO.connect();

    String sqlInput = "DELETE FROM Proj_Managers WHERE Proj_Manager_id = ?";

    preparedStatement = connection.prepareStatement(sqlInput);
    preparedStatement.setInt(1, id);
    rowsAffected = preparedStatement.executeUpdate();

    System.out.println(rowsAffected + " entry has been deleted. ");

  }

  /**
   * The method below prompts the user to enter the information needed to create a
   * new ProjManagerDAO entry and inserts it into the PoisePMS database.
   * 
   * @param input is used to get input from the user
   * @throws SQLException because the method connects to the SQL Database.
   */
  public static void inputAndInsert(Scanner input) throws SQLException {
    System.out.println("Enter the Name of the Project Manager.");
    String name = input.nextLine();

    System.out.println("Enter the Surname of the Project Manager.");
    String surname = input.nextLine();

    System.out.println("Enter the Telephone Number of the Project Manager.");
    String telephoneNumber = input.nextLine();

    String range = "[0-9]+";
    while (!telephoneNumber.startsWith("0") || !telephoneNumber.startsWith("+") && !telephoneNumber.matches(range)) {
      System.out.println("Type in a valid Telephone number that starts with either 0 or +");
      telephoneNumber = input.nextLine();
    }

    System.out.println("Enter the Email Address of the Project Manager.");
    String emailAddress = input.nextLine();

    while (!emailAddress.contains("@") && !emailAddress.contains(".")) {
      System.out.println("Type in a Valid email Address");
      emailAddress = input.nextLine();
    }

    System.out.println("Enter the Physical Address of the Project Manager.");
    String physicalAddress = input.nextLine();

    ProjManager projManager = new ProjManager(surname, name, telephoneNumber, emailAddress, physicalAddress);
    ProjManagerDAO projManagerDAO = new ProjManagerDAO(projManager);
    projManagerDAO.insert();
  }

  /**
   * This method retrieves an entry from the PoisePMS database from the
   * Proj_Managers table with the projManagerID/primaryKey entered in the get
   * parameters and returns it as a projManager Object.
   * 
   * @param projManagerID contains the projManagerID/primaryKey of the projManager
   *                      Entry that the method will retrieve.
   * @return a ProjManager Object
   * @throws SQLException because the methods connects to the SQL Database.
   */
  public static ProjManager get(int projManagerID) throws SQLException {

    Connection connection = ConnectionDAO.connect();

    String sqlInput = "SELECT * FROM Proj_Managers WHERE Proj_Manager_id = ?";

    PreparedStatement preparedStatement = connection.prepareStatement(sqlInput);
    preparedStatement.setInt(1, projManagerID);
    ResultSet results = preparedStatement.executeQuery();
    ProjManager projManager = null;

    if (results.next()) {
      String surname = results.getString("Surname");
      String name = results.getString("Name");
      String telephoneNumber = results.getString("Telephone_Number");
      String emailAddress = results.getString("Email_Address");
      String physicalAddress = results.getString("Physical_Address");

      projManager = new ProjManager(surname, name, telephoneNumber, emailAddress, physicalAddress);
    }
    return projManager;
  }

  /**
   * This will print the ResultSets for the Queries from the Proj_Managers table
   * from the PoisePMS Database
   * 
   * @param results contains the results from the query made from the
   *                Proj_Managers table in the PoisePMS
   */
  public static void printQuery(ResultSet results) {
    try {
      while (results.next()) {
        System.out.println(results.getInt("Proj_Manager_id") + " , " + results.getString("Surname") + " , "
            + results.getString("Name") + " , " + results.getString("Telephone_Number") + " , "
            + results.getString("Email_Address") + " ," + results.getString("Physical_Address"));
      }
    } catch (SQLException e) {
      System.out.println("Failed to print personal information.");
    }
  }

  /**
   * This method will run a string as an SQL statement then print the results of
   * the Query.
   * 
   * @param sqlInput is the string that will be run like a SQL Statement.
   * @throws SQLException because it connects to the SQL Database.
   */
  public static void runQuery(String sqlInput) throws SQLException {
    Connection connection = ConnectionDAO.connect();

    try {
      PreparedStatement preparedStatement;
      ResultSet results;
      preparedStatement = connection.prepareStatement(sqlInput);
      results = preparedStatement.executeQuery();

      ProjManagerDAO.printQuery(results);
    } catch (SQLException e) {
      System.out.println("Failed to retrieve Project Information.");
    }
  }

  /**
   * This is a method that will take a string that contains a SQL Statement that
   * intends to search the SQL database with a like Function and using a phrase or
   * number that is entered(userSearch).
   * 
   * @param sqlInput   contains the string that will be run as a SQL Statement and
   *                   contains a like function.
   * @param userSearch contains the string/phrase the user would like to use
   *                   search the database with.
   * @throws SQLException the method connects to the SQL Database.
   */
  public static void runSearch(String sqlInput, String userSearch) throws SQLException {

    Connection connection = ConnectionDAO.connect();

    try {
      PreparedStatement preparedStatement;
      ResultSet results;
      preparedStatement = connection.prepareStatement(sqlInput);
      preparedStatement.setString(1, "%" + userSearch + "%");

      results = preparedStatement.executeQuery();

      /**
       * the results found when the executeQuery was executed will printed
       */
      ProjManagerDAO.printQuery(results);

    } catch (SQLException e) {
      System.out.println("Failed to Execute Search in the database.");
    }
  }

  /**
   * This method will print all the entries in the Proj_Managers table
   * 
   * @throws SQLException because the method connects to the SQL Database.
   */
  public static void printAll() throws SQLException {
    Connection connection = ConnectionDAO.connect();
    PreparedStatement preparedStatement;
    ResultSet results;

    String sqlInput = "SELECT * FROM Proj_Managers";

    preparedStatement = connection.prepareStatement(sqlInput);
    results = preparedStatement.executeQuery();

    printQuery(results);

  }

  /**
   * This method will prompt the user to choose which column they would like to
   * filter by , then the user will be prompted to enter the phrase or number they
   * would like to search for then the Proj_Managers entries will be filtered by
   * the phrase by that column.
   * 
   * @param input will be used to get input from them keyboard
   * @throws SQLException because the method connects to the SQL database.
   */
  public static void likeSearch(Scanner input) throws SQLException {

    System.out.println("\nChoose a Column to Search by.\n1.Proj_Manager_id"
        + "\n2.Surname\n3.Name\n4.Telephone Number\n5.Email Address\n6.Physical Address");
    int userColumn = input.nextInt();
    input.nextLine();

    System.out.println("Enter the phrase or char You would like to filter by.");
    String userSearch = input.nextLine();
    /*
     * Below for each option that is chosen i have created a individual
     * SQLStatements for each Column the user chooses to search by and the statement
     * will be executed using the runSearch method from the ProjManagerDAO class.
     */
    if (userColumn == 1) {
      String sqlInput = "SELECT * FROM Proj_Managers WHERE Proj_Manager_id LIKE ?";
      ProjManagerDAO.runSearch(sqlInput, userSearch);

    } else if (userColumn == 2) {
      String sqlInput = "SELECT * FROM Proj_Managers WHERE Surname LIKE ?";
      ProjManagerDAO.runSearch(sqlInput, userSearch);

    } else if (userColumn == 3) {
      String sqlInput = "SELECT * FROM Proj_Managers WHERE Name LIKE ?";
      ProjManagerDAO.runSearch(sqlInput, userSearch);

    } else if (userColumn == 4) {
      String sqlInput = "SELECT * FROM Proj_Managers WHERE Telephone_Number LIKE ?";
      ProjManagerDAO.runSearch(sqlInput, userSearch);

    } else if (userColumn == 5) {
      String sqlInput = "SELECT * FROM Proj_Managers WHERE Email_Address LIKE ?";
      ProjManagerDAO.runSearch(sqlInput, userSearch);

    } else if (userColumn == 6) {
      String sqlInput = "SELECT * FROM Proj_Managers WHERE Physical_Address LIKE ?";
      ProjManagerDAO.runSearch(sqlInput, userSearch);
    }
  }

  /**
   * The user will be prompted to enter the Project Manager id for the
   * Proj_Managers entry which contains a field they would like to update then the
   * user will be prompted to enter the number of the field they would like to
   * update of that Proj_Managers entry then the user's entry will be updated into
   * the PoisePMS database
   * 
   * @param input the scanner object that is being used to collect user input in
   *              this program
   * @throws SQLException because the method is connecting to the SQL Database.
   */
  public static void updateDatabase(Scanner input) throws SQLException {
    System.out.println("Enter the Project Manager id of the entry you want to Update.");
    int projManagerID = input.nextInt();
    input.nextLine();

    System.out.println(
        "Which field would you like to update?\n1.Surname\n2.Name\n3.Telephone Number\n4.Email Address\n5.Physical Address"
            + "\nEnter the number of the option you choose");
    int userChoice1 = input.nextInt();
    input.nextLine();

    System.out.println("Enter the new Value update to.");
    String newValue = input.nextLine();

    /*
     * Below for each option i am retrieving the entry the user wants to update as a
     * ProjManager object and storing it under the variable projManager then using
     * the set method the field the user wants to update will be changed then the
     * projManager variable will stored within the ProjManagerDAO Object then
     * updated using the update method from the ProjManagerDAO class.
     */
    if (userChoice1 == 1) {
      ProjManager projManager = ProjManagerDAO.get(projManagerID);
      projManager.setSurname(newValue);
      ProjManagerDAO projManagerDAO = new ProjManagerDAO(projManager);
      projManagerDAO.update(projManagerID);

    } else if (userChoice1 == 2) {
      ProjManager projManager = ProjManagerDAO.get(projManagerID);
      projManager.setName(newValue);
      ProjManagerDAO projManagerDAO = new ProjManagerDAO(projManager);
      projManagerDAO.update(projManagerID);

    } else if (userChoice1 == 3) {
      ProjManager projManager = ProjManagerDAO.get(projManagerID);

      // Below the telephoneNumber the user input will be validated to make sure it is
      // valid
      // if not then the user will be ask to Re-Enter until it is valid
      String range = "[0-9]+";
      while (!newValue.startsWith("0") || !newValue.startsWith("+") && !newValue.matches(range)) {
        System.out.println("Type in a valid Telephone number that starts with either 0 or +");
        newValue = input.nextLine();
      }
      projManager.setTelephoneNumber(newValue);
      ProjManagerDAO projManagerDAO = new ProjManagerDAO(projManager);
      projManagerDAO.update(projManagerID);

    } else if (userChoice1 == 4) {
      ProjManager projManager = ProjManagerDAO.get(projManagerID);
      // Below the emailAddress the user input will be validated to make sure it is
      // valid
      // if not then the user will be ask to Re-Enter until it is valid
      while (!newValue.contains("@") && !newValue.contains(".")) {
        System.out.println("Type in a Valid email Address");
        newValue = input.nextLine();
      }
      projManager.setEmailAddress(newValue);

      while (newValue.contains("@") && newValue.contains(".")) {
        System.out.println("Type in a Valid email Address");

        newValue = input.nextLine();

      }
      ProjManagerDAO projManagerDAO = new ProjManagerDAO(projManager);
      projManagerDAO.update(projManagerID);

    } else if (userChoice1 == 5) {
      ProjManager projManager = ProjManagerDAO.get(projManagerID);
      projManager.setSurname(newValue);
      ProjManagerDAO projManagerDAO = new ProjManagerDAO(projManager);
      projManagerDAO.update(projManagerID);

    } else {
      System.out.println("Invalid Option Entered!!");
    }
  }

}
