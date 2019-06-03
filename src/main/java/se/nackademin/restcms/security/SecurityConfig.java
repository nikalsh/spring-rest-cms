package se.nackademin.restcms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import se.nackademin.restcms.crudrepositories.BlogAdminRepository;

import java.util.Arrays;
import java.util.Collections;

//@EnableJpaRepositories(basePackageClasses = BlogAdminRepository.class)
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService () {
//        UserDetails user = User.builder (). username ("user"). password (passwordEncoder (). encode ("secret")).
//                roles ("USER"). build ();
//        UserDetails userAdmin = User.builder (). username ("admin"). password (passwordEncoder (). encode ("secret")).
//                roles ("ADMIN"). build ();
//        return new InMemoryUserDetailsManager(user, userAdmin);
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder () {
//        return new BCryptPasswordEncoder();
//    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http    .cors().and()
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/","/index","/webpublico").permitAll()
//                .antMatchers("/webprivado").authenticated()
//                .antMatchers("/webadmin").hasRole("ADMIN").and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout() // Metodo get pues he desabilitado CSRF
//                .permitAll();
//    }
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//
//    private final PasswordEncoder encoder;
//
//    private final UserDetailsServiceImpl userDetailsService;
//    private RESTAuthenticationEntryPoint restAuthenticationEntryPoint;
//    @Autowired
//    private RESTAuthenticationSuccessHandler restAuthenticationSuccessHandler;
//    @Autowired
//    private RESTAuthenticationFailureHandler restAuthenticationFailureHandler;
//
//    @Autowired
//    public SecurityConfig(PasswordEncoder encoder, UserDetailsServiceImpl userDetailsService) {
//        this.encoder = encoder;
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
//
//    }
//
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }
//    @Bean
//    public CorsFilter corsFilter() {
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        final CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.setAllowedOrigins(Collections.singletonList("*"));
//        config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept"));
//        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.headers().frameOptions().disable();
//
//        http.csrf().disable();
//
//        http
//                .cors()
//                .and()
//                .csrf().disable()
//                .httpBasic()
//                .and()
//                .authorizeRequests()
//
////                .antMatchers(HttpMethod.OPTIONS, "/blogadmin/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/blogadmin/me").hasAnyRole("USER", "ADMIN")
//                .antMatchers(HttpMethod.POST, "/blogadmin/**").permitAll()
//
//                .antMatchers("/blogadmin/**").hasAnyRole("USER", "ADMIN")
//
//                .antMatchers(HttpMethod.GET, "/post/**").permitAll()
//                .antMatchers("/post/**").authenticated().anyRequest().permitAll()
//
//                .antMatchers(HttpMethod.GET, "/blog/**").permitAll()
//                .antMatchers("/blog/**").hasAnyRole("USER", "ADMIN");
//
//        http.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint);
//        http.formLogin().successHandler(restAuthenticationSuccessHandler);
//        http.formLogin().failureHandler(restAuthenticationFailureHandler);
//
//        http.logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/rest/author/all");
//
//
//    }


//}