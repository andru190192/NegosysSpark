package ec.com.redepronik.negosys.controller;

import static ec.com.redepronik.negosys.utils.UtilsAplicacion.presentaMensaje;
import static ec.com.redepronik.negosys.utils.UtilsMath.newBigDecimal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ec.com.redepronik.negosys.entity.Entrada;
import ec.com.redepronik.negosys.entity.Factura;
import ec.com.redepronik.negosys.entity.TipoPago;
import ec.com.redepronik.negosys.entityAux.CobroReporte;
import ec.com.redepronik.negosys.service.FacturaService;
import ec.com.redepronik.negosys.service.PersonaService;

@Controller
@Scope("session")
public class CobroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private PersonaService personaService;

	@Autowired
	private FacturaService facturaService;

	// @Autowired
	// private PagoEntradaFacturaService pagoEntradaService;

	private String criterioBusqueda;

	private List<CobroReporte> listaFacturasPendientes;
	private List<CobroReporte> listaFacturasSeleccionados;
	private Entrada entrada;

	private CobroReporte cobroReporte;
	private Factura factura;
	// private List<PagoEntrada> listaPagoEntrada;
	private Date fechapago;
	private BigDecimal totalCuota;
	private BigDecimal totalTotal;
	private BigDecimal totalAbonos;
	private BigDecimal totalSaldo;
	private Date fechaPagoRapido;

	public CobroBean() {
		// listaFacturas = new ArrayList<CobroReporte>();
		// listaPagoEntrada = new ArrayList<PagoEntrada>();
		// pagoEntrada = new PagoEntrada();
		// pagoEntrada.setTipoPago(TipoPago.EF);
		totalCuota = newBigDecimal();
		totalTotal = newBigDecimal();
		totalSaldo = newBigDecimal();
		totalAbonos = newBigDecimal();
		factura = new Factura();
		cobroReporte = new CobroReporte();
		fechaPagoRapido = new Date();
		fechapago = new Date();
	}

	public void cargarEgreso() {
		factura = facturaService.obtenerPorCodigo(cobroReporte
				.getCodigoDocumento());
	}

	public void cargarFactura() {
		factura = facturaService.obtenerPorCodigo(cobroReporte
				.getCodigoDocumento());
	}

	public void comprobarFacturasSeleccionados() {
		RequestContext context = RequestContext.getCurrentInstance();
		boolean error = false;

		if (listaFacturasSeleccionados == null
				|| listaFacturasSeleccionados.isEmpty()) {
			presentaMensaje(FacesMessage.SEVERITY_WARN,
					"ESCOJA POR LO MENOS UNA FACTURA");
			error = true;
		}
		context.addCallbackParam("error1", error ? true : false);
	}

	// public void eliminarPagoEntrada() {
	// listaPagoEntrada.remove(pagoEntrada);
	// totalCuota = totalCuota.subtract(pagoEntrada.getCuota());
	// }

	public CobroReporte getCobroReporte() {
		return cobroReporte;
	}

	public String getCriterioBusqueda() {
		return criterioBusqueda;
	}

	public Entrada getEntrada() {
		return entrada;
	}

	public void setEntrada(Entrada entrada) {
		this.entrada = entrada;
	}

	public Factura getFactura() {
		return factura;
	}

	public Date getFechapago() {
		return fechapago;
	}

	// public List<PagoEntrada> getListaPagoEntrada() {
	// return listaPagoEntrada;
	// }

	public Date getFechaPagoRapido() {
		return fechaPagoRapido;
	}

	// public PagoEntrada getPagoEntrada() {
	// return pagoEntrada;
	// }

	public List<CobroReporte> getListaFacturasPendientes() {
		return listaFacturasPendientes;
	}

	public List<CobroReporte> getListaFacturasSeleccionados() {
		return listaFacturasSeleccionados;
	}

	public TipoPago[] getListaTiposPago() {
		return TipoPago.values();
	}

	public BigDecimal getTotalAbonos() {
		return totalAbonos;
	}

	public BigDecimal getTotalCuota() {
		return totalCuota;
	}

	public BigDecimal getTotalSaldo() {
		return totalSaldo;
	}

	public BigDecimal getTotalTotal() {
		return totalTotal;
	}

	@PostConstruct
	public void init() {
		// listaFacturasSeleccionados = new ArrayList<CobroReporte>();
	}

	public void insertarCobro() {
		RequestContext context = RequestContext.getCurrentInstance();
		boolean error = false;

		// if (listaPagoEntrada == null || listaPagoEntrada.isEmpty()) {
		// presentaMensaje(FacesMessage.SEVERITY_WARN,
		// "INGRESE POR LO MENOS UN PAGO");
		// error = true;
		// } else {
		// pagoEntradaService.cobro(listaPagoEntrada, listaFacturas,
		// listaFacturasSeleccionados);
		// listaPagoEntrada = new ArrayList<PagoEntrada>();
		totalCuota = new BigDecimal("0");
		listaFacturasSeleccionados = new ArrayList<CobroReporte>();
		totalTotal = newBigDecimal();
		totalAbonos = newBigDecimal();
		totalSaldo = newBigDecimal();
		fechapago = new Date();
		// }
		context.addCallbackParam("error", error ? true : false);
	}

	public void insertarPagoEntrada() {
		// if (pagoEntrada.getTipoPago() == null
		// || pagoEntrada.getTipoPago().getId() == 0) {
		// presentaMensaje(FacesMessage.SEVERITY_WARN,
		// "INGRESE UN TIPO DE PAGO");
		// } else if (pagoEntrada.getCuota().compareTo(new BigDecimal("0")) ==
		// 0) {
		// presentaMensaje(FacesMessage.SEVERITY_WARN, "INGRESE UN MONTO");
		// } else if (fechapago == null) {
		// presentaMensaje(FacesMessage.SEVERITY_WARN, "INGRESE UNA FECHA");
		// } else {
		// BigDecimal auxTotalCuota = totalCuota.add(pagoEntrada.getCuota());
		// if (auxTotalCuota.compareTo(totalSaldo) > 0) {
		// presentaMensaje(FacesMessage.SEVERITY_ERROR,
		// "LA SUMA DE LAS CUOTAS NO DEBE SER MAYOR AL TOTAL DEL SALDO");
		// } else {
		// pagoEntrada.setFechaPago(new Timestamp(fechapago.getTime()));
		//
		// totalCuota = auxTotalCuota;
		// listaPagoEntrada.add(pagoEntrada);
		//
		// pagoEntrada = new PagoEntrada();
		// pagoEntrada.setTipoPago(TipoPago.EF);
		// }
		// }
	}

	public void limpiar() {
		// listaPagoEntrada = new ArrayList<PagoEntrada>();
		totalCuota = newBigDecimal();
		totalTotal = newBigDecimal();
		totalSaldo = newBigDecimal();
		totalAbonos = newBigDecimal();
	}

	public void obtenerFacturasPendientes() {
		List<Factura> lista = facturaService
				.obtenerLista(
						"select distinct f from Factura f "
								+ "inner join f.cliente c "
								+ "where p.numeroDocumento=?1 "
								+ "and f.fechaCierre is null and f.activo=true order by f.fechaInicio",
						new Object[] { criterioBusqueda }, true,
						new Object[] {});

		if (lista.isEmpty()) {
			limpiar();
		} else {
			listaFacturasPendientes = new ArrayList<CobroReporte>();
			criterioBusqueda = new String();

			totalTotal = newBigDecimal();
			totalAbonos = newBigDecimal();
			totalSaldo = newBigDecimal();

			for (Factura factura : lista) {
				CobroReporte cobroReporte = new CobroReporte();

				cobroReporte.setId(factura.getId());
				cobroReporte.setNombre(factura.getCliente().getRazonSocial());
				cobroReporte.setCodigoDocumento(factura.getEstablecimiento()
						.concat("-").concat(factura.getPuntoEmision())
						.concat("-").concat(factura.getSecuencia()));
				cobroReporte.setFechaEmision(factura.getFechaInicio());
				cobroReporte.setEscogido(false);
				cobroReporte.setOrden(0);
				cobroReporte.setTotal(facturaService.calcularTotal(factura));
				cobroReporte.setAbono(facturaService.calcularEntradas(factura));
				cobroReporte.setSaldo(cobroReporte.getTotal().subtract(
						cobroReporte.getAbono()));

				listaFacturasPendientes.add(cobroReporte);
			}
		}
	}

	public void onRowSelect(SelectEvent event) {
		if (listaFacturasSeleccionados.size() > 1
				&& listaFacturasSeleccionados
						.get(0)
						.getNombre()
						.compareTo(
								listaFacturasSeleccionados.get(
										listaFacturasSeleccionados.size() - 1)
										.getNombre()) != 0) {
			listaFacturasSeleccionados
					.remove(listaFacturasSeleccionados.size() - 1);
			presentaMensaje(FacesMessage.SEVERITY_WARN,
					"SOLO PUEDE ESCOJER FACTURAS DE UN MISMO CLIENTE");
		}

		totalTotal = newBigDecimal();
		totalAbonos = newBigDecimal();
		totalSaldo = newBigDecimal();
		for (CobroReporte cr : listaFacturasSeleccionados) {
			totalTotal = totalTotal.add(cr.getTotal());
			totalAbonos = totalAbonos.add(cr.getAbono());
			totalSaldo = totalSaldo.add(cr.getSaldo());
		}

		for (CobroReporte cr1 : listaFacturasPendientes) {
			cr1.setEscogido(false);
			cr1.setOrden(0);
		}

		int orden = 1;
		for (CobroReporte cr1 : listaFacturasSeleccionados) {
			cr1.setOrden(orden++);
		}

		for (CobroReporte cr1 : listaFacturasPendientes) {
			for (CobroReporte cr2 : listaFacturasSeleccionados) {
				if (cr1.getId() == cr2.getId()) {
					cr1.setEscogido(true);
					cr1.setOrden(cr2.getOrden());
					break;
				}
			}
		}
	}

	public void onRowUnselect(UnselectEvent event) {
		totalAbonos = newBigDecimal();
		totalSaldo = newBigDecimal();
		for (CobroReporte cr : listaFacturasSeleccionados) {
			totalTotal = totalTotal.add(cr.getTotal());
			totalAbonos = totalAbonos.add(cr.getAbono());
			totalSaldo = totalSaldo.add(cr.getSaldo());
		}

		for (CobroReporte cr1 : listaFacturasPendientes) {
			cr1.setEscogido(false);
			cr1.setOrden(0);
		}

		int orden = 1;
		for (CobroReporte cr1 : listaFacturasSeleccionados) {
			cr1.setOrden(orden++);
		}

		for (CobroReporte cr1 : listaFacturasPendientes) {
			for (CobroReporte cr2 : listaFacturasSeleccionados) {
				if (cr1.getId() == cr2.getId()) {
					cr1.setEscogido(true);
					cr1.setOrden(cr2.getOrden());
					break;
				}
			}
		}
	}

	public void pagoLote() {
		// pagoEntradaService.pagoLote(listaFacturas,
		// listaFacturasSeleccionados,
		// fechaPagoRapido);
		// listaPagoEntrada = new ArrayList<PagoEntrada>();
		totalCuota = new BigDecimal("0");
		listaFacturasSeleccionados = new ArrayList<CobroReporte>();
		totalTotal = newBigDecimal();
		totalAbonos = newBigDecimal();
		totalSaldo = newBigDecimal();
		fechaPagoRapido = new Date();
	}

	public void setCobroReporte(CobroReporte cobroReporte) {
		this.cobroReporte = cobroReporte;
	}

	// public void setListaPagoEntrada(List<PagoEntrada> listaPagoEntrada) {
	// this.listaPagoEntrada = listaPagoEntrada;
	// }
	//
	// public void setPagoEntrada(PagoEntrada pagoEntrada) {
	// this.pagoEntrada = pagoEntrada;
	// }

	public void setCriterioBusqueda(String criterioBusqueda) {
		this.criterioBusqueda = criterioBusqueda;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public void setFechapago(Date fechapago) {
		this.fechapago = fechapago;
	}

	public void setFechaPagoRapido(Date fechaPagoRapido) {
		this.fechaPagoRapido = fechaPagoRapido;
	}

	public void setListaFacturasPendientes(
			List<CobroReporte> listaFacturasPendientes) {
		this.listaFacturasPendientes = listaFacturasPendientes;
	}

	public void setListaFacturasSeleccionados(
			List<CobroReporte> listaFacturasSeleccionados) {
		this.listaFacturasSeleccionados = listaFacturasSeleccionados;
	}

	public void setTotalAbonos(BigDecimal totalAbonos) {
		this.totalAbonos = totalAbonos;
	}

	public void setTotalCuota(BigDecimal totalCuota) {
		this.totalCuota = totalCuota;
	}

	public void setTotalSaldo(BigDecimal totalSaldo) {
		this.totalSaldo = totalSaldo;
	}

	public void setTotalTotal(BigDecimal totalTotal) {
		this.totalTotal = totalTotal;
	}

}