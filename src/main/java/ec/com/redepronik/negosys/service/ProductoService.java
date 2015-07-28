package ec.com.redepronik.negosys.service;

import org.springframework.transaction.annotation.Transactional;

import ec.com.redepronik.negosys.entity.Producto;

public interface ProductoService extends GenericService<Producto, Long> {

	@Transactional
	public boolean actualizar(Producto producto);

	@Transactional
	public void insertar(Producto producto);
}