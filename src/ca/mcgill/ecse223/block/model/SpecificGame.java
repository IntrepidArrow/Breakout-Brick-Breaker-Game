/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.sql.Date;
import java.util.*;

// line 4 "../../../../../Block223PlayGame.ump"
public class SpecificGame extends Game
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SpecificGame Attributes
  private Date date;
  private int paddleXPos;
  private int paddleYPos;
  private int ballXPos;
  private int ballYPos;
  private int currentLevelPlayed;
  private int nrOfLife;

  //Autounique Attributes
  private int id;

  //SpecificGame Associations
  private Game game;
  private Player player;
  private Score score;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SpecificGame(String aName, int aNrBlocksPerLevel, Admin aAdmin, Ball aBall, Paddle aPaddle, Block223 aBlock223, Date aDate, int aCurrentLevelPlayed, Game aGame, Player aPlayer, Score aScore)
  {
    super(aName, aNrBlocksPerLevel, aAdmin, aBall, aPaddle, aBlock223);
    date = aDate;
    resetPaddleXPos();
    resetPaddleYPos();
    resetBallXPos();
    resetBallYPos();
    currentLevelPlayed = aCurrentLevelPlayed;
    resetNrOfLife();
    id = nextId++;
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create specificGame due to game");
    }
    boolean didAddPlayer = setPlayer(aPlayer);
    if (!didAddPlayer)
    {
      throw new RuntimeException("Unable to create specificGame due to player");
    }
    if (aScore == null || aScore.getSpecificGame() != null)
    {
      throw new RuntimeException("Unable to create SpecificGame due to aScore");
    }
    score = aScore;
  }

  public SpecificGame(String aName, int aNrBlocksPerLevel, Admin aAdmin, Ball aBall, Paddle aPaddle, Block223 aBlock223, Date aDate, int aCurrentLevelPlayed, Game aGame, Player aPlayer, Game aGameForScore, Player aPlayerForScore)
  {
    super(aName, aNrBlocksPerLevel, aAdmin, aBall, aPaddle, aBlock223);
    date = aDate;
    resetPaddleXPos();
    resetPaddleYPos();
    resetBallXPos();
    resetBallYPos();
    currentLevelPlayed = aCurrentLevelPlayed;
    resetNrOfLife();
    id = nextId++;
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create specificGame due to game");
    }
    boolean didAddPlayer = setPlayer(aPlayer);
    if (!didAddPlayer)
    {
      throw new RuntimeException("Unable to create specificGame due to player");
    }
    score = new Score(aGameForScore, aPlayerForScore, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setPaddleXPos(int aPaddleXPos)
  {
    boolean wasSet = false;
    paddleXPos = aPaddleXPos;
    wasSet = true;
    return wasSet;
  }

  public boolean resetPaddleXPos()
  {
    boolean wasReset = false;
    paddleXPos = getDefaultPaddleXPos();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setPaddleYPos(int aPaddleYPos)
  {
    boolean wasSet = false;
    paddleYPos = aPaddleYPos;
    wasSet = true;
    return wasSet;
  }

  public boolean resetPaddleYPos()
  {
    boolean wasReset = false;
    paddleYPos = getDefaultPaddleYPos();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setBallXPos(int aBallXPos)
  {
    boolean wasSet = false;
    ballXPos = aBallXPos;
    wasSet = true;
    return wasSet;
  }

  public boolean resetBallXPos()
  {
    boolean wasReset = false;
    ballXPos = getDefaultBallXPos();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setBallYPos(int aBallYPos)
  {
    boolean wasSet = false;
    ballYPos = aBallYPos;
    wasSet = true;
    return wasSet;
  }

  public boolean resetBallYPos()
  {
    boolean wasReset = false;
    ballYPos = getDefaultBallYPos();
    wasReset = true;
    return wasReset;
  }

  public boolean setCurrentLevelPlayed(int aCurrentLevelPlayed)
  {
    boolean wasSet = false;
    currentLevelPlayed = aCurrentLevelPlayed;
    wasSet = true;
    return wasSet;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setNrOfLife(int aNrOfLife)
  {
    boolean wasSet = false;
    nrOfLife = aNrOfLife;
    wasSet = true;
    return wasSet;
  }

  public boolean resetNrOfLife()
  {
    boolean wasReset = false;
    nrOfLife = getDefaultNrOfLife();
    wasReset = true;
    return wasReset;
  }

  public Date getDate()
  {
    return date;
  }

  /**
   * depends on paddle length
   */
  public int getPaddleXPos()
  {
    return paddleXPos;
  }
  /* Code from template attribute_GetDefaulted */
  public int getDefaultPaddleXPos()
  {
    return 195;
  }

  /**
   * depends on paddle length
   */
  public int getPaddleYPos()
  {
    return paddleYPos;
  }
  /* Code from template attribute_GetDefaulted */
  public int getDefaultPaddleYPos()
  {
    return 195;
  }

  public int getBallXPos()
  {
    return ballXPos;
  }
  /* Code from template attribute_GetDefaulted */
  public int getDefaultBallXPos()
  {
    return 195;
  }

  public int getBallYPos()
  {
    return ballYPos;
  }
  /* Code from template attribute_GetDefaulted */
  public int getDefaultBallYPos()
  {
    return 195;
  }

  public int getCurrentLevelPlayed()
  {
    return currentLevelPlayed;
  }

  public int getNrOfLife()
  {
    return nrOfLife;
  }
  /* Code from template attribute_GetDefaulted */
  public int getDefaultNrOfLife()
  {
    return 3;
  }

  public int getId()
  {
    return id;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_GetOne */
  public Player getPlayer()
  {
    return player;
  }
  /* Code from template association_GetOne */
  public Score getScore()
  {
    return score;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGame(Game aGame)
  {
    boolean wasSet = false;
    if (aGame == null)
    {
      return wasSet;
    }

    Game existingGame = game;
    game = aGame;
    if (existingGame != null && !existingGame.equals(aGame))
    {
      existingGame.removeSpecificGame(this);
    }
    game.addSpecificGame(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setPlayer(Player aPlayer)
  {
    boolean wasSet = false;
    if (aPlayer == null)
    {
      return wasSet;
    }

    Player existingPlayer = player;
    player = aPlayer;
    if (existingPlayer != null && !existingPlayer.equals(aPlayer))
    {
      existingPlayer.removeSpecificGame(this);
    }
    player.addSpecificGame(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removeSpecificGame(this);
    }
    Player placeholderPlayer = player;
    this.player = null;
    if(placeholderPlayer != null)
    {
      placeholderPlayer.removeSpecificGame(this);
    }
    Score existingScore = score;
    score = null;
    if (existingScore != null)
    {
      existingScore.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "paddleXPos" + ":" + getPaddleXPos()+ "," +
            "paddleYPos" + ":" + getPaddleYPos()+ "," +
            "ballXPos" + ":" + getBallXPos()+ "," +
            "ballYPos" + ":" + getBallYPos()+ "," +
            "currentLevelPlayed" + ":" + getCurrentLevelPlayed()+ "," +
            "nrOfLife" + ":" + getNrOfLife()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "score = "+(getScore()!=null?Integer.toHexString(System.identityHashCode(getScore())):"null");
  }
}