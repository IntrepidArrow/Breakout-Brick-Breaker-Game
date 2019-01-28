/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/



// line 49 "UmpleMakeMeeting1.ump"
public class Cell
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Cell Associations
  private GameBlock gameBlock;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Cell()
  {}

  //------------------------
  // INTERFACE
  //------------------------
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

  public void delete()
  {
    GameBlock existingGameBlock = gameBlock;
    gameBlock = null;
    if (existingGameBlock != null)
    {
      existingGameBlock.delete();
    }
  }

}