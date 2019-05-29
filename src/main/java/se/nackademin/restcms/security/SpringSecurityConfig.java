package se.nackademin.restcms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

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
        auth.userDetailsService((userDetailsService)).passwordEncoder(encoder);

    }

// regular in-memory auth
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user").password("{noop}user").roles("USER")
//                .and()
//                .withUser("admin").password("{noop}admin").roles("USER", "ADMIN");
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();


        http
                .httpBasic()
                .and()
                .authorizeRequests()


                .antMatchers(HttpMethod.POST, "/blogadmin/**").permitAll()
                .antMatchers("/blogadmin/**").hasAnyRole("USER", "ADMIN")

                .antMatchers(HttpMethod.GET, "/post/**").permitAll()
                .antMatchers("/post/**").hasAnyRole("USER", "ADMIN")

                .antMatchers(HttpMethod.GET, "/blog/**").permitAll()
                .antMatchers("/blog/**").hasAnyRole("USER", "ADMIN")


                .and()

                .csrf().disable()
                .formLogin().disable();
    }


}