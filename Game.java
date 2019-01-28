/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 26 "UmpleMakeMeeting1.ump"
public class Game
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, Game> gamesByName = new HashMap<String, Game>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Game Attributes
  private String name;
  private float minPaddleLength;
  private float maxPaddleLength;
  private float speedFactor;
  private int numOfBlocks;
  private int blockLimit;
  private float minBallSpeed;

  //Game Associations
  private GameDesigner gameDesigner;
  private List<GameBlock> blocks;
  private List<Level> levels;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game(String aName, float aMinPaddleLength, float aMaxPaddleLength, float aSpeedFactor, int aNumOfBlocks, int aBlockLimit, float aMinBallSpeed, GameDesigner aGameDesigner)
  {
    minPaddleLength = aMinPaddleLength;
    maxPaddleLength = aMaxPaddleLength;
    speedFactor = aSpeedFactor;
    numOfBlocks = aNumOfBlocks;
    blockLimit = aBlockLimit;
    minBallSpeed = aMinBallSpeed;
    if (!setName(aName))
    {
      throw new RuntimeException("Cannot create due to duplicate name");
    }
    boolean didAddGameDesigner = setGameDesigner(aGameDesigner);
    if (!didAddGameDesigner)
    {
      throw new RuntimeException("Unable to create game due to gameDesigner");
    }
    blocks = new ArrayList<GameBlock>();
    levels = new ArrayList<Level>();
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
      gamesByName.remove(anOldName);
    }
    gamesByName.put(aName, this);
    return wasSet;
  }

  public boolean setMinPaddleLength(float aMinPaddleLength)
  {
    boolean wasSet = false;
    minPaddleLength = aMinPaddleLength;
    wasSet = true;
    return wasSet;
  }

  public boolean setMaxPaddleLength(float aMaxPaddleLength)
  {
    boolean wasSet = false;
    maxPaddleLength = aMaxPaddleLength;
    wasSet = true;
    return wasSet;
  }

  public boolean setSpeedFactor(float aSpeedFactor)
  {
    boolean wasSet = false;
    speedFactor = aSpeedFactor;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumOfBlocks(int aNumOfBlocks)
  {
    boolean wasSet = false;
    numOfBlocks = aNumOfBlocks;
    wasSet = true;
    return wasSet;
  }

  public boolean setBlockLimit(int aBlockLimit)
  {
    boolean wasSet = false;
    blockLimit = aBlockLimit;
    wasSet = true;
    return wasSet;
  }

  public boolean setMinBallSpeed(float aMinBallSpeed)
  {
    boolean wasSet = false;
    minBallSpeed = aMinBallSpeed;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template attribute_GetUnique */
  public static Game getWithName(String aName)
  {
    return gamesByName.get(aName);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithName(String aName)
  {
    return getWithName(aName) != null;
  }

  public float getMinPaddleLength()
  {
    return minPaddleLength;
  }

  public float getMaxPaddleLength()
  {
    return maxPaddleLength;
  }

  public float getSpeedFactor()
  {
    return speedFactor;
  }

  public int getNumOfBlocks()
  {
    return numOfBlocks;
  }

  public int getBlockLimit()
  {
    return blockLimit;
  }

  public float getMinBallSpeed()
  {
    return minBallSpeed;
  }
  /* Code from template association_GetOne */
  public GameDesigner getGameDesigner()
  {
    return gameDesigner;
  }
  /* Code from template association_GetMany */
  public GameBlock getBlock(int index)
  {
    GameBlock aBlock = blocks.get(index);
    return aBlock;
  }

  public List<GameBlock> getBlocks()
  {
    List<GameBlock> newBlocks = Collections.unmodifiableList(blocks);
    return newBlocks;
  }

  public int numberOfBlocks()
  {
    int number = blocks.size();
    return number;
  }

  public boolean hasBlocks()
  {
    boolean has = blocks.size() > 0;
    return has;
  }

  public int indexOfBlock(GameBlock aBlock)
  {
    int index = blocks.indexOf(aBlock);
    return index;
  }
  /* Code from template association_GetMany */
  public Level getLevel(int index)
  {
    Level aLevel = levels.get(index);
    return aLevel;
  }

  public List<Level> getLevels()
  {
    List<Level> newLevels = Collections.unmodifiableList(levels);
    return newLevels;
  }

  public int numberOfLevels()
  {
    int number = levels.size();
    return number;
  }

  public boolean hasLevels()
  {
    boolean has = levels.size() > 0;
    return has;
  }

  public int indexOfLevel(Level aLevel)
  {
    int index = levels.indexOf(aLevel);
    return index;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGameDesigner(GameDesigner aGameDesigner)
  {
    boolean wasSet = false;
    if (aGameDesigner == null)
    {
      return wasSet;
    }

    GameDesigner existingGameDesigner = gameDesigner;
    gameDesigner = aGameDesigner;
    if (existingGameDesigner != null && !existingGameDesigner.equals(aGameDesigner))
    {
      existingGameDesigner.removeGame(this);
    }
    gameDesigner.addGame(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlocks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public GameBlock addBlock(GameBlock.Color aBlockColor, float aBlockPoints, Cell aCell)
  {
    return new GameBlock(aBlockColor, aBlockPoints, this, aCell);
  }

  public boolean addBlock(GameBlock aBlock)
  {
    boolean wasAdded = false;
    if (blocks.contains(aBlock)) { return false; }
    Game existingGame = aBlock.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);
    if (isNewGame)
    {
      aBlock.setGame(this);
    }
    else
    {
      blocks.add(aBlock);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlock(GameBlock aBlock)
  {
    boolean wasRemoved = false;
    //Unable to remove aBlock, as it must always have a game
    if (!this.equals(aBlock.getGame()))
    {
      blocks.remove(aBlock);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlockAt(GameBlock aBlock, int index)
  {  
    boolean wasAdded = false;
    if(addBlock(aBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlocks()) { index = numberOfBlocks() - 1; }
      blocks.remove(aBlock);
      blocks.add(index, aBlock);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBlockAt(GameBlock aBlock, int index)
  {
    boolean wasAdded = false;
    if(blocks.contains(aBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlocks()) { index = numberOfBlocks() - 1; }
      blocks.remove(aBlock);
      blocks.add(index, aBlock);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBlockAt(aBlock, index);
    }
    return wasAdded;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfLevelsValid()
  {
    boolean isValid = numberOfLevels() >= minimumNumberOfLevels() && numberOfLevels() <= maximumNumberOfLevels();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLevels()
  {
    return 1;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfLevels()
  {
    return 99;
  }
  /* Code from template association_AddMNToOnlyOne */
  public Level addLevel(int aLevelNumber)
  {
    if (numberOfLevels() >= maximumNumberOfLevels())
    {
      return null;
    }
    else
    {
      return new Level(aLevelNumber, this);
    }
  }

  public boolean addLevel(Level aLevel)
  {
    boolean wasAdded = false;
    if (levels.contains(aLevel)) { return false; }
    if (numberOfLevels() >= maximumNumberOfLevels())
    {
      return wasAdded;
    }

    Game existingGame = aLevel.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);

    if (isNewGame && existingGame.numberOfLevels() <= minimumNumberOfLevels())
    {
      return wasAdded;
    }

    if (isNewGame)
    {
      aLevel.setGame(this);
    }
    else
    {
      levels.add(aLevel);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLevel(Level aLevel)
  {
    boolean wasRemoved = false;
    //Unable to remove aLevel, as it must always have a game
    if (this.equals(aLevel.getGame()))
    {
      return wasRemoved;
    }

    //game already at minimum (1)
    if (numberOfLevels() <= minimumNumberOfLevels())
    {
      return wasRemoved;
    }
    levels.remove(aLevel);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLevelAt(Level aLevel, int index)
  {  
    boolean wasAdded = false;
    if(addLevel(aLevel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLevels()) { index = numberOfLevels() - 1; }
      levels.remove(aLevel);
      levels.add(index, aLevel);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLevelAt(Level aLevel, int index)
  {
    boolean wasAdded = false;
    if(levels.contains(aLevel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLevels()) { index = numberOfLevels() - 1; }
      levels.remove(aLevel);
      levels.add(index, aLevel);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLevelAt(aLevel, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    gamesByName.remove(getName());
    GameDesigner placeholderGameDesigner = gameDesigner;
    this.gameDesigner = null;
    if(placeholderGameDesigner != null)
    {
      placeholderGameDesigner.removeGame(this);
    }
    while (blocks.size() > 0)
    {
      GameBlock aBlock = blocks.get(blocks.size() - 1);
      aBlock.delete();
      blocks.remove(aBlock);
    }
    
    while (levels.size() > 0)
    {
      Level aLevel = levels.get(levels.size() - 1);
      aLevel.delete();
      levels.remove(aLevel);
    }
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "minPaddleLength" + ":" + getMinPaddleLength()+ "," +
            "maxPaddleLength" + ":" + getMaxPaddleLength()+ "," +
            "speedFactor" + ":" + getSpeedFactor()+ "," +
            "numOfBlocks" + ":" + getNumOfBlocks()+ "," +
            "blockLimit" + ":" + getBlockLimit()+ "," +
            "minBallSpeed" + ":" + getMinBallSpeed()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "gameDesigner = "+(getGameDesigner()!=null?Integer.toHexString(System.identityHashCode(getGameDesigner())):"null");
  }
}