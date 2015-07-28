package ec.com.redepronik.negosys.controller;

import static ec.com.redepronik.negosys.utils.UtilsAplicacion.enviarVariableVista;
import static ec.com.redepronik.negosys.utils.UtilsAplicacion.presentaMensaje;
import static ec.com.redepronik.negosys.utils.UtilsDate.timestamp;
import static ec.com.redepronik.negosys.utils.UtilsDate.timestampCompleto;
import static ec.com.redepronik.negosys.utils.UtilsImpresion.escribeCabezeraFactura;
import static ec.com.redepronik.negosys.utils.UtilsImpresion.escribeCuerpoFactura;
import static ec.com.redepronik.negosys.utils.UtilsImpresion.escribeFinFactura;
import static ec.com.redepronik.negosys.utils.UtilsImpresion.escribirFactura;
import static ec.com.redepronik.negosys.utils.UtilsMath.newBigDecimal;
import static ec.com.redepronik.negosys.utils.UtilsMath.redondearTotales;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import ec.com.redepronik.negosys.entity.Ciudad;
import ec.com.redepronik.negosys.entity.DetalleFactura;
import ec.com.redepronik.negosys.entity.Entrada;
import ec.com.redepronik.negosys.entity.EstadoProductoVenta;
import ec.com.redepronik.negosys.entity.Factura;
import ec.com.redepronik.negosys.entity.Local;
import ec.com.redepronik.negosys.entity.Persona;
import ec.com.redepronik.negosys.entity.Producto;
import ec.com.redepronik.negosys.entity.Provincia;
import ec.com.redepronik.negosys.entity.TipoPago;
import ec.com.redepronik.negosys.entityAux.CantidadFactura;
import ec.com.redepronik.negosys.entityAux.FacturaReporte;
import ec.com.redepronik.negosys.entityAux.ProductoPvp;
import ec.com.redepronik.negosys.service.CiudadService;
import ec.com.redepronik.negosys.service.DocumentosElectronicosService;
import ec.com.redepronik.negosys.service.FacturaCajeroService;
import ec.com.redepronik.negosys.service.FacturaService;
import ec.com.redepronik.negosys.service.LocalService;
import ec.com.redepronik.negosys.service.PersonaService;
import ec.com.redepronik.negosys.service.ProductoService;
import ec.com.redepronik.negosys.utils.Utils;

@Controller
@Scope("session")
public class FacturaCajeroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private FacturaService facturaService;

	@Autowired
	private FacturaCajeroService facturaCajeroService;

	@Autowired
	private ProductoService productoService;

	@Autowired
	private PersonaService clienteService;

	@Autowired
	private LocalService localService;

	// @Autowired
	// private EmpleadoService empleadoService;

	@Autowired
	private PersonaService personaService;

	@Autowired
	private CiudadService ciudadService;

	@Autowired
	private DocumentosElectronicosService documentosElectronicosService;

	private List<EstadoProductoVenta> listEstadoProductoFactura;

	private Factura egreso;

	private List<FacturaReporte> listaFacturaReporte;
	private CantidadFactura cfCalculos;
	private CantidadFactura cfPresentar;

	private BigDecimal cambio;
	private BigDecimal montoEntrada;

	private Integer localId;

	// Cuadro de Busqueda de clientes
	private List<Persona> listaClienteBusqueda;
	private String criterioClienteBusqueda;

	private int registrosActivos;

	private ProductoPvp productoPvp;
	private String criterioBusquedaProducto;
	private List<ProductoPvp> listaBusquedaProducto;

	private String descripcion;
	private String mayorista;
	private String cedula;
	private Persona persona;
	private List<Ciudad> listaCiudades;

	private String cantidadProducto;

	// mostrar datos del cliente
	private String cliente;
	private String clienteFactura;

	public FacturaCajeroBean() {
		RequestContext.getCurrentInstance().execute(
				"PF('buscarCliente').show()");
	}

	public void cambiarCantidad(FacturaReporte facturaReporte) {
		int fila = listaFacturaReporte.indexOf(facturaReporte);
		cfCalculos = facturaCajeroService.calcularCantidad(cfCalculos,
				facturaReporte, true);
		if (fila == listaFacturaReporte.size() - 1)
			listaFacturaReporte.add(new FacturaReporte());
		registrosActivos = facturaCajeroService
				.contarRegistrosActivos(listaFacturaReporte);
	}

	public void cargarCiudades() {
		listaCiudades = new ArrayList<Ciudad>();
		listaCiudades = ciudadService.obtenerLista("", new Object[] {}, false,
				new Object[] {});
		// obtenerPorProvincia(persona.getCiudad()
		// .getProvincia());
	}

	public void cargarCliente() {
		cambio = new BigDecimal("0.00");
		if (criterioClienteBusqueda.length() == 2
				&& criterioClienteBusqueda.compareTo("99") == 0) {
			Persona p = clienteService.obtenerObjeto("", new Object[] {},
					false, new Object[] {});
			// obtenerPorCedula("9999999999999");
			egreso.setCliente(p);
			egreso.setClienteFactura(p);
			cliente = p.getId().toString().concat("-")
					.concat(p.getNumeroDocumento()).concat("-")
					.concat(p.getRazonSocial());
			clienteFactura = cliente;
			enviarVariableVista("errorCliente", false);
		} else if (criterioClienteBusqueda.length() != 10
				&& criterioClienteBusqueda.length() != 13) {
			presentaMensaje(FacesMessage.SEVERITY_ERROR,
					"CÉDULA O RUC INCORRECTOS", "errorCliente", true);
		} else {
			Persona p = clienteService.obtenerObjeto("", new Object[] {},
					false, new Object[] {});
			// .obtenerPorCedula(criterioClienteBusqueda);
			Persona clienteCompra = null;

			if (p == null) {
				limpiarPersona();
				persona.setNumeroDocumento(criterioClienteBusqueda);
				enviarVariableVista("errorCliente", true);
				enviarVariableVista("errorClienteNoExiste", true);
			} else {
				clienteCompra = p;
				Persona cf = clienteService.obtenerObjeto("", new Object[] {},
						false, new Object[] {});
				// obtenerPorCedula(
				// criterioClienteBusqueda).getCliente();
				egreso.setCliente(clienteCompra);
				egreso.setClienteFactura(cf);
				cliente = p.getId().toString().concat("-")
						.concat(p.getNumeroDocumento()).concat("-")
						.concat(p.getRazonSocial());
				clienteFactura = cliente;
				enviarVariableVista("errorCliente", false);
			}
		}
		setCriterioClienteBusqueda("");
	}

	public void cargarClienteFactura() {
		// egreso.setClienteFactura(clienteService.cargarCliente(clienteFactura));
	}

	public void cargarClienteNombreFactura(SelectEvent event) {
		// Persona persona = clienteService.obtenerPorPersonaId(egreso
		// .getClienteFactura().getPersona().getId());
		// egreso.setClienteFactura(persona.getCliente());
	}

	public void cargarProducto(FacturaReporte facturaReporte) {
		String codigo = facturaReporte.getCodigo();
		String com = "";
		mayorista = "";
		int cantidad = 1;
		boolean ingreso = true;
		if (codigo.toUpperCase().compareTo("PP") != 0) {
			if (codigo.contains("-")) {
				String aux[] = codigo.split("-");
				if (aux.length == 3
						&& Utils.validacion(
								codigo,
								"(cnt-|CNT-|cla-|CLA-|mov-|MOV-|dir-|DIR-|tvc-|TVC-){1,3}[0-9]+(-){1,1}[0-9]+",
								"null")) {
					String ope = aux[0];
					String num = aux[1];
					if (ope.compareToIgnoreCase("cnt") == 0) {
						facturaReporte.setCodigo("06");
						facturaReporte.setDescripcion("RECARGA DE CNT-#" + num);
					} else if (ope.compareToIgnoreCase("cla") == 0) {
						facturaReporte.setCodigo("05");
						facturaReporte.setDescripcion("RECARGA DE CLARO-#"
								+ num);
					} else if (ope.compareToIgnoreCase("mov") == 0) {
						facturaReporte.setCodigo("01");
						facturaReporte.setDescripcion("RECARGA DE MOVISTAR-#"
								+ num);
					} else if (ope.compareToIgnoreCase("dir") == 0) {
						facturaReporte.setCodigo("12");
						facturaReporte.setDescripcion("RECARGA DE DIRECTV-#"
								+ num);
					} else if (ope.compareToIgnoreCase("tvc") == 0) {
						facturaReporte.setCodigo("31");
						facturaReporte.setDescripcion("RECARGA DE TVCABLE-#"
								+ num);
					}
					facturaReporte.setPrecioUnitVenta(new BigDecimal(aux[2]));
					ingreso = true;
				} else {
					String cod = aux[0];
					if (cod.length() == 1) {
						if (Utils.validacion(cod, "(v|V|e|E|c|C|p|P|m|M){1,1}",
								"null")) {
							com = String.valueOf(cod.charAt(0));
							facturaReporte.setCodigo(aux[1]);
							ingreso = true;
							// if (cod.compareToIgnoreCase("v") == 0) {
							// Producto producto = productoService
							// .obtenerPorEan(String.valueOf(aux[1]));
							// descripcion = producto.getNombre()
							// + "- $ "
							// + redondearTotales(productoService
							// .obtenerPvpConImpuestos(producto))
							// + " -";
							// mayorista = "$ "
							// + redondearTotalS(productoService
							// .obtenerMayoristaConImpuestos(producto));
							// listaFacturaReporte.get(
							// listaFacturaReporte.size() - 1)
							// .setCodigo("");
							// ingreso = false;
							// }
						}
					} else {
						if (Utils.validacion(cod,
								"(e|E|c|C|p|P|m|M){1,1}[0-9]+", "null")) {
							com = String.valueOf(cod.charAt(0));
							cantidad = Integer.parseInt(cod.substring(1,
									cod.length()));
							facturaReporte.setCodigo(aux[1]);
							ingreso = true;
						}
					}
				}
			}
			if (ingreso
					&& facturaCajeroService.cargarProductoLista(facturaReporte,
							listaFacturaReporte, localId)) {
				FacturaReporte fr = listaFacturaReporte.get(listaFacturaReporte
						.size() - 1);
				if (com.compareToIgnoreCase("e") == 0)
					cantidad *= -1;
				else if (com.compareToIgnoreCase("p") == 0)
					facturaCajeroService.promocion(fr);
				else if (com.compareToIgnoreCase("m") == 0)
					facturaCajeroService.precioMayorista(fr);

				fr.setCantidad(cantidad);
				cambiarCantidad(fr);
				// descripcion = fr.getDescripcion()
				// + " - $"
				// + redondearTotales(productoService
				// .obtenerPvpConImpuestos(fr.getProducto()));

				escribeCabezeraFactura(localService.obtenerObjeto("",
						new Object[] {}, false, new Object[] {}), cedula);
				// obtenerPorEstablecimiento(egreso
				// .getEstablecimiento()), cedula);
				fr = listaFacturaReporte.get(listaFacturaReporte.size() - 2);
				escribeCuerpoFactura(cedula, fr, com);
			}
		}
	}

	public boolean comprobarLocal() {
		if (egreso.getEstablecimiento() == null
				|| egreso.getEstablecimiento().compareTo("") == 0) {
			presentaMensaje(FacesMessage.SEVERITY_ERROR,
					"ERROR: DEBE ESCOJER UN LOCAL");
			return false;
		}
		return true;
	}

	public void comprobarPersona() {
		String cedula = persona.getNumeroDocumento();
		persona = personaService.obtenerObjeto("", new Object[] {}, false,
				new Object[] {});
		// obtenerPorCedula(persona.getNumeroDocumento());
		if (persona != null) {
			limpiarPersona();
			presentaMensaje(FacesMessage.SEVERITY_ERROR, "EL CLIENTE YA EXISTE");
		} else if (persona != null) {
			// persona.getCliente().setNombreComercial(
			// persona.getApellido().concat(" ")
			// .concat(persona.getNombre()));
			cargarCiudades();
		} else {
			limpiarPersona();
			persona.setNumeroDocumento(cedula);
		}
	}

	public void comprobarTodo() {
		boolean bn = false;
		if (egreso.getCliente() == null) {
			presentaMensaje(FacesMessage.SEVERITY_ERROR,
					"ERROR: INGRESE UN CLIENTE");
			bn = true;
		}
		enviarVariableVista("errorFactura", bn);
	}

	public BigDecimal getCambio() {
		return cambio;
	}

	public String getCantidadProducto() {
		return cantidadProducto;
	}

	public CantidadFactura getCfPresentar() {
		cfPresentar = facturaCajeroService.redondearCantidadFactura(cfCalculos);
		return cfPresentar;
	}

	public String getCliente() {
		return cliente;
	}

	public String getClienteFactura() {
		return clienteFactura;
	}

	public String getCriterioBusquedaProducto() {
		return criterioBusquedaProducto;
	}

	public String getCriterioClienteBusqueda() {
		return criterioClienteBusqueda;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Factura getEgreso() {
		return egreso;
	}

	public List<ProductoPvp> getListaBusquedaProducto() {
		return listaBusquedaProducto;
	}

	public List<Ciudad> getListaCiudades() {
		return listaCiudades;
	}

	public List<Persona> getListaClienteBusqueda() {
		return listaClienteBusqueda;
	}

	public List<FacturaReporte> getListaFacturaReporte() {
		return listaFacturaReporte;
	}

	public Provincia[] getListaProvincias() {
		return Provincia.values();
	}

	public TipoPago[] getListaTiposPago() {
		return TipoPago.values();
	}

	// public List<EntradaReporte> getListaEntradaReporte() {
	// return listaEntradaReporte;
	// }

	public List<EstadoProductoVenta> getListEstadoProductoFactura() {
		return listEstadoProductoFactura;
	}

	public Integer getLocalId() {
		return localId;
	}

	public String getMayorista() {
		return mayorista;
	}

	public BigDecimal getMontoEntrada() {
		return redondearTotales(montoEntrada);
	}

	public Persona getPersona() {
		return persona;
	}

	public ProductoPvp getProductoPvp() {
		return productoPvp;
	}

	public int getRegistrosActivos() {
		return registrosActivos;
	}

	public void guardarFactura() {
		insertar();
	}

	public void ingresarCantidadProducto() {
		Producto producto = productoPvp.getProducto();
		boolean insertar = false;
		String codigo = null;
		if (cantidadProducto.length() == 1
				&& Utils.validacion(cantidadProducto, "(e|E|c|C|p|P|m|M){1,1}",
						"")) {
			codigo = String.valueOf(cantidadProducto.charAt(0));
			insertar = true;

		} else if (Utils.validacion(cantidadProducto,
				"(e|E|c|C|p|P|m|M){1,1}[0-9]+", "")) {
			codigo = String.valueOf(cantidadProducto.charAt(0));
			listaFacturaReporte.get(listaFacturaReporte.size() - 1)
					.setCantidad(
							Integer.parseInt(cantidadProducto.substring(1,
									cantidadProducto.length())));
			insertar = true;
		} else {
			presentaMensaje(FacesMessage.SEVERITY_INFO, "CANTIDAD INCORRECTA");
		}

		if (insertar) {
			listaFacturaReporte.get(listaFacturaReporte.size() - 1).setCodigo(
					producto.getCodigo());
			if (facturaCajeroService.cargarProductoLista(
					listaFacturaReporte.get(listaFacturaReporte.size() - 1),
					listaFacturaReporte, localId)) {
				FacturaReporte fr = listaFacturaReporte.get(listaFacturaReporte
						.size() - 1);

				if (codigo.compareToIgnoreCase("e") == 0)
					fr.setCantidad(fr.getCantidad() * -1);
				else if (codigo.compareToIgnoreCase("p") == 0)
					facturaCajeroService.promocion(fr);
				else if (codigo.compareToIgnoreCase("m") == 0)
					facturaCajeroService.precioMayorista(fr);

				fr.setCodigo(producto.getCodigo());
				// descripcion = fr.getDescripcion()
				// + " - $"
				// + redondearTotales(productoService
				// .obtenerPvpConImpuestos(producto));

				cambiarCantidad(fr);

				escribeCabezeraFactura(localService.obtenerObjeto("",
						new Object[] {}, false, new Object[] {}), cedula);
				// obtenerPorEstablecimiento(egreso
				// .getEstablecimiento()), cedula);
				fr = listaFacturaReporte.get(listaFacturaReporte.size() - 2);
				escribeCuerpoFactura(cedula, fr, codigo);
				RequestContext.getCurrentInstance().execute(
						"PF('dialogoCantidadProducto').hide()");
				cantidadProducto = new String();
				criterioBusquedaProducto = "";
			}
		}
	}

	@PostConstruct
	public void init() {
		cedula = SecurityContextHolder.getContext().getAuthentication()
				.getName();

		nuevaFactura(false);
		limpiarPersona();
	}

	public void insertar() {
		if (cfPresentar.getValorTotal().compareTo(new BigDecimal("20.00")) > 0
				&& egreso.getClienteFactura().getNumeroDocumento()
						.compareTo("9999999999999") == 0) {
			presentaMensaje(
					FacesMessage.SEVERITY_FATAL,
					"COMPRA MAYOR A $20.00 INGRESE UN RUC DIFERENTE A CONSUMIDOR FINAL",
					"consumidorFinal", true);
		} else if (montoEntrada.compareTo(cfPresentar.getValorTotal()) < 0) {
			presentaMensaje(FacesMessage.SEVERITY_FATAL,
					"EL MONTO INGRESADO ES MENOR AL TOTAL DE LA COMPRA",
					"consumidorFinal", true);
		} else {
			// PagoEntrada pagoEntrada = new PagoEntrada();
			// pagoEntrada.setCuota(cfPresentar.getValorTotal());
			// pagoEntrada.setFechaPago(timestampCompleto(new Date()));
			// pagoEntrada.setPagado(true);
			// pagoEntrada.setActivo(true);
			// pagoEntrada.setTipoPago(TipoPago.EF);

			Entrada entrada = new Entrada();
			entrada.setFechaPago(timestampCompleto(new Date()));
			// entrada.setFechaMora(fechaFormatoDate("01-01-1900"));
			// entrada.setFechaLimite(entrada.getFechaPago());
			// entrada.setPagado(true);
			// entrada.setActivo(true);
			// entrada.setCuota(pagoEntrada.getCuota());
			// entrada.setSaldo(newBigDecimal());
			// entrada.setCajero(empleadoService
			// .obtenerEmpleadoCargoPorCedulaAndCargo(cedula, 4));
			// entrada.setPagoEntradas(new ArrayList<PagoEntrada>());
			// entrada.addPagoEntrada(pagoEntrada);

			egreso.setFechaCierre(timestamp());
			egreso.setDescuento(newBigDecimal());
			egreso.setEntradas(new ArrayList<Entrada>());
			egreso.addEntrada(entrada);

			cambio = redondearTotales(montoEntrada.subtract(cfPresentar
					.getValorTotal()));

			int facturaId = facturaService.insertarFacturaCajero(egreso,
					listaFacturaReporte).getId();
			if (facturaId != 0) {
				escribeFinFactura(cedula, cfPresentar,
						facturaService.obtenerPorFacturaId(facturaId),
						registrosActivos,
						cfPresentar.getValorTotal().add(cambio));
				documentosElectronicosService.generarFacturaXML(facturaId,
						cfPresentar);
				nuevaFactura(false);
				RequestContext.getCurrentInstance().execute(
						"PF('buscarCliente').show()");
			} else
				escribirFactura(cedula, "\nfin");
		}

	}

	public void insertarCliente(ActionEvent actionEvent) {
		// List<DetalleCliente> list =
		// persona.getCliente().getDetalleClientes();
		// persona.getCliente()
		// .setDetalleClientes(new ArrayList<DetalleCliente>());
		// if (list != null)
		// for (DetalleCliente de : list) {
		// persona.getCliente().addDetalleClientes(de);
		// }
		// if (clienteService.insertar(persona).getCliente().getId() != null) {
		// Cliente clienteCompra = clienteService.obtenerPorCedula(
		// persona.getCedula()).getCliente();
		// Cliente clienteFactura = clienteService.obtenerPorCedula(
		// persona.getCedula()).getCliente();
		// egreso.setCliente(clienteCompra);
		// egreso.setClienteFactura(clienteFactura);
		// // persona = new Persona();
		// // persona.setCiudad(new Ciudad());
		// // persona.setCliente(new Cliente());
		// cliente = egreso.getCliente().getId().toString().concat("-")
		// .concat(egreso.getCliente().getPersona().getCedula())
		// .concat("-")
		// .concat(egreso.getCliente().getPersona().getApellido())
		// .concat(" ")
		// .concat(egreso.getCliente().getPersona().getNombre());
		// this.clienteFactura = cliente;
		// }
	}

	public void insertarProductoRapido(SelectEvent event) {
		Producto producto = (Producto) event.getObject();
		listaFacturaReporte.get(listaFacturaReporte.size() - 1).setCodigo(
				producto.getCodigo());
		facturaCajeroService.cargarProductoLista(
				listaFacturaReporte.get(listaFacturaReporte.size() - 1),
				listaFacturaReporte, localId);
		listaFacturaReporte.add(new FacturaReporte());

		presentaMensaje(FacesMessage.SEVERITY_INFO, "AGREGÓ PRODUCTO "
				+ producto.getNombre());
	}

	public void limpiarObjetosBusquedaCliente() {
		criterioClienteBusqueda = new String();
		listaClienteBusqueda = new ArrayList<Persona>();
	}

	public void limpiarPersona() {
		persona = new Persona();
		persona.setEmail("");
		persona.setTelefono("");

		persona.setCiudad(new Ciudad());
		// persona.setCliente(new Cliente());
		// persona.getCliente()
		// .setDetalleClientes(new ArrayList<DetalleCliente>());
		// persona.getCliente().setFolio(
		// String.valueOf(clienteService.contar() + 1));
	}

	public void nuevaFactura(boolean nuevaFactura) {
		// FACTURA
		egreso = new Factura();
		// egreso.setCliente(new Cliente());
		// egreso.setClienteFactura(new Cliente());
		// egreso.setVendedor(empleadoService
		// .obtenerEmpleadoCargoPorCedulaAndCargo(cedula, 5));
		Local local = localService.obtenerObjeto("", new Object[] {}, false,
				new Object[] {});
		// obtenerPorCedulaAndCargo(cedula, 4).get(0);
		egreso.setEstablecimiento(local.getCodigoEstablecimiento());
		egreso.setEntradas(new ArrayList<Entrada>());
		egreso.setDetalleFactura(new ArrayList<DetalleFactura>());

		// VARIABLES AXULIARES
		montoEntrada = newBigDecimal();
		cfCalculos = new CantidadFactura();
		cfPresentar = new CantidadFactura();
		setCliente("");
		setClienteFactura("");
		setDescripcion("");
		registrosActivos = 0;
		criterioBusquedaProducto = new String();
		localId = local.getId();

		listaFacturaReporte = new ArrayList<FacturaReporte>();
		listaFacturaReporte.add(new FacturaReporte());
		listaBusquedaProducto = new ArrayList<ProductoPvp>();
		RequestContext.getCurrentInstance().update("formBusquedaProducto");

		if (nuevaFactura)
			escribirFactura(cedula, "\nfin");
		RequestContext.getCurrentInstance().execute(
				"PF('buscarCliente').show()");
	}

	public List<String> obtenerClienteFacturaPorBusqueda(
			String criterioClienteBusqueda) {
		// List<String> lista = clienteService
		// .obtenerListaClientesAutoComplete(criterioClienteBusqueda);
		// if (lista.size() == 1) {
		// clienteFactura = (lista.get(0));
		// cargarClienteFactura();
		// }
		return null;
		// lista;
	}

	public void obtenerProducto() {
		// listaBusquedaProducto = new ArrayList<ProductoPvp>();
		// List<Producto> listaProducto = productoService.obtenerPorLocal(
		// criterioBusquedaProducto, localId);
		// for (Producto producto : listaProducto) {
		// listaBusquedaProducto.add(new ProductoPvp(producto,
		// redondearTotales(productoService
		// .obtenerPvpConImpuestos(producto))));
		// }
	}

	public void setCambio(BigDecimal cambio) {
		this.cambio = cambio;
	}

	public void setCantidadProducto(String cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}

	public void setCfPresentar(CantidadFactura cfPresentar) {
		this.cfPresentar = cfPresentar;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public void setClienteFactura(String clienteFactura) {
		this.clienteFactura = clienteFactura;
	}

	public void setCriterioBusquedaProducto(String criterioBusquedaProducto) {
		this.criterioBusquedaProducto = criterioBusquedaProducto;
	}

	public void setCriterioBusquedaRapida(String criterioBusquedaProducto) {
		this.criterioBusquedaProducto = criterioBusquedaProducto;
	}

	public void setCriterioClienteBusqueda(String criterioClienteBusqueda) {
		this.criterioClienteBusqueda = criterioClienteBusqueda;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setEgreso(Factura egreso) {
		this.egreso = egreso;
	}

	public void setListaBusquedaProducto(List<ProductoPvp> listaBusquedaProducto) {
		this.listaBusquedaProducto = listaBusquedaProducto;
	}

	public void setListaCiudades(List<Ciudad> listaCiudades) {
		this.listaCiudades = listaCiudades;
	}

	public void setListaClienteBusqueda(List<Persona> listaClienteBusqueda) {
		this.listaClienteBusqueda = listaClienteBusqueda;
	}

	public void setListaFacturaReporte(List<FacturaReporte> listaFacturaReporte) {
		this.listaFacturaReporte = listaFacturaReporte;
	}

	public void setListEstadoProductoFactura(
			List<EstadoProductoVenta> listEstadoProductoFactura) {
		this.listEstadoProductoFactura = listEstadoProductoFactura;
	}

	// public void setListaEntradaReporte(List<EntradaReporte>
	// listaEntradaReporte) {
	// this.listaEntradaReporte = listaEntradaReporte;
	// }

	public void setLocalId(Integer localId) {
		this.localId = localId;
	}

	public void setMayorista(String mayorista) {
		this.mayorista = mayorista;
	}

	public void setMontoEntrada(BigDecimal montoEntrada) {
		this.montoEntrada = montoEntrada;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public void setProductoPvp(ProductoPvp productoPvp) {
		this.productoPvp = productoPvp;
	}

	public void setRegistrosActivos(int registrosActivos) {
		this.registrosActivos = registrosActivos;
	}

}