/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.util.*;
import java.sql.Date;

// line 3 "../../../../../Block223PlayGame.ump"
public class Game
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Game Associations
  private List<SpecificGame> specificGames;
  private List<Score> scores;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game()
  {
    specificGames = new ArrayList<SpecificGame>();
    scores = new ArrayList<Score>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public SpecificGame getSpecificGame(int index)
  {
    SpecificGame aSpecificGame = specificGames.get(index);
    return aSpecificGame;
  }

  public List<SpecificGame> getSpecificGames()
  {
    List<SpecificGame> newSpecificGames = Collections.unmodifiableList(specificGames);
    return newSpecificGames;
  }

  public int numberOfSpecificGames()
  {
    int number = specificGames.size();
    return number;
  }

  public boolean hasSpecificGames()
  {
    boolean has = specificGames.size() > 0;
    return has;
  }

  public int indexOfSpecificGame(SpecificGame aSpecificGame)
  {
    int index = specificGames.indexOf(aSpecificGame);
    return index;
  }
  /* Code from template association_GetMany */
  public Score getScore(int index)
  {
    Score aScore = scores.get(index);
    return aScore;
  }

  public List<Score> getScores()
  {
    List<Score> newScores = Collections.unmodifiableList(scores);
    return newScores;
  }

  public int numberOfScores()
  {
    int number = scores.size();
    return number;
  }

  public boolean hasScores()
  {
    boolean has = scores.size() > 0;
    return has;
  }

  public int indexOfScore(Score aScore)
  {
    int index = scores.indexOf(aScore);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSpecificGames()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public SpecificGame addSpecificGame(Date aDate, int aCurrentLevelPlayed, Player aPlayer, Score aScore)
  {
    return new SpecificGame(aDate, aCurrentLevelPlayed, this, aPlayer, aScore);
  }

  public boolean addSpecificGame(SpecificGame aSpecificGame)
  {
    boolean wasAdded = false;
    if (specificGames.contains(aSpecificGame)) { return false; }
    Game existingGame = aSpecificGame.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);
    if (isNewGame)
    {
      aSpecificGame.setGame(this);
    }
    else
    {
      specificGames.add(aSpecificGame);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSpecificGame(SpecificGame aSpecificGame)
  {
    boolean wasRemoved = false;
    //Unable to remove aSpecificGame, as it must always have a game
    if (!this.equals(aSpecificGame.getGame()))
    {
      specificGames.remove(aSpecificGame);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSpecificGameAt(SpecificGame aSpecificGame, int index)
  {  
    boolean wasAdded = false;
    if(addSpecificGame(aSpecificGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecificGames()) { index = numberOfSpecificGames() - 1; }
      specificGames.remove(aSpecificGame);
      specificGames.add(index, aSpecificGame);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSpecificGameAt(SpecificGame aSpecificGame, int index)
  {
    boolean wasAdded = false;
    if(specificGames.contains(aSpecificGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecificGames()) { index = numberOfSpecificGames() - 1; }
      specificGames.remove(aSpecificGame);
      specificGames.add(index, aSpecificGame);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSpecificGameAt(aSpecificGame, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfScores()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Score addScore(Player aPlayer, SpecificGame aSpecificGame)
  {
    return new Score(this, aPlayer, aSpecificGame);
  }

  public boolean addScore(Score aScore)
  {
    boolean wasAdded = false;
    if (scores.contains(aScore)) { return false; }
    Game existingGame = aScore.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);
    if (isNewGame)
    {
      aScore.setGame(this);
    }
    else
    {
      scores.add(aScore);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeScore(Score aScore)
  {
    boolean wasRemoved = false;
    //Unable to remove aScore, as it must always have a game
    if (!this.equals(aScore.getGame()))
    {
      scores.remove(aScore);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addScoreAt(Score aScore, int index)
  {  
    boolean wasAdded = false;
    if(addScore(aScore))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfScores()) { index = numberOfScores() - 1; }
      scores.remove(aScore);
      scores.add(index, aScore);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveScoreAt(Score aScore, int index)
  {
    boolean wasAdded = false;
    if(scores.contains(aScore))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfScores()) { index = numberOfScores() - 1; }
      scores.remove(aScore);
      scores.add(index, aScore);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addScoreAt(aScore, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (specificGames.size() > 0)
    {
      SpecificGame aSpecificGame = specificGames.get(specificGames.size() - 1);
      aSpecificGame.delete();
      specificGames.remove(aSpecificGame);
    }
    
    while (scores.size() > 0)
    {
      Score aScore = scores.get(scores.size() - 1);
      aScore.delete();
      scores.remove(aScore);
    }
    
  }

}