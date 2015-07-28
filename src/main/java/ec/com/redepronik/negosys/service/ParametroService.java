package ec.com.redepronik.negosys.service;

import org.springframework.transaction.annotation.Transactional;

import ec.com.redepronik.negosys.entity.Parametro;

public interface ParametroService extends GenericService<Parametro, Integer> {
	@Transactional
	public void actualizar(Parametro parametro);
}
