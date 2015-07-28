package ec.com.redepronik.negosys.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ec.com.redepronik.negosys.entity.Ciudad;
import ec.com.redepronik.negosys.entity.DetalleGuiaRemision;
import ec.com.redepronik.negosys.entity.Factura;
import ec.com.redepronik.negosys.entity.GuiaRemision;
import ec.com.redepronik.negosys.entity.Local;
import ec.com.redepronik.negosys.entity.MotivoTraslado;
import ec.com.redepronik.negosys.entity.Persona;
import ec.com.redepronik.negosys.service.CiudadService;
import ec.com.redepronik.negosys.service.FacturaService;
import ec.com.redepronik.negosys.service.GuiaRemisionService;
import ec.com.redepronik.negosys.service.LocalService;

@Controller
@Scope("session")
public class GuiaRemisionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private GuiaRemisionService guiaRemisionService;

	@Autowired
	private FacturaService egresoService;

	@Autowired
	private LocalService localService;

	@Autowired
	private CiudadService ciudadService;

	// @Autowired
	// private GuiaRemisionReport guiaRemisionReport;

//	@Autowired
//	private EmpleadoService empleadoService;

	private List<Local> listaLocales;
	private List<Ciudad> listaCiudades;
	private List<MotivoTraslado> listaMotivotraslados;
//	private List<PersonaCedulaNombre> listaTransportistas;

	private GuiaRemision guiaRemision;
	private Persona cliente;
//	private EmpleadoCargo transportista;

	private List<Factura> listaFacturasGuiaRemision;
	private List<Factura> listaAuxFactura;
	private List<Factura> listaQuitarFacturasGuiaRemision;
	private List<DetalleGuiaRemision> listDetalleGuiaRemision;

	private List<Factura> listaFacturas;
	private String establecimiento;
	private int ciudadId;
	private String criterioBusquedaCodigo;

	private boolean bn = true;

	public GuiaRemisionBean() {
		limpiarObjetos();
	}

	public void cargarCliente() {
		guiaRemisionService.cargarCliente(listaFacturasGuiaRemision, cliente);
	}

	public void cargarDetalleGuiaRemision() {
		guiaRemisionService.cargarDetalleGuiaRemision(cliente,
				listaFacturasGuiaRemision, listaAuxFactura,
				listDetalleGuiaRemision);
	}

	public int getCiudadId() {
		return ciudadId;
	}

	public Persona getCliente() {
		return cliente;
	}

	public String getCriterioBusquedaCodigo() {
		return criterioBusquedaCodigo;
	}

	public String getEstablecimiento() {
		return establecimiento;
	}

	public GuiaRemision getGuiaRemision() {
		return guiaRemision;
	}

	public List<Ciudad> getListaCiudades() {
		return listaCiudades;
	}

	public List<Factura> getListaFacturas() {
		return listaFacturas;
	}

	public List<Factura> getListaFacturasGuiaRemision() {
		return listaFacturasGuiaRemision;
	}

	public List<Local> getListaLocales() {
		return listaLocales;
	}

	public MotivoTraslado[] getListaMotivoTranslado() {
		return MotivoTraslado.values();
	}

	public List<MotivoTraslado> getListaMotivotraslados() {
		return listaMotivotraslados;
	}

	public List<Factura> getListaQuitarFacturasGuiaRemision() {
		return listaQuitarFacturasGuiaRemision;
	}

//	public List<PersonaCedulaNombre> getListaTransportistas() {
//		return listaTransportistas;
//	}

	public List<DetalleGuiaRemision> getListDetalleGuiaRemision() {
		return listDetalleGuiaRemision;
	}

//	public EmpleadoCargo getTransportista() {
//		return transportista;
//	}

	public void imprimirGuiaRemision() {
		// guiaRemisionReport.reporteGiaRemision(guiaRemision);
		limpiarObjetos();
	}

	@PostConstruct
	public void init() {
//		listaLocales = localService.obtenerPorCedulaAndCargo(
//				SecurityContextHolder.getContext().getAuthentication()
//						.getName(), 4);
//		listaTransportistas = empleadoService.obtenerPorCargo(6);
		// listaMotivotraslados = motivoTrasladoService.obtener(true);
//		listaCiudades = ciudadService.obtener(null);
	}

	public void insertar(ActionEvent actionEvent) {
//		guiaRemisionService.insertar(guiaRemision, cliente, transportista,
//				listDetalleGuiaRemision, listaFacturasGuiaRemision);
	}

	public boolean isBn() {
		return bn;
	}

	public void limpiarObjetos() {
		bn = true;

		listaFacturasGuiaRemision = new ArrayList<Factura>();
		listaAuxFactura = new ArrayList<Factura>();
		listaQuitarFacturasGuiaRemision = new ArrayList<Factura>();
		listDetalleGuiaRemision = new ArrayList<DetalleGuiaRemision>();
		guiaRemision = new GuiaRemision();
//		transportista = new EmpleadoCargo();
		cliente = new Persona();

	}

	public void limpiarObjetosBusqueda() {
		establecimiento = "";
		ciudadId = 0;
		criterioBusquedaCodigo = new String();
		listaFacturas = new ArrayList<Factura>();
	}

	public void obtenerFacturas() {
		listaFacturas = egresoService
				.obtenerFacturasParaGuiaRemision(establecimiento, ciudadId,
						criterioBusquedaCodigo.toUpperCase());
	}

	public void quitarDetalleGuiaRemision() {
		guiaRemisionService.quitarDetalleGuiaRemision(listaAuxFactura,
				listaFacturasGuiaRemision, listaQuitarFacturasGuiaRemision,
				listDetalleGuiaRemision, cliente);
	}

	public void setBn(boolean bn) {
		this.bn = bn;
	}

	public void setCiudadId(int ciudadId) {
		this.ciudadId = ciudadId;
	}

	public void setCliente(Persona cliente) {
		this.cliente = cliente;
	}

	public void setCriterioBusquedaCodigo(String criterioBusquedaCodigo) {
		this.criterioBusquedaCodigo = criterioBusquedaCodigo;
	}

	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}

	public void setGuiaRemision(GuiaRemision guiaRemision) {
		this.guiaRemision = guiaRemision;
	}

	public void setListaCiudades(List<Ciudad> listaCiudades) {
		this.listaCiudades = listaCiudades;
	}

	public void setListaFacturas(List<Factura> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}

	public void setListaFacturasGuiaRemision(
			List<Factura> listaFacturasGuiaRemision) {
		this.listaFacturasGuiaRemision = listaFacturasGuiaRemision;
	}

	public void setListaLocales(List<Local> listaLocales) {
		this.listaLocales = listaLocales;
	}

	public void setListaMotivotraslados(
			List<MotivoTraslado> listaMotivotraslados) {
		this.listaMotivotraslados = listaMotivotraslados;
	}

	public void setListaQuitarFacturasGuiaRemision(
			List<Factura> listaQuitarFacturasGuiaRemision) {
		this.listaQuitarFacturasGuiaRemision = listaQuitarFacturasGuiaRemision;
	}

//	public void setListaTransportistas(
//			List<PersonaCedulaNombre> listaTransportistas) {
//		this.listaTransportistas = listaTransportistas;
//	}

	public void setListDetalleGuiaRemision(
			List<DetalleGuiaRemision> listDetalleGuiaRemision) {
		this.listDetalleGuiaRemision = listDetalleGuiaRemision;
	}

	// public void setTransportista(EmpleadoCargo transportista) {
	// this.transportista = transportista;
	// }

}