
package mq.test;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage; 
 
public class Sender implements Runnable {
 
    private final ConnectionFactory factory = null;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private MessageProducer producer = null;
 
    public Sender(Connection connection) throws JMSException {
        this.connection = connection;
        this.connection.start();
 
    }
 
    public void sendMessage() {
 
        try {
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("MyTopic");
            producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage();
            message.setText("Hello ...This is a sample message..sending from FirstClient");
            producer.send(message);
            System.out.println("Sent: " + message.getText());
         } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        sendMessage();
      
       
             
           
        
     }
 
   
 
}
