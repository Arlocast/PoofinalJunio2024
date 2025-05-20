package pooFinalJunio2024;

import java.util.HashMap;
import java.util.HashSet;

public abstract class Cata {
	private String nombre;
	private HashSet<Vino> vinos =  new HashSet<>();
	private HashSet<Valoracion> valoraciones = new HashSet<>();
	
	public Cata(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public HashSet<Vino> getVinos() {
		return vinos;
	}

	public void setVinos(HashSet<Vino> vinos) {
		this.vinos = vinos;
	}

	public HashSet<Valoracion> getValoraciones() {
		return valoraciones;
	}

	public void setValoraciones(HashSet<Valoracion> valoraciones) {
		this.valoraciones = valoraciones;
	}
	
	/*- Registrar un vino. Recibe como parámetro un vino y si el vino no está registrado ya, lo añade a la colección
	de vinos de la cata. El método devuelve un valor booleano para indicar si el vino se ha podido registrar.*/
	
	public boolean registrarVino(Vino vino) {
		if (vinos.contains(vino)) return false;
		return vinos.add(vino);
	}
	
	/*- Consultar la colección de catadores que han realizado alguna valoración.*/
	
	public HashSet<String> getCatadoresValoradores(){
		HashSet<String> catadores = new HashSet<>();
		for (Valoracion v : valoraciones) {
			catadores.add(v.getCatador());
		}
		return catadores;
	}
	
	/*- Consultar los vinos que ha valorado un catador. Devuelve la colección con los vinos que ha valorado el
	catador que se pasa como parámetro. Si no ha valorado ninguno devolverá una colección vacía.*/
	
	public HashSet<Vino> getVinosValoradosPorCatador(String catador){
		HashSet<Vino> vinosValorados = new HashSet<>();
		for (Valoracion v : valoraciones) {
			if (v.getCatador().equals(catador)) {
				vinosValorados.add(v.getVino());
			}
		}
		return vinosValorados;
	}
	
	/*- Consultar si un vino ha sido valorado por un catador. Recibe como parámetro el nombre del catador y el
	vino y devuelve un valor booleano verdadero si el vino ha sido valorado por el catador, o un valor falso si
	el vino no ha sido valorado o no es un vino registrado en la cata.*/
	
	public boolean vinoValoradoPorCatador(String catador, Vino vino) {
		if (!vinos.contains(vino)) return false;
		for (Valoracion va : valoraciones) {
			if (va.getCatador().equals(catador) && va.getVino().equals(vino)) {
				return true;
			}
		}
		return false;
	}
	
	/*- Valorar un vino por un catador. El método recibe como parámetro el nombre del catador, el vino que se
	va a valorar y las calificaciones olfativa, visual y gustativa correspondientes. Si el catador es apto para la
	cata, el vino está registrado en ella, y el vino aún no lo ha valorado el catador, se crea el objeto valoración,
	se añade a la colección de valoraciones y se devuelve el valor verdadero para indicar que la valoración se
	ha registrado con éxito. En caso contrario, se devuelve falso. El criterio para decidir si un catador es apto
	depende del tipo de cata. Es un requisito para la implementación de este método que se utilice el
	concepto de método plantilla.*/
	
	protected abstract boolean esApto(String catador);
	
	public boolean valorarVinoPorCatador(String catador, Vino vino, int calOlf, int calVis, int calGus) {
		if (this.esApto(catador) && vinos.contains(vino) && !this.vinoValoradoPorCatador(catador, vino)) {
			valoraciones.add(new Valoracion(catador, vino, calOlf, calVis, calGus));
			return true;
		}
		return false;
	}
	
	/*- Consultar las valoraciones organizadas por vinos. El método devuelve un mapa en el que las claves son
	los vinos y cada uno tiene asociada la colección de las valoraciones emitidas por los catadores.*/
	
	public HashMap<Vino, HashSet<Valoracion>> getValoracionesPorVino(){
		HashMap<Vino, HashSet<Valoracion>> valoracionesPorVino = new HashMap<>();
		for (Vino vi : vinos) {
			HashSet<Valoracion> valoracionesDeEseVino = new HashSet<>();
			for (Valoracion va : valoraciones) {
				if (va.getVino().equals(vi)) {
					valoracionesDeEseVino.add(va);
				}
			}
			valoracionesPorVino.putIfAbsent(vi, valoracionesDeEseVino);
		}
		return valoracionesPorVino;
	}
	
	/*- Método sobrecargado de consulta de valoraciones. El método devuelve una colección con las
	valoraciones asociadas al vino indicado como argumento. Si el vino está registrado, pero no ha sido
	valorado aún devolverá una colección vacía. En otro caso, devolverá una referencia nula.*/
	
	public HashSet<Valoracion> getValoracionesPorVino(Vino vino){
		if (!vinos.contains(vino)) return null;
		HashSet<Valoracion> valoracionesDeEseVino = new HashSet<>();
		for (Valoracion v : valoraciones) {
			if (v.getVino().equals(vino)) {
				valoracionesDeEseVino.add(v);
			}
		}
		return valoracionesDeEseVino;
	}
	
	
	/*- Consultar la puntuación de un vino pasado como argumento. La puntuación se calcula como la media de
	las notas establecidas por los catadores que lo han valorado. Si el vino no está registrado en la cata, el
	método devolverá el valor -1.*/
	
	public int getPuntuacionVino(Vino vino) {
		int notaTotal = 0;
		int numNotas = 0;
		if (!vinos.contains(vino)) return -1;
		for (Valoracion v : valoraciones) {
			if (v.getVino().equals(vino)){
				notaTotal += v.getNota();
				numNotas++;
			}
		}
		return notaTotal / numNotas;
	}
	
	/*- Consultar el mejor o mejores vinos de la cata (en caso de empate), es decir, los vinos que tienen la mejor
	puntuación. El método devolverá una colección con los vinos con la mejor puntuación o una colección
	vacía si ningún vino tiene valoraciones aún en la cata.*/
	
	public HashSet<Vino> getMejorVino(){
		HashSet<Vino> mejores = new HashSet<>();
		int mejorNota = 0;
		for (Valoracion v : valoraciones) {
			if (v.getNota() >= mejorNota) {
				if (v.getNota() > mejorNota) mejores.clear(); // Quitamos el antiguo vino con mejor nota. Solo en caso de que sea menor que el nuevo. Si es igual deben salir ambos
				mejorNota = v.getNota();
				mejores.add(v.getVino());
			}
		}
		return mejores;
	}
	
	@Override
	public Cata clone() {
		Cata copia = copiaSuperficial();
		copia.valoraciones = new HashSet<Valoracion>(valoraciones);
		copia.vinos = new HashSet<Vino>();
		return copia;
	}
	
	public Cata copiaSuperficial() {
		try {
			Cata copia = (Cata) super.clone();
			return copia;
		} catch (CloneNotSupportedException e) {
			System.out.println("No es posible clonar esta Cata");
		}
		return null;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + ": nombre=" + nombre + ", vinos=" + vinos + ", valoraciones=" + valoraciones;
	}
	
}
