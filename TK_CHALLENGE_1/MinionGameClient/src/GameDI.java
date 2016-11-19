



/**
 *
 * @authors Group C
 */
public class GameDI {

    //current minions position

    private Coordinates currentMinionCoordinates;
    //players name
    private String playerName;
    //players score
    private int Score;
    //list object players
    private AllPlayers players = new AllPlayers();

    /**
     * return current minions positions
     *
     * @return getCurrentMinionCoordinates
     */
    public Coordinates getCurrentMinionCoordinates() {
        return currentMinionCoordinates;
    }

    /**
     * set current minions positions
     *
     * @param currentMinionCoordinates
     */
    public void setCurrentMinionCoordinates(Coordinates currentMinionCoordinates) {
        System.out.println("------------------");
        System.out.println("FEED ANG FEED MORE"); 
        System.out.println("------------------");
        this.currentMinionCoordinates = currentMinionCoordinates;
    }

    /**
     * return players name
     *
     * @return playerName
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * set player name
     *
     * @param playerName
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * get players score
     *
     * @return getScore
     */
    public int getScore() {
        return Score;
    }

    /**
     * set players score
     *
     * @param Score
     */
    public void setScore(int Score) {
        this.Score = Score;
    }

    /**
     * update players core
     *
     * @param name
     * @param score
     */
    public void updatePlayersScore(String name, int score) {
        players.updatePlayersList(name, score);
    }

    /**
     * return plugged players list
     *
     * @return players
     */
    public AllPlayers getPlayers() {
        return players;
    }

    /**
     * set layers list
     *
     * @param players
     */
    public void setPlayers(AllPlayers players) {
        this.players = players;
    }

}
