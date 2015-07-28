package ec.com.redepronik.negosys.controller;

import static ec.com.redepronik.negosys.utils.UtilsAplicacion.redireccionar;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import ec.com.redepronik.negosys.entity.DetalleRetencion;
import ec.com.redepronik.negosys.entity.Retencion;
import ec.com.redepronik.negosys.entity.TipoComprobanteRetencion;
import ec.com.redepronik.negosys.service.DocumentosElectronicosService;
import ec.com.redepronik.negosys.service.LocalService;
import ec.com.redepronik.negosys.service.ProductoService;
import ec.com.redepronik.negosys.service.RetencionService;

@Controller
@Scope("session")
public class ListadoRetencionBean {

	@Autowired
	private RetencionService retencionService;

	@Autowired
	private ProductoService productoService;

	@Autowired
	private LocalService localService;

	@Autowired
	private DocumentosElectronicosService documentosElectronicosService;

	private List<Retencion> listaRetenciones;
	private String criterioBusquedaProveedor;
	private String criterioBusquedaNumeroComprobante;
	private String criterioBusquedaNumeroRetencion;
	private Date criterioBusquedaFechaIngreso;
	private Date criterioBusquedaFechaRetencion;
	private DetalleRetencion detalleRetencion;

	private Retencion retencion;

	public ListadoRetencionBean() {

	}

	public void generarXML() {
		if (SecurityContextHolder.getContext().getAuthentication().getName()
				.compareTo("0123456789") == 0) {
			documentosElectronicosService
					.generarRetencionXML(retencion.getId());
		}
	}

	public Date getCriterioBusquedaFechaIngreso() {
		return criterioBusquedaFechaIngreso;
	}

	public Date getCriterioBusquedaFechaRetencion() {
		return criterioBusquedaFechaRetencion;
	}

	public String getCriterioBusquedaNumeroComprobante() {
		return criterioBusquedaNumeroComprobante;
	}

	public String getCriterioBusquedaNumeroRetencion() {
		return criterioBusquedaNumeroRetencion;
	}

	public String getCriterioBusquedaProveedor() {
		return criterioBusquedaProveedor;
	}

	public DetalleRetencion getDetalleRetencion() {
		return detalleRetencion;
	}

	public TipoComprobanteRetencion[] getListaComprobantes() {
		return TipoComprobanteRetencion.values();
	}

	public List<Retencion> getListaRetenciones() {
		return listaRetenciones;
	}

	public LocalService getLocalService() {
		return localService;
	}

	public ProductoService getProductoService() {
		return productoService;
	}

	public Retencion getRetencion() {
		return retencion;
	}

	public RetencionService getRetencionService() {
		return retencionService;
	}

	@PostConstruct
	public void init() {
		retencion = new Retencion();
		detalleRetencion = new DetalleRetencion();

		// for (Retencion retencion : retencionService.obtener())
		// documentosElectronicosService
		// .generarRetencionXML(retencion.getId());
	}

	public void limpiarObjetos() {
		criterioBusquedaProveedor = new String();
		criterioBusquedaNumeroComprobante = new String();
		criterioBusquedaFechaIngreso = new Date();
	}

	public void obtener() {
		listaRetenciones = retencionService
				.obtener(criterioBusquedaProveedor,
						criterioBusquedaNumeroComprobante,
						criterioBusquedaFechaIngreso,criterioBusquedaNumeroRetencion,criterioBusquedaFechaRetencion);
	}

	public void redirecionar() {
		redireccionar("retencion.jsf");
	}

	public void setCriterioBusquedaFechaIngreso(
			Date criterioBusquedaFechaIngreso) {
		this.criterioBusquedaFechaIngreso = criterioBusquedaFechaIngreso;
	}

	public void setCriterioBusquedaFechaRetencion(
			Date criterioBusquedaFechaRetencion) {
		this.criterioBusquedaFechaRetencion = criterioBusquedaFechaRetencion;
	}

	public void setCriterioBusquedaNumeroComprobante(
			String criterioBusquedaNumeroComprobante) {
		this.criterioBusquedaNumeroComprobante = criterioBusquedaNumeroComprobante;
	}

	public void setCriterioBusquedaNumeroRetencion(
			String criterioBusquedaNumeroRetencion) {
		this.criterioBusquedaNumeroRetencion = criterioBusquedaNumeroRetencion;
	}

	public void setCriterioBusquedaProveedor(String criterioBusquedaProveedor) {
		this.criterioBusquedaProveedor = criterioBusquedaProveedor;
	}

	public void setDetalleRetencion(DetalleRetencion detalleRetencion) {
		this.detalleRetencion = detalleRetencion;
	}

	public void setListaRetenciones(List<Retencion> listaRetenciones) {
		this.listaRetenciones = listaRetenciones;
	}

	public void setLocalService(LocalService localService) {
		this.localService = localService;
	}

	public void setProductoService(ProductoService productoService) {
		this.productoService = productoService;
	}

	public void setRetencion(Retencion retencion) {
		this.retencion = retencion;
	}

	public void setRetencionService(RetencionService retencionService) {
		this.retencionService = retencionService;
	}

}
