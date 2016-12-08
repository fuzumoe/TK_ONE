 
package repositories;

import models.Room;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
/**
 *
 * @author adam
 */
 @XmlRootElement
public interface RoomsRepository {

    /**
     *
     * @return
     */
       @XmlElement
    List<Room> getAllRooms();

    /**
     *
     * @param roomId
     * @return
     */
    Room  getRoomByID(int roomId);
    Room  getRoomByName(String  room);

    /**
     *
     * @param room
     */
    void addRoom(Room room);

    /**
     *
     * @param room
     */
    void removeRoom(Room room);

    /**
     *
     * @param roomId
     * @param value
     */
    void updateRoomAmount(int roomId,int value);

    /**
     *
     * @param roomId
     * @param vaule
     */
    void updateRoomPrice(int roomId,double vaule);

    /**
     *
     * @param room
     * @param value
     */
    void updateRoomAmount(Room room,int value);

    /**
     *
     * @param room
     * @param valeu
     */
    void updateRoomPrice(Room room,double valeu);

    /**
     *
     * @param roomId
     */
    void removeRoom(int roomId);
    
 
    boolean checkAvailablity(int room, int amount);
    
}
