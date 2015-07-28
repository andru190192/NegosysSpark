package ec.com.redepronik.negosys.utils.enums;

public enum Meses {
	EN(1, "ENERO"), FE(2, "FEBRERO"), MR(3, "MARZO"), AB(4, "ABRIL"), MY(5,
			"MAYO"), JN(6, "JUNIO"), JL(7, "JULIO"), AG(8, "AGOSTO"), SE(9,
			"SEPTIEMBRE"), OC(10, "OCTUBRE"), NO(11, "NOVIEMBRE"), DI(12,
			"DICIEMBRE");

	private final Integer id;
	private final String nombre;

	private Meses(Integer id, String nombre) {
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
