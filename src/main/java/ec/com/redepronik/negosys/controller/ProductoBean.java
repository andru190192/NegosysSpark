package ec.com.redepronik.negosys.controller;

import static ec.com.redepronik.negosys.utils.UtilsAplicacion.presentaMensaje;
import static ec.com.redepronik.negosys.utils.UtilsAplicacion.redireccionar;
import static ec.com.redepronik.negosys.utils.UtilsArchivos.getRutaImagenProducto;
import static ec.com.redepronik.negosys.utils.UtilsArchivos.guardarImagen;
import static ec.com.redepronik.negosys.utils.UtilsArchivos.leerImagen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ec.com.redepronik.negosys.entity.Grupo;
import ec.com.redepronik.negosys.entity.Impuesto;
import ec.com.redepronik.negosys.entity.Producto;
import ec.com.redepronik.negosys.entity.Tarifa;
import ec.com.redepronik.negosys.entity.TipoProducto;
import ec.com.redepronik.negosys.entity.Unidad;
import ec.com.redepronik.negosys.entityAux.CodigoProductoReporte;
import ec.com.redepronik.negosys.service.GrupoService;
import ec.com.redepronik.negosys.service.ProductoService;
import ec.com.redepronik.negosys.utils.UtilsAplicacion;
import ec.com.redepronik.negosys.utils.service.ReporteService;

@Controller
@Scope("session")
public class ProductoBean {

	@Autowired
	private ProductoService productoService;

	@Autowired
	private GrupoService grupoService;

	@Autowired
	private ReporteService reporteService;

	private String criterioBusqueda;
	private Producto producto;
	private StreamedContent codigoBarra;

	// private StreamedContent logo;
	// private InputStream is;

	private List<Producto> productos;
	private List<Grupo> grupos;

	public ProductoBean() {
	}

	public void actualizar(ActionEvent actionEvent) {
		productoService.actualizar(producto);
		productos = new ArrayList<Producto>();
		productos.add(productoService.obtenerObjeto(
				"select p where p.id=?1",
				new Object[] { producto.getId() }, false, new Object[] {}));
		redireccionar("producto.jsf");
	}

	public void cargarActualizar() {
		// listaTpp = new ArrayList<TablaPrecios>();
		// for (Precio precio : producto.getPrecios()) {
		// TablaPrecios tablaPrecios = new TablaPrecios();
		// tablaPrecios.setPrecioProducto(precio);
		// listaTpp.add(tablaPrecios);
		// }
		// listaTpp = productoService.calcularPrecios(producto, listaTpp);

		// readOnlyPrecio = false;
		// for (Local b : localService.obtener(true)) {
		// if (kardexService.obtenerSaldoActual(producto.getEan(), b.getId()) !=
		// null) {
		// readOnlyPrecio = true;
		// break;
		// }
		// }

		UtilsAplicacion.redireccionar("productoActualizar.jsf");
	}

	public void cargarInsertar() {
		producto = new Producto();
		UtilsAplicacion.redireccionar("productoInsertar.jsf");
	}

	public void generarCodigo() {
		// HashMap<String, Object> parametro = productoService
		// .generarCodigo(producto);
		// codigoBarra = (StreamedContent) parametro.get("imgCodigo");
		// codigo = (String) parametro.get("codigo");
	}

	public StreamedContent getCodigoBarra() {
		return codigoBarra;
	}

	public String getCriterioBusqueda() {
		return criterioBusqueda;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public Tarifa[] getListaTarifas() {
		return Tarifa.values();
	}

	// public StreamedContent getLogo() {
	// is = leerImagen(getRutaImagenProducto() + producto.getEan() + ".png");
	// if (is != null)
	// setLogo(new DefaultStreamedContent(is, "image/png"));
	// return logo;
	// }

	public Producto getProducto() {
		return producto;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public Tarifa[] getTarifasIce() {
		return Tarifa.obtener(Impuesto.IC);
	}

	public Tarifa[] getTarifasIrp() {
		return Tarifa.obtener(Impuesto.IR);
	}

	public Tarifa[] getTarifasIva() {
		return Tarifa.obtener(Impuesto.IV);
	}

	public TipoProducto[] getTiposProductos() {
		return TipoProducto.values();
	}

	public Unidad[] getUnidades() {
		return Unidad.values();
	}

	@PostConstruct
	private void init() {
		limpiarObjetos();
		grupos = grupoService.obtenerLista(
				"select g from Grupo g order by g.nombre", new Object[] {},
				false, new Object[] {});
	}

	public void insertar(ActionEvent actionEvent) {
		productoService.insertar(producto);
		if (producto.getId() != null) {
			productos = new ArrayList<Producto>();
			productos.add(productoService.obtenerObjeto(
					"select p from Producto p where p.id=?1",
					new Object[] { producto.getId() }, false, new Object[] {}));
			redireccionar("producto.jsf");
		}
		// productoService.insertar(producto, listaTpp);
		// productoService
		// .generarInventarioInicial(producto, listaBodegaProductos);
	}

	public void limpiarObjetos() {
		producto = new Producto();
		producto.setGrupo(new Grupo());
		producto.setTipoProducto(TipoProducto.BN);
	}

	public void obtener() {
		productos = productoService
				.obtenerLista(
						"select p from Producto p where p.nombre like ?1 order by p.nombre",
						new Object[] { "%" + criterioBusqueda + "%" }, true,
						new Object[] {});
	}

	public void obtenerProducto(Producto producto) {
		// this.producto =
		// productoService.obtenerPorProductoId(producto.getId());
		generarCodigo();
	}

	public void regresar() {
		UtilsAplicacion.redireccionar("producto.jsf");
	}

	public void reporteCodigoProductoPersonalizado() {
		if (producto == null || producto.getId() == null
				|| producto.getId() == 0)
			presentaMensaje(FacesMessage.SEVERITY_ERROR, "ESCOJA UN PRODUCTO");
		else {
			// if (!comprobarEan13(codigo))
			// presentaMensaje(FacesMessage.SEVERITY_ERROR,
			// "EL PRODUCTO NO TIENE UN EAN13 VALIDO");
			// else {
			List<CodigoProductoReporte> lista = new ArrayList<CodigoProductoReporte>();
			// if (bnPrecio)
			// lista.add(new CodigoProductoReporte(codigo, "$ "
			// + String.valueOf(redondearTotales(productoService
			// .obtenerPvp(producto.getPrecio(),
			// producto.getPrecios()))), producto
			// .getNombre(), parametro.getRazonSocial(), matriz
			// .getWeb()));
			// else
			// lista.add(new CodigoProductoReporte(codigo, null, producto
			// .getNombre(), parametro.getRazonSocial(), matriz
			// .getWeb()));
			Map<String, Object> parametros = new HashMap<String, Object>();
			reporteService.generarReportePDFSencillo(lista, parametros,
					"CodigoBarra");
		}
		// }
	}

	public void reporteCodigoProductoSimple() {
		// if (producto == null || producto.getId() == null
		// || producto.getId() == 0)
		// presentaMensaje(FacesMessage.SEVERITY_ERROR, "ESCOJA UN PRODUCTO");
		// else {
		// if (!comprobarEan13(codigo))
		// presentaMensaje(FacesMessage.SEVERITY_ERROR,
		// "EL PRODUCTO NO TIENE UN EAN13 VALIDO");
		// else {
		// List<CodigoProductoReporte> lista = new
		// ArrayList<CodigoProductoReporte>();
		// if (bnPrecio)
		// lista.add(new CodigoProductoReporte(codigo, "$ "
		// + String.valueOf(redondearTotales(productoService
		// .obtenerPvp(producto.getPrecio(),
		// producto.getPrecios()))), producto
		// .getNombre(), parametro.getRazonSocial(), matriz
		// .getWeb()));
		// else
		// lista.add(new CodigoProductoReporte(codigo, null, producto
		// .getNombre(), parametro.getRazonSocial(), matriz
		// .getWeb()));
		// Map<String, Object> parametros = new HashMap<String,
		// Object>();
		// reporteService.generarReportePDFSencillo(lista, parametros,
		// "CodigoBarraSimple");
		// }
		// }
	}

	public void setCodigoBarra(StreamedContent codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public void setCriterioBusqueda(String criterioBusqueda) {
		this.criterioBusqueda = criterioBusqueda;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	// public void setTipoPrecioProducto(TipoPrecioProducto tipoPrecioProducto)
	// {
	// this.tipoPrecioProducto = tipoPrecioProducto;
	// }

	public void subirImagen(FileUploadEvent event) {
		try {
			guardarImagen(getRutaImagenProducto(),
					leerImagen(event.getFile().getInputstream(), null),
					producto.getCodigo(), 300, 300);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
