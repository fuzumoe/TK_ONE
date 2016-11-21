


import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author Group C
 */
public class MinionsController {
    //server's stub From the RMI client
    private static IGameServer serverStub = null;
    //cleint's stub From the RMI Client
    private static IGameClient clientStub = null;
    // Specification of the object to be serialized and unserialized 
    private IGameClientImpl clientImpl = null;
    // the cleints  game view
    private MinionsView mainView;
    // the clients login view
    private PlugInView pluginView;
    /**
    * Contructor :: constructs the Client side of the app
    */
    public MinionsController() throws RemoteException {
          try {
         //set the plugin view
         this.pluginView = new PlugInView(this);
         // set the Game main view
         this.mainView = new MinionsView(this);
         // clients stub of the server
         this.clientImpl = new IGameClientImpl();
         // serialize and  prepare to push object to RMI server
         clientStub = (IGameClient) UnicastRemoteObject.exportObject(clientImpl, 0);
        //RMI registry object
         Registry reg = LocateRegistry.getRegistry(2005);
        //look up for RMI server
         serverStub = (IGameServer) reg.lookup("IGame");
         //set the controller for the RMI server to contact to
         this.clientImpl.setMinionsController(this);
         //set the plugin view visible
         this.pluginView.setVisible(true);
       } catch (Exception e) {
            System.err.println("Client exception thrown: " + e.toString());
           e.printStackTrace();
        }
    }


    /* =================================================================*/
    /**
     ******************* View Controller *****************************
     */
    /* 
     /* =================================================================*/
     /**
     * Set coordinates of minion in the JFrame
     */
    public void setMinionCordinates(Coordinates coordinates) {
        this.mainView.getMinionLabel().setLocation(coordinates.getX(), coordinates.getY());
    }

 

    /**
     * set players nick name
     */
    public void pluginPlayer() {
        try {
            this.pluginView.setPlayersName(this.pluginView.getNickNameTxt().getText());
            if (!"".equals(this.pluginView.getPlayersName()) || null != this.pluginView.getPlayersName()) {
                this.clientImpl.getGameData().setPlayerName(this.pluginView.getPlayersName());
                this.clientImpl.getGameData().setScore(0);
                MinionsController.serverStub.login(this.pluginView.getPlayersName(), clientStub);
                this.mainView.setTitle(":: Feed The Minions :: >"+this.pluginView.getPlayersName());
                System.out.println("pluged in as:" + clientImpl.getGameData().getPlayerName());
                this.pluginView.setVisible(false);
               this.mainView.setVisible(true);
            }
        } catch (Exception e) {
            System.out.println("Remote method exception thrown: " + e.getMessage());
        }
    }

    /**
     * feed the minion
     */
    public void feedMinion() {
        try {
            String name = clientImpl.getGameData().getPlayerName();
            serverStub.feedMinion(name);
        } catch (RemoteException ex) {
            Logger.getLogger(MinionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    
    @Override
    public String toString() {
        return "MainController{" + "clientImpl=" + clientImpl + '}';
    }
    
  

}
