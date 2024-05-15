package dominio;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase principal que contiene el método main y gestiona la interacción con el usuario.
 */
public class Main {

	private static final Logger logger = Logger.getLogger(Main.class.getName());

	// Se implementa un bucle principal de la aplicación que muestra el menú y
	// procesa las opciones seleccionadas por el usuario.
	private static final Scanner scanner = new Scanner(System.in);
	private static final BilleteraService billeteraService = new BilleteraService();

	/**
     * Método principal de la aplicación.
     * @param args Los argumentos de línea de comandos (no se utilizan en esta aplicación).
     */
	public static void main(String[] args) {

		while (true) {
			mostrarMenu();
			int opcion;
			try {
				opcion = scanner.nextInt();
				scanner.nextLine();
				procesarOpcion(opcion);

			} catch (InputMismatchException e) {
				// Maneja la excepción de entrada inválida y registra un mensaje de advertencia
				logger.log(Level.WARNING, "***Entrada inválida. Por favor, ingrese un número válido.***");
				scanner.nextLine(); // Consumir la entrada inválida para evitar bucles infinitos
			}
		}
	}

	// Función auxiliar para mostrar el menú de opciones al usuario
	private static void mostrarMenu() {
		System.out.println("=== Bienvenido a Alke Wallet ===");
		System.out.println("┌───────────────────────────────┐");
		System.out.println("│           Menú de Opciones    │");
		System.out.println("├───────────────────────────────┤");
		System.out.println("│  1. Ver saldo                 │");
		System.out.println("│  2. Ver saldo por moneda      │");
		System.out.println("│  3. Realizar depósito         │");
		System.out.println("│  4. Realizar retiro           │");
		System.out.println("│  5. Convertir fondos          │");
		System.out.println("│  6. Salir                     │");
		System.out.println("└───────────────────────────────┘");
		System.out.print("Ingrese su opción: ");
	}

	// Método para procesar la opción seleccionada por el usuario
	private static void procesarOpcion(int opcion) {

		switch (opcion) {
		case 1:
			billeteraService.mostrarSaldos();
			break;
		case 2:
			consultaSaldoPorMoneda();
			break;
		case 3:
			procesarDeposito();
			break;
		case 4:
			procesarRetiro();
			break;
		case 5:
			procesarConversion();
			break;
		case 6:
			// Mensaje de despedida y finalización del programa
			System.out.println("***Gracias por usar Alke Wallet. ¡Hasta luego!***");
			System.exit(0);
		default:
			// Manejo de opción no válida
			logger.log(Level.WARNING,"***Opción no válida. Por favor, seleccione una opción válida.***");
		}
	}

	// Métodos auxiliares para procesar depósitos, retiros y conversiones.
	// Cada método tiene una lógica para validar las entradas del usuario y realizar
	// la operación correspondiente en la billetera.
	
	// Mérodo para consultar saldo por moneda especificada
	private static void consultaSaldoPorMoneda() {
	    try {
	    	// Solicita al usuario ingresar la moneda para la consulta de saldo
	        System.out.print("Ingrese la moneda (USD, EUR, GBP, CLP): ");
	        String monedaConsulta = scanner.nextLine().toUpperCase();

	        // Verifica si la moneda ingresada es válida
	        if (!monedaConsulta.equals("USD") && !monedaConsulta.equals("EUR") && !monedaConsulta.equals("GBP")
	                && !monedaConsulta.equals("CLP")) {
	            System.out.println("***Moneda ingresada no válida. Por favor, ingrese una moneda válida.***");
	            return;
	        }
	        
	        // Realiza la consulta de saldo y muestra el resultado
	        double saldoConsulta = billeteraService.consultarSaldo(monedaConsulta);
	        System.out.println("Saldo en " + monedaConsulta + ": " + String.format("%.2f", saldoConsulta));
	    } catch (Exception e) {
	    	// Maneja cualquier error durante la consulta de saldo
	        System.out.println("***Ocurrió un error al consultar el saldo. Por favor, inténtelo nuevamente.***");
	    }
	}

	// Método para procesar un depósito
	private static void procesarDeposito() {
		try {
			// Solicita al usuario ingresar la cantidad y la moneda para el depósito
			System.out.print("Ingrese la cantidad a depositar: ");
			double cantidadDeposito = Double.parseDouble(scanner.next());
			scanner.nextLine(); // Consumir el salto de línea
			System.out.print("Ingrese la moneda (USD, EUR, GBP, CLP): ");
			String monedaDeposito = scanner.nextLine().toUpperCase();
			
			// Realiza el depósito y muestra un mensaje de éxito
			billeteraService.depositar(cantidadDeposito, monedaDeposito);
			System.out.println("***Depósito realizado con éxito.***");
		} catch (NumberFormatException e) {
			// Maneja la excepción de entrada inválida para la cantidad
			logger.log(Level.WARNING, "Entrada inválida. Por favor, ingrese un número válido para la cantidad.");
			scanner.nextLine(); // Consumir el salto de línea
		}
	}

	// Método para procesar el retiro de fondos de la billetera
	private static void procesarRetiro() {
		try {
			// Solicita al usuario ingresar la cantidad y la moneda para el retiro
			System.out.print("Ingrese la cantidad a retirar: ");
			double cantidadRetiro = Double.parseDouble(scanner.next());
			scanner.nextLine(); // Consumir el salto de línea
			System.out.print("Ingrese la moneda (USD, EUR, GBP, CLP): ");
			String monedaRetiro = scanner.nextLine().toUpperCase();
			
			// Realiza el retiro de fondos
			billeteraService.retirar(cantidadRetiro, monedaRetiro);
		} catch (NumberFormatException e) {
			// Maneja la excepción de entrada inválida para la cantidad
			logger.log(Level.WARNING, "Entrada inválida. Por favor, ingrese un número válido para la cantidad.");
			scanner.nextLine(); // Consumir el salto de línea
		}
	}
	
	// Método para procesar la conversión de fondos entre monedas
	private static void procesarConversion() {
		try {
			// Solicita al usuario ingresar la cantidad y las monedas de origen y destino
			System.out.print("Ingrese la cantidad a convertir: ");
			double cantidadConvertir = Double.parseDouble(scanner.next());
			scanner.nextLine(); // Consumir el salto de línea
			System.out.print("Ingrese la moneda de origen (USD, EUR, GBP, CLP): ");
			String monedaOrigen = scanner.nextLine().toUpperCase();
			System.out.print("Ingrese la moneda de destino (USD, EUR, GBP, CLP): ");
			String monedaDestino = scanner.nextLine().toUpperCase();
			
			// Realiza la conversión de fondos entre monedas
			billeteraService.convertirMoneda(cantidadConvertir, monedaOrigen, monedaDestino);
		} catch (NumberFormatException e) {
			// Maneja la excepción de entrada inválida para la cantidad
			logger.log(Level.WARNING, "Entrada inválida. Por favor, ingrese un número válido para la cantidad.");
			scanner.nextLine(); // Consumir el salto de línea
		}
	}

}