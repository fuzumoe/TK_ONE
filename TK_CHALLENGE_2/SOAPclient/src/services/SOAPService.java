 
package services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import models.Room;
 

/**
 *
 * @author adam
 */
@WebService(name = "SOAP",portName = "SOAP")
@SOAPBinding(style = Style.RPC)
public interface SOAPService {
    
    @WebMethod(operationName = "SayHello")
    @WebResult(partName = "return")
    public String sayHello(@WebParam(name = "name",partName ="name") String name);
 
    @WebMethod(operationName = "getTickets")
    public Room[] getAllRooms();
    
    @WebMethod(operationName = "checkAvailablity")
    public boolean checTicket(@WebParam(name = "ticketId")
            int ticket, @WebParam(name = "amount") int amount);
    

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
    @WebMethod(operationName = "authenticateUser")
    public boolean getUserAuthenticate(@WebParam(name = "username") String username,
            @WebParam(name = "password") String password) ;
    /**
     *
     * @param username
     * @param password
     * @return
     */
    @WebMethod(operationName = "getUserID")
    public int getUserUserId(@WebParam(name = "username") String username,
            @WebParam(name = "password") String password) ;

    /**
     *
     * @param username
     * @param password
     * @return
     */
    @WebMethod(operationName = "getUserFullName")
    public String getUserUserFullName(@WebParam(name = "username") String username,
            @WebParam(name = "password") String password) ;
    
    
      public boolean orderRoom(  @WebParam(name ="room") String room, 
                                   @WebParam(name ="amount") int amount,
                                   @WebParam(name ="coast")double coast,
                                   @WebParam(name ="user") String user,
                                   @WebParam(name ="resdate")  String resdate);
      
}
