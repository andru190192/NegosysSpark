package ec.com.redepronik.negosys.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import ec.com.redepronik.negosys.entity.Persona;

@Repository
public class PersonaDaoImpl extends GenericDaoImpl<Persona, Integer> implements
		PersonaDao, Serializable {

	private static final long serialVersionUID = 1L;
}