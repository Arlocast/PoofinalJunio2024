package pooFinalJunio2024;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.function.Function;

public class Utils {
	public static HashSet<Vino> registrarVinos() throws IOException{
		HashSet<Vino> vinosRegistrados = new HashSet<>();
		Scanner scanner = new Scanner(System.in);
		String nombre;
		LocalDate fecha;
		boolean continua = true;
		
		while (continua) {
			System.out.println("\n");
			System.out.println("¡Se ha iniciado el registro de un nuevo vino!");
			System.out.println("Introduzca el nombre del vino: ");
			nombre = scanner.nextLine();
			
			System.out.println("Introduzca la fecha de producción del vino: ");
			fecha = LocalDate.parse(scanner.nextLine());
			
			Vino vino = new Vino(fecha, nombre);
			vinosRegistrados.add(vino);
			System.out.println("¡El vino " + vino.getNombre() + " con fecha de producción " + vino.getFechaProduccion() + " ha sido registrado con éxito!");
			
				System.out.println("¿Quiere registrar otro vino? si/no: ");
				String decision = scanner.nextLine();
				if (decision.equals("si")) continua = true;
				else continua = false;
				// Decidí que si no recibe ni si ni no termine, por simplificar el código.
		}
		
		scanner.close();
		return vinosRegistrados;
	}
	
	public static <T> void buscarReemplazar(Collection<T> col, T obj, Function<T, T> fun) {
		Collection<T> copia = new LinkedList<T>(col);
		col.clear();
		
		for (T o : copia) {
			if (o.equals(obj)) {
				T resul = fun.apply(o);
				col.add(resul);
			}
			else col.add(o);
		}
	}
}
