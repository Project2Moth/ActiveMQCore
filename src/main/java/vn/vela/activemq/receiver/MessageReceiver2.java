package vn.vela.activemq.receiver;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageReceiver2 {

  private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
  private static String subject = "JCG_QUEUE";

  public static void main(String[] args) throws JMSException {
    ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
    Connection connection = connectionFactory.createConnection();
    connection.start();

    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    Destination destination = session.createTopic(subject);
    MessageConsumer consumer = session.createConsumer(destination);
    Message message = consumer.receive();
    if (message instanceof TextMessage) {
      TextMessage textMessage = (TextMessage) message;
      System.out.println(" Receive message ' " + textMessage.getText() + "'");
      connection.close();
    }

  }

}
