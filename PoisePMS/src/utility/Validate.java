package utility;

import java.util.Scanner;

/**
 * @author Lethabo
 * @version 2024/10/01
 * 
 */
public class Validate {
  /**
   * The method prompts the user to enter the year ,month and day of the deadline
   * date they choose and validates that each of those inputs are integers and
   * concatenates those inputs into a string and returns it
   * 
   * @param input contains the scanner used to get user input
   * @return returns a string
   */
  public static String enterDeadline(Scanner input) {
    System.out.println("Enter the year of the deadline date. Format(YYYY E.G 2000)");
    String year = input.nextLine();

    while (!validateInteger(year)) {
      System.out.println("Type in a valid Year!(2024-2099)");
      year = input.nextLine();

    }

    System.out.println("Enter the month of the deadline date. Format(MM E.G 03 FOR MARCH, 05 FOR MAY)");
    String month = input.nextLine();

    while (!validateInteger(month)) {
      System.out.println("Type in a valid month!(1-12)");
      year = input.nextLine();

    }

    System.out
        .println("Enter the day of the month of the deadline date. Format(DD E.G 30 FOR 3OTH of the month or 1 for "
            + " for first of the month)");
    String day = input.nextLine();

    while (!validateInteger(day)) {
      System.out.println("Type in a valid day!(1-31)");
      day = input.nextLine();
    }
    String deadlineDate = year + "-" + month + "-" + day;
    return deadlineDate;
  }

  /**
   * The method prompts the user to enter the year ,month and day of the
   * completion date they choose and validates that each of those inputs are
   * integers and concatenates those inputs into a string and returns it
   * 
   * @param input contains the scanner used to get user input
   * @return returns a string
   */
  public static String enterCompletion(Scanner input) {
    System.out.println("Enter the year of the Completion date. Format(YYYY E.G 2000)");
    String year = input.nextLine();

    while (!validateInteger(year)) {
      System.out.println("Type in a valid Year!(2024-2099)");
      year = input.nextLine();

    }

    System.out.println("Enter the month of the Completion date. Format(MM E.G 03 FOR MARCH, 05 FOR MAY)");
    String month = input.nextLine();

    while (!validateInteger(month)) {
      System.out.println("Type in a valid month!(1-12)");
      year = input.nextLine();

    }

    System.out
        .println("Enter the day of the month of the Completion date. Format(DD E.G 30 FOR 3OTH of the month or 1 for "
            + " for first of the month)");
    String day = input.nextLine();

    while (!validateInteger(day)) {
      System.out.println("Type in a valid day!(1-31)");
      day = input.nextLine();

    }

    String deadlineDate = year + "-" + month + "-" + day;

    return deadlineDate;
  }

  /**
   * The method below validates the string testString value is a double. if the
   * string is a double the method will return a boolean of true and if false it
   * is not.
   * 
   * @param testString contains the string that will validated if it is a double
   *                   or not.
   * @return returns true or false (Boolean)
   */
  public static Boolean validateDouble(String testString) {
    try {
      double number = Double.parseDouble(testString);
      return true;

    } catch (NumberFormatException e) {
      return false;
    }
  }

  /**
   * The method below validates the string testString value is a integer. if the
   * string is a integer the method will return a boolean of true and if false it
   * is not.
   * 
   * @param testString contains the string that will validated if it is a integer
   *                   or not.
   * @return returns true or false (Boolean)
   */
  public static Boolean validateInteger(String testString) {
    try {
      int number = Integer.parseInt(testString);
      return true;

    } catch (NumberFormatException e) {

      return false;
    }
  }

  /**
   * The method below will prompt the user the to enter the amount charged if the
   * amount charged is not a valid double the user will prompted to re-enter until
   * a valid double is entered.
   * 
   * @param input the scanner object that will get the users input.
   * @return double which contains the valid double value.
   */
  public static double TotalCharged(Scanner input) {
    System.out.println("Enter the Total Amount Charged. (Use a full Stop(.) for a decimal point) ");
    String doubleString = input.nextLine();

    while (!validateDouble(doubleString)) {
      System.out.println("Enter a Valid Amount with a decimal value. ");
      doubleString = input.nextLine();
    }
    double totalAmountCharged = Double.parseDouble(doubleString);
    return totalAmountCharged;
  }

  /**
   * The method below will prompt the user the to enter the amount paid if the
   * amount paid is not a valid double the user will prompted to re-enter until a
   * valid double is entered.
   * 
   * @param input the scanner object that will get the users input.
   * @return double which contains the valid double value.
   */
  public static double TotalPaid(Scanner input) {
    System.out.println("Enter the Total Amount Paid. (Use a Full Stop (.) for a decimal point) ");
    String doubleString = input.nextLine();

    while (!validateDouble(doubleString)) {
      System.out.println("Enter a Valid Amount with a decimal value.  ");
      doubleString = input.nextLine();
    }
    double totalAmountCharged = Double.parseDouble(doubleString);
    return totalAmountCharged;
  }

  /**
   * The method below will prompt the user the to enter the erfNumber if the
   * erfNumber is not a valid integer the user will prompted to re-enter until a
   * valid integer is entered.
   * 
   * @param input the scanner object that will get the users input.
   * @return double which contains the valid double value.
   */
  public static int erfNumber(Scanner input) {
    System.out.println("Enter a Erf Number");
    String erfNumString = input.nextLine();

    while (!validateInteger(erfNumString)) {
      System.out.println("Enter a valid ERF Number");
      erfNumString = input.nextLine();
    }

    int erfNum = Integer.parseInt(erfNumString);
    return erfNum;
  }
}
