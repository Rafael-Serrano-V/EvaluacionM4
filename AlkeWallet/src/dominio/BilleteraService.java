package dominio;

import java.util.HashMap;
import java.util.Map;

public class BilleteraService implements GestionarFondos {
	
	/**
     * Mapa para almacenar los saldos de las diferentes monedas.
     * La clave es el código de la moneda y el valor es el saldo correspondiente.
     */
	private Map<String, Double> saldos;
	
	/**
     * Constructor para inicializar los saldos de las monedas disponibles.
     */
	public BilleteraService() {
		saldos = new HashMap<>();
		// Inicializa los saldos de las monedas con 0.0
		saldos.put("USD", 0.0);
		saldos.put("EUR", 0.0);
		saldos.put("GBP", 0.0);
		saldos.put("CLP", 0.0);
	}

	// Métodos para depositar, retirar y consultar saldo
	/**
     * Deposita una cantidad especificada de una moneda en la billetera.
     * @param cantidad La cantidad a depositar.
     * @param moneda La moneda en la que se realiza el depósito.
     */
	@Override
	public void depositar(double cantidad, String moneda) {
		// Verifica que la cantidad sea positiva
		if (cantidad <= 0) {
			System.out.println("***Error: La cantidad a depositar debe ser un valor positivo.***");
			return;
		}
		// Obtiene el saldo actual de la moneda y lo actualiza con la cantidad depositada
		double saldoActual = saldos.get(moneda);
		saldos.put(moneda, saldoActual + cantidad);
	}

	/**
     * Retira una cantidad especificada de una moneda de la billetera.
     * @param cantidad La cantidad a retirar.
     * @param moneda La moneda de la que se retira el saldo.
     */
	@Override
	public void retirar(double cantidad, String moneda) {
		// Verifica que la cantidad sea positiva
		if (cantidad <= 0) {
			System.out.println("***Error: La cantidad a retirar debe ser un valor positivo.***");
			return;
		}
		// Obtiene el saldo actual de la moneda y realiza el retiro si hay saldo suficiente
		double saldoActual = saldos.get(moneda);
		if (cantidad <= saldoActual) {
			saldos.put(moneda, saldoActual - cantidad);
			System.out.println("***Retiro realizado con éxito.***");
		} else {
			System.out.println("***Saldo insuficiente en " + moneda + "***");
		}
	}

	/**
     * Convierte una cantidad de una moneda de origen a una moneda de destino.
     * @param cantidad La cantidad a convertir.
     * @param monedaOrigen La moneda de origen.
     * @param monedaDestino La moneda de destino.
     */
	@Override
	public void convertirMoneda(double cantidad, String monedaOrigen, String monedaDestino) {
		// Verifica que la cantidad sea positiva
		if (cantidad <= 0) {
			System.out.println("***Error: La cantidad a convertir debe ser un valor positivo.***");
			return;
		}
		// Obtiene los saldos de las monedas de origen y destino, y la tasa de cambio entre ellas
		double saldoOrigen = saldos.getOrDefault(monedaOrigen, 0.0);
		double saldoDestino = saldos.getOrDefault(monedaDestino, 0.0);
		ServicioCambioMoneda servicio = new ServicioCambioMoneda();
		double tasaDeCambio = servicio.obtenerTasaDeCambio(monedaOrigen, monedaDestino);

		// Verifica que la tasa de cambio sea válida
		if (tasaDeCambio == 0) {
			System.out.println("***Error: La tasa de cambio de " + monedaOrigen + " a " + monedaDestino
					+ " es cero. No se puede realizar la conversión.***");
			return;
		}
		
		// Calcula la cantidad convertida y actualiza los saldos de las monedas
		double cantidadConvertida = cantidad * tasaDeCambio;

		// Verifica que haya saldo suficiente en la moneda de origen
		if (saldoOrigen < cantidad) {
			System.out.println("***Saldo insuficiente en " + monedaOrigen + "***");
			return;
		}
		
		// Verifica que la moneda de origen no esté vacía
		if (saldoOrigen == 0) {
			System.out.println(
					"***Error: La moneda de origen " + monedaOrigen + " esta en 0.***");
			return;
		}
		
		// Actualiza los saldos de las monedas de origen y destino
		saldos.put(monedaDestino, saldoDestino + cantidadConvertida);
		saldos.put(monedaOrigen, saldoOrigen - cantidad);
		System.out.println("***Conversión realizada con éxito.***");
	}

	/**
     * Muestra los saldos de todas las monedas en la billetera.
     */
	@Override
	public void mostrarSaldos() {
		// Calcula la longitud máxima de las monedas para formatear correctamente la salida
		int maxLongitudMoneda = 0;
		for (String moneda : saldos.keySet()) {
			maxLongitudMoneda = Math.max(maxLongitudMoneda, moneda.length());
		}

		// Imprime los saldos formateados de todas las monedas
		System.out.println("=== Saldo disponible ===");
		for (String moneda : saldos.keySet()) {
			double saldo = saldos.get(moneda);
			String saldoFormateado = String.format("%.2f", saldo);
			System.out.printf("%-" + (maxLongitudMoneda + 3) + "s : %s%n", moneda + " ", saldoFormateado);
		}
	}

	 /**
     * Consulta el saldo actual de una moneda específica en la billetera.
     * @param moneda La moneda de la que se desea consultar el saldo.
     * @return El saldo actual de la moneda especificada.
     */
	@Override
	public double consultarSaldo(String moneda) {
		// Devuelve el saldo de la moneda especificada
		return saldos.getOrDefault(moneda, 0.0);
	}

}
