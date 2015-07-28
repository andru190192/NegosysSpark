package ec.com.redepronik.negosys.service;

import org.springframework.transaction.annotation.Transactional;

import ec.com.redepronik.negosys.entity.Grupo;

public interface GrupoService extends GenericService<Grupo, Integer> {
	@Transactional
	public boolean actualizar(Grupo grupo);

	@Transactional
	public void insertar(Grupo grupo);
}