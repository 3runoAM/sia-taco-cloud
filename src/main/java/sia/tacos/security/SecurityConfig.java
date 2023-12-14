package sia.tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import sia.tacos.entities.Usuario;
import sia.tacos.repositories.UserRepository;
import sia.tacos.services.CustomOAuth2UserService;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

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
            Usuario user = userRepo.findByUsername(username);
            // Se o usuário existir, retorne-o
            if (user != null) return user;
            // Caso o usuário exista, lance UsernameNotFoundException
            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(auth -> {
           auth.requestMatchers("/design/**", "/orders", "/orders/current").hasRole("USER")
               .requestMatchers("/", "/**", "/oauth2/authorization/google").permitAll();
        })

        // Desabilita a proteção CSRF (Cross-Site Request Forgery) - Ataque de falsificação de solicitação entre sites
        .csrf(csrf -> csrf.disable())

        // Configura o formulário de login
        .formLogin(form -> form
            // Define a página de login
            .loginPage("/login")
            // Define a página de sucesso após o login
            .defaultSuccessUrl("/design"))

        .oauth2Login(oauth2 -> oauth2
                .loginPage("/login")
                .defaultSuccessUrl("/design")
                .userInfoEndpoint(userInfo -> userInfo
                    .oidcUserService(customOAuth2UserService)))
        .logout(logout -> logout
                // Define a página de logout
                .logoutSuccessUrl("/")
        );
        return http.build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(this.googleClientRegistration());
    }

    private ClientRegistration googleClientRegistration() {
        return CommonOAuth2Provider.GOOGLE.getBuilder("google")
                .clientId("14853626209-r23njq670v46b1gu20smkbt4eqoh7d6i.apps.googleusercontent.com")
                .clientSecret("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")
                .build();
    }
}