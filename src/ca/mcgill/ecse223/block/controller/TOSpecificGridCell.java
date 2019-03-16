/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.controller;

// line 46 "../../../../../Block223TransferObjects.ump"
public class TOSpecificGridCell
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOSpecificGridCell Attributes
  private int posX;
  private int posY;
  private int id;
  private int red;
  private int green;
  private int blue;
  private int points;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOSpecificGridCell(int aPosX, int aPosY, int aId, int aRed, int aGreen, int aBlue, int aPoints)
  {
    posX = aPosX;
    posY = aPosY;
    id = aId;
    red = aRed;
    green = aGreen;
    blue = aBlue;
    points = aPoints;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPosX(int aPosX)
  {
    boolean wasSet = false;
    posX = aPosX;
    wasSet = true;
    return wasSet;
  }

  public boolean setPosY(int aPosY)
  {
    boolean wasSet = false;
    posY = aPosY;
    wasSet = true;
    return wasSet;
  }

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public boolean setRed(int aRed)
  {
    boolean wasSet = false;
    red = aRed;
    wasSet = true;
    return wasSet;
  }

  public boolean setGreen(int aGreen)
  {
    boolean wasSet = false;
    green = aGreen;
    wasSet = true;
    return wasSet;
  }

  public boolean setBlue(int aBlue)
  {
    boolean wasSet = false;
    blue = aBlue;
    wasSet = true;
    return wasSet;
  }

  public boolean setPoints(int aPoints)
  {
    boolean wasSet = false;
    points = aPoints;
    wasSet = true;
    return wasSet;
  }

  public int getPosX()
  {
    return posX;
  }

  public int getPosY()
  {
    return posY;
  }

  public int getId()
  {
    return id;
  }

  public int getRed()
  {
    return red;
  }

  public int getGreen()
  {
    return green;
  }

  public int getBlue()
  {
    return blue;
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
            "posX" + ":" + getPosX()+ "," +
            "posY" + ":" + getPosY()+ "," +
            "id" + ":" + getId()+ "," +
            "red" + ":" + getRed()+ "," +
            "green" + ":" + getGreen()+ "," +
            "blue" + ":" + getBlue()+ "," +
            "points" + ":" + getPoints()+ "]";
  }
}