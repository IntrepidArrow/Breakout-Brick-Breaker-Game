/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.controller;

// line 41 "../../../../../Block223TransferObjects.ump"
public class TOHallOfFame
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOHallOfFame Attributes
  private List<TOScore> listOfToScores;
  private List<String> listOfUsernames;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOHallOfFame(List<TOScore> aListOfToScores, List<String> aListOfUsernames)
  {
    listOfToScores = aListOfToScores;
    listOfUsernames = aListOfUsernames;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setListOfToScores(List<TOScore> aListOfToScores)
  {
    boolean wasSet = false;
    listOfToScores = aListOfToScores;
    wasSet = true;
    return wasSet;
  }

  public boolean setListOfUsernames(List<String> aListOfUsernames)
  {
    boolean wasSet = false;
    listOfUsernames = aListOfUsernames;
    wasSet = true;
    return wasSet;
  }

  public List<TOScore> getListOfToScores()
  {
    return listOfToScores;
  }

  public List<String> getListOfUsernames()
  {
    return listOfUsernames;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "listOfToScores" + "=" + (getListOfToScores() != null ? !getListOfToScores().equals(this)  ? getListOfToScores().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "listOfUsernames" + "=" + (getListOfUsernames() != null ? !getListOfUsernames().equals(this)  ? getListOfUsernames().toString().replaceAll("  ","    ") : "this" : "null");
  }
}