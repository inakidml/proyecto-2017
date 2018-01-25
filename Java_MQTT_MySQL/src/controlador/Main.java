package controlador;

public class Main {

	public static void main(String[] args) throws Exception {

		//https://github.com/fusesource/mqtt-client
		
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
