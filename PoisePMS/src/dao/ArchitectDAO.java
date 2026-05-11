package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import models.Architect;
import utility.ConnectionDAO;

/**
 * @author Lethabo
 * @version 2024/10/01
 * 
 */
public class ArchitectDAO {
  /**
   * this class only has one attribute Architect architect attribute
   */
  Architect architect;

  /**
   * Below this will create a ArchitectDAO Object using the Architect
   * 
   * @param architect contains the object with that will be used for the
   *                  ArchitectDAO object
   */
  public ArchitectDAO(Architect architect) {
    super();
    this.architect = architect;
  }

  /**
   * This method will insert the Architect Object within the ArchitectDAO object
   * into the PoisePms Database Architects Table.
   * 
   * @throws SQLException because the method connects to the SQL Database.
   */
  public void insert() throws SQLException {
    PreparedStatement preparedStatement;
    int rowsAffected;

    Connection connection = ConnectionDAO.connect();

    String sqlInput = "INSERT INTO Architects (Surname,Name,Telephone_Number,Email_Address,Physical_Address) "
        + "VALUES(?,?,?,?,?)";
    try {
      preparedStatement = connection.prepareStatement(sqlInput);
      preparedStatement.setString(1, architect.getSurname());
      preparedStatement.setString(2, architect.getName());
      preparedStatement.setString(3, architect.getTelephoneNumber());
      preparedStatement.setString(4, architect.getEmailAddress());
      preparedStatement.setString(5, architect.getPhysicalAddress());

      rowsAffected = preparedStatement.executeUpdate();

      System.out.println(rowsAffected + " Entry has been updated. ");

    } catch (SQLException e) {
      System.out.println("Failed insert new Entry in the database.");
    }
  }

  /**
   * This method updates an entry from the PoisePMS database from the Architects
   * table that has the architectID/primaryKey entered in the parameters and
   * updates it with the Architect Object in the ArchitectDAO Object.
   * 
   * @param architectID contains the architectID/primaryKey of the Architects
   *                    entry that will be updated.
   * @throws SQLException because the method is connecting to the SQL Database.
   */
  public void update(int architectID) throws SQLException {
    PreparedStatement preparedStatement;
    int rowsAffected;

    Connection connection = ConnectionDAO.connect();

    String sqlInput = "Update Architects  SET Surname = ? ,Name = ? ,Telephone_Number = ?,Email_Address = ?,Physical_Address = ? "
        + "WHERE Architect_id = ?";
    try {
      preparedStatement = connection.prepareStatement(sqlInput);
      preparedStatement.setString(1, architect.getSurname());
      preparedStatement.setString(2, architect.getName());
      preparedStatement.setString(3, architect.getTelephoneNumber());
      preparedStatement.setString(4, architect.getEmailAddress());
      preparedStatement.setString(5, architect.getPhysicalAddress());
      preparedStatement.setInt(6, architectID);

      rowsAffected = preparedStatement.executeUpdate();

      System.out.println(rowsAffected + " Entry has been updated. ");

    } catch (SQLException e) {
      System.out.println("Failed insert new Entry in the database.");
    }

  }

  /**
   * The method below deletes the Architects entry in the database that has the
   * same id as the number contained under the variable integer id
   * 
   * @param id contains the id/primaryKey of the Architects entry that needs to be
   *           deleted.
   * @throws SQLException because it connects to the SQL Database.
   */
  public static void delete(int id) throws SQLException {
    PreparedStatement preparedStatement;
    int rowsAffected = 0;
    Connection connection = ConnectionDAO.connect();

    String sqlInput = "DELETE FROM Architects WHERE Architect_id = ?";

    preparedStatement = connection.prepareStatement(sqlInput);
    preparedStatement.setInt(1, id);
    rowsAffected = preparedStatement.executeUpdate();

    System.out.println(rowsAffected + " entry has been deleted. ");

  }

  /**
   * The method below prompts the user to enter the information needed to create a
   * new Architects entry and inserts it into the PoisePMS database.
   * 
   * @param input is used to get input from the user
   * @throws SQLException because the method connects to the SQL Database.
   */

  public static void inputAndInsert(Scanner input) throws SQLException {

    System.out.println("Enter the Name of the Architect.");
    String name = input.nextLine();

    System.out.println("Enter the Surname of the Architect.");
    String surname = input.nextLine();

    System.out.println("Enter the Telephone Number of the Architect.");
    String telephoneNumber = input.nextLine();

    String range = "[0-9]+";
    while (!telephoneNumber.startsWith("0") || !telephoneNumber.startsWith("+") && !telephoneNumber.matches(range)) {
      System.out.println("Type in a valid Telephone number that starts with either 0 or +");
      telephoneNumber = input.nextLine();
    }

    System.out.println("Enter the Email Address of the Architect.");
    String emailAddress = input.nextLine();

    while (!emailAddress.contains("@") && !emailAddress.contains(".")) {
      System.out.println("Type in a Valid email Address");
      emailAddress = input.nextLine();
    }

    System.out.println("Enter the Physical Address of the Architect.");
    String physicalAddress = input.nextLine();

    Architect architect = new Architect(surname, name, telephoneNumber, emailAddress, physicalAddress);
    ArchitectDAO architectDAO = new ArchitectDAO(architect);
    architectDAO.insert();

  }

  /**
   * This method retrieves an entry from the PoisePMS database from the Architects
   * table with the architectID/primaryKey entered in the get parameters and
   * returns it as a Architect Object.
   * 
   * @param architectID contains the architectID/primaryKey of the Architects
   *                    entry that the method will retrieve.
   * @return a Architect Object
   * @throws SQLException because the method connects to the SQL Database.
   */
  public static Architect get(int architectID) throws SQLException {

    Connection connection = ConnectionDAO.connect();

    String sqlInput = "SELECT * FROM Architects WHERE Architect_id = ?";

    PreparedStatement preparedStatement = connection.prepareStatement(sqlInput);
    preparedStatement.setInt(1, architectID);
    ResultSet results = preparedStatement.executeQuery();
    Architect architect = null;

    if (results.next()) {
      String surname = results.getString("Surname");
      String name = results.getString("Name");
      String telephoneNumber = results.getString("Telephone_Number");
      String emailAddress = results.getString("Email_Address");
      String physicalAddress = results.getString("Physical_Address");

      architect = new Architect(surname, name, telephoneNumber, emailAddress, physicalAddress);
    }
    return architect;
  }

  /**
   * This will print the ResultSets for the Queries from the Architects table from
   * the PoisePMS Database
   * 
   * @param results contains the results from the query made from the Architects
   *                table in the PoisePMS
   */
  public static void printQuery(ResultSet results) {
    try {
      while (results.next()) {
        System.out.println(results.getInt("Architect_id") + " , " + results.getString("Surname") + " , "
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

      ArchitectDAO.printQuery(results);
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
      ArchitectDAO.printQuery(results);

    } catch (SQLException e) {
      System.out.println("Failed to Execute Search in the database.");
    }
  }

  /**
   * This method will print all the entries in the Architects table
   * 
   * @throws SQLException because the method connects to the SQL Database.
   */
  public static void printAll() throws SQLException {
    Connection connection = ConnectionDAO.connect();
    PreparedStatement preparedStatement;
    ResultSet results;

    String sqlInput = "SELECT * FROM Architects";

    preparedStatement = connection.prepareStatement(sqlInput);
    results = preparedStatement.executeQuery();

    printQuery(results);

  }

  /**
   * This method will prompt the user to choose which column they would like to
   * filter by , then the user will be prompted to enter the phrase or number they
   * would like to search for then the Architects entries will be filtered by the
   * phrase by that column.
   * 
   * @param input will be used to get input from them keyboard
   * @throws SQLException because the method connects to the SQL database.
   */
  public static void likeSearch(Scanner input) throws SQLException {

    System.out.println("Choose a Column to Search by.\n1.Architect_id"
        + "\n2.Surname\n3.Name\n4.Telephone Number\n5.Email Address\n6.Physical Address");
    int userColumn = input.nextInt();
    input.nextLine();

    System.out.println("Enter the phrase or char You would like to filter by.");
    String userSearch = input.nextLine();

    /*
     * Below for each option that is chosen i have created a individual
     * SQLStatements for each Column the user chooses to search by and the statement
     * will be executed using the runSearch method from the ArchitectDAO class.
     */
    if (userColumn == 1) {
      String sqlInput = "SELECT * FROM Architects WHERE Architect_id LIKE ?";
      ArchitectDAO.runSearch(sqlInput, userSearch);

    } else if (userColumn == 2) {
      String sqlInput = "SELECT * FROM Architects WHERE Surname LIKE ?";
      ArchitectDAO.runSearch(sqlInput, userSearch);

    } else if (userColumn == 3) {
      String sqlInput = "SELECT * FROM Architects WHERE Name LIKE ?";
      ArchitectDAO.runSearch(sqlInput, userSearch);

    } else if (userColumn == 4) {
      String sqlInput = "SELECT * FROM Architects WHERE Telephone_Number LIKE ?";
      ArchitectDAO.runSearch(sqlInput, userSearch);

    } else if (userColumn == 5) {
      String sqlInput = "SELECT * FROM Architects WHERE Email_Address LIKE ?";
      ArchitectDAO.runSearch(sqlInput, userSearch);

    } else if (userColumn == 6) {
      String sqlInput = "SELECT * FROM Architects WHERE Physical_Address LIKE ?";
      ArchitectDAO.runSearch(sqlInput, userSearch);

    }
  }

  /**
   * The user will be prompted to enter the Architect id for the Architects entry
   * which contains a field they would like to update then the user will be
   * prompted to enter the number of the field they would like to update of that
   * Architects entry then the user's entry will be updated into the PoisePMS
   * database
   * 
   * @param input the scanner object that is being used to collect user input in
   *              this program
   * @throws SQLException because the method is connecting to the SQL Database.
   */

  public static void updateDatabase(Scanner input) throws SQLException {

    System.out.println("Enter the Architect id of the entry you want to Update.");
    int architectID = input.nextInt();
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
     * Architect object and storing it under the variable architect then using the
     * set method the field the user wants to update will be changed then the
     * architect variable will stored within the architectDAO Object then updated
     * using the update method from the ArchitectDAO class.
     */
    if (userChoice1 == 1) {
      Architect architect = ArchitectDAO.get(architectID);
      architect.setSurname(newValue);
      ArchitectDAO architectDAO = new ArchitectDAO(architect);
      architectDAO.update(architectID);

    } else if (userChoice1 == 2) {
      Architect architect = ArchitectDAO.get(architectID);
      architect.setName(newValue);
      ArchitectDAO architectDAO = new ArchitectDAO(architect);
      architectDAO.update(architectID);

    } else if (userChoice1 == 3) {
      Architect architect = ArchitectDAO.get(architectID);
      architect.setTelephoneNumber(newValue);

      // Below the telephoneNumber the user input will be validated to make sure it is
      // valid
      // if not then the user will be ask to Re-Enter until it is valid

      String range = "[0-9]+";
      while (!newValue.startsWith("0") || !newValue.startsWith("+") && !newValue.matches(range)) {
        System.out.println("Type in a valid Telephone number that starts with either 0 or +");
        newValue = input.nextLine();
      }
      ArchitectDAO architectDAO = new ArchitectDAO(architect);
      architectDAO.update(architectID);

    } else if (userChoice1 == 4) {
      Architect architect = ArchitectDAO.get(architectID);
      // Below the emailAddress the user input will validated to make sure it is valid
      // if not then the user will be ask to Re-Enter until it is valid
      while (newValue.contains("@") && newValue.contains(".")) {
        System.out.println("Type in a Valid email Address");
        newValue = input.nextLine();
      }
      architect.setEmailAddress(newValue);
      ArchitectDAO architectDAO = new ArchitectDAO(architect);
      architectDAO.update(architectID);

    } else if (userChoice1 == 5) {
      Architect architect = ArchitectDAO.get(architectID);
      architect.setPhysicalAddress(newValue);
      ArchitectDAO architectDAO = new ArchitectDAO(architect);
      architectDAO.update(architectID);

    } else {
      System.out.println("Invalid Option Entered!!");

    }
  }

}
