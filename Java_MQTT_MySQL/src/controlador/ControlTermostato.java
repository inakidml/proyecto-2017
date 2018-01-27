package controlador;

import modelo.ConfTermostato;

public class ControlTermostato {
	private static boolean activado = false;
	private static boolean presencia = false;
	private static float temperaturaTermostato = 20;

	public static boolean isActivado() {
		return activado;
	}

	public static void setActivado(boolean activado) {
		ControlTermostato.activado = activado;
	}

	public static boolean isPresencia() {
		return presencia;
	}

	public static void setPresencia(boolean presencia) {
		ControlTermostato.presencia = presencia;
	}

	public static float getTemperaturaTermostato() {
		return temperaturaTermostato;
	}

	public static void setTemperaturaTermostato(float temperaturaTermostato) {
		ControlTermostato.temperaturaTermostato = temperaturaTermostato;
	}
	
	public static void setTermostato(ConfTermostato conf) {
		setTemperaturaTermostato(conf.getTemperatura());
		setActivado(conf.isReglas());
		setPresencia(conf.isPresencia());
	}
//reglas del termostato
	public static void activarEnchufe(float temperatura, float luz) {
		if (activado) {// si las reglas estan activadas

			if (!presencia) {// si no queremos presencia
				if (temperatura < temperaturaTermostato + 0.3f) {
					ControlOpenhabian.encenderEnchufe();
				} else {
					ControlOpenhabian.apagarEnchufe();
				}
			} else {// si queremos presencia, móviles o luz
				if (ControlOpenhabian.isIker() || ControlOpenhabian.isInaki() || luz > 5f) {

					if (temperatura < temperaturaTermostato + 0.3f) {
						ControlOpenhabian.encenderEnchufe();
					} else {
						ControlOpenhabian.apagarEnchufe();
					}
				} else {
					ControlOpenhabian.apagarEnchufe();
				}
			}
		} else {
			ControlOpenhabian.apagarEnchufe();
		}
	}
}
