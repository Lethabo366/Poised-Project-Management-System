package models;

/**
 * @author Lethabo
 * @version 2024/10/01
 * 
 */
public class Project {
  /**
   *  below are the attributes of this class has 15 attributes 
   *  */
  String projectName;
  String buildingType;
  String physicalAddress;
  int erfNumber;
  double totalAmountCharged;
  double totalAmountPaid;
  String deadlineDate;
  String completionDate;
  int structuralEngineerID;
  int projectManagerID;
  int architectID;
  int customerID;
  int contractorID;
  boolean finalized;

  /**
   * Below is the constructor that uses all the field attributes to create a
   * Project object.
   * @param projectName contains the string that will be used for the projectName attribute.
   * @param buildingType contains the string that will be used for the buildingType attribute.
   * @param physicalAddress contains the string that will be used for the physicalAddress attribute.
   * @param erfNumber contains the int that will be used for the erfNumber attribute.
   * @param totalAmountCharged contains the double that will be used for the totalAmountCharged attribute.
   * @param totalAmountPaid contains the double that will be used for the totalAmountPaid attribute.
   * @param deadlineDate contains the string that will be used for the deadlineDate attribute.
   * @param completionDate contains the string that will be used for the completionDate attribute.
   * @param structuralEngineerID contains the int that will be used for the structuralEngineerID attribute.
   * @param projectManagerID contains the int that will be used for the projectManagerID attribute.
   * @param architectID contains the int that will be used for the architectID attribute.
   * @param customerID contains the int that will be used for the customerID attribute.
   * @param contractorID contains the int that will be used for the contractorID attribute.
   * @param finalized contains the boolean that will be used for the finalized attribute.
   */
  public Project(String projectName, String buildingType, String physicalAddress, int erfNumber,
      double totalAmountCharged, double totalAmountPaid, String deadlineDate, String completionDate,
      int structuralEngineerID, int projectManagerID, int architectID, int customerID, int contractorID,
      boolean finalized) {
    super();
    this.projectName = projectName;
    this.buildingType = buildingType;
    this.physicalAddress = physicalAddress;
    this.erfNumber = erfNumber;
    this.totalAmountCharged = totalAmountCharged;
    this.totalAmountPaid = totalAmountPaid;
    this.deadlineDate = deadlineDate;
    this.completionDate = completionDate;
    this.structuralEngineerID = structuralEngineerID;
    this.projectManagerID = projectManagerID;
    this.architectID = architectID;
    this.customerID = customerID;
    this.contractorID = contractorID;
    this.finalized = finalized;
  }

  /* Below are all the set and Get methods for this Class */

  /**
   * the method below returns the projectName attribute of the object.
   * @return returns the projectName attribute of the object
   */
  public String getProjectName() {
    return projectName;
  }

  /**
   * the method below sets the projectName attribute to the word the String
   * projectName parameter contains.
   * 
   * @param projectName contains the string that projectName attribute is going to
   *                    be set to.
   */
  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  /**
   * The method below returns the buildingType attribute of the object.
   * @return returns the buildingType attribute of the object
   */
  public String getBuildingType() {
    return buildingType;
  }

  /**
   * the method below sets the buildingType attribute to the word the String
   * buildingType parameter contains.
   * 
   * @param buildingType contains the string that physicalAddress attribute is
   *                     going to be set to.
   */
  public void setBuildingType(String buildingType) {
    this.buildingType = buildingType;
  }

  /**
   * The method below returns the physicalAddress attribute of the object.
   * @return returns the physicalAddress attribute of the object
   */
  public String getPhysicalAddress() {
    return physicalAddress;
  }

  /**
   * the method below sets the physicalAddress attribute to the word the String
   * physicalAddress parameter contains.
   * 
   * @param physicalAddress contains the string that physicalAddress attribute is
   *                        going to be set to.
   */
  public void setPhysicalAddress(String physicalAddress) {
    this.physicalAddress = physicalAddress;
  }

  /**
   * The method below returns the ERF attribute of the object.
   * @return returns the erfNumber attribute of the object
   */
  public int getErfNumber() {
    return erfNumber;
  }

  /**
   * the method below sets the erfNumber attribute to the integer the int
   * erfNumber parameter contains.
   * 
   * @param erfNumber contains the integer that erfNumber attribute is going to be
   *                  set to.
   */
  public void setErfNumber(int erfNumber) {
    this.erfNumber = erfNumber;
  }

  /**
   * The method below returns the totalAmountCharged attribute of the object.
   * @return returns the totalAmountCharged attribute of the object
   */
  public double getTotalAmountCharged() {
    return totalAmountCharged;
  }

  /**
   * the method below sets the totalAmountCharged attribute to the double
   * totalAmountCharged parameter contains.
   * 
   * @param totalAmountCharged contains the double that totalAmountCharged
   *                           attribute is going to be set to.
   */
  public void setTotalAmountCharged(double totalAmountCharged) {
    this.totalAmountCharged = totalAmountCharged;
  }

  /**
   * The method below returns the TotalAmountPaid attribute of the object.
   * @return returns the totalAmountPaid attribute of the object
   */
  public double getTotalAmountPaid() {
    return totalAmountPaid;
  }

  /**
   * the method below sets the totalAmountPaid attribute to the double
   * totalAmountPaid parameter contains.
   * 
   * @param totalAmountPaid contains the double that the totalAmountPaid attribute
   *                        is going to be set to.
   */
  public void setTotalAmountPaid(double totalAmountPaid) {
    this.totalAmountPaid = totalAmountPaid;
  }

  /**
   * The method below returns the deadlineDate attribute of the object.
   * @return returns the deadlineDate attribute of the object
   */
  public String getDeadlineDate() {
    return deadlineDate;
  }

  /**
   * the method below sets the deadlineDate attribute to the String deadlineDate
   * parameter contains.
   * 
   * @param deadlineDate contains the string that deadlineDate attribute is going
   *                     to be set to.
   */
  public void setDeadlineDate(String deadlineDate) {
    this.deadlineDate = deadlineDate;
  }

  /**
   * The method below returns the completionDate attribute of the object.
   * @return returns the completionDate attribute of the object
   */
  public String getCompletionDate() {
    return completionDate;
  }

  /**
   * the method below sets the Completion attribute to the String physicalAddress
   * parameter contains.
   * 
   * @param completionDate contains the string that projectName attribute is going
   *                       to be set to.
   */
  public void setCompletionDate(String completionDate) {
    this.completionDate = completionDate;
  }

  /**
   * The method below returns the structuralEngineerID attribute of the object.
   * @return returns the structuralEngineerID attribute of the object
   */
  public int getStructuralEngineerID() {
    return structuralEngineerID;
  }

  /**
   * the method below sets the structuralEngineerID attribute to the int
   * structuralEngineerID parameter contains.
   * 
   * @param structuralEngineerID contains the int that structuralEngineer attribute
   *                            is going to be set to.
   */

  public void setStructuralEngineerID(int structuralEngineerID) {
    this.structuralEngineerID = structuralEngineerID;
  }

  /**
   * The method below returns the ProjectManagerID attribute of the object.
   * @return returns the projectManagerID attribute of the object
   */
  public int getProjectManagerID() {
    return projectManagerID;
  }

  /**
   * the method below sets the projectManager attribute to the word the String
   * projectManager parameter contains.
   * 
   * @param projectManagerID contains the string that projectManager attribute is
   *                       going to be set to.
   */
  public void setProjectManagerID(int projectManagerID) {
    this.projectManagerID = projectManagerID;
  }

  /**
   * The method below returns the ArchitectID attribute of the object.
   * @return returns the architectID attribute of the object.
   */
  public int getArchitectID() {
    return architectID;
  }

  /**
   * the method below sets the ArchitectID attribute to the int architect ID
   * parameter contains.
   * 
   * @param architectID contains the string that architectID attribute is going to
   *                    be set to.
   */

  public void setArchitectID(int architectID) {
    this.architectID = architectID;
  }

  /**
   * The method below returns the CustomerID attribute of the object.
   * @return returns the customerID attribute of the object.
   */
  public int getCustomerID() {
    return customerID;
  }

  /**
   * the method below sets the customerID attribute to the int customerID
   * parameter contains.
   * 
   * @param customerID contains the int that customerID attribute is going to be
   *                   set to.
   */
  public void setCustomerID(int customerID) {
    this.customerID = customerID;
  }

  /**
   * The method below returns the ContractorID attribute of the object.
   * @return returns the contractorID attribute of the object
   */

  public int getContractorID() {
    return contractorID;
  }

  /**
   * the method below sets the contractorID attribute to the int contractorID
   * parameter contains.
   * 
   * @param contractorID contains the int that attribute is going to be set to.
   */
  public void setContractorID(int contractorID) {
    this.contractorID = contractorID;
  }

  /**
   * The method below returns the completed boolean attribute of the object
   * @return returns the finalized attribute of the project object.
   */

  public boolean getFinalized() {
    return finalized;
  }

  /**
   * the method below sets the finalized boolean attribute to the boolean
   * finalized parameter contains.
   * 
   * @param finalized contains the boolean that the attribute finalized is going to be set to.
   */
  public void setFinalized(boolean finalized) {
    this.finalized = finalized;
  }

}
