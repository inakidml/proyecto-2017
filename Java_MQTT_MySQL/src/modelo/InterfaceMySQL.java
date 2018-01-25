package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class InterfaceMySQL {
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
	
	private static Connection getConexion() {

		try {

			return ConectorMysql.getCon_mysql_jdbc();

		} catch (ClassNotFoundException | SQLException e) {
			return null;

		}
	}
}
