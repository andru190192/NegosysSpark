package ec.com.redepronik.negosys.controller;

import static ec.com.redepronik.negosys.utils.UtilsAplicacion.presentaMensaje;
import static ec.com.redepronik.negosys.utils.UtilsMath.newBigDecimal;
import static ec.com.redepronik.negosys.utils.UtilsMath.redondearTotales;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ec.com.redepronik.negosys.entity.Ciudad;
import ec.com.redepronik.negosys.entity.Factura;
import ec.com.redepronik.negosys.entity.Persona;
import ec.com.redepronik.negosys.entity.Provincia;
import ec.com.redepronik.negosys.entity.Rol;
import ec.com.redepronik.negosys.entityAux.CobroFacturaReporte;
import ec.com.redepronik.negosys.entityAux.CobroFacturaReporteAuxiliar;
import ec.com.redepronik.negosys.service.CiudadService;
import ec.com.redepronik.negosys.service.FacturaService;
import ec.com.redepronik.negosys.service.PersonaService;
import ec.com.redepronik.negosys.utils.service.ReporteService;

@Controller
@Scope("session")
public class CuentasPorCobrarReporteBean {

	@Autowired
	private ReporteService reporteService;

	@Autowired
	private PersonaService personaService;

	@Autowired
	private FacturaService facturaService;

	@Autowired
	private CiudadService ciudadService;

	private List<Persona> vendedores;
	private Integer vendedor;

	private List<Ciudad> ciudades;
	private Provincia provincia;
	private Integer ciudad;

	public CuentasPorCobrarReporteBean() {
	}

	public void cargarCiudades() {
		ciudades = new ArrayList<Ciudad>();
		ciudades = ciudadService.obtenerLista(
				"select c from Ciudad c where c.provincia like ?1",
				new Object[] { provincia }, false, new Object[] {});
	}

	// private String comprobarCed(String cedulaRuc) {
	// if (comprobarCedula(cedulaRuc)) {
	// if (cedulaRuc.length() == 10)
	// return "C";
	// else if (cedulaRuc.length() == 13)
	// return "R";
	// }
	// return "NO TIENE";
	// return null;
	// }

	// private int diasVencido(Date fechaCorte, Factura egreso) {
	// List<DetalleCredito> list2 = egreso.getCredito().getDetallesCreditos();
	// for (DetalleCredito detalleCredito : list2)
	// if (!detalleCredito.getPagado())
	// if (detalleCredito.getFechaLimite().compareTo(fechaCorte) < 0)
	// return diasMora(fechaCorte, detalleCredito.getFechaLimite())
	// .intValue();
	//
	// return 0;
	// }

	// @SuppressWarnings("static-access")
	// private Date fechaCorte() {
	// Calendar cal = Calendar.getInstance();
	// int anio = cal.get(Calendar.YEAR);
	// int mes = cal.get(Calendar.MONTH);
	// int dia = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	// if (mes == 0) {
	// mes = 11;
	// anio--;
	// } else {
	// mes--;
	// cal.set(anio, mes, Calendar.DAY_OF_MONTH);
	// dia = cal.getActualMaximum(cal.DAY_OF_MONTH);
	// }
	// cal.set(anio, mes, dia);
	// return cal.getTime();
	// }

	// private List<CentralRiesgoReporte> generarCentralRiesgo() {
	// Date FEC_CORTE_SALDO = fechaCorte();
	// List<CentralRiesgoReporte> list = new ArrayList<CentralRiesgoReporte>();
	//
	// List<Factura> listaEgreso = egresoService
	// .obtenerPorCreditoAlDiaAndFechaLimite(FEC_CORTE_SALDO);
	//
	// List<Factura> listaEgresoVencido = egresoService
	// .obtenerPorCreditoVencidoAndFechaLimite(FEC_CORTE_SALDO);
	//
	// if (listaEgreso != null && !listaEgreso.isEmpty()) {
	//
	// for (int i = 0; i < listaEgreso.size(); i++)
	// for (DetalleCredito detalleCredito : listaEgreso.get(i)
	// .getCredito().getDetallesCreditos())
	// if (detalleCredito.getFechaLimite().compareTo(
	// FEC_CORTE_SALDO) > 0) {
	// listaEgreso.remove(i);
	// break;
	// }
	//
	// int morososEnviar = listaEgreso.size() * 28 / 72;
	// for (int i = 0; i <= morososEnviar; i++)
	// listaEgreso.add(listaEgresoVencido.get(i));
	//
	// for (Factura egreso : listaEgreso) {
	// CentralRiesgoReporte centralRiesgoReporteCliente = new
	// CentralRiesgoReporte();
	// Persona cliente = personaService.obtenerObjeto("",
	// new Object[] {}, false, new Object[] {});
	// // obtenerPorId(egreso
	// // .getClienteFactura().getId());
	//
	// String VAL_OPERACION = totalFactura(egreso);
	// String VAL_TOTAL = totalTotal(egreso, FEC_CORTE_SALDO);
	// String VAL_VENCIDO = totalVencido(egreso, FEC_CORTE_SALDO);
	// String VAL_XVENCER = totalXVencer(egreso, FEC_CORTE_SALDO);
	// int NUM_DIAS_VENCIDO = diasVencido(FEC_CORTE_SALDO, egreso);
	//
	// centralRiesgoReporteCliente.setCOD_TIPO_ID(comprobarCed(cliente
	// .getNumeroDocumento()));
	// centralRiesgoReporteCliente.setCOD_ID_SUJETO(cliente
	// .getNumeroDocumento());
	// centralRiesgoReporteCliente.setNOM_SUJETO(cliente
	// .getRazonSocial());
	// centralRiesgoReporteCliente
	// .setDIRECCION(cliente.getDireccion());
	// centralRiesgoReporteCliente.setCIUDAD(cliente.getCiudad()
	// .getNombre());
	// if (cliente.getTelefono() != null)
	// centralRiesgoReporteCliente.setTELEFONO(cliente
	// .getTelefono());
	// else
	// centralRiesgoReporteCliente.setTELEFONO("0994387691");
	//
	// // //////////////////////////////
	// centralRiesgoReporteCliente.setACREEDOR("ALMACENES PATTY");
	// centralRiesgoReporteCliente
	// .setFEC_CORTE_SALDO(fechaFormatoString(FEC_CORTE_SALDO));
	// centralRiesgoReporteCliente.setTIPO_DEUDOR("TITULAR");
	// centralRiesgoReporteCliente.setNUM_OPERACION(String
	// .valueOf(egreso.getId()));
	// centralRiesgoReporteCliente
	// .setFEC_CONCESION(fechaFormatoString(egreso
	// .getFechaInicio()));
	// centralRiesgoReporteCliente.setVAL_OPERACION(VAL_OPERACION);
	// centralRiesgoReporteCliente.setVAL_TOTAL(VAL_TOTAL);
	// centralRiesgoReporteCliente.setVAL_XVENCER(VAL_XVENCER);
	// centralRiesgoReporteCliente.setVAL_VENCIDO(VAL_VENCIDO);
	// centralRiesgoReporteCliente.setVAL_DEM_JUDICIAL("0,00");
	// centralRiesgoReporteCliente.setVAL_CART_CASTIGADA("0,00");
	// centralRiesgoReporteCliente
	// .setNUM_DIAS_VENCIDO(NUM_DIAS_VENCIDO);
	//
	// list.add(centralRiesgoReporteCliente);
	//
	// List<Persona> listGarantes = personaService.obtenerLista("",
	// new Object[] {}, false, new Object[] {});
	// // .obtenerGarantesPorCredito(egreso.getCredito().getId());
	// if (!listGarantes.isEmpty()) {
	// CentralRiesgoReporte centralRiesgoReporteGarante = new
	// CentralRiesgoReporte();
	// Persona garante = listGarantes.get(0);
	//
	// centralRiesgoReporteGarante
	// .setCOD_TIPO_ID(comprobarCed(garante
	// .getNumeroDocumento()));
	// centralRiesgoReporteGarante.setCOD_ID_SUJETO(garante
	// .getNumeroDocumento());
	// centralRiesgoReporteGarante.setNOM_SUJETO(garante
	// .getRazonSocial());
	// centralRiesgoReporteGarante.setDIRECCION(garante
	// .getDireccion());
	// centralRiesgoReporteGarante.setCIUDAD(garante.getCiudad()
	// .getNombre());
	// if (garante.getTelefono() != null) {
	// centralRiesgoReporteGarante.setTELEFONO(garante
	// .getTelefono());
	// if (list.get(list.indexOf(centralRiesgoReporteCliente))
	// .getTELEFONO().equalsIgnoreCase("0994387691"))
	// list.get(list.indexOf(centralRiesgoReporteCliente))
	// .setTELEFONO(garante.getTelefono());
	// } else
	// centralRiesgoReporteGarante.setTELEFONO("0994387691");
	//
	// // //////////////////////////////
	// centralRiesgoReporteGarante.setACREEDOR("ALMACENES PATTY");
	// centralRiesgoReporteGarante
	// .setFEC_CORTE_SALDO(fechaFormatoString(FEC_CORTE_SALDO));
	// centralRiesgoReporteGarante.setTIPO_DEUDOR("GARANTE");
	// centralRiesgoReporteGarante.setNUM_OPERACION(String
	// .valueOf(egreso.getId()));
	// centralRiesgoReporteGarante
	// .setFEC_CONCESION(fechaFormatoString(egreso
	// .getFechaInicio()));
	// centralRiesgoReporteGarante.setVAL_OPERACION(VAL_OPERACION);
	// centralRiesgoReporteGarante.setVAL_TOTAL(VAL_TOTAL);
	// centralRiesgoReporteGarante.setVAL_XVENCER(VAL_XVENCER);
	// centralRiesgoReporteGarante.setVAL_VENCIDO(VAL_VENCIDO);
	// centralRiesgoReporteGarante.setVAL_DEM_JUDICIAL("0,00");
	// centralRiesgoReporteGarante.setVAL_CART_CASTIGADA("0,00");
	// centralRiesgoReporteGarante
	// .setNUM_DIAS_VENCIDO(NUM_DIAS_VENCIDO);
	//
	// list.add(centralRiesgoReporteGarante);
	// }
	//
	// }
	// }
	// return list;
	// }

	// private List<CobranzaReporte> generarCobranza() {
	// List<CobranzaReporte> list = new ArrayList<CobranzaReporte>();
	// for (Factura egreso : egresoService.obtenerNoPagadosPorCiudad(ciudad)) {
	// CobranzaReporte cr = new CobranzaReporte();
	// cr.setCliente(personaService.obtenerObjeto("", new Object[] {},
	// false, new Object[] {}));
	// // obtenerPorId(egreso.getCliente()
	// // .getId()));
	// List<Persona> listaGarantes = personaService.obtenerLista("",
	// new Object[] {}, false, new Object[] {});
	// // .obtenerGarantesPorCredito(egreso.getCredito().getId());
	// if (listaGarantes != null && !listaGarantes.isEmpty())
	// cr.setGarante(personaService.obtenerObjeto("", new Object[] {},
	// false, new Object[] {}));
	// // obtenerPorId(listaGarantes.get(0)
	// // .getId()));
	//
	// if (!egreso.getDetalleFactura().isEmpty())
	// cr.setNombreProducto(egreso.getDetalleFactura().get(0)
	// .getProducto().getNombre());
	//
	// cr.setList(new ArrayList<CobranzaReporteAuxiliar>());
	// BigDecimal mora = parametro.getInteresMora();
	// for (DetalleCredito detalleCredito : egreso.getCredito()
	// .getDetallesCreditos())
	// if (!detalleCredito.getPagado()) {
	// CobranzaReporteAuxiliar cra = new CobranzaReporteAuxiliar();
	// if (compareTo(detalleCredito.getFechaLimite(), new Date()) < 0) {
	// if (compareTo(new Date(), detalleCredito.getFechaMora()) >= 0) {
	// BigDecimal moraTotal = moraTotal(new Date(),
	// detalleCredito.getFechaMora(), mora);
	// cra.setMora(multiplicarDivide(moraTotal,
	// detalleCredito.getSaldo()));
	// cra.setSubTotal(detalleCredito.getSaldo().add(
	// cra.getMora()));
	// } else {
	// cra.setMora(newBigDecimal());
	// cra.setSubTotal(detalleCredito.getSaldo());
	// }
	// cra.setFechaLimite(fechaFormatoString(detalleCredito
	// .getFechaLimite()));
	// cra.setSaldo(detalleCredito.getSaldo());
	//
	// cra.setSaldo(redondearTotales(cra.getSaldo()));
	// cra.setMora(redondearTotales(cra.getMora()));
	// cra.setSubTotal(redondearTotales(cra.getSubTotal()));
	// cr.getList().add(cra);
	// }
	// }
	// list.add(cr);
	// }
	// return list;
	// }

	public Provincia[] getProvincias() {
		return Provincia.values();
	}

	public Provincia getProvincia() {
		return provincia;
	}

	@PostConstruct
	public void init() {
		provincia = getProvincias()[0];
		cargarCiudades();
		vendedores = personaService.obtenerLista("select p from Persona p "
				+ "inner join p.permisosPersonas pp "
				+ "where pp.rol=?1 order by p.razonSocial",
				new Object[] { Rol.VEND }, false, new Object[] {});
	}

	public void reporteCentralRiesgo(ActionEvent actionEvent) {
		// List<CentralRiesgoReporte> list = generarCentralRiesgo();
		// Map<String, Object> parametro = new HashMap<String, Object>();
		// reporteService.generarReporteXLS(list, parametro, "DATOS");
	}

	public void reporteCobranzaCredito(ActionEvent actionEvent) {
		// List<CobranzaReporte> list = generarCobranza();
		// Map<String, Object> parametro = new HashMap<String, Object>();
		// parametro.put("CIUDAD",
		// ciudadService.obtenerPorId(ciudad).getNombre());
		// reporteService.generarReportePDF(list, parametro, "CobranzaCredito");
	}

	public void reporteCobranzaFactura(ActionEvent actionEvent) {
		boolean bn = false;
		List<CobroFacturaReporte> list = new ArrayList<CobroFacturaReporte>();
		BigDecimal sumTotal = newBigDecimal();
		for (Persona persona : personaService.obtenerLista("", new Object[] {},
				false, new Object[] {})) {
			// .obtenerClientesPorFacturasPendientes(ciudad.getId(),
			// vendedorId)) {
			bn = true;
			List<CobroFacturaReporteAuxiliar> list1 = new ArrayList<CobroFacturaReporteAuxiliar>();
			for (Factura e : facturaService.obtenerPorEstado(
					persona.getNumeroDocumento(), null, null, null)) {
				BigDecimal total = redondearTotales(facturaService
						.calcularTotal(e).subtract(
								facturaService.calcularEntradas(e)));

				list1.add(new CobroFacturaReporteAuxiliar(e.getFechaInicio(),
						String.valueOf(e.getId()), total));
				sumTotal = sumTotal.add(total);
			}
			list.add(new CobroFacturaReporte(persona.getId(), persona
					.getRazonSocial(), persona.getDireccion(), persona
					.getReferencia(), list1));
		}
		if (bn) {
			String nombre = "";
			if (vendedor != 0) {
				Persona p = personaService.obtenerObjeto("", new Object[] {},
						false, new Object[] {});
				// obtenerPorId(vendedorId);
				nombre = p.getRazonSocial();
			}
			Map<String, Object> parametro = new HashMap<String, Object>();
			parametro.put("cobrador", nombre);
			parametro.put("ciudad", ciudadService.obtenerPorId(ciudad)
					.getNombre());
			parametro.put("total", sumTotal);
			reporteService
					.generarReportePDF(list, parametro, "CobranzaFactura");
		} else
			presentaMensaje(FacesMessage.SEVERITY_ERROR,
					"NO TIENE FACTURAS POR COBRAR");
	}

	public List<Persona> getVendedores() {
		return vendedores;
	}

	public void setVendedores(List<Persona> vendedores) {
		this.vendedores = vendedores;
	}

	public Integer getVendedor() {
		return vendedor;
	}

	public void setVendedor(Integer vendedor) {
		this.vendedor = vendedor;
	}

	public List<Ciudad> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}

	public Integer getCiudad() {
		return ciudad;
	}

	public void setCiudad(Integer ciudad) {
		this.ciudad = ciudad;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	// private String totalFactura(Factura egreso) {
	// return redondear(egreso.getCredito().getMonto()).toString().replace(
	// '.', ',');
	// }
	//
	// private String totalTotal(Factura egreso, Date fechaCorte) {
	// BigDecimal val = newBigDecimal();
	// for (DetalleCredito detalleCredito : egreso.getCredito()
	// .getDetallesCreditos())
	// if (!detalleCredito.getPagado())
	// val = val.add(detalleCredito.getCuota());
	// return redondear(val).toString().replace('.', ',');
	// }
	//
	// private String totalVencido(Factura egreso, Date fechaCorte) {
	// BigDecimal val = newBigDecimal();
	// for (DetalleCredito detalleCredito : egreso.getCredito()
	// .getDetallesCreditos())
	// if (!detalleCredito.getPagado())
	// if (compareTo(detalleCredito.getFechaLimite(), fechaCorte) < 0)
	// val = val.add(detalleCredito.getCuota());
	// return redondear(val).toString().replace('.', ',');
	// }
	//
	// private String totalXVencer(Factura egreso, Date fechaCorte) {
	// BigDecimal val = newBigDecimal();
	// for (DetalleCredito detalleCredito : egreso.getCredito()
	// .getDetallesCreditos())
	// if (!detalleCredito.getPagado())
	// if (compareTo(detalleCredito.getFechaLimite(), fechaCorte) >= 0)
	// val = val.add(detalleCredito.getCuota());
	// return redondear(val).toString().replace('.', ',');
	// }

}