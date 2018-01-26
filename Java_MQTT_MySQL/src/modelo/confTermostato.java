package modelo;

public class confTermostato {
private float temperatura;
private boolean reglas;
private boolean presencia;


public confTermostato(float temperatura, boolean reglas, boolean presencia) {
	super();
	this.temperatura = temperatura;
	this.reglas = reglas;
	this.presencia = presencia;
}
public float getTemperatura() {
	return temperatura;
}
public void setTemperatura(float temperatura) {
	this.temperatura = temperatura;
}
public boolean isReglas() {
	return reglas;
}
public void setReglas(boolean reglas) {
	this.reglas = reglas;
}
public boolean isPresencia() {
	return presencia;
}
public void setPresencia(boolean presencia) {
	this.presencia = presencia;
}
}
