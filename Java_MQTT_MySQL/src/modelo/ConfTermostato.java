package modelo;

public class ConfTermostato {
	private float temperatura;
	private boolean reglas;
	private boolean presencia;

	// clase para recibir la configuración del termostato
	public ConfTermostato(float temperatura, boolean reglas, boolean presencia) {
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

	public int convertirBooleanInt(boolean bool) {

		if (bool) {
			return 1;
		} else {
			return 0;
		}
	}

	public boolean convertirIntBoolean(int entero) {

		if (entero > 0) {
			return true;
		} else {
			return false;
		}
	}
}
