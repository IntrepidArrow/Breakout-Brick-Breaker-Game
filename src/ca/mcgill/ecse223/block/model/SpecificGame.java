/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 7 "../../../../../Block223States.ump"
public class SpecificGame
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SpecificGame State Machines
  public enum GameStatus { Init, ongoing, Paused, ResetLevel, Lvl_End, Game_End }
  private GameStatus gameStatus;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SpecificGame()
  {
    setGameStatus(GameStatus.Init);
  }

  //------------------------
  // INTERFACE
  //------------------------

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
          doWallPaddleHit()
          setGameStatus(GameStatus.ongoing);
          wasEventProcessed = true;
          break;
        }
        if (isBlockHit(level))
        {
        // line 19 "../../../../../Block223States.ump"
          doBlockHit()
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
        saveGame()
        break;
      case Game_End:
        // line 52 "../../../../../Block223States.ump"
        saveScoreandDelete()
        break;
    }
  }

  public void delete()
  {}

}