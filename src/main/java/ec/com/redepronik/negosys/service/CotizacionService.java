package ec.com.redepronik.negosys.service;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ec.com.redepronik.negosys.entity.Cotizacion;
import ec.com.redepronik.negosys.entity.DetalleCotizacion;
import ec.com.redepronik.negosys.entityAux.CantidadFactura;
import ec.com.redepronik.negosys.entityAux.CotizacionImprimir;
import ec.com.redepronik.negosys.entityAux.FacturaReporte;

public interface CotizacionService extends GenericService<Cotizacion, Integer> {

	@Transactional
	public void actualizar(Cotizacion cotizacion);

	public FacturaReporte asignar(DetalleCotizacion de);

	public FacturaReporte asignar(DetalleCotizacion de, int localId);

	public FacturaReporte asignar(FacturaReporte facturaReporte);

	public CantidadFactura calcularCantidad(FacturaReporte facturaReporte,
			List<FacturaReporte> list);

	public CantidadFactura calcularCantidadFactura(
			CantidadFactura cantidadFactura, FacturaReporte facturaReporte);

	public CantidadFactura calcularDescuentoDolares(FacturaReporte fr,
			List<FacturaReporte> list);

	public CantidadFactura calcularDescuentoPorcentaje(FacturaReporte fr,
			List<FacturaReporte> list);

	public void calcularImporte(FacturaReporte fr);

	public CantidadFactura calcularImporte(FacturaReporte fr,
			List<FacturaReporte> list);

	public CantidadFactura calcularPrecio(FacturaReporte facturaReporte,
			List<FacturaReporte> list);

	public CantidadFactura calcularTipoPrecio(FacturaReporte facturaReporte,
			List<FacturaReporte> list);

	public CantidadFactura cambiarEstado(FacturaReporte fr,
			List<FacturaReporte> lista);

	public boolean cargarProductoLista(FacturaReporte facturaReporte,
			List<FacturaReporte> list, int localId);

	public boolean convertirListaFacturaReporteListaCotizacionDetalle(
			List<FacturaReporte> list, Cotizacion cotizacion);

	@Transactional
	public List<DetalleCotizacion> duplicarDetalleCotizacion(
			List<DetalleCotizacion> detalleCotizacions);

	@Transactional
	public void eliminar(Cotizacion cotizacion, String login, String pass);

	@Transactional
	public boolean imprimirCotizacion(int cotizacionId,
			List<CotizacionImprimir> list);

	@Transactional
	public Cotizacion insertar(Cotizacion cotizacion);

	@Transactional
	public List<Cotizacion> obtener(String criterioBusquedaCliente,
			String criterioBusquedaCodigo, String criterioBusquedaDetalle,
			Date criterioBusquedaFechaDocumento);

	@Transactional
	public Cotizacion obtenerPorCotizacionId(Integer cotizacionId);

	@Transactional
	public int posicion(FacturaReporte facturaReporte,
			List<DetalleCotizacion> detalleCotizacion);

	public List<FacturaReporte> quitarProductosCantidadCero(
			List<FacturaReporte> list);

	@Transactional
	public CantidadFactura redondearCantidadFactura(
			CantidadFactura cantidadFactura);

}
