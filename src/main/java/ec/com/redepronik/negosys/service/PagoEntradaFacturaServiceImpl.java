package ec.com.redepronik.negosys.service;

import static ec.com.redepronik.negosys.utils.UtilsDate.timestamp;
import static ec.com.redepronik.negosys.utils.UtilsDate.timestampCompleto;
import static ec.com.redepronik.negosys.utils.UtilsMath.newBigDecimal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.redepronik.negosys.entity.Entrada;
import ec.com.redepronik.negosys.entity.Factura;
import ec.com.redepronik.negosys.entityAux.CobroReporte;

@Service
public class PagoEntradaFacturaServiceImpl implements PagoEntradaFacturaService {

	// @Autowired
	// private EntradaDao entradaDao;

	@Autowired
	private FacturaService facturaService;

	@Autowired
	private PersonaService personaService;

	public String actualizar(Entrada pagoEntrada) {
		// entradaDao.actualizar(pagoEntrada);
		return "SAVE";
	}

	public void cobro(List<Entrada> listaPagoEntrada,
			List<CobroReporte> listaFacturas,
			List<CobroReporte> listaFacturasSeleccionados) {

		CobroReporte cobroReporte = null;
		Factura factura = null;
		int ic = 0;
		int ie = 0;
		BigDecimal cuota = newBigDecimal();
		BigDecimal saldo = newBigDecimal();
		do {
			// OBTENER VALORES INICIALES
			if (saldo.compareTo(newBigDecimal()) <= 0) {
				cobroReporte = listaFacturasSeleccionados.get(ie);
				factura = facturaService.obtenerPorFacturaId(cobroReporte
						.getId());
				saldo = cobroReporte.getSaldo();
			}
			if (cuota.compareTo(newBigDecimal()) == 0)
				cuota = listaPagoEntrada.get(ic).getCuota();

			// CONSTRUIR ENTRADA
			Entrada entrada = new Entrada();
			entrada.setCajero(personaService.obtenerObjeto("", new Object[] {},
					false, new Object[] {}));
			// obtenerPorDocumentoYRol(
			// SecurityContextHolder.getContext().getAuthentication()
			// .getName(), Rol.CAJA));
			// entrada.setFechaPago(pagoEntrada.getFechaPago());
			entrada.setCuota(cuota);

			// AGREGAR PAGOENTRADA->ENTRADA->EGRESO
			factura.addEntrada(entrada);

			int c = 1;
			List<Entrada> list = factura.getEntradas();
			if (list != null && !list.isEmpty())
				for (Entrada e : list)
					e.setOrden(c++);

			// DAR POR CANCELADO LA FACTURA
			if (saldo.compareTo(newBigDecimal()) <= 0) {
				factura.setFechaCierre(timestamp());
				Factura e = facturaService.obtenerPorFacturaId(factura.getId());
				e.setFechaCierre(timestamp());
				facturaService.actualizar(e);
				listaFacturas.remove(cobroReporte);
			} else {
				cobroReporte.setEscogido(false);
				cobroReporte.setOrden(0);
				cobroReporte.setTotal(facturaService.calcularTotal(factura));
				cobroReporte.setAbono(facturaService.calcularEntradas(factura));
				cobroReporte.setSaldo(cobroReporte.getTotal().subtract(
						cobroReporte.getAbono()));
			}

			// GUARDAR EL EGRESO CON SUS ENTRADAS
			// if (saldo.compareTo(newBigDecimal()) <= 0
			// || (ic == listaPagoEntrada.size() && cuota
			// .compareTo(newBigDecimal()) <= 0)) {
			// // listaFactura.add(factura);
			//
			// }

		} while (ic <= (listaPagoEntrada.size() - 1));
	}

	public Entrada obtenerPorPagoEntradaId(Integer pagoEntradaId) {
		return new Entrada();
		// pagoEntradaDao.obtenerPorId(PagoEntrada.class, pagoEntradaId);
	}

	public void pagoLote(List<CobroReporte> listaFacturas,
			List<CobroReporte> listaFacturasSeleccionados, Date fechaPagoRapido) {
		Factura factura = null;

		for (CobroReporte cr : listaFacturasSeleccionados) {
			factura = facturaService.obtenerPorFacturaId(cr.getId());
			if (factura.getEntradas() == null)
				factura.setEntradas(new ArrayList<Entrada>());

			// CONSTRUIR ENTRADA
			Entrada entrada = new Entrada();
			entrada.setFechaPago(timestamp());
			entrada.setCuota(cr.getSaldo());
			entrada.setCajero(personaService.obtenerObjeto("", new Object[] {},
					false, new Object[] {}));
			// obtenerPorDocumentoYRol(
			// SecurityContextHolder.getContext().getAuthentication()
			// .getName(), Rol.CAJA));

			factura.addEntrada(entrada);
			factura.setFechaCierre(timestampCompleto(fechaPagoRapido));
			int c = 1;
			List<Entrada> list = factura.getEntradas();
			if (list != null && !list.isEmpty())
				for (Entrada e : list)
					e.setOrden(c++);
			facturaService.actualizar(factura);
			listaFacturas.remove(cr);
		}
	}
}