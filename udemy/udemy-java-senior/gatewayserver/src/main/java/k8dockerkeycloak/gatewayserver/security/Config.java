package k8dockerkeycloak.gatewayserver.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class Config {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(
            ServerHttpSecurity http) {
        http.authorizeExchange()
                .pathMatchers("/actuator/**", "auth/**", "login/**").permitAll()
                .and().authorizeExchange().anyExchange().authenticated();
        http.csrf().disable().oauth2Login(Customizer.withDefaults());


        return http.build();
    }
}