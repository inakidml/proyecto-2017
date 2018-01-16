import java.net.URISyntaxException;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.Message;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		MQTT mqtt = new MQTT();
		mqtt.setHost("10.1.100.100", 1883);
		BlockingConnection connection = mqtt.blockingConnection();
		connection.connect();
		while (true) {
			Topic[] topics = { new Topic("TopicPrueba/+", QoS.AT_LEAST_ONCE) };
			byte[] qoses = connection.subscribe(topics);

			Message message = connection.receive();
			System.out.print(message.getTopic() + ": ");
			byte[] payload = message.getPayload();
			// process the message then:

			System.out.println(new String(payload));
			message.ack();
		}


	}

}
