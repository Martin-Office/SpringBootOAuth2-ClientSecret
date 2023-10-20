package willydekeyser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class SpringBootOauth2AuthorizationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootOauth2AuthorizationServerApplication.class, args);
	}

}
