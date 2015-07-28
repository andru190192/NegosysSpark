package ec.com.redepronik.negosys.service;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ec.com.redepronik.negosys.entity.Gastos;
import ec.com.redepronik.negosys.entityAux.EgresoCaja;
import ec.com.redepronik.negosys.entityAux.GastosAux;

public interface GastoService extends GenericService<Gastos, Integer> {
	@Transactional
	public void actualizar(Gastos gasto);

	public GastosAux converirGastosGastosAux(Gastos g);

	@Transactional
	public void eliminar(Gastos gasto);

	@Transactional
	public void insertar(Gastos gasto);

	@Transactional
	public List<Gastos> obtener(Date fechaInicio, Date fechaFin);

	@Transactional
	public List<Gastos> obtener(Date criterioBusquedaFecha,
			String criterioBusquedaDescripcion, String criterioBusquedaProveedor);

	@Transactional
	public List<GastosAux> obtenerGastos(Date fechaInicio, Date fechaFin);

	@Transactional
	public List<GastosAux> obtenerGastos(Date criterioBusquedaFecha,
			String criterioBusquedaDescripcion, String criterioBusquedaProveedor);

	@Transactional
	public List<String> obtenerListaDescripcionesAutoComplete(
			String criterioProveedorBusqueda);

	@Transactional
	public Gastos obtenerPorGastosId(Integer gastoId);

	@Transactional
	public List<EgresoCaja> reporteEgreso(Date fechaInicio, Date fechaFin);

	@Transactional
	public List<EgresoCaja> reporteEgresoCaja(int cajero, Date fecha);

}