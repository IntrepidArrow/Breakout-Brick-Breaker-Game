/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;
import java.awt.geom.*;
import java.util.*;

// line 102 "../../../../../Block223Persistence.ump"
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
    // line 540 "../../../../../Block223PlayMode.ump"
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


  /**
   * helper, returns the sign of the argument
   */
  // line 64 "../../../../../Block223PlayMode.ump"
   private double signum(double a){
    if(a>=0)
		   return 1;
	   else
		   return -1;
  }

  // line 73 "../../../../../Block223PlayMode.ump"
   private BouncePoint calculateBouncePointBlock(PlayedBlockAssignment block){
    BouncePoint returnedBp = null;
    int xBlock = block.getX();
	int yBlock = block.getY();
	int sideBlock = Block.SIZE;
	int ballRadius = Ball.BALL_DIAMETER / 2 ;
	
	 ArrayList<BouncePoint> allIntersections = new ArrayList<BouncePoint>();
	
	 // blockshape + yellow line
	Rectangle2D blockShape = new Rectangle2D.Double(xBlock - ballRadius,yBlock - ballRadius, sideBlock + 2*ballRadius, sideBlock + 2*ballRadius );
	
	Line2D path = new Line2D.Double(currentBallX,currentBallY,currentBallX + ballDirectionX, currentBallY + ballDirectionY);
	
	if(!blockShape.intersectsLine(path)) {
		return null;
	}
		// segments representing the sides of the block
		Line2D blockTopLine = new Line2D.Double(xBlock,yBlock - ballRadius,
				xBlock + sideBlock, yBlock - ballRadius);
		
		Line2D blockBottomLine = new Line2D.Double(xBlock,yBlock + ballRadius + sideBlock,
				xBlock + sideBlock, yBlock + ballRadius + sideBlock);
		
		Line2D blockRightLine = new Line2D.Double(xBlock + ballRadius + sideBlock ,yBlock,
				xBlock + ballRadius + sideBlock, yBlock + sideBlock);
		
		Line2D blockLeftLine = new Line2D.Double(xBlock - ballRadius ,yBlock,
				xBlock - ballRadius, yBlock + sideBlock) ;
		
		
		// check if hit the top of block
		Point2D interA = intersectLine(blockTopLine, path);
		if(interA != null) {
			allIntersections.add(new BouncePoint(interA.getX(),interA.getY(),BouncePoint.BounceDirection.FLIP_Y));
		}

		// check if hit the bottom of block
		Point2D interD = intersectLine(blockBottomLine, path);	
		if(interD != null) {
			allIntersections.add(new BouncePoint(interD.getX(),interD.getY(),BouncePoint.BounceDirection.FLIP_Y));
		}


		// check if hit the right side of block
		Point2D interC = intersectLine(blockRightLine, path);
		if(interC != null) {
			allIntersections.add( new BouncePoint(interC.getX(),interC.getY(),BouncePoint.BounceDirection.FLIP_X));
		}

		// check if hit the left side of block
		Point2D interB = intersectLine(blockLeftLine, path);
		if(interB != null) {
			allIntersections.add( new BouncePoint(interB.getX(),interB.getY(),BouncePoint.BounceDirection.FLIP_X));
		}
		
		// Check if hit the left upper corner from the left
		Point2D interLeftE = intersectArc(xBlock,yBlock,ballRadius,Math.PI*0.75,Math.PI,path,10);
		if(interLeftE != null) {
			allIntersections.add( new BouncePoint(interLeftE.getX(),interLeftE.getY(),getBallDirectionX()<0?BouncePoint.BounceDirection.FLIP_Y:BouncePoint.BounceDirection.FLIP_X));
		}
		
		// Check if hit the left upper corner from the right
		Point2D interRightE = intersectArc(xBlock,yBlock,ballRadius,Math.PI*0.5,Math.PI*0.75,path,10);
		if(interRightE != null) {
			allIntersections.add( new BouncePoint(interRightE.getX(),interRightE.getY(),getBallDirectionX()<0?BouncePoint.BounceDirection.FLIP_Y:BouncePoint.BounceDirection.FLIP_X));
		}
		
		// Check if hit the right upper corner from the left
		Point2D interLeftF = intersectArc(xBlock + sideBlock ,yBlock,ballRadius,Math.PI*0.25,Math.PI*0.5,path,10);
		if(interLeftF != null) {
			allIntersections.add( new BouncePoint(interLeftF.getX(),interLeftF.getY(),getBallDirectionX()<0?BouncePoint.BounceDirection.FLIP_X:BouncePoint.BounceDirection.FLIP_Y));
		}
		
		// Check if hit the right upper corner from the right
		Point2D interRightF = intersectArc(xBlock + sideBlock ,yBlock,ballRadius,0,Math.PI*0.25,path,10);
		if(interRightF != null) {
			allIntersections.add( new BouncePoint(interRightF.getX(),interRightF.getY(),getBallDirectionX()<0?BouncePoint.BounceDirection.FLIP_X:BouncePoint.BounceDirection.FLIP_Y));
		}
		
		// Check if hit the left lower corner from the left
		Point2D interLeftG = intersectArc(xBlock,yBlock + sideBlock,ballRadius,Math.PI,Math.PI*1.25,path,10);
		if(interLeftG != null) {
			allIntersections.add( new BouncePoint(interLeftG.getX(),interLeftG.getY(),getBallDirectionX()<0?BouncePoint.BounceDirection.FLIP_Y:BouncePoint.BounceDirection.FLIP_X));
		}

		// Check if hit the left lower corner from the right
		Point2D interRightG = intersectArc(xBlock,yBlock + sideBlock,ballRadius,Math.PI*1.25,Math.PI*1.5,path,10);
		if(interRightG != null) {
			allIntersections.add( new BouncePoint(interRightG.getX(),interRightG.getY(),getBallDirectionX()<0?BouncePoint.BounceDirection.FLIP_Y:BouncePoint.BounceDirection.FLIP_X));
		}

		// Check if hit the right lower corner from the left
		Point2D interLeftH = intersectArc(xBlock + sideBlock ,yBlock + sideBlock,ballRadius,Math.PI*1.5,Math.PI*1.75,path,10);
		if(interLeftH != null) {
			allIntersections.add( new BouncePoint(interLeftH.getX(),interLeftH.getY(),getBallDirectionX()<0?BouncePoint.BounceDirection.FLIP_X:BouncePoint.BounceDirection.FLIP_Y));
		}

		// Check if hit the right lower corner from the right
		Point2D interRightH = intersectArc(xBlock + sideBlock ,yBlock + sideBlock,ballRadius,Math.PI*1.75,Math.PI*2,path,10);
		if(interRightH != null) {
			allIntersections.add( new BouncePoint(interRightH.getX(),interRightH.getY(),getBallDirectionX()<0?BouncePoint.BounceDirection.FLIP_X:BouncePoint.BounceDirection.FLIP_Y));
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
					returnedBp = allIntersections.get(minIndex);
					returnedBp.setHitBlock(block);
					// return null if bp is exactly at the new ball position
					if(returnedBp.getX() == currentBallX + ballDirectionX & returnedBp.getY() == currentBallY + ballDirectionY ) {
						returnedBp = null;
					}
					
					return returnedBp;
				}
  }

  // line 205 "../../../../../Block223PlayMode.ump"
   private void bounceBall(){
    BouncePoint bounce = getBounce();
	   double newDirectionX = 0;
	   double newDirectionY = 0;
		//computing euclidian distance
	   // incoming distance: the distance between the current position of the center of the ball (currentBallX/Y) and the position of the bounce point
	   double inDist = Math.pow((bounce.getX() - currentBallX),2) +Math.pow((bounce.getY() - currentBallY),2);
	   inDist = Math.sqrt(inDist);
	   
	   // outcoming distance: the remaining distance after bounce point
	   double totalDist=Math.sqrt((ballDirectionX*ballDirectionX +ballDirectionY*ballDirectionY));
	   double remDist = totalDist-inDist;
	   
	   if(remDist == 0) { //new ball position is at bounce point
		   newDirectionX = ballDirectionX;
		   newDirectionY = ballDirectionY;
		   currentBallX = bounce.getX();
		   currentBallY = bounce.getY();
	   }
	   else if( bounce.getDirection() == BouncePoint.BounceDirection.FLIP_Y ) {
		   // ball direction updated
		   newDirectionX = ballDirectionX + signum(ballDirectionX)*(0.1)*Math.abs(ballDirectionY) ;
		   newDirectionY = (-1)*ballDirectionY ;
		   // current ball position updated
		   currentBallX = bounce.getX() + (remDist/totalDist) * newDirectionX ;
		   currentBallY = bounce.getY() + (remDist/totalDist) * newDirectionY ;
	   }
	   else if ( bounce.getDirection() == BouncePoint.BounceDirection.FLIP_X ) {
		   // ball direction updated
		   newDirectionX = (-1)*ballDirectionX ;
		   newDirectionY = ballDirectionY + signum(ballDirectionY)*(0.1)*Math.abs(ballDirectionX);
		   // current ball position updated
		   currentBallX = bounce.getX() + (remDist/totalDist) * newDirectionX ;
		   currentBallY = bounce.getY() + (remDist/totalDist) * newDirectionY ;
	   }
	   else if ( bounce.getDirection() == BouncePoint.BounceDirection.FLIP_BOTH ){
		   // ball direction updated
		   newDirectionX = (-1)*ballDirectionX ;
		   newDirectionY = (-1)*ballDirectionY ;
		   // current ball position updated
		   currentBallX = bounce.getX() + (remDist/totalDist) * newDirectionX ;
		   currentBallY = bounce.getY() + (remDist/totalDist) * newDirectionY ;
	   }

	   ballDirectionX = newDirectionX;
	   ballDirectionY = newDirectionY;
  }

  // line 254 "../../../../../Block223PlayMode.ump"
   private BouncePoint calculateBouncePointWall(){
    ArrayList<BouncePoint> allIntersections = new ArrayList<BouncePoint>();
			
		Line2D path = new Line2D.Double(getCurrentBallX(), getCurrentBallY(), getCurrentBallX()+getBallDirectionX(), getCurrentBallY()+getBallDirectionY());

		
		Line2D sectA = new Line2D.Double(
				Ball.BALL_DIAMETER/2, Ball.BALL_DIAMETER/2,
				Ball.BALL_DIAMETER/2, Game.PLAY_AREA_SIDE-Ball.BALL_DIAMETER/2);
		
		Line2D sectB = new Line2D.Double(
				Ball.BALL_DIAMETER/2, Ball.BALL_DIAMETER/2,
				Game.PLAY_AREA_SIDE-Ball.BALL_DIAMETER/2, Ball.BALL_DIAMETER/2);
		
		Line2D sectC = new Line2D.Double(
				Game.PLAY_AREA_SIDE-Ball.BALL_DIAMETER/2, Ball.BALL_DIAMETER/2,
				Game.PLAY_AREA_SIDE-Ball.BALL_DIAMETER/2, Game.PLAY_AREA_SIDE-Ball.BALL_DIAMETER/2);
		
		//intersect the ball path line with sectors A,B,C and add computed intersections to list of intersected lines
		Point2D interA = intersectLine(sectA, path);
		if(interA != null) {
			allIntersections.add(new BouncePoint(interA.getX(),interA.getY(),BouncePoint.BounceDirection.FLIP_X));
		}
		Point2D interB = intersectLine(sectB, path);
		if(interB != null) {
			allIntersections.add( new BouncePoint(interB.getX(),interB.getY(),BouncePoint.BounceDirection.FLIP_Y));
		}
		Point2D interC = intersectLine(sectC, path);
		if(interC != null) {
			allIntersections.add( new BouncePoint(interC.getX(),interC.getY(),BouncePoint.BounceDirection.FLIP_X));
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
			BouncePoint closest;
			//check if the point is one of the two corners and adjust bounce direction otherwise return nearest intersection
			if((allIntersections.get(minIndex).getX()==5.0 && allIntersections.get(minIndex).getY()==5.0)||
					(allIntersections.get(minIndex).getX()==385.0 && allIntersections.get(minIndex).getY()==5.0)) {
				closest= new BouncePoint(allIntersections.get(minIndex).getX(),allIntersections.get(minIndex).getY(),BouncePoint.BounceDirection.FLIP_BOTH);
			}else {
				closest=allIntersections.get(minIndex);
			}
			
			if(closest.getX()==(currentBallX+ballDirectionX) &&
					closest.getY()==(currentBallY+ballDirectionY)) {
				return null;
			}else {
				return closest;
			}
			 
		}
  }

  // line 323 "../../../../../Block223PlayMode.ump"
   private boolean isBallOutOfBounds(){
    boolean outofbounds = false; 
		if(getCurrentBallY()+ getBallDirectionY() +5 > 390){
			outofbounds = true;
		}
		return outofbounds;
  }

  // line 334 "../../../../../Block223PlayMode.ump"
   private BouncePoint calculateBouncePointPaddle(){
    ArrayList<BouncePoint> allIntersections = new ArrayList<BouncePoint>();
		Rectangle2D paddle = new Rectangle2D.Double(getCurrentPaddleX()- Ball.BALL_DIAMETER/2,getCurrentPaddleY()- Ball.BALL_DIAMETER/2,getCurrentPaddleLength()+ Ball.BALL_DIAMETER,Paddle.PADDLE_WIDTH+ Ball.BALL_DIAMETER/2);
				
		Line2D path = new Line2D.Double(getCurrentBallX(), getCurrentBallY(), getCurrentBallX()+getBallDirectionX(), getCurrentBallY()+getBallDirectionY());
		
		if(!paddle.intersectsLine(path)) {
			return null;
		}
		
		Line2D sectA = new Line2D.Double(
				getCurrentPaddleX(),getCurrentPaddleY() - Ball.BALL_DIAMETER/2,
				getCurrentPaddleX() + getCurrentPaddleLength(), getCurrentPaddleY() - Ball.BALL_DIAMETER/2);
		
		Line2D sectB = new Line2D.Double(
				getCurrentPaddleX() - Ball.BALL_DIAMETER/2,getCurrentPaddleY(),
				getCurrentPaddleX() - Ball.BALL_DIAMETER/2, getCurrentPaddleY() + Paddle.PADDLE_WIDTH);
		
		Line2D sectC = new Line2D.Double(
				getCurrentPaddleX() + getCurrentPaddleLength() + Ball.BALL_DIAMETER/2,getCurrentPaddleY(),
				getCurrentPaddleX() + getCurrentPaddleLength() + Ball.BALL_DIAMETER/2, getCurrentPaddleY() + Paddle.PADDLE_WIDTH);
		
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

		Point2D interLeftE = intersectArc(getCurrentPaddleX(),getCurrentPaddleY(),Ball.BALL_DIAMETER/2,Math.PI*0.75,Math.PI,path,20);
		if(interLeftE != null) {
			allIntersections.add( new BouncePoint(interLeftE.getX(),interLeftE.getY(),getBallDirectionX()<0?BouncePoint.BounceDirection.FLIP_Y:BouncePoint.BounceDirection.FLIP_X));
		}
		Point2D interRightE = intersectArc(getCurrentPaddleX(),getCurrentPaddleY(),Ball.BALL_DIAMETER/2,Math.PI*0.5,Math.PI*0.75,path,20);
		if(interRightE != null) {
			allIntersections.add( new BouncePoint(interRightE.getX(),interRightE.getY(),getBallDirectionX()<0?BouncePoint.BounceDirection.FLIP_Y:BouncePoint.BounceDirection.FLIP_X));
		}
		Point2D interLeftF = intersectArc(getCurrentPaddleX()+getCurrentPaddleLength(),getCurrentPaddleY(),Ball.BALL_DIAMETER/2,0.0,Math.PI*0.25,path,20);
		if(interLeftF != null) {
			allIntersections.add( new BouncePoint(interLeftF.getX(),interLeftF.getY(),getBallDirectionX()<0?BouncePoint.BounceDirection.FLIP_X:BouncePoint.BounceDirection.FLIP_Y));
		}
		Point2D interRightF = intersectArc(getCurrentPaddleX()+getCurrentPaddleLength(),getCurrentPaddleY(),Ball.BALL_DIAMETER/2,Math.PI*0.25,Math.PI*0.5,path,20);
		if(interRightF != null) {
			allIntersections.add( new BouncePoint(interRightF.getX(),interRightF.getY(),getBallDirectionX()<0?BouncePoint.BounceDirection.FLIP_X:BouncePoint.BounceDirection.FLIP_Y));
		}
		
		//return the nearest intersection
		if(allIntersections.isEmpty()) {
			return null;
		}else {
			double dist;
			int minIndex = 0;
			double min= Double.MAX_VALUE;
			for(int i=0;i<allIntersections.size(); i++) {
				dist=distance(allIntersections.get(i).getX(),allIntersections.get(i).getY(),getCurrentBallX(), getCurrentBallY());
				if(dist<min) {
					min=dist;
					minIndex=i;
				}
			}
			if(allIntersections.get(minIndex).getX()==(currentBallX+ballDirectionX) &&
					allIntersections.get(minIndex).getY()==(currentBallY+ballDirectionY)) {
				return null;
			}else {
				return allIntersections.get(minIndex);
			}
		}
  }


  /**
   * find euclidian distance squared
   */
  // line 413 "../../../../../Block223PlayMode.ump"
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
  // line 425 "../../../../../Block223PlayMode.ump"
   private Point2D intersectArc(double aX, double aY, double aRadius, double aAngleStart, double aAngleEnd, Line2D l1, double divisions){
    double granularity = (aAngleEnd - aAngleStart) / divisions;

		//arc is divided into divisions number of segments then segments are intersected counterclockwise until
		//having covered the entire arc 
		for( double rad = aAngleStart; rad<=(aAngleEnd-granularity); rad+=granularity) {
			Line2D arcSection = new Line2D.Double(
					aRadius * Math.cos(rad) + aX, -aRadius * Math.sin(rad) + aY,
					aRadius * Math.cos(rad+granularity) + aX, -aRadius * Math.sin(rad+granularity) + aY);
			
			Point2D inter=intersectLine(arcSection, l1);

			if(inter!=null) {
				double a=Math.round(inter.getX()*10)/10;
				double b=Math.round(inter.getY()*10)/10;
				Point2D rounded = new Point2D.Double(a,b);
				return rounded; 
				
			}
			
		}
		
		
		return null;
  }


  /**
   * return intersection point of two lines if it exist
   */
  // line 452 "../../../../../Block223PlayMode.ump"
   private Point2D intersectLine(Line2D l1, Line2D l2){
    double a1,a2,c1,c2,x,y;
    double vertY;
    	if(l1.getX1()==l1.getX2() && l2.getX1()==l2.getX2()) {
    		return null;
    	}
    	if(l1.getX1()==l1.getX2()) {
    		a2=(l2.getY2() - l2.getY1()) / (l2.getX2() - l2.getX1());
    		c2= l2.getY1() - a2 * l2.getX1();
    		//y=x*a+c
    		vertY=l1.getX1()*a2+c2;
    		
    		if((Math.min(l1.getX1(),l1.getX2()) <= l1.getX1() && l1.getX1() <= Math.max(l1.getX1(),l1.getX2()))
    				&& (Math.min(l2.getX1(),l2.getX2()) <= l1.getX1() && l1.getX1() <= Math.max(l2.getX1(),l2.getX2()))
    				&& (Math.min(l1.getY1(),l1.getY2()) <= vertY && vertY <= Math.max(l1.getY1(),l1.getY2()))
    				&& (Math.min(l2.getY1(),l2.getY2()) <= vertY && vertY <= Math.max(l2.getY1(),l2.getY2()))) {
    			
    			Point2D intersection = new Point2D.Double(l1.getX1(),vertY);
    			
    			return intersection;
    		}else {
    			return null;
    		}
    		
    	}
    	if(l2.getX1()==l2.getX2()) {

    		a1=(l1.getY2() - l1.getY1()) / (l1.getX2() - l1.getX1());
    		c1= l1.getY1() - a1 * l1.getX1();
    		//y=x*a+c
    		vertY=l2.getX1()*a1+c1;
    		
    		if((Math.min(l1.getX1(),l1.getX2()) <= l2.getX1() && l2.getX1() <= Math.max(l1.getX1(),l1.getX2()))
    				&& (Math.min(l2.getX1(),l2.getX2()) <= l2.getX1() && l2.getX1() <= Math.max(l2.getX1(),l2.getX2()))
    				&& (Math.min(l1.getY1(),l1.getY2()) <= vertY && vertY <= Math.max(l1.getY1(),l1.getY2()))
    				&& (Math.min(l2.getY1(),l2.getY2()) <= vertY && vertY <= Math.max(l2.getY1(),l2.getY2()))) {
    			
    			Point2D intersection = new Point2D.Double(l2.getX1(),vertY);
    			
    			return intersection;
    		}else {
    			return null;
    		}
    		
    	}
    
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
			
			y=a1 * x + c1;

			Point2D intersection = new Point2D.Double(x,y);
			
			return intersection;
		}else {
			return null;
		}
  }

  // line 522 "../../../../../Block223PlayMode.ump"
   private boolean isCloser(BouncePoint first, BouncePoint second){
    boolean isCloser = false;
	   if(second == null) {
		   isCloser =  true;
	   }
	   else if(first == null) {
		   isCloser = false;
	   }
	   else if(distance(first.getX(),first.getY(),currentBallX,currentBallY) > distance(second.getX(),second.getY(),currentBallX,currentBallY) ) {
		 isCloser = true;
	 }
	   return isCloser;
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
    boolean outOfBounds = false; 
	if (getLives()==1) {
		return true;
	}
	return false;
  }

  // line 50 "../../../../../Block223States.ump"
   private boolean isOutOfBounds(){
    boolean isOutOfBounds = isBallOutOfBounds();
	return isOutOfBounds;
  }

  // line 55 "../../../../../Block223States.ump"
   private boolean hitLastBlockAndLastLevel(){
    // TODO implement
	   Game game = getGame();
	   int nrLevels = game.numberOfLevels();
	   setBounce(null);
	   
	   if( nrLevels == currentLevel ) {
		   int nrBlocks = numberOfBlocks();
		   
		   if( nrBlocks == 1 ) {
			   PlayedBlockAssignment block = getBlock(0);
			   BouncePoint bp = calculateBouncePointBlock(block);
			   setBounce(bp);
			   if(bp != null){
			   return true;
			   }
			 }
		  }
	   
    return false;
  }

  // line 77 "../../../../../Block223States.ump"
   private boolean hitLastBlock(){
    // TODO implement
    
	   int nrBlocks = numberOfBlocks();
	   setBounce(null);
	   
	   if( nrBlocks == 1 ) {
		   PlayedBlockAssignment block = getBlock(0);
		   BouncePoint bp = calculateBouncePointBlock(block);
		   setBounce(bp);
		   if (bp != null){
		   	return true;
		   }
	   }
	   return false;
  }

  // line 94 "../../../../../Block223States.ump"
   private boolean hitBlock(){
    // TODO implement
   int nrBlocks = numberOfBlocks();
	   setBounce(null);
	   
	   for ( int i = 0 ; i < nrBlocks ; i++ ) {
		   PlayedBlockAssignment block = getBlock(i);
		   BouncePoint bp = calculateBouncePointBlock(block);
		   BouncePoint bounce = getBounce();
		   boolean closer = isCloser(bp,bounce);
		   if(closer) {
			   setBounce(bp);
		   }
	   }
    return getBounce() != null;
  }


  /**
   * line 111 "../../../../../Block223States.ump"
   */
  // line 112 "../../../../../Block223States.ump"
   private boolean hitWall(){
    BouncePoint bp = calculateBouncePointWall();
	if(bp == null){
		return false;
	}
	setBounce(bp);	

    return true;
  }


  /**
   * Actions
   */
  // line 124 "../../../../../Block223States.ump"
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
	  int x = rand.nextInt(game.getMaxNumberHorizontalBlocks()) + 1;
	  int y = rand.nextInt(game.getMaxNumberVerticalBlocks()) + 1;
	  while (true) {
		final int finalX = Game.WALL_PADDING + (Block.SIZE + Game.COLUMNS_PADDING) * (x - 1);
		final int finalY = Game.WALL_PADDING + (Block.SIZE + Game.COLUMNS_PADDING) * (y - 1);
		if (blocks.stream().noneMatch(b -> (b.getX() == finalX && b.getY() == finalY))) {
		  PlayedBlockAssignment p = new PlayedBlockAssignment(finalX, finalY, game.getRandomBlock(), this);
		  break;
		}
		if (x < game.getMaxNumberHorizontalBlocks())
		  x++;
		else if (y < game.getMaxNumberVerticalBlocks()) {
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

  // line 163 "../../../../../Block223States.ump"
   private void doHitPaddleOrWall(){
    bounceBall();
	return;
  }

  // line 168 "../../../../../Block223States.ump"
   private void doOutOfBounds(){
    int lives = getLives();
	setLives(lives-1);
	resetCurrentBallX();
	resetCurrentBallY();
	resetCurrentPaddleX();
	resetBallDirectionX(); 			
	resetBallDirectionY();
  }

  // line 179 "../../../../../Block223States.ump"
   private void doHitBlock(){
    // TODO implement
    int score = getScore();
	   BouncePoint bounce = getBounce();
	   PlayedBlockAssignment pblock = bounce.getHitBlock();
	   int bscore = pblock.getBlock().getPoints();
	   setScore(score + bscore);
	   pblock.delete();
	   bounceBall();
	   setBounce(null);
  }

  // line 191 "../../../../../Block223States.ump"
   private void doHitBlockNextLevel(){
    // TODO implement
    doHitBlock();
	   int level = getCurrentLevel();
	   setCurrentLevel(++level);
	   int paddleLength = getGame().getPaddle().getMaxPaddleLength() - 
			   			( getGame().getPaddle().getMaxPaddleLength() - getGame().getPaddle().getMinPaddleLength() ) /
			   			( getGame().numberOfLevels() - 1 ) * ( getCurrentLevel() - 1 ) ;
	   setCurrentPaddleLength(paddleLength);
	   setWaitTime( INITIAL_WAIT_TIME * Math.pow(getGame().getBall().getBallSpeedIncreaseFactor(), ( getCurrentLevel() - 1 )));
  }

  // line 204 "../../../../../Block223States.ump"
   private void doHitNothingAndNotOutOfBounds(){
    //double x = getCurrentBallX();
    //double y = getCurrentBallY();
    //double dx = getBallDirectionX();
    //double dy = getBallDirectionY();
    //setCurrentBallX(x+dx);
    //setCurrentBallY(y+dy);
    
    setCurrentBallX(getCurrentBallX() + getBallDirectionX());
    setCurrentBallY(getCurrentBallY() + getBallDirectionY());
  }

  // line 216 "../../../../../Block223States.ump"
   private void doGameOver(){
    Player p;
	String playername;
	int score;
	Block223 block223 = getBlock223();
	score = getScore();
	playername= getPlayername();
	p = getPlayer();
	if (p!= null) {
	Game game_this = getGame();
	HallOfFameEntry hof = new HallOfFameEntry (score, playername, p, game_this, block223);
	game_this.setMostRecentEntry(hof);
	}
	delete();
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
  
  // line 105 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = 8597675110221231714L ;

  
}