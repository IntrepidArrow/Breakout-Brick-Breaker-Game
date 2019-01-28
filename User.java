/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 18 "UmpleMakeMeeting1.ump"
public class User
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, User> usersByName = new HashMap<String, User>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String name;
  private String password;
  private boolean isAdmin;
  private String adminPassword;

  //User Associations
  private GameDesigner gameDesigner;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aName, String aPassword, boolean aIsAdmin, String aAdminPassword, GameDesigner aGameDesigner)
  {
    password = aPassword;
    isAdmin = aIsAdmin;
    adminPassword = aAdminPassword;
    if (!setName(aName))
    {
      throw new RuntimeException("Cannot create due to duplicate name");
    }
    if (aGameDesigner == null || aGameDesigner.getUser() != null)
    {
      throw new RuntimeException("Unable to create User due to aGameDesigner");
    }
    gameDesigner = aGameDesigner;
  }

  public User(String aName, String aPassword, boolean aIsAdmin, String aAdminPassword, int aPlayAreaLengthForGameDesigner, int aPlayAreaWidthForGameDesigner, AppManager aAppManagerForGameDesigner)
  {
    name = aName;
    password = aPassword;
    isAdmin = aIsAdmin;
    adminPassword = aAdminPassword;
    gameDesigner = new GameDesigner(aPlayAreaLengthForGameDesigner, aPlayAreaWidthForGameDesigner, aAppManagerForGameDesigner, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    String anOldName = getName();
    if (hasWithName(aName)) {
      return wasSet;
    }
    name = aName;
    wasSet = true;
    if (anOldName != null) {
      usersByName.remove(anOldName);
    }
    usersByName.put(aName, this);
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsAdmin(boolean aIsAdmin)
  {
    boolean wasSet = false;
    isAdmin = aIsAdmin;
    wasSet = true;
    return wasSet;
  }

  public boolean setAdminPassword(String aAdminPassword)
  {
    boolean wasSet = false;
    adminPassword = aAdminPassword;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template attribute_GetUnique */
  public static User getWithName(String aName)
  {
    return usersByName.get(aName);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithName(String aName)
  {
    return getWithName(aName) != null;
  }

  public String getPassword()
  {
    return password;
  }

  public boolean getIsAdmin()
  {
    return isAdmin;
  }

  public String getAdminPassword()
  {
    return adminPassword;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsAdmin()
  {
    return isAdmin;
  }
  /* Code from template association_GetOne */
  public GameDesigner getGameDesigner()
  {
    return gameDesigner;
  }

  public void delete()
  {
    usersByName.remove(getName());
    GameDesigner existingGameDesigner = gameDesigner;
    gameDesigner = null;
    if (existingGameDesigner != null)
    {
      existingGameDesigner.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "password" + ":" + getPassword()+ "," +
            "isAdmin" + ":" + getIsAdmin()+ "," +
            "adminPassword" + ":" + getAdminPassword()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "gameDesigner = "+(getGameDesigner()!=null?Integer.toHexString(System.identityHashCode(getGameDesigner())):"null");
  }
}