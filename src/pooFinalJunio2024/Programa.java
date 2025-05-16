package pooFinalJunio2024;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;

public class Programa {
	public static void main(String[] args) {
		Vino v1 = new Vino(LocalDate.of(2020, 6, 1), "Marqués", ColorVino.TINTO, MaduracionVino.RESERVA, 15);
		Vino v2 = new Vino(LocalDate.of(2015, 10, 1), "Constantino", ColorVino.TINTO, MaduracionVino.GRAN_RESERVA, 30);
		Vino v3 = new Vino(LocalDate.of(2022, 9, 1), "El purísimo", ColorVino.ROSADO, MaduracionVino.CRIANZA, 5);
		Vino v4 = new Vino(LocalDate.of(2023, 10, 1), "Don Pinpón");
		Vino v5 = new Vino(LocalDate.of(2020, 11, 1), "Gil y Gil");
		
		HashSet<Vino> vinos = new HashSet<Vino>();
		vinos.add(v1);
		vinos.add(v2);
		vinos.add(v3);
		vinos.add(v4);
		vinos.add(v5);
		
		LinkedList<String> sumilleres = new LinkedList<String>();
		sumilleres.add("Andoni");
		sumilleres.add("Gemma");
		
		CataProfesional cata1 = new CataProfesional("Bodegas Talavera", sumilleres);
		CataAmateur cata2 = new CataAmateur("Taller Iniciación");
		
		LinkedList<Cata> catas = new LinkedList<>();
		catas.add(cata1);
		catas.add(cata2);
		
		for (Cata ca : catas) {
			for (Vino vi : vinos) {
				ca.registrarVino(vi);
			}
		}
		
		System.out.println("Vinos cata 1: " + cata1.getVinos());
		System.out.println("Vinos cata 2: " + cata2.getVinos());
		
		for (Cata ca : catas) {
			System.out.println("Cata: " + ca.getNombre());
			if (ca instanceof CataProfesional) {
				System.out.println("Responsable: " + ((CataProfesional) ca).getResponsable());
				for (Vino vi : vinos) {
					ca.valorarVinoPorCatador("Andoni", vi, 8, 7, 9);
					ca.valorarVinoPorCatador("Custodio", vi, 5, 8, 9);
				}
			}
			else if (ca instanceof CataAmateur) {
				// Es correcto poner la valoración así? 
				// No se ha introducido anteriormente en valoraciones por lo que no tengo otra forma de obtenerla
				((CataAmateur) ca).rectificarValoracion(new Valoracion("Custodio", v5, 5, 8, 9), 9, 9, 9);
			}
		}
		
		HashSet<Vino> mejoresVinos = cata1.getMejorVino();
		System.out.println(mejoresVinos);

		mejoresVinos.addAll(cata2.getMejorVino());
		
		System.out.println(mejoresVinos);		
	}
}
