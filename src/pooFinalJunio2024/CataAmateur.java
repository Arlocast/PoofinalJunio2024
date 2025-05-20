package pooFinalJunio2024;

import java.util.HashSet;

public class CataAmateur extends Cata{

	// Cualquier catador es apto y sólo se van a catar vinos jóvenes
	
	public CataAmateur(String nombre) {
		super(nombre);
	}

	@Override
	protected boolean esApto(String catador) {
		return true;
	}
	
	@Override
	public boolean registrarVino(Vino vino) {
		if (vino.getMaduracion().equals(MaduracionVino.JOVEN)) return super.registrarVino(vino);
		return false;
	}
	
	public boolean rectificarValoracion(Valoracion v, int nuevaCalOlf, int nuevaCalVis, int nuevaCalGus) {
		String catador = v.getCatador();
		Vino vino = v.getVino();
		HashSet<Valoracion> nuevasValoraciones = getValoraciones();
		if (nuevasValoraciones.remove(v)) { // Si no existe la valoracion no la podemos rectificar
			nuevasValoraciones.add(new Valoracion(catador, vino, nuevaCalOlf, nuevaCalVis, nuevaCalGus));
			this.setValoraciones(nuevasValoraciones);
			return true;
		}
		return false;
	}

	@Override
	public CataAmateur clone() {
		return (CataAmateur) super.clone();
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	
	
}
