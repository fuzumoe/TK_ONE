




import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @authors Group C 
 */

public interface IGameClient extends Remote, Serializable{
        /**
         * recieve players name and score
         * @param playerName
         * @param newPoints
         * @throws RemoteException 
         */
	void recieveMinonsFed(String playerName, int newPoints) throws RemoteException;
        /**
         *  recieve Minion position cordinate (x,y)
         * @param x :: x axis pos cordinate
         * @param y :: y axis pos cordinate
         * @throws RemoteException 
         */
	void recieveMinionPosition(int x, int y) throws RemoteException;
        
}
