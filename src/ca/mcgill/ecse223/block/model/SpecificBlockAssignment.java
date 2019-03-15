/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 46 "../../../../../Block223PlayGame.ump"
public class SpecificBlockAssignment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SpecificBlockAssignment Attributes
  private int positionX;
  private int positionY;

  //SpecificBlockAssignment Associations
  private Block block;
  private SpecificGame specificGame;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SpecificBlockAssignment(int aPositionX, int aPositionY, Block aBlock, SpecificGame aSpecificGame)
  {
    positionX = aPositionX;
    positionY = aPositionY;
    if (!setBlock(aBlock))
    {
      throw new RuntimeException("Unable to create SpecificBlockAssignment due to aBlock");
    }
    boolean didAddSpecificGame = setSpecificGame(aSpecificGame);
    if (!didAddSpecificGame)
    {
      throw new RuntimeException("Unable to create blockAssignment due to specificGame");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  /**
   * specific block assignments by pixel positions
   */
  public int getPositionX()
  {
    return positionX;
  }

  public int getPositionY()
  {
    return positionY;
  }
  /* Code from template association_GetOne */
  public Block getBlock()
  {
    return block;
  }
  /* Code from template association_GetOne */
  public SpecificGame getSpecificGame()
  {
    return specificGame;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setBlock(Block aNewBlock)
  {
    boolean wasSet = false;
    if (aNewBlock != null)
    {
      block = aNewBlock;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setSpecificGame(SpecificGame aSpecificGame)
  {
    boolean wasSet = false;
    if (aSpecificGame == null)
    {
      return wasSet;
    }

    SpecificGame existingSpecificGame = specificGame;
    specificGame = aSpecificGame;
    if (existingSpecificGame != null && !existingSpecificGame.equals(aSpecificGame))
    {
      existingSpecificGame.removeBlockAssignment(this);
    }
    specificGame.addBlockAssignment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    block = null;
    SpecificGame placeholderSpecificGame = specificGame;
    this.specificGame = null;
    if(placeholderSpecificGame != null)
    {
      placeholderSpecificGame.removeBlockAssignment(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "positionX" + ":" + getPositionX()+ "," +
            "positionY" + ":" + getPositionY()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "block = "+(getBlock()!=null?Integer.toHexString(System.identityHashCode(getBlock())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "specificGame = "+(getSpecificGame()!=null?Integer.toHexString(System.identityHashCode(getSpecificGame())):"null");
  }
}