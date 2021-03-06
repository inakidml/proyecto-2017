package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//Interface con alguna función estática para la base de datos
public class InterfaceMySQL {
	// guardamos un registro
	public static boolean insertRegistro(float temp, float humedad, float luz) throws SQLException {
		Connection conn = null;
		boolean result = false;

		try {
			// Establecemos conexión
			conn = getConexion();

			// Query
			String query = "INSERT INTO registro (temperatura, humedad, luz) values(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query);

			// A�adimos los datos
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
			System.out.println(ex);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

		// Devolvemos el resultado
		return result;
	}

	// guardamos la configuración del termostato
	public static boolean insertTermostato(ConfTermostato confTermostato) throws SQLException {
		Connection conn = null;
		boolean result = false;

		try {
			// Establecemos conexion
			conn = getConexion();

			// Query
			String query = "INSERT INTO termostato (temperatura, controlAct, reglasPres) values(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			float temp = confTermostato.getTemperatura();
			int reglas = confTermostato.convertirBooleanInt(confTermostato.isReglas());
			int presencia = confTermostato.convertirBooleanInt(confTermostato.isPresencia());
			// A�adimos los datos
			ps.setFloat(1, temp);
			ps.setInt(2, reglas);
			ps.setInt(3, presencia);

			// Ejecutamos la insert
			int action = ps.executeUpdate();

			// Comprobamos si insert ha funcionado
			if (action > 0) {
				result = true;
			}
			// Cerrar conexion
			conn.close();
		} catch (SQLException ex) {
			System.out.println(ex);
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

	public static ConfTermostato getTermostato() {
		Connection conn = null;
		boolean result = false;

		// Establecemos conexion
		conn = getConexion();

		String query = "SELECT * from termostato ORDER BY idTermostato DESC LIMIT 1";

		// create the java statement
		Statement st;
		try {
			st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset
			float temperatura = 0;
			boolean reglas = false;
			boolean presencia = false;

			while (rs.next()) {
				temperatura = Float.parseFloat(rs.getString("temperatura"));

				if (rs.getString("controlAct").equals("1")) {
					reglas = true;
				} else {
					reglas = false;
				}

				if (rs.getString("reglasPres").equals("1")) {
					presencia = true;
				} else {
					presencia = false;
				}

			}
			st.close();
			return new ConfTermostato(temperatura, reglas, presencia);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
