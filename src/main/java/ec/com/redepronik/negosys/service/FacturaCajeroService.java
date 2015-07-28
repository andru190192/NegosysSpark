package ec.com.redepronik.negosys.service;

import java.util.List;

import ec.com.redepronik.negosys.entityAux.CantidadFactura;
import ec.com.redepronik.negosys.entityAux.FacturaReporte;

public interface FacturaCajeroService {

	public CantidadFactura calcularCantidad(CantidadFactura cantidadFactura,
			FacturaReporte fr, boolean bn);

	public CantidadFactura calcularCantidadFactura(
			CantidadFactura cantidadFactura, FacturaReporte facturaReporte);

	public void calcularImporte(FacturaReporte fr);

	public boolean cargarProductoLista(FacturaReporte facturaReporte,
			List<FacturaReporte> list, int bodegaId);

	public int contarRegistrosActivos(List<FacturaReporte> facturaReporte);

	public void precioMayorista(FacturaReporte fr);

	public void promocion(FacturaReporte fr);

	public CantidadFactura redondearCantidadFactura(CantidadFactura cfc);

}
