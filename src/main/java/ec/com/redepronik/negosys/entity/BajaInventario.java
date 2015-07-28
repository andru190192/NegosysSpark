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
@Table(name = "`bajasInventarios`")
public class BajaInventario implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Timestamp fechaEmision;
	private String establecimiento;
	private String puntoEmision;
	private String secuencia;
	private Persona bodeguero;
	private List<DetalleBajaInventario> detallesBajasInventarios;

	public BajaInventario() {
	}

	public BajaInventario(Integer id, Persona bodeguero,
			Timestamp fechaEmision, String establecimiento,
			String puntoEmision, String secuencia,
			List<DetalleBajaInventario> detallesBajasInventarios) {
		this.id = id;
		this.bodeguero = bodeguero;
		this.fechaEmision = fechaEmision;
		this.establecimiento = establecimiento;
		this.puntoEmision = puntoEmision;
		this.secuencia = secuencia;
		this.detallesBajasInventarios = detallesBajasInventarios;
	}

	public DetalleBajaInventario addDetalleBajaInventario(
			DetalleBajaInventario detalleBajaInventario) {
		getDetallesBajasInventarios().add(detalleBajaInventario);
		detalleBajaInventario.setBajaInventario(this);

		return detalleBajaInventario;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BajaInventario other = (BajaInventario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@ManyToOne
	@JoinColumn(nullable = false)
	public Persona getBodeguero() {
		return bodeguero;
	}

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "bajaInventario")
	// @IndexColumn(name = "orden", base = 1)
	public List<DetalleBajaInventario> getDetallesBajasInventarios() {
		return detallesBajasInventarios;
	}

	@Column(length = 3, nullable = false)
	public String getEstablecimiento() {
		return establecimiento;
	}

	@Column(name = "`fechaEmision`", nullable = false)
	public Timestamp getFechaEmision() {
		return fechaEmision;
	}

	@Id
	@SequenceGenerator(allocationSize = 1, name = "BAJASINVENTARIOS_ID_GENERATOR", sequenceName = "BAJASINVENTARIOS_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BAJASINVENTARIOS_ID_GENERATOR")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public DetalleBajaInventario removeDetalleBajaInventario(
			DetalleBajaInventario detalleBajaInventario) {
		getDetallesBajasInventarios().remove(detalleBajaInventario);
		detalleBajaInventario.setBajaInventario(null);

		return detalleBajaInventario;
	}

	public void setBodeguero(Persona bodeguero) {
		this.bodeguero = bodeguero;
	}

	public void setDetallesBajasInventarios(
			List<DetalleBajaInventario> detallesBajasInventarios) {
		this.detallesBajasInventarios = detallesBajasInventarios;
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

}