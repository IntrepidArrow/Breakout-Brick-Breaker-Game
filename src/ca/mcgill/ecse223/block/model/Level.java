/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;
import java.util.*;

/**
 * random attribute not needed anymore
 * Each level is filled up with random blocks just before playing the level to reach the nrBlocksPerLevel defined in Game
 */
// line 77 "../../../../../Block223Persistence.ump"
// line 10 "../../../../../Block223PlayGame.ump"
// line 138 "../../../../../Block223.ump"
public class Level implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Level Associations
  private List<SpecificBlockAssignment> specificBlockAssignments;
  private Game game;
  private List<BlockAssignment> blockAssignments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Level(Game aGame)
  {
    specificBlockAssignments = new ArrayList<SpecificBlockAssignment>();
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create level due to game");
    }
    blockAssignments = new ArrayList<BlockAssignment>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public SpecificBlockAssignment getSpecificBlockAssignment(int index)
  {
    SpecificBlockAssignment aSpecificBlockAssignment = specificBlockAssignments.get(index);
    return aSpecificBlockAssignment;
  }

  public List<SpecificBlockAssignment> getSpecificBlockAssignments()
  {
    List<SpecificBlockAssignment> newSpecificBlockAssignments = Collections.unmodifiableList(specificBlockAssignments);
    return newSpecificBlockAssignments;
  }

  public int numberOfSpecificBlockAssignments()
  {
    int number = specificBlockAssignments.size();
    return number;
  }

  public boolean hasSpecificBlockAssignments()
  {
    boolean has = specificBlockAssignments.size() > 0;
    return has;
  }

  public int indexOfSpecificBlockAssignment(SpecificBlockAssignment aSpecificBlockAssignment)
  {
    int index = specificBlockAssignments.indexOf(aSpecificBlockAssignment);
    return index;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_GetMany */
  public BlockAssignment getBlockAssignment(int index)
  {
    BlockAssignment aBlockAssignment = blockAssignments.get(index);
    return aBlockAssignment;
  }

  public List<BlockAssignment> getBlockAssignments()
  {
    List<BlockAssignment> newBlockAssignments = Collections.unmodifiableList(blockAssignments);
    return newBlockAssignments;
  }

  public int numberOfBlockAssignments()
  {
    int number = blockAssignments.size();
    return number;
  }

  public boolean hasBlockAssignments()
  {
    boolean has = blockAssignments.size() > 0;
    return has;
  }

  public int indexOfBlockAssignment(BlockAssignment aBlockAssignment)
  {
    int index = blockAssignments.indexOf(aBlockAssignment);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSpecificBlockAssignments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public SpecificBlockAssignment addSpecificBlockAssignment(int aPositionX, int aPositionY, Block aBlock, SpecificGame aSpecificGame)
  {
    return new SpecificBlockAssignment(aPositionX, aPositionY, this, aBlock, aSpecificGame);
  }

  public boolean addSpecificBlockAssignment(SpecificBlockAssignment aSpecificBlockAssignment)
  {
    boolean wasAdded = false;
    if (specificBlockAssignments.contains(aSpecificBlockAssignment)) { return false; }
    Level existingLevel = aSpecificBlockAssignment.getLevel();
    boolean isNewLevel = existingLevel != null && !this.equals(existingLevel);
    if (isNewLevel)
    {
      aSpecificBlockAssignment.setLevel(this);
    }
    else
    {
      specificBlockAssignments.add(aSpecificBlockAssignment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSpecificBlockAssignment(SpecificBlockAssignment aSpecificBlockAssignment)
  {
    boolean wasRemoved = false;
    //Unable to remove aSpecificBlockAssignment, as it must always have a level
    if (!this.equals(aSpecificBlockAssignment.getLevel()))
    {
      specificBlockAssignments.remove(aSpecificBlockAssignment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSpecificBlockAssignmentAt(SpecificBlockAssignment aSpecificBlockAssignment, int index)
  {  
    boolean wasAdded = false;
    if(addSpecificBlockAssignment(aSpecificBlockAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecificBlockAssignments()) { index = numberOfSpecificBlockAssignments() - 1; }
      specificBlockAssignments.remove(aSpecificBlockAssignment);
      specificBlockAssignments.add(index, aSpecificBlockAssignment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSpecificBlockAssignmentAt(SpecificBlockAssignment aSpecificBlockAssignment, int index)
  {
    boolean wasAdded = false;
    if(specificBlockAssignments.contains(aSpecificBlockAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecificBlockAssignments()) { index = numberOfSpecificBlockAssignments() - 1; }
      specificBlockAssignments.remove(aSpecificBlockAssignment);
      specificBlockAssignments.add(index, aSpecificBlockAssignment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSpecificBlockAssignmentAt(aSpecificBlockAssignment, index);
    }
    return wasAdded;
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlockAssignments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public BlockAssignment addBlockAssignment(int aGridHorizontalPosition, int aGridVerticalPosition, Block aBlock, Game aGame)
  {
    return new BlockAssignment(aGridHorizontalPosition, aGridVerticalPosition, this, aBlock, aGame);
  }

  public boolean addBlockAssignment(BlockAssignment aBlockAssignment)
  {
    boolean wasAdded = false;
    if (blockAssignments.contains(aBlockAssignment)) { return false; }
    Level existingLevel = aBlockAssignment.getLevel();
    boolean isNewLevel = existingLevel != null && !this.equals(existingLevel);
    if (isNewLevel)
    {
      aBlockAssignment.setLevel(this);
    }
    else
    {
      blockAssignments.add(aBlockAssignment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlockAssignment(BlockAssignment aBlockAssignment)
  {
    boolean wasRemoved = false;
    //Unable to remove aBlockAssignment, as it must always have a level
    if (!this.equals(aBlockAssignment.getLevel()))
    {
      blockAssignments.remove(aBlockAssignment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlockAssignmentAt(BlockAssignment aBlockAssignment, int index)
  {  
    boolean wasAdded = false;
    if(addBlockAssignment(aBlockAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlockAssignments()) { index = numberOfBlockAssignments() - 1; }
      blockAssignments.remove(aBlockAssignment);
      blockAssignments.add(index, aBlockAssignment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBlockAssignmentAt(BlockAssignment aBlockAssignment, int index)
  {
    boolean wasAdded = false;
    if(blockAssignments.contains(aBlockAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlockAssignments()) { index = numberOfBlockAssignments() - 1; }
      blockAssignments.remove(aBlockAssignment);
      blockAssignments.add(index, aBlockAssignment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBlockAssignmentAt(aBlockAssignment, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=specificBlockAssignments.size(); i > 0; i--)
    {
      SpecificBlockAssignment aSpecificBlockAssignment = specificBlockAssignments.get(i - 1);
      aSpecificBlockAssignment.delete();
    }
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removeLevel(this);
    }
    for(int i=blockAssignments.size(); i > 0; i--)
    {
      BlockAssignment aBlockAssignment = blockAssignments.get(i - 1);
      aBlockAssignment.delete();
    }
  }

  // line 140 "../../../../../Block223.ump"
   public BlockAssignment findBlockAssignment(int gridHorizontalPosition, int gridVerticalPosition){
    BlockAssignment foundBlockAssignment = null;
  for(BlockAssignment blockAssignment : this.getBlockAssignments()) {
		if( blockAssignment.getGridHorizontalPosition() == gridHorizontalPosition && blockAssignment.getGridVerticalPosition() == gridVerticalPosition ) {
			foundBlockAssignment = blockAssignment;
			break;
		}
	}
  return foundBlockAssignment;
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 80 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = -2683493616927797654L ;

  
}