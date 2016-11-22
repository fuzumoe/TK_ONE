package models;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author adam
 */
@XmlRootElement
public class Order {

    private String ticket;
    private String orderer;
    private int amount;
    private double coast;

    public Order() {
    }

    public Order(String ticket, String orderer, int amount, double coast) {
        this.ticket = ticket;
        this.orderer = orderer;
        this.amount = amount;
        this.coast = coast;
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

    public String getTicket() {
        return ticket;
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

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

}
