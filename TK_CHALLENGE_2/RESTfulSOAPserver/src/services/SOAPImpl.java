 
package services;


import javax.jws.WebService;
import models.Order;

import models.Room;
import stubs.RepositoryStub;

/**
 *
 * @author adam
 */ 

@WebService(endpointInterface = "services.SOAPService",serviceName = "SOAP",targetNamespace ="http://SoccerTicketsService/")

public class SOAPImpl implements SOAPService {

    /**
     * ***************************
     * Repositories **************
     */
    public RepositoryStub repository = RepositoryStub.staticInstance;

    /**
     *
     * @param name
     * @return
     */
   
    @Override
    public String sayHello(String name) {
        return "Hi " + name;
    }

    /**
     * *************************
     * Tickets *****************
     */
    /**
     *
     * @return
     */

    @Override
    public Room[] getAllRooms() {
        System.out.println("=>::Request[SOAP-Cleint]::..:getAllTickets.....:>>");
        System.out.println("=>::Response::.::..:getAllTickets..:Complteted::");
        return repository.getRoomsRepository().getAllRooms().
                toArray(new Room[repository.getRoomsRepository().getAllRooms().size()]);
    }


    @Override
    public boolean checTicket( int room, int amount) {
         System.out.println("=>::Request[SOAP-Cleint]::..:checTicket.....:>>");
         System.out.println("=>::Response::.::..:checTicket..:Complteted::");
         return repository.getRoomsRepository().checkAvailablity(room, amount);
    }
//    

    /**
     * ************************
     * USERS ******************
     */
    /**
     *
     * @param username
     * @param password
     * @return
     */

    @Override
    public boolean getUserAuthenticate( String username,
             String password) {
         System.out.println("=>::Request[SOAP-Cleint]::..:getUserAuthenticate.....:>>");
         System.out.println("=>::Response::.::..:getUserAuthenticate..:Complteted::");
        return repository.getUsersRepository().
                userAuthenticate(username, password);
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */

    @Override
    public int getUserUserId(String username,
              String password) {
         System.out.println("=>::Request[SOAP-Cleint]::..:getUserUserId.....:>>");
         System.out.println("=>::Response::.::..:getUserUserId..:Complteted::");
        repository.tempBool
                = repository.getUsersRepository().userAuthenticate(username, password);
        if (true == repository.tempBool) {

            repository.userID = repository.getUsersRepository().getUserId(username);
            System.out.println("request responed");
        }
        return repository.userID;
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public String getUserUserFullName(String username,
            String password) {
        
        
         System.out.println("=>::Request[SOAP-Cleint]::..:getUserUserFullName.....:>>");
         System.out.println("=>::Response::.::..:getUserUserFullName..:Complteted::");
        repository.tempBool
                = repository.getUsersRepository().userAuthenticate(username, password);
        if (true == repository.tempBool) {

            repository.userFullName = repository.getUsersRepository().
                    getUserFullName(username);
        }
        return repository.userFullName;
    }

    @Override
    public boolean orderRoom(String ticket, int amount, double coast, String user, String resdate) {
        boolean check = true;
        System.out.println("=>::Request[RESTful-Cleint]::..:orderTicket in Progress.....:>>");
        
         Room  tmp = repository.getRoomsRepository().getRoomByName(ticket);
         System.out.println(tmp.getPrice());
         int index = repository.getRoomsRepository().getAllRooms().indexOf(tmp);
         System.out.println(index);
         int preAmount = repository.getRoomsRepository().getAllRooms().get(index).getAmount();
         if(preAmount < amount){
             check = false;
         }else  if(preAmount >= amount){
         repository.getRoomsRepository().getAllRooms().get(index).setAmount(preAmount-amount);
          repository.getOrdersRepository().getAllOrders().add(new Order(ticket, user, amount, coast,resdate));
          check = true;
         }
       System.out.println("=>::Response::.::..:orderTicket..:Complteted::");
      
        return check;
    }

}
