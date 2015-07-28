package ec.com.redepronik.negosys.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface GenericService<T, K extends Serializable> {

	@Transactional(readOnly = true)
	List<T> obtenerLista(String consulta, Object[] valoresConsulta,
			boolean mensaje, Object[] valoresInicializar);

	@Transactional(readOnly = true)
	T obtenerObjeto(String consulta, Object[] valoresConsulta, boolean mensaje,
			Object[] valoresInicializar);
}
