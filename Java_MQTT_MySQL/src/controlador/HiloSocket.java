package controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.sql.SQLException;

import modelo.ConfTermostato;
import modelo.InterfaceMySQL;

public class HiloSocket extends Thread {
	private boolean fin = false;

	@Override
	public void run() {

		ServerSocket servidor;
		Socket socket;
		InputStreamReader datosCliente;
		BufferedReader b;
		int puerto = 4455;
		try {
			servidor = new ServerSocket(puerto);
			servidor.setSoTimeout(10000);//timeout para poder terminar ordenadamente

			while (fin != true) {
				try {
					// System.out.println("Esperando al cliente...");
					socket = servidor.accept();
					// System.out.println("Cliente conectado");
					datosCliente = new InputStreamReader(socket.getInputStream());
					b = new BufferedReader(datosCliente);
					// EL CLIENTE ENVIA UN MENSAJE
					String textoRec = b.readLine();
					// System.out.println("Recibiendo del cliente:\n\t" + textoRec);
					if (textoRec != null) {
						if (!textoRec.equals("null")) {
							
							String[] textos = textoRec.split("\\?");//despues del ? del GET
							String[] datos = textos[1].split(",");//lo separo por comas
							String[] reglas = datos[1].split(" ");// limpio
							String[] presencia = datos[2].split(" ");// limpio, quito HTTP1.1....
							
//							Debug							
//							System.out.println("T� termostato: " + datos[0]);
//							System.out.println("Reglas activadas: " + reglas[0]);
//							System.out.println("Presencia: " + presencia[0]);
							
							//Convertimos de string a booolean
							boolean reglasAct;
							if (reglas[0].equals("true")) {
								reglasAct = true;
							} else {
								reglasAct = false;
							}
							boolean reglasPresencia;
							if (presencia[0].equals("true")) {
								reglasPresencia = true;
							} else {
								reglasPresencia = false;
							}

							ConfTermostato conf = new ConfTermostato(Float.parseFloat(datos[0]), reglasAct, reglasPresencia);							
							ControlTermostato.setTermostato(conf);//configuramos el termostato						
							InterfaceMySQL.insertTermostato(conf);//registramos la configuración

						}
					}

				} catch (SocketTimeoutException s) {
					// System.out.println("Socket timed out!");
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Fin hilo socket");
	}

	public void finHilo() {
		fin = true;
	}

}
