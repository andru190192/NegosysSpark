package ec.com.redepronik.negosys.utils.enums;

public enum Dias {
	LU(1, "LUNES"), MA(2, "MARTES"), MI(3, "MIERCOLES"), JU(4, "JUEVES"), VI(5,
			"VIERNES"), SA(6, "SABADO"), DO(7, "DOMINGO");

	private final Integer id;
	private final String nombre;

	private Dias(Integer id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}
}
