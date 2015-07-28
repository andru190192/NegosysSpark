package ec.com.redepronik.negosys.service;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ec.com.redepronik.negosys.entity.Entrada;
import ec.com.redepronik.negosys.entityAux.CobroReporte;

public interface PagoEntradaFacturaService {
	@Transactional
	public String actualizar(Entrada pagoEntrada);

	@Transactional
	public void cobro(List<Entrada> listaPagoEntrada,
			List<CobroReporte> listaEgresos,
			List<CobroReporte> listaEgresosSeleccionados);

	@Transactional
	public Entrada obtenerPorPagoEntradaId(Integer pagoEntradaId);

	@Transactional
	public void pagoLote(List<CobroReporte> listaEgresos,
			List<CobroReporte> listaEgresosSeleccionados, Date fechaPagoRapido);
}