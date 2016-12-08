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

    private String room; //room name
    private String orderer; // orderer name
    private int amount; // order amount
    private double coast; // order coast
    private String reserveDate; // reservation date
    public DateFormat df; //date formatter

    /**
     * Default Constructor
     */
    public Order() {
    }

    /**
     * Variant Constructor
     *
     * @param room
     * @param orderer
     * @param amount
     * @param coast
     * @param reserveDate
     */
    public Order(String room, String orderer, int amount, double coast, String reserveDate) {
        this.room = room;
        this.orderer = orderer;
        this.amount = amount;
        this.coast = coast;
        this.reserveDate = reserveDate;
        this.df = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",
                Locale.ENGLISH);
    }

    /**
     *
     * @return
     */
    public String getReserveDate() {
        return reserveDate;
    }

    /**
     *
     * @param reserveDate
     */
    public void setReserveDate(String reserveDate) {
        this.reserveDate = reserveDate;
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
     * @return
     */
    public double getCoast() {
        return coast;
    }

    public String getOrderer() {
        return orderer;
    }

    /**
     *
     * @return
     */
    public String getRoom() {
        return room;
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
     * @param coast
     */
    public void setCoast(double coast) {
        this.coast = coast;
    }

    /**
     *
     * @param orderer
     */
    public void setOrderer(String orderer) {
        this.orderer = orderer;
    }

    /**
     *
     * @param room
     */
    public void setRoom(String room) {
        this.room = room;
    }

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
        return "Order{" + "room=" + room + ", orderer=" + orderer + ", amount=" + amount + ", coast=" + coast + ", reserveDate=" + reserveDate + '}';
    }

}
