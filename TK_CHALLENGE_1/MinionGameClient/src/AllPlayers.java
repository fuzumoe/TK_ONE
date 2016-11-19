


import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Group C
 */
public class AllPlayers {

    //list of all players
    private List<Player> playersList = new ArrayList<Player>();

    /**
     *
     * @return get list of all playing players
     */
    public List<Player> getPlayersList() {
        return playersList;
    }

    /**
     * set list of all playing players
     *
     * @param allPlayersCurrentlyConnected
     */
    public void setPlayersList(List<Player> playersList) {
        this.playersList = playersList;
    }

    /**
     * update list of all remote players
     *
     * @param playerName
     * @param score
     */
    public void updatePlayersList(String playerName, int score) {

        Player player = this.findObjectByName(playerName);
        if (null == player) {
            player = new Player(playerName, score);
            this.playersList.add(player);
        } else {
            this.playersList.remove(player);
            this.playersList.add(player);
        }
    }

    /**
     * find object from list by property of name and return the list of all
     * playing players list
     *
     * @param name
     * @return list of all playing players list
     */
    public Player findObjectByName(String name) {

        for (Player eachPlayerData : playersList) {
            if (eachPlayerData.getPlayerName().equals(name)) {
                return eachPlayerData;
            }
        }
        return null;

    }

}
