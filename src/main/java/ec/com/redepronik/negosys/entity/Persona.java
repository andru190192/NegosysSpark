package ec.com.redepronik.negosys.entity;

import java.io.Serializable;
import java.util.List;

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

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "personas")
public class Persona implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Boolean activo;
	private Boolean visible;
	private String razonSocial;
	private String numeroDocumento;
	private String direccion;
	private String referencia;
	private Ciudad ciudad;
	private String password;
	private String email;
	private String telefono;
	private List<Bitacora> bitacoras;
	private List<PermisoPersona> permisosPersonas;

	public Persona() {
	}

	public Persona(Integer id, Boolean activo, Boolean visible,
			String numeroDocumento, String direccion, String razonSocial,
			String referencia, Ciudad ciudad, String password, String email,
			String telefono, List<Bitacora> bitacoras,
			List<PermisoPersona> permisosPersonas) {
		this.id = id;
		this.activo = activo;
		this.visible = visible;
		this.numeroDocumento = numeroDocumento;
		this.direccion = direccion;
		this.razonSocial = razonSocial;
		this.referencia = referencia;
		this.ciudad = ciudad;
		this.password = password;
		this.email = email;
		this.telefono = telefono;
		this.bitacoras = bitacoras;
		this.permisosPersonas = permisosPersonas;
	}

	public Bitacora addBitacora(Bitacora bitacora) {
		getBitacoras().add(bitacora);
		bitacora.setPersona(this);

		return bitacora;
	}

	public PermisoPersona addPermisosPersonas(PermisoPersona permisosPersonas) {
		getPermisosPersonas().add(permisosPersonas);
		permisosPersonas.setPersona(this);

		return permisosPersonas;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
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

	@OneToMany(mappedBy = "persona")
	public List<Bitacora> getBitacoras() {
		return bitacoras;
	}

	@Column(name = "`numeroDocumento`", nullable = false, length = 13, unique = true)
	public String getNumeroDocumento() {
		return this.numeroDocumento;
	}

	@ManyToOne
	@JoinColumn(name = "ciudad", nullable = false)
	public Ciudad getCiudad() {
		return this.ciudad;
	}

	@Column(length = 100)
	public String getDireccion() {
		return this.direccion;
	}

	public String getEmail() {
		return email;
	}

	@Id
	@SequenceGenerator(allocationSize = 1, name = "PERSONAS_ID_GENERATOR", sequenceName = "PERSONAS_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSONAS_ID_GENERATOR")
	@Column(unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	@OneToMany(mappedBy = "persona")
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	// @LazyCollection(LazyCollectionOption.EXTRA)
	// @Fetch(FetchMode.JOIN)
	public List<PermisoPersona> getPermisosPersonas() {
		return permisosPersonas;
	}

	@Column(name = "`razonSocial`", nullable = false, length = 50)
	public String getRazonSocial() {
		return this.razonSocial;
	}

	@Column(length = 100)
	public String getReferencia() {
		return this.referencia;
	}

	public String getTelefono() {
		return telefono;
	}

	@Column(nullable = false)
	public Boolean getVisible() {
		return visible;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public Bitacora removeBitacora(Bitacora bitacora) {
		getBitacoras().remove(bitacora);
		bitacora.setPersona(null);

		return bitacora;
	}

	public PermisoPersona removePermisosPersonas(PermisoPersona permisosPersonas) {
		getPermisosPersonas().remove(permisosPersonas);
		permisosPersonas.setPersona(null);

		return permisosPersonas;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public void setBitacoras(List<Bitacora> bitacoras) {
		this.bitacoras = bitacoras;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPermisosPersonas(List<PermisoPersona> permisosPersonas) {
		this.permisosPersonas = permisosPersonas;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

}