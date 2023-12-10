package sia.tacos.entities;

import java.util.Arrays;
import java.util.Collection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


/**
 * Classe de entidade que representa um usuário.
 *
 * @Entity Anotação do JPA que indica que essa classe é uma entidade.
 * @Data Anotação do Lombok para gerar getters e setters automaticamente.
 * @NoArgsConstructor Anotação do Lombok para gerar um construtor sem argumentos.
 * @RequiredArgsConstructor Anotação do Lombok para gerar um construtor com argumentos obrigatórios.
 */
@Entity
@Data
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
@RequiredArgsConstructor
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private final String username;
    private final String password;
    private final String fullname;
    private final String street;
    private final String city;
    private final String state;
    private final String zip;
    private final String phoneNumber;

    /**
     * Retorna as autoridades concedidas ao usuário.
     *
     * @return uma coleção de autoridades concedidas ao usuário.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")); // Concede a autoridade de "ROLE_USER" ao usuário.
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}