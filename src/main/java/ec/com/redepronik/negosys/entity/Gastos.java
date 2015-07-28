package ec.com.redepronik.negosys.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

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
@Table(name = "gastos")
public class Gastos implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Timestamp fecha;
	private Boolean activo;
	private String descripcion;
	private Boolean iva;
	private BigDecimal precioUnitarioCosto;
	private Integer cantidad;
	private BigDecimal porcentajeRetencionFuente;
	private BigDecimal porcentajeRetencionIva;
	private Persona cajero;
	private TipoPago tipoPago;
	private Persona proveedor;

	public Gastos() {
	}

	public Gastos(Integer id, Timestamp fecha, Boolean activo,
			String descripcion, Boolean iva, BigDecimal precioUnitarioCosto,
			Integer cantidad, BigDecimal porcentajeRetencionFuente,
			BigDecimal porcentajeRetencionIva, Persona cajero,
			TipoPago tipoPago, Persona proveedor) {
		this.id = id;
		this.fecha = fecha;
		this.activo = activo;
		this.descripcion = descripcion;
		this.iva = iva;
		this.precioUnitarioCosto = precioUnitarioCosto;
		this.cantidad = cantidad;
		this.porcentajeRetencionFuente = porcentajeRetencionFuente;
		this.porcentajeRetencionIva = porcentajeRetencionIva;
		this.cajero = cajero;
		this.tipoPago = tipoPago;
		this.proveedor = proveedor;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gastos other = (Gastos) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Column(nullable = false)
	public Boolean getActivo() {
		return activo;
	}

	@ManyToOne
	@JoinColumn(nullable = false)
	public Persona getCajero() {
		return cajero;
	}

	@Column(nullable = false)
	public Integer getCantidad() {
		return cantidad;
	}

	@Column(nullable = false, length = 150)
	public String getDescripcion() {
		return descripcion;
	}

	@Column(nullable = false)
	public Timestamp getFecha() {
		return fecha;
	}

	@Id
	@SequenceGenerator(allocationSize = 1, name = "GASTOS_ID_GENERATOR", sequenceName = "GASTOS_ID_SEQ")
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GASTOS_ID_GENERATOR")
	@Column(unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	@Column(nullable = false)
	public Boolean getIva() {
		return iva;
	}

	@Column(name = "`porcentajeRetencionFuente`", precision = 5, scale = 2)
	public BigDecimal getPorcentajeRetencionFuente() {
		return porcentajeRetencionFuente;
	}

	@Column(name = "`porcentajeRetencionIva`", precision = 5, scale = 2)
	public BigDecimal getPorcentajeRetencionIva() {
		return porcentajeRetencionIva;
	}

	@Column(name = "`precioUnitarioCosto`", nullable = false, precision = 12, scale = 6)
	public BigDecimal getPrecioUnitarioCosto() {
		return precioUnitarioCosto;
	}

	@ManyToOne
	@JoinColumn(nullable = false)
	public Persona getProveedor() {
		return proveedor;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "`tipoPago`", nullable = false)
	public TipoPago getTipoPago() {
		return tipoPago;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public void setCajero(Persona cajero) {
		this.cajero = cajero;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIva(Boolean iva) {
		this.iva = iva;
	}

	public void setPorcentajeRetencionFuente(
			BigDecimal porcentajeRetencionFuente) {
		this.porcentajeRetencionFuente = porcentajeRetencionFuente;
	}

	public void setPorcentajeRetencionIva(BigDecimal porcentajeRetencionIva) {
		this.porcentajeRetencionIva = porcentajeRetencionIva;
	}

	public void setPrecioUnitarioCosto(BigDecimal precioUnitarioCosto) {
		this.precioUnitarioCosto = precioUnitarioCosto;
	}

	public void setProveedor(Persona proveedor) {
		this.proveedor = proveedor;
	}

	public void setTipoPago(TipoPago tipoPago) {
		this.tipoPago = tipoPago;
	}

}