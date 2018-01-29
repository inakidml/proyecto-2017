// This #include statement was automatically added by the Particle IDE.
#include <MQTT.h>
#include <PietteTech_DHT.h>


//https://github.com/piettetech/PietteTech_DHT/blob/master/firmware/examples/DHT_example.cpp


// Parámetros DHT
#define DHTTYPE  DHT22  
#define DHTPIN   D1  
#define DHT_SAMPLE_INTERVAL   1000  // Tiempo de mmuestreo

int light_sensor_pin = A0;  //pin del sensor DHT
int light;                  //pin fotoresistor
int power = A5;             //Alimentación fotoresistor desde un pin analógico
int led = D5;               //pin led

// globals
unsigned int DHTnextSampleTime; // Next time we want to start sample


//función de callback para DHT
void dht_wrapper();

//  DHT Lib Initialize
PietteTech_DHT DHT(DHTPIN, DHTTYPE, dht_wrapper);

void dht_wrapper() {
    DHT.isrCallback();
}

//MQTT
/**
 * if want to use IP address,
 * byte server[] = { XXX,XXX,XXX,XXX };
 * MQTT client(server, 1883, callback);
 * want to use domain name,
 * MQTT client("www.sample.com", 1883, callback);
 **/
byte server[] = {10,1,100,100}; //Servidor vagrant con mosquitto 
MQTT client(server, 1883, callback); 
//callback MQTT
void callback (char* topic, byte* payload, unsigned int length) {}


//SETUP
void setup() {
    DHTnextSampleTime = 0; //SIguiente muestra del DHT
    pinMode(light_sensor_pin, INPUT);   //Configuramos el pin del sensor como input
    pinMode(power, OUTPUT);             //Pin para alimentar el fotoresistor, como salida
    digitalWrite(power,HIGH);           //Activamos la alimentación del LDR
    pinMode(led, OUTPUT);               //Pin del LED como salida
    
     client.connect("SensorPhoton6");   //Nos conec
    
}

void loop()
{
  // Light level measurement
  float light_measurement = analogRead(light_sensor_pin);
  light = (int)(light_measurement);
  light = map(light, 0, 4096, 0, 100);
  

  if (millis() > DHTnextSampleTime) {
	DHT.acquire();

    if (!DHT.acquiring()) {		//Si ha terminado de recibir

        //int result = DHT.getStatus();
        float humedad = DHT.getHumidity();
        float tempc = DHT.getCelsius();
    
        Particle.publish("Humedad", String(humedad) + "%");
        Particle.publish("Temperatura", String(tempc) + " °C");
        Spark.publish("Luz", String(light) + "%");
        
        Particle.publish("spark/device/ip");
        
    //Destello led    
        digitalWrite(led, HIGH);
        delay(250);
        digitalWrite(led, LOW);
    
        DHTnextSampleTime = millis() + DHT_SAMPLE_INTERVAL;
        
   client.connect("SensorPhoton6");
   if (client.isConnected()) {
        client.publish("TopicIkerInaki/Temperatura", String(tempc));
        client.publish("TopicIkerInaki/Humedad", String(humedad));
        client.publish("TopicIkerInaki/Luz", String(light));
   }
        
    }
  }
}