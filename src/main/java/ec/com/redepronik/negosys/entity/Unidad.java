package ec.com.redepronik.negosys.entity;


public enum Unidad {

	UN("UNIDAD", "U"), 
	CJ("CAJA", "C"), 
	PQ("PAQUETE", "P"), 
	DP("DISPLAY", "D"), 
	BL("BULTO", "B"), 
	FN("FUNDA", "F"), 
	TR("TIRA", "U"),
	LB("LIBRA", "L");

	private final String nombre;
	private final String abreviatura;
		
	private Unidad(String nombre, String abreviatura) {
		this.nombre = nombre;
		this.abreviatura = abreviatura;
	}
	
	public String getAbreviatura() {
		return abreviatura;
	}
	
	public String getNombre() {
		return nombre;
	}
}