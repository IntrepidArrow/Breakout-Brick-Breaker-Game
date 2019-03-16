/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 49 "../../../../../Block223PlayGame.ump"
public class SpecificPaddle
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SpecificPaddle Attributes
  private int paddlePosX;

  //SpecificPaddle Associations
  private Paddle paddle;
  private SpecificGame specificGame;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SpecificPaddle(Paddle aPaddle, SpecificGame aSpecificGame)
  {
    paddlePosX = 195 - (paddle.getMaxPaddleLength() / 2);
    if (!setPaddle(aPaddle))
    {
      throw new RuntimeException("Unable to create SpecificPaddle due to aPaddle");
    }
    if (aSpecificGame == null || aSpecificGame.getSpecificPaddle() != null)
    {
      throw new RuntimeException("Unable to create SpecificPaddle due to aSpecificGame");
    }
    specificGame = aSpecificGame;
  }

  public SpecificPaddle(Paddle aPaddle, int aCurrentLevelPlayedForSpecificGame, SpecificBall aSpecificBallForSpecificGame, Game aGameForSpecificGame, Player aPlayerForSpecificGame)
  {
    paddlePosX = 195 - (paddle.getMaxPaddleLength() / 2);
    boolean didAddPaddle = setPaddle(aPaddle);
    if (!didAddPaddle)
    {
      throw new RuntimeException("Unable to create specificPaddle due to paddle");
    }
    specificGame = new SpecificGame(aCurrentLevelPlayedForSpecificGame, aSpecificBallForSpecificGame, this, aGameForSpecificGame, aPlayerForSpecificGame);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPaddlePosX(int aPaddlePosX)
  {
    boolean wasSet = false;
    paddlePosX = aPaddlePosX;
    wasSet = true;
    return wasSet;
  }

  /**
   * paddle will start in the center of the screen at the start of a new game - default start position with respect to top left
   * corner of the paddle
   */
  public int getPaddlePosX()
  {
    return paddlePosX;
  }
  /* Code from template association_GetOne */
  public Paddle getPaddle()
  {
    return paddle;
  }
  /* Code from template association_GetOne */
  public SpecificGame getSpecificGame()
  {
    return specificGame;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setPaddle(Paddle aNewPaddle)
  {
    boolean wasSet = false;
    if (aNewPaddle != null)
    {
      paddle = aNewPaddle;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    paddle = null;
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
            "paddlePosX" + ":" + getPaddlePosX()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "paddle = "+(getPaddle()!=null?Integer.toHexString(System.identityHashCode(getPaddle())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "specificGame = "+(getSpecificGame()!=null?Integer.toHexString(System.identityHashCode(getSpecificGame())):"null");
  }
}