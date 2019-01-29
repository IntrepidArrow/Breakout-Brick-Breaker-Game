package ca.mcgill.ecse223.block.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/



// line 48 "Block223.ump"
public class Cell
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Cell Attributes
  private int positionX;
  private int positionY;

  //Cell Associations
  private GameBlock gameBlock;
  private GridSystem gridSystem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Cell(int aPositionX, int aPositionY, GridSystem aGridSystem)
  {
    positionX = aPositionX;
    positionY = aPositionY;
    boolean didAddGridSystem = setGridSystem(aGridSystem);
    if (!didAddGridSystem)
    {
      throw new RuntimeException("Unable to create cell due to gridSystem");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPositionX(int aPositionX)
  {
    boolean wasSet = false;
    positionX = aPositionX;
    wasSet = true;
    return wasSet;
  }

  public boolean setPositionY(int aPositionY)
  {
    boolean wasSet = false;
    positionY = aPositionY;
    wasSet = true;
    return wasSet;
  }

  public int getPositionX()
  {
    return positionX;
  }

  public int getPositionY()
  {
    return positionY;
  }
  /* Code from template association_GetOne */
  public GameBlock getGameBlock()
  {
    return gameBlock;
  }

  public boolean hasGameBlock()
  {
    boolean has = gameBlock != null;
    return has;
  }
  /* Code from template association_GetOne */
  public GridSystem getGridSystem()
  {
    return gridSystem;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setGameBlock(GameBlock aNewGameBlock)
  {
    boolean wasSet = false;
    if (gameBlock != null && !gameBlock.equals(aNewGameBlock) && equals(gameBlock.getCell()))
    {
      //Unable to setGameBlock, as existing gameBlock would become an orphan
      return wasSet;
    }

    gameBlock = aNewGameBlock;
    Cell anOldCell = aNewGameBlock != null ? aNewGameBlock.getCell() : null;

    if (!this.equals(anOldCell))
    {
      if (anOldCell != null)
      {
        anOldCell.gameBlock = null;
      }
      if (gameBlock != null)
      {
        gameBlock.setCell(this);
      }
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGridSystem(GridSystem aGridSystem)
  {
    boolean wasSet = false;
    if (aGridSystem == null)
    {
      return wasSet;
    }

    GridSystem existingGridSystem = gridSystem;
    gridSystem = aGridSystem;
    if (existingGridSystem != null && !existingGridSystem.equals(aGridSystem))
    {
      existingGridSystem.removeCell(this);
    }
    gridSystem.addCell(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    GameBlock existingGameBlock = gameBlock;
    gameBlock = null;
    if (existingGameBlock != null)
    {
      existingGameBlock.delete();
    }
    GridSystem placeholderGridSystem = gridSystem;
    this.gridSystem = null;
    if(placeholderGridSystem != null)
    {
      placeholderGridSystem.removeCell(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "positionX" + ":" + getPositionX()+ "," +
            "positionY" + ":" + getPositionY()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "gameBlock = "+(getGameBlock()!=null?Integer.toHexString(System.identityHashCode(getGameBlock())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "gridSystem = "+(getGridSystem()!=null?Integer.toHexString(System.identityHashCode(getGridSystem())):"null");
  }
}