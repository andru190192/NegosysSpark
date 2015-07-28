package ec.com.redepronik.negosys.service;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ec.com.redepronik.negosys.entityAux.VolumenVentaProductoProveedor;

public interface VolumenVentaProductoProveedorService {

	@Transactional
	public List<VolumenVentaProductoProveedor> obtenerPorFechasPorProveedor(
			Integer proveedorId, Date fechaInicio, Date fechaaFin);
}
