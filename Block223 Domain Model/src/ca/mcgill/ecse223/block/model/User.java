package ca.mcgill.ecse223.block.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 1 "Block223.ump"
public class User
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, User> usersByUserName = new HashMap<String, User>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String userName;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aUserName)
  {
    if (!setUserName(aUserName))
    {
      throw new RuntimeException("Cannot create due to duplicate userName");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setUserName(String aUserName)
  {
    boolean wasSet = false;
    String anOldUserName = getUserName();
    if (hasWithUserName(aUserName)) {
      return wasSet;
    }
    userName = aUserName;
    wasSet = true;
    if (anOldUserName != null) {
      usersByUserName.remove(anOldUserName);
    }
    usersByUserName.put(aUserName, this);
    return wasSet;
  }

  public String getUserName()
  {
    return userName;
  }
  /* Code from template attribute_GetUnique */
  public static User getWithUserName(String aUserName)
  {
    return usersByUserName.get(aUserName);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithUserName(String aUserName)
  {
    return getWithUserName(aUserName) != null;
  }

  public void delete()
  {
    usersByUserName.remove(getUserName());
  }


  public String toString()
  {
    return super.toString() + "["+
            "userName" + ":" + getUserName()+ "]";
  }
}