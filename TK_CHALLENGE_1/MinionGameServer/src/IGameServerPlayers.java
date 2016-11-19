




import java.util.List;
import java.util.ArrayList;


/**
 *
 * @authors Group C 
 */

public class IGameServerPlayers {
    //list of all players
    private List<Player> allPlayersCurrentlyConnected = new ArrayList<Player>();
    //static refernce of IGameServerPlayer
    private static IGameServerPlayers instance;
    //constructor :: empty
    private IGameServerPlayers() {  }
    /**
     * 
     * @return static singleton recieve of this class
     */
    public static IGameServerPlayers getInstance() {
        //if ther is no instance of the class create new one
        if (null == instance) { instance = new IGameServerPlayers();}
        return instance;
    }
     /**
      *  
      * @return get list of all playing players
      */
    public List<Player> getAllPlayersCurrentlyConnected() {
        return allPlayersCurrentlyConnected;
    }
    /**
     * set list of all playing players
     * @param allPlayersCurrentlyConnected 
     */
    public void setAllPlayersCurrentlyConnected(List<Player> allPlayersCurrentlyConnected) {
        this.allPlayersCurrentlyConnected = allPlayersCurrentlyConnected;
    }
    /**
     *  add plugged in  player to list of all playing players
     * @param player
     * @return 
     */
    public String addPlayer(Player player) {
        try {
            allPlayersCurrentlyConnected.add(player);
            return "success";
        } catch (Exception e) {
            return "fail";
        }
    }
   /**
    * remove unplugged player from list of all playing players
    * @param name
    * @return 
    */
    public String removePlayer(String name) {
        try {
            allPlayersCurrentlyConnected.remove(findObjectByName(name));
            return "Success";
        } catch (Exception e) {
            return "fail";
        }
    }
    /**
     * find object from list by property of name and return the list of all playing players list
     * @param name
     * @return   list of all playing players list
     */
    public Player findObjectByName(String name) {

        for (Player eachPlayerData : allPlayersCurrentlyConnected) {
            if (eachPlayerData.getPlayerName().equals(name)) {
                return eachPlayerData;
            }
        }

        return null;

    }
    /**
     * determine winner
     * @param playerName 
     */
    public void setWinner(String playerName){
 
        Player winner = findObjectByName(playerName);        
        winner.setPlayerScore(winner.getPlayerScore()+1);
    }

}
