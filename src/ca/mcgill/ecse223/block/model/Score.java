/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.sql.Date;

// line 25 "../../../../../Block223PlayGame.ump"
public class Score
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Score Associations
  private Game game;
  private Player player;
  private SpecificGame specificGame;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Score(Game aGame, Player aPlayer, SpecificGame aSpecificGame)
  {
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create score due to game");
    }
    boolean didAddPlayer = setPlayer(aPlayer);
    if (!didAddPlayer)
    {
      throw new RuntimeException("Unable to create score due to player");
    }
    if (aSpecificGame == null || aSpecificGame.getScore() != null)
    {
      throw new RuntimeException("Unable to create Score due to aSpecificGame");
    }
    specificGame = aSpecificGame;
  }

  public Score(Game aGame, Player aPlayer, Date aDateForSpecificGame, int aCurrentLevelPlayedForSpecificGame, Game aGameForSpecificGame, Player aPlayerForSpecificGame)
  {
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create score due to game");
    }
    boolean didAddPlayer = setPlayer(aPlayer);
    if (!didAddPlayer)
    {
      throw new RuntimeException("Unable to create score due to player");
    }
    specificGame = new SpecificGame(aDateForSpecificGame, aCurrentLevelPlayedForSpecificGame, aGameForSpecificGame, aPlayerForSpecificGame, this);
  }

  //------------------------
  // INTERFACE
  //------------------------
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
      existingGame.removeScore(this);
    }
    game.addScore(this);
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
      existingPlayer.removeScore(this);
    }
    player.addScore(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removeScore(this);
    }
    Player placeholderPlayer = player;
    this.player = null;
    if(placeholderPlayer != null)
    {
      placeholderPlayer.removeScore(this);
    }
    SpecificGame existingSpecificGame = specificGame;
    specificGame = null;
    if (existingSpecificGame != null)
    {
      existingSpecificGame.delete();
    }
  }

}