package models;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author adam
 */
@XmlRootElement
public class Ticket {

    private String ticket;
    private int amount;
    private double price;
    private int id;

    /**
     *
     * @param ticket
     * @param amount
     * @param price
     */
    public Ticket(int id, String ticket, int amount, double price) {
        this.ticket = ticket;
        this.amount = amount;
        this.price = price;
        this.id = id;

    }

    /**
     *
     */
    public Ticket() {
    }

    /**
     *
     * @return
     */
    public String getTicket() {
        return ticket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @param ticket
     */
    public void setTicket(String ticket) {
        this.ticket = ticket;
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

    @Override
    public String toString() {
        return "Ticket :" + getTicket() + " Amount :" + getAmount();
    }

}
