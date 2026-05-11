package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import models.Customer;
import models.Project;
import utility.ConnectionDAO;
import utility.Validate;

/**
 * @author Lethabo
 * @version 2024/10/01
 * 
 */
public class ProjectDAO {

  Project project;
  
  /**
   * Below creates a ProjectDAO Object using a Project object as a parameter.
   *  
   * @param project  the object contains the attributes or information that will be used 
   *                  for the ProjectDAO object.
   */
  public ProjectDAO(Project project) {
    this.project = project;
  }

  /**
   * This method will insert the project Object within the ProjectDAO object into the
   * PoisePms Database Projects Table.
   * 
   * @throws SQLException because the method connects to the SQL Database.
   */
  public void insert() throws SQLException {

    Connection connection = ConnectionDAO.connect();

    try {
      PreparedStatement preparedStatement;
      int rowsAffected;
      preparedStatement = connection.prepareStatement("INSERT INTO Projects (Project_Name,Building_Type,"
          + "Physical_Address,ERF_Number,Total_Amount_Charged,Total_Amount_Paid,Deadline_Date,"
          + "Structural_Engineer_id,Project_Manager_id,"
          + "Architect_id,Customer_id,Contractor_id,finalized) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");

      preparedStatement.setString(1, project.getProjectName());
      preparedStatement.setString(2, project.getBuildingType());
      preparedStatement.setString(3, project.getPhysicalAddress());
      preparedStatement.setInt(4, project.getErfNumber());
      preparedStatement.setDouble(5, project.getTotalAmountCharged());
      preparedStatement.setDouble(6, project.getTotalAmountPaid());
      preparedStatement.setString(7, project.getDeadlineDate());
      preparedStatement.setInt(8, project.getStructuralEngineerID());
      preparedStatement.setInt(9, project.getProjectManagerID());
      preparedStatement.setInt(10, project.getArchitectID());
      preparedStatement.setInt(11, project.getCustomerID());
      preparedStatement.setInt(12,project.getContractorID());
      preparedStatement.setBoolean(13, project.getFinalized());

      rowsAffected = preparedStatement.executeUpdate();

      System.out.println(rowsAffected + " project has been added.");

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * This method retrieves an entry from the PoisePMS database from the projects table
   * with the projectNumber/primaryKey entered in the get parameters and returns it as 
   * a project Object.
   * 
   * @param projectNumber contains the projectNumber/primaryKey of the project that the method 
   * will retrieve.
   * @return a project Object the will contain the information retrieved from the database
   * @throws SQLException the method connects to MySQL Database
   */
  public static Project get(int projectNumber) throws SQLException {

    Connection connection = ConnectionDAO.connect();

    String sqlInput = "SELECT * FROM Projects WHERE Project_Number = ?";

    PreparedStatement preparedStatement = connection.prepareStatement(sqlInput);
    preparedStatement.setInt(1, projectNumber);
    ResultSet results = preparedStatement.executeQuery();
    Project project = null;

    if (results.next()) {

      String projectName = results.getString("Project_Name");
      String buildingType = results.getString("Building_Type");
      String physicalAddress = results.getString("Physical_Address");
      int erfNumber = results.getInt("ERF_Number");
      double totalAmountCharged = results.getDouble("Total_Amount_Charged");
      double totalAmountPaid = results.getDouble("Total_Amount_Paid");
      String deadlineDate = results.getString("Deadline_Date");
      String completionDate = results.getString("Completion_Date");
      int structuralEngineerID = results.getInt("Structural_Engineer_ID");
      int projectManagerID = results.getInt("Project_Manager_ID");
      int architectID = results.getInt("Architect_ID");
      int customerID = results.getInt("Customer_ID");
      int contractorID = results.getInt("Contractor_ID");
      boolean finalized = results.getBoolean("Finalized");

      project = new Project(projectName, buildingType, physicalAddress, erfNumber, totalAmountCharged, totalAmountPaid,
          deadlineDate, completionDate, structuralEngineerID, projectManagerID,
           architectID, customerID, contractorID,finalized);
    }
    return project;
  }

  /**
   * the method below update the project entry with the projectNumber entered in
   * the parameters bracket(projectNumber) with the project object entered into the projectDAO
   * constructor
   * 
   * @param projectNumber contains the projectNumber/primaryKey of the project that will be updated.
   * @throws SQLException  because the method is connecting to the MySQL database.
   */
  public void update(int projectNumber) throws SQLException {

  try {  Connection connection = ConnectionDAO.connect();
    int rowsAffected;

    String sqlInput = "UPDATE Projects SET Project_Name = ?,Building_Type = ?,"
        + "Physical_Address = ?,ERF_Number = ?,Total_Amount_Charged = ?,Total_Amount_Paid = ?,Deadline_Date = ?,"
        + "Completion_Date = ?, Structural_Engineer_id = ?,Project_Manager_id = ?,"
        + "Architect_id = ?,Customer_id = ?, Contractor_id = ? ,Finalized = ? WHERE Project_Number = ?";

    PreparedStatement preparedStatement = connection.prepareStatement(sqlInput);
    preparedStatement.setString(1, project.getProjectName());
    preparedStatement.setString(2, project.getBuildingType());
    preparedStatement.setString(3, project.getPhysicalAddress());
    preparedStatement.setInt(4, project.getErfNumber());
    preparedStatement.setDouble(5, project.getTotalAmountCharged());
    preparedStatement.setDouble(6, project.getTotalAmountPaid());
    preparedStatement.setString(7, project.getDeadlineDate());
    preparedStatement.setString(8, project.getCompletionDate());
    preparedStatement.setInt(9,project.getStructuralEngineerID());
    preparedStatement.setInt(10, project.getProjectManagerID());
    preparedStatement.setInt(11, project.getArchitectID());
    preparedStatement.setInt(12, project.getCustomerID());
    preparedStatement.setInt(13, project.getContractorID());
    preparedStatement.setBoolean(14, project.getFinalized());
    preparedStatement.setInt(15, projectNumber);

    rowsAffected = preparedStatement.executeUpdate();

    System.out.println(rowsAffected + " have been projects have been updated.");
  
  }catch (Exception e) {
    System.out.println("Failed to update project.(Tip: ID'S entered must exist in the associated Table)");
  }
  }

  /**
   * The method below deletes the project entry in the database with that has the
   * same projectNumber as the contained under the variable int projectNumber
   * 
   * @param projectNumber contains the projectNumber/primaryKey of the project that needs to be deleted.
   * @throws SQLException the method connects to the MySQL Database.
   */
  public static void delete(int projectNumber) throws SQLException {
    Connection connection = ConnectionDAO.connect();
    int rowsAffected = 0;

    String sqlInput = "DELETE FROM Projects WHERE Project_Number = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(sqlInput);
    preparedStatement.setInt(1, projectNumber);

    rowsAffected = preparedStatement.executeUpdate();

    System.out.println(rowsAffected + " have been updated.");

  }

  /**
   * This is a method that will take a string that contains a SQL Statement that intends to search the 
   * SQL database with a like Function and using a phrase or number that is entered(userSearch).
   * 
   * @param sqlInput contains the string that will be run as a SQL Statement and contains a like function.
   * @param userSearch contains the string/phrase the user would like to use search the database with.
   * @throws SQLException the method connects to the MySQL Database
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
       * the results found when the executeQuery was executed will be printed
       */
      ProjectDAO.printQuery(results);

    } catch (SQLException e) {
      System.out.println("Failed to Execute Search in the database.");
    }
  }

/**
 * This will print the ResultSets for the Queries from the Projects table from
 * the PoisePMS Database
 * 
 * @param results contains the results from the query made from the Projects
 *                table in the PoisePMS
 */

  public static void printQuery(ResultSet results) {
    try {
      while (results.next()) {
        System.out.println(results.getInt("Project_Number") + " , " + results.getString("Project_Name") + " , "
            + results.getString("Building_Type") + " , " + results.getString("Physical_Address") + " , "
            + results.getInt("ERF_Number") + " , " + results.getDouble("Total_Amount_Charged") + " , "
            + results.getDouble("Total_Amount_Paid") + " , " + results.getString("Deadline_Date") + " , "
            + results.getString("Completion_Date") + " , " + results.getInt("Structural_Engineer_id") + " , " 
            + results.getInt("Project_Manager_ID") + " , " + results.getInt("Architect_ID") + " , " 
            + results.getInt("Customer_ID") + " ," + results.getInt("Contractor_id") + " ," 
            + results.getBoolean("Finalized"));
      }
    } catch (SQLException e) {
      System.out.println("Failed to print Project information.");
    }
  }

  /**
   * This method will run a string as an SQL statement then print the results of the 
   * Query.
   * 
   * @param sqlInput  is the string that will be run like a SQL Statement.
   * @throws SQLException because it connects to the SQL Database.
   */
   
  public static void runQuery(String sqlInput) throws SQLException {
    Connection connection = ConnectionDAO.connect();

    try {
      PreparedStatement preparedStatement;
      ResultSet results;
      preparedStatement = connection.prepareStatement(sqlInput);
      results = preparedStatement.executeQuery();

      ProjectDAO.printQuery(results);
    } catch (SQLException e) {
      System.out.println("Failed to retrieve Project Information.");
    }
  }
  
  /**
   * This method will print all the projects in the projects table
   * 
   * @throws SQLException because the method connects to the SQL Database.
   */

  public static void printAll() throws SQLException {
    Connection connection = ConnectionDAO.connect();
    PreparedStatement preparedStatement;
    ResultSet results;

    String sqlInput = "SELECT * FROM Projects";

    preparedStatement = connection.prepareStatement(sqlInput);
    results = preparedStatement.executeQuery();

    printQuery(results);

  }
  
  /**
   * This method will prompt the user to choose which column they would like to filter by ,
   * then the user will be prompted to enter the phrase or number they would like to search for 
   * then the Projects will be filtered by the phrase by that column.
   * 
   * @param input will be used to get input from them keyboard.
   * @return int will be returned after the method has been executed.
   * @throws SQLException the method connects to the MySQL Database.
   */

  public static int likeSearch(Scanner input) throws SQLException {

    int backToMenu = 1;
    printAll();
    System.out.println("\n1.Search Projects Columns by Phrase/Letter/Number\n2.Incomplete Projects\n"
        + "3.Overdue Projects\n4.Finalised Projects\n0.Back to Main Menu");

    int userOption = input.nextInt();
    input.nextLine();
    if (userOption == 1) {

      System.out.println("Which column do you want search by?\n1.Project Number\n2.Project Name\n3.Building Type\n"
          + "4.Physical Address\n5.ERF Number\n6.Total Amount Charged\n7.Total Amount Paid\n8.Deadline Date\n"
          + "9.Completion Date\n10.Structural Engineer Name\n11.Structural Engineer id \n"
          + "12.Project Manager Name\n13.Project Manager id\n14.Architect\n15.Architect id\n"
          + "16.Customer Name\n17.Customer id" + "\n\nEnter the Number of the option you would like to choose");
      int userChoice = input.nextInt();
      input.nextLine();

      System.out.println("Enter a letter,Number or Phrase. ");
      String userSearch = input.nextLine();

      /*
       * Below the the letter,phrase and number will search in the database using the
       * runSearch method which uses the Like statement to search for the information
       * that has the value the user entered under the variable userSearch and prints
       * the results variable
       */
      if (userChoice == 1) {
        String sqlInput = "SELECT * FROM Projects WHERE Project_Number LIKE ?";
        ProjectDAO.runSearch(sqlInput, userSearch);

      } else if (userChoice == 2) {
        String sqlInput = "SELECT * FROM Projects WHERE Project_Name LIKE ?";
        ProjectDAO.runSearch(sqlInput, userSearch);

      } else if (userChoice == 3) {
        String sqlInput = "SELECT * FROM Projects WHERE Building_Type LIKE ?";
        ProjectDAO.runSearch(sqlInput, userSearch);

      } else if (userChoice == 4) {
        String sqlInput = "SELECT * FROM Projects WHERE Physical_Address LIKE ?";
        ProjectDAO.runSearch(sqlInput, userSearch);

      } else if (userChoice == 5) {
        String sqlInput = "SELECT * FROM Projects WHERE ERF_Number LIKE ?";
        ProjectDAO.runSearch(sqlInput, userSearch);

      } else if (userChoice == 6) {
        String sqlInput = "SELECT * FROM Projects WHERE Total_Amount_Charged LIKE ?";
        ProjectDAO.runSearch(sqlInput, userSearch);

      } else if (userChoice == 7) {
        String sqlInput = "SELECT * FROM Projects WHERE Total_Amount_Paid LIKE ?";
        ProjectDAO.runSearch(sqlInput, userSearch);

      } else if (userChoice == 8) {
        String sqlInput = "SELECT * FROM Projects WHERE Deadline_Date LIKE ?";
        ProjectDAO.runSearch(sqlInput, userSearch);

      } else if (userChoice == 9) {
        String sqlInput = "SELECT * FROM Projects WHERE Completion_Date LIKE ?";
        ProjectDAO.runSearch(sqlInput, userSearch);

      } else if (userChoice == 10) {
        String sqlInput = "SELECT * FROM Projects WHERE Structural_Engineer LIKE ?";
        ProjectDAO.runSearch(sqlInput, userSearch);

      } else if (userChoice == 11) {
        String sqlInput = "SELECT * FROM Projects WHERE Structural_Engineer_id LIKE ?";
        ProjectDAO.runSearch(sqlInput, userSearch);

      } else if (userChoice == 12) {
        String sqlInput = "SELECT * FROM Projects WHERE Project_Manager LIKE ?";
        ProjectDAO.runSearch(sqlInput, userSearch);

      } else if (userChoice == 13) {
        String sqlInput = "SELECT * FROM Projects WHERE Project_Manager_id LIKE ?";
        ProjectDAO.runSearch(sqlInput, userSearch);

      } else if (userChoice == 14) {
        String sqlInput = "SELECT * FROM Projects WHERE Architect LIKE ?";
        ProjectDAO.runSearch(sqlInput, userSearch);

      } else if (userChoice == 15) {
        String sqlInput = "SELECT * FROM Projects WHERE Architect_id LIKE ?";
        ProjectDAO.runSearch(sqlInput, userSearch);

      } else if (userChoice == 16) {
        String sqlInput = "SELECT * FROM Projects WHERE Customer LIKE ?";
        ProjectDAO.runSearch(sqlInput, userSearch);

      } else if (userChoice == 17) {
        String sqlInput = "SELECT * FROM Projects WHERE Customer_id LIKE ?";
        ProjectDAO.runSearch(sqlInput, userSearch);

      } else if (userChoice == 18) {
        String sqlInput = "SELECT * FROM Projects WHERE Contractor LIKE ?";
        ProjectDAO.runSearch(sqlInput, userSearch);

      } else if (userChoice == 19) {
        String sqlInput = "SELECT * FROM Projects WHERE Contractor_id LIKE ?";
        ProjectDAO.runSearch(sqlInput, userSearch);

      } else {
        System.out.println("Invalid option entered");
      }
      /*
       * Below the Projects which have false in their Finalized column will be returned 
       * when the incompleted projects option is chosen
       */
    } else if (userOption == 2) {
      String sqlInput = "SELECT * FROM Projects WHERE Finalized = false";
      ProjectDAO.runQuery(sqlInput);

      /*
       * Below the Projects with a deadline greater than the current date and which
       * the completion_Date is equal to Null will be printed as overdue Projects.
       */
    } else if (userOption == 3) {
      String sqlInput = "SELECT * FROM Projects WHERE Deadline_Date < CURDATE() AND Completion_Date is NULL";
      ProjectDAO.runQuery(sqlInput);

      /* Below Projects with a true in their Finalized column will returned when finalized projects is 
      * chosen.*/
    } else if (userOption == 4) {
      String sqlInput = "SELECT * FROM Projects WHERE Finalized = true";
      ProjectDAO.runQuery(sqlInput);

      // ALL Projects are printed for option 5.
    } else if (userOption == 0) {
      backToMenu = 0;

    } else {
      System.out.println("Invalid Option Entered.");
    }
    return backToMenu;
  }

  /**
   * Below the user will prompted to enter the information of each field that is
   * need to create a project except for project completion date because if the
   * completion date indicates the project has been finalized in order to add a
   * completion date the users has to use the updateProjectoption
   * 
   * @param input the Scanner input that is used to get user input
   * @throws SQLException indicates we connecting to the SQL database
   */
  public static void addProjectOption(Scanner input) throws SQLException {
    System.out.println("Enter the Project Name");
    String projectName = input.nextLine();

    System.out.println("Enter the Building Type.");
    String buildingType = input.nextLine();

    System.out.println("Enter the Physical Address.");
    String physicalAddress = input.nextLine();

    int erfNum = Validate.erfNumber(input);
 
    double amountCharged = Validate.TotalCharged(input);
//    input.nextLine();

    double amountPaid = Validate.TotalPaid(input);
//    input.nextLine();

    String deadlineDate = Validate.enterDeadline(input);

    StructEngineerDAO.printAll();
    System.out.println("Enter the Structural Engineer ID.");
    int structEngineerID = input.nextInt();
    input.nextLine();
    
    ProjManagerDAO.printAll();
    System.out.println("Enter the Project manager's ID.");
    int projManagerID = input.nextInt();
    input.nextLine();
    
    ArchitectDAO.printAll();
    System.out.println("Enter the Architect's ID.");
    int architectID = input.nextInt();
    input.nextLine();

    CustomerDAO.printAll();
    System.out.println("Enter the Customer's ID.");
    int customerID = input.nextInt();
    input.nextLine();

    ContractorDAO.printAll();
    System.out.println("Enter the Contractor's ID.");
    int contractorID = input.nextInt();
    input.nextLine();

    System.out.println();

    if (projectName.isEmpty() || projectName.equals(" ")) {
      
      Customer customer = CustomerDAO.get(customerID);

      try{projectName =  customer.getSurname() + " " + buildingType;
      }
      catch(NullPointerException e) {
        System.out.println("The customer_id you have added is invalid");
      }
      Project project = new Project(projectName, buildingType, physicalAddress, erfNum, amountCharged, amountPaid,
          deadlineDate, null, structEngineerID, projManagerID, 
          architectID, customerID, contractorID,false);

      ProjectDAO projectDAO = new ProjectDAO(project);

      projectDAO.insert();

    } else {

      Project project = new Project(projectName, buildingType, physicalAddress, erfNum, amountCharged, amountPaid,
          deadlineDate, null, structEngineerID, projManagerID, 
          architectID, customerID, contractorID,false);

      ProjectDAO projectDAO = new ProjectDAO(project);
      projectDAO.insert();

    }

  }

  /**
   * The user will be prompted to enter the project number for the project which
   * contains the field they would like to update then the user will be prompted to
   * enter the number of the field they would like to update of that project then the
   * user's entry will be updated into the PoisePMS database.
   * 
   * @param input the scanner object that is being used to collect user input in
   *              this program.
   * @throws SQLException indicates we connecting to the SQL database.
   */

  public static void updateDatabase(Scanner input) throws SQLException {
    printAll();
    System.out.println("\nEnter the project number of the project you would like to Update.");
    int projectNumber = input.nextInt();
    input.nextLine();

    System.out.println(
        "Pick the Column you would like to update \n1.Project Name\n2.Building Type\n3.Physical Address\n4.ERF Number\n5.Total Amount Charged\n"
            + "6.Total Amount Paid\n7.Deadline Date\n8.Completion Date\n9.Structural Engineer id\n"
            + "10.Project Manager id\n11.Architect id\n13.Customer id\n14.Contractor Id" 
            + "\n\nEnter the Number of the option you would like to choose");

    int userOption = input.nextInt();
    input.nextLine();
    ProjectDAO projectDAO;

    if (userOption == 1) {
      System.out.println("Enter the new Project Name value.");
      String newProjectName = input.nextLine();

      Project project = ProjectDAO.get(projectNumber);
      project.setProjectName(newProjectName);

    } else if (userOption == 2) {
      System.out.println("Enter the new Building Type Value.");
      String newBuildingType = input.nextLine();

      Project project = ProjectDAO.get(projectNumber);
      project.setBuildingType(newBuildingType);
      projectDAO = new ProjectDAO(project);
      projectDAO.update(projectNumber);

    } else if (userOption == 3) {
      System.out.println("Enter the new Physical Address Value.");
      String newPhysicalAddress = input.nextLine();

      Project project = ProjectDAO.get(projectNumber);
      project.setPhysicalAddress(newPhysicalAddress);
      projectDAO = new ProjectDAO(project);
      projectDAO.update(projectNumber);

    } else if (userOption == 4) {
      
      int newERFNum = Validate.erfNumber(input);

      Project project = ProjectDAO.get(projectNumber);
      project.setErfNumber(newERFNum);
      projectDAO = new ProjectDAO(project);
      projectDAO.update(projectNumber);

    } else if (userOption == 5) {
      double newTotalAmountCharged = Validate.TotalCharged(input);
      Project project = ProjectDAO.get(projectNumber);
      project.setTotalAmountCharged(newTotalAmountCharged);
      projectDAO = new ProjectDAO(project);
      projectDAO.update(projectNumber);

    } else if (userOption == 6) {
      double newTotalAmountPaid = Validate.TotalPaid(input);
      Project project = ProjectDAO.get(projectNumber);
      project.setTotalAmountCharged(newTotalAmountPaid);
      projectDAO = new ProjectDAO(project);
      projectDAO.update(projectNumber);

    } else if (userOption == 7) {
      String newDeadlineDate = Validate.enterDeadline(input);

      Project project = ProjectDAO.get(projectNumber);
      project.setDeadlineDate(newDeadlineDate);
      projectDAO = new ProjectDAO(project);
      projectDAO.update(projectNumber);

    } else if (userOption == 8) {
      String newCompletionDate = Validate.enterCompletion(input);

      Project project = ProjectDAO.get(projectNumber);
      project.setCompletionDate(newCompletionDate);
      project.setFinalized(true);
      projectDAO = new ProjectDAO(project);
      projectDAO.update(projectNumber);

    }  else if (userOption == 9) {
      System.out.println("Enter the new Structural Engineer ID Value.");
      int newStructEngineerID = input.nextInt();

      Project project = ProjectDAO.get(projectNumber);
      project.setStructuralEngineerID(newStructEngineerID);
      projectDAO = new ProjectDAO(project);
      projectDAO.update(projectNumber);

    }  else if (userOption == 10) {
      System.out.println("Enter the new Project Manager ID Value.");
      int newProjManagerID = input.nextInt();

      Project project = ProjectDAO.get(projectNumber);
      project.setProjectManagerID(newProjManagerID);
      projectDAO = new ProjectDAO(project);
      projectDAO.update(projectNumber);

    }  else if (userOption == 11) {
      System.out.println("Enter the new Architect ID Value.");
      int newArchitectID = input.nextInt();

      Project project = ProjectDAO.get(projectNumber);
      project.setArchitectID(newArchitectID);
      projectDAO = new ProjectDAO(project);
      projectDAO.update(projectNumber);

    }  else if (userOption == 12) {
      System.out.println("Enter the new Customer ID Value.");
      int newCustomerID = input.nextInt();

      Project project = ProjectDAO.get(projectNumber);
      project.setCustomerID(newCustomerID);
      projectDAO = new ProjectDAO(project);
      projectDAO.update(projectNumber);

    }  else if (userOption == 13) {
      System.out.println("Enter the new Contractor ID Value.");
      int newContractorID = input.nextInt();

      Project project = ProjectDAO.get(projectNumber);
      project.setContractorID(newContractorID);
      projectDAO = new ProjectDAO(project);
      projectDAO.update(projectNumber);

    } else {
      System.out.println("Invalid option Entered!!");
    }
  }

  /**
   * The delete project method will will ask the user for information needed to
   * delete a project field or an entire project field.
   *
   * @param input      is the scanner object being used to collect user input
   * @throws SQLException indicates we are connecting to the SQL database
   */
  public static void deleteProjectOption(Scanner input) throws SQLException {
    printAll();
    System.out.println("Enter the Project Number for the Project or field you want deleted.");
    int projectNumber = input.nextInt();

    System.out.println(
        "What would you like to delete\n1.project_Name\n2.Building Type\n3.Physical Address\n4.ERF Number\n5.Total Amount Charged"
            + "6.Total Amount Paid\n7.Deadline Date\n8.Completion Date\n9.Structural Engineer id "
            + "\n10.Project Manager id\n11.Architect id\n12.Customer id\n13.Contractor id\n14.Entire Project"
            + "\n\nEnter the Number of the option you would like to choose");
    int userOption = input.nextInt();
    input.nextLine();

    /*
     * if the user decides to delete the project name instead of deleting the field
     * the the project Name will consist of the Customer full name and the building
     * type
     **/
    if (userOption == 1) {

      Project project = ProjectDAO.get(projectNumber);
      Customer customer = CustomerDAO.get(project.getCustomerID());
      
      String newProjectName = customer.getSurname() + " " + project.getBuildingType();
      
      project.setProjectName(newProjectName);
      ProjectDAO projectDAO = new ProjectDAO(project);
      projectDAO.update(projectNumber);

    } else if (userOption == 2) {
      Project project = ProjectDAO.get(projectNumber);
      project.setBuildingType(null);
      ProjectDAO projectDAO = new ProjectDAO(project);
      projectDAO.update(projectNumber);

    } else if (userOption == 3) {
      Project project = ProjectDAO.get(projectNumber);
      project.setPhysicalAddress(null);
      ProjectDAO projectDAO = new ProjectDAO(project);
      projectDAO.update(projectNumber);

    } else if (userOption == 4) {
      String sqlInput = "UPDATE Projects SET ERF_Number = NULL WHERE Project_Number = ?";
      deleteColumnEntry(sqlInput, projectNumber);

    } else if (userOption == 5) {
      String sqlInput = "UPDATE Projects SET Total_Amount_Charged = NULL WHERE Project_Number = ?";
      deleteColumnEntry(sqlInput, projectNumber);

    } else if (userOption == 6) {
      String sqlInput = "UPDATE Projects SET Total_Amount_Paid = NULL WHERE Project_Number = ?";
      deleteColumnEntry(sqlInput, projectNumber);

    } else if (userOption == 7) {
      Project project = ProjectDAO.get(projectNumber);
      project.setDeadlineDate(null);
      ProjectDAO projectDAO = new ProjectDAO(project);
      projectDAO.update(projectNumber);

    } else if (userOption == 8) {
      Project project = ProjectDAO.get(projectNumber);
      project.setCompletionDate(null);
      project.setFinalized(false);
      ProjectDAO projectDAO = new ProjectDAO(project);
      projectDAO.update(projectNumber);

    } else if (userOption == 9) {
      String sqlInput = "Update Projects SET structuralEngineer_id = null WHERE = ?";
      deleteColumnEntry(sqlInput, projectNumber);

    } else if (userOption == 10) {
      String sqlInput = "Update Projects SET Project_Manager_id = null WHERE = ?";
      deleteColumnEntry(sqlInput, projectNumber);

    } else if (userOption == 11) {
      String sqlInput = "Update Projects SET Architect_id = null WHERE = ?";
      deleteColumnEntry(sqlInput, projectNumber);

    } else if (userOption == 12) {
      String sqlInput = "Update Projects SET Customer_id = null WHERE = ?";
      deleteColumnEntry(sqlInput, projectNumber);

    } else if (userOption == 13) {
      String sqlInput = "Update Projects SET Contractor_id = null WHERE = ?";
      deleteColumnEntry(sqlInput, projectNumber);

    } else if (userOption == 14) {
      ProjectDAO.delete(projectNumber);

    } else {

      System.out.println("Invalid Option Entered!!");
    }
  }

  /**
   * the deleteColumnEntry will change the sqlInput into a preparedStatement and
   * insert the projectNumber variable under the first ? or placeholder and run
   * the code and store the number of rows affected under the variable
   * rowsAffected and that will be printed with the phrase has been deleted
   * 
   * @param sqlInput   contains the string to be run as an SQL statement
   * 
   * @param primaryKey contains the primary key of the table that is mentioned in
   *                   the sqlInput
   * @throws SQLException the method connects to the MySQL Database.
   */
  public static void deleteColumnEntry(String sqlInput, int primaryKey) throws SQLException {
    Connection connection = ConnectionDAO.connect();
    PreparedStatement preparedStatement;
    int rowsAffected;

    try {

      preparedStatement = connection.prepareStatement(sqlInput);
      preparedStatement.setInt(1, primaryKey);

      rowsAffected = preparedStatement.executeUpdate();

      System.out.println(rowsAffected + " for project Number  has been deleted");

    } catch (SQLException e) {
      System.out.println("Failed delete item in the database.");
    }
  }
}
