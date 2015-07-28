package ec.com.redepronik.negosys.service;

import org.springframework.transaction.annotation.Transactional;

import ec.com.redepronik.negosys.entity.Persona;
import ec.com.redepronik.negosys.entity.Rol;

public interface PersonaService extends GenericService<Persona, Integer> {

	@Transactional
	public boolean actualizar(Persona persona);

	@Transactional
	public void cambiarClave(String claveActual, String clave1, String clave2);

	@Transactional
	public String generarClave(String clave);

	@Transactional
	public void insertar(Persona persona, Rol rol);
}