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
@Table(name = "traspasos")
public class Traspaso implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Timestamp fecha;
	private String establecimiento;
	private String puntoEmision;
	private String secuencia;
	private List<DetalleTraspaso> detallesTraspasos;
	private Local localOrigen;
	private Local localDestino;

	public Traspaso() {
	}

	public Traspaso(Integer id, Timestamp fecha, String establecimiento,
			String puntoEmision, String secuencia,
			List<DetalleTraspaso> detallesTraspasos, Local localOrigen,
			Local localDestino) {
		this.id = id;
		this.fecha = fecha;
		this.establecimiento = establecimiento;
		this.puntoEmision = puntoEmision;
		this.secuencia = secuencia;
		this.detallesTraspasos = detallesTraspasos;
		this.localOrigen = localOrigen;
		this.localDestino = localDestino;
	}

	public DetalleTraspaso addDetalleTraspaso(DetalleTraspaso detalletraspaso) {
		getDetallesTraspasos().add(detalletraspaso);
		detalletraspaso.setTraspaso(this);

		return detalletraspaso;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Traspaso other = (Traspaso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "traspaso")
	public List<DetalleTraspaso> getDetallesTraspasos() {
		return detallesTraspasos;
	}

	@Column(length = 3, nullable = false)
	public String getEstablecimiento() {
		return establecimiento;
	}

	@Column(nullable = false)
	public Timestamp getFecha() {
		return this.fecha;
	}

	@Id
	@SequenceGenerator(allocationSize = 1, name = "TRASPASOS_ID_GENERATOR", sequenceName = "TRASPASOS_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRASPASOS_ID_GENERATOR")
	@Column(unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	@ManyToOne
	@JoinColumn(name = "`localDestino`", nullable = false)
	public Local getLocalDestino() {
		return localDestino;
	}

	@ManyToOne
	@JoinColumn(name = "`localOrigen`", nullable = false)
	public Local getLocalOrigen() {
		return localOrigen;
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

	public DetalleTraspaso removeDetalleTraspaso(DetalleTraspaso detalletraspaso) {
		getDetallesTraspasos().remove(detalletraspaso);
		detalletraspaso.setTraspaso(null);

		return detalletraspaso;
	}

	public void setDetallesTraspasos(List<DetalleTraspaso> detallesTraspasos) {
		this.detallesTraspasos = detallesTraspasos;
	}

	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLocalDestino(Local localDestino) {
		this.localDestino = localDestino;
	}

	public void setLocalOrigen(Local localOrigen) {
		this.localOrigen = localOrigen;
	}

	public void setPuntoEmision(String puntoEmision) {
		this.puntoEmision = puntoEmision;
	}

	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}

}