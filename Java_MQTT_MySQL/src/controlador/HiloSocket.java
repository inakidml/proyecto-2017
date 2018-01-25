package controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

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
			servidor.setSoTimeout(10000);

			while (fin != true) {
				try {
					// System.out.println("Esperando al cliente...");
					socket = servidor.accept();
					// System.out.println("Cliente conectado");
					// En 2 líneas
					datosCliente = new InputStreamReader(socket.getInputStream());
					b = new BufferedReader(datosCliente);
					// EL CLIENTE ENVIA UN MENSAJE
					String textoRec = b.readLine();
					// System.out.println("Recibiendo del cliente:\n\t" + textoRec);
					if (textoRec != null) {
						if (!textoRec.equals("null")) {
							String[] textos = textoRec.split("\\?");
							String[] datos = textos[1].split(",");
							String[] reglas = datos[1].split(" ");
							System.out.println("Tª termostato: " + datos[0]);
							System.out.println("Reglas activadas: " + reglas[0] + reglas[1]);//es textos
						}
					}

				} catch (SocketTimeoutException s) {
					// System.out.println("Socket timed out!");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Fin hilo socket");
	}

	public void finHilo() {
		fin = true;
	}

}
