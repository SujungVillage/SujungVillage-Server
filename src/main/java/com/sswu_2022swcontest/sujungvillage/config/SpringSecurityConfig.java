package com.sswu_2022swcontest.sujungvillage.config;

import com.sswu_2022swcontest.sujungvillage.filter.JwtAuthorizationFilter;
import com.sswu_2022swcontest.sujungvillage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;
    private final PropertyConfig propertyConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 필터 비활성화
        http.httpBasic().disable();
        http.csrf().disable();
        http.rememberMe().disable();

        // 비연결상태 설정
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // jwt 필터 추가
        http.addFilterAfter(
                new JwtAuthorizationFilter(userRepository, propertyConfig),
                BasicAuthenticationFilter.class
        );

        // 접근권한 설정
        http.authorizeRequests().antMatchers(
                "/",
                "/api/student/login", "/api/student/signup",
                "/api/admin/login").permitAll();

        http.authorizeRequests().antMatchers(
                "/api/common/**").hasAnyRole("RESIDENT", "ADMIN");

        http.authorizeRequests().antMatchers(
                "/api/student/**").hasRole("RESIDENT");

        http.authorizeRequests().antMatchers(
                "/api/admin/**").hasRole("ADMIN");

    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

}
