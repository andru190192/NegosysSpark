package ec.com.redepronik.negosys.controller;

import static ec.com.redepronik.negosys.utils.UtilsAplicacion.presentaMensaje;
import static ec.com.redepronik.negosys.utils.UtilsDate.fechaFormatoDate;
import static ec.com.redepronik.negosys.utils.UtilsMath.newBigDecimal;
import static ec.com.redepronik.negosys.utils.UtilsMath.redondearTotales;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import ec.com.redepronik.negosys.entity.Ciudad;
import ec.com.redepronik.negosys.entity.Cotizacion;
import ec.com.redepronik.negosys.entity.DetalleFactura;
import ec.com.redepronik.negosys.entity.EstadoProductoVenta;
import ec.com.redepronik.negosys.entity.Factura;
import ec.com.redepronik.negosys.entity.Local;
import ec.com.redepronik.negosys.entity.Persona;
import ec.com.redepronik.negosys.entity.Provincia;
import ec.com.redepronik.negosys.entityAux.CierreCajaCajero;
import ec.com.redepronik.negosys.entityAux.CotizacionImprimir;
import ec.com.redepronik.negosys.entityAux.EgresoCaja;
import ec.com.redepronik.negosys.entityAux.EntregaProductoReporte;
import ec.com.redepronik.negosys.entityAux.FacturaImprimir;
import ec.com.redepronik.negosys.entityAux.GananciaFacturaReporte;
import ec.com.redepronik.negosys.entityAux.IngresoCaja;
import ec.com.redepronik.negosys.entityAux.ListFacturaImprimir;
import ec.com.redepronik.negosys.entityAux.RefrigeradorCliente;
import ec.com.redepronik.negosys.entityAux.ReporteDialtor;
import ec.com.redepronik.negosys.entityAux.VolumenVentaFactura;
import ec.com.redepronik.negosys.entityAux.VolumenVentaFacturaVendedor;
import ec.com.redepronik.negosys.entityAux.VolumenVentaProducto;
import ec.com.redepronik.negosys.entityAux.VolumenVentaProductoCliente;
import ec.com.redepronik.negosys.entityAux.VolumenVentaProductoProveedor;
import ec.com.redepronik.negosys.service.CiudadService;
import ec.com.redepronik.negosys.service.CotizacionService;
import ec.com.redepronik.negosys.service.FacturaService;
import ec.com.redepronik.negosys.service.LocalService;
import ec.com.redepronik.negosys.service.NotaCreditoService;
import ec.com.redepronik.negosys.service.PersonaService;
import ec.com.redepronik.negosys.service.ProductoService;
import ec.com.redepronik.negosys.service.VolumenVentaFacturaService;
import ec.com.redepronik.negosys.service.VolumenVentaProductoProveedorService;
import ec.com.redepronik.negosys.utils.service.ReporteService;

@Controller
@Scope("session")
public class FacturacionReportes {

	@Autowired
	private ReporteService reporteService;

	@Autowired
	private VolumenVentaFacturaService volumenVentaFacturaService;

	@Autowired
	private FacturaService egresoService;

	@Autowired
	private VolumenVentaProductoProveedorService volumenVentaProductoProveedorService;

	@Autowired
	private NotaCreditoService notaCreditoService;

	@Autowired
	private CiudadService ciudadService;

	@Autowired
	private ProductoService productoService;

	@Autowired
	private CotizacionService cotizacionService;

	@Autowired
	private PersonaService personaService;

	@Autowired
	private LocalService localService;

	// //cierre caja
	private List<Persona> listaCajeros;
	private Date fechaCierreCaja;
	private int cajero;
	private boolean disabledCajero;

	private Local local;
	private Date fechaInicio;
	private Date fechaFin;
	private Provincia provincia;
	private Ciudad ciudad;

	private List<Factura> listaFacturaSeleccionado;
	private List<Factura> listaFacturas;
	private List<Persona> listaVendedores;
	private List<Persona> listaProveedores;
	private List<Ciudad> listaCiudades;

	private int vendedorId;
	private int ordenarId;
	private int estadoDocumento;
	private int consumidorFinal;

	private Date fechaInicioVVPPC;
	private Date fechaFinVVPPC;
	private int proveedorId;
	private int PersonaId;
	private int ciudadId;
	private List<Ciudad> listaCiudadesVVPPC;
	private List<Persona> listaPersonasVVPPC;

	private EstadoProductoVenta estado;

	private boolean gananciaBrutaNeta;

	public void cargarCiudades() {
		if (provincia.getId() != 0) {
			listaCiudades = new ArrayList<Ciudad>();
			// listaCiudades = ciudadService.obtenerPorProvincia(provincia);
		}
	}

	public void cargarPersonaProveedorCiudadFechas() {
		// if (proveedorId != 0 && ciudadId != 0)
		// listaPersonasVVPPC = personaService.obtenerPorProveedorPorCiudad(
		// proveedorId, ciudadId, fechaInicioVVPPC, fechaFinVVPPC);
	}

	public int getCajero() {
		return cajero;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public int getCiudadId() {
		return ciudadId;
	}

	public int getPersonaId() {
		return PersonaId;
	}

	public int getConsumidorFinal() {
		return consumidorFinal;
	}

	public EstadoProductoVenta getEstado() {
		return estado;
	}

	public int getEstadoDocumento() {
		return estadoDocumento;
	}

	public Date getFechaCierreCaja() {
		return fechaCierreCaja;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public Date getFechaFinVVPPC() {
		return fechaFinVVPPC;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public Date getFechaInicioVVPPC() {
		return fechaInicioVVPPC;
	}

	public List<Persona> getListaCajeros() {
		return listaCajeros;
	}

	public List<Ciudad> getListaCiudades() {
		return listaCiudades;
	}

	public List<Ciudad> getListaCiudadesVVPPC() {
		return listaCiudadesVVPPC;
	}

	public List<Persona> getListaPersonasVVPPC() {
		return listaPersonasVVPPC;
	}

	public List<Factura> getListaFacturas() {
		return listaFacturas;
	}

	public List<Factura> getListaFacturaSeleccionado() {
		return listaFacturaSeleccionado;
	}

	public List<Persona> getListaProveedores() {
		return listaProveedores;
	}

	public Provincia[] getListaProvincias() {
		return Provincia.values();
	}

	public List<Persona> getListaVendedores() {
		return listaVendedores;
	}

	public EstadoProductoVenta[] getListEstadoProductoFacturas() {
		return EstadoProductoVenta.values();
	}

	public Local getLocal() {
		return local;
	}

	public int getOrdenarId() {
		return ordenarId;
	}

	public int getProveedorId() {
		return proveedorId;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public int getVendedorId() {
		return vendedorId;
	}

	@PostConstruct
	public void init() {
		// listaVendedores = personaService.obtenerPorRol(Rol.VEND);
		// listaCajeros = personaService.obtenerPorRol(Rol.CAJA);
		// listaProveedores = personaService.obtenerPorRol(Rol.PROV);
		// listaCiudadesVVPPC = ciudadService.obtener(true);

		setDisabledCajero(false);
		// if (!personaService.comprobarRol(SecurityContextHolder.getContext()
		// .getAuthentication().getName(), "ADMI")) {
		// cajero = personaService.obtenerPorNumeroDocumentoYRol(
		// SecurityContextHolder.getContext().getAuthentication()
		// .getName(), Rol.CAJA).getId();
		// setDisabledCajero(true);
		// }

		fechaCierreCaja = new Date();

		listaFacturaSeleccionado = new ArrayList<Factura>();
		ciudad = new Ciudad();
		listaCiudades = new ArrayList<Ciudad>();
		local = new Local();
		fechaInicioVVPPC = new Date();
		fechaFinVVPPC = new Date();
		gananciaBrutaNeta = false;
	}

	public boolean isDisabledCajero() {
		return disabledCajero;
	}

	public boolean isGananciaBrutaNeta() {
		return gananciaBrutaNeta;
	}

	public void limpiarObjetosBusqueda() {
		fechaInicio = null;
		fechaFin = null;
		ciudad = new Ciudad();
		local = new Local();
		listaFacturas = new ArrayList<Factura>();
	}

	public void obtenerFacturas() {
		if (fechaInicio == null)
			presentaMensaje(FacesMessage.SEVERITY_ERROR,
					"INGRESE UNA FECHA DE INICIO");
		else if (fechaFin == null)
			presentaMensaje(FacesMessage.SEVERITY_ERROR,
					"INGRESE UNA FECHA DE FIN");
		else if (local.getCodigoEstablecimiento().compareToIgnoreCase("") == 0)
			presentaMensaje(FacesMessage.SEVERITY_ERROR, "ESCOJA UN LOCAL");
		else if (provincia.getId() == 0)
			presentaMensaje(FacesMessage.SEVERITY_ERROR, "ESCOJA UNA PROVINCIA");
		else if (ciudad.getId() == 0)
			presentaMensaje(FacesMessage.SEVERITY_ERROR, "ESCOJA UNA CIUDAD");
		else
			listaFacturas = egresoService.obtenerEntregaProducto(fechaInicio,
					fechaFin, local, ciudad);
	}

	public void onDateSelect(SelectEvent event) {
		fechaInicioVVPPC = fechaFormatoDate((Date) event.getObject());
	}

	public void onDateSelect1(SelectEvent event) {
		fechaFinVVPPC = fechaFormatoDate((Date) event.getObject());
	}

	private List<EntregaProductoReporte> quicksort(
			List<EntregaProductoReporte> list, int iz, int de) {
		if (iz >= de)
			return list;
		int i = iz;
		int d = de;
		if (iz != de) {
			EntregaProductoReporte epr;
			int pivote = iz;
			while (iz != de) {
				while (list.get(de).getNombre()
						.compareToIgnoreCase(list.get(pivote).getNombre()) >= 0
						&& i < de)
					de--;
				while (list.get(iz).getNombre()
						.compareToIgnoreCase(list.get(pivote).getNombre()) < 0
						&& i < de)
					iz++;
				if (de != iz) {
					epr = list.get(de);
					list.set(de, list.get(iz));
					list.set(iz, epr);
				}
				if (iz == de) {
					quicksort(list, i, iz - 1);
					quicksort(list, iz + 1, d);
				}
			}
		}
		return list;
	}

	@PreAuthorize("hasAnyRole('READ','ADMI','CAJA')")
	public void reporteCierreCaja(ActionEvent actionEvent) {
		List<CierreCajaCajero> list = new ArrayList<CierreCajaCajero>();
		if (cajero == 0)
			for (Persona c : listaCajeros) {
				List<IngresoCaja> listIngresoCaja = egresoService
						.reporteIngresoCaja(c.getId(), fechaCierreCaja);
				List<EgresoCaja> listEgresoCaja = notaCreditoService
						.reporteEgresoCaja(c.getId(), fechaCierreCaja);
				if (!listIngresoCaja.isEmpty() || !listEgresoCaja.isEmpty()) {
					BigDecimal totalIngreso = newBigDecimal();
					BigDecimal totalEgreso = newBigDecimal();
					for (IngresoCaja ic : listIngresoCaja) {
						ic.setTotalFactura(egresoService.calcularTotal(ic
								.getCodigoDocumento()));
						totalIngreso = totalIngreso.add(ic.getPago());
					}
					for (EgresoCaja ec : listEgresoCaja) {
						ec.setTotalFactura(redondearTotales(ec.getPago()
								.subtract(
										egresoService.calcularTotal(ec
												.getCodigoDocumentoF()))));
						ec.setPago(ec.getTotalFactura());
						totalEgreso = totalEgreso.add(ec.getPago());
					}
					list.add(new CierreCajaCajero(
							c.getRazonSocial(),
							listIngresoCaja,
							listEgresoCaja,
							redondearTotales(totalIngreso.subtract(totalEgreso))));
				}
			}
		else {
			List<IngresoCaja> listIngresoCaja = egresoService
					.reporteIngresoCaja(cajero, fechaCierreCaja);
			List<EgresoCaja> listEgresoCaja = notaCreditoService
					.reporteEgresoCaja(cajero, fechaCierreCaja);
			if (!listIngresoCaja.isEmpty() || !listEgresoCaja.isEmpty()) {
				BigDecimal totalIngreso = newBigDecimal();
				BigDecimal totalEgreso = newBigDecimal();
				for (IngresoCaja ic : listIngresoCaja) {
					ic.setTotalFactura(egresoService.calcularTotal(ic
							.getCodigoDocumento()));
					totalIngreso = totalIngreso.add(ic.getPago());
				}
				for (EgresoCaja ec : listEgresoCaja) {
					ec.setTotalFactura(egresoService.calcularTotal(ec
							.getCodigoDocumentoF()));
					totalEgreso = totalEgreso.add(ec.getPago());
				}
				// Persona p = personaService.obtenerObjeto("", new Object[] {},
				// false);
				// obtenerPorId(cajero);
				// list.add(new CierreCajaCajero(p.getRazonSocial(),
				// listIngresoCaja, listEgresoCaja,
				// redondearTotales(totalIngreso.subtract(totalEgreso))));
			}
		}
		Map<String, Object> parametro = new HashMap<String, Object>();
		parametro.put("fechaCierre", fechaCierreCaja);
		reporteService.generarReportePDF(list, parametro, "CierreCajaCajero");
	}

	public boolean reporteCotizacion(Integer cotizacionId) {
		List<CotizacionImprimir> list = new ArrayList<CotizacionImprimir>();
		boolean bn = cotizacionService.imprimirCotizacion(cotizacionId, list);
		if (bn) {
			Cotizacion c = list.get(0).getEgreso();
			Map<String, Object> parametro = new HashMap<String, Object>();
			parametro.put("numCotizacion",
					c.getEstablecimiento() + "-" + c.getPuntoEmision() + "-"
							+ c.getSecuencia());
			reporteService.generarReportePDF(list, parametro, "Cotizacion");
		}
		return bn;
	}

	@PreAuthorize("hasAnyRole('READ','ADMI')")
	public void reporteEntregaProducto(ActionEvent actionEvent) {
		if (listaFacturaSeleccionado.isEmpty())
			presentaMensaje(FacesMessage.SEVERITY_ERROR,
					"ESCOJA UNA FACTURA PARA GENERAR EL REPORTE");
		else {
			List<DetalleFactura> listDetalleFactura = new ArrayList<DetalleFactura>();
			for (Factura egreso : listaFacturaSeleccionado)
				listDetalleFactura.addAll(egresoService
						.duplicarDetalleFactura(egreso.getDetalleFactura()));

			List<DetalleFactura> listDetalleFacturaFinal = egresoService
					.sumarCantidadesTodo(listDetalleFactura);

			List<EntregaProductoReporte> list = new ArrayList<EntregaProductoReporte>();

			// for (DetalleFactura de : listDetalleFacturaFinal) {
			// List<ProductoUnidad> listaUnidades = productoService
			// .obtenerUnidadesPorProductoId(de.getProducto().getId())
			// .getProductosUnidades();
			//
			// list.add(new EntregaProductoReporte(de.getProducto().getId()
			// .toString(), de.getProducto().getNombre(), "("
			// + de.getCantidad()
			// + listaUnidades.get(0).getUnidad().getAbreviatura()
			// + ") "
			// + productoService.convertirUnidadString(
			// de.getCantidad(), listaUnidades)));
			// }

			Map<String, Object> parametro = new HashMap<String, Object>();
			parametro.put("fechInicio", fechaInicio);
			parametro.put("fechFin", fechaFin);
			parametro.put("CIUDAD", ciudadService.obtenerPorId(ciudad.getId())
					.getNombre());
			reporteService.generarReportePDF(
					quicksort(list, 0, list.size() - 1), parametro,
					"EntregaProducto");
		}
	}

	public boolean reporteFactura(Integer facturaId) {
		List<FacturaImprimir> list = new ArrayList<FacturaImprimir>();
		boolean bn = egresoService.imprimirFactura(facturaId, list);
		if (bn)
			reporteService.generarReportePDF(list,
					new HashMap<String, Object>(), "Factura");
		return bn;
	}

	public boolean reporteFacturaC(Integer facturaId) {
		List<FacturaImprimir> list1 = new ArrayList<FacturaImprimir>();
		boolean bn = egresoService.imprimirFactura(facturaId, list1);
		if (bn) {
			List<ListFacturaImprimir> list = new ArrayList<ListFacturaImprimir>();
			list.add(new ListFacturaImprimir(list1));
			reporteService.generarReportePDF(list,
					new HashMap<String, Object>(), "FacturaC");
		}
		return bn;
	}

	@PreAuthorize("hasRole('READ')")
	public void reporteGanancia(ActionEvent actionEvent) {
		List<GananciaFacturaReporte> list = new ArrayList<GananciaFacturaReporte>();
		if (gananciaBrutaNeta)
			list = egresoService.obtenerGananciaBrutaPorFechas(fechaInicio,
					fechaFin);
		else
			list = egresoService.obtenerGananciaNetaPorFechas(fechaInicio,
					fechaFin);

		if (list == null || list.isEmpty()) {
			presentaMensaje(FacesMessage.SEVERITY_ERROR,
					"NO HAY DATOS QUE MOSTRAR");
		} else {
			Map<String, Object> parametro = new HashMap<String, Object>();
			parametro.put("fechaInicio", fechaInicio);
			parametro.put("fechaFin", fechaFin);
			reporteService.generarReportePDF(list, parametro, "Ganancia");
		}
		fechaInicio = null;
		fechaFin = null;
		gananciaBrutaNeta = false;
	}

	@PreAuthorize("hasRole('READ')")
	public void reporteGananciaPorProducto(ActionEvent actionEvent) {
		List<VolumenVentaProductoProveedor> list = new ArrayList<VolumenVentaProductoProveedor>();
		list = volumenVentaProductoProveedorService
				.obtenerPorFechasPorProveedor(proveedorId, fechaInicio,
						fechaFin);

		Map<String, Object> parametro = new HashMap<String, Object>();
		parametro.put("fechaInicio", fechaInicio);
		parametro.put("fechaFin", fechaFin);

		Persona p = personaService.obtenerObjeto("", new Object[] {}, false,
				new Object[] {});
		// obtenerPorId(proveedorId);
		parametro.put("proveedor", p.getRazonSocial());
		reporteService
				.generarReportePDF(list, parametro, "GananciaPorProducto");

		fechaInicio = null;
		fechaFin = null;
	}

	@PreAuthorize("hasAnyRole('READ','ADMI')")
	public void reporteVolumenVentaPersonaProveedor(ActionEvent actionEvent) {
		if (proveedorId == 0) {
			presentaMensaje(FacesMessage.SEVERITY_ERROR, "ESCOJA UN PROVEEDOR");
		} else if (ciudadId == 0) {
			presentaMensaje(FacesMessage.SEVERITY_ERROR, "ESCOJA UNA CIUDAD");
		} else if (fechaInicioVVPPC.after(fechaFinVVPPC)) {
			presentaMensaje(FacesMessage.SEVERITY_ERROR,
					"LA FECHA INICIO NO PUEDE ESTAR DESPUES DE LA FECHA FIN");
		} else {
			if (PersonaId == 0) {
				List<ReporteDialtor> list = new ArrayList<ReporteDialtor>();
				List<VolumenVentaProductoCliente> listadoVVPC = new ArrayList<VolumenVentaProductoCliente>();
				List<RefrigeradorCliente> listadoRC = new ArrayList<RefrigeradorCliente>();
				for (Persona Persona : personaService.obtenerLista("",
						new Object[] {}, false, new Object[] {})) {
					// .obtenerPorProveedorPorCiudad(proveedorId, ciudadId,
					// fechaInicioVVPPC, fechaFinVVPPC)) {
					// for (Producto producto : productoService
					// .obtenerPorProveedor(proveedorId)) {
					// listadoVVPC
					// .add(new VolumenVentaProductoCliente(
					// producto.getNombre(),
					// Persona.getRazonSocial(),
					// egresoService
					// .obtenerCantidadVendidaPorProveedorPersona(
					// Persona.getId(),
					// producto.getId(),
					// fechaInicioVVPPC,
					// fechaFinVVPPC)
					// .intValue()));
					// }
				}
				listadoRC.add(new RefrigeradorCliente("prueba", "prueba",
						"prueba"));
				list.add(new ReporteDialtor(listadoVVPC, listadoRC));
				Map<String, Object> parametro = new HashMap<String, Object>();
				reporteService.generarReporteXLS(list, parametro,
						"ReporteCoaltor");
			} else {
				List<VolumenVentaProductoCliente> list = new ArrayList<VolumenVentaProductoCliente>();
				Persona Persona = personaService.obtenerObjeto("",
						new Object[] {}, false, new Object[] {});
				// obtenerPorId(PersonaId);
				// for (Producto producto : productoService
				// .obtenerPorProveedor(proveedorId)) {
				// list.add(new VolumenVentaProductoCliente(producto
				// .getNombre(), Persona.getRazonSocial(),
				// egresoService
				// .obtenerCantidadVendidaPorProveedorCliente(
				// Persona.getId(), producto.getId(),
				// fechaInicioVVPPC, fechaFinVVPPC)
				// .intValue()));
				// }
				Map<String, Object> parametro = new HashMap<String, Object>();
				reporteService.generarReporteXLS(list, parametro,
						"ReporteCoaltorSimple");
			}
		}
	}

	@PreAuthorize("hasAnyRole('READ','CONT','ADMI')")
	public void reporteVolumenVentaConsumidorFinal() {
		if (fechaInicio == null || fechaFin == null) {
			presentaMensaje(FacesMessage.SEVERITY_WARN,
					"DEBE INGRESAR LA FECHA DE INICIO Y FIN");
		} else if (fechaInicio.compareTo(fechaFin) > 0)
			presentaMensaje(FacesMessage.SEVERITY_WARN,
					"DEBE INGRESAR LA FECHA DE INICIO Y FIN");
		else {
			List<VolumenVentaFactura> list = volumenVentaFacturaService
					.obtenerVolumenventaFacturaPorConsumidorFinal(
							consumidorFinal, fechaInicio, fechaFin);
			Map<String, Object> parametro = new HashMap<String, Object>();
			parametro.put("fechInicio", fechaInicio);
			parametro.put("fechFin", fechaFin);
			parametro.put("VENDEDOR",
					(consumidorFinal == 0) ? "CONSUMIDOR FINAL"
							: (consumidorFinal == 1) ? "SIN CONSUMIDOR FINAL"
									: "TODOS");

			reporteService.generarReportePDF(list, parametro,
					"VolumenVentaFactura");
		}
	}

	@PreAuthorize("hasRole('READ')")
	public void reporteVolumenVentaFactura(ActionEvent actionEvent) {
		if (vendedorId == 0) {
			List<VolumenVentaFacturaVendedor> listaVvfv = new ArrayList<VolumenVentaFacturaVendedor>();
			for (Persona pcn : listaVendedores) {
				List<VolumenVentaFactura> listaVolumenVentaFactura = new ArrayList<VolumenVentaFactura>();
				BigDecimal total = newBigDecimal();
				for (Factura f : egresoService
						.obtenerPorFechasAndVendedorAndEstadoDocumento(
								fechaInicio, fechaFin, pcn.getId(),
								estadoDocumento)) {
					BigDecimal tFactura = egresoService.calcularTotal(f);
					total = total.add(tFactura);
					listaVolumenVentaFactura.add(new VolumenVentaFactura(f
							.getFechaInicio(), f.getEstablecimiento() + "-"
							+ f.getPuntoEmision() + "-" + f.getSecuencia(), f
							.getClienteFactura().getRazonSocial(), tFactura));
				}
				if (!listaVolumenVentaFactura.isEmpty())
					listaVvfv
							.add(new VolumenVentaFacturaVendedor(pcn
									.getRazonSocial(), total,
									listaVolumenVentaFactura));
			}
			Map<String, Object> parametro = new HashMap<String, Object>();
			parametro.put("fechInicio", fechaInicio);
			parametro.put("fechFin", fechaFin);
			reporteService.generarReportePDF(listaVvfv, parametro,
					"VolumenVentaFacturaVendedor");
		} else {
			List<VolumenVentaFactura> list = volumenVentaFacturaService
					.obtenerPorFechasAndVendedorAndEstadoDocumento(vendedorId,
							estadoDocumento, fechaInicio, fechaFin);
			Persona p = personaService.obtenerObjeto("", new Object[] {},
					false, new Object[] {});
			// obtenerPorId(vendedorId);

			Map<String, Object> parametro = new HashMap<String, Object>();
			parametro.put("fechInicio", fechaInicio);
			parametro.put("fechFin", fechaFin);
			parametro.put("VENDEDOR", p.getRazonSocial());
			reporteService.generarReportePDF(list, parametro,
					"VolumenVentaFactura");
		}
		fechaInicio = null;
		fechaFin = null;
	}

	@PreAuthorize("hasAnyRole('READ','ADMI')")
	public void reporteVolumenVentaProducto(ActionEvent actionEvent) {
		List<VolumenVentaProducto> list = egresoService
				.obtenerVolumenVentaProducto(fechaInicio, fechaFin, ordenarId,
						estado);
		if (!list.isEmpty()) {
			// for (VolumenVentaProducto vvp : list) {
			// List<ProductoUnidad> listaUnidades = productoService
			// .obtenerUnidadesPorProductoId(vvp.getId())
			// .getProductosUnidades();
			// vvp.setCantidadString("("
			// + vvp.getCantidad()
			// + listaUnidades.get(0).getUnidad().getAbreviatura()
			// + ") "
			// + productoService.convertirUnidadString(vvp
			// .getCantidad().intValue(), listaUnidades));
			// }
			Map<String, Object> parametro = new HashMap<String, Object>();
			parametro.put("fechInicio", fechaInicio);
			parametro.put("fechFin", fechaFin);
			parametro.put("estado",
					estado == null ? "TODOS" : estado.getNombre());
			fechaInicio = null;
			fechaFin = null;
			reporteService.generarReportePDF(list, parametro,
					"VolumenVentaProducto");
		}
	}

	public void setCajero(int cajero) {
		this.cajero = cajero;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public void setCiudadId(int ciudadId) {
		this.ciudadId = ciudadId;
	}

	public void setPersonaId(int PersonaId) {
		this.PersonaId = PersonaId;
	}

	public void setConsumidorFinal(int consumidorFinal) {
		this.consumidorFinal = consumidorFinal;
	}

	public void setDisabledCajero(boolean disabledCajero) {
		this.disabledCajero = disabledCajero;
	}

	public void setEstado(EstadoProductoVenta estado) {
		this.estado = estado;
	}

	public void setEstadoDocumento(int estadoDocumento) {
		this.estadoDocumento = estadoDocumento;
	}

	public void setFechaCierreCaja(Date fechaCierreCaja) {
		this.fechaCierreCaja = fechaCierreCaja;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public void setFechaFinVVPPC(Date fechaFinVVPPC) {
		this.fechaFinVVPPC = fechaFinVVPPC;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public void setFechaInicioVVPPC(Date fechaInicioVVPPC) {
		this.fechaInicioVVPPC = fechaInicioVVPPC;
	}

	public void setGananciaBrutaNeta(boolean gananciaBrutaNeta) {
		this.gananciaBrutaNeta = gananciaBrutaNeta;
	}

	public void setListaCajeros(List<Persona> listaCajeros) {
		this.listaCajeros = listaCajeros;
	}

	public void setListaCiudades(List<Ciudad> listaCiudades) {
		this.listaCiudades = listaCiudades;
	}

	public void setListaCiudadesVVPPC(List<Ciudad> listaCiudadesVVPPC) {
		this.listaCiudadesVVPPC = listaCiudadesVVPPC;
	}

	public void setListaPersonasVVPPC(List<Persona> listaPersonasVVPPC) {
		this.listaPersonasVVPPC = listaPersonasVVPPC;
	}

	public void setListaFacturas(List<Factura> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}

	public void setListaFacturaSeleccionado(
			List<Factura> listaFacturaSeleccionado) {
		this.listaFacturaSeleccionado = listaFacturaSeleccionado;
	}

	public void setListaProveedores(List<Persona> listaProveedores) {
		this.listaProveedores = listaProveedores;
	}

	public void setListaVendedores(List<Persona> listaVendedores) {
		this.listaVendedores = listaVendedores;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public void setOrdenarId(int ordenarId) {
		this.ordenarId = ordenarId;
	}

	public void setProveedorId(int proveedorId) {
		this.proveedorId = proveedorId;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public void setVendedorId(int vendedorId) {
		this.vendedorId = vendedorId;
	}

	// public void reporteGiaRemision(Guiaremision guiaRemision) {
	// List<Guiaremision> list = new ArrayList<Guiaremision>();
	// list.add(guiaRemision);
	// Map<String, Object> parametro = new HashMap<String, Object>();
	// reporteService.generarReportePDF(list, parametro, "GuiaRemision");
	// }
}