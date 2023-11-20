package com.sparta.todocard.config;


import com.sparta.todocard.jwt.JwtAuthenticationFilter;
import com.sparta.todocard.jwt.JwtUtil;
import com.sparta.todocard.signup.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // Spring Security 지원을 가능하게 함

public class WebSecurityConfig {

    private final JwtUtil jwtUtil;
    private final AuthenticationConfiguration authenticationConfiguration;

    public WebSecurityConfig(JwtUtil jwtUtil , AuthenticationConfiguration authenticationConfiguration) {
        this.jwtUtil = jwtUtil;
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Bean //AuthenticationManager는 바로 주입받을수 없어서, AuthenticationConfiguration을 매개변수로 받아서 직접 만들어야 한다. 그래서 수동등록함.
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager(); //그리고get해와서 사용가능하도록 함
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(jwtUtil); // JwtAuthenticationFilter이 생성자로 jwtUtil을 받아서
        filter.setAuthenticationManager(authenticationManager(authenticationConfiguration)); // 빈에 등록한 authenticationManger를 set해줘야
        return filter;                                                                       // JwtAuthenticationFilter의 attemptAuthentication()메서드처럼 나중에 get해서 쓸수 있다.
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CSRF 설정
        http.csrf((csrf) -> csrf.disable());

        // 기본 설정인 Session 방식은 사용하지 않고 JWT 방식을 사용하기 위한 설정
        http.sessionManagement((sessionManagement) ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.authorizeHttpRequests((authorizeHttpRequests) ->
                authorizeHttpRequests
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // resources 접근 허용 설정
                        .requestMatchers("/api/user/**").permitAll() // '/api/user/'로 시작하는 요청 모두 접근 허가
                        .anyRequest().authenticated() // 그 외 모든 요청 인증처리
        );

        http.formLogin((formLogin) ->
                formLogin
                        .loginPage("/api/user/login-page").permitAll()
        );

        // 필터 관리(만든 필터들을 이제 어디에 위치시킬건지)
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        //접근불가 페이지
        http.exceptionHandling((exceptionHandling) ->
                exceptionHandling
                        .accessDeniedPage("/forbidden.html")
        );

        return http.build();
    }
}