
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @authors Group C
 */
public class ServerGamePos {
    // position cordinates
    private Coordinates posSize;
    // current cordinates of the Minion in the screen 
    private Coordinates currentMinionPosition;
    //static instance of this class
    private static ServerGamePos instance;
    //list of all privious position of the Minion
    private List<Coordinates> minionPriviuosPosition;
    //constructor
    private ServerGamePos() {
        this.posSize = new Coordinates(400, 450);
        this.minionPriviuosPosition = new ArrayList<Coordinates>();
        minionPriviuosPosition.add(generateRandomeMinionPos());
    }
    
    /**
     * 
     * @return :: static reference of this class
     */
    public static ServerGamePos getInstance() {
        // if no instance  of this class exists create one
        if (null == instance) {instance = new ServerGamePos();}
        return instance;
    }
    /**
     * 
     * @return :: current minion position
     */
    public Coordinates generateRandomeMinionPos() {
         // random number object
        Random randomNumber = new Random();
        //construct new conrdinates objet with random position
        this.currentMinionPosition = new Coordinates(
                randomNumber.nextInt(this.posSize.getX() + 1),
                randomNumber.nextInt(this.posSize.getY() + 1));
        return currentMinionPosition;
    }
    /**
     * return coordinates position
     * @return :: posSize
     */
    public Coordinates getGridSize() {
        return posSize;
    }
    /**
     * 
     * @param gridSize ::set coordinates position
     */
    public void setGridSize(Coordinates gridSize) {
        this.posSize = gridSize;
    }
    /**
     * 
     * @return :: current minion position
     */
    public Coordinates getCurrentMinionPosition() {
        return currentMinionPosition;
    }
    /**
     * 
     * @param :: set current minion position
     */
    public void setCurrentMinionPosition(Coordinates currentMinionPosition) {
        this.currentMinionPosition = currentMinionPosition;
    }

}
