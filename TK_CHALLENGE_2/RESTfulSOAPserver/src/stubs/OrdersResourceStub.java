/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stubs;

import java.util.ArrayList;
import java.util.List;
import models.Order;
 
import repositories.OrdersRepository;

/**
 *
 * @author adam
 */
public class OrdersResourceStub implements OrdersRepository {

   
  
    public List<Order> orders;
  
   public static OrdersResourceStub  staticInstance =
                          new OrdersResourceStub();
    {
      orders = new ArrayList<>();
    }

 
    
    
    public OrdersResourceStub() {
    }
    
   
  
    @Override
    public List<Order> getAllOrders() {
        return orders;
     }

    @Override
    public void removeOrder(int orderIndex) {
        
        orders.remove(orders.get(orderIndex));
     }

    @Override
    public void removeOrderByUser(String user) {
        for(int i = 0; i < orders.size(); i++){
            if(orders.get(i).getOrderer().equals(user)){
               orders.remove(orders.get(i));
            }
        }
     }

    @Override
    public void removeOrderByTicket(String ticket) {
         for(int i = 0; i < orders.size(); i++){
            if(orders.get(i).getTicket().equals(ticket)){
               orders.remove(orders.get(i));
            }
        }
     }
    

    @Override
     public void  order(String user,String ticket,int amount,double coust){
            orders.add(new Order(ticket, user, amount, coust));
     }
    
    
    
}
