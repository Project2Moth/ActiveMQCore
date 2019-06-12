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

public class MessageReceiver {

  //cung ket noi vao 1 cong
  private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

  //ten queue se nhan tin
  private static String subject = "JCG_QUE";

  public static void main(String[] args) throws JMSException {
    //tao ket noi
    ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
    Connection connection = connectionFactory.createConnection();
    connection.start();

    //tao phien giao dich
    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//    Destination destination = session.createQueue(subject);
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
