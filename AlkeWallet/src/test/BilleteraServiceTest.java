package test;

import org.junit.jupiter.api.Test;

import dominio.BilleteraService;

import static org.junit.jupiter.api.Assertions.*;

public class BilleteraServiceTest {

	// Prueba la función de depósito en distintas monedas
	@Test
	public void testDepositar() {
		System.out.println("Soy el testDepositar()");
		BilleteraService billeteraService = new BilleteraService();

		// Test de depósito en USD
		billeteraService.depositar(100, "USD");
		assertEquals(100, billeteraService.consultarSaldo("USD"));

		// Test de depósito en EUR
		billeteraService.depositar(100, "EUR");
		assertEquals(100, billeteraService.consultarSaldo("EUR"));
	}

	// Prueba la función de retiro con casos de saldo suficiente e insuficiente
	@Test
	public void testRetirar() {
	    // Test de retiro con saldo suficiente
	    System.out.println("Soy el testRetirarConSaldoSuficiente()");
	    BilleteraService billeteraService = new BilleteraService();
	    billeteraService.depositar(100, "USD");
	    billeteraService.retirar(50, "USD");
	    assertEquals(50, billeteraService.consultarSaldo("USD"));

	    // Test de retiro con saldo insuficiente
	    billeteraService = new BilleteraService();
	    billeteraService.depositar(20, "USD");
	    billeteraService.retirar(50, "USD");
	    assertEquals(20, billeteraService.consultarSaldo("USD"));
	}


	// Prueba las conversiones de moneda
	@Test
	public void testConvertirMoneda() {
	    // Test de conversión de moneda USD a EUR
	    System.out.println("Soy el testConvertirMoneda()");
	    BilleteraService billeteraService = new BilleteraService();
	    billeteraService.depositar(100, "USD");
	    billeteraService.convertirMoneda(50, "USD", "EUR");
	    assertEquals(46.5, billeteraService.consultarSaldo("EUR")); // 50 USD * 0.93 tasa de cambio USD a EUR

	    // Test de conversión de moneda EUR a USD
	    billeteraService = new BilleteraService();
	    billeteraService.depositar(100, "EUR");
	    billeteraService.convertirMoneda(50, "EUR", "USD");
	    assertEquals(54, billeteraService.consultarSaldo("USD")); // 50 EUR * 1.08 tasa de cambio EUR a USD
	}


	// Prueba de consulta de saldo para una moneda inexistente
	@Test
	public void testConsultarSaldoMonedaInexistente() {
		System.out.println("Soy el testConsultarSaldoMonedaInexistente()");
		BilleteraService billeteraService = new BilleteraService();
		assertEquals(0.0, billeteraService.consultarSaldo("AUD")); // Debería devolver 0.0 para una moneda inexistente
	}

	// Prueba de depósito con cantidad negativa
	@Test
	public void testDepositarCantidadNegativa() {
		System.out.println("Soy el testDepositarCantidadNegativa()");
		BilleteraService billeteraService = new BilleteraService();
		billeteraService.depositar(-50, "USD");
		assertEquals(0, billeteraService.consultarSaldo("USD")); // El saldo no debe cambiar con una cantidad negativa
	}

	// Prueba de retiro con cantidad negativa
	@Test
	public void testRetirarCantidadNegativa() {
		System.out.println("Soy el testRetirarCantidadNegativa()");
		BilleteraService billeteraService = new BilleteraService();
		billeteraService.depositar(100, "USD");
		billeteraService.retirar(-50, "USD");
		assertEquals(100, billeteraService.consultarSaldo("USD")); // El saldo no debe cambiar con una cantidad negativa
	}
	
	
	// Prueba de retiro con cantidad cero
	@Test
	public void testRetirarCantidadCero() {
		System.out.println("Soy el testRetirarCantidadCero()");
		BilleteraService billeteraService = new BilleteraService();
		billeteraService.depositar(100, "USD");
		billeteraService.retirar(0, "USD");
		assertEquals(100, billeteraService.consultarSaldo("USD")); // El saldo no debe cambiar con una cantidad de
																	// retiro cero
	}

	// Prueba de conversión de moneda con cantidad negativa
	@Test
	public void testConvertirMonedaCantidadNegativa() {
		System.out.println("Soy el testConvertirMonedaCantidadNegativa()");
		BilleteraService billeteraService = new BilleteraService();
		billeteraService.depositar(100, "USD");
		billeteraService.convertirMoneda(-50, "USD", "EUR");
		assertEquals(0, billeteraService.consultarSaldo("EUR")); // La conversión no debe afectar el saldo con una
																	// cantidad negativa
	}

	// Prueba de conversión de moneda con tasa de cambio cero
	@Test
	public void testConvertirMonedaTasaCero() {
		System.out.println("Soy el testConvertirMonedaTasaCero()");
		BilleteraService billeteraService = new BilleteraService();
		billeteraService.depositar(100, "USD");
		billeteraService.convertirMoneda(50, "USD", "AUD"); // Supongamos que la tasa de cambio USD a AUD es cero
		assertEquals(0, billeteraService.consultarSaldo("AUD")); // El saldo en AUD debe seguir siendo cero
	}

}
