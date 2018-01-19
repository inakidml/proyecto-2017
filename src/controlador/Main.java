package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.Message;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;

import modelo.ConectorMysql;

public class Main {
	private static Connection conector;
	private static boolean fin = false;

	public static void main(String[] args) throws Exception {
		
		//conectamos al broker mosquitto
		MQTT mqtt = new MQTT();
		mqtt.setHost("localhost", 1883);
		BlockingConnection connection = mqtt.blockingConnection();
		connection.connect();
		
		float temp = 0f;
		float humedad = 0f;
		float luz = 0f;
		System.out.println("Pulse cualquier tecla para terminar el programa");
		//recogemos los datos del topic
		while (System.in.available() == 0) {
			Topic[] topics = { new Topic("TopicIkerInaki/+", QoS.AT_LEAST_ONCE) };
			byte[] qoses = connection.subscribe(topics);
			Message message = connection.receive();
			//System.out.print(message.getTopic() + ": ");
			byte[] payload = message.getPayload();
			//System.out.print(new String(payload) + "\r");
			
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
			
			if (temp > 0 && humedad > 0 && luz > 0) {
				insertRegistro(temp, humedad, luz);
				System.out.print("Temperatura: "+ temp + "ºC, Humedad: "+ humedad+"%, luz: " + luz + "% \r");
				temp = 0f;
				humedad = 0f;
				luz = 0f;
			}
			message.ack();
		}
		System.out.println("Fin del programa");
	}

	private static Connection getConexion() {

		try {

			return ConectorMysql.getCon_mysql_jdbc();

		} catch (ClassNotFoundException | SQLException e) {
			return null;

		}
	}

	public static boolean insertRegistro(float temp, float humedad, float luz) throws SQLException {
		Connection conn = null;
		boolean result = false;

		try {
			// Establecemos conexion
			conn = getConexion();

			// Query
			String query = "INSERT INTO registro (temperatura, humedad, luz) values(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query);

			// Añadimos los datos
			ps.setFloat(1, temp);
			ps.setFloat(2, humedad);
			ps.setFloat(3, luz);

			// Ejecutamos la insert
			int action = ps.executeUpdate();

			// Comprobamos si insert ha funcionado
			if (action > 0) {
				result = true;
			}
			// Cerrar conexion
			conn.close();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

		// Devolvemos el resultado
		return result;
	}

}
