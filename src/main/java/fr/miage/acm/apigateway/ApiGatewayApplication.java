package fr.miage.acm.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.http.HttpMethod;

@SpringBootApplication
@EnableWebFluxSecurity
public class ApiGatewayApplication {

	@Bean
	public GlobalFilter customFilter() {
		return new PreFilter();
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
		http
				.authorizeExchange(exchanges -> exchanges
						.pathMatchers(HttpMethod.OPTIONS, "/**").permitAll()
						.pathMatchers(HttpMethod.GET, "/**").permitAll()
						.pathMatchers(HttpMethod.POST, "/**").permitAll()
						.pathMatchers(HttpMethod.PUT, "/**").permitAll()
						.pathMatchers(HttpMethod.DELETE, "/**").permitAll()
						.anyExchange().permitAll()
				)
				.csrf().disable();
		return http.build();
	}
}
