package pooFinalJunio2024;

import java.time.LocalDate;
import java.util.Objects;

public class Vino implements Comparable<Vino>{
	private final LocalDate fechaDeProduccion; 
	private final String nombre;
	private final ColorVino color;
	private final MaduracionVino maduracion;
	private final int coste;
		
	public MaduracionVino getMaduracion() {
		return maduracion;
	}
	
	public int getCoste() {
		return coste;
	}
	
	// Primer constructor
	public Vino(LocalDate fechaDeProduccion, String nombre, ColorVino color, MaduracionVino maduracion, int coste) {
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
		return "Vino [fechaDeProduccion=" + fechaDeProduccion + ", nombre=" + nombre + ", color=" + color
				+ ", maduracion=" + maduracion + ", coste=" + coste + "]";
	}
		
}
