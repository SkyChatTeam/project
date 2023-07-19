package com.project.project.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeRequests().antMatchers("/").permitAll().and().build();
    }

    //특정 http 요청에 대한 웹 보안
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //권한에 따라 허용하는 url 설정
        // "/login", "/signup" 페이지는 모두 허용, 그 외의 페이지는 인증된 사용자만 허용함
        return http
                .authorizeHttpRequests() //인증 인가 설정
                .antMatchers("/login", "/signup").permitAll()
                .anyRequest().authenticated() //anyRequest : 설정한 url 이외의 요청 설정
                .and()

                .formLogin()  //폼 기반 login 설정
                .loginPage("/login")  //로그인 페이지 경로 설정
                .defaultSuccessUrl("/") //로그인완료되었을 때 이동 경로
                .loginProcessingUrl("/auth") //post 요청. login 창에 입력한 데이터 처리
                .usernameParameter("email") //login에 필요한 id값을 email로 설정
                .passwordParameter("password") //login에 필요한 password 값을 password로 기본설정
                .defaultSuccessUrl("/") //login에 성공하면 /로 redirect
                .and()

                .logout()  //로그아웃 설정
                .logoutUrl("/logout")
                .logoutSuccessUrl("/") //로그아웃 완료 후 이동 경로
                .and()

                .httpBasic().disable() //rest api 만을 고려하여 기본설정 해제
                .csrf().disable()  //CSRF공격 방지 위해서 활성화가 좋음
                .build();
    }
}

