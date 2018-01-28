# Proyecto DAM 2017

## Iker e Iñaki

Repositorio del módulo de proyecto de Desarrollo de Aplicaciones Multiplataforma en [Egibide Arriaga](http://www.egibide.org/2/es/25/donde-nos-encontramos.html).

La carpeta [docs](./docs/) contiene el [sitio web](https://egibide-dam.github.io/proyecto-2017/) con la documentación.

### Introducción
El proyecto consta de dos partes.
1. Recepción de datos del Photon en servidor mosquitto, registro en base de datos MySQL a través de aplicación Java y visualización de gráficas en página Web. Control de la calefacción mediante plataforma OPENHABIAN, creando "things", items y reglas. Regulación con termostato y presencia detectada por la luz o telefonos móviles.
2. Control con termostato y presencia mediante la Web y la aplicación Java. Evitando las reglas de Openhabian.

### Control de calefactor con OpenHabian
Controlaremos un calefactor con una Raspberry Pi con la distribución OpenHabianPi instalada, un stick USB Zwave y un switch Zwave. Recibiremos los datos de la estancia desde una placa Particle Photon, que a través de un "Broker" Mosquitto instalado en una máquina virtual scotchBox, registrará la temperatura, humeddad y luz en una base de datos MySQL. Estos datos podremos consultarlos mediante una Web que nos mostrará una gráfica con el valor del instante y tres gráficas con el historico de los tres datos almacenados, temperatura, humedad y luz.

#### Material necesario:
* [Raspberry Pi](https://www.raspberrypi.org/products/)
* [USB Zwave](http://zwave.es/AeonUsb)
* [Enchufe Zwave](http://zwave.es/FibaroWallPlugZwavePlus?search=enchufe)
* [Particle Photon](https://store.particle.io/#photon)
* [Adafruit DHT22](https://www.adafruit.com/product/385) 
* Fotoresistor, viene con el photon
* Alguna resistencia

#### Instalación
* Raspberry Pi
    * [Instalación de OpenHabianPi](https://docs.openhab.org/installation/openhabian.html), lo más cómodo es grabar una imagen del SO en un tarjeta SD.
* Particle Photon    
    * Hacemos la puesta en marcha del [Photon](https://docs.particle.io/guide/getting-started/intro/photon/), subscribiendolo a nuestra red Wi-Fi.
    * Nos logeamos en la nube de [Particle](https://login.particle.io/login?app=web-ide&redirect=https://build.particle.io/build).
    * Grabamos el [sketch](./Photon/Sketch_Photon.ino) en nuestro photon.
* Vagrant scotchBox
    * Instalamos la máquina virtual vagrant [scotchBox](https://github.com/Egibide/scotch-box).
    * En el fichero Vagrantfile que se encuentra en la carpeta de la máquina virtual vagrant, añadimos la siguiente línea
'''ruby
    config.vm.network "public_network", ip: "10.1.100.100", netmask: "255.255.255.0"
'''
    Con esto habilitamos el acceso a la máquina virtual desde la red inforwifi, para que el photon se pueda comunicar con Mosquitto.
