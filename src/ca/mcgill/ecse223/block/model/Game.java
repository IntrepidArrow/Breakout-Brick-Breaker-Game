/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;
import java.util.*;

// line 45 "../../../../../Block223Persistence.ump"
// line 3 "../../../../../Block223PlayGame.ump"
// line 56 "../../../../../Block223.ump"
public class Game implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  public static final int MIN_NR_LEVELS = 1;

  /**
   * this is somewhat redundant because the max multiplicity is enforced by Umple
   */
  public static final int MAX_NR_LEVELS = 99;

  /**
   * play area is now constant
   */
  public static final int PLAY_AREA_SIDE = 390;
  public static final int WALL_PADDING = 10;
  public static final int COLUMNS_PADDING = 5;
  public static final int ROW_PADDING = 2;
  private static Map<String, Game> gamesByName = new HashMap<String, Game>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Game Attributes
  private boolean published;
  private String name;
  private int nrBlocksPerLevel;

  //Game Associations
  private List<SpecificGame> specificGames;
  private List<Score> gameScores;
  private Admin admin;
  private List<Block> blocks;
  private List<Level> levels;
  private List<BlockAssignment> blockAssignments;
  private Ball ball;
  private Paddle paddle;
  private Block223 block223;

  //Helper Variables
  private boolean canSetPublished;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game(String aName, int aNrBlocksPerLevel, Admin aAdmin, Ball aBall, Paddle aPaddle, Block223 aBlock223)
  {
    // line 83 "../../../../../Block223.ump"
    if(aNrBlocksPerLevel <= 0){
      		throw new RuntimeException("The number of blocks per level must be greater than zero.");
      		}
    // END OF UMPLE BEFORE INJECTION
    canSetPublished = true;
    nrBlocksPerLevel = aNrBlocksPerLevel;
    if (!setName(aName))
    {
      throw new RuntimeException("Cannot create due to duplicate name");
    }
    specificGames = new ArrayList<SpecificGame>();
    gameScores = new ArrayList<Score>();
    boolean didAddAdmin = setAdmin(aAdmin);
    if (!didAddAdmin)
    {
      throw new RuntimeException("Unable to create game due to admin");
    }
    blocks = new ArrayList<Block>();
    levels = new ArrayList<Level>();
    blockAssignments = new ArrayList<BlockAssignment>();
    if (aBall == null || aBall.getGame() != null)
    {
      throw new RuntimeException("Unable to create Game due to aBall");
    }
    ball = aBall;
    if (aPaddle == null || aPaddle.getGame() != null)
    {
      throw new RuntimeException("Unable to create Game due to aPaddle");
    }
    paddle = aPaddle;
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create game due to block223");
    }
  }

  public Game(String aName, int aNrBlocksPerLevel, Admin aAdmin, int aMinBallSpeedXForBall, int aMinBallSpeedYForBall, double aBallSpeedIncreaseFactorForBall, int aMaxPaddleLengthForPaddle, int aMinPaddleLengthForPaddle, Block223 aBlock223)
  {
    // line 83 "../../../../../Block223.ump"
    if(aNrBlocksPerLevel <= 0){
      		throw new RuntimeException("The number of blocks per level must be greater than zero.");
      		}
    // END OF UMPLE BEFORE INJECTION
    published = false;
    name = aName;
    nrBlocksPerLevel = aNrBlocksPerLevel;
    specificGames = new ArrayList<SpecificGame>();
    gameScores = new ArrayList<Score>();
    boolean didAddAdmin = setAdmin(aAdmin);
    if (!didAddAdmin)
    {
      throw new RuntimeException("Unable to create game due to admin");
    }
    blocks = new ArrayList<Block>();
    levels = new ArrayList<Level>();
    blockAssignments = new ArrayList<BlockAssignment>();
    ball = new Ball(aMinBallSpeedXForBall, aMinBallSpeedYForBall, aBallSpeedIncreaseFactorForBall, this);
    paddle = new Paddle(aMaxPaddleLengthForPaddle, aMinPaddleLengthForPaddle, this);
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create game due to block223");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template attribute_SetImmutable */
  public boolean setPublished(boolean aPublished)
  {
    boolean wasSet = false;
    if (!canSetPublished) { return false; }
    canSetPublished = false;
    published = aPublished;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    // line 88 "../../../../../Block223.ump"
    if(aName == null){
      		throw new RuntimeException("The name of a ame must be specified.");
      	}
    // END OF UMPLE BEFORE INJECTION
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

  public boolean setNrBlocksPerLevel(int aNrBlocksPerLevel)
  {
    boolean wasSet = false;
    // line 83 "../../../../../Block223.ump"
    if(aNrBlocksPerLevel <= 0){
      		throw new RuntimeException("The number of blocks per level must be greater than zero.");
      		}
    // END OF UMPLE BEFORE INJECTION
    nrBlocksPerLevel = aNrBlocksPerLevel;
    wasSet = true;
    return wasSet;
  }

  public boolean getPublished()
  {
    return published;
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

  public int getNrBlocksPerLevel()
  {
    return nrBlocksPerLevel;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isPublished()
  {
    return published;
  }
  /* Code from template association_GetMany */
  public SpecificGame getSpecificGame(int index)
  {
    SpecificGame aSpecificGame = specificGames.get(index);
    return aSpecificGame;
  }

  public List<SpecificGame> getSpecificGames()
  {
    List<SpecificGame> newSpecificGames = Collections.unmodifiableList(specificGames);
    return newSpecificGames;
  }

  public int numberOfSpecificGames()
  {
    int number = specificGames.size();
    return number;
  }

  public boolean hasSpecificGames()
  {
    boolean has = specificGames.size() > 0;
    return has;
  }

  public int indexOfSpecificGame(SpecificGame aSpecificGame)
  {
    int index = specificGames.indexOf(aSpecificGame);
    return index;
  }
  /* Code from template association_GetMany */
  public Score getGameScore(int index)
  {
    Score aGameScore = gameScores.get(index);
    return aGameScore;
  }

  /**
   * After game is over it is deleted. Only it's scores need to be saved for future games
   */
  public List<Score> getGameScores()
  {
    List<Score> newGameScores = Collections.unmodifiableList(gameScores);
    return newGameScores;
  }

  public int numberOfGameScores()
  {
    int number = gameScores.size();
    return number;
  }

  public boolean hasGameScores()
  {
    boolean has = gameScores.size() > 0;
    return has;
  }

  public int indexOfGameScore(Score aGameScore)
  {
    int index = gameScores.indexOf(aGameScore);
    return index;
  }
  /* Code from template association_GetOne */
  public Admin getAdmin()
  {
    return admin;
  }
  /* Code from template association_GetMany */
  public Block getBlock(int index)
  {
    Block aBlock = blocks.get(index);
    return aBlock;
  }

  public List<Block> getBlocks()
  {
    List<Block> newBlocks = Collections.unmodifiableList(blocks);
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

  public int indexOfBlock(Block aBlock)
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
  /* Code from template association_GetMany */
  public BlockAssignment getBlockAssignment(int index)
  {
    BlockAssignment aBlockAssignment = blockAssignments.get(index);
    return aBlockAssignment;
  }

  public List<BlockAssignment> getBlockAssignments()
  {
    List<BlockAssignment> newBlockAssignments = Collections.unmodifiableList(blockAssignments);
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

  public int indexOfBlockAssignment(BlockAssignment aBlockAssignment)
  {
    int index = blockAssignments.indexOf(aBlockAssignment);
    return index;
  }
  /* Code from template association_GetOne */
  public Ball getBall()
  {
    return ball;
  }
  /* Code from template association_GetOne */
  public Paddle getPaddle()
  {
    return paddle;
  }
  /* Code from template association_GetOne */
  public Block223 getBlock223()
  {
    return block223;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSpecificGames()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public SpecificGame addSpecificGame(Player aPlayer)
  {
    return new SpecificGame(this, aPlayer);
  }

  public boolean addSpecificGame(SpecificGame aSpecificGame)
  {
    boolean wasAdded = false;
    if (specificGames.contains(aSpecificGame)) { return false; }
    Game existingGame = aSpecificGame.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);
    if (isNewGame)
    {
      aSpecificGame.setGame(this);
    }
    else
    {
      specificGames.add(aSpecificGame);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSpecificGame(SpecificGame aSpecificGame)
  {
    boolean wasRemoved = false;
    //Unable to remove aSpecificGame, as it must always have a game
    if (!this.equals(aSpecificGame.getGame()))
    {
      specificGames.remove(aSpecificGame);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSpecificGameAt(SpecificGame aSpecificGame, int index)
  {  
    boolean wasAdded = false;
    if(addSpecificGame(aSpecificGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecificGames()) { index = numberOfSpecificGames() - 1; }
      specificGames.remove(aSpecificGame);
      specificGames.add(index, aSpecificGame);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSpecificGameAt(SpecificGame aSpecificGame, int index)
  {
    boolean wasAdded = false;
    if(specificGames.contains(aSpecificGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecificGames()) { index = numberOfSpecificGames() - 1; }
      specificGames.remove(aSpecificGame);
      specificGames.add(index, aSpecificGame);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSpecificGameAt(aSpecificGame, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGameScores()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Score addGameScore(int aPoints, Player aPlayer, SpecificGame aSpecificGame)
  {
    return new Score(aPoints, this, aPlayer, aSpecificGame);
  }

  public boolean addGameScore(Score aGameScore)
  {
    boolean wasAdded = false;
    if (gameScores.contains(aGameScore)) { return false; }
    Game existingGame = aGameScore.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);
    if (isNewGame)
    {
      aGameScore.setGame(this);
    }
    else
    {
      gameScores.add(aGameScore);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGameScore(Score aGameScore)
  {
    boolean wasRemoved = false;
    //Unable to remove aGameScore, as it must always have a game
    if (!this.equals(aGameScore.getGame()))
    {
      gameScores.remove(aGameScore);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGameScoreAt(Score aGameScore, int index)
  {  
    boolean wasAdded = false;
    if(addGameScore(aGameScore))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGameScores()) { index = numberOfGameScores() - 1; }
      gameScores.remove(aGameScore);
      gameScores.add(index, aGameScore);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGameScoreAt(Score aGameScore, int index)
  {
    boolean wasAdded = false;
    if(gameScores.contains(aGameScore))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGameScores()) { index = numberOfGameScores() - 1; }
      gameScores.remove(aGameScore);
      gameScores.add(index, aGameScore);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGameScoreAt(aGameScore, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setAdmin(Admin aAdmin)
  {
    boolean wasSet = false;
    if (aAdmin == null)
    {
      return wasSet;
    }

    Admin existingAdmin = admin;
    admin = aAdmin;
    if (existingAdmin != null && !existingAdmin.equals(aAdmin))
    {
      existingAdmin.removeGame(this);
    }
    admin.addGame(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlocks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Block addBlock(int aRed, int aGreen, int aBlue, int aPoints)
  {
    return new Block(aRed, aGreen, aBlue, aPoints, this);
  }

  public boolean addBlock(Block aBlock)
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

  public boolean removeBlock(Block aBlock)
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
  public boolean addBlockAt(Block aBlock, int index)
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

  public boolean addOrMoveBlockAt(Block aBlock, int index)
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
  public Level addLevel()
  {
    if (numberOfLevels() >= maximumNumberOfLevels())
    {
      return null;
    }
    else
    {
      return new Level(this);
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlockAssignments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public BlockAssignment addBlockAssignment(int aGridHorizontalPosition, int aGridVerticalPosition, Level aLevel, Block aBlock)
  {
    return new BlockAssignment(aGridHorizontalPosition, aGridVerticalPosition, aLevel, aBlock, this);
  }

  public boolean addBlockAssignment(BlockAssignment aBlockAssignment)
  {
    boolean wasAdded = false;
    if (blockAssignments.contains(aBlockAssignment)) { return false; }
    Game existingGame = aBlockAssignment.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);
    if (isNewGame)
    {
      aBlockAssignment.setGame(this);
    }
    else
    {
      blockAssignments.add(aBlockAssignment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlockAssignment(BlockAssignment aBlockAssignment)
  {
    boolean wasRemoved = false;
    //Unable to remove aBlockAssignment, as it must always have a game
    if (!this.equals(aBlockAssignment.getGame()))
    {
      blockAssignments.remove(aBlockAssignment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlockAssignmentAt(BlockAssignment aBlockAssignment, int index)
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

  public boolean addOrMoveBlockAssignmentAt(BlockAssignment aBlockAssignment, int index)
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
  public boolean setBlock223(Block223 aBlock223)
  {
    boolean wasSet = false;
    if (aBlock223 == null)
    {
      return wasSet;
    }

    Block223 existingBlock223 = block223;
    block223 = aBlock223;
    if (existingBlock223 != null && !existingBlock223.equals(aBlock223))
    {
      existingBlock223.removeGame(this);
    }
    block223.addGame(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    gamesByName.remove(getName());
    while (specificGames.size() > 0)
    {
      SpecificGame aSpecificGame = specificGames.get(specificGames.size() - 1);
      aSpecificGame.delete();
      specificGames.remove(aSpecificGame);
    }
    
    for(int i=gameScores.size(); i > 0; i--)
    {
      Score aGameScore = gameScores.get(i - 1);
      aGameScore.delete();
    }
    Admin placeholderAdmin = admin;
    this.admin = null;
    if(placeholderAdmin != null)
    {
      placeholderAdmin.removeGame(this);
    }
    while (blocks.size() > 0)
    {
      Block aBlock = blocks.get(blocks.size() - 1);
      aBlock.delete();
      blocks.remove(aBlock);
    }
    
    while (levels.size() > 0)
    {
      Level aLevel = levels.get(levels.size() - 1);
      aLevel.delete();
      levels.remove(aLevel);
    }
    
    while (blockAssignments.size() > 0)
    {
      BlockAssignment aBlockAssignment = blockAssignments.get(blockAssignments.size() - 1);
      aBlockAssignment.delete();
      blockAssignments.remove(aBlockAssignment);
    }
    
    Ball existingBall = ball;
    ball = null;
    if (existingBall != null)
    {
      existingBall.delete();
    }
    Paddle existingPaddle = paddle;
    paddle = null;
    if (existingPaddle != null)
    {
      existingPaddle.delete();
    }
    Block223 placeholderBlock223 = block223;
    this.block223 = null;
    if(placeholderBlock223 != null)
    {
      placeholderBlock223.removeGame(this);
    }
  }

  // line 51 "../../../../../Block223Persistence.ump"
   public static  void reinitializeUniqueName(List<Game> games){
    Map<String, Game> gamesByName = new HashMap<String, Game>();
    for (Game g : games) {
      gamesByName.put(g.getName(), g);
    }
  }

  // line 72 "../../../../../Block223.ump"
   public Block findBlock(int id){
    Block foundBlock = null;
	  List<Block> blocks = this.getBlocks();
	  for(Block block : blocks) {
		  if(block.getId() == id) {
			  foundBlock = block;
		  }
	  }
	  return foundBlock;
  }

  // line 94 "../../../../../Block223.ump"
   public int getMaxNumberHorizontalBlocks(){
    int maxNbr = Math.floorDiv( PLAY_AREA_SIDE - 2*WALL_PADDING , (Block.SIZE + COLUMNS_PADDING) );
	  return maxNbr;
  }

  // line 98 "../../../../../Block223.ump"
   public int getMaxNumberVerticalBlocks(){
    int maxNbr = Math.floorDiv( PLAY_AREA_SIDE - WALL_PADDING - Paddle.VERTICAL_DISTANCE - Paddle.PADDLE_WIDTH - Ball.BALL_DIAMETER , (Block.SIZE + ROW_PADDING) ); 
	  return maxNbr;
  }


  public String toString()
  {
    return super.toString() + "["+
            "published" + ":" + getPublished()+ "," +
            "name" + ":" + getName()+ "," +
            "nrBlocksPerLevel" + ":" + getNrBlocksPerLevel()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "admin = "+(getAdmin()!=null?Integer.toHexString(System.identityHashCode(getAdmin())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "ball = "+(getBall()!=null?Integer.toHexString(System.identityHashCode(getBall())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "paddle = "+(getPaddle()!=null?Integer.toHexString(System.identityHashCode(getPaddle())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "block223 = "+(getBlock223()!=null?Integer.toHexString(System.identityHashCode(getBlock223())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 48 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = -2687593616927799864L ;

  
}