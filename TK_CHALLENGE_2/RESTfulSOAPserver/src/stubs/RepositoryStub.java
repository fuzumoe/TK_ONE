/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stubs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import models.Order;
import models.Room;
import models.User;
import repositories.OrdersRepository;
import repositories.RoomsRepository;
import repositories.UsersRepository;

/**
 *
 * @author adam
 */
 
 @XmlRootElement
public class RepositoryStub {
    /**
     * ***************************
     * Repositories **************
     */
    private final   RoomsRepository roomsRepository;
    private final   UsersRepository usersRepository;
    private final   OrdersRepository ordersRepository;
    public static   RepositoryStub staticInstance = new RepositoryStub();
    /**
     * ***************************
     * USER RELATED VARIABLES ****
     */
    public  int userID = 0;
    public String userFullName = null;

    /**
     * ***************************
     * ORDER RELATED VARIABLES ***
     */
    private final  List<Order> orders;
    private final  List<Room> rooms;
    private final  List<User> users;
    
                              
   
    // temp variables to be used for future use
    public User temUser = null; 
    public boolean tempBool;
    public  List<Room> tempTickets =null;
    
    
    /***
     *  ****** Repositories Constructor ************
     * 
     * @param ticketsRepository
     * @param usersRepository
     * @param ordersRepository 
     * 
    **/
    {
             roomsRepository
            = RoomsResourcesStub.staticInstance;
       usersRepository
           = UserResourceStub.staicInstance;
     ordersRepository
            = OrdersResourceStub.staticInstance;

    }
    public RepositoryStub() {
        
         orders = this.ordersRepository.getAllOrders();
         rooms = this.roomsRepository.getAllRooms();
         users = this.usersRepository.getAllUsers();
         tempTickets = rooms;
         
         
    }
    
    
 
     public String getThisDay() {
    
      DateFormat dateFormat;
                 dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
  
         return  dateFormat.format(new Date());
    }
     

   

    public OrdersRepository getOrdersRepository() {
        return ordersRepository;
    }

    public RoomsRepository getRoomsRepository() {
        return roomsRepository;
    }

    public UsersRepository getUsersRepository() {
        return usersRepository;
    }

    public List<User> getUsers() {
        return users;
    }
    
    public List<Room> getRooms() {
        return rooms;
    }
    public List<Order> getOrders() {
        return orders;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public int getUserID() {
        return userID;
    }
    
    
    
    
    /**
     * ***************************
     * MOCK METHODS **************
     *
     */
    /**
     *
     * @return
     */
    public List<Room> getMockTicketsList() {

        tempTickets.get(0).setAmount(1);
        tempTickets.get(1).setAmount(2);
        tempTickets.get(2).setAmount(3);

        return tempTickets;
    }

    /**
     *
     * @return
     */
    public List<Order> getMockOrdersList() {
        List<Order> tempOrders = orders;

        return tempOrders;
    }

    /**
     *
     * @return
     */
    public List<User> getMockUsersList() {
        List<User> tempUsers = users;
        return tempUsers;
    }

   

    
  
  
   
    
    
}
