package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectorMysql {
//conector para la base de datos
	private static Connection con_mysql_jdbc;

	public static Connection getCon_mysql_jdbc() throws ClassNotFoundException, SQLException {
	
		// Cargar el driver
		Class.forName("com.mysql.jdbc.Driver");
		// Establecemos la conexion con la BD
		ConectorMysql.con_mysql_jdbc = DriverManager.getConnection("jdbc:mysql://192.168.33.10/Proyecto2018_db", "root", "root");
		return con_mysql_jdbc;
	}
}
