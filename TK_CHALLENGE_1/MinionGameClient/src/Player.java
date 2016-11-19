


/**
 *
 * @authors Group C
 */
public class Player {

    //player's name
    private String playerName;
    // players score
    private int playerScore;

    /**
     * constructor
     *
     * @param name
     * @param client
     * @param score
     */
    public Player(String playerName, int playerScore) {
        this.playerName = playerName;
        this.playerScore = playerScore;
    }

    /**
     *
     * @return players name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * set players name
     *
     * @param playerName
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     *
     * @return player's score
     */
    public int getPlayerScore() {
        return playerScore;
    }

    /**
     * set player's score
     *
     * @param playerScore
     */
    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

}
