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
 
import javax.jms.*;
import javax.naming.*;

import org.apache.log4j.BasicConfigurator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class DemoPublisherSubscriberModel implements javax.jms.MessageListener {

    private TopicSession pubSession;
    private TopicPublisher publisher;
    private TopicConnection connection;
    
  

public InitialContext createInitialContext(String resource) throws IOException, NamingException {
    InputStream is = getClass().getResourceAsStream(resource);
    Properties props = new Properties();
    try {
        props.load(is);
    } finally {
        is.close();
    }
    return new InitialContext(props);
}
//dynamicQueues/FOO.BAR
    /* Establish JMS publisher and subscriber */
    public DemoPublisherSubscriberModel(String topicName, String username,
            String password) throws Exception {
        // Obtain a JNDI connection
        Properties props = new Properties();
props.setProperty(Context.INITIAL_CONTEXT_FACTORY,"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
props.setProperty(Context.PROVIDER_URL,"tcp://localhost:61616");
        InitialContext jndi =  new InitialContext(props);

        // Look up a JMS connection factory
        TopicConnectionFactory conFactory = (TopicConnectionFactory) jndi.lookup("connectionFactry");

        // Create a JMS connection
        connection = conFactory.createTopicConnection(username, password);

        // Create JMS session objects for publisher and subscriber
        pubSession = connection.createTopicSession(false,
                Session.AUTO_ACKNOWLEDGE);
        TopicSession subSession = connection.createTopicSession(false,
                Session.AUTO_ACKNOWLEDGE);

        // Look up a JMS topic
        Topic chatTopic = (Topic) jndi.lookup(topicName);

        // Create a JMS publisher and subscriber
        publisher = pubSession.createPublisher(chatTopic);
        TopicSubscriber subscriber = subSession.createSubscriber(chatTopic);

        // Set a JMS message listener
        subscriber.setMessageListener(this);

        // Start the JMS connection; allows messages to be delivered
        connection.start();

        // Create and send message using topic publisher
        TextMessage message = pubSession.createTextMessage();
        message.setText(username +": Howdy Friends!");
        publisher.publish(message);

    }

    /*
  * A client can register a message listener with a consumer. A message
  * listener is similar to an event listener. Whenever a message arrives at
  * the destination, the JMS provider delivers the message by calling the
  * listener"s onMessage method, which acts on the contents of the message.
     */
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            System.out.println(text);
        } catch (JMSException jmse) {
            jmse.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BasicConfigurator.configure();
        try {
        
            DemoPublisherSubscriberModel demo = new DemoPublisherSubscriberModel(
                   "adm", "daa", "dsafas");

            BufferedReader commandLine = new java.io.BufferedReader(
                    new InputStreamReader(System.in));

            // closes the connection and exit the system when "exit" enters in
            // the command line
            while (true) {
                String s = commandLine.readLine();
                if (s.equalsIgnoreCase("exit"
                )) {
                    demo.connection.close();
                    System.exit(0);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
