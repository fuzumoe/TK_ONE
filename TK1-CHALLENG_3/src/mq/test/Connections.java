/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mq.test;

/**
 *
 * @author tk1
 */


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Connections {
    private ConnectionFactory factory = null;
    private Connection connection = null;
    public Connections() throws JMSException{
           factory = new ActiveMQConnectionFactory(
                             "vm://localhost");
            connection = factory.createConnection();
            connection = factory.createConnection();
            connection.start();
    }
    
    public Connection getConnection(){
        return this.connection;
    }
    
}
