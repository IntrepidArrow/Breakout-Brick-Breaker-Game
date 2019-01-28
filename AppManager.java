/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/



// line 1 "UmpleMakeMeeting1.ump"
public class AppManager
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //AppManager Associations
  private GameDesigner gameDesigner;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public AppManager(GameDesigner aGameDesigner)
  {
    if (aGameDesigner == null || aGameDesigner.getAppManager() != null)
    {
      throw new RuntimeException("Unable to create AppManager due to aGameDesigner");
    }
    gameDesigner = aGameDesigner;
  }

  public AppManager(int aPlayAreaLengthForGameDesigner, int aPlayAreaWidthForGameDesigner, User aUserForGameDesigner)
  {
    gameDesigner = new GameDesigner(aPlayAreaLengthForGameDesigner, aPlayAreaWidthForGameDesigner, this, aUserForGameDesigner);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public GameDesigner getGameDesigner()
  {
    return gameDesigner;
  }

  public void delete()
  {
    GameDesigner existingGameDesigner = gameDesigner;
    gameDesigner = null;
    if (existingGameDesigner != null)
    {
      existingGameDesigner.delete();
    }
  }

}