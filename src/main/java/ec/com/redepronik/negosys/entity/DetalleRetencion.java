package ec.com.redepronik.negosys.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
@Table(name = "`detallesRetenciones`")
public class DetalleRetencion implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Retencion retencion;
	private TipoComprobanteRetencion tipoComprobanteRetencion;
	private String numeroComprobante;
	private Date fechaEmision;
	private ImpuestoRetencion impuestoRetencion;
	private TarifaRetencion tarifaRetencion;
	private BigDecimal baseImponible;
	private BigDecimal porcentajeRetencion;

	public DetalleRetencion() {
	}

	public DetalleRetencion(Integer id, Retencion retencion,
			TipoComprobanteRetencion tipoComprobanteRetencion,
			String numeroComprobante, Date fechaEmision,
			ImpuestoRetencion impuestoRetencion,
			TarifaRetencion tarifaRetencion, BigDecimal baseImponible,
			BigDecimal porcentajeRetencion) {
		this.id = id;
		this.retencion = retencion;
		this.tipoComprobanteRetencion = tipoComprobanteRetencion;
		this.numeroComprobante = numeroComprobante;
		this.fechaEmision = fechaEmision;
		this.impuestoRetencion = impuestoRetencion;
		this.tarifaRetencion = tarifaRetencion;
		this.baseImponible = baseImponible;
		this.porcentajeRetencion = porcentajeRetencion;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetalleRetencion other = (DetalleRetencion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Column(name = "`baseImponible`", precision = 8, scale = 2, nullable = false)
	public BigDecimal getBaseImponible() {
		return baseImponible;
	}

	@Column(name = "`fechaEmision`", nullable = false)
	public Date getFechaEmision() {
		return fechaEmision;
	}

	@Id
	@SequenceGenerator(allocationSize = 1, name = "DETALLESRETENCIONES_ID_GENERATOR", sequenceName = "DETALLESRETENCIONES_ID_SEQ")
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DETALLESRETENCIONES_ID_GENERATOR")
	@Column(unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "`impuestoRetencion`", nullable = false)
	public ImpuestoRetencion getImpuestoRetencion() {
		return impuestoRetencion;
	}

	@Column(name = "`numeroComprobante`", length = 15, nullable = false)
	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	@Column(name = "`porcentajeRetencion`", precision = 5, scale = 2, nullable = false)
	public BigDecimal getPorcentajeRetencion() {
		return porcentajeRetencion;
	}

	@ManyToOne
	@JoinColumn(nullable = false)
	public Retencion getRetencion() {
		return retencion;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "`tarifaRetencion`", nullable = false)
	public TarifaRetencion getTarifaRetencion() {
		return tarifaRetencion;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "`tipoComprobanteRetencion`", nullable = false)
	public TipoComprobanteRetencion getTipoComprobanteRetencion() {
		return tipoComprobanteRetencion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public void setBaseImponible(BigDecimal baseImponible) {
		this.baseImponible = baseImponible;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setImpuestoRetencion(ImpuestoRetencion impuestoRetencion) {
		this.impuestoRetencion = impuestoRetencion;
	}

	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	public void setPorcentajeRetencion(BigDecimal porcentajeRetencion) {
		this.porcentajeRetencion = porcentajeRetencion;
	}

	public void setRetencion(Retencion retencion) {
		this.retencion = retencion;
	}

	public void setTarifaRetencion(TarifaRetencion tarifaRetencion) {
		this.tarifaRetencion = tarifaRetencion;
	}

	public void setTipoComprobanteRetencion(
			TipoComprobanteRetencion tipoComprobanteRetencion) {
		this.tipoComprobanteRetencion = tipoComprobanteRetencion;
	}
}