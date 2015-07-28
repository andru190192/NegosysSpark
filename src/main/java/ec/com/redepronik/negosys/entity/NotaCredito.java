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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "`notasCreditos`")
public class NotaCredito implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String autorizacion;
	private String motivo;
	private Factura factura;
	private Persona cajero;
	private Timestamp fechaEmision;
	private List<DetalleNotaCredito> detallesNotasCreditos;
	private String establecimiento;
	private String puntoEmision;
	private String secuencia;

	public NotaCredito() {
	}

	public NotaCredito(Integer id, String autorizacion, String motivo,
			Factura factura, Persona cajero, Timestamp fechaEmision,
			List<DetalleNotaCredito> detallesNotasCreditos,
			String establecimiento, String puntoEmision, String secuencia) {
		this.id = id;
		this.autorizacion = autorizacion;
		this.motivo = motivo;
		this.factura = factura;
		this.cajero = cajero;
		this.fechaEmision = fechaEmision;
		this.detallesNotasCreditos = detallesNotasCreditos;
		this.establecimiento = establecimiento;
		this.puntoEmision = puntoEmision;
		this.secuencia = secuencia;
	}

	public DetalleNotaCredito addDetallesNotasCreditos(
			DetalleNotaCredito detalleNotaCredito) {
		getDetallesNotasCreditos().add(detalleNotaCredito);
		detalleNotaCredito.setNotaCredito(this);

		return detalleNotaCredito;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotaCredito other = (NotaCredito) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Column(length = 10)
	public String getAutorizacion() {
		return autorizacion;
	}

	@ManyToOne
	@JoinColumn(nullable = false)
	public Persona getCajero() {
		return cajero;
	}

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "notaCredito")
	public List<DetalleNotaCredito> getDetallesNotasCreditos() {
		return detallesNotasCreditos;
	}

	@Column(length = 3, nullable = false)
	public String getEstablecimiento() {
		return establecimiento;
	}

	@OneToOne
	@JoinColumn(nullable = false)
	public Factura getFactura() {
		return factura;
	}

	@Column(name = "`fechaEmision`", nullable = false)
	public Timestamp getFechaEmision() {
		return fechaEmision;
	}

	@Id
	@SequenceGenerator(allocationSize = 1, name = "NOTASCREDITOS_ID_GENERATOR", sequenceName = "NOTASCREDITOS_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTASCREDITOS_ID_GENERATOR")
	@Column(unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	@Column(length = 200)
	public String getMotivo() {
		return motivo;
	}

	@Column(name = "`puntoEmision`", length = 3, nullable = false)
	public String getPuntoEmision() {
		return puntoEmision;
	}

	@Column(length = 9, nullable = false)
	public String getSecuencia() {
		return secuencia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public DetalleNotaCredito removeDetallesNotasCreditos(
			DetalleNotaCredito detalleNotaCredito) {
		getDetallesNotasCreditos().remove(detalleNotaCredito);
		detalleNotaCredito.setNotaCredito(null);

		return detalleNotaCredito;
	}

	public void setAutorizacion(String autorizacion) {
		this.autorizacion = autorizacion;
	}

	public void setCajero(Persona cajero) {
		this.cajero = cajero;
	}

	public void setDetallesNotasCreditos(
			List<DetalleNotaCredito> detallesNotasCreditos) {
		this.detallesNotasCreditos = detallesNotasCreditos;
	}

	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public void setFechaEmision(Timestamp fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public void setPuntoEmision(String puntoEmision) {
		this.puntoEmision = puntoEmision;
	}

	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}

}