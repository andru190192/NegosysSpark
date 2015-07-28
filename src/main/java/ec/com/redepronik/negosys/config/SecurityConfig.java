package ec.com.redepronik.negosys.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import ec.com.redepronik.negosys.service.LoginService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment env;

	@Autowired
	private LoginService loginService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()

		.authorizeRequests()

		.antMatchers("/javax.faces.resource/**", "/resources/**", "/login.jsf").permitAll()

		.antMatchers("/views/directorio/cliente.jsf").hasAnyAuthority("ADMI", "CAJA").antMatchers("/views/directorio/clienteInsertar.jsf")
				.hasAnyAuthority("ADMI", "CAJA").antMatchers("/views/directorio/clienteActualizar.jsf").hasAnyAuthority("ADMI", "CAJA")

				.antMatchers("/views/directorio/proveedor.jsf").hasAnyAuthority("ADMI", "BODE")
				.antMatchers("/views/directorio/proveedorInsertar.jsf").hasAnyAuthority("ADMI", "CAJA")
				.antMatchers("/views/directorio/proveedorActualizar.jsf").hasAnyAuthority("ADMI", "CAJA")

				.antMatchers("/views/directorio/reportes.jsf").hasAnyAuthority("ADMI")

				.antMatchers("/views/inventario/producto.jsf").hasAnyAuthority("ADMI", "BODE").antMatchers("/views/inventario/listadoIngreso.jsf")
				.hasAnyAuthority("ADMI", "BODE").antMatchers("/views/inventario/ingreso.jsf").hasAnyAuthority("BODE")
				.antMatchers("/views/inventario/kardex.jsf").hasAnyAuthority("ADMI", "CAJA", "BODE")
				.antMatchers("/views/inventario/listadoBajaInventario.jsf").hasAnyAuthority("ADMI", "BODE")
				.antMatchers("/views/inventario/bajaInventario.jsf").hasAnyAuthority("BODE").antMatchers("/views/inventario/traspasoBodega.jsf")
				.hasAnyAuthority("BODE").antMatchers("/views/inventario/reportes.jsf").hasAnyAuthority("ADMI")

				.antMatchers("/views/facturacion/listadoFactura.jsf").hasAnyAuthority("ADMI", "CAJA").antMatchers("/views/facturacion/factura.jsf")
				.hasAnyAuthority("CAJA").antMatchers("/views/facturacion/listadoNotaEntrega.jsf").hasAnyAuthority("ADMI", "CAJA")
				.antMatchers("/views/facturacion/notaEntrega.jsf").hasAnyAuthority("CAJA").antMatchers("/views/facturacion/listadoCotizacion.jsf")
				.hasAnyAuthority("ADMI", "VEND").antMatchers("/views/facturacion/cotizacion.jsf").hasAnyAuthority("VEND")
				.antMatchers("/views/facturacion/listadoPedido.jsf").hasAnyAuthority("ADMI", "VEND").antMatchers("/views/facturacion/pedido.jsf")
				.hasAnyAuthority("VEND").antMatchers("/views/facturacion/listadoNotaCredito.jsf").hasAnyAuthority("ADMI", "CAJA")
				.antMatchers("/views/facturacion/listadoDevolucionVenta.jsf").hasAnyAuthority("ADMI", "CAJA")
				.antMatchers("/views/facturacion/reportes.jsf").hasAnyAuthority("ADMI")

				.antMatchers("/views/cuentasPorPagar/pago.jsf").hasAnyAuthority("ADMI", "CAJA").antMatchers("/views/cuentasPorPagar/reportes.jsf")
				.hasAnyAuthority("ADMI", "CAJA")

				.antMatchers("/views/cuentasPorCobrar/cobro.jsf").hasAnyAuthority("ADMI", "CAJA").antMatchers("/views/cuentasPorCobrar/credito.jsf")
				.hasAnyAuthority("ADMI", "CAJA").antMatchers("/views/cuentasPorCobrar/reportes.jsf").hasAnyAuthority("ADMI", "CAJA")

				.antMatchers("/views/rrhh/persona.jsf").hasAnyAuthority("ADMI").antMatchers("/views/rrhh/nomina/cargo.jsf").hasAnyAuthority("ADMI")
				.antMatchers("/views/rrhh/nomina/empleado.jsf").hasAnyAuthority("ADMI")

				.antMatchers("/views/configuracion/parametro.jsf").hasAnyAuthority("ADMI").antMatchers("/views/seguridad/usuario/bitacora.jsf")
				.hasAnyAuthority("SEGU")

				.antMatchers("/views/seguridad/cambiarClave.jsf").access("isAuthenticated()")

				.and().formLogin().loginPage("/login.jsf")

				.and().logout().invalidateHttpSession(true).deleteCookies("JSESSIONID").logoutSuccessUrl("/")

				.and().sessionManagement().invalidSessionUrl("/login.jsf").maximumSessions(1);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// PersistenceConfig persistenceConfig = new PersistenceConfig();

		// auth.jdbcAuthentication().dataSource(persistenceConfig.dataSource())
		// .passwordEncoder(new ShaPasswordEncoder(256))
		// .usersByUsernameQuery(getUserQuery())
		// .authoritiesByUsernameQuery(getAuthoritiesQuery());
		auth.userDetailsService(loginService).passwordEncoder(new ShaPasswordEncoder(256));
	}

	// private String getAuthoritiesQuery() {
	// return "select p.\"numeroDocumento\" , pp.rol "
	// + "from negosys.personas as p, negosys.\"permisosPersonas\" as pp "
	// +
	// "where p.id = pp.persona and pp.activo=true and p.\"numeroDocumento\" = ?";
	// }

	// private String getUserQuery() {
	// return
	// "select \"numeroDocumento\", password, activo from negosys.personas "
	// + "where \"numeroDocumento\" = ?";
	// }
}
