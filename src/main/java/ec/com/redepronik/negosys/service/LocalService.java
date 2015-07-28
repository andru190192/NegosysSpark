package ec.com.redepronik.negosys.service;

import ec.com.redepronik.negosys.entity.Local;

public interface LocalService extends GenericService<Local, Integer> {

	// @Transactional
	// public List<Local> obtener(Boolean activo);
	//
	// @Transactional
	// public List<Local> obtenerActivosPorLocalCajero();
	//
	// @Transactional
	// public List<Local> obtenerPorCedulaAndCargo(String cedula, int cargo);
	//
	// @Transactional
	// public Local obtenerPorEstablecimiento(String establecimiento);
	//
	// @Transactional
	// public Local obtenerPorLocalId(Integer localId);
	//
	// @Transactional(readOnly = true)
	// public List<Local> obtenerTodos();
}