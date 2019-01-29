package ca.mcgill.ecse223.block.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/



// line 44 "Block223.ump"
public class Level
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Level Attributes
  private int levelNumber;

  //Level Associations
  private Game game;
  private GridSystem gridSystem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Level(int aLevelNumber, Game aGame, GridSystem aGridSystem)
  {
    levelNumber = aLevelNumber;
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create level due to game");
    }
    if (aGridSystem == null || aGridSystem.getLevel() != null)
    {
      throw new RuntimeException("Unable to create Level due to aGridSystem");
    }
    gridSystem = aGridSystem;
  }

  public Level(int aLevelNumber, Game aGame)
  {
    levelNumber = aLevelNumber;
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create level due to game");
    }
    gridSystem = new GridSystem(this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLevelNumber(int aLevelNumber)
  {
    boolean wasSet = false;
    levelNumber = aLevelNumber;
    wasSet = true;
    return wasSet;
  }

  public int getLevelNumber()
  {
    return levelNumber;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_GetOne */
  public GridSystem getGridSystem()
  {
    return gridSystem;
  }
  /* Code from template association_SetOneToAtMostN */
  public boolean setGame(Game aGame)
  {
    boolean wasSet = false;
    //Must provide game to level
    if (aGame == null)
    {
      return wasSet;
    }

    //game already at maximum (99)
    if (aGame.numberOfLevels() >= Game.maximumNumberOfLevels())
    {
      return wasSet;
    }
    
    Game existingGame = game;
    game = aGame;
    if (existingGame != null && !existingGame.equals(aGame))
    {
      boolean didRemove = existingGame.removeLevel(this);
      if (!didRemove)
      {
        game = existingGame;
        return wasSet;
      }
    }
    game.addLevel(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removeLevel(this);
    }
    GridSystem existingGridSystem = gridSystem;
    gridSystem = null;
    if (existingGridSystem != null)
    {
      existingGridSystem.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "levelNumber" + ":" + getLevelNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "gridSystem = "+(getGridSystem()!=null?Integer.toHexString(System.identityHashCode(getGridSystem())):"null");
  }
}