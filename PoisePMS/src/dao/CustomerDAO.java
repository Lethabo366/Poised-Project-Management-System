package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import models.Customer;
import utility.ConnectionDAO;

/**
 * @author Lethabo
 * @version 2024/10/01
 * 
 */
public class CustomerDAO {

  Customer customer;

  /**
   * Below this will create a CustomerDAO Object using the Customer object
   * 
   * @param customer contains the object with that will be used for the
   *                 CustomerDAO object
   */
  public CustomerDAO(Customer customer) {
    this.customer = customer;
  }

  /**
   * This method will insert the Customer Object within the CustomerDAO object
   * into the PoisePms Database Customers Table.
   * 
   * @throws SQLException because the method connects to the SQL Database.
   */
  public void insert() throws SQLException {
    PreparedStatement preparedStatement;
    int rowsAffected;

    Connection connection = ConnectionDAO.connect();

    String sqlInput = "INSERT INTO Customers (Surname,Name,Telephone_Number,Email_Address,Physical_Address) "
        + "VALUES(?,?,?,?,?)";
    try {
      preparedStatement = connection.prepareStatement(sqlInput);
      preparedStatement.setString(1, customer.getSurname());
      preparedStatement.setString(2, customer.getName());
      preparedStatement.setString(3, customer.getTelephoneNumber());
      preparedStatement.setString(4, customer.getEmailAddress());
      preparedStatement.setString(5, customer.getPhysicalAddress());

      rowsAffected = preparedStatement.executeUpdate();

      System.out.println(rowsAffected + " Entry has been updated. ");

    } catch (SQLException e) {
      System.out.println("Failed insert new Entry in the database.");
    }

  }

  /**
   * This method updates an entry from the PoisePMS database from the Customers
   * table that has the customerID/primaryKey entered in the parameters and
   * updates it with the Customer Object in the CustomerAO Object.
   * 
   * @param customerID contains the customerID/primaryKey of the Customers entry
   *                   that will be updated.
   * @throws SQLException because the method is connecting to the SQL Database.
   */
  public void update(int customerID) throws SQLException {
    PreparedStatement preparedStatement;
    int rowsAffected;

    Connection connection = ConnectionDAO.connect();

    String sqlInput = "Update Customers  SET Surname = ? ,Name = ? ,Telephone_Number = ?,Email_Address = ?,Physical_Address = ? "
        + "WHERE Customer_id = ?";
    try {
      preparedStatement = connection.prepareStatement(sqlInput);
      preparedStatement.setString(1, customer.getSurname());
      preparedStatement.setString(2, customer.getName());
      preparedStatement.setString(3, customer.getTelephoneNumber());
      preparedStatement.setString(4, customer.getEmailAddress());
      preparedStatement.setString(5, customer.getPhysicalAddress());
      preparedStatement.setInt(6, customerID);

      rowsAffected = preparedStatement.executeUpdate();

      System.out.println(rowsAffected + " Entry has been updated. ");

    } catch (SQLException e) {
      System.out.println("Failed insert new Entry in the database.");
    }

  }

  /**
   * The method below deletes the Customers entry in the database that has the
   * same id as the number contained under the variable integer id
   * 
   * @param id contains the id/primaryKey of the Customers entry that needs to be
   *           deleted.
   * @throws SQLException because it connects to the SQL Database.
   */
  public static void delete(int id) throws SQLException {
    PreparedStatement preparedStatement;
    int rowsAffected = 0;
    Connection connection = ConnectionDAO.connect();

    String sqlInput = "DELETE FROM Customers WHERE Customer_id = ?";

    preparedStatement = connection.prepareStatement(sqlInput);
    preparedStatement.setInt(1, id);
    rowsAffected = preparedStatement.executeUpdate();

    System.out.println(rowsAffected + " entry has been deleted. ");

  }

  /**
   * The method below prompts the user to enter the information needed to create a
   * new Customers entry and inserts it into the PoisePMS database.
   * 
   * @param input is used to get input from the user
   * @throws SQLException because the method connects to the SQL Database.
   */
  public static void inputAndInsert(Scanner input) throws SQLException {

    System.out.println("Enter the Name of the Customer.");
    String name = input.nextLine();

    System.out.println("Enter the Surname of the Customer.");
    String surname = input.nextLine();

    System.out.println("Enter the Telephone Number of the Customer.");
    String telephoneNumber = input.nextLine();

    String range = "[0-9]+";
    while (!telephoneNumber.startsWith("0") || !telephoneNumber.startsWith("+") && !telephoneNumber.matches(range)) {
      System.out.println("Type in a valid Telephone number that starts with either 0 or +");
      telephoneNumber = input.nextLine();
    }

    System.out.println("Enter the Email Address of the Customer.");
    String emailAddress = input.nextLine();

    while (!emailAddress.contains("@") && !emailAddress.contains(".")) {
      System.out.println("Type in a Valid email Address");
      emailAddress = input.nextLine();
    }

    System.out.println("Enter the Physical Address of the Customer.");
    String physicalAddress = input.nextLine();

    Customer customer = new Customer(surname, name, telephoneNumber, emailAddress, physicalAddress);
    CustomerDAO customerDAO = new CustomerDAO(customer);
    customerDAO.insert();

  }

  /**
   * This method retrieves an entry from the PoisePMS database from the Customers
   * table with the customerID/primaryKey entered in the get parameters and
   * returns it as a Customer Object.
   * 
   * @param customerID contains the customerID/primaryKey of the Customers entry
   *                   that the method will retrieve.
   * @return a Customers Object
   * @throws SQLException because the method connects to a sql Database
   */
  public static Customer get(int customerID) throws SQLException {

    Connection connection = ConnectionDAO.connect();

    String sqlInput = "SELECT * FROM Customers WHERE Customer_id = ?";

    PreparedStatement preparedStatement = connection.prepareStatement(sqlInput);
    preparedStatement.setInt(1, customerID);
    ResultSet results = preparedStatement.executeQuery();
    Customer customer = null;

    if (results.next()) {
      String surname = results.getString("Surname");
      String name = results.getString("Name");
      String telephoneNumber = results.getString("Telephone_Number");
      String emailAddress = results.getString("Email_Address");
      String physicalAddress = results.getString("Physical_Address");

      customer = new Customer(surname, name, telephoneNumber, emailAddress, physicalAddress);
    }
    return customer;
  }

  /**
   * This will print the ResultSets for the Queries from the Customers table from
   * the PoisePMS Database
   * 
   * @param results contains the results from the query made from the Customers
   *                table in the PoisePMS
   */
  public static void printQuery(ResultSet results) {
    try {
      while (results.next()) {
        System.out.println(results.getInt("Customer_id") + " , " + results.getString("Surname") + " , "
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

      CustomerDAO.printQuery(results);
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
      CustomerDAO.printQuery(results);

    } catch (SQLException e) {
      System.out.println("Failed to Execute Search in the database.");
    }
  }

  /**
   * This method will print all the entries in the Customers table
   * 
   * @throws SQLException because the method connects to the SQL Database.
   */
  public static void printAll() throws SQLException {
    Connection connection = ConnectionDAO.connect();
    PreparedStatement preparedStatement;
    ResultSet results;

    String sqlInput = "SELECT * FROM Customers";

    preparedStatement = connection.prepareStatement(sqlInput);
    results = preparedStatement.executeQuery();

    printQuery(results);

  }

  /**
   * This method will prompt the user to choose which column they would like to
   * filter by , then the user will be prompted to enter the phrase or number they
   * would like to search for then the Customers entries will be filtered by the
   * phrase by that column.
   * 
   * @param input will be used to get input from them keyboard
   * @throws SQLException because the method connects to the SQL database.
   */
  public static void likeSearch(Scanner input) throws SQLException {
    /*
     * if the user decides to View the Customer Database the user will be given two
     * options search the Database using a phrase,letter or NUmber the other option
     * is to show all entries.
     */

    /*
     * if the user chooses to search they will asked which column they would like to
     * search by. and enter the phrase or number they are looking for.
     */
    System.out.println("Choose a Column to Search by.\n1.Customer_id"
        + "\n2.Surname\n3.Name\n4.Telephone Number\n5.Email Address\n6.Physical Address");
    int userColumn = input.nextInt();
    input.nextLine();

    System.out.println("Enter the phrase or char You would like to filter by.");
    String userSearch = input.nextLine();

    /*
     * Below i am using the method runSearch from the CustomerDAO to search for the
     * value the user entered that is stored under the variable userSearch and print
     * the results.
     */
    if (userColumn == 1) {
      String sqlInput = "SELECT * FROM Customers WHERE Customer_id LIKE ?";
      CustomerDAO.runSearch(sqlInput, userSearch);

    } else if (userColumn == 2) {
      String sqlInput = "SELECT * FROM Customers WHERE Surname LIKE ?";
      CustomerDAO.runSearch(sqlInput, userSearch);

    } else if (userColumn == 3) {
      String sqlInput = "SELECT * FROM Customers WHERE Name LIKE ?";
      CustomerDAO.runSearch(sqlInput, userSearch);

    } else if (userColumn == 4) {
      String sqlInput = "SELECT * FROM Customers WHERE Telephone_Number LIKE ?";
      CustomerDAO.runSearch(sqlInput, userSearch);

    } else if (userColumn == 5) {
      String sqlInput = "SELECT * FROM Customers WHERE Email_Address LIKE ?";
      CustomerDAO.runSearch(sqlInput, userSearch);

    } else if (userColumn == 6) {
      String sqlInput = "SELECT * FROM Customers WHERE Physical_Address LIKE ?";
      CustomerDAO.runSearch(sqlInput, userSearch);
    }
  }

  /**
   * The user will be prompted to enter the Customer id for the Customers entry
   * which contains a field they would like to update then the user will be
   * prompted to enter the number of the field they would like to update of that
   * Customers entry then the user's entry will be updated into the PoisePMS
   * database
   * 
   * @param input the scanner object that is being used to collect user input in
   *              this program
   * @throws SQLException because the method is connecting to the SQL Database.
   */

  public static void updateDatabase(Scanner input) throws SQLException {
    System.out.println("Enter the Customer id of the entry you want to Update.");
    int customerID = input.nextInt();

    Customer customer;
    input.nextLine();

    System.out.println(
        "Which field would you like to update?\n1.Surname\n2.Name\n3.Telephone Number\n4.Email Address\n5.Physical Address"
            + "\nEnter the number of the option you choose");
    int userChoice1 = input.nextInt();
    input.nextLine();

    System.out.println("Enter the new Value update to.");
    String newValue = input.nextLine();
    /*
     * Below i am importing the Customer object information from the Customer Table
     * in the database and storing it under the Customer object name customer then i
     * am using the set method to set the field the user chose to change/update to
     * the value stored under newValue. Using the update method from the CustomerDao
     * class to update the entire entry to the values under the customer class
     * object
     */
    if (userChoice1 == 1) {
      customer = CustomerDAO.get(customerID);
      customer.setSurname(newValue);
      CustomerDAO customerDAO = new CustomerDAO(customer);
      customerDAO.update(customerID);

    } else if (userChoice1 == 2) {
      customer = CustomerDAO.get(customerID);
      customer.setName(newValue);
      CustomerDAO customerDAO = new CustomerDAO(customer);
      customerDAO.update(customerID);

    } else if (userChoice1 == 3) {
      customer = CustomerDAO.get(customerID);
      String range = "[0-9]+";
      while (!newValue.startsWith("0") || !newValue.startsWith("+") && !newValue.matches(range)) {
        System.out.println("Type in a valid Telephone number that starts with either 0 or +");
        newValue = input.nextLine();
      }
      customer.setTelephoneNumber(newValue);
      CustomerDAO customerDAO = new CustomerDAO(customer);
      customerDAO.update(customerID);

    } else if (userChoice1 == 4) {
      customer = CustomerDAO.get(customerID);
      while (!newValue.contains("@") && !newValue.contains(".")) {
        System.out.println("Type in a Valid email Address");
        newValue = input.nextLine();
      }

      customer.setEmailAddress(newValue);
      CustomerDAO customerDAO = new CustomerDAO(customer);
      customerDAO.update(customerID);

    } else if (userChoice1 == 5) {
      customer = CustomerDAO.get(customerID);
      customer.setPhysicalAddress(newValue);
      CustomerDAO customerDAO = new CustomerDAO(customer);
      customerDAO.update(customerID);

    } else {
      System.out.println("Invalid Option Entered!!");
    }
  }

}
