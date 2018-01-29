package controlador;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.Message;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;

import modelo.InterfaceMySQL;

public class HiloMQTT extends Thread {
	
//Libreria MQTT para java que hemos utilizado
// https://github.com/fusesource/mqtt-client
	
	@Override
	public void run() {
		// conectamos al broker mosquitto
		MQTT mqtt = new MQTT();
		try {
			mqtt.setHost("127.0.0.1", 1883);
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		BlockingConnection connection = mqtt.blockingConnection();
		try {
			connection.connect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		float temp = 0f;
		float humedad = 0f;
		float luz = 0f;
		System.out.println("Pulse cualquier tecla para terminar el programa");
		// recogemos los datos del topic
		try {
			Topic[] topics = { new Topic("TopicIkerInaki/+", QoS.AT_LEAST_ONCE) };
			byte[] qoses = connection.subscribe(topics);

			while (System.in.available() == 0) {//loop hasta que pulsemos una tecla
				//recibimos mensaje, timeout para terminar bien
				Message message = connection.receive(10000, TimeUnit.MILLISECONDS);

				if (message != null) {
					byte[] payload = message.getPayload();

					switch (message.getTopic()) {
					case "TopicIkerInaki/Temperatura":
						temp = Float.parseFloat(new String(message.getPayload()));
						break;
					case "TopicIkerInaki/Humedad":
						humedad = Float.parseFloat(new String(message.getPayload()));
						break;
					case "TopicIkerInaki/Luz":
						luz = Float.parseFloat(new String(message.getPayload()));
						break;
					default:
						break;
					}
					//cuando hemos recibido los tres mensajes
					if (temp > 0 && humedad > 0 && luz > 0) {
						try {
							InterfaceMySQL.insertRegistro(temp, humedad, luz);
						} catch (SQLException e) {
							System.out.println("Fallo al insertar: " + e);
						}
						//System.out.print("Temperatura: " + temp + "ºC, Humedad: " + humedad + "%, luz: " + luz + "% \r");
						//pasamos los datos del instatnte al control del enchufe
						ControlTermostato.activarEnchufe(temp, luz);
						
						//reseteamos variables
						temp = 0f;
						humedad = 0f;
						luz = 0f;
					}
					message.ack();
				}
			}//termina while
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//fin del hilo
		System.out.println("Fin hilo MQTT");
	}
}
