package ec.com.redepronik.negosys.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cotizaciones")
public class Cotizacion implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Timestamp fechaEmision;
	private String establecimiento;
	private String puntoEmision;
	private String secuencia;
	private Persona cliente;
	private Persona vendedor;
	private Persona cajero;
	private List<DetalleCotizacion> detallesCotizaciones;

	public Cotizacion() {
	}

	public Cotizacion(Integer id, Persona cliente, Persona vendedor,
			Persona cajero, Timestamp fechaEmision, Boolean activo,
			String establecimiento, String puntoEmision, String secuencia,
			List<DetalleCotizacion> detallesCotizaciones) {
		this.id = id;
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.cajero = cajero;
		this.fechaEmision = fechaEmision;
		this.establecimiento = establecimiento;
		this.puntoEmision = puntoEmision;
		this.secuencia = secuencia;
		this.detallesCotizaciones = detallesCotizaciones;
	}

	public DetalleCotizacion addDetalleCotizacion(
			DetalleCotizacion detalleCotizacion) {
		getDetallesCotizaciones().add(detalleCotizacion);
		detalleCotizacion.setCotizacion(this);

		return detalleCotizacion;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cotizacion other = (Cotizacion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@ManyToOne
	@JoinColumn(nullable = false)
	public Persona getCajero() {
		return cajero;
	}

	@ManyToOne
	@JoinColumn(nullable = false)
	public Persona getCliente() {
		return cliente;
	}

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "cotizacion")
	// @IndexColumn(name = "orden", base = 1)
	public List<DetalleCotizacion> getDetallesCotizaciones() {
		return detallesCotizaciones;
	}

	@Column(length = 3, nullable = false)
	public String getEstablecimiento() {
		return establecimiento;
	}

	@Column(nullable = false)
	public Timestamp getFechaEmision() {
		return fechaEmision;
	}

	@Id
	@SequenceGenerator(allocationSize = 1, name = "COTIZACIONES_ID_GENERATOR", sequenceName = "COTIZACIONES_ID_SEQ")
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COTIZACIONES_ID_GENERATOR")
	@Column(unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	@Column(name = "`puntoEmision`", length = 3, nullable = false)
	public String getPuntoEmision() {
		return puntoEmision;
	}

	@Column(length = 9, nullable = false)
	public String getSecuencia() {
		return secuencia;
	}

	@ManyToOne
	@JoinColumn(nullable = false)
	public Persona getVendedor() {
		return vendedor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public DetalleCotizacion removeDetalleCotizacion(
			DetalleCotizacion detalleCotizacion) {
		getDetallesCotizaciones().remove(detalleCotizacion);
		detalleCotizacion.setCotizacion(null);

		return detalleCotizacion;
	}

	public void setCajero(Persona cajero) {
		this.cajero = cajero;
	}

	public void setCliente(Persona cliente) {
		this.cliente = cliente;
	}

	public void setDetallesCotizaciones(
			List<DetalleCotizacion> detallesCotizaciones) {
		this.detallesCotizaciones = detallesCotizaciones;
	}

	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}

	public void setFechaEmision(Timestamp fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPuntoEmision(String puntoEmision) {
		this.puntoEmision = puntoEmision;
	}

	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}

	public void setVendedor(Persona vendedor) {
		this.vendedor = vendedor;
	}

}