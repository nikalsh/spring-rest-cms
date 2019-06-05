package se.nackademin.restcms.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.*;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    static final String RESOURCE_ID = "resource_id";

    private final DefaultTokenServices tokenServices;

    private final TokenStore tokenStore;

    @Autowired
    public ResourceServerConfig(DefaultTokenServices tokenServices, TokenStore tokenStore, CustomUserDetailsService customUserDetailsService) {
        super();
        this.tokenServices = tokenServices;
        this.tokenStore = tokenStore;

    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID)
                .tokenServices(tokenServices)
                .tokenStore(tokenStore)
                .stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        //this line is needed to access h2-console with oauth2 enabled
        //disable for production
        http.headers().frameOptions().sameOrigin();
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/user/**").permitAll()
                .antMatchers(HttpMethod.GET, "/user/**").permitAll()
                .antMatchers(HttpMethod.GET, "/post/**").permitAll()
                .antMatchers(HttpMethod.GET, "/post/downloadPost/**").authenticated()
                .antMatchers(HttpMethod.GET, "/downloadFile/{fileId}").authenticated()
                .antMatchers("/").permitAll() //disable for production
                .antMatchers("/h2-console/**").permitAll() //disable for production
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }

}