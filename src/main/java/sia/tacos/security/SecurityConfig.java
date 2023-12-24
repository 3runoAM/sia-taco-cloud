package sia.tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.embedded.DataSourceFactory;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import sia.tacos.entities.Usuario;
import sia.tacos.repositories.UserRepository;
import sia.tacos.services.OAuth2UserService;

import javax.sql.DataSource;
import java.util.Optional;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private OAuth2UserService oauth2UserService;

    @Bean // Esse método cria um Bean do tipo PasswordEnconder
    public PasswordEncoder passwordEncoder() {
        // Utiliza a implementação de criptografia BCryp
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        // A lambda recebe o username como parametro
        return username -> {
            // Instanciamos um Usuario - que estende UserDetails - usando o repositório
            return userRepo.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' not found"));
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
        .authorizeHttpRequests(auth -> {
           auth.requestMatchers("/design/**", "/orders", "/orders/current").hasRole("USER")
               .requestMatchers("/", "/**").permitAll();
        })

        .formLogin(form -> form
            .loginPage("/login")
            .defaultSuccessUrl("/design"))

        .oauth2Login(oauth2 -> oauth2
            .loginPage("/login")
            .defaultSuccessUrl("/design")
            .userInfoEndpoint(userInfo -> userInfo
                .oidcUserService(oauth2UserService)))

        .logout(logout -> logout
            .logoutSuccessUrl("/")
        );

        return http.build();
    }

    private ClientRegistration googleClientRegistration() {
        return CommonOAuth2Provider.GOOGLE.getBuilder("google")
                .clientId("14853626209-r23njq670v46b1gu20smkbt4eqoh7d6i.apps.googleusercontent.com")
                .clientSecret("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")
                .build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(this.googleClientRegistration());
    }
}