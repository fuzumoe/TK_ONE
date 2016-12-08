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
    public void removeOrderByName(String room) {
         for(int i = 0; i < orders.size(); i++){
            if(orders.get(i).getRoom().equals(room)){
               orders.remove(orders.get(i));
            }
        }
     }
    

    @Override
     public void  order(String user,String room,int amount,double coust, String reserveDate){
//             public Order(String room, String orderer, int amount, double coast, String reserveDate ) {

            orders.add(new Order(room, user, amount, coust,reserveDate));
     }
    
    
    
}
