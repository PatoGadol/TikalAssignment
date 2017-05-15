package com.tikal.utils;


import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Date;

/**
 * Created by pavel_sopher on 11/05/2017.
 */
public class Mail {

    public static void sendMail(String reciever, String message) throws NamingException, JMSException {
        // create sender and receiver
        InitialContext ctx = new InitialContext();
        QueueConnectionFactory factory = (QueueConnectionFactory)
                ctx.lookup("queue/connectionFactory");
        Queue queue = (Queue) ctx.lookup("queue/queue0");
        QueueConnection conn = factory.createQueueConnection();
        QueueSession session = conn.createQueueSession(false,
                Session.AUTO_ACKNOWLEDGE);
        conn.start();

        // first arg is recipient, second arg is message
        if (reciever != null && message != null) {
            QueueSender sender = session.createSender(queue);
            TextMessage msg = session.createTextMessage(message);
            msg.setStringProperty("sender", System.getProperty("user.name"));
            msg.setStringProperty("to", reciever);
            msg.setStringProperty("date", new Date().toString());
            sender.send(msg);
        } else {
            String selector = "to = '" + System.getProperty("user.name") + "'";
            QueueReceiver receiver = session.createReceiver(queue, selector);
            TextMessage msg = (TextMessage) receiver.receive(500);
            if (msg != null) {
                System.out.println("From: " + msg.getStringProperty("sender"));
                System.out.println("To: " + msg.getStringProperty("to"));
                System.out.println("Date: " + msg.getStringProperty("date"));
                System.out.println("\n" + msg.getText());
            } else {
                System.out.println("no messages");
            }
        }
    }
}
