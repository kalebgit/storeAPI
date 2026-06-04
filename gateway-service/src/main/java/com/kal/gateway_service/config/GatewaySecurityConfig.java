package com.kal.gateway_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class GatewaySecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           CorsConfigurationSource corsConfigurationSource) throws Exception {
        http
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/actuator/**", "/auth/**", "/error").permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults()));

        // Remover OAuth2ProtectedResourceMetadataFilter — nuevo en SS7
        // Bloquea requests ANTES de que AuthorizationFilter evalúe el permitAll
        http.addFilterAfter(new jakarta.servlet.Filter() {
            @Override
            public void doFilter(jakarta.servlet.ServletRequest req,
                                 jakarta.servlet.ServletResponse res,
                                 jakarta.servlet.FilterChain chain)
                    throws java.io.IOException, jakarta.servlet.ServletException {
                chain.doFilter(req, res);
            }
        }, BearerTokenAuthenticationFilter.class);

        SecurityFilterChain chain = http.build();

        // Remover el OAuth2ProtectedResourceMetadataFilter de la cadena construida
        chain.getFilters().removeIf(f ->
                f.getClass().getSimpleName().equals("OAuth2ProtectedResourceMetadataFilter"));

        return chain;
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder
                .withJwkSetUri("http://auth-service:8085/.well-known/jwks.json")
                .build();
    }
}
