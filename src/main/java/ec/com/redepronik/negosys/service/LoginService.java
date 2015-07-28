package ec.com.redepronik.negosys.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ec.com.redepronik.negosys.entity.PermisoPersona;
import ec.com.redepronik.negosys.entity.Persona;

@Service
public class LoginService implements UserDetailsService {

	@Autowired
	private PersonaService personaService;

	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Persona usuario = personaService.obtenerObjeto("select p from Persona where p.numeroDocumento=?1", new Object[] { login }, false,
				new Object[] { "getPermisosPersonas" });

		if (usuario == null)
			throw new UsernameNotFoundException("Usuario " + login + " no encontrado");

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (PermisoPersona roles : usuario.getPermisosPersonas())
			authorities.add(new SimpleGrantedAuthority(roles.getRol().getNombre()));

		return new User(usuario.getNumeroDocumento(), usuario.getPassword(), usuario.getActivo(), true, true, true, authorities);
	}
}
