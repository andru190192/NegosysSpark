package ec.com.redepronik.negosys.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
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

@Entity
@Table(name = "entradas")
public class Entrada implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private BigDecimal cuota;
	private Timestamp fechaPago;
	private Integer orden;
	private Persona cajero;
	private Factura factura;

	public Entrada() {
	}

	public Entrada(Integer id, BigDecimal cuota, Date fechaLimite,
			Date fechaMora, Boolean activo, Timestamp fechaPago,
			BigDecimal mora, Boolean pagado, BigDecimal saldo, Persona cajero,
			Factura factura, Integer orden) {
		this.id = id;
		this.cuota = cuota;
		this.fechaPago = fechaPago;
		this.cajero = cajero;
		this.factura = factura;
		this.orden = orden;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entrada other = (Entrada) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@ManyToOne
	@JoinColumn(name = "cajero", nullable = false)
	public Persona getCajero() {
		return cajero;
	}

	@Column(nullable = false, precision = 8, scale = 2)
	public BigDecimal getCuota() {
		return this.cuota;
	}

	@ManyToOne
	@JoinColumn(name = "factura", nullable = false)
	public Factura getFactura() {
		return this.factura;
	}

	@Column(name = "`fechaPago`")
	public Timestamp getFechaPago() {
		return fechaPago;
	}

	@Id
	@SequenceGenerator(allocationSize = 1, name = "ENTRADAS_ID_GENERATOR", sequenceName = "ENTRADAS_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ENTRADAS_ID_GENERATOR")
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

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public void setFechaPago(Timestamp fechaPago) {
		this.fechaPago = fechaPago;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}
}