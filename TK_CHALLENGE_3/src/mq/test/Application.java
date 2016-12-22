/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mq.test;

import javax.jms.JMSException;

/**
 *
 * @author tk1
 */
public class Application {

    public static void main(String[] args) throws JMSException {

        Connections conn = new Connections();
        Sender sender = new Sender(conn.getConnection());
        sender.run();
        
        Connections conn1 = new Connections();
        Receiver receiver = new Receiver(conn1.getConnection());
        receiver.run();
    }

}
