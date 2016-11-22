 
package repositories;

import models.Ticket;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
/**
 *
 * @author adam
 */
 @XmlRootElement
public interface TicketsRepository {

    /**
     *
     * @return
     */
       @XmlElement
    List<Ticket> getAllTickets();

    /**
     *
     * @param ticketId
     * @return
     */
    Ticket  getTicketByID(int ticketId);
    Ticket  getTicketByTicket(String  ticket);

    /**
     *
     * @param ticket
     */
    void addTicket(Ticket ticket);

    /**
     *
     * @param ticket
     */
    void removeTicket(Ticket ticket);

    /**
     *
     * @param ticketId
     * @param value
     */
    void updateTicketAmount(int ticketId,int value);

    /**
     *
     * @param ticketId
     * @param vaule
     */
    void updateTicketPrice(int ticketId,double vaule);

    /**
     *
     * @param ticket
     * @param value
     */
    void updateTicketAmount(Ticket ticket,int value);

    /**
     *
     * @param ticket
     * @param valeu
     */
    void updateTicketPrice(Ticket ticket,double valeu);

    /**
     *
     * @param ticketId
     */
    void removeTicket(int ticketId);
    
 
    boolean checkAvailablity(int ticket, int amount);
    
}
