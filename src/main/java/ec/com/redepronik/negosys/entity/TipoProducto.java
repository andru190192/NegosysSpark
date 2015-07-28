package ec.com.redepronik.negosys.entity;

public enum TipoProducto {

	BN(1, "BIEN"), SR(2, "SERVICIO");

	private final Integer id;
	private final String nombre;

	private TipoProducto(Integer id, String nombre) {
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