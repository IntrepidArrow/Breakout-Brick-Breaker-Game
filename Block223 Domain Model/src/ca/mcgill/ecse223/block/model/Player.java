package ca.mcgill.ecse223.block.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 3 "Block223.ump"
public class Player extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Attributes
  private String passwordPlayer;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(String aUserName, String aPasswordPlayer)
  {
    super(aUserName);
    passwordPlayer = aPasswordPlayer;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPasswordPlayer(String aPasswordPlayer)
  {
    boolean wasSet = false;
    passwordPlayer = aPasswordPlayer;
    wasSet = true;
    return wasSet;
  }

  public String getPasswordPlayer()
  {
    return passwordPlayer;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "passwordPlayer" + ":" + getPasswordPlayer()+ "]";
  }
}