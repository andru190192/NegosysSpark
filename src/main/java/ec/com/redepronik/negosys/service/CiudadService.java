package ec.com.redepronik.negosys.service;

import org.springframework.transaction.annotation.Transactional;

import ec.com.redepronik.negosys.entity.Ciudad;
import ec.com.redepronik.negosys.entity.Provincia;

public interface CiudadService extends GenericService<Ciudad, Integer> {
	@Transactional
	public void insertar(Provincia provincia, Ciudad ciudad);

	@Transactional
	public Ciudad obtenerPorId(int id);

	@Transactional
	public Ciudad obtenerPorProvinciaCiudad(Provincia provincia, Ciudad ciudad);

}