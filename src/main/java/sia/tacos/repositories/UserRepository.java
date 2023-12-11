package sia.tacos.repositories;

import org.springframework.data.repository.CrudRepository;
import sia.tacos.entities.Usuario;

public interface UserRepository extends CrudRepository<Usuario, Long> {
    Usuario findByUsername(String username);
}