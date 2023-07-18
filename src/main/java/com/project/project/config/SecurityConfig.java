package com.cutra.clientapi.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            .httpBasic().disable()
            .csrf().disable()
            .addFilter(corsFilter())
            .sessionManagement(session -> session.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS))// jwt token으로 인증할것이므로 세션필요없으므로 생성안함.
            .formLogin().disable()
            .authorizeRequests(authorize -> authorize
                    .antMatchers("/v3/api-docs/**",
                            "/api-docs/**",
                            "/swagger-ui/**",
                            "/configuration/**",
                            "/swagger-resources/**",
                            "/v2/api-docs", "/webjars/**",
                            "/v3/api-docs/**").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().permitAll()
            )
    ;
    return http.build();
  }


  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);//내 서버가 응답을 할 때 json을 자바스크립트에서 처리할 수 있게 할지를 설정하는 것
    config.addAllowedOriginPattern("*");//모든 ip에 응답 허용
    config.addAllowedHeader("*");//모든 header에 응답 허용
    config.addAllowedMethod("*");//post,get, put, delete, patch 요청을 허용
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }

}
