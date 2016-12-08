
package stubs;

import repositories.RoomsRepository;
import models.Room;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author adam
 */
 @XmlRootElement
public class RoomsResourcesStub implements RoomsRepository {
//     private static List<Room>  tickets;
     private static List<Room>  rooms;
     public static RoomsResourcesStub staticInstance =
                          new RoomsResourcesStub();
             
    /**
     *
     * @return 
     */
    @Override
    public List<Room> getAllRooms(){
       
//         return tickets;
//        System.out.println(rooms.toString());
           return rooms;
    }
    
    static {

         rooms = new ArrayList<>();
         rooms.add(new Room("Double Room  ",3,400.0,new Date().toString(),"Free car",1));
         rooms.add(new Room("Presidential Room  ",6,400.0,new Date().toString(),"Free car",1));
         rooms.add(new Room("Family Room  ",3,500.0,new Date().toString(),"Free car",1));
        
    }

    /**
     *
     */
    public RoomsResourcesStub() {
        
         
    }
    

    /**
     *
     * @param roomId
     * @return
     */
    @Override
    public Room getRoomByID(int roomId) {
        Room room = null;
         for(int i = 0; i < rooms.size(); i++){
             if(rooms.get(i).getId() == roomId){
                 room = rooms.get(i);
             }
         }
       return room;
     }

    @Override
    public void addRoom(Room room) {
        rooms.add(room);
    }

    @Override
    public void removeRoom(Room room) {
         rooms.remove(room);
     }

    @Override
    public void updateRoomAmount(int roomId, int value) {
        rooms.get(roomId).setAmount(value);
    }

    @Override
    public void updateRoomPrice(int roomId, double vaule) {
         rooms.get(roomId).setPrice(vaule);
    }

    @Override
    public void updateRoomAmount(Room room, int value) {
       rooms.get(rooms.indexOf(room)).setAmount(value);
     }

    @Override
    public void updateRoomPrice(Room ticket, double valeu) {
       rooms.get(rooms.indexOf(ticket)).setPrice(valeu);
    }

    @Override
    public void removeRoom(int ticketId) {
       rooms.remove(ticketId);
    }

    @Override
   public  boolean checkAvailablity(int ticketId, int amount){
        
      
          boolean  check = true;
         
         for(int i = 0; i < rooms.size(); i++){
             if(rooms.get(i).getId() == ticketId){
               if(amount > rooms.get(i).getAmount()){
                   check = false;
                   System.out.println("amaount");
               }
             }
         }

         return check;
     }

    @Override
    public Room getRoomByName(String thisTicket) {
         
        Room ticket = null;
         for(int i = 0; i < rooms.size(); i++){
              if(rooms.get(i).getRoom().equals(thisTicket)){
                  ticket = rooms.get(i);
              }
             
         }
       return ticket;
    }
 
}
