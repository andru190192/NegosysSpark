package ec.com.redepronik.negosys.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ingresos")
public class Ingreso implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Boolean activo;
	private String codigoDocumento;
	private Date fechaEmisionDocumento;
	private Timestamp fechaIngreso;
	private Timestamp fechaCierre;
	private Boolean pagado;
	private Persona bodeguero;
	private Persona proveedor;
	private BigDecimal total;
	private List<DetalleIngreso> detallesIngresos;

	public Ingreso() {
	}

	public Ingreso(Long id, Boolean activo, String codigoDocumento,
			Date fechaEmisionDocumento, Timestamp fechaIngreso,
			Timestamp fechaCierre, Boolean pagado, Persona bodeguero,
			Persona proveedor, BigDecimal total,
			List<DetalleIngreso> detallesIngresos) {
		this.id = id;
		this.activo = activo;
		this.codigoDocumento = codigoDocumento;
		this.fechaEmisionDocumento = fechaEmisionDocumento;
		this.fechaIngreso = fechaIngreso;
		this.fechaCierre = fechaCierre;
		this.pagado = pagado;
		this.bodeguero = bodeguero;
		this.proveedor = proveedor;
		this.total = total;
		this.detallesIngresos = detallesIngresos;
	}

	public DetalleIngreso addDetallesIngresos(DetalleIngreso detalleingreso) {
		getDetallesIngresos().add(detalleingreso);
		detalleingreso.setIngreso(this);

		return detalleingreso;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingreso other = (Ingreso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Column(nullable = false)
	public Boolean getActivo() {
		return this.activo;
	}

	@ManyToOne
	@JoinColumn(nullable = false)
	public Persona getBodeguero() {
		return bodeguero;
	}

	@Column(name = "`codigoDocumento`", nullable = false, length = 25)
	public String getCodigoDocumento() {
		return codigoDocumento;
	}

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "ingreso")
	// @IndexColumn(name = "orden", base = 1)
	public List<DetalleIngreso> getDetallesIngresos() {
		return detallesIngresos;
	}

	@Column(name = "`fechaCierre`")
	public Timestamp getFechaCierre() {
		return fechaCierre;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "`fechaEmisionDocumento`", nullable = false)
	public Date getFechaEmisionDocumento() {
		return fechaEmisionDocumento;
	}

	@Column(name = "`fechaIngreso`", nullable = false)
	public Timestamp getFechaIngreso() {
		return fechaIngreso;
	}

	@Id
	@SequenceGenerator(allocationSize = 1, name = "INGRESOS_ID_GENERATOR", sequenceName = "INGRESOS_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INGRESOS_ID_GENERATOR")
	@Column(unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	@Column(nullable = false)
	public Boolean getPagado() {
		return this.pagado;
	}

	@ManyToOne
	@JoinColumn(nullable = false)
	public Persona getProveedor() {
		return this.proveedor;
	}

	@Column(nullable = false, precision = 8, scale = 2)
	public BigDecimal getTotal() {
		return this.total;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public DetalleIngreso removeDetalleIngreso(DetalleIngreso detalleIngreso) {
		getDetallesIngresos().remove(detalleIngreso);
		detalleIngreso.setIngreso(null);

		return detalleIngreso;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public void setBodeguero(Persona bodeguero) {
		this.bodeguero = bodeguero;
	}

	public void setCodigoDocumento(String codigoDocumento) {
		this.codigoDocumento = codigoDocumento;
	}

	public void setDetallesIngresos(List<DetalleIngreso> detallesIngresos) {
		this.detallesIngresos = detallesIngresos;
	}

	public void setFechaCierre(Timestamp fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public void setFechaEmisionDocumento(Date fechaEmisionDocumento) {
		this.fechaEmisionDocumento = fechaEmisionDocumento;
	}

	public void setFechaIngreso(Timestamp fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPagado(Boolean pagado) {
		this.pagado = pagado;
	}

	public void setProveedor(Persona proveedor) {
		this.proveedor = proveedor;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}