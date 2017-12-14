<iframe width="560" height="315" src="https://www.youtube.com/embed/FHQahlC9Wzw?rel=0&amp;showinfo=0" frameborder="0" gesture="media" allow="encrypted-media" allowfullscreen style="margin-bottom:1em;"></iframe>

## Restricciones y sugerencias 

- El trabajo se realizará en equipos formados por dos personas.
- Siguiendo las normas para el trabajo en grupo que se explican en [este curso sobre Git](https://edx.egibide.org/courses/course-v1:Egibide+Egibide_Git+2017/about), cada grupo trabajará en su propio _fork_ de [este repositorio](https://github.com/Egibide-DAM/proyecto-2017) y subirá los resultados a GitHub.
- Una vez hecho el _fork_, activad los _issues_ en los ajustes del nuevo repositorio para poder comentar el trabajo.
- En el fichero `README.md` del proyecto se incluirán las instrucciones necesarias para que cualquiera pueda ponerlo en marcha, con todos los detalles para que funcione correctamente. 

## Evaluación

- [Programación abreviada de la asignatura](Prog_Abrev_Modulo11_Proyecto_Desarrollo_Aplicaciones_Multiplataforma.pdf).
- [Rúbrica de evaluación](https://www.quickrubric.com/r#/qr/widemos/proyecto-2017).

> **Importante**: No se admitirán como válidos proyectos en los que la lógica y estructura de los programas no sea de elaboración propia. Se pueden utilizar fragmentos de ejemplos y librerías externas, solo si su licencia permite su uso y se enlaza a su origen. 

## Documentación y recursos

### Arduino

- Cada equipo dispondrá de un [Particle Photon](https://www.particle.io/products/hardware/photon-wifi-dev-kit) y un sensor [Adafruit DHT22](https://learn.adafruit.com/dht). 
- IDE de desarrollo [Particle Build](https://build.particle.io/build).
- Referencia del [lenguaje Arduino](https://www.arduino.cc/reference/en/).
- [Ejemplo](https://openhomeautomation.net/cloud-data-logger-particle-photon/) de registrador de datos con Particle.

### MQTT

- [MQTT](http://mqtt.org/), protocolo de comunicación para IoT.
- [Mosquitto](https://mosquitto.org/), broker de mensajes para MQTT.
- [Librería MQTT](https://github.com/hirotakaster/MQTT) para Photon.
- [Ejemplo](http://ediy.com.my/blog/item/143-store-messages-from-mosquitto-mqtt-broker-into-sql-database) de almacenaje de mensajes MQTT en una base de datos SQL.
  
### openHAB

- Documentación de [openHAB](https://docs.openhab.org/introduction.html).
- [URL de acceso](http://10.1.3.14:8080/) a la Raspberry Pi que controla el [enchufe inteligente](https://www.fibaro.com/en/products/wall-plug/) conectado al calefactor (solo disponible en la red `inforwifi`).
- Referencia del [binding MQTT](https://docs.openhab.org/addons/bindings/mqtt1/readme.html
) de openHAB.
- Referencia de las [reglas de openHAB](https://docs.openhab.org/configuration/rules-dsl.html) para automatizar tareas.

### Machine learning

- Ejemplo de [termostato inteligente](https://niektemme.com/2015/08/09/smart-thermostat/).

> Si tenéis algún recurso interesante que queráis aportar o detectáis errores en la lista, abrid un _pull request_ a [este repositorio](https://github.com/Egibide-DAM/proyecto-2017) para añadirlo o corregirlo. 
