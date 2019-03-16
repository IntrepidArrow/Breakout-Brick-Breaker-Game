/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.util.*;

// line 18 "../../../../../Block223PlayGame.ump"
// line 7 "../../../../../Block223States.ump"
public class SpecificGame
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SpecificGame Attributes
  private int nrOfLife;
  private int currentLevelPlayed;

  //Autounique Attributes
  private int id;

  //SpecificGame State Machines
  public enum GameStatus { Init, ongoing, Paused, ResetLevel, Lvl_End, Game_End }
  private GameStatus gameStatus;

  //SpecificGame Associations
  private List<Score> scores;
  private SpecificBall specificBall;
  private SpecificPaddle specificPaddle;
  private List<SpecificBlockAssignment> blockAssignments;
  private Game game;
  private Player player;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SpecificGame(int aCurrentLevelPlayed, SpecificBall aSpecificBall, SpecificPaddle aSpecificPaddle, Game aGame, Player aPlayer)
  {
    resetNrOfLife();
    currentLevelPlayed = aCurrentLevelPlayed;
    id = nextId++;
    scores = new ArrayList<Score>();
    if (aSpecificBall == null || aSpecificBall.getSpecificGame() != null)
    {
      throw new RuntimeException("Unable to create SpecificGame due to aSpecificBall");
    }
    specificBall = aSpecificBall;
    if (aSpecificPaddle == null || aSpecificPaddle.getSpecificGame() != null)
    {
      throw new RuntimeException("Unable to create SpecificGame due to aSpecificPaddle");
    }
    specificPaddle = aSpecificPaddle;
    blockAssignments = new ArrayList<SpecificBlockAssignment>();
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
    setGameStatus(GameStatus.Init);
  }

  public SpecificGame(int aCurrentLevelPlayed, Ball aBallForSpecificBall, Paddle aPaddleForSpecificPaddle, Game aGame, Player aPlayer)
  {
    resetNrOfLife();
    currentLevelPlayed = aCurrentLevelPlayed;
    id = nextId++;
    scores = new ArrayList<Score>();
    specificBall = new SpecificBall(aBallForSpecificBall, this);
    specificPaddle = new SpecificPaddle(aPaddleForSpecificPaddle, this);
    blockAssignments = new ArrayList<SpecificBlockAssignment>();
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
  }

  //------------------------
  // INTERFACE
  //------------------------
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

  public boolean setCurrentLevelPlayed(int aCurrentLevelPlayed)
  {
    boolean wasSet = false;
    currentLevelPlayed = aCurrentLevelPlayed;
    wasSet = true;
    return wasSet;
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

  public int getCurrentLevelPlayed()
  {
    return currentLevelPlayed;
  }

  public int getId()
  {
    return id;
  }

  public String getGameStatusFullName()
  {
    String answer = gameStatus.toString();
    return answer;
  }

  public GameStatus getGameStatus()
  {
    return gameStatus;
  }

  public boolean setup()
  {
    boolean wasEventProcessed = false;
    
    GameStatus aGameStatus = gameStatus;
    switch (aGameStatus)
    {
      case Init:
        setGameStatus(GameStatus.ongoing);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean getInput()
  {
    boolean wasEventProcessed = false;
    
    GameStatus aGameStatus = gameStatus;
    switch (aGameStatus)
    {
      case ongoing:
        setGameStatus(GameStatus.Paused);
        wasEventProcessed = true;
        break;
      case Paused:
        setGameStatus(GameStatus.ongoing);
        wasEventProcessed = true;
        break;
      case ResetLevel:
        setGameStatus(GameStatus.ongoing);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean moveBall()
  {
    boolean wasEventProcessed = false;
    
    GameStatus aGameStatus = gameStatus;
    switch (aGameStatus)
    {
      case ongoing:
        if (isWallPaddleHit())
        {
        // line 17 "../../../../../Block223States.ump"
          doWallPaddleHit();
          setGameStatus(GameStatus.ongoing);
          wasEventProcessed = true;
          break;
        }
        if (isBlockHit(level))
        {
        // line 19 "../../../../../Block223States.ump"
          doBlockHit();
          setGameStatus(GameStatus.ongoing);
          wasEventProcessed = true;
          break;
        }
        if (level.getSpecificBlockAssignments().size==0)
        {
        // line 21 "../../../../../Block223States.ump"
          saveScoreAndDelete();
          setGameStatus(GameStatus.Game_End);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBounds()&&specificGame.getNrOfLife()==1)
        {
          setGameStatus(GameStatus.Game_End);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBounds()&&specificGame.getNrOfLife()>1)
        {
        // line 27 "../../../../../Block223States.ump"
          doOutOfBounds();
          setGameStatus(GameStatus.ResetLevel);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean levelCompleted()
  {
    boolean wasEventProcessed = false;
    
    GameStatus aGameStatus = gameStatus;
    switch (aGameStatus)
    {
      case ongoing:
        setGameStatus(GameStatus.Lvl_End);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean gameCompleted()
  {
    boolean wasEventProcessed = false;
    
    GameStatus aGameStatus = gameStatus;
    switch (aGameStatus)
    {
      case Lvl_End:
        setGameStatus(GameStatus.Game_End);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean space()
  {
    boolean wasEventProcessed = false;
    
    GameStatus aGameStatus = gameStatus;
    switch (aGameStatus)
    {
      case Lvl_End:
        setGameStatus(GameStatus.Init);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setGameStatus(GameStatus aGameStatus)
  {
    gameStatus = aGameStatus;

    // entry actions and do activities
    switch(gameStatus)
    {
      case Paused:
        // line 38 "../../../../../Block223States.ump"
        saveGame();
        break;
      case Game_End:
        // line 52 "../../../../../Block223States.ump"
        saveScoreandDelete()
        break;
    }
  }
  /* Code from template association_GetMany */
  public Score getScore(int index)
  {
    Score aScore = scores.get(index);
    return aScore;
  }

  public List<Score> getScores()
  {
    List<Score> newScores = Collections.unmodifiableList(scores);
    return newScores;
  }

  public int numberOfScores()
  {
    int number = scores.size();
    return number;
  }

  public boolean hasScores()
  {
    boolean has = scores.size() > 0;
    return has;
  }

  public int indexOfScore(Score aScore)
  {
    int index = scores.indexOf(aScore);
    return index;
  }
  /* Code from template association_GetOne */
  public SpecificBall getSpecificBall()
  {
    return specificBall;
  }
  /* Code from template association_GetOne */
  public SpecificPaddle getSpecificPaddle()
  {
    return specificPaddle;
  }
  /* Code from template association_GetMany */
  public SpecificBlockAssignment getBlockAssignment(int index)
  {
    SpecificBlockAssignment aBlockAssignment = blockAssignments.get(index);
    return aBlockAssignment;
  }

  public List<SpecificBlockAssignment> getBlockAssignments()
  {
    List<SpecificBlockAssignment> newBlockAssignments = Collections.unmodifiableList(blockAssignments);
    return newBlockAssignments;
  }

  public int numberOfBlockAssignments()
  {
    int number = blockAssignments.size();
    return number;
  }

  public boolean hasBlockAssignments()
  {
    boolean has = blockAssignments.size() > 0;
    return has;
  }

  public int indexOfBlockAssignment(SpecificBlockAssignment aBlockAssignment)
  {
    int index = blockAssignments.indexOf(aBlockAssignment);
    return index;
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfScores()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Score addScore(int aPoints, Game aGame, Player aPlayer)
  {
    return new Score(aPoints, aGame, aPlayer, this);
  }

  public boolean addScore(Score aScore)
  {
    boolean wasAdded = false;
    if (scores.contains(aScore)) { return false; }
    SpecificGame existingSpecificGame = aScore.getSpecificGame();
    boolean isNewSpecificGame = existingSpecificGame != null && !this.equals(existingSpecificGame);
    if (isNewSpecificGame)
    {
      aScore.setSpecificGame(this);
    }
    else
    {
      scores.add(aScore);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeScore(Score aScore)
  {
    boolean wasRemoved = false;
    //Unable to remove aScore, as it must always have a specificGame
    if (!this.equals(aScore.getSpecificGame()))
    {
      scores.remove(aScore);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addScoreAt(Score aScore, int index)
  {  
    boolean wasAdded = false;
    if(addScore(aScore))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfScores()) { index = numberOfScores() - 1; }
      scores.remove(aScore);
      scores.add(index, aScore);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveScoreAt(Score aScore, int index)
  {
    boolean wasAdded = false;
    if(scores.contains(aScore))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfScores()) { index = numberOfScores() - 1; }
      scores.remove(aScore);
      scores.add(index, aScore);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addScoreAt(aScore, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlockAssignments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public SpecificBlockAssignment addBlockAssignment(int aPositionX, int aPositionY, Level aLevel, Block aBlock)
  {
    return new SpecificBlockAssignment(aPositionX, aPositionY, aLevel, aBlock, this);
  }

  public boolean addBlockAssignment(SpecificBlockAssignment aBlockAssignment)
  {
    boolean wasAdded = false;
    if (blockAssignments.contains(aBlockAssignment)) { return false; }
    SpecificGame existingSpecificGame = aBlockAssignment.getSpecificGame();
    boolean isNewSpecificGame = existingSpecificGame != null && !this.equals(existingSpecificGame);
    if (isNewSpecificGame)
    {
      aBlockAssignment.setSpecificGame(this);
    }
    else
    {
      blockAssignments.add(aBlockAssignment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlockAssignment(SpecificBlockAssignment aBlockAssignment)
  {
    boolean wasRemoved = false;
    //Unable to remove aBlockAssignment, as it must always have a specificGame
    if (!this.equals(aBlockAssignment.getSpecificGame()))
    {
      blockAssignments.remove(aBlockAssignment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlockAssignmentAt(SpecificBlockAssignment aBlockAssignment, int index)
  {  
    boolean wasAdded = false;
    if(addBlockAssignment(aBlockAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlockAssignments()) { index = numberOfBlockAssignments() - 1; }
      blockAssignments.remove(aBlockAssignment);
      blockAssignments.add(index, aBlockAssignment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBlockAssignmentAt(SpecificBlockAssignment aBlockAssignment, int index)
  {
    boolean wasAdded = false;
    if(blockAssignments.contains(aBlockAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlockAssignments()) { index = numberOfBlockAssignments() - 1; }
      blockAssignments.remove(aBlockAssignment);
      blockAssignments.add(index, aBlockAssignment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBlockAssignmentAt(aBlockAssignment, index);
    }
    return wasAdded;
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
    for(int i=scores.size(); i > 0; i--)
    {
      Score aScore = scores.get(i - 1);
      aScore.delete();
    }
    SpecificBall existingSpecificBall = specificBall;
    specificBall = null;
    if (existingSpecificBall != null)
    {
      existingSpecificBall.delete();
    }
    SpecificPaddle existingSpecificPaddle = specificPaddle;
    specificPaddle = null;
    if (existingSpecificPaddle != null)
    {
      existingSpecificPaddle.delete();
    }
    while (blockAssignments.size() > 0)
    {
      SpecificBlockAssignment aBlockAssignment = blockAssignments.get(blockAssignments.size() - 1);
      aBlockAssignment.delete();
      blockAssignments.remove(aBlockAssignment);
    }
    
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
  }

  // line 29 "../../../../../Block223PlayGame.ump"
   private boolean isWallPaddleHit(){
    return false;
  }

  // line 32 "../../../../../Block223PlayGame.ump"
   public static  int signum(int a){
    return a<0?-1:1;
  }

  // line 35 "../../../../../Block223PlayGame.ump"
   private void doWallPaddleHit(){
    
  }

  // line 37 "../../../../../Block223PlayGame.ump"
   private boolean paddleIntersecting(){
    return false;
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "nrOfLife" + ":" + getNrOfLife()+ "," +
            "currentLevelPlayed" + ":" + getCurrentLevelPlayed()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "specificBall = "+(getSpecificBall()!=null?Integer.toHexString(System.identityHashCode(getSpecificBall())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "specificPaddle = "+(getSpecificPaddle()!=null?Integer.toHexString(System.identityHashCode(getSpecificPaddle())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null");
  }
}