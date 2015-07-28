package ec.com.redepronik.negosys.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ec.com.redepronik.negosys.entity.DetalleGuiaRemision;
import ec.com.redepronik.negosys.entity.Factura;
import ec.com.redepronik.negosys.entity.GuiaRemision;
import ec.com.redepronik.negosys.entity.Persona;

public interface GuiaRemisionService extends
		GenericService<GuiaRemision, Integer> {
	@Transactional
	public void cargarCliente(List<Factura> listaFacturasGuiaRemision,
			Persona cliente);

	@Transactional
	public void cargarDetalleGuiaRemision(Persona cliente,
			List<Factura> listaFacturasGuiaRemision,
			List<Factura> listaAuxFactura,
			List<DetalleGuiaRemision> listDetalleGuiaRemision);

	@Transactional
	public boolean comprobarGuiaRemision(GuiaRemision guiaRemision,
			Persona transportista,
			List<DetalleGuiaRemision> listDetalleGuiaRemision);

	@Transactional
	public void insertar(GuiaRemision guiaRemision, Integer egresoId,
			Persona transportista);

	@Transactional
	public void insertar(GuiaRemision guiaRemision, Persona cliente,
			Persona transportista,
			List<DetalleGuiaRemision> listDetalleGuiaRemision,
			List<Factura> listaFacturasGuiaRemision);

	@Transactional
	public List<GuiaRemision> obtener(String criterioBusquedaCliente,
			String criterioBusquedaCodigo);

	@Transactional
	public GuiaRemision obtenerPorGuiaRemisionId(Long guiaRemisionId);

	@Transactional
	public void quitarDetalleGuiaRemision(List<Factura> listaAuxFactura,
			List<Factura> listaFacturasGuiaRemision,
			List<Factura> listaQuitarFacturasGuiaRemision,
			List<DetalleGuiaRemision> listDetalleGuiaRemision, Persona cliente);

}