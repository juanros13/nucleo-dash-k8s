package com.nucleo.dash.msvc.gateway.config.security;

import com.nucleo.dash.msvc.gateway.config.GatewayConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration {
    private static final String FRONTEND_LOCALHOST = "http://localhost:4200";
    private static final String FRONTEND_STAGING = "https://somehost.github.io";
    Logger logger = LoggerFactory.getLogger(GatewayConfiguration.class);

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.cors();
        http
                    .authorizeExchange()
                    //ALLOWING REGISTER API FOR DIRECT ACCESS
                    .pathMatchers(
                            "/user/api/v1/user/register",
                            "/user/api/v1/user/recoverypassword",
                            "/user/api/v1/user/sendmailverify",
                            "/calendar/googlesignin",
                            "/calendar",
                            "/followers"
                    ).permitAll()
                    //ALL OTHER APIS ARE AUTHENTICATED
                    .anyExchange().authenticated()
                    .and()
                    .csrf().disable()
                    .oauth2Login()
                    .and()
                    .oauth2ResourceServer()
                    .jwt();
        return http.build();
    }


}
