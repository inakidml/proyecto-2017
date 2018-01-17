package controlador;

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

	public static void main(String[] args) throws Exception {

		MQTT mqtt = new MQTT();
		mqtt.setHost("10.1.100.100", 1883);
		BlockingConnection connection = mqtt.blockingConnection();
		connection.connect();
		float temp = 0f;
		float humedad = 0f;
		float luz = 0f;
		while (true) {
			Topic[] topics = { new Topic("TopicPrueba/+", QoS.AT_LEAST_ONCE) };
			byte[] qoses = connection.subscribe(topics);

			Message message = connection.receive();
			System.out.print(message.getTopic() + ": ");
			byte[] payload = message.getPayload();
			// process the message then:

			System.out.println(new String(payload));

			switch (message.getTopic()) {
			case "TopicPrueba/Temperatura":
				temp = Float.parseFloat(new String(message.getPayload()));
				break;
			case "TopicPrueba/Humedad":
				humedad = Float.parseFloat(new String(message.getPayload()));
				break;
			case "TopicPrueba/Luz":
				luz = Float.parseFloat(new String(message.getPayload()));
				break;
			default:
				break;
			}
			if (temp > 0 && humedad > 0 && luz > 0) {
				insertRegistro(temp, humedad, luz);
				temp = 0f;
				humedad = 0f;
				luz = 0f;
			}
			message.ack();
		}
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
			
			JOptionPane.showMessageDialog(null, ex, "Error",
					JOptionPane.ERROR_MESSAGE);

		} finally {
			if (conn != null) {
				conn.close();
			}
		}

		// Devolvemos el resultado
		return result;
	}

}
