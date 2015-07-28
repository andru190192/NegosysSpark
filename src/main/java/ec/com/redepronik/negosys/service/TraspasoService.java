package ec.com.redepronik.negosys.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ec.com.redepronik.negosys.entity.Traspaso;

public interface TraspasoService extends GenericService<Traspaso, Integer>{
	@Transactional
	public String insertar(Traspaso traspaso);

	@Transactional
	public List<Traspaso> obtener();
}