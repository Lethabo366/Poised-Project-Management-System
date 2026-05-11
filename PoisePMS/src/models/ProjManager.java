package models;

/**
 * @author Lethabo
 * @version 2024/10/01
 * 
 */
public class ProjManager {
  /*
   * This class has 5 attributes surname,name,telephone Number,emailAddress and
   * physicalAddress
   */
  String surname;
  String name;
  String telephoneNumber;
  String emailAddress;
  String physicalAddress;

  /**
   * Below is the constructor that uses all the field attributes to create a
   * ProjManager object.
   * 
   * @param surname         contains that will be the surname attribute of the
   *                        object.
   * @param name            contains that will be the name attribute of the
   *                        object.
   * @param telephoneNumber contains that will be the telephoneNumber attribute of
   *                        the object.
   * @param address         contains that will be the emailAddress attribute of
   *                        the object.
   * @param physicalAddress contains that will be the physicalAddress attribute of
   *                        the object.
   */
  public ProjManager(String surname, String name, String telephoneNumber, String address, String physicalAddress) {
    this.surname = surname;
    this.name = name;
    this.telephoneNumber = telephoneNumber;
    this.emailAddress = address;
    this.physicalAddress = physicalAddress;
  }

  /**
   * the method below returns the surname attribute of the object
   * 
   * @return returns the surname attribute of the object.
   */
  public String getSurname() {
    return surname;
  }

  /**
   * the method below sets the surname attribute to the word String surname
   * parameter.
   * 
   * @param surname contains the string that surname attribute is going to be set
   *                to.
   */
  public void setSurname(String surname) {
    this.surname = surname;
  }

  /**
   * The method below returns the Name attribute of the object
   * 
   * @return returns the name attribute of the object.
   */
  public String getName() {
    return name;
  }

  /**
   * the method below sets the name attribute to the word String name parameter
   * contains.
   * 
   * @param name contains the string that name attribute is going changed to
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * The method below returns the Name attribute of the object.
   * 
   * @return returns the telephone attribute of the object.
   */
  public String getTelephoneNumber() {
    return telephoneNumber;
  }

  /**
   * the method below sets the telephoneNumber attribute to the word String name
   * parameter contains
   * 
   * @param telephoneNumber contains the string that telephoneNumber attribute is
   *                        going to be set to
   */
  public void setTelephoneNumber(String telephoneNumber) {
    this.telephoneNumber = telephoneNumber;
  }

  /**
   * The method below returns the emailAddress attribute of the object.
   * 
   * @return returns the emailAddress attribute of the object.
   */
  public String getEmailAddress() {
    return emailAddress;
  }

  /**
   * the method below sets the address attribute to the word the String address
   * parameter contains
   * 
   * @param address contains the string that EmailAddress attribute is going to be
   *                set to
   */
  public void setEmailAddress(String address) {
    this.emailAddress = address;
  }

  /**
   * The method below returns the physicalAddress attribute of the object.
   * 
   * @return returns the Physical attribute of the object.
   */
  public String getPhysicalAddress() {
    return physicalAddress;
  }

  /**
   * the method below sets the physicalAddress attribute to the word the String
   * physicalAddress parameter contains
   * 
   * @param physicalAddress contains the string that phyiscalAddress attribute is
   *                        going changed to
   */
  public void setPhysicalAddress(String physicalAddress) {
    this.physicalAddress = physicalAddress;
  }

}
