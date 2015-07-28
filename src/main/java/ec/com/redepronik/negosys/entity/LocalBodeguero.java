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
@Table(name = "`localesBodegueros`")
public class LocalBodeguero implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Persona bodeguero;
	private Local local;
	private Boolean activo;

	public LocalBodeguero() {
	}

	public LocalBodeguero(Integer id, Persona bodeguero, Local local,
			Boolean activo) {
		this.id = id;
		this.bodeguero = bodeguero;
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
		LocalBodeguero other = (LocalBodeguero) obj;
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
	public Persona getBodeguero() {
		return bodeguero;
	}

	@Id
	@SequenceGenerator(allocationSize = 1, name = "LOCALESBODEGUEROS_ID_GENERATOR", sequenceName = "LOCALESBODEGUEROS_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOCALESBODEGUEROS_ID_GENERATOR")
	@Column(unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	@ManyToOne
	@JoinColumn(nullable = false)
	public Local getLocal() {
		return local;
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

	public void setBodeguero(Persona bodeguero) {
		this.bodeguero = bodeguero;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

}