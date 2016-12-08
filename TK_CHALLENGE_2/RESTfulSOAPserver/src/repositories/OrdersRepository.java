 
package repositories;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
import models.Order;
 

/**
 *
 * @author adam
 */
 @XmlRootElement
public interface OrdersRepository {

    /**
     *
     * @return
     */
       @XmlElement
    List<Order> getAllOrders();
    
    /**
     *
     */
     
    /**
     *
     * @param orderIndex
     */
    void  removeOrder(int orderIndex);

    /**
     *
     * @param user
     */
    void  removeOrderByUser(String user);

    /**
     *
     * @param room
     */
    void  removeOrderByName(String room);

    /**
     *
     * @param ticket
     * @param amount
     * @param coust
     * @param user
     */
    void  order(String user,String room,int amount,double coust, String reserveDate);

 
    
}
