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
public class Order {

    private String room;
    private String orderer;
    private int amount;
    private double coast;
    private String reserveDate;
     DateFormat df;
    
    public Order() {
    }
 
    
    public Order(String room, String orderer, int amount, double coast, String reserveDate ) {
        this.room = room;
        this.orderer = orderer;
        this.amount = amount;
        this.coast = coast;
        this.reserveDate = reserveDate;
        this.df = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",
            Locale.ENGLISH);
    }
    
   
     public String getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(String reserveDate) {
        this.reserveDate = reserveDate;
    }

    public int getAmount() {
        return amount;
    }

    public double getCoast() {
        return coast;
    }

    public String getOrderer() {
        return orderer;
    }

    public String getRoom() {
        return room;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCoast(double coast) {
        this.coast = coast;
    }

    public void setOrderer(String orderer) {
        this.orderer = orderer;
    }

    public void setRoom(String room) {
        this.room = room;
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
        return "Order{" + "room=" + room + ", orderer=" + orderer + ", amount=" + amount + ", coast=" + coast + ", reserveDate=" + reserveDate + '}';
    }
    

}
