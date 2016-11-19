 
  
/**
 *
 * @authors Group C 
 */

public class Player {
    //players name
    private String playerName;
    // players remote object
    private IGameClient clientClass;
    //players score
    private int playerScore;
    //constructor :: empty
    public Player() { }
    /**
     * constructor
     * @param name
     * @param client
     * @param score 
     */
    public Player(String name, IGameClient client, int score) {
        this.playerName = name;
        this.clientClass = client;
        this.playerScore = score;
    }
    /**
     * 
     * @return  players name
     */
    public String getPlayerName() {
        return playerName;
    }
   /**
    * set players name
    * @param playerName 
    */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    /**
     * 
     * @return  return player's remote object
     */
    public IGameClient getClientClass() {
        return clientClass;
    }
   /**
    * set player's  remote object
    * @param clientClass 
    */
    public void setClientClass(IGameClient clientClass) {
        this.clientClass = clientClass;
    }
    /**
     * 
     * @return player's score
     */
    public int getPlayerScore() {
        return playerScore;
    }
    /**
     *  set player's score
     * @param playerScore 
     */
    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }
    /**
     * increment players score
     */
    public void incrementScore(){
        this.playerScore++;
    }

}
