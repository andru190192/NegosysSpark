package ec.com.redepronik.negosys.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "productos")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Boolean activo;
	private Boolean kardex;
	private Integer cantidadMinima;
	private String codigo;
	private Boolean importado;
	private Boolean facturacion;
	private String nombre;
	private BigDecimal precioNeto;
	private Tarifa iva;
	private Tarifa ice;
	private Tarifa irbpnr;
	private Boolean porcentajePrecio1;
	private BigDecimal precio1;
	private Boolean porcentajePrecio2;
	private BigDecimal precio2;
	private Boolean porcentajePrecio3;
	private BigDecimal precio3;
	private Boolean porcentajePrecio4;
	private BigDecimal precio4;
	private Unidad unidad1;
	private Integer cantidadUnidad1;
	private Unidad unidad2;
	private Integer cantidadUnidad2;
	private Unidad unidad3;
	private Integer cantidadUnidad3;
	private Unidad unidad4;
	private Integer cantidadUnidad4;
	private List<DetalleFactura> detallesFacturas;
	private List<DetalleCotizacion> detallesCotizaciones;
	private List<DetalleBajaInventario> detallesBajasInventarios;
	private List<DetalleIngreso> detallesIngresos;
	private List<DetalleTraspaso> detallesTraspasos;
	private List<DetalleNotaCredito> detallesNotasCreditos;
	private List<Kardex> kardexs;
	private Grupo grupo;
	private TipoProducto tipoProducto;

	public Producto() {
		cantidadMinima = null;
		precioNeto = null;
		grupo = new Grupo();
		tipoProducto = TipoProducto.BN;
		kardex = true;
	}

	public Producto(Long id, Boolean activo, Boolean kardex,
			Integer cantidadMinima, String codigo, Boolean importado,
			Boolean facturacion, String nombre, BigDecimal precioNeto,
			List<DetalleFactura> detallesFacturas,
			List<DetalleCotizacion> detallesCotizaciones,
			List<DetalleBajaInventario> detallesBajasInventarios,
			List<DetalleIngreso> detallesIngresos,
			List<DetalleTraspaso> detallesTraspasos,
			List<DetalleNotaCredito> detallesNotasCreditos,
			List<Kardex> kardexs, Grupo grupo, TipoProducto tipoProducto) {
		this.id = id;
		this.activo = activo;
		this.kardex = kardex;
		this.cantidadMinima = cantidadMinima;
		this.codigo = codigo;
		this.importado = importado;
		this.facturacion = facturacion;
		this.nombre = nombre;
		this.precioNeto = precioNeto;
		this.detallesFacturas = detallesFacturas;
		this.detallesCotizaciones = detallesCotizaciones;
		this.detallesBajasInventarios = detallesBajasInventarios;
		this.detallesIngresos = detallesIngresos;
		this.detallesTraspasos = detallesTraspasos;
		this.detallesNotasCreditos = detallesNotasCreditos;
		this.kardexs = kardexs;
		this.grupo = grupo;
		this.tipoProducto = tipoProducto;
	}

	public DetalleBajaInventario addDetalleBajaInventario(
			DetalleBajaInventario detalleBajaInventario) {
		getDetallesBajasInventarios().add(detalleBajaInventario);
		detalleBajaInventario.setProducto(this);

		return detalleBajaInventario;
	}

	public DetalleCotizacion addDetalleCotizacion(
			DetalleCotizacion detalleCotizacion) {
		getDetallesCotizaciones().add(detalleCotizacion);
		detalleCotizacion.setProducto(this);

		return detalleCotizacion;
	}

	public DetalleFactura addDetalleFactura(DetalleFactura detalleFactura) {
		getDetallesFacturas().add(detalleFactura);
		detalleFactura.setProducto(this);

		return detalleFactura;
	}

	public DetalleIngreso addDetalleIngreso(DetalleIngreso detalleIngreso) {
		getDetallesIngresos().add(detalleIngreso);
		detalleIngreso.setProducto(this);

		return detalleIngreso;
	}

	public DetalleNotaCredito addDetalleNotaCredito(
			DetalleNotaCredito detalleNotaCredito) {
		getDetallesNotasCreditos().add(detalleNotaCredito);
		detalleNotaCredito.setProducto(this);

		return detalleNotaCredito;
	}

	public DetalleTraspaso addDetalleTraspaso(DetalleTraspaso detalleTraspaso) {
		getDetallesTraspasos().add(detalleTraspaso);
		detalleTraspaso.setProducto(this);

		return detalleTraspaso;
	}

	public Kardex addKardex(Kardex kardex) {
		getKardexs().add(kardex);
		kardex.setProducto(this);

		return kardex;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
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

	@Column(name = "`cantidadMinima`", nullable = false)
	public Integer getCantidadMinima() {
		return cantidadMinima;
	}

	@Column(name = "`cantidadUnidad1`")
	public Integer getCantidadUnidad1() {
		return cantidadUnidad1;
	}

	@Column(name = "`cantidadUnidad2`")
	public Integer getCantidadUnidad2() {
		return cantidadUnidad2;
	}

	@Column(name = "`cantidadUnidad3`")
	public Integer getCantidadUnidad3() {
		return cantidadUnidad3;
	}

	@Column(name = "`cantidadUnidad4`")
	public Integer getCantidadUnidad4() {
		return cantidadUnidad4;
	}

	@Column(nullable = false, length = 25)
	public String getCodigo() {
		return this.codigo;
	}

	@OneToMany(mappedBy = "producto")
	public List<DetalleBajaInventario> getDetallesBajasInventarios() {
		return detallesBajasInventarios;
	}

	@OneToMany(mappedBy = "producto")
	public List<DetalleCotizacion> getDetallesCotizaciones() {
		return detallesCotizaciones;
	}

	@OneToMany(mappedBy = "producto")
	public List<DetalleFactura> getDetallesFacturas() {
		return detallesFacturas;
	}

	@OneToMany(mappedBy = "producto")
	public List<DetalleIngreso> getDetallesIngresos() {
		return detallesIngresos;
	}

	@OneToMany(mappedBy = "producto")
	public List<DetalleNotaCredito> getDetallesNotasCreditos() {
		return detallesNotasCreditos;
	}

	@OneToMany(mappedBy = "producto")
	public List<DetalleTraspaso> getDetallesTraspasos() {
		return detallesTraspasos;
	}

	@Column(nullable = false)
	public Boolean getFacturacion() {
		return facturacion;
	}

	@ManyToOne
	@JoinColumn(name = "grupo", nullable = false)
	public Grupo getGrupo() {
		return this.grupo;
	}

	@Enumerated(EnumType.STRING)
	public Tarifa getIce() {
		return ice;
	}

	@Id
	@SequenceGenerator(allocationSize = 1, name = "PRODUCTOS_ID_GENERATOR", sequenceName = "PRODUCTOS_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCTOS_ID_GENERATOR")
	@Column(unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	@Column(nullable = false)
	public Boolean getImportado() {
		return importado;
	}

	@Enumerated(EnumType.STRING)
	public Tarifa getIrbpnr() {
		return irbpnr;
	}

	@Enumerated(EnumType.STRING)
	public Tarifa getIva() {
		return iva;
	}

	@Column(nullable = false)
	public Boolean getKardex() {
		return kardex;
	}

	@OneToMany(mappedBy = "producto")
	public List<Kardex> getKardexs() {
		return this.kardexs;
	}

	@Column(nullable = false)
	@Type(type = "text")
	public String getNombre() {
		return nombre;
	}

	@Column(name = "`porcentajePrecio1`")
	public Boolean getPorcentajePrecio1() {
		return porcentajePrecio1;
	}

	@Column(name = "`porcentajePrecio2`")
	public Boolean getPorcentajePrecio2() {
		return porcentajePrecio2;
	}

	@Column(name = "`porcentajePrecio3`")
	public Boolean getPorcentajePrecio3() {
		return porcentajePrecio3;
	}

	@Column(name = "`porcentajePrecio4`")
	public Boolean getPorcentajePrecio4() {
		return porcentajePrecio4;
	}

	@Column(name = "`precioNeto`", nullable = false, precision = 12, scale = 6)
	public BigDecimal getPrecioNeto() {
		return this.precioNeto;
	}

	@Column(precision = 12, scale = 6)
	public BigDecimal getPrecio1() {
		return precio1;
	}

	@Column(precision = 12, scale = 6)
	public BigDecimal getPrecio2() {
		return precio2;
	}

	@Column(precision = 12, scale = 6)
	public BigDecimal getPrecio3() {
		return precio3;
	}

	@Column(precision = 12, scale = 6)
	public BigDecimal getPrecio4() {
		return precio4;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "`tipoProducto`", nullable = false)
	public TipoProducto getTipoProducto() {
		return tipoProducto;
	}

	@Enumerated(EnumType.STRING)
	public Unidad getUnidad1() {
		return unidad1;
	}

	@Enumerated(EnumType.STRING)
	public Unidad getUnidad2() {
		return unidad2;
	}

	@Enumerated(EnumType.STRING)
	public Unidad getUnidad3() {
		return unidad3;
	}

	@Enumerated(EnumType.STRING)
	public Unidad getUnidad4() {
		return unidad4;
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
		detalleBajaInventario.setProducto(null);

		return detalleBajaInventario;
	}

	public DetalleCotizacion removeDetalleCotizacion(
			DetalleCotizacion detalleCotizacion) {
		getDetallesCotizaciones().remove(detalleCotizacion);
		detalleCotizacion.setProducto(null);

		return detalleCotizacion;
	}

	public DetalleFactura removeDetalleFactura(DetalleFactura detalleFactura) {
		getDetallesFacturas().remove(detalleFactura);
		detalleFactura.setProducto(null);

		return detalleFactura;
	}

	public DetalleIngreso removeDetalleIngreso(DetalleIngreso detalleIngreso) {
		getDetallesIngresos().remove(detalleIngreso);
		detalleIngreso.setProducto(null);

		return detalleIngreso;
	}

	public DetalleNotaCredito removeDetalleNotaCredito(
			DetalleNotaCredito detalleNotaCredito) {
		getDetallesNotasCreditos().remove(detalleNotaCredito);
		detalleNotaCredito.setProducto(null);

		return detalleNotaCredito;
	}

	public DetalleTraspaso removeDetalleTraspaso(DetalleTraspaso detalleTraspaso) {
		getDetallesTraspasos().remove(detalleTraspaso);
		detalleTraspaso.setProducto(null);

		return detalleTraspaso;
	}

	public Kardex removeKardex(Kardex kardex) {
		getKardexs().remove(kardex);
		kardex.setProducto(null);

		return kardex;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public void setCantidadMinima(Integer cantidadMinima) {
		this.cantidadMinima = cantidadMinima;
	}

	public void setCantidadUnidad1(Integer cantidadUnidad1) {
		this.cantidadUnidad1 = cantidadUnidad1;
	}

	public void setCantidadUnidad2(Integer cantidadUnidad2) {
		this.cantidadUnidad2 = cantidadUnidad2;
	}

	public void setCantidadUnidad3(Integer cantidadUnidad3) {
		this.cantidadUnidad3 = cantidadUnidad3;
	}

	public void setCantidadUnidad4(Integer cantidadUnidad4) {
		this.cantidadUnidad4 = cantidadUnidad4;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setDetalleBajaInventario(
			List<DetalleBajaInventario> detallesBajasInventarios) {
		this.detallesBajasInventarios = detallesBajasInventarios;
	}

	public void setDetalleCotizacion(
			List<DetalleCotizacion> detallesCotizaciones) {
		this.detallesCotizaciones = detallesCotizaciones;
	}

	public void setDetalleFactura(List<DetalleFactura> detallesFacturas) {
		this.detallesFacturas = detallesFacturas;
	}

	public void setDetalleIngresos(List<DetalleIngreso> detallesIngresos) {
		this.detallesIngresos = detallesIngresos;
	}

	public void setDetallesBajasInventarios(
			List<DetalleBajaInventario> detallesBajasInventarios) {
		this.detallesBajasInventarios = detallesBajasInventarios;
	}

	public void setDetallesCotizaciones(
			List<DetalleCotizacion> detallesCotizaciones) {
		this.detallesCotizaciones = detallesCotizaciones;
	}

	public void setDetallesFacturas(List<DetalleFactura> detallesFacturas) {
		this.detallesFacturas = detallesFacturas;
	}

	public void setDetallesIngresos(List<DetalleIngreso> detallesIngresos) {
		this.detallesIngresos = detallesIngresos;
	}

	public void setDetallesNotasCreditos(
			List<DetalleNotaCredito> detallesNotasCreditos) {
		this.detallesNotasCreditos = detallesNotasCreditos;
	}

	public void setDetallesTraspasos(List<DetalleTraspaso> detallesTraspasos) {
		this.detallesTraspasos = detallesTraspasos;
	}

	public void setDetalleTraspasos(List<DetalleTraspaso> detallesTraspasos) {
		this.detallesTraspasos = detallesTraspasos;
	}

	public void setFacturacion(Boolean facturacion) {
		this.facturacion = facturacion;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public void setIce(Tarifa ice) {
		this.ice = ice;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setImportado(Boolean importado) {
		this.importado = importado;
	}

	public void setIrbpnr(Tarifa irbpnr) {
		this.irbpnr = irbpnr;
	}

	public void setIva(Tarifa iva) {
		this.iva = iva;
	}

	public void setKardex(Boolean kardex) {
		this.kardex = kardex;
	}

	public void setKardexs(List<Kardex> kardexs) {
		this.kardexs = kardexs;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNombreImprimir(String nombre) {
		this.nombre = nombre;
	}

	public void setPorcentajePrecio1(Boolean porcentajePrecio1) {
		this.porcentajePrecio1 = porcentajePrecio1;
	}

	public void setPorcentajePrecio2(Boolean porcentajePrecio2) {
		this.porcentajePrecio2 = porcentajePrecio2;
	}

	public void setPorcentajePrecio3(Boolean porcentajePrecio3) {
		this.porcentajePrecio3 = porcentajePrecio3;
	}

	public void setPorcentajePrecio4(Boolean porcentajePrecio4) {
		this.porcentajePrecio4 = porcentajePrecio4;
	}

	public void setPrecioNeto(BigDecimal precioNeto) {
		this.precioNeto = precioNeto;
	}

	public void setPrecio1(BigDecimal precio1) {
		this.precio1 = precio1;
	}

	public void setPrecio2(BigDecimal precio2) {
		this.precio2 = precio2;
	}

	public void setPrecio3(BigDecimal precio3) {
		this.precio3 = precio3;
	}

	public void setPrecio4(BigDecimal precio4) {
		this.precio4 = precio4;
	}

	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public void setUnidad1(Unidad unidad1) {
		this.unidad1 = unidad1;
	}

	public void setUnidad2(Unidad unidad2) {
		this.unidad2 = unidad2;
	}

	public void setUnidad3(Unidad unidad3) {
		this.unidad3 = unidad3;
	}

	public void setUnidad4(Unidad unidad4) {
		this.unidad4 = unidad4;
	}

}