

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
 /**
 *
 * @authors Group C 
 */
public class IGameClientImpl implements IGameClient {
    // game data and information
    private GameDI gameDI = new GameDI();
    //Clients GUI
    private MinionsController minionsController = null;
         /**
         * receive players name and score :: implementation
         * @param playerName
         * @param newPoints
         * @throws RemoteException 
         */
    @Override
    public void recieveMinonsFed(String playerName, int newPoints) throws RemoteException {
        gameDI.updatePlayersScore(playerName, newPoints);
             System.out.println("------------------");
            System.out.println(new SimpleDateFormat("yyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
             System.out.println("Player::"+ playerName +", Score::"+ newPoints);
             System.out.println("------------------");
        for(Player info : this.gameDI.getPlayers().getPlayersList()){
            System.out.println(info.getPlayerName());
        }
    }
       /**
         *  receive Minion position coordinate (x,y) :: implementation
         * @param x :: x axis pos coordinate
         * @param y :: y axis pos coordinate
         * @throws RemoteException 
         */
    @Override
    public void recieveMinionPosition(int x, int y) throws RemoteException {
        Coordinates coordinates = new Coordinates(x, y);
        gameDI.setCurrentMinionCoordinates(coordinates);
       this.minionsController.setMinionCordinates(coordinates);
        
    }
    /**
     *  return Game data and information object
     * @return GameDI
     */
    public GameDI getGameData() {
        return gameDI;
    }
   /**
    * set Game data and information object
    * @param gameData 
    */
    public void setGameData(GameDI gameData) {
        this.gameDI = gameData;
    }
    /**
     * return MinionsView GUI
     * @return  MinionsView
     */
    public MinionsController getMinionsController() {
        return minionsController;
    }
    /**
     * set MinionsView GUI
     * @param minionsGUI 
     */
    public void setMinionsController(MinionsController minionsController) {
        this.minionsController = minionsController;
    }

    

}
