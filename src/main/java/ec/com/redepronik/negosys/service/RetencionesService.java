package ec.com.redepronik.negosys.service;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ec.com.redepronik.negosys.entityAux.Retenciones;

public interface RetencionesService extends
		GenericService<Retenciones, Integer> {

	@Transactional
	public List<Retenciones> obtener(Integer proveedorId, Date fechaInicio,
			Date fechaFin);
}
