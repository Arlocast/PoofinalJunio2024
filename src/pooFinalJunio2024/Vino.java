package pooFinalJunio2024;

import java.time.LocalDate;
import java.util.Objects;

public class Vino implements Comparable<Vino>{
	private final LocalDate fechaDeProduccion; 
	private final String nombre;
	private final ColorVino color;
	private final MaduracionVino maduracion;
	private final int coste;
		
	public LocalDate getFechaProduccion() {
		return fechaDeProduccion;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public MaduracionVino getMaduracion() {
		return maduracion;
	}
	
	public ColorVino getColor() {
		return color;
	}
	
	public int getCoste() {
		return coste;
	}
	
	// Primer constructor
	public Vino(LocalDate fechaDeProduccion, String nombre, ColorVino color, MaduracionVino maduracion, int coste) {
		if (fechaDeProduccion == null) throw new IllegalArgumentException("La fecha de producción del vino no puede ser nula");
		
		if (nombre == null) throw new IllegalArgumentException("El nombre del vino no puede ser nulo");
		
		
		boolean colorValid = false;
		for (ColorVino c : ColorVino.values()) {
			if (color.equals(c)) colorValid = true;
		}
		if (!colorValid) throw new IllegalArgumentException("El color del vino no está entre los posibles valores");
		
		boolean maduracionValid = false;
		for (MaduracionVino m : MaduracionVino.values()) {
			if (maduracion.equals(m)) maduracionValid = true;
		}
		if (!maduracionValid) throw new IllegalArgumentException("La maduración del vino no está entre los posibles valores");

		if (coste < 0) throw new IllegalArgumentException("El coste del vino no puede ser negativo");
		
		this.fechaDeProduccion = fechaDeProduccion; 
		this.nombre =  nombre;
		this.color =  color;
		this.maduracion =  maduracion;
		this.coste = coste;
	}
	
	// Segundo constructor
	public Vino(LocalDate fechaDeProduccion, String nombre) {
		this(fechaDeProduccion, nombre, ColorVino.BLANCO, MaduracionVino.JOVEN, 2);
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, fechaDeProduccion, maduracion, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vino other = (Vino) obj;
		return color == other.color && Objects.equals(fechaDeProduccion, other.fechaDeProduccion) 
									&& maduracion == other.maduracion
									&& Objects.equals(nombre, other.nombre);
	}

	@Override
	public int compareTo(Vino otro) {
		// Compara primero por el tipo de maduración y si son iguales por la fecha de producción (siempre en orden natural)
		int comparacionMaduracion = this.maduracion.compareTo(otro.maduracion);
		if (comparacionMaduracion != 0) {
			return comparacionMaduracion;
		}
		return this.fechaDeProduccion.compareTo(otro.fechaDeProduccion);
	}

	@Override
	public String toString() {
		return "Vino: [fechaDeProduccion=" + fechaDeProduccion + ", nombre=" + nombre + ", color=" + color
				+ ", maduracion=" + maduracion + ", coste=" + coste + "]";
	}
		
}
