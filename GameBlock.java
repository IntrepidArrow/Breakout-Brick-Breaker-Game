/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/



// line 39 "UmpleMakeMeeting1.ump"
public class GameBlock
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Color { Red, Blue, Green }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GameBlock Attributes
  private Color blockColor;
  private float blockPoints;

  //GameBlock Associations
  private Game game;
  private Cell cell;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GameBlock(Color aBlockColor, float aBlockPoints, Game aGame, Cell aCell)
  {
    blockColor = aBlockColor;
    blockPoints = aBlockPoints;
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create block due to game");
    }
    boolean didAddCell = setCell(aCell);
    if (!didAddCell)
    {
      throw new RuntimeException("Unable to create gameBlock due to cell");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setBlockColor(Color aBlockColor)
  {
    boolean wasSet = false;
    blockColor = aBlockColor;
    wasSet = true;
    return wasSet;
  }

  public boolean setBlockPoints(float aBlockPoints)
  {
    boolean wasSet = false;
    blockPoints = aBlockPoints;
    wasSet = true;
    return wasSet;
  }

  public Color getBlockColor()
  {
    return blockColor;
  }

  public float getBlockPoints()
  {
    return blockPoints;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_GetOne */
  public Cell getCell()
  {
    return cell;
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
      existingGame.removeBlock(this);
    }
    game.addBlock(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setCell(Cell aNewCell)
  {
    boolean wasSet = false;
    if (aNewCell == null)
    {
      //Unable to setCell to null, as gameBlock must always be associated to a cell
      return wasSet;
    }
    
    GameBlock existingGameBlock = aNewCell.getGameBlock();
    if (existingGameBlock != null && !equals(existingGameBlock))
    {
      //Unable to setCell, the current cell already has a gameBlock, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Cell anOldCell = cell;
    cell = aNewCell;
    cell.setGameBlock(this);

    if (anOldCell != null)
    {
      anOldCell.setGameBlock(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removeBlock(this);
    }
    Cell existingCell = cell;
    cell = null;
    if (existingCell != null)
    {
      existingCell.setGameBlock(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "blockPoints" + ":" + getBlockPoints()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "blockColor" + "=" + (getBlockColor() != null ? !getBlockColor().equals(this)  ? getBlockColor().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "cell = "+(getCell()!=null?Integer.toHexString(System.identityHashCode(getCell())):"null");
  }
}