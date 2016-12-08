 
package cleint;

import com.sun.jersey.api.client.WebResource;
import java.util.List;
import javax.ws.rs.client.Client;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import models.Room;

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

    public List<Room> getTicketsList() {
        System.out.println("<<::Requesting<< List of availablable Tickets from RESTful Ticket Services....");
        List<Room> response
                = target.path("getAllTickets").
                request(MediaType.APPLICATION_JSON).
                get(new GenericType<List<Room>>() {
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

    public boolean checkTicketById(Room ticket) {

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
  public String orderRoom( String room,  int amount,double coast,  String user,String resdate) {
 
        System.out.println("<<::Requesting<<  orderTicket " + room + "  by RESTful Ticket Services....");
        String response
                = target.path("order/"+room+"/"+amount+"/"+coast+"/"+user+"/"+resdate).
                request(MediaType.APPLICATION_JSON).
                get(String.class);
        System.out.println(">>::Recieved<< Response successfully by RESTful Ticket Services....::");
        return response;
  }
  
 

}

 