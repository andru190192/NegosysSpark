package ec.com.redepronik.negosys.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import ec.com.redepronik.negosys.entity.Ciudad;

@Repository
public class CiudadDaoImpl extends GenericDaoImpl<Ciudad, Integer> implements
		CiudadDao, Serializable {
	private static final long serialVersionUID = 1L;

}