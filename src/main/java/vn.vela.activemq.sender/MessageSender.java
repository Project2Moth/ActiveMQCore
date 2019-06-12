package vn.vela.activemq.sender;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageSender {

  //Lay URL mac dinh cua apache activeMQ
  private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
  //mac dinh broker URL la : tcp://localhost:61616
  private static String subject = "JCG_QUEUE";

  public static void main(String[] args) {
    //De JMS connection va start no len
    ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
    try {
      Connection connection = connectionFactory.createConnection();
      connection.start();
      //tao phien giao dich transaction
      Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//      Destination destination = session.createQueue(subject);
      Destination destination = session.createTopic(subject);
      MessageProducer producer = session.createProducer(destination);
      TextMessage message = session.createTextMessage("Hello, welcome to activeMq");
      //send message
      producer.send(message);
      System.out.println("print ' " + message.getText() + " ' ");
      connection.close();
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }

}
