package ec.com.redepronik.negosys.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ciudades")
public class Ciudad implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Boolean activo;
	private String nombre;
	private Provincia provincia;
	private List<Persona> personas;
	private List<Local> locales;

	public Ciudad() {
	}

	public Ciudad(Integer id, Boolean activo, String nombre,
			Provincia provincia, List<Persona> personas, List<Local> locales) {
		this.id = id;
		this.activo = activo;
		this.nombre = nombre;
		this.provincia = provincia;
		this.personas = personas;
		this.locales = locales;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ciudad other = (Ciudad) obj;
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

	@Id
	@SequenceGenerator(allocationSize = 1, name = "CIUDADES_ID_GENERATOR", sequenceName = "CIUDADES_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CIUDADES_ID_GENERATOR")
	@Column(unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "ciudad")
	public List<Local> getLocales() {
		return locales;
	}

	@Column(nullable = false, length = 25)
	public String getNombre() {
		return this.nombre;
	}

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "ciudad")
	public List<Persona> getPersonas() {
		return this.personas;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public Provincia getProvincia() {
		return this.provincia;
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

	public void setLocales(List<Local> locales) {
		this.locales = locales;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

}