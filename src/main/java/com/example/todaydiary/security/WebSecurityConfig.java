package com.example.todaydiary.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.*;

import static javax.swing.text.html.FormSubmitEvent.MethodType.GET;
import static javax.swing.text.html.FormSubmitEvent.MethodType.POST;
import static org.aspectj.lang.reflect.DeclareAnnotation.Kind.Type;
import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
@EnableGlobalMethodSecurity(securedEnabled = true) // @Secured 어노테이션 활성화
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final CorsConfig corsConfig;
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
// h2-console 사용에 대한 허용 (CSRF, FrameOptions 무시)
        web
                .ignoring()
                .antMatchers("/h2-console/**",
                        "/favicon.ico",
                        "/error"
                );
    }

    @Autowired
    FailureHandler FailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
                .addFilter(corsConfig.corsFilter())
                .httpBasic().disable() // rest api 만을 고려하여 기본 설정은 해제하겠습니다.
                .csrf().disable()

                .exceptionHandling()

                // enable h2-console
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                // 세션을 사용하지 않기 때문에 STATELESS로 설정
                .and()
//                .sessionManagement()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 토큰 기반 인증이므로 세션 역시 사용하지 않습니다.
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/images/**").permitAll()
//                .antMatchers("/api/authenticate").permitAll()
//                .antMatchers("/api/signup").permitAll()
//                .anyRequest().authenticated()
                .anyRequest().permitAll() // 그외 나머지 요청은 누구나 접근 가능

                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class);





//        http.authorizeRequests()
//                .antMatchers("/**","/api/login","/api/signup","/api/**").permitAll()
//                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
//                .anyRequest().permitAll()
//                .and().cors()
////                .and().csrf().disable()
//                .and()
//                // 로그인 기능 허용
//                .formLogin()
//                .loginProcessingUrl("/api/login")
//                .failureHandler(FailureHandler)
//                .permitAll()
//                .and()
//                // 로그아웃 기능 허용
//                .logout()
//                .logoutUrl("/logout")
//                .permitAll()
//                .and()
//                .exceptionHandling()
//                .accessDeniedPage("/");
    }
//
//
//    private JwtAuthFilter jwtFilter() throws Exception {
//        List<String> skipPathList = new ArrayList<>();
//
//        // Static 정보 접근 허용
//        skipPathList.add("GET,/images/**");
//        skipPathList.add("GET,/css/**");
//
//        // h2-console 허용
//        skipPathList.add("GET,/h2-console/**");
//        skipPathList.add("POST,/h2-console/**");
//        // 회원 관리 API 허용
////        skipPathList.add("GET,/user/**");
//        skipPathList.add("POST,/user/signup");
//
//        skipPathList.add("GET,/");
//        skipPathList.add("GET,/basic.js");
//
//        skipPathList.add("GET,/favicon.ico");
//
//        FilterSkipMatcher matcher = new FilterSkipMatcher(
//                skipPathList,
//                "/**"
//        );
//
//        JwtAuthFilter filter = new JwtAuthFilter(
//                matcher,
//                headerTokenExtractor
//        );
//        filter.setAuthenticationManager(super.authenticationManagerBean());
//
//        return filter;
//    }

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("*")
                .allowedHeaders("*")
                .exposedHeaders(HttpHeaders.AUTHORIZATION)
                .allowCredentials(true);
    }

    @Bean
    public CorsConfigurationSource configurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://localhost:3000");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("*"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
        corsConfiguration.setMaxAge(3600L);
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;


    }
}