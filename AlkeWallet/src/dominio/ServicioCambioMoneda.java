package dominio;
/**
 * Esta clase proporciona la lógica para convertir una cantidad de una moneda a otra.
 */
//Se implementa la interfaz 'ConversorMoneda' y proporciona la lógica para convertir una cantidad de una moneda a otra.
public class ServicioCambioMoneda implements ConversorMoneda {

	// Método para convertir una cantidad de una moneda a otra
	@Override
	public double convertir(double cantidad, String monedaOrigen, String monedaDestino) {
		// Obtiene la tasa de cambio entre las monedas de origen y destino
		double tasaDeCambio = obtenerTasaDeCambio(monedaOrigen, monedaDestino);
		// Calcula y devuelve la cantidad convertida
		return cantidad * tasaDeCambio;
	}

	/**
     * Obtiene la tasa de cambio entre dos monedas.
     * @param monedaOrigen La moneda de origen.
     * @param monedaDestino La moneda de destino.
     * @return La tasa de cambio entre las dos monedas especificadas.
     */
	public double obtenerTasaDeCambio(String monedaOrigen, String monedaDestino) {
		double tasa = 0.0;

		// Conversiones desde CLP
		if (monedaOrigen.equalsIgnoreCase("CLP")) {
			if (monedaDestino.equalsIgnoreCase("USD")) {
				tasa = 0.0011; // 1 CLP = 0.0011 USD
			} else if (monedaDestino.equalsIgnoreCase("EUR")) {
				tasa = 0.001; // 1 CLP = 0.001 EUR
			} else if (monedaDestino.equalsIgnoreCase("GBP")) {
				tasa = 0.00086; // 1 CLP = 0.00086 GBP
			}
		}

		// Conversiones desde USD
		if (monedaOrigen.equalsIgnoreCase("USD")) {
			if (monedaDestino.equalsIgnoreCase("CLP")) {
				tasa = 924.55; // 1 USD = 924.55 CLP
			} else if (monedaDestino.equalsIgnoreCase("EUR")) {
				tasa = 0.93; // 1 USD = 0.93 EUR
			} else if (monedaDestino.equalsIgnoreCase("GBP")) {
				tasa = 0.90; // 1 USD = 0.90 GBP
			}
		}

		// Conversiones desde GBP
		if (monedaOrigen.equalsIgnoreCase("GBP")) {
			if (monedaDestino.equalsIgnoreCase("CLP")) {
				tasa = 1157.80; // 1 GBP = 1157.80 CLP
			} else if (monedaDestino.equalsIgnoreCase("USD")) {
				tasa = 1.25; // 1 GBP = 1.25 USD
			} else if (monedaDestino.equalsIgnoreCase("EUR")) {
				tasa = 1.16; // 1 GBP = 1.16 EUR
			}
		}

		// Conversiones desde EUR
		if (monedaOrigen.equalsIgnoreCase("EUR")) {
			if (monedaDestino.equalsIgnoreCase("CLP")) {
				tasa = 996.74; // 1 EUR = 996.74 CLP
			} else if (monedaDestino.equalsIgnoreCase("USD")) {
				tasa = 1.08; // 1 EUR = 1.08 USD
			} else if (monedaDestino.equalsIgnoreCase("GBP")) {
				tasa = 0.86; // 1 EUR = 0.86 GBP
			}
		}

		// Devuelve la tasa de cambio calculada
		return tasa;
	}

}
