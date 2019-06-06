package se.nackademin.restcms.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(encoder());

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/user/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/blogadmin/me").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/blog/**").permitAll()
//
                .antMatchers(HttpMethod.POST, "/user/**").permitAll()
//                .antMatchers("/blogadmin/**").hasAnyRole("USER", "ADMIN")
//
                .antMatchers(HttpMethod.GET, "/post/**").authenticated()
                .antMatchers(HttpMethod.GET, "/post/downloadPost/**").authenticated()
//                .antMatchers("/post/**").hasAnyRole("USER", "ADMIN", "ROLE_USER", "ROLE_ADMIN")
//                .antMatchers(HttpMethod.GET, "/user").permitAll()
//                .antMatchers(HttpMethod.GET, "/blog/**").permitAll()
//                .antMatchers("/blog/**").hasAnyRole("USER", "ADMIN")
//                .antMatchers("/oauth/token").permitAll()
//                .antMatchers("/api-docs/**").permitAll()
//                .antMatchers("/**").authenticated()
//                .antMatchers("/").permitAll()
//                .antMatchers("/h2-console/**").permitAll()

                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }


    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);

    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }



}