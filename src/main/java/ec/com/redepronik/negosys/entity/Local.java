package ec.com.redepronik.negosys.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "locales")
public class Local implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Boolean activo;
	private String ciudad;
	private String nombre;
	private String direccion;
	private String codigoEstablecimiento;
	private Boolean matriz;
	private String web;
	private Integer puntoEmisionFactura;
	private Integer secuenciaFactura;
	private Integer puntoEmisionNotaCredito;
	private Integer secuenciaNotaCredito;
	private Integer puntoEmisionNotaDebito;
	private Integer secuenciaNotaDebito;
	private Integer puntoEmisionGuiaRemision;
	private Integer secuenciaGuiaRemision;
	private Integer puntoEmisionRetencion;
	private Integer secuenciaRetencion;
	private Integer puntoEmisionTraspaso;
	private Integer secuenciaTraspaso;
	private Integer puntoEmisionBajaInventario;
	private Integer secuenciaBajaInventario;
	private Integer puntoEmisionCotizacion;
	private Integer secuenciaCotizacion;
	private List<LocalBodeguero> localesBodegueros;
	private List<DetalleIngreso> detallesIngresos;
	private List<Kardex> kardexs;
	private List<Traspaso> traspasos1;
	private List<Traspaso> traspasos2;
	private List<LocalCajero> localesCajeros;

	public Local() {
	}

	public DetalleIngreso addDetallesIngresos(DetalleIngreso detalleIngreso) {
		getDetallesIngresos().add(detalleIngreso);
		detalleIngreso.setLocal(this);
		return detalleIngreso;
	}

	public Kardex addKardex(Kardex kardex) {
		getKardexs().add(kardex);
		kardex.setLocal(this);
		return kardex;
	}

	public LocalCajero addLocalCajero(LocalCajero localCajero) {
		getLocalesCajeros().add(localCajero);
		localCajero.setLocal(this);
		return localCajero;
	}

	public LocalBodeguero addLocalesBodegeros(LocalBodeguero localBodegero) {
		getLocalesBodegueros().add(localBodegero);
		localBodegero.setLocal(this);
		return localBodegero;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Local other = (Local) obj;
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

	@Column(nullable = false, length = 20)
	public String getCiudad() {
		return this.ciudad;
	}

	@Column(name = "`codigoEstablecimiento`", nullable = false, length = 3)
	public String getCodigoEstablecimiento() {
		return codigoEstablecimiento;
	}

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "local")
	public List<DetalleIngreso> getDetallesIngresos() {
		return detallesIngresos;
	}

	@Column(nullable = false, length = 50)
	public String getDireccion() {
		return this.direccion;
	}

	@Id
	@SequenceGenerator(allocationSize = 1, name = "LOCALES_ID_GENERATOR", sequenceName = "LOCALES_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOCALES_ID_GENERATOR")
	@Column(unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "local")
	public List<Kardex> getKardexs() {
		return kardexs;
	}

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "local")
	public List<LocalBodeguero> getLocalesBodegueros() {
		return localesBodegueros;
	}

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "local")
	public List<LocalCajero> getLocalesCajeros() {
		return localesCajeros;
	}

	@Column(nullable = false)
	public Boolean getMatriz() {
		return matriz;
	}

	@Column(nullable = false, length = 30)
	public String getNombre() {
		return nombre;
	}

	@Column(name = "`puntoEmisionBajaInventario`", nullable = false)
	public Integer getPuntoEmisionBajaInventario() {
		return puntoEmisionBajaInventario;
	}

	@Column(name = "`puntoEmisionCotizacion`", nullable = false)
	public Integer getPuntoEmisionCotizacion() {
		return puntoEmisionCotizacion;
	}

	@Column(name = "`puntoEmisionFactura`", nullable = false)
	public Integer getPuntoEmisionFactura() {
		return puntoEmisionFactura;
	}

	@Column(name = "`puntoEmisionGuiaRemision`", nullable = false)
	public Integer getPuntoEmisionGuiaRemision() {
		return puntoEmisionGuiaRemision;
	}

	@Column(name = "`puntoEmisionNotaCredito`", nullable = false)
	public Integer getPuntoEmisionNotaCredito() {
		return puntoEmisionNotaCredito;
	}

	@Column(name = "`puntoEmisionNotaDebito`", nullable = false)
	public Integer getPuntoEmisionNotaDebito() {
		return puntoEmisionNotaDebito;
	}

	@Column(name = "`puntoEmisionRetencion`", nullable = false)
	public Integer getPuntoEmisionRetencion() {
		return puntoEmisionRetencion;
	}

	@Column(name = "`puntoEmisionTraspaso`", nullable = false)
	public Integer getPuntoEmisionTraspaso() {
		return puntoEmisionTraspaso;
	}

	@Column(name = "`secuenciaBajaInventario`", nullable = false)
	public Integer getSecuenciaBajaInventario() {
		return secuenciaBajaInventario;
	}

	@Column(name = "`secuenciaCotizacion`", nullable = false)
	public Integer getSecuenciaCotizacion() {
		return secuenciaCotizacion;
	}

	@Column(name = "`secuenciaFactura`", nullable = false)
	public Integer getSecuenciaFactura() {
		return secuenciaFactura;
	}

	@Column(name = "`secuenciaGuiaRemision`", nullable = false)
	public Integer getSecuenciaGuiaRemision() {
		return secuenciaGuiaRemision;
	}

	@Column(name = "`secuenciaNotaCredito`", nullable = false)
	public Integer getSecuenciaNotaCredito() {
		return secuenciaNotaCredito;
	}

	@Column(name = "`secuenciaNotaDebito`", nullable = false)
	public Integer getSecuenciaNotaDebito() {
		return secuenciaNotaDebito;
	}

	@Column(name = "`secuenciaRetencion`", nullable = false)
	public Integer getSecuenciaRetencion() {
		return secuenciaRetencion;
	}

	@Column(name = "`secuenciaTraspaso`", nullable = false)
	public Integer getSecuenciaTraspaso() {
		return secuenciaTraspaso;
	}

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "localOrigen")
	public List<Traspaso> getTraspasos1() {
		return traspasos1;
	}

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "localDestino")
	public List<Traspaso> getTraspasos2() {
		return traspasos2;
	}

	@Column(length = 50)
	public String getWeb() {
		return web;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public DetalleIngreso removeDetallesIngresos(DetalleIngreso detalleIngreso) {
		getDetallesIngresos().remove(detalleIngreso);
		detalleIngreso.setLocal(null);
		return detalleIngreso;
	}

	public Kardex removeKardex(Kardex kardex) {
		getKardexs().remove(kardex);
		kardex.setLocal(null);
		return kardex;
	}

	public LocalCajero removeLocalCajero(LocalCajero localCajero) {
		getLocalesCajeros().remove(localCajero);
		localCajero.setLocal(null);
		return localCajero;
	}

	public LocalBodeguero removeLocalesBodegueros(LocalBodeguero localBodeguero) {
		getLocalesBodegueros().remove(localBodeguero);
		localBodeguero.setLocal(null);
		return localBodeguero;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public void setCodigoEstablecimiento(String codigoEstablecimiento) {
		this.codigoEstablecimiento = codigoEstablecimiento;
	}

	public void setDetallesIngresos(List<DetalleIngreso> detallesIngresos) {
		this.detallesIngresos = detallesIngresos;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setKardexs(List<Kardex> kardexs) {
		this.kardexs = kardexs;
	}

	public void setLocalesBodegueros(List<LocalBodeguero> localesBodegueros) {
		this.localesBodegueros = localesBodegueros;
	}

	public void setLocalesCajeros(List<LocalCajero> localesCajeros) {
		this.localesCajeros = localesCajeros;
	}

	public void setMatriz(Boolean matriz) {
		this.matriz = matriz;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPuntoEmisionBajaInventario(Integer puntoEmisionBajaInventario) {
		this.puntoEmisionBajaInventario = puntoEmisionBajaInventario;
	}

	public void setPuntoEmisionCotizacion(Integer puntoEmisionCotizacion) {
		this.puntoEmisionCotizacion = puntoEmisionCotizacion;
	}

	public void setPuntoEmisionFactura(Integer puntoEmisionFactura) {
		this.puntoEmisionFactura = puntoEmisionFactura;
	}

	public void setPuntoEmisionGuiaRemision(Integer puntoEmisionGuiaRemision) {
		this.puntoEmisionGuiaRemision = puntoEmisionGuiaRemision;
	}

	public void setPuntoEmisionNotaCredito(Integer puntoEmisionNotaCredito) {
		this.puntoEmisionNotaCredito = puntoEmisionNotaCredito;
	}

	public void setPuntoEmisionNotaDebito(Integer puntoEmisionNotaDebito) {
		this.puntoEmisionNotaDebito = puntoEmisionNotaDebito;
	}

	public void setPuntoEmisionRetencion(Integer puntoEmisionRetencion) {
		this.puntoEmisionRetencion = puntoEmisionRetencion;
	}

	public void setPuntoEmisionTraspaso(Integer puntoEmisionTraspaso) {
		this.puntoEmisionTraspaso = puntoEmisionTraspaso;
	}

	public void setSecuenciaBajaInventario(Integer secuenciaBajaInventario) {
		this.secuenciaBajaInventario = secuenciaBajaInventario;
	}

	public void setSecuenciaCotizacion(Integer secuenciaCotizacion) {
		this.secuenciaCotizacion = secuenciaCotizacion;
	}

	public void setSecuenciaFactura(Integer secuenciaFactura) {
		this.secuenciaFactura = secuenciaFactura;
	}

	public void setSecuenciaGuiaRemision(Integer secuenciaGuiaRemision) {
		this.secuenciaGuiaRemision = secuenciaGuiaRemision;
	}

	public void setSecuenciaNotaCredito(Integer secuenciaNotaCredito) {
		this.secuenciaNotaCredito = secuenciaNotaCredito;
	}

	public void setSecuenciaNotaDebito(Integer secuenciaNotaDebito) {
		this.secuenciaNotaDebito = secuenciaNotaDebito;
	}

	public void setSecuenciaRetencion(Integer secuenciaRetencion) {
		this.secuenciaRetencion = secuenciaRetencion;
	}

	public void setSecuenciaTraspaso(Integer secuenciaTraspaso) {
		this.secuenciaTraspaso = secuenciaTraspaso;
	}

	public void setTraspasos1(List<Traspaso> traspasos1) {
		this.traspasos1 = traspasos1;
	}

	public void setTraspasos2(List<Traspaso> traspasos2) {
		this.traspasos2 = traspasos2;
	}

	public void setWeb(String web) {
		this.web = web;
	}

}