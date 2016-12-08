package models;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author adam
 */
@XmlRootElement
public class Room {
    private int id;
    private String room;
    private int amount;
    private double price;
    private String available;
    private String additionalService;    
    DateFormat df;

  
  /**
     *
     */
    public Room() {
    }

 

    public Room(String room, int amount, double price, String available, String additionalService, int id) {
        this.room = room;
        this.amount = amount;
        this.price = price;
        this.available = available;
        this.additionalService = additionalService;
        this.id = id;
        df = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",
                                            Locale.ENGLISH);
    }

    public String getAdditionalService() {
        return additionalService;
    }

    public void setAdditionalService(String additionalService) {
        this.additionalService = additionalService;
    }
    
    
  
    /**
     * 
     * @return 
     */
    public String getAvailable() {
        return available;
    }
    /**
     * 
     * @param available 
     */
    public void setAvailable(String available) {
        this.available = available;
    }
    
    
         
  
    /**
     *
     * @return
     */
    public String getRoom() {
        return room;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @param room
     */
    public void setRoom(String room) {
        this.room = room;
    }

    /**
     *
     * @return
     */
    public int getAmount() {

        return amount;
    }

    /**
     *
     * @param amount
     */
    public void setAmount(int amount) {

        this.amount = amount;
    }

    /**
     *
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     *
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
     public String getStringFromDate(Date dateInObject){
     
       return df.format(dateInObject);
   }
   public Date getDateFromString(String dateInString){
          
             Date dt =null ;
          try{
             dt = (Date) df.parse(dateInString); 
          }catch(Exception e){
              System.out.println(e.toString());
          }
       return  dt;
   }
    @Override
    public String toString() {
        return "Id:"+getId()
               +" Room :" + getRoom()
               +" Price: "+getPrice()
               +" Amount :" + getAmount()
               +" Additional Service" +getAdditionalService()
               +" Available Date:" + getAvailable()+ " ";
    }

}
