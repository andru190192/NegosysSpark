package ec.com.redepronik.negosys.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import ec.com.redepronik.negosys.entity.Bitacora;

@Repository
public class BitacoraDaoImpl extends GenericDaoImpl<Bitacora, Integer>
		implements BitacoraDao, Serializable {

	private static final long serialVersionUID = 1L;

}
