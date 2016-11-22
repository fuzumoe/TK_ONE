package services;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import models.Order;
import models.Ticket;
import stubs.RepositoryStub;

/**
 *
 * @author adam
 */
@Path("/RESTful")
public class RESTfulService {

    /**
     * ***************************
     * Repositories **************
     */
    public RepositoryStub repository = RepositoryStub.staticInstance;

    /**
     * ***************************
     * POST METHODS **************
     */
    /**
     *
     * @param ticketId
     * @param amount
     * @return
     */
    @GET
    @Path("checkTicket/{ticketId}/{amount}")
    @Produces({MediaType.APPLICATION_JSON,
        MediaType.APPLICATION_XML})
    public boolean checTicket(@PathParam("ticketId") int ticketId, 
                              @PathParam("amount") int amount) {
        
        System.out.println("=>::Request[RESTful-Cleint]::..:checTicket.....:>>");
        System.out.println("=>::Response::.::..:checTicket..:Complteted::");
        return repository.getTicketsRepository()
                         .checkAvailablity(ticketId, amount);
    }
    
    @GET
    @Path("order/{ticket}/{amount}/{coast}/{user}")
    @Produces({MediaType.APPLICATION_JSON,
        MediaType.APPLICATION_XML})
    public boolean orderTicket(@PathParam("ticket") String ticket, 
                               @PathParam("amount") int amount,
                               @PathParam("coast")double coast,
                               @PathParam("user") String user) {
    
        boolean check = true;
        System.out.println("=>::Request[RESTful-Cleint]::..:orderTicket in Progress.....:>>");
        
         Ticket  tmp = repository.getTicketsRepository().getTicketByTicket(ticket);
         System.out.println(tmp.getPrice());
         int index = repository.getTicketsRepository().getAllTickets().indexOf(tmp);
         System.out.println(index);
         int preAmount = repository.getTicketsRepository().getAllTickets().get(index).getAmount();
         if(preAmount < amount){
             check = false;
         }else  if(preAmount >= amount){
         repository.getTicketsRepository().getAllTickets().get(index).setAmount(preAmount-amount);
         repository.getOrdersRepository().getAllOrders().add(new Order(ticket, user, amount, coast));
          check = true;
         }
       System.out.println("=>::Response::.::..:orderTicket..:Complteted::");
      
        return check;
    }

    /**
     * **************************
     * GET METHODS **************
     */
    /**
     * *************************
     * Tickets *****************
     */
    /**
     *
     * @return
     */
    @GET
    @Path("/getAllTickets")
    @Produces({MediaType.APPLICATION_XML,
        MediaType.APPLICATION_JSON})
    public List<Ticket> getAllTickets() {
        System.out.println("=>::Request[RESTful-Cleint]::..:getAllTickets.....:>>");
        System.out.println("=>::Response::.::..:getAllTickets..:Complteted::");
        return repository.getTicketsRepository().getAllTickets();
    }

    /**
     *
     * @param ticket
     * @return
     */
    @GET
    @Path("getTickets/{ticketId}")
    @Produces({MediaType.APPLICATION_XML,
        MediaType.APPLICATION_JSON})
    public Ticket geckTicketById(@PathParam("ticketId") int ticket) {

        System.out.println("=>::Request[RESTful-Cleint]::..:getTicketById.....:>>");
        System.out.println("=>::Response::.::..:getTicketById..:Complteted::");
        return repository.getTicketsRepository().getTicketByID(ticket);

    }

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
    @GET
    @Path("authenticateUser/{username}/{password}")
   @Produces({MediaType.APPLICATION_JSON,
              MediaType.APPLICATION_XML})
    public String  getUserAuthenticate(@PathParam("username") String username,
            @PathParam("password") String password) {
        System.out.println("=>::Request[RESTful-Cleint]::..:getUserAuthenticate.....:>>");
        System.out.println("=>::Response::.::..:getUserAuthenticate..:Complteted::");
        
        return repository.getUsersRepository().userAuthenticate(username, password)+"";
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    @GET
    @Path("getUserID/{username}/{password}/ID")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public int getUserUserId(@PathParam("username") String username,
            @PathParam("password") String password) {

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
    @GET
    @Path("getUserFullName/{username}/{password}/Name")
    @Produces({ MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String getUserUserFullName(@PathParam("username") String username,
            @PathParam("password") String password) {
        repository.tempBool
                = repository.getUsersRepository().userAuthenticate(username, password);
        if (true == repository.tempBool) {

            repository.userFullName = repository.getUsersRepository().
                    getUserFullName(username);
        }
      
        return repository.userFullName;
    }
}
