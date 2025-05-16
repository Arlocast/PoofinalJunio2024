package pooFinalJunio2024;

public enum MaduracionVino {JOVEN("Joven", 0), 
							CRIANZA("Crianza", 6), 
							RESERVA("Reserva", 12), 
							GRAN_RESERVA("Gran Reserva", 36);
							
private final String descripcion;
private final int meses;
	
MaduracionVino(String desc, int meses){
	this.descripcion = desc;
	this.meses = meses;
}

public String getDescripcion() {
	return this.descripcion;
}

public int getMeses() {
	return this.meses;
}
}