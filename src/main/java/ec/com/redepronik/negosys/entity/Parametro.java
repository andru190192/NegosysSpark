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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "parametros")
public class Parametro implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String ruc;
	private String razonSocial;
	private String direccion;
	private String codigoContribuyente;
	private Boolean contabilidad;
	private Short esperaAutorizacion;
	private Boolean tipoEmision;
	private Boolean tipoAmbiente;
	private String autorizacionSri;
	private Date fechaInicioAutorizacion;
	private Date fechaFinAutorizacion;
	private String cenResolucion;
	private Boolean facturacionElectronica;
	private String email;
	private TipoServidorEmail tipoServidorEmail;
	private String passEmail;
	private String passToken;
	private String userAwsS3;
	private String passAwsS3;
	private String bucketAwsS3;
	private Date fechaCorte;
	private BigDecimal interesCredito;
	private BigDecimal interesMora;

	public Parametro() {
	}

	public Parametro(Integer id, String ruc, String razonSocial,
			String direccion, String codigoContribuyente, Boolean contabilidad,
			Short esperaAutorizacion, Boolean tipoEmision,
			Boolean tipoAmbiente, String autorizacionSri,
			Date fechaInicioAutorizacion, Date fechaFinAutorizacion,
			String cenResolucion, Boolean facturacionElectronica, String email,
			TipoServidorEmail tipoServidorEmail, String passEmail,
			String passToken, String userAwsS3, String passAwsS3,
			String bucketAwsS3, Date fechaCorte, BigDecimal interesCredito,
			BigDecimal interesMora) {
		this.id = id;
		this.ruc = ruc;
		this.razonSocial = razonSocial;
		this.direccion = direccion;
		this.codigoContribuyente = codigoContribuyente;
		this.contabilidad = contabilidad;
		this.esperaAutorizacion = esperaAutorizacion;
		this.tipoEmision = tipoEmision;
		this.tipoAmbiente = tipoAmbiente;
		this.autorizacionSri = autorizacionSri;
		this.fechaInicioAutorizacion = fechaInicioAutorizacion;
		this.fechaFinAutorizacion = fechaFinAutorizacion;
		this.cenResolucion = cenResolucion;
		this.facturacionElectronica = facturacionElectronica;
		this.email = email;
		this.tipoServidorEmail = tipoServidorEmail;
		this.passEmail = passEmail;
		this.passToken = passToken;
		this.userAwsS3 = userAwsS3;
		this.passAwsS3 = passAwsS3;
		this.bucketAwsS3 = bucketAwsS3;
		this.fechaCorte = fechaCorte;
		this.interesCredito = interesCredito;
		this.interesMora = interesMora;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parametro other = (Parametro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Column(name = "`autorizacionSri`", length = 15)
	public String getAutorizacionSri() {
		return autorizacionSri;
	}

	@Column(name = "`bucketAwsS3`", length = 25)
	public String getBucketAwsS3() {
		return bucketAwsS3;
	}

	@Column(name = "`cenResolucion`", length = 15)
	public String getCenResolucion() {
		return cenResolucion;
	}

	@Column(name = "`codigoContribuyente`", length = 20)
	public String getCodigoContribuyente() {
		return codigoContribuyente;
	}

	@Column(nullable = false)
	public Boolean getContabilidad() {
		return this.contabilidad;
	}

	@Column(nullable = false, length = 100)
	public String getDireccion() {
		return this.direccion;
	}

	@Column(length = 50)
	public String getEmail() {
		return email;
	}

	@Column(name = "`esperaAutorizacion`", nullable = false)
	public Short getEsperaAutorizacion() {
		return esperaAutorizacion;
	}

	@Column(name = "`facturacionElectronica`", nullable = false)
	public Boolean getFacturacionElectronica() {
		return facturacionElectronica;
	}

	@Column(name = "`fechaCorte`")
	public Date getFechaCorte() {
		return fechaCorte;
	}

	@Column(name = "`fechaFinAutorizacion`")
	@Temporal(TemporalType.DATE)
	public Date getFechaFinAutorizacion() {
		return fechaFinAutorizacion;
	}

	@Column(name = "`fechaInicioAutorizacion`")
	@Temporal(TemporalType.DATE)
	public Date getFechaInicioAutorizacion() {
		return fechaInicioAutorizacion;
	}

	@Id
	@SequenceGenerator(allocationSize = 1, name = "PARAMETROS_ID_GENERATOR", sequenceName = "PARAMETROS_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARAMETROS_ID_GENERATOR")
	@Column(unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	@Column(name = "`interesCredito`", precision = 8, scale = 2)
	public BigDecimal getInteresCredito() {
		return interesCredito;
	}

	@Column(name = "`interesMora`", precision = 8, scale = 2)
	public BigDecimal getInteresMora() {
		return interesMora;
	}

	@Column(name = "`passAwsS3`", length = 50)
	public String getPassAwsS3() {
		return passAwsS3;
	}

	@Column(name = "`passEmail`", length = 15)
	public String getPassEmail() {
		return passEmail;
	}

	@Column(name = "`passToken`", length = 15)
	public String getPassToken() {
		return passToken;
	}

	@Column(name = "`razonSocial`", nullable = false, length = 50)
	public String getRazonSocial() {
		return razonSocial;
	}

	@Column(nullable = false, length = 13)
	public String getRuc() {
		return this.ruc;
	}

	@Column(name = "`tipoAmbiente`", nullable = false)
	public Boolean getTipoAmbiente() {
		return tipoAmbiente;
	}

	@Column(name = "`tipoEmision`", nullable = false)
	public Boolean getTipoEmision() {
		return tipoEmision;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "`tipoServidorEmail`")
	public TipoServidorEmail getTipoServidorEmail() {
		return tipoServidorEmail;
	}

	@Column(name = "`userAwsS3`", length = 30)
	public String getUserAwsS3() {
		return userAwsS3;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public void setAutorizacionSri(String autorizacionSri) {
		this.autorizacionSri = autorizacionSri;
	}

	public void setBucketAwsS3(String bucketAwsS3) {
		this.bucketAwsS3 = bucketAwsS3;
	}

	public void setCenResolucion(String cenResolucion) {
		this.cenResolucion = cenResolucion;
	}

	public void setCodigoContribuyente(String codigoContribuyente) {
		this.codigoContribuyente = codigoContribuyente;
	}

	public void setContabilidad(Boolean contabilidad) {
		this.contabilidad = contabilidad;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEsperaAutorizacion(Short esperaAutorizacion) {
		this.esperaAutorizacion = esperaAutorizacion;
	}

	public void setFacturacionElectronica(Boolean facturacionElectronica) {
		this.facturacionElectronica = facturacionElectronica;
	}

	public void setFechaCorte(Date fechaCorte) {
		this.fechaCorte = fechaCorte;
	}

	public void setFechaFinAutorizacion(Date fechaFinAutorizacion) {
		this.fechaFinAutorizacion = fechaFinAutorizacion;
	}

	public void setFechaInicioAutorizacion(Date fechaInicioAutorizacion) {
		this.fechaInicioAutorizacion = fechaInicioAutorizacion;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setInteresCredito(BigDecimal interesCredito) {
		this.interesCredito = interesCredito;
	}

	public void setInteresMora(BigDecimal interesMora) {
		this.interesMora = interesMora;
	}

	public void setPassAwsS3(String passAwsS3) {
		this.passAwsS3 = passAwsS3;
	}

	public void setPassEmail(String passEmail) {
		this.passEmail = passEmail;
	}

	public void setPassToken(String passToken) {
		this.passToken = passToken;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public void setTipoAmbiente(Boolean tipoAmbiente) {
		this.tipoAmbiente = tipoAmbiente;
	}

	public void setTipoEmision(Boolean tipoEmision) {
		this.tipoEmision = tipoEmision;
	}

	public void setTipoServidorEmail(TipoServidorEmail tipoServidorEmail) {
		this.tipoServidorEmail = tipoServidorEmail;
	}

	public void setUserAwsS3(String userAwsS3) {
		this.userAwsS3 = userAwsS3;
	}

}