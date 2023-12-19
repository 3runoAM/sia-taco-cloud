package sia.tacos.repositories;

import org.springframework.data.repository.CrudRepository;
import sia.tacos.entities.Usuario;

import java.util.Optional;

public interface UserRepository extends CrudRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}