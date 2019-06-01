package se.nackademin.restcms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    private final PasswordEncoder encoder;

    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public SpringSecurityConfig(PasswordEncoder encoder, UserDetailsServiceImpl userDetailsService) {
        this.encoder = encoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);

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









    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();

        http.csrf().disable();

        http.cors().and()
                .httpBasic()
                .and()
                .authorizeRequests()

//                .antMatchers(HttpMethod.OPTIONS, "/blogadmin/**").permitAll()
                .antMatchers(HttpMethod.POST, "/blogadmin/me").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/blogadmin/**").permitAll()

                .antMatchers("/blogadmin/**").hasAnyRole("USER", "ADMIN")

                .antMatchers(HttpMethod.GET, "/post/**").permitAll()
                .antMatchers("/post/**").hasAnyRole("USER", "ADMIN")

                .antMatchers(HttpMethod.GET, "/blog/**").permitAll()
                .antMatchers("/blog/**").hasAnyRole("USER", "ADMIN")


                .and()


                .formLogin().disable();


    }




}