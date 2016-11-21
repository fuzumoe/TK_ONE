 
package cleint;

import com.sun.jersey.api.client.WebResource;
import java.util.List;
import javax.ws.rs.client.Client;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import models.Ticket;

/**
 *
 * @author adam 
 */
public class RESTCleint {

    private Client cleint;
    private WebTarget target;
    private String TARGET = "http://localhost:8080/services/SoccerTicketsService/RESTful";
    WebResource webResource;

    public RESTCleint() {

        cleint = ClientBuilder.newClient();
        target = cleint.target(TARGET);

    }

    public List<Ticket> getTicketsList() {
        System.out.println("<<::Requesting<< List of availablable Tickets from RESTful Ticket Services....");
        List<Ticket> response
                = target.path("getAllTickets").
                request(MediaType.APPLICATION_JSON).
                get(new GenericType<List<Ticket>>() {
                });
        System.out.println(">>::Recieved<< List of availablable Tickets from RESTful Ticket Services....::");
        return response;
    }

    public String getUserAuthenticate(String username, String password) {
        System.out.println("<<::Requesting<<  Authenticating " + username + "  by RESTful Ticket Services....");
        String response
                = target.path("authenticateUser/" + username + "/" + password).
                request(MediaType.APPLICATION_JSON).
                get(String.class);
        System.out.println(">>::Recieved<< User Authenticated successfully by RESTful Ticket Services....::");
        return response;

    }

    public boolean checkTicketById(Ticket ticket) {

        boolean response
                = target.path("getTickets/{ticketId}/" + ticket).
                request(MediaType.APPLICATION_JSON).
                get(boolean.class);
        return response;
    }

 

    public int getUserUserId(String username,
            String password, int ID) {

        int response
                = target.path("getUserID/" + username + "/" + password + "/" + ID)
                .
                request(MediaType.APPLICATION_JSON).
                get(Integer.class);
        return response;
    }

    public String getUserUserFullName(String username,
            String password) {
        System.out.println("<<::Requesting<< User's Full Name from RESTful Ticket Services....");

        String response
                = target.path("getUserFullName/" + username + "/" + password + "/Name").
                request(MediaType.APPLICATION_JSON).
                get(String.class);
        System.out.println(">>::Recieved<< User's Full Name from RESTful Ticket Services....::");
        return response;
    }
  public String orderTicket( String ticket,  int amount,double coast,  String user) {
      
        System.out.println("<<::Requesting<<  orderTicket " + ticket + "  by RESTful Ticket Services....");
        String response
                = target.path("order/"+ticket+"/"+amount+"/"+coast+"/"+user).
                request(MediaType.APPLICATION_JSON).
                get(String.class);
        System.out.println(">>::Recieved<< Response successfully by RESTful Ticket Services....::");
        return response;
  }
  
 

}

 