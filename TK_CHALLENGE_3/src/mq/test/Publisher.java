/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mq.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.log4j.BasicConfigurator;

/**
 *
 * @author tk1
 */
public class Publisher {
    private TopicSession pubSession;
    private TopicPublisher publisher;
    private TopicConnection connection;
    
    public Publisher(String TopicName,String username) throws NamingException, JMSException{
        
        Properties props = new Properties();
        props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.setProperty(Context.PROVIDER_URL, "vm://localhost");
        javax.naming.Context ctx = new InitialContext(props);
        TopicConnectionFactory factory = (TopicConnectionFactory) ctx.lookup("ConnectionFactory");

         connection = factory.createTopicConnection();
         connection.start();
 
         pubSession = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
          Topic mytopic  = pubSession.createTopic(TopicName);
         System.out.println("Topic "+TopicName+" created");
        
         publisher = pubSession.createPublisher(mytopic);
          System.out.println("Topic "+TopicName+" published");
 
        
        TextMessage message = pubSession.createTextMessage();
        message.setObjectProperty(TopicName, mytopic.getTopicName());
        message.setText(username + ": Howdy Friend!");
        System.out.println("Message  'Howdy Friend!' published");
        publisher.publish(message);
         connection.close();
    }
    
    public static void main(String[] args) {
        BasicConfigurator.configure();
        try {
           
            Publisher demo = new Publisher("myTopic","me" );
             
           
        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }
    }

}


    

