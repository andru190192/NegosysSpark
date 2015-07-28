package ec.com.redepronik.negosys.entity;

public enum Rol {

	ADMI(1, "ADMINISTRADOR"), SEGU(2, "SEGURIDAD"), REPO(3, "REPORTEADOR"), BODE(
			4, "BODEGUERO"), CAJA(5, "CAJERO"), SUCA(6, "SUPERVISOR DE CAJA"), CLIE(
			7, "CLIENTE"), PROV(8, "PROVEEDOR"), VEND(9, "VENDEDOR"), CHOF(10,
			"CHOFER"), READ(11, "REPOTEADOR ADMINISTRADOR"), CONT(12,
			"CONTADOR");

	private final Integer id;
	private final String nombre;

	private Rol(Integer id, String nombre) {
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