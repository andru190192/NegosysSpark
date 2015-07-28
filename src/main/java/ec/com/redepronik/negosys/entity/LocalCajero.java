package ec.com.redepronik.negosys.entity;

import java.io.Serializable;

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
@Table(name = "`localesCajeros`")
public class LocalCajero implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Persona cajero;
	private Local local;
	private Boolean activo;

	public LocalCajero() {
	}

	public LocalCajero(Integer id, Persona cajero, Local local, Boolean activo) {
		this.id = id;
		this.cajero = cajero;
		this.local = local;
		this.activo = activo;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LocalCajero other = (LocalCajero) obj;
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

	@Id
	@SequenceGenerator(allocationSize = 1, name = "LOCALESCAJEROS_ID_GENERATOR", sequenceName = "LOCALESCAJEROS_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOCALESCAJEROS_ID_GENERATOR")
	@Column(unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	@ManyToOne
	@JoinColumn(nullable = false)
	public Local getLocal() {
		return this.local;
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

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

}