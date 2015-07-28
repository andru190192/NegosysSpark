package ec.com.redepronik.negosys.controller;

import static ec.com.redepronik.negosys.utils.UtilsAplicacion.actualizarMatriz;
import static ec.com.redepronik.negosys.utils.UtilsMath.actualizar;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import ec.com.redepronik.negosys.entity.Persona;
import ec.com.redepronik.negosys.service.LocalService;
import ec.com.redepronik.negosys.service.LoginService;
import ec.com.redepronik.negosys.service.ParametroService;
import ec.com.redepronik.negosys.service.PersonaService;
import ec.com.redepronik.negosys.utils.UtilsAplicacion;

@Controller
@Scope("session")
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private PersonaService personaService;

	@Autowired
	private ParametroService parametroService;

	@Autowired
	private LocalService localService;

	@Autowired
	private LoginService loginService;

	// @Autowired
//	@ManagedProperty(value = "#{authenticationManager}")
	private AuthenticationManager authenticationManager;

	private String username;
	private String password;

	private String nombreUsuario;

	public LoginBean() {
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public String login() {
		try {
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
			Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);

			actualizar(parametroService.obtenerObjeto("from Parametro p where p.id=?1", new Object[] { 1 }, false, new Object[] {}));

			actualizarMatriz(localService
					.obtenerObjeto("select distinct l from Local l where l.matriz=true", new Object[] {}, false, new Object[] {}));

			Persona p = personaService.obtenerObjeto("select p from Persona p where p.numeroDocumento=?1 and p.activo=true",
					new Object[] { SecurityContextHolder.getContext().getAuthentication().getName() }, false, new Object[] {});
			nombreUsuario = p.getRazonSocial();

			// Date fechaCorteWS = null;
			// Boolean estadoConexion = null;
			// try {
			// if (new Socket("ws.redepronik.com.ec", 80).isConnected())
			// estadoConexion = true;
			// } catch (Exception e) {
			// estadoConexion = false;
			// }
			// if (estadoConexion) {
			// fechaCorteWS = fechaCorte(parametro.getRuc());
			// if (compareTo(fechaCorteWS, parametro.getFechaCorte()) != 0) {
			// parametro.setFechaCorte(fechaCorteWS);
			// parametroService.actualizar(parametro);
			// }
			//
			// lblBienvendida.append("BIENVENID@ " + p.getRazonSocial());
			// if (compareTo(new Date(), parametro.getFechaCorte()) <= 0) {
			// cargarMenu(menuModel, p.getNumeroDocumento());
			// String claveActual = personaService.generarClave(p
			// .getNumeroDocumento());
			// if (claveActual.compareTo(p.getPassword()) == 0)
			// redireccionar("../../views/seguridad/cambiarClaveNueva.jsf");
			// } else
			// redireccionar("../../views/seguridad/errorPago.jsf");
			// } else {
			// lblBienvendida.append("BIENVENID@ " + p.getRazonSocial());
			// if (compareTo(new Date(), parametro.getFechaCorte()) <= 0) {
			// cargarMenu(menuModel, p.getNumeroDocumento());
			// String claveActual = personaService.generarClave(p
			// .getNumeroDocumento());
			// if (claveActual.compareTo(p.getPassword()) == 0)
			// redireccionar("../../views/seguridad/cambiarClaveNueva.jsf");
			// } else
			// redireccionar("../../views/seguridad/errorPago.jsf");
			// }

			return "correcto";
		} catch (final Exception e) {
			UtilsAplicacion.presentaMensaje(FacesMessage.SEVERITY_INFO, "Usuario o contraseña incorrecta");
		}

		return null;
	}

	public String logout() {
		SecurityContextHolder.clearContext();
		return "logout";
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}