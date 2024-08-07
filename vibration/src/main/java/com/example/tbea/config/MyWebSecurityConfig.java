//package com.example.tbea.config;
//
//import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//
//@Configuration
//@EnableWebSecurity
//@EnableWebFluxSecurity
//public class MyWebSecurityConfig {
//
//    @Bean
//    public SecurityWebFilterChain customSecurityFilterChain(ServerHttpSecurity http) {
//        http.authorizeExchange((exchange) -> {
//            //对静态资源的访问不需要任何认证或授权，任何人都可以访问。
//            exchange.matchers(PathRequest.toStaticResources().atCommonLocations()).permitAll();
//            // 设置 /login/tbea_user/login 路径不需要认证。
//            exchange.pathMatchers("/login/tbea_user/login").permitAll();
//            //访问路径 /foo 和 /bar 需要经过认证。
//            exchange.pathMatchers("/foo", "/bar").authenticated();
//            // 其他所有请求都需要认证。
//            exchange.anyExchange().authenticated();
//        });
//        //配置了表单登录，使用默认的登录页面和登录逻辑。
//        http.formLogin(withDefaults());
//        return http.build();
//    }
//
//}