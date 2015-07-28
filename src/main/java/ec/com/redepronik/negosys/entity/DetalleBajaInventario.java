package ec.com.redepronik.negosys.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "`detallesBajasInventarios`")
public class DetalleBajaInventario implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Integer orden;
	private Integer cantidad;
	private BigDecimal precioCosto;
	private BajaInventario bajaInventario;
	private MotivoBaja motivoBaja;
	private Producto producto;

	public DetalleBajaInventario() {
	}

	public DetalleBajaInventario(Long id, Integer orden,
			BajaInventario bajaInventario, MotivoBaja motivoBaja,
			Producto producto, Integer cantidad, BigDecimal precioCosto) {
		this.id = id;
		this.orden = orden;
		this.bajaInventario = bajaInventario;
		this.motivoBaja = motivoBaja;
		this.producto = producto;
		this.cantidad = cantidad;
		this.precioCosto = precioCosto;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetalleBajaInventario other = (DetalleBajaInventario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@ManyToOne
	@JoinColumn(name = "`bajaInventario`", nullable = false)
	public BajaInventario getBajaInventario() {
		return bajaInventario;
	}

	@Column(nullable = false)
	public Integer getCantidad() {
		return cantidad;
	}

	@Id
	@SequenceGenerator(allocationSize = 1, name = "DETALLESBAJASINVENTARIOS_ID_GENERATOR", sequenceName = "DETALLESBAJASINVENTARIOS_ID_SEQ")
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DETALLESBAJASINVENTARIOS_ID_GENERATOR")
	@Column(unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "`motivoBaja`", nullable = false)
	public MotivoBaja getMotivoBaja() {
		return motivoBaja;
	}

	@Column(nullable = false)
	public Integer getOrden() {
		return orden;
	}

	@Column(name = "`precioCosto`", nullable = false, precision = 12, scale = 6)
	public BigDecimal getPrecioCosto() {
		return precioCosto;
	}

	@ManyToOne
	@JoinColumn(nullable = false)
	public Producto getProducto() {
		return producto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public void setBajaInventario(BajaInventario bajaInventario) {
		this.bajaInventario = bajaInventario;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMotivoBaja(MotivoBaja motivoBaja) {
		this.motivoBaja = motivoBaja;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public void setPrecioCosto(BigDecimal precioCosto) {
		this.precioCosto = precioCosto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}