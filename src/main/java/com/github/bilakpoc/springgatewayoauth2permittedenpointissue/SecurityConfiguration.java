package com.github.bilakpoc.springgatewayoauth2permittedenpointissue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.header.XFrameOptionsServerHttpHeadersWriter;
import org.springframework.security.web.server.util.matcher.OrServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {



    @Bean
    @Order(1)
    public SecurityWebFilterChain authSpringSecurityFilterChain(final ServerHttpSecurity http) {
        return http
                .anonymous(withDefaults())
                .cors(ServerHttpSecurity.CorsSpec::disable)
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .headers(headers -> headers.frameOptions(opt -> opt.mode(XFrameOptionsServerHttpHeadersWriter.Mode.SAMEORIGIN)))
                .securityMatcher(new OrServerWebExchangeMatcher(
                        new PathPatternParserServerWebExchangeMatcher("/auth/**"),
                        new PathPatternParserServerWebExchangeMatcher("/permitted.html")))
                .build();
    }

    @Bean
    @Order(2)
    public SecurityWebFilterChain springSecurityFilterChain(final ServerHttpSecurity http) {
        return http
                .authorizeExchange(e -> e.anyExchange().authenticated())
                .cors(ServerHttpSecurity.CorsSpec::disable)
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt)
                .build();
    }
}
