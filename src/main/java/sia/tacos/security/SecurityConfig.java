package sia.tacos.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import sia.tacos.entities.Usuario;
import sia.tacos.repositories.UserRepository;

@Configuration
public class SecurityConfig {
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
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
           auth.requestMatchers("/design", "/orders").hasRole("USER")
                   .requestMatchers("/", "/register").permitAll();
        });
        return http.build();
    }
}