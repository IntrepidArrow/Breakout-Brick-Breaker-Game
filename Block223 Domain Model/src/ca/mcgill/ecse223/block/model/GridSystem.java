package ca.mcgill.ecse223.block.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 54 "Block223.ump"
public class GridSystem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GridSystem Associations
  private Level level;
  private List<Cell> cells;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GridSystem(Level aLevel)
  {
    if (aLevel == null || aLevel.getGridSystem() != null)
    {
      throw new RuntimeException("Unable to create GridSystem due to aLevel");
    }
    level = aLevel;
    cells = new ArrayList<Cell>();
  }

  public GridSystem(int aLevelNumberForLevel, Game aGameForLevel)
  {
    level = new Level(aLevelNumberForLevel, aGameForLevel, this);
    cells = new ArrayList<Cell>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Level getLevel()
  {
    return level;
  }
  /* Code from template association_GetMany */
  public Cell getCell(int index)
  {
    Cell aCell = cells.get(index);
    return aCell;
  }

  public List<Cell> getCells()
  {
    List<Cell> newCells = Collections.unmodifiableList(cells);
    return newCells;
  }

  public int numberOfCells()
  {
    int number = cells.size();
    return number;
  }

  public boolean hasCells()
  {
    boolean has = cells.size() > 0;
    return has;
  }

  public int indexOfCell(Cell aCell)
  {
    int index = cells.indexOf(aCell);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCells()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Cell addCell(int aPositionX, int aPositionY)
  {
    return new Cell(aPositionX, aPositionY, this);
  }

  public boolean addCell(Cell aCell)
  {
    boolean wasAdded = false;
    if (cells.contains(aCell)) { return false; }
    GridSystem existingGridSystem = aCell.getGridSystem();
    boolean isNewGridSystem = existingGridSystem != null && !this.equals(existingGridSystem);
    if (isNewGridSystem)
    {
      aCell.setGridSystem(this);
    }
    else
    {
      cells.add(aCell);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCell(Cell aCell)
  {
    boolean wasRemoved = false;
    //Unable to remove aCell, as it must always have a gridSystem
    if (!this.equals(aCell.getGridSystem()))
    {
      cells.remove(aCell);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCellAt(Cell aCell, int index)
  {  
    boolean wasAdded = false;
    if(addCell(aCell))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCells()) { index = numberOfCells() - 1; }
      cells.remove(aCell);
      cells.add(index, aCell);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCellAt(Cell aCell, int index)
  {
    boolean wasAdded = false;
    if(cells.contains(aCell))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCells()) { index = numberOfCells() - 1; }
      cells.remove(aCell);
      cells.add(index, aCell);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCellAt(aCell, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Level existingLevel = level;
    level = null;
    if (existingLevel != null)
    {
      existingLevel.delete();
    }
    while (cells.size() > 0)
    {
      Cell aCell = cells.get(cells.size() - 1);
      aCell.delete();
      cells.remove(aCell);
    }
    
  }

}