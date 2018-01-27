package controlador;

import modelo.InterfaceMySQL;
import modelo.ConfTermostato;

public class Main {

	public static void main(String[] args) throws Exception {
		
		ConfTermostato termostato = InterfaceMySQL.getTermostato();//conseguimos la última configuración del termostato
		ControlTermostato.setTemperaturaTermostato(termostato.getTemperatura());//configuramos la temperatura del termostato
		ControlTermostato.setActivado(termostato.isReglas());//estaba activado la última vez?
		ControlTermostato.setPresencia(termostato.isPresencia());//estabamos usando las reglas de presencia?

		// arrancamos hilos
		HiloMQTT hiloMqtt = new HiloMQTT();//Mosquitto
		HiloSocket hiloSocket = new HiloSocket();//Socket para recibir conf desde nuestra web
		hiloMqtt.start();
		hiloSocket.start();

		// Termina el programa
		hiloMqtt.join(); //esperamos a que termine el hilo mqtt, cuando pulsen una tecla
		hiloSocket.finHilo();//mandamos fin al hilo socket
		hiloSocket.join(); //espoeramos a que termine
		System.out.println("Fin del programa");
	}

}
