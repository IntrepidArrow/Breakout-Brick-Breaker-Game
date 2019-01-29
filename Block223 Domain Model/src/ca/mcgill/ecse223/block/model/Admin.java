package ca.mcgill.ecse223.block.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 7 "Block223.ump"
public class Admin extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Admin Attributes
  private String passwordAdmin;

  //Admin Associations
  private List<Game> games;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Admin(String aUserName, String aPasswordAdmin)
  {
    super(aUserName);
    passwordAdmin = aPasswordAdmin;
    games = new ArrayList<Game>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPasswordAdmin(String aPasswordAdmin)
  {
    boolean wasSet = false;
    passwordAdmin = aPasswordAdmin;
    wasSet = true;
    return wasSet;
  }

  public String getPasswordAdmin()
  {
    return passwordAdmin;
  }
  /* Code from template association_GetMany */
  public Game getGame(int index)
  {
    Game aGame = games.get(index);
    return aGame;
  }

  public List<Game> getGames()
  {
    List<Game> newGames = Collections.unmodifiableList(games);
    return newGames;
  }

  public int numberOfGames()
  {
    int number = games.size();
    return number;
  }

  public boolean hasGames()
  {
    boolean has = games.size() > 0;
    return has;
  }

  public int indexOfGame(Game aGame)
  {
    int index = games.indexOf(aGame);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGames()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Game addGame(String aName, int aId, float aMinPaddleLength, float aMaxPaddleLength, float aSpeedFactor, int aNumOfBlocks, int aBlockLimit, float aMinBallSpeed, int aPlayAreaLength, int aPlayAreaWidth)
  {
    return new Game(aName, aId, aMinPaddleLength, aMaxPaddleLength, aSpeedFactor, aNumOfBlocks, aBlockLimit, aMinBallSpeed, aPlayAreaLength, aPlayAreaWidth, this);
  }

  public boolean addGame(Game aGame)
  {
    boolean wasAdded = false;
    if (games.contains(aGame)) { return false; }
    Admin existingDesigner = aGame.getDesigner();
    boolean isNewDesigner = existingDesigner != null && !this.equals(existingDesigner);
    if (isNewDesigner)
    {
      aGame.setDesigner(this);
    }
    else
    {
      games.add(aGame);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGame(Game aGame)
  {
    boolean wasRemoved = false;
    //Unable to remove aGame, as it must always have a designer
    if (!this.equals(aGame.getDesigner()))
    {
      games.remove(aGame);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGameAt(Game aGame, int index)
  {  
    boolean wasAdded = false;
    if(addGame(aGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGames()) { index = numberOfGames() - 1; }
      games.remove(aGame);
      games.add(index, aGame);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGameAt(Game aGame, int index)
  {
    boolean wasAdded = false;
    if(games.contains(aGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGames()) { index = numberOfGames() - 1; }
      games.remove(aGame);
      games.add(index, aGame);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGameAt(aGame, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=games.size(); i > 0; i--)
    {
      Game aGame = games.get(i - 1);
      aGame.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "passwordAdmin" + ":" + getPasswordAdmin()+ "]";
  }
}