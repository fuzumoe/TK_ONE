


import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @authors Group C 
 */

public class Server {
    //RMI Server constructor
    public Server() {  }
    
    /**
     * Main Method :: RUN RMI SERVER
     * @param args 
     */
    public static void main(String args[]) {
	
	try {
            //Instiate RMI game server
//            System.setProperty("java.rmi.server.codebase", "file:/C:/PROJECTX/bin/");
	    IGameServerImpl gameServerImpl = new IGameServerImpl();
            // instansiate marshaling RMI server
	    IGameServer stub = (IGameServer) UnicastRemoteObject.exportObject(gameServerImpl, 0);

        
	    Registry registry = LocateRegistry.createRegistry(2005);
	    registry.bind("IGame", stub);
          
            System.out.println(".....FEED THE MINIONS GAME SEREVER IS RUNNING......");

	} catch (Exception e) {
	    System.err.println("Server exception thrown: " + e.toString());
	    e.printStackTrace();
	}
    }

}
