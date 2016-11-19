

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @authors Group C 
 */
public interface IGameClient extends Remote, Serializable{
        /**
         * receive players name and score
         * @param playerName
         * @param newPoints
         * @throws RemoteException 
         */
	void recieveMinonsFed(String playerName, int newPoints) throws RemoteException;
        /**
         *  receive Minion position coordinate (x,y)
         * @param x :: x axis pos coordinate
         * @param y :: y axis pos coordinate
         * @throws RemoteException 
         */
	void recieveMinionPosition(int x, int y) throws RemoteException;
        
        
}
