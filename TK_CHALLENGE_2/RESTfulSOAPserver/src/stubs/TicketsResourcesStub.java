
package stubs;

import repositories.TicketsRepository;
import models.Ticket;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author adam
 */
 @XmlRootElement
public class TicketsResourcesStub implements TicketsRepository {
     private static List<Ticket>  tickets;
     public static TicketsResourcesStub staticInstance =
                          new TicketsResourcesStub();
             
    /**
     *
     * @return 
     */
    @Override
    public List<Ticket> getAllTickets(){
       
         return tickets;
    }
    
    static {
         tickets = new ArrayList<>();
         tickets.add(new Ticket(1,"Real Madrid vs Barcelona",3,400.0));
         tickets.add(new Ticket(2,"Liverpool vs Man United",3,500.0));
         tickets.add(new Ticket(3,"Darmstadt 98 vs Bayern Munich",3,500.0));
        
    }

    /**
     *
     */
    public TicketsResourcesStub() {
        
         
    }
    

    /**
     *
     * @param ticketId
     * @return
     */
    @Override
    public Ticket getTicketByID(int ticketId) {
        Ticket ticket = null;
         for(int i = 0; i < tickets.size(); i++){
             if(tickets.get(i).getId() == ticketId){
                 ticket = tickets.get(i);
             }
         }
       return ticket;
     }

    @Override
    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    @Override
    public void removeTicket(Ticket ticket) {
         tickets.remove(ticket);
     }

    @Override
    public void updateTicketAmount(int ticketId, int value) {
        tickets.get(ticketId).setAmount(value);
    }

    @Override
    public void updateTicketPrice(int ticketId, double vaule) {
         tickets.get(ticketId).setPrice(vaule);
    }

    @Override
    public void updateTicketAmount(Ticket ticket, int value) {
       tickets.get(tickets.indexOf(ticket)).setAmount(value);
     }

    @Override
    public void updateTicketPrice(Ticket ticket, double valeu) {
       tickets.get(tickets.indexOf(ticket)).setPrice(valeu);
    }

    @Override
    public void removeTicket(int ticketId) {
       tickets.remove(ticketId);
    }

    @Override
   public  boolean checkAvailablity(int ticketId, int amount){
        
      
          boolean  check = true;
         
         for(int i = 0; i < tickets.size(); i++){
             if(tickets.get(i).getId() == ticketId){
               if(amount > tickets.get(i).getAmount()){
                   check = false;
                   System.out.println("amaount");
               }
             }
         }

         return check;
     }

    @Override
    public Ticket getTicketByTicket(String thisTicket) {
         
        Ticket ticket = null;
         for(int i = 0; i < tickets.size(); i++){
              if(tickets.get(i).getTicket().equals(thisTicket)){
                  ticket = tickets.get(i);
              }
             
         }
       return ticket;
    }
 

     

    

 
}
