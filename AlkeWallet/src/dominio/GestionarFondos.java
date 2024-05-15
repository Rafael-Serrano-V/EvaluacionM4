package dominio;

//Interfaz que define los métodos necesarios para gestionar fondos en una billetera electrónica.
public interface GestionarFondos {
	// Método para mostrar los saldos de todas las monedas en la billetera.
	void mostrarSaldos();
	// Método para depositar una cantidad especificada de una moneda en la billetera.
	void depositar(double cantidad, String moneda);
	// Método para retirar una cantidad especificada de una moneda de la billetera.
	void retirar(double cantidad, String moneda);
	// Método para convertir una cantidad especificada de una moneda de origen a una moneda de destino.
	void convertirMoneda(double cantidad, String monedaOrigen, String monedaDestino);
	// Método para consultar el saldo actual de una moneda específica en la billetera.
	double consultarSaldo(String moneda);
	
}
