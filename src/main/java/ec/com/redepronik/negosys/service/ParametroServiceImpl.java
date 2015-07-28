package ec.com.redepronik.negosys.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.redepronik.negosys.dao.ParametroDao;
import ec.com.redepronik.negosys.entity.Parametro;

@Service
public class ParametroServiceImpl extends
		GenericServiceImpl<Parametro, Integer> implements ParametroService,
		Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ParametroDao parametroDao;

	public void actualizar(Parametro parametro) {
		parametroDao.actualizar(parametro);
	}
}
