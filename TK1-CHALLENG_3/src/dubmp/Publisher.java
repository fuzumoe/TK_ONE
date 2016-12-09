
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dubmp;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author tk1
 */
public class Publisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(Publisher.class);

    private String clientId;
    private Connection connection;
    private Session session;
    private MessageProducer messageProducer;

    public void create(String clientId, String topicName) throws JMSException {
        this.clientId = clientId;

        // create a Connection Factory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_BROKER_URL);

        // create a Connection
        connection = connectionFactory.createConnection();
        connection.setClientID(clientId);

        // create a Session
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // create the Topic to which messages will be sent
        Topic topic = session.createTopic(topicName);

        // create a MessageProducer for sending messages
        messageProducer = session.createProducer(topic);
    }

    public void closeConnection() throws JMSException {
        connection.close();
    }

    public void sendName(String firstName, String lastName) throws JMSException {
        String text = firstName + " " + lastName;

        // create a JMS TextMessage
        TextMessage textMessage = session.createTextMessage(text);

        // send the message to the topic destination
        messageProducer.send(textMessage);

        LOGGER.debug(clientId + ": sent message with text='{}'", text);
    }
}
