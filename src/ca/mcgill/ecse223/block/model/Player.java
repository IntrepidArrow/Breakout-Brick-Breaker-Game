/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;
import java.util.*;

// line 39 "../../../../../Block223Persistence.ump"
// line 13 "../../../../../Block223PlayGame.ump"
// line 52 "../../../../../Block223.ump"
public class Player extends UserRole implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Associations
  private List<Score> playerScores;
  private List<SpecificGame> specificGames;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(String aPassword, Block223 aBlock223)
  {
    super(aPassword, aBlock223);
    playerScores = new ArrayList<Score>();
    specificGames = new ArrayList<SpecificGame>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Score getPlayerScore(int index)
  {
    Score aPlayerScore = playerScores.get(index);
    return aPlayerScore;
  }

  public List<Score> getPlayerScores()
  {
    List<Score> newPlayerScores = Collections.unmodifiableList(playerScores);
    return newPlayerScores;
  }

  public int numberOfPlayerScores()
  {
    int number = playerScores.size();
    return number;
  }

  public boolean hasPlayerScores()
  {
    boolean has = playerScores.size() > 0;
    return has;
  }

  public int indexOfPlayerScore(Score aPlayerScore)
  {
    int index = playerScores.indexOf(aPlayerScore);
    return index;
  }
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPlayerScores()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Score addPlayerScore(int aPoints, Game aGame, SpecificGame aSpecificGame)
  {
    return new Score(aPoints, aGame, this, aSpecificGame);
  }

  public boolean addPlayerScore(Score aPlayerScore)
  {
    boolean wasAdded = false;
    if (playerScores.contains(aPlayerScore)) { return false; }
    Player existingPlayer = aPlayerScore.getPlayer();
    boolean isNewPlayer = existingPlayer != null && !this.equals(existingPlayer);
    if (isNewPlayer)
    {
      aPlayerScore.setPlayer(this);
    }
    else
    {
      playerScores.add(aPlayerScore);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePlayerScore(Score aPlayerScore)
  {
    boolean wasRemoved = false;
    //Unable to remove aPlayerScore, as it must always have a player
    if (!this.equals(aPlayerScore.getPlayer()))
    {
      playerScores.remove(aPlayerScore);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPlayerScoreAt(Score aPlayerScore, int index)
  {  
    boolean wasAdded = false;
    if(addPlayerScore(aPlayerScore))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayerScores()) { index = numberOfPlayerScores() - 1; }
      playerScores.remove(aPlayerScore);
      playerScores.add(index, aPlayerScore);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePlayerScoreAt(Score aPlayerScore, int index)
  {
    boolean wasAdded = false;
    if(playerScores.contains(aPlayerScore))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayerScores()) { index = numberOfPlayerScores() - 1; }
      playerScores.remove(aPlayerScore);
      playerScores.add(index, aPlayerScore);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPlayerScoreAt(aPlayerScore, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSpecificGames()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public SpecificGame addSpecificGame(Game aGame)
  {
    return new SpecificGame(aGame, this);
  }

  public boolean addSpecificGame(SpecificGame aSpecificGame)
  {
    boolean wasAdded = false;
    if (specificGames.contains(aSpecificGame)) { return false; }
    Player existingPlayer = aSpecificGame.getPlayer();
    boolean isNewPlayer = existingPlayer != null && !this.equals(existingPlayer);
    if (isNewPlayer)
    {
      aSpecificGame.setPlayer(this);
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
    //Unable to remove aSpecificGame, as it must always have a player
    if (!this.equals(aSpecificGame.getPlayer()))
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

  public void delete()
  {
    for(int i=playerScores.size(); i > 0; i--)
    {
      Score aPlayerScore = playerScores.get(i - 1);
      aPlayerScore.delete();
    }
    for(int i=specificGames.size(); i > 0; i--)
    {
      SpecificGame aSpecificGame = specificGames.get(i - 1);
      aSpecificGame.delete();
    }
    super.delete();
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 42 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = -2683573616927799873L ;

  
}