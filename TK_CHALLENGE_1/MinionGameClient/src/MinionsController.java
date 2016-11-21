


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

    private static IGameServer serverStub = null;
    private static IGameClient clientStub = null;
    private IGameClientImpl clientImpl = null;
    private static DefaultListModel<String> data = null;
    private MinionsView mainView;
    private PlugInView pluginView;

    public MinionsController() throws RemoteException {
          try {
         this.pluginView = new PlugInView(this);
         this.mainView = new MinionsView(this);

              this.clientImpl = new IGameClientImpl();
             clientStub = (IGameClient) UnicastRemoteObject.exportObject(clientImpl, 0);
        
            Registry reg = LocateRegistry.getRegistry(2005);
            serverStub = (IGameServer) reg.lookup("IGame");
 
           this.clientImpl.setMinionsController(this);
           this.pluginView.setVisible(true);
        
         
       } catch (Exception e) {
            System.err.println("Client exception thrown: " + e.toString());
             e.printStackTrace();
        }
    }


    /* =================================================================*/

    /**
     ******************* RMI GETTERS AND SETTERS *********************
     */
    /* 
     /* ==============================================================*/
    public static IGameServer getServerStub() {
        return serverStub;
    }

    public static IGameClient getClientStub() {
        return clientStub;
    }

    public IGameClientImpl getClientImpl() {
        return clientImpl;
    }

    public static void setServerStub(IGameServer serverStub) {
        MinionsController.serverStub = serverStub;
    }

    public static void setClientStub(IGameClient clientStub) {
        MinionsController.clientStub = clientStub;
    }

    public void setClientImpl(IGameClientImpl clientImpl) {
        this.clientImpl = clientImpl;
    }

    /* =================================================================*/
    /**
     ******************* View Controller *****************************
     */
    /* 
     /* =================================================================*/
    public void setMinionCordinates(Coordinates coordinates) {
        this.mainView.getMinionLabel().setLocation(coordinates.getX(), coordinates.getY());
    }

    public DefaultListModel<String> updateAndGetListModel() {
        if (this.data == null) {
            data = new DefaultListModel<String>();
        }
        data.clear();
        data.addElement("");
        try {
            if (null != this.clientImpl.getGameData()) {
                if (null != this.clientImpl.getGameData().getPlayers()) {
                    for (Player info : this.clientImpl.getGameData().getPlayers().getPlayersList()) {
                        data.addElement(info.getPlayerName() + " : " + info.getPlayerScore());
                    }
                }
            }
        } catch (NullPointerException e) {

        }
        return data;
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
