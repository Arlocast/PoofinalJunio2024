package pooFinalJunio2024;

import java.util.LinkedList;

public class CataProfesional extends Cata {

	//  Se caracteriza porque sólo se valoran vinos "reserva" o "gran reserva" y los catadores son sumilleres profesionales
	private final LinkedList<String> sumilleres;
	private final String responsable;
	
	public CataProfesional(String nombre, LinkedList<String> sumilleres) {
		super(nombre);
		this.sumilleres = new LinkedList<>(sumilleres);
		this.responsable = sumilleres.getFirst();
	}

	public LinkedList<String> getSumilleres() {
		return sumilleres;
	}

	public String getResponsable() {
		return responsable;
	}
	
	@Override
	protected boolean esApto(String catador) {
		if (sumilleres.contains(catador)) return true;
		return false;
	}
	
	@Override
	public boolean registrarVino(Vino vino) {
		if (vino.getMaduracion().equals(MaduracionVino.RESERVA) || vino.getMaduracion().equals(MaduracionVino.GRAN_RESERVA)) {
			return super.registrarVino(vino);
		}
		return false;
	}

	@Override
	public CataProfesional clone() {
		CataProfesional copia = (CataProfesional) super.clone();
		copia.sumilleres.clear();
		copia.sumilleres.addAll(sumilleres); // Modificamos la lista pq es final y no podemos reasignarla
		return copia;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", sumilleres=" + sumilleres + ", responsable=" + responsable;
	}

	
	
}
