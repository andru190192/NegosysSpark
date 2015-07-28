package ec.com.redepronik.negosys.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ec.com.redepronik.negosys.dao.GenericDao;

public class GenericServiceImpl<T, K extends Serializable> implements
		GenericService<T, K> {

	@Autowired
	GenericDao<T, K> genericDao;

	public List<T> obtenerLista(String consulta, Object[] valoresConsulta,
			boolean mensaje, Object[] valoresInicializar) {
		return genericDao.obtenerLista(consulta, valoresConsulta, mensaje,
				valoresInicializar);
	}

	public T obtenerObjeto(String consulta, Object[] valoresConsulta,
			boolean mensaje, Object[] valoresInicializar) {
		return genericDao.obtenerObjeto(consulta, valoresConsulta, mensaje,
				valoresInicializar);
	}
}
