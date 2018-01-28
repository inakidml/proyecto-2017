# Proyecto DAM 2017

## Iñaki e Iker

Repositorio del módulo de proyecto de Desarrollo de Aplicaciones Multiplataforma en [Egibide Arriaga](http://www.egibide.org/2/es/25/donde-nos-encontramos.html).

La carpeta [docs](./docs/) contiene el [sitio web](https://egibide-dam.github.io/proyecto-2017/) con la documentación.

### Introducción
El proyecto consta de dos partes.
1. Recepción de datos del Photon en servidor mosquitto, registro en base de datos MySQL a través de aplicación Java y visualización de gráficas en página Web. Control de la calefacción mediante plataforma OPENHABIAN, creando "things", items y reglas. Regulación con termostato y presencia detectada por la luz o telefonos móviles.
2. Control con termostato y presencia mediante la Web y la aplicación Java. Evitando las reglas de Openhabian.

### Control de calefactor con OpenHabian
Controlaremos un calefactor con una Raspberry Pi con la distribución OpenHabianPi instalada un stick USB Zwave y un switch Zwave. Recibiremos los datos de la estancia desde una placa Particle Photon, que a través de un "Broker" Mosquitto instalado en una máquina virtual scotchBox, registrará la temperatura, humeddad y luz en una base de datos MySQL. Estos datos podremos consultarlos mediante una Web que nos mostrará una gráfica con el valor del instante y tres gráficas con el historico de los tres datos almacenados, temperatura, humedad y luz.

#### Material necesario:
* [Raspberry Pi](https://www.raspberrypi.org/products/)
* [USB Zwave](http://zwave.es/AeonUsb)
* [Enchufe Zwave](http://zwave.es/FibaroWallPlugZwavePlus?search=enchufe)
* [Particle Photon](https://store.particle.io/#photon)
* [Adafruit DHT22](https://www.adafruit.com/product/385) 
* Fotoresistor, viene con el photon
* Alguna resistencia
