/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.io.Serializable;

// line 41 "Block223Persistence.ump"
public class Game implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game()
  {}

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {}

  // line 46 "Block223Persistence.ump"
   public static  void reinitializeUniqueName(List<Game> games){
    Map<String, Game> gamesByName = new HashMap<String, Game>();
    for (Game g : games) {
      gamesByName.put(g.getName(), g);
    }
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 44 "Block223Persistence.ump"
  private static final long serialVersionUID = -210105651472293481L ;

  
}