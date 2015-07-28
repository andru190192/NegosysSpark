package ec.com.redepronik.negosys.service;

import static ec.com.redepronik.negosys.utils.UtilsAplicacion.presentaMensaje;
import static ec.com.redepronik.negosys.utils.UtilsAplicacion.redireccionar;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ec.com.redepronik.negosys.dao.PersonaDao;
import ec.com.redepronik.negosys.entity.PermisoPersona;
import ec.com.redepronik.negosys.entity.Persona;
import ec.com.redepronik.negosys.entity.Rol;
import ec.com.redepronik.negosys.utils.Utils;

@Service
public class PersonaServiceImpl extends GenericServiceImpl<Persona, Integer>
		implements PersonaService, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private PersonaDao personaDao;

	public boolean actualizar(Persona persona) {
		if (validar(persona))
			return personaDao.actualizar(persona);
		return false;
	}

	public void cambiarClave(String claveActual, String clave1, String clave2) {
		Persona persona = this.obtenerObjeto(
				"select p from Persona p where p.numeroDocumento=?1",
				new Object[] { SecurityContextHolder.getContext()
						.getAuthentication().getName() }, false,
				new Object[] {});
		if (claveActual.length() == 0 || clave1.length() == 0
				|| clave2.length() == 0) {
			presentaMensaje(FacesMessage.SEVERITY_INFO,
					"INGRESE TODOS LOS DATOS REQUERIDOS");
		} else if (clave1.length() < 8 || clave2.length() < 8) {
			presentaMensaje(FacesMessage.SEVERITY_INFO,
					"LA NUEVA CONTRASEÑA DEBE TENER UNA LONGITUD MINIMA DE 8 CARACTERES");
		} else if (persona.getPassword().compareTo(generarClave(claveActual)) != 0) {
			presentaMensaje(FacesMessage.SEVERITY_INFO,
					"LA CONTRASEÑA ACTUAL ES INCORRECTA");
		} else if (clave1.compareTo(clave2) != 0) {
			presentaMensaje(FacesMessage.SEVERITY_INFO,
					"LAS CONTRASEÑAS NUEVAS NO COINCIDEN");
		} else if (clave1.compareTo(persona.getNumeroDocumento()) == 0) {
			presentaMensaje(FacesMessage.SEVERITY_INFO,
					"LA CONTRASEÑA NO PUEDE SER IGUAL AL USUARIO");
		} else {
			persona.setPassword(generarClave(clave1));
			personaDao.actualizar(persona);
			presentaMensaje(FacesMessage.SEVERITY_INFO,
					"CAMBIO DE PASSWORD EXITOSO");
			redireccionar("../home.jsf");
		}
	}

	public String generarClave(String clave) {
		ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder(256);
		return shaPasswordEncoder.encodePassword(clave, null);
	}

	public void insertar(Persona persona, Rol rol) {
		PermisoPersona permisoPersona = new PermisoPersona();
		permisoPersona.setRol(rol);
		permisoPersona.setActivo(true);
		permisoPersona.setPersona(persona);
		if (persona.getPermisosPersonas() == null) {
			persona.setPermisosPersonas(new ArrayList<PermisoPersona>());
			persona.setPassword(generarClave(persona.getNumeroDocumento()));

			persona.getPermisosPersonas().add(permisoPersona);
			persona.setActivo(true);
			persona.setVisible(true);

			if (validar(persona))
				personaDao.insertar(persona);
		} else {
			persona.getPermisosPersonas().add(permisoPersona);
			actualizar(persona);
		}
	}

	public Boolean validar(Persona persona) {
		if (!Utils.validarDocumento(persona.getNumeroDocumento())) {
			presentaMensaje(FacesMessage.SEVERITY_INFO,
					"NÚMERO DE DOCUMENTO INCORRECTO");
			return false;
		} else if (!personaDao.comprobarIndiceUnico(persona, "numeroDocumento",
				persona.getNumeroDocumento())) {
			presentaMensaje(FacesMessage.SEVERITY_INFO,
					"LA CÉDULA O RUC YA EXISTEN");
			return false;
		}
		return true;
	}
}
