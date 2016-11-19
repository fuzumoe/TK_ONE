
import java.rmi.RemoteException;

/**
 *
 * @authors Group C
 */
public class IGameServerImpl implements IGameServer {

    /**
     * feedMinion :: Feed Minion implementation
     *
     * @param playerName
     * @throws RemoteException
     */
    @Override
    public void login(String playerName, IGameClient client) throws RemoteException {
        //print out users name
        System.out.println(" added player : " + playerName + " : "
                + //plug user to registry
                IGameServerPlayers.getInstance().
                addPlayer(new Player(playerName, client, 0)) + client);
        //players receive current position of the minion
        client.recieveMinionPosition(ServerGamePos.getInstance().getCurrentMinionPosition().getX(),
                ServerGamePos.getInstance().getCurrentMinionPosition().getY());

        //send pluged remote player's info to all other remote players
        this.broadcastOnePlayerToAll(playerName, 0);
        System.out.println(" broadcasted player: " + playerName);

        // send all remote players info to remote player
        for (Player eachPlayer : IGameServerPlayers.getInstance().getAllPlayersCurrentlyConnected()) {
            client.recieveMinonsFed(eachPlayer.getPlayerName(), eachPlayer.getPlayerScore());
        }

    }

    /**
     * unplug remote players from game Implementation
     *
     * @param playerName
     * @throws RemoteException
     */
    @Override
    public void logout(String playerName) throws RemoteException {
        System.out.println("Player removed : " + playerName + " : "
                + IGameServerPlayers.getInstance().removePlayer(playerName));

        // TODO: send notification to other clients. 
    }

    /**
     * feedMinions :: Feed Minions implementation
     *
     * @param playerName
     * @throws RemoteException
     */
    @Override
    public void feedMinion(String playerName) throws RemoteException {
        Player winner = IGameServerPlayers.getInstance().findObjectByName(playerName);
        System.out.println(playerName);
        winner.incrementScore();

        // Update other players and inform winner. 
        this.broadcastOnePlayerToAll(winner.getPlayerName(), winner.getPlayerScore());
        // Get new fly position and broadcast to all.
        this.broadCastNewMinionPositionToAll();

        // TODO: low priority: handle losers of that game using gameCounter.
    }

    /**
     * broadcast scores of the game to all remote players
     *
     * @throws RemoteException
     */
    public void broadcastScoresAndPlayersToAll() throws RemoteException {
        for (Player eachPlayer : IGameServerPlayers.getInstance().getAllPlayersCurrentlyConnected()) {
            for (Player everyPlayer : IGameServerPlayers.getInstance().getAllPlayersCurrentlyConnected()) {
                eachPlayer.getClientClass().recieveMinonsFed(everyPlayer.getPlayerName(), everyPlayer.getPlayerScore());
            }
        }
    }

    /**
     * broadcast players score to Server. and Server in hand to All
     *
     * @param player
     * @param playerScore
     * @throws RemoteException
     */
    public void broadcastOnePlayerToAll(String player, int playerScore) throws RemoteException {
        for (Player eachPlayer : IGameServerPlayers.getInstance().getAllPlayersCurrentlyConnected()) {
            eachPlayer.getClientClass().recieveMinonsFed(player, playerScore);
        }
    }

    /**
     * broadcast Minion new Minion x,y cordinate position to all players.
     *
     * @throws RemoteException
     */
    public void broadCastNewMinionPositionToAll() throws RemoteException {
        ServerGamePos.getInstance().generateRandomeMinionPos();
        for (Player eachPlayer : IGameServerPlayers.getInstance().getAllPlayersCurrentlyConnected()) {
            eachPlayer.getClientClass().recieveMinionPosition(ServerGamePos.getInstance().getCurrentMinionPosition().getX(), ServerGamePos.getInstance().getCurrentMinionPosition().getY());
        }
    }

}
