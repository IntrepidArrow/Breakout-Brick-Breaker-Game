/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 45 "../../../../../Block223PlayGame.ump"
public class SpecificBall
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SpecificBall Attributes
  private int ballPosX;
  private int ballPosY;
  private int directionX;
  private int directionY;

  //SpecificBall Associations
  private Ball ball;
  private SpecificGame specificGame;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SpecificBall(Ball aBall, SpecificGame aSpecificGame)
  {
    resetBallPosX();
    resetBallPosY();
    resetDirectionX();
    resetDirectionY();
    if (!setBall(aBall))
    {
      throw new RuntimeException("Unable to create SpecificBall due to aBall");
    }
    if (aSpecificGame == null || aSpecificGame.getSpecificBall() != null)
    {
      throw new RuntimeException("Unable to create SpecificBall due to aSpecificGame");
    }
    specificGame = aSpecificGame;
  }

  public SpecificBall(Ball aBall, int aCurrentLevelPlayedForSpecificGame, SpecificPaddle aSpecificPaddleForSpecificGame, Game aGameForSpecificGame, Player aPlayerForSpecificGame)
  {
    resetBallPosX();
    resetBallPosY();
    resetDirectionX();
    resetDirectionY();
    boolean didAddBall = setBall(aBall);
    if (!didAddBall)
    {
      throw new RuntimeException("Unable to create specificBall due to ball");
    }
    specificGame = new SpecificGame(aCurrentLevelPlayedForSpecificGame, this, aSpecificPaddleForSpecificGame, aGameForSpecificGame, aPlayerForSpecificGame);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template attribute_SetDefaulted */
  public boolean setBallPosX(int aBallPosX)
  {
    boolean wasSet = false;
    ballPosX = aBallPosX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetBallPosX()
  {
    boolean wasReset = false;
    ballPosX = getDefaultBallPosX();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setBallPosY(int aBallPosY)
  {
    boolean wasSet = false;
    ballPosY = aBallPosY;
    wasSet = true;
    return wasSet;
  }

  public boolean resetBallPosY()
  {
    boolean wasReset = false;
    ballPosY = getDefaultBallPosY();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setDirectionX(int aDirectionX)
  {
    boolean wasSet = false;
    directionX = aDirectionX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetDirectionX()
  {
    boolean wasReset = false;
    directionX = getDefaultDirectionX();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setDirectionY(int aDirectionY)
  {
    boolean wasSet = false;
    directionY = aDirectionY;
    wasSet = true;
    return wasSet;
  }

  public boolean resetDirectionY()
  {
    boolean wasReset = false;
    directionY = getDefaultDirectionY();
    wasReset = true;
    return wasReset;
  }

  /**
   * default start postions of the ball specified in assignment doc - center of the play area
   */
  public int getBallPosX()
  {
    return ballPosX;
  }
  /* Code from template attribute_GetDefaulted */
  public int getDefaultBallPosX()
  {
    return 195;
  }

  public int getBallPosY()
  {
    return ballPosY;
  }
  /* Code from template attribute_GetDefaulted */
  public int getDefaultBallPosY()
  {
    return 195;
  }

  public int getDirectionX()
  {
    return directionX;
  }
  /* Code from template attribute_GetDefaulted */
  public int getDefaultDirectionX()
  {
    return ball.getMinBallSpeedX();
  }

  public int getDirectionY()
  {
    return directionY;
  }
  /* Code from template attribute_GetDefaulted */
  public int getDefaultDirectionY()
  {
    return ball.getMinBallSpeedY();
  }
  /* Code from template association_GetOne */
  public Ball getBall()
  {
    return ball;
  }
  /* Code from template association_GetOne */
  public SpecificGame getSpecificGame()
  {
    return specificGame;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setBall(Ball aNewBall)
  {
    boolean wasSet = false;
    if (aNewBall != null)
    {
      ball = aNewBall;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    ball = null;
    SpecificGame existingSpecificGame = specificGame;
    specificGame = null;
    if (existingSpecificGame != null)
    {
      existingSpecificGame.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "ballPosX" + ":" + getBallPosX()+ "," +
            "ballPosY" + ":" + getBallPosY()+ "," +
            "directionX" + ":" + getDirectionX()+ "," +
            "directionY" + ":" + getDirectionY()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "ball = "+(getBall()!=null?Integer.toHexString(System.identityHashCode(getBall())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "specificGame = "+(getSpecificGame()!=null?Integer.toHexString(System.identityHashCode(getSpecificGame())):"null");
  }
}