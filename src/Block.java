/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.io.Serializable;

// line 53 "Block223Persistence.ump"
public class Block implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Block()
  {}

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {}

  // line 58 "Block223Persistence.ump"
   public static  void reinitializeAutoUniqueId(List<Game> games){
    nextId=0;
  	for(Game g : games){
  		List<Block> blocks = g.getBlocks();
  		for(Block b : blocks){
  			if(b.getId()>nextId){
  				nextId=b.getId();
  			}
  		}
  	}
  	nextId++;
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 56 "Block223Persistence.ump"
  private static final long serialVersionUID = 5332292624658907512L ;

  
}