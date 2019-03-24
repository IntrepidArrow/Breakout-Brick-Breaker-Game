/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;
import java.awt.geom.*;
import java.util.*;

// line 101 "../../../../../Block223Persistence.ump"
// line 25 "../../../../../Block223PlayMode.ump"
// line 1 "../../../../../Block223States.ump"
public class PlayedGame implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------


  /**
   * at design time, the initial wait time may be adjusted as seen fit
   */
  public static final int INITIAL_WAIT_TIME = 1000;
  private static int nextId = 1;
  public static final int NR_LIVES = 3;

  /**
   * the PlayedBall and PlayedPaddle are not in a separate class to avoid the bug in Umple that occurred for the second constructor of Game
   * no direct link to Ball, because the ball can be found by navigating to PlayedGame, Game, and then Ball
   */
  public static final int BALL_INITIAL_X = Game.PLAY_AREA_SIDE / 2;
  public static final int BALL_INITIAL_Y = Game.PLAY_AREA_SIDE / 2;

  /**
   * no direct link to Paddle, because the paddle can be found by navigating to PlayedGame, Game, and then Paddle
   * pixels moved when right arrow key is pressed
   */
  public static final int PADDLE_MOVE_RIGHT = 1;

  /**
   * pixels moved when left arrow key is pressed
   */
  public static final int PADDLE_MOVE_LEFT = -1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PlayedGame Attributes
  private int score;
  private int lives;
  private int currentLevel;
  private double waitTime;
  private String playername;
  private double ballDirectionX;
  private double ballDirectionY;
  private double currentBallX;
  private double currentBallY;
  private double currentPaddleLength;
  private double currentPaddleX;
  private double currentPaddleY;

  //Autounique Attributes
  private int id;

  //PlayedGame State Machines
  public enum PlayStatus { Ready, Moving, Paused, GameOver }
  private PlayStatus playStatus;

  //PlayedGame Associations
  private Player player;
  private Game game;
  private List<PlayedBlockAssignment> blocks;
  private BouncePoint bounce;
  private Block223 block223;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PlayedGame(String aPlayername, Game aGame, Block223 aBlock223)
  {
    // line 208 "../../../../../Block223PlayMode.ump"
    boolean didAddGameResult = setGame(aGame);
          if (!didAddGameResult)
          {
             throw new RuntimeException("Unable to create playedGame due to game");
          }
    // END OF UMPLE BEFORE INJECTION
    score = 0;
    lives = NR_LIVES;
    currentLevel = 1;
    waitTime = INITIAL_WAIT_TIME;
    playername = aPlayername;
    resetBallDirectionX();
    resetBallDirectionY();
    resetCurrentBallX();
    resetCurrentBallY();
    currentPaddleLength = getGame().getPaddle().getMaxPaddleLength();
    resetCurrentPaddleX();
    currentPaddleY = Game.PLAY_AREA_SIDE - Paddle.VERTICAL_DISTANCE - Paddle.PADDLE_WIDTH;
    id = nextId++;
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create playedGame due to game");
    }
    blocks = new ArrayList<PlayedBlockAssignment>();
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create playedGame due to block223");
    }
    setPlayStatus(PlayStatus.Ready);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setScore(int aScore)
  {
    boolean wasSet = false;
    score = aScore;
    wasSet = true;
    return wasSet;
  }

  public boolean setLives(int aLives)
  {
    boolean wasSet = false;
    lives = aLives;
    wasSet = true;
    return wasSet;
  }

  public boolean setCurrentLevel(int aCurrentLevel)
  {
    boolean wasSet = false;
    currentLevel = aCurrentLevel;
    wasSet = true;
    return wasSet;
  }

  public boolean setWaitTime(double aWaitTime)
  {
    boolean wasSet = false;
    waitTime = aWaitTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setPlayername(String aPlayername)
  {
    boolean wasSet = false;
    playername = aPlayername;
    wasSet = true;
    return wasSet;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setBallDirectionX(double aBallDirectionX)
  {
    boolean wasSet = false;
    ballDirectionX = aBallDirectionX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetBallDirectionX()
  {
    boolean wasReset = false;
    ballDirectionX = getDefaultBallDirectionX();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setBallDirectionY(double aBallDirectionY)
  {
    boolean wasSet = false;
    ballDirectionY = aBallDirectionY;
    wasSet = true;
    return wasSet;
  }

  public boolean resetBallDirectionY()
  {
    boolean wasReset = false;
    ballDirectionY = getDefaultBallDirectionY();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setCurrentBallX(double aCurrentBallX)
  {
    boolean wasSet = false;
    currentBallX = aCurrentBallX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetCurrentBallX()
  {
    boolean wasReset = false;
    currentBallX = getDefaultCurrentBallX();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setCurrentBallY(double aCurrentBallY)
  {
    boolean wasSet = false;
    currentBallY = aCurrentBallY;
    wasSet = true;
    return wasSet;
  }

  public boolean resetCurrentBallY()
  {
    boolean wasReset = false;
    currentBallY = getDefaultCurrentBallY();
    wasReset = true;
    return wasReset;
  }

  public boolean setCurrentPaddleLength(double aCurrentPaddleLength)
  {
    boolean wasSet = false;
    currentPaddleLength = aCurrentPaddleLength;
    wasSet = true;
    return wasSet;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setCurrentPaddleX(double aCurrentPaddleX)
  {
    boolean wasSet = false;
    currentPaddleX = aCurrentPaddleX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetCurrentPaddleX()
  {
    boolean wasReset = false;
    currentPaddleX = getDefaultCurrentPaddleX();
    wasReset = true;
    return wasReset;
  }

  public int getScore()
  {
    return score;
  }

  public int getLives()
  {
    return lives;
  }

  public int getCurrentLevel()
  {
    return currentLevel;
  }

  public double getWaitTime()
  {
    return waitTime;
  }

  /**
   * added here so that it only needs to be determined once
   */
  public String getPlayername()
  {
    return playername;
  }

  /**
   * 0/0 is the top left corner of the play area, i.e., a directionX/Y of 0/1 moves the ball down in a straight line
   */
  public double getBallDirectionX()
  {
    return ballDirectionX;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultBallDirectionX()
  {
    return getGame().getBall().getMinBallSpeedX();
  }

  public double getBallDirectionY()
  {
    return ballDirectionY;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultBallDirectionY()
  {
    return getGame().getBall().getMinBallSpeedY();
  }

  /**
   * the position of the ball is at the center of the ball
   */
  public double getCurrentBallX()
  {
    return currentBallX;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultCurrentBallX()
  {
    return BALL_INITIAL_X;
  }

  public double getCurrentBallY()
  {
    return currentBallY;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultCurrentBallY()
  {
    return BALL_INITIAL_Y;
  }

  public double getCurrentPaddleLength()
  {
    return currentPaddleLength;
  }

  /**
   * the position of the paddle is at its top right corner
   */
  public double getCurrentPaddleX()
  {
    return currentPaddleX;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultCurrentPaddleX()
  {
    return (Game.PLAY_AREA_SIDE - currentPaddleLength) / 2;
  }

  public double getCurrentPaddleY()
  {
    return currentPaddleY;
  }

  public int getId()
  {
    return id;
  }

  public String getPlayStatusFullName()
  {
    String answer = playStatus.toString();
    return answer;
  }

  public PlayStatus getPlayStatus()
  {
    return playStatus;
  }

  public boolean play()
  {
    boolean wasEventProcessed = false;
    
    PlayStatus aPlayStatus = playStatus;
    switch (aPlayStatus)
    {
      case Ready:
        setPlayStatus(PlayStatus.Moving);
        wasEventProcessed = true;
        break;
      case Paused:
        setPlayStatus(PlayStatus.Moving);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean pause()
  {
    boolean wasEventProcessed = false;
    
    PlayStatus aPlayStatus = playStatus;
    switch (aPlayStatus)
    {
      case Moving:
        setPlayStatus(PlayStatus.Paused);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean move()
  {
    boolean wasEventProcessed = false;
    
    PlayStatus aPlayStatus = playStatus;
    switch (aPlayStatus)
    {
      case Moving:
        if (hitPaddle())
        {
        // line 12 "../../../../../Block223States.ump"
          doHitPaddleOrWall();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBoundsAndLastLife())
        {
        // line 13 "../../../../../Block223States.ump"
          doOutOfBounds();
          setPlayStatus(PlayStatus.GameOver);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBounds())
        {
        // line 14 "../../../../../Block223States.ump"
          doOutOfBounds();
          setPlayStatus(PlayStatus.Paused);
          wasEventProcessed = true;
          break;
        }
        if (hitLastBlockAndLastLevel())
        {
        // line 15 "../../../../../Block223States.ump"
          doHitBlock();
          setPlayStatus(PlayStatus.GameOver);
          wasEventProcessed = true;
          break;
        }
        if (hitLastBlock())
        {
        // line 16 "../../../../../Block223States.ump"
          doHitBlockNextLevel();
          setPlayStatus(PlayStatus.Ready);
          wasEventProcessed = true;
          break;
        }
        if (hitBlock())
        {
        // line 17 "../../../../../Block223States.ump"
          doHitBlock();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        if (hitWall())
        {
        // line 18 "../../../../../Block223States.ump"
          doHitPaddleOrWall();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        // line 19 "../../../../../Block223States.ump"
        doHitNothingAndNotOutOfBounds();
        setPlayStatus(PlayStatus.Moving);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setPlayStatus(PlayStatus aPlayStatus)
  {
    playStatus = aPlayStatus;

    // entry actions and do activities
    switch(playStatus)
    {
      case Ready:
        // line 7 "../../../../../Block223States.ump"
        doSetup();
        break;
      case GameOver:
        // line 25 "../../../../../Block223States.ump"
        doGameOver();
        break;
    }
  }
  /* Code from template association_GetOne */
  public Player getPlayer()
  {
    return player;
  }

  public boolean hasPlayer()
  {
    boolean has = player != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_GetMany */
  public PlayedBlockAssignment getBlock(int index)
  {
    PlayedBlockAssignment aBlock = blocks.get(index);
    return aBlock;
  }

  public List<PlayedBlockAssignment> getBlocks()
  {
    List<PlayedBlockAssignment> newBlocks = Collections.unmodifiableList(blocks);
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

  public int indexOfBlock(PlayedBlockAssignment aBlock)
  {
    int index = blocks.indexOf(aBlock);
    return index;
  }
  /* Code from template association_GetOne */
  public BouncePoint getBounce()
  {
    return bounce;
  }

  public boolean hasBounce()
  {
    boolean has = bounce != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Block223 getBlock223()
  {
    return block223;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setPlayer(Player aPlayer)
  {
    boolean wasSet = false;
    Player existingPlayer = player;
    player = aPlayer;
    if (existingPlayer != null && !existingPlayer.equals(aPlayer))
    {
      existingPlayer.removePlayedGame(this);
    }
    if (aPlayer != null)
    {
      aPlayer.addPlayedGame(this);
    }
    wasSet = true;
    return wasSet;
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
      existingGame.removePlayedGame(this);
    }
    game.addPlayedGame(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlocks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public PlayedBlockAssignment addBlock(int aX, int aY, Block aBlock)
  {
    return new PlayedBlockAssignment(aX, aY, aBlock, this);
  }

  public boolean addBlock(PlayedBlockAssignment aBlock)
  {
    boolean wasAdded = false;
    if (blocks.contains(aBlock)) { return false; }
    PlayedGame existingPlayedGame = aBlock.getPlayedGame();
    boolean isNewPlayedGame = existingPlayedGame != null && !this.equals(existingPlayedGame);
    if (isNewPlayedGame)
    {
      aBlock.setPlayedGame(this);
    }
    else
    {
      blocks.add(aBlock);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlock(PlayedBlockAssignment aBlock)
  {
    boolean wasRemoved = false;
    //Unable to remove aBlock, as it must always have a playedGame
    if (!this.equals(aBlock.getPlayedGame()))
    {
      blocks.remove(aBlock);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlockAt(PlayedBlockAssignment aBlock, int index)
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

  public boolean addOrMoveBlockAt(PlayedBlockAssignment aBlock, int index)
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
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setBounce(BouncePoint aNewBounce)
  {
    boolean wasSet = false;
    bounce = aNewBounce;
    wasSet = true;
    return wasSet;
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
      existingBlock223.removePlayedGame(this);
    }
    block223.addPlayedGame(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    if (player != null)
    {
      Player placeholderPlayer = player;
      this.player = null;
      placeholderPlayer.removePlayedGame(this);
    }
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removePlayedGame(this);
    }
    while (blocks.size() > 0)
    {
      PlayedBlockAssignment aBlock = blocks.get(blocks.size() - 1);
      aBlock.delete();
      blocks.remove(aBlock);
    }
    
    bounce = null;
    Block223 placeholderBlock223 = block223;
    this.block223 = null;
    if(placeholderBlock223 != null)
    {
      placeholderBlock223.removePlayedGame(this);
    }
  }

  // line 63 "../../../../../Block223PlayMode.ump"
   private BouncePoint calculateBouncePointWall(){
    //TODO
		return null;
  }

  // line 68 "../../../../../Block223PlayMode.ump"
   private BouncePoint calculateBouncePointPaddle(){
    ArrayList<BouncePoint> allIntersections = new ArrayList<BouncePoint>();
		
		Rectangle2D paddle = new Rectangle2D.Double(getCurrentPaddleX(),getCurrentPaddleY(),getCurrentPaddleLength(),Paddle.PADDLE_WIDTH);
		
		Line2D path = new Line2D.Double(getCurrentBallX(), getCurrentBallY(), getBallDirectionX(), getBallDirectionY());
		
		if(paddle.intersectsLine(path)) {
			return null;
		}
		
		Line2D sectA = new Line2D.Double(
				getCurrentPaddleX(),getCurrentPaddleY() - Ball.BALL_DIAMETER,
				getCurrentPaddleX() + getCurrentPaddleLength(), getCurrentPaddleY() - Ball.BALL_DIAMETER);
		
		Line2D sectB = new Line2D.Double(
				getCurrentPaddleX() - Ball.BALL_DIAMETER,getCurrentPaddleY(),
				getCurrentPaddleX() - Ball.BALL_DIAMETER, getCurrentPaddleY() + Paddle.PADDLE_WIDTH);
		
		Line2D sectC = new Line2D.Double(
				getCurrentPaddleX() + getCurrentPaddleLength() + Ball.BALL_DIAMETER,getCurrentPaddleY(),
				getCurrentPaddleX() + getCurrentPaddleLength() + Ball.BALL_DIAMETER, getCurrentPaddleY() + Paddle.PADDLE_WIDTH);
		
		//intersect the ball path line with sectors A,B,C and add computed intersections to list of intersected lines
		Point2D interA = intersectLine(sectA, path);
		if(interA != null) {
			allIntersections.add(new BouncePoint(interA.getX(),interA.getY(),BouncePoint.BounceDirection.FLIP_Y));
		}
		Point2D interB = intersectLine(sectB, path);
		if(interB != null) {
			allIntersections.add( new BouncePoint(interB.getX(),interB.getY(),BouncePoint.BounceDirection.FLIP_X));
		}
		Point2D interC = intersectLine(sectC, path);
		if(interC != null) {
			allIntersections.add( new BouncePoint(interC.getX(),interC.getY(),BouncePoint.BounceDirection.FLIP_X));
		}
		
		//intersect the ball path line with sectors E,F and add computed intersections to list of intersected lines

		Point2D interLeftE = intersectArc(getCurrentPaddleX(),getCurrentPaddleY(),Paddle.PADDLE_WIDTH,Math.PI*0.75,Math.PI,path,10);
		if(interLeftE != null) {
			allIntersections.add( new BouncePoint(interLeftE.getX(),interLeftE.getY(),BouncePoint.BounceDirection.FLIP_X));
		}
		Point2D interRightE = intersectArc(getCurrentPaddleX(),getCurrentPaddleY(),Paddle.PADDLE_WIDTH,Math.PI*0.5,Math.PI*0.75,path,10);
		if(interRightE != null) {
			allIntersections.add( new BouncePoint(interRightE.getX(),interRightE.getY(),BouncePoint.BounceDirection.FLIP_Y));
		}
		
		Point2D interLeftF = intersectArc(getCurrentPaddleX()+getCurrentPaddleLength(),getCurrentPaddleY(),Paddle.PADDLE_WIDTH,0.0,Math.PI*0.25,path,10);
		if(interLeftF != null) {
			allIntersections.add( new BouncePoint(interLeftF.getX(),interLeftF.getY(),BouncePoint.BounceDirection.FLIP_Y));
		}
		Point2D interRightF = intersectArc(getCurrentPaddleX()+getCurrentPaddleLength(),getCurrentPaddleY(),Paddle.PADDLE_WIDTH,Math.PI*0.25,Math.PI*0.5,path,10);
		if(interRightF != null) {
			allIntersections.add( new BouncePoint(interRightF.getX(),interRightF.getY(),BouncePoint.BounceDirection.FLIP_X));
		}
		
		//return the nearest intersection
		if(allIntersections.isEmpty()) {
			return null;
		}else {
			double dist;
			int minIndex = 0;
			double min= Double.MAX_VALUE;
			for(int i=0;i<allIntersections.size(); i++) {
				dist=distance(allIntersections.get(i).getX(),allIntersections.get(i).getY(),getCurrentBallX(),getCurrentBallY());
				if(dist<min) {
					min=dist;
					minIndex=i;
				}
			}
			return allIntersections.get(minIndex);
		}
  }


  /**
   * find euclidian distance squared
   */
  // line 149 "../../../../../Block223PlayMode.ump"
   private double distance(double x1, double y1, double x2, double y2){
    return Math.pow(x2-x1, 2) +Math.pow(y2-y1, 2);
  }


  /**
   * intersect line with an arc by dividing the arc into smaller segments which are then checked for intersection using intersectLine
   * higher divisions means greater intersection accuracy
   * parameters:
   * aX,aY center of circle of radius aRadius
   * aAngleStart and aAngleEnd define the location of the arc on the circle, angles in radians going from 0 to 2pi counterclockwise
   * l1 should be the ball path line
   * divisions is the number of segments the arc is divided into, recommend using less than 10 since the arc is very small anyways
   */
  // line 160 "../../../../../Block223PlayMode.ump"
   private Point2D intersectArc(double aX, double aY, double aRadius, double aAngleStart, double aAngleEnd, Line2D l1, double divisions){
    double granularity = (aAngleEnd - aAngleStart) / divisions;
		
		//arc is divided into divisions number of segments then segments are intersected counterclockwise until
		//having covered the entire arc 
		for( double rad = aAngleStart; rad<=(aAngleEnd-granularity); rad+=granularity) {
			Line2D arcSection = new Line2D.Double(
					aRadius * Math.cos(rad) + aX, aRadius * Math.sin(rad) + aY,
					aRadius * Math.cos(rad+granularity) + aX, aRadius * Math.sin(rad+granularity) + aY);
			
			return intersectLine(arcSection, l1);
			
		}
		
		
		return null;
  }


  /**
   * return intersection point of two lines if it exist
   */
  // line 181 "../../../../../Block223PlayMode.ump"
   private Point2D intersectLine(Line2D l1, Line2D l2){
    double a1,a2,c1,c2,x,y;
		a1=(l1.getY2() - l1.getY1()) / (l1.getX2() - l1.getX1());
		a2=(l2.getY2() - l2.getY1()) / (l2.getX2() - l2.getX1());
		c1= l1.getY1() - a1 * l1.getX1();
		c2= l2.getY1() - a2 * l2.getX1();
		
		if(a1==a2) {
			return null;
		}
		x=(c2 - c1) / (a1 - a2);
		if((Math.min(l1.getX1(),l1.getX2()) <= x && x <= Math.max(l1.getX1(),l1.getX2()))
				&& (Math.min(l2.getX1(),l2.getX2()) <= x && x <= Math.max(l2.getX1(),l2.getX2()))) {
			
		}else {
			return null;
		}
		
		y=a1 * x + c1;
		
		
		Point2D intersection = new Point2D.Double(x,y);
		
		return intersection;
  }


  /**
   * Guards
   */
  // line 32 "../../../../../Block223States.ump"
   private boolean hitPaddle(){
    BouncePoint bp = calculateBouncePointPaddle();
	if(bp == null){
		return false;
	}
	setBounce(bp);	

    return true;
  }

  // line 42 "../../../../../Block223States.ump"
   private boolean isOutOfBoundsAndLastLife(){
    // TODO implement
    return false;
  }

  // line 47 "../../../../../Block223States.ump"
   private boolean isOutOfBounds(){
    // TODO implement
    return false;
  }

  // line 52 "../../../../../Block223States.ump"
   private boolean hitLastBlockAndLastLevel(){
    // TODO implement
    return false;
  }

  // line 57 "../../../../../Block223States.ump"
   private boolean hitLastBlock(){
    // TODO implement
    return false;
  }

  // line 62 "../../../../../Block223States.ump"
   private boolean hitBlock(){
    // TODO implement
    return false;
  }

  // line 67 "../../../../../Block223States.ump"
   private boolean hitWall(){
    // TODO implement
    return false;
  }


  /**
   * Actions
   */
  // line 74 "../../../../../Block223States.ump"
   private void doSetup(){
    this.resetCurrentBallX();
    this.resetCurrentBallY();
    this.resetBallDirectionX();
    this.resetBallDirectionY();
    this.resetCurrentPaddleX();
    Level level = game.getLevel(currentLevel - 1);
    List<BlockAssignment> blockAssignments = level.getBlockAssignments();
    for (BlockAssignment a : blockAssignments) {
	  PlayedBlockAssignment p = new PlayedBlockAssignment(
		Game.WALL_PADDING + (Block.SIZE + Game.COLUMNS_PADDING) * (a.getGridHorizontalPosition() - 1),
		Game.WALL_PADDING + (Block.SIZE + Game.ROW_PADDING) * (a.getGridVerticalPosition() - 1),
		a.getBlock(), this);
    }
    while (numberOfBlocks() < game.getNrBlocksPerLevel()) {
	  Random rand = new Random();
	  int x = rand.nextInt(Game.PLAY_AREA_SIDE + 1) + 1;
	  int y = rand.nextInt(Game.PLAY_AREA_SIDE + 1) + 1;
	  while (true) {
		final int finalX = x;
		final int finalY = y;
		if (blocks.stream().noneMatch(b -> (b.getX() == finalX && b.getY() == finalY))) {
		  PlayedBlockAssignment p = new PlayedBlockAssignment(x, y, game.getRandomBlock(), this);
		  break;
		}
		if (x < Game.PLAY_AREA_SIDE + 1)
		  x++;
		else if (y < Game.PLAY_AREA_SIDE + 1) {
		  y++;
		  x = 1;
		}
		else {
		  x = 1;
		  y = 1;
		}
	  }
	}
  }

  // line 113 "../../../../../Block223States.ump"
   private void doHitPaddleOrWall(){
    // TODO implement
  }

  // line 117 "../../../../../Block223States.ump"
   private void doOutOfBounds(){
    // TODO implement
  }

  // line 121 "../../../../../Block223States.ump"
   private void doHitBlock(){
    // TODO implement
  }

  // line 125 "../../../../../Block223States.ump"
   private void doHitBlockNextLevel(){
    // TODO implement
  }

  // line 129 "../../../../../Block223States.ump"
   private void doHitNothingAndNotOutOfBounds(){
    double x = getCurrentBallX();
    double y = getCurrentBallY();
    double dx = getBallDirectionX();
    double dy = getBallDirectionY();
    setCurrentBallX(x+dx);
    setCurrentBallY(y+dy);
  }

  // line 138 "../../../../../Block223States.ump"
   private void doGameOver(){
    // TODO implement
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "score" + ":" + getScore()+ "," +
            "lives" + ":" + getLives()+ "," +
            "currentLevel" + ":" + getCurrentLevel()+ "," +
            "waitTime" + ":" + getWaitTime()+ "," +
            "playername" + ":" + getPlayername()+ "," +
            "ballDirectionX" + ":" + getBallDirectionX()+ "," +
            "ballDirectionY" + ":" + getBallDirectionY()+ "," +
            "currentBallX" + ":" + getCurrentBallX()+ "," +
            "currentBallY" + ":" + getCurrentBallY()+ "," +
            "currentPaddleLength" + ":" + getCurrentPaddleLength()+ "," +
            "currentPaddleX" + ":" + getCurrentPaddleX()+ "," +
            "currentPaddleY" + ":" + getCurrentPaddleY()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "bounce = "+(getBounce()!=null?Integer.toHexString(System.identityHashCode(getBounce())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "block223 = "+(getBlock223()!=null?Integer.toHexString(System.identityHashCode(getBlock223())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 104 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = 8597675110221231714L ;

  
}