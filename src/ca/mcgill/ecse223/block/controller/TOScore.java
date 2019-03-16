/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.controller;

// line 37 "../../../../../Block223TransferObjects.ump"
public class TOScore
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOScore Attributes
  private int points;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOScore(int aPoints)
  {
    points = aPoints;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPoints(int aPoints)
  {
    boolean wasSet = false;
    points = aPoints;
    wasSet = true;
    return wasSet;
  }

  public int getPoints()
  {
    return points;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "points" + ":" + getPoints()+ "]";
  }
}