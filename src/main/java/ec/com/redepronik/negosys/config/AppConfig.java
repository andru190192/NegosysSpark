package ec.com.redepronik.negosys.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ PersistenceConfig.class, SecurityConfig.class })
public class AppConfig {

}
