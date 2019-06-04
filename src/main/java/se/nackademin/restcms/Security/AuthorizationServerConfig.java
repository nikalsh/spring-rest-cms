package se.nackademin.restcms.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final CustomUserDetailsService customUserDetailsService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public AuthorizationServerConfig(CustomUserDetailsService customUserDetailsService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.customUserDetailsService = customUserDetailsService;

        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer
                .inMemory()
                .withClient("android-client")
                .secret(passwordEncoder.encode("android-secret"))
                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit" )
                .scopes("read","write")
                .accessTokenValiditySeconds(360)
                .refreshTokenValiditySeconds(2160)
                .resourceIds(ResourceServerConfig.RESOURCE_ID);
    }
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .tokenServices(tokenServices())
                .authenticationManager(authenticationManager)
        .userDetailsService(customUserDetailsService);
    }
    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }
    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    @Bean
    public UserAuthenticationConverter userAuthenticationConverter() {
        DefaultUserAuthenticationConverter defaultUAC = new DefaultUserAuthenticationConverter();
        defaultUAC.setUserDetailsService(customUserDetailsService);
        return defaultUAC;
    }
    @Bean
    public AccessTokenConverter accessTokenConverter() {
        DefaultAccessTokenConverter defaultATC = new DefaultAccessTokenConverter();
        defaultATC.setUserTokenConverter(userAuthenticationConverter());
        return defaultATC;
    }

}
