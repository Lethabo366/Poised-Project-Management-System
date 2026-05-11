
import java.sql.SQLException;
import java.util.Scanner;

import dao.ArchitectDAO;
import dao.CustomerDAO;
import dao.ProjManagerDAO;
import dao.ProjectDAO;
import dao.StructEngineerDAO;
import dao.ContractorDAO;

/**
 * @author Lethabo
 * @version 2024/10/01
 * 
 */
public class Main {

  /**
   * The code below will allow the user to have a database that will allow the
   * user to store,edit and delete information on Projects that are currently
   * being worked on and have been completed as well as information on
   * Customers,Structural engineers,architects and Project Managers
   * 
   * @param args this where the main function will run
   */
  public static void main(String[] args) {

    try {
      /**
       * The connection variable connects the java program to the PoisePMS Database
       */
      Scanner input = new Scanner(System.in);
      int userInput = 7;

      while (userInput != 0) {
        System.out
            .println("1.Add a Project\n2.Update a Project\n3.Delete a Project or Project field\n4.View Projects"
                + "\n5.Customer Database\n6.Employee Database\n0.Exit Program");
        userInput = input.nextInt();
        input.nextLine();
        /*
         * if the user decides choose option 1/to the addProjectOption method will run
         * which will ask the user to enter information for each of the fields then the
         * users project will be added to the PoisePMS database table
         */
        if (userInput == 1) {
          ProjectDAO.addProjectOption(input);
          /*
           * if the user chooses option 2 /Update a Project, the updateProjectOption will
           * run The user will be requested to enter the project Number of the project
           * they would like to update and pick a column(By entering the number of the
           * corresponding option then enter the new value and the newValue will be
           * updated into the PoisePMS database.
           */

        } else if (userInput == 2) {
          ProjectDAO.updateDatabase(input);
        } else if (userInput == 3) {
          /*
           * if the user chooses option 3 the deleteProjectOption the user will be asked
           * which field they would like to delete in the database or if they would like
           * to delete an entry in the database
           */
          ProjectDAO.deleteProjectOption(input);

        } else if (userInput == 4) {

          int backToMenu = ProjectDAO.likeSearch(input);

          if (backToMenu == 0) {
            continue;

          }
        } else if (userInput == 5) {
          /*
           * Below all the Customer table entries will be printed and the user will then
           * get choose what they would like to do the Search,delete or Update the
           * database
           */
          CustomerDAO.printAll();
          System.out.println(
              "\n1.Search Customer Database\n2.Delete Entry\n3.Update Customer database\n4.Add a new Entry\n0.Back to Main Menu");
          int userOption1 = input.nextInt();
          input.nextLine();
          if (userOption1 == 1) {
            /*
             * the method below will run if the user chooses option 1 which will provide
             * them with options to filter Architect database by.
             */
            CustomerDAO.likeSearch(input);

          } else if (userOption1 == 2) {
            /*
             * The user will enter the Customer id of the entry they would like to delete
             * then that entry will be deleted using the delete method from the CustomerDAO
             * class
             */

            System.out.println("Enter the Customer Id of the Entry you want to delete.");
            int customerID = input.nextInt();
            CustomerDAO.delete(customerID);

          } else if (userOption1 == 3) {
            /*
             * the method below will run if the user chooses option 3 which will allow the
             * user to update an entry using the Customer id and choose which they would to
             * update.
             */
            CustomerDAO.updateDatabase(input);

          } else if (userOption1 == 4) {
            CustomerDAO.inputAndInsert(input);

          } else if (userOption1 == 0) {
          //This will takes the user back to the main menu
            continue;

          } else {
            System.out.println("Invalid Option has been entered.");
          }
        } else if (userInput == 6) {

          /*
           * Below the user will be allowed to choose which table they would like to edit
           * between the Structural engineer Project Manager Database and the Architect
           * Database each with the same options as the customers database table above
           */

          System.out.println(
              "Which database Table would you like to access?\n1.Structural Engineer Database\n2.Project Manager Database\n3.Architect Database"
                  + "\n4.Contractor Database\n0.Back to the Main menu");
          int userOption = input.nextInt();

          if (userOption == 1) {
            StructEngineerDAO.printAll();

            System.out.println(
                "\n1.Search Structural Engineer Database\n2.Delete Entry\n3.Update Structural Engineer database\n4.Add a new Entry\n0.Back to Main Menu.");
            int userOption1 = input.nextInt();
            input.nextLine();

            if (userOption1 == 1) {
              /*
               * the method below will run if the user chooses option 1 which will provide
               * them with options to filter Struct_Engineers database by.
               */
              StructEngineerDAO.likeSearch(input);

            } else if (userOption1 == 2) {
              /*
               * The user will enter the Structural Engineer id of the entry they would like
               * to delete then that entry will be deleted using the delete method from the
               * StuctEngineerDAO class
               */
              System.out.println("Enter the Structural Engineer Id of the Entry you want to delete.");
              int structEngineerID = input.nextInt();
              StructEngineerDAO.delete(structEngineerID);

            } else if (userOption1 == 3) {
              /*
               * the method below will run if the user chooses option 3 which will allow the
               * user to update an entry using the Structural Engineer id and choose which
               * they would to update.
               */
              StructEngineerDAO.updateDatabase(input);

            } else if (userOption1 == 4) {
              StructEngineerDAO.inputAndInsert(input);

            } else {
              System.out.println("Invalid Option has been entered.");
            }
          } else if (userOption == 2) {
            ProjManagerDAO.printAll();
            System.out.println(
                "\n1.Search Project Manager Database\n2.Delete Entry\n3.Update Project Manager database\n4.Add a new Entry\n0.Back To Main Menu");
            int userOption1 = input.nextInt();
            input.nextLine();

            if (userOption1 == 1) {
              /*
               * the method below will run if the user chooses option 1 which will provide
               * them with options to filter project manager database by.
               */
              ProjManagerDAO.likeSearch(input);

            } else if (userOption1 == 2) {
              /*
               * The user will enter the project Manager id of the entry they would like to
               * delete then that entry will be deleted using the delete method from the
               * ProjManagerDAO class
               */
              System.out.println("Enter the Project Manager Id of the Entry you want to delete.");
              int projManagerID = input.nextInt();
              ProjManagerDAO.delete(projManagerID);

            } else if (userOption1 == 3) {
              /*
               * the method below will run if the user chooses option 3 which will allow the
               * user to update an entry using the project Manager id and choose which they
               * would to update.
               */
              ProjManagerDAO.updateDatabase(input);

            } else if (userOption1 == 4) {
              /*
               * if the user chooses option 4 the user will be prompted to enter information
               * needed to create a new entry then the new entry will be added.
               */
              ProjManagerDAO.inputAndInsert(input);

            } else if (userOption1 == 0) {
              continue;

            } else {
              System.out.println("Invalid Option has been entered.");
            }
          } else if (userOption == 3) {
            ArchitectDAO.printAll();
            System.out.println(
                "\n1.Search Architect Database\n2.Delete Entry\n3.Update Architect database\n4.Add a new Entry\n0.Back to Main Menu");
            int userOption1 = input.nextInt();
            input.nextLine();

            if (userOption1 == 1) {
              /*
               * the method below will run if the user chooses option 1 which will provide
               * them with options to filter Architect database by.
               */
              ArchitectDAO.likeSearch(input);

            } else if (userOption1 == 2) {
              /*
               * The user will enter the Architect id of the entry they would like to delete
               * then that entry will be deleted using the delete method from the ArchitectDAO
               * class
               */
              System.out.println("Enter the Architect Id of the Entry you want to delete.");
              int architectID = input.nextInt();
              ArchitectDAO.delete(architectID);

            } else if (userOption1 == 3) {
              /*
               * the method below will run if the user chooses option 3 which will allow the
               * user to update an entry using the Architect id and choose which they would to
               * update.
               */
              ArchitectDAO.updateDatabase(input);

            } else if (userOption1 == 4) {
              /*
               * if the user chooses option 4 the user will be prompted to enter information
               * needed to create a new entry then the new entry will be added.
               */
              ArchitectDAO.inputAndInsert(input);

            } else if (userOption1 == 0) {
              // This will takes the user back to the main menu
              continue;

            } else {
              System.out.println("Invalid Option has been entered.");
            }
          } else if (userOption == 4) {
            ContractorDAO.printAll();
            System.out.println(
                "\n1.Search Contractor Database\n2.Delete Entry\n3.Update Contractor database\n4.Add a new Entry\n0.Back to Main Menu");
            int userOption1 = input.nextInt();
            input.nextLine();

            if (userOption1 == 1) {
              /*
               * the method below will run if the user chooses option 1 which will provide
               * them with options to filter Contractor database by.
               */
              ContractorDAO.likeSearch(input);

            } else if (userOption1 == 2) {
              /*
               * The user will enter the Contractor id of the entry they would like to delete
               * then that entry will be deleted using the delete method from the
               * ContractorDAO class
               */
              System.out.println("Enter the Contractor Id of the Entry you want to delete.");
              int contractorID = input.nextInt();
              ContractorDAO.delete(contractorID);

            } else if (userOption1 == 3) {
              /*
               * the method below will run if the user chooses option 3 which will allow the
               * user to update an entry using the Contractor id and choose which they would
               * to update.
               */
              ContractorDAO.updateDatabase(input);

            } else if (userOption1 == 4) {
              /*
               * if the user chooses option 4 the user will be prompted to enter information
               * needed to create a new entry then the new entry will be added.
               */
              ContractorDAO.inputAndInsert(input);

            } else if (userOption1 == 0) {
              // This will takes the user back to the main menu
              continue;

            } else {
              System.out.println("Invalid Option has been entered.");
            }

          } else if (userOption == 0) {
            // This will takes the user back to the main menu
            continue;

          } else {
            System.out.println("Invalid option Entered");
          }
        } else if (userInput == 0) {
          System.out.println("Program has been terminated\nGOODBYE");
        }
      }
      input.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

}
