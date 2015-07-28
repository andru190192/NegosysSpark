package ec.com.redepronik.negosys.service;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ec.com.redepronik.negosys.entity.DetalleIngreso;
import ec.com.redepronik.negosys.entity.Ingreso;
import ec.com.redepronik.negosys.entity.Local;

public interface IngresoService extends GenericService<Ingreso, Long> {

	@Transactional
	public Ingreso actualizar(Ingreso ingreso);

	@Transactional
	public void comprobar(Ingreso ingreso);

	@Transactional
	public void eliminar(Ingreso ingreso);

	@Transactional
	public void eliminarDetalle(Ingreso ingreso, DetalleIngreso detalleIngreso);

	@Transactional
	public boolean insertar(Ingreso ingreso, List<Local> listaBodegas);

	@Transactional
	public boolean insertarDetalle(Ingreso ingreso,
			DetalleIngreso detalleIngreso);

	@Transactional
	public List<Ingreso> obtener(Boolean activo);

	@Transactional(readOnly = true)
	public List<Ingreso> obtener(String criterioBusquedaProveedor,
			String criterioBusquedaNumeroFactura,
			Date criterioBusquedaFechaIngreso, Date criterioBusquedaFechaFactura);

	@Transactional
	public List<Ingreso> obtenerIngresosNoPagados(
			String criterioBusquedaProveedor, String criterioBusquedaDocumento,
			Date criterioBusquedafechaInicio, Date criterioBusquedafechaFin);

	@Transactional
	public Ingreso obtenerPorIngresoId(Long ingresoId);
}