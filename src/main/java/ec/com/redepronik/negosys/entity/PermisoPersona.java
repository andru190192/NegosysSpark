package ec.com.redepronik.negosys.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "`permisosPersonas`")
public class PermisoPersona implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Boolean activo;
	private Rol rol;
	private Persona persona;

	public PermisoPersona() {
	}

	public PermisoPersona(Integer id, Boolean activo, Rol rol, Persona persona) {
		this.id = id;
		this.activo = activo;
		this.rol = rol;
		this.persona = persona;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PermisoPersona other = (PermisoPersona) obj;
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

	@Id
	@SequenceGenerator(allocationSize = 1, name = "PERMISOSPERSONAS_ID_GENERATOR", sequenceName = "\"permisosPersonas_id_seq\"")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERMISOSPERSONAS_ID_GENERATOR")
	@Column(unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "persona", nullable = false)
	public Persona getPersona() {
		return persona;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public Rol getRol() {
		return this.rol;
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

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

}
