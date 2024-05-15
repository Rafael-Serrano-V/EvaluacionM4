package dominio;

//Se define la interfaz con un método 'convertir', que será implementado por clases que realicen conversiones de moneda.
public interface ConversorMoneda {
	// Método para convertir una cantidad de una moneda a otra.
	double convertir(double cantidad,String monedaOrigen, String monedaDestino);
}
