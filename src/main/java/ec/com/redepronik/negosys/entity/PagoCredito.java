package ec.com.redepronik.negosys.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "`pagosCreditos`")
public class PagoCredito implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private BigDecimal cuota;
	private Integer orden;
	private Date fechaPago;
	private DetalleCredito detalleCredito;
	private Persona cajero;

	public PagoCredito() {
	}

	public PagoCredito(Integer id, BigDecimal cuota, Integer orden,
			Date fechaPago, DetalleCredito detalleCredito, Persona cajero) {
		this.id = id;
		this.cuota = cuota;
		this.orden = orden;
		this.fechaPago = fechaPago;
		this.detalleCredito = detalleCredito;
		this.cajero = cajero;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PagoCredito other = (PagoCredito) obj;
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

	@Column(nullable = false, precision = 8, scale = 2)
	public BigDecimal getCuota() {
		return this.cuota;
	}

	@ManyToOne
	@JoinColumn(nullable = false)
	public DetalleCredito getDetalleCredito() {
		return detalleCredito;
	}

	@Column(name = "`fechaPago`")
	@Temporal(TemporalType.DATE)
	public Date getFechaPago() {
		return fechaPago;
	}

	@Id
	@SequenceGenerator(allocationSize = 1, name = "PAGOSCREDITOS_ID_GENERATOR", sequenceName = "PAGOSCREDITOS_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAGOSCREDITOS_ID_GENERATOR")
	@Column(unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	@Column(nullable = false)
	public Integer getOrden() {
		return orden;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public void setCajero(Persona cajero) {
		this.cajero = cajero;
	}

	public void setCuota(BigDecimal cuota) {
		this.cuota = cuota;
	}

	public void setDetalleCredito(DetalleCredito detalleCredito) {
		this.detalleCredito = detalleCredito;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

}
