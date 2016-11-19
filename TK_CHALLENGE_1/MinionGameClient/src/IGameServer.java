


import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 *
 * @authors Group C 
 */

public interface IGameServer extends Remote{
	
          /**
        * login :: plugout new player/'s to game
        * @param playerName
        * @param client
        * @throws RemoteException 
        */
	void login (String playerName, IGameClient client) throws RemoteException;
        /**
         * logout:: plugout player from game
         * @param playerName
         * @throws RemoteException 
         */
	void logout(String playerName) throws RemoteException;
        /**
         * feedMinion :: Feed Minion
         * @param playerName
         * @throws RemoteException 
         */
	void feedMinion(String playerName) throws RemoteException;

}
