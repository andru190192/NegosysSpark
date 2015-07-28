package ec.com.redepronik.negosys.service;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import ec.com.redepronik.negosys.entity.Local;

@Service
public class LocalServiceImpl extends GenericServiceImpl<Local, Integer>
		implements LocalService, Serializable {

	private static final long serialVersionUID = 1L;

	// public List<Local> obtenerActivosPorLocalCajero() {
	// List<Local> list = localDao
	// .obtenerPorHql(
	// "select distinct l from Local l left join fetch l.localBodeguero where l.activo=true "
	// + "order by l.nombre", new Object[] {});
	// return list;
	// }
	//
	// public List<Local> obtenerPorCedulaAndCargo(String cedula, int cargo) {
	// String cad = cargo == 4 ? "inner join l.localCajero lec "
	// : "inner join l.localBodeguero lec ";
	// List<Local> list = localDao
	// .obtenerPorHql(
	// "select l from Local l "
	// + cad
	// + "inner join lec.empleadoCargo ec "
	// + "inner join ec.cargo c "
	// + "inner join ec.empleado e "
	// + "inner join e.persona p "
	// +
	// "where l.activo=true and lec.activo=true and ec.actual=true and c.activo=true and e.activo=true and p.activo=true and p.cedula=?1 and c.id=?2",
	// new Object[] { cedula, cargo });
	// return list;
	// }
	//
	// public Local obtenerPorEstablecimiento(String establecimiento) {
	// Local local = localDao
	// .obtenerPorHql(
	// "select l from Local l "
	// + "where l.codigoEstablecimiento = ?1",
	// new Object[] { establecimiento }).get(0);
	// return local;
	// }
	//
	// public Local obtenerPorLocalId(Integer localId) {
	// Local local = localDao.obtenerPorHql(
	// "select distinct l from Local l "
	// + "where l.id=?1 and l.activo=true",
	// new Object[] { localId }).get(0);
	// return local;
	// }
	//
	// public List<Local> obtenerTodos() {
	// List<Local> list = localDao.obtenerPorHql(
	// "select distinct l from Local l left join fetch l.localCajero "
	// + "order by l.nombre", new Object[] {});
	// return list;
	// }
}