package ec.com.redepronik.negosys.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "`detallesTraspasos`")
public class DetalleTraspaso implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer cantidad;
	private Producto producto;
	private Traspaso traspaso;

	public DetalleTraspaso() {
	}

	public DetalleTraspaso(Integer id, Integer cantidad, Producto producto,
			Traspaso traspaso) {
		this.id = id;
		this.cantidad = cantidad;
		this.producto = producto;
		this.traspaso = traspaso;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetalleTraspaso other = (DetalleTraspaso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Column(nullable = false)
	public Integer getCantidad() {
		return this.cantidad;
	}

	@Id
	@SequenceGenerator(allocationSize = 1, name = "DETALLESTRASPASOS_ID_GENERATOR", sequenceName = "DETALLESTRASPASOS_ID_SEQ")
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DETALLESTRASPASOS_ID_GENERATOR")
	@Column(unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	@ManyToOne
	@JoinColumn(nullable = false)
	public Producto getProducto() {
		return this.producto;
	}

	@ManyToOne
	@JoinColumn(nullable = false)
	public Traspaso getTraspaso() {
		return this.traspaso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public void setTraspaso(Traspaso traspaso) {
		this.traspaso = traspaso;
	}

}
