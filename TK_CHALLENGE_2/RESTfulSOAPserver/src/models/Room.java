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

    private int id; //room Id
    private String room; // room name
    private int amount; // room amount
    private double price; //room price
    private String available; //room availablity date
    private String additionalService;  // room additional services  
    DateFormat df;

    /**
     * Default Constructor
     */
    public Room() {
    }

    /**
     * Variant constructor
     *
     * @param room
     * @param amount
     * @param price
     * @param available
     * @param additionalService
     * @param id
     */
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

    /**
     *
     * @return
     */
    public String getAdditionalService() {
        return additionalService;
    }

    /**
     *
     * @param additionalService
     */
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

    /**
     *
     * @param dateInObject
     * @return
     */
    public String getStringFromDate(Date dateInObject) {

        return df.format(dateInObject);
    }

    /**
     *
     * @param dateInString
     * @return
     */
    public Date getDateFromString(String dateInString) {

        Date dt = null;
        try {
            dt = (Date) df.parse(dateInString);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return dt;
    }
    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "Id:" + getId()
                + " Room :" + getRoom()
                + " Price: " + getPrice()
                + " Amount :" + getAmount()
                + " Additional Service" + getAdditionalService()
                + " Available Date:" + getAvailable() + " ";
    }

}
