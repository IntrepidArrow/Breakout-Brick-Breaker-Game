/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.io.Serializable;

// line 16 "Block223Persistence.ump"
public class User implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User()
  {}

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {}

  // line 21 "Block223Persistence.ump"
   public static  void reinitializeUniqueUsername(List<User> users){
    Map<String, User> tmpUsersByUsername = new HashMap<String, User>();
    for (User u : users) {
      tmpUsersByUsername.put(u.getUsername(), u);
    }
    User.usersByUsername=tmpUsersByUsername;
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 19 "Block223Persistence.ump"
  private static final long serialVersionUID = 4267485601061759914L ;

  
}