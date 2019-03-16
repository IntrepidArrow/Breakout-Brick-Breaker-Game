/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 56 "../../../../../Block223PlayGame.ump"
public class Score
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Score Attributes
  private int points;

  //Score Associations
  private Game game;
  private Player player;
  private SpecificGame specificGame;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Score(int aPoints, Game aGame, Player aPlayer, SpecificGame aSpecificGame)
  {
    points = aPoints;
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create gameScore due to game");
    }
    boolean didAddPlayer = setPlayer(aPlayer);
    if (!didAddPlayer)
    {
      throw new RuntimeException("Unable to create playerScore due to player");
    }
    boolean didAddSpecificGame = setSpecificGame(aSpecificGame);
    if (!didAddSpecificGame)
    {
      throw new RuntimeException("Unable to create score due to specificGame");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPoints(int aPoints)
  {
    boolean wasSet = false;
    points = aPoints;
    wasSet = true;
    return wasSet;
  }

  public int getPoints()
  {
    return points;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_GetOne */
  public Player getPlayer()
  {
    return player;
  }
  /* Code from template association_GetOne */
  public SpecificGame getSpecificGame()
  {
    return specificGame;
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
      existingGame.removeGameScore(this);
    }
    game.addGameScore(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setPlayer(Player aPlayer)
  {
    boolean wasSet = false;
    if (aPlayer == null)
    {
      return wasSet;
    }

    Player existingPlayer = player;
    player = aPlayer;
    if (existingPlayer != null && !existingPlayer.equals(aPlayer))
    {
      existingPlayer.removePlayerScore(this);
    }
    player.addPlayerScore(this);
    wasSet = true;
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
      existingSpecificGame.removeScore(this);
    }
    specificGame.addScore(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removeGameScore(this);
    }
    Player placeholderPlayer = player;
    this.player = null;
    if(placeholderPlayer != null)
    {
      placeholderPlayer.removePlayerScore(this);
    }
    SpecificGame placeholderSpecificGame = specificGame;
    this.specificGame = null;
    if(placeholderSpecificGame != null)
    {
      placeholderSpecificGame.removeScore(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "points" + ":" + getPoints()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "specificGame = "+(getSpecificGame()!=null?Integer.toHexString(System.identityHashCode(getSpecificGame())):"null");
  }
}