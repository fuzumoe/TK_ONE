package mq.test;

/**
 *
 * @author tk1
 */
import javax.jms.*;
import javax.naming.*;

import org.apache.log4j.BasicConfigurator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Subscriber implements javax.jms.MessageListener {

    private TopicSession pubSession;
    private TopicConnection connection;
    private String topicName;
    private Topic mytopic;

    /* Establish JMS publisher and subscriber */
    public Subscriber(String topicName, String username) throws Exception {
        this.topicName = topicName;
          Properties props = new Properties();
        props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.setProperty(Context.PROVIDER_URL, "vm://localhost");
        javax.naming.Context ctx = new InitialContext(props);
        TopicConnectionFactory factory = (TopicConnectionFactory) ctx.lookup("connectionFactory");

         connection = factory.createTopicConnection();
         connection.start();
          pubSession = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
         
          try{
          mytopic =  (Topic) ctx.lookup("MyTopic");  
          }catch(Exception e){
              e.printStackTrace();
          }
          
          TopicSubscriber subscriber = pubSession.createSubscriber(mytopic);
          subscriber.setMessageListener(this);


        

    }

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        String text = null;
        try {
            text = textMessage.getText();
            
        } catch (JMSException ex) {
            Logger.getLogger(Subscriber.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(text);
    }

    public static void main(String[] args) throws Exception {
        BasicConfigurator.configure();
        
           
            Subscriber demo = new Subscriber("myTopic","you" );
            BufferedReader commandLine = new BufferedReader(
                    new InputStreamReader(System.in));
            while (true) {
                String s = commandLine.readLine();
                if (s.equalsIgnoreCase("exit")) {
                    demo.connection.close();
                    System.exit(0);

                }
            }
        
    }

}
