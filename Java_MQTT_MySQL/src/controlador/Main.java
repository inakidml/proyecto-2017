package controlador;

import modelo.InterfaceMySQL;
import modelo.confTermostato;

public class Main {

	public static void main(String[] args) throws Exception {

		// https://github.com/fusesource/mqtt-client

		confTermostato termostato = InterfaceMySQL.getTermostato();
		Termostato.setTemperaturaTermostato(termostato.getTemperatura());
		Termostato.setActivado(termostato.isReglas());
		Termostato.setPresencia(termostato.isPresencia());

		// arrancamos hilos
		HiloMQTT hiloMqtt = new HiloMQTT();
		HiloSocket hiloSocket = new HiloSocket();
		hiloMqtt.start();
		hiloSocket.start();

		// Termina el programa
		hiloMqtt.join();
		hiloSocket.finHilo();
		hiloSocket.join();
		System.out.println("Fin del programa");
	}

}
