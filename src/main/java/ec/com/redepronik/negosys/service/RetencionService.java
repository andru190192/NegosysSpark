package ec.com.redepronik.negosys.service;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ec.com.redepronik.negosys.entity.Retencion;

public interface RetencionService {
	@Transactional
	public String actualizar(Retencion retencion);

	@Transactional
	public Retencion insertar(Retencion retencion);

	@Transactional
	public List<Retencion> obtener();

	@Transactional
	public List<Retencion> obtener(String criterioBusquedaProveedor,
			String criterioBusquedaNumeroComprobante,
			Date criterioBusquedaFechaIngreso,
			String criterioBusquedaNumeroRetencion,
			Date criterioBusquedaFechaRetencion);

	@Transactional
	public Retencion obtenerPorId(Integer id);
}