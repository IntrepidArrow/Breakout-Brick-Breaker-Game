/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 5 "UmpleMakeMeeting1.ump"
public class GameDesigner
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  public static final float PADDLEWIDTH = (float) 5.0;
  public static final float BALLDIAMETER = (float) 10.0;
  public static final float BLOCKSIDE = (float) 20.0;
  public static final int MINPLAYAREALENGTH = 200;
  public static final int MAXPLAYAREALENGTH = 500;
  public static final int MINPLAYAREAWIDTH = 200;
  public static final int MAXPLAYAREAWIDTH = 500;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GameDesigner Attributes
  private int playAreaLength;
  private int playAreaWidth;

  //GameDesigner Associations
  private AppManager appManager;
  private User user;
  private List<Game> games;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GameDesigner(int aPlayAreaLength, int aPlayAreaWidth, AppManager aAppManager, User aUser)
  {
    playAreaLength = aPlayAreaLength;
    playAreaWidth = aPlayAreaWidth;
    if (aAppManager == null || aAppManager.getGameDesigner() != null)
    {
      throw new RuntimeException("Unable to create GameDesigner due to aAppManager");
    }
    appManager = aAppManager;
    if (aUser == null || aUser.getGameDesigner() != null)
    {
      throw new RuntimeException("Unable to create GameDesigner due to aUser");
    }
    user = aUser;
    games = new ArrayList<Game>();
  }

  public GameDesigner(int aPlayAreaLength, int aPlayAreaWidth, String aNameForUser, String aPasswordForUser, boolean aIsAdminForUser, String aAdminPasswordForUser)
  {
    playAreaLength = aPlayAreaLength;
    playAreaWidth = aPlayAreaWidth;
    appManager = new AppManager(this);
    user = new User(aNameForUser, aPasswordForUser, aIsAdminForUser, aAdminPasswordForUser, this);
    games = new ArrayList<Game>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public int getPlayAreaLength()
  {
    return playAreaLength;
  }

  public int getPlayAreaWidth()
  {
    return playAreaWidth;
  }
  /* Code from template association_GetOne */
  public AppManager getAppManager()
  {
    return appManager;
  }
  /* Code from template association_GetOne */
  public User getUser()
  {
    return user;
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
  public Game addGame(String aName, float aMinPaddleLength, float aMaxPaddleLength, float aSpeedFactor, int aNumOfBlocks, int aBlockLimit, float aMinBallSpeed)
  {
    return new Game(aName, aMinPaddleLength, aMaxPaddleLength, aSpeedFactor, aNumOfBlocks, aBlockLimit, aMinBallSpeed, this);
  }

  public boolean addGame(Game aGame)
  {
    boolean wasAdded = false;
    if (games.contains(aGame)) { return false; }
    GameDesigner existingGameDesigner = aGame.getGameDesigner();
    boolean isNewGameDesigner = existingGameDesigner != null && !this.equals(existingGameDesigner);
    if (isNewGameDesigner)
    {
      aGame.setGameDesigner(this);
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
    //Unable to remove aGame, as it must always have a gameDesigner
    if (!this.equals(aGame.getGameDesigner()))
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
    AppManager existingAppManager = appManager;
    appManager = null;
    if (existingAppManager != null)
    {
      existingAppManager.delete();
    }
    User existingUser = user;
    user = null;
    if (existingUser != null)
    {
      existingUser.delete();
    }
    for(int i=games.size(); i > 0; i--)
    {
      Game aGame = games.get(i - 1);
      aGame.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "playAreaLength" + ":" + getPlayAreaLength()+ "," +
            "playAreaWidth" + ":" + getPlayAreaWidth()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "appManager = "+(getAppManager()!=null?Integer.toHexString(System.identityHashCode(getAppManager())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "user = "+(getUser()!=null?Integer.toHexString(System.identityHashCode(getUser())):"null");
  }
}