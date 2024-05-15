package test;

import org.junit.jupiter.api.Test;
import dominio.ServicioCambioMoneda;
import static org.junit.jupiter.api.Assertions.*;

public class ServicioCambioMonedaTest {

	@Test
	public void testConvertirMoneda() {
		// Se instancia el servicio de cambio de moneda para realizar las pruebas
		ServicioCambioMoneda servicio = new ServicioCambioMoneda();

		// Array de datos de prueba: [cantidad, monedaOrigen, monedaDestino,
		// tasaEsperada]
		Object[][] datosPrueba = { { 1000.0, "CLP", "USD", 0.0011 }, // 1000 CLP convertidos a USD
				{ 100.0, "USD", "CLP", 924.55 }, // 100 USD convertidos a CLP
				{ 50.0, "GBP", "EUR", 1.16 }, // 50 GBP convertidos a EUR
				{ 30.0, "EUR", "GBP", 0.86 }, // 30 EUR convertidos a GBP
				{ 100.0, "AUD", "USD", 0.0 } // 100 AUD, moneda no soportada, debería retornar 0
		};

		// Iterar sobre cada conjunto de datos de prueba
		for (Object[] prueba : datosPrueba) {
			// Extraer los datos de la prueba
			double cantidad = (double) prueba[0];
			String monedaOrigen = (String) prueba[1];
			String monedaDestino = (String) prueba[2];
			double tasaEsperada = (double) prueba[3];

			// Realizar la conversión y verificar el resultado
			double cantidadConvertida = servicio.convertir(cantidad, monedaOrigen, monedaDestino);
			assertEquals(cantidad * tasaEsperada, cantidadConvertida);
		}
	}
}
