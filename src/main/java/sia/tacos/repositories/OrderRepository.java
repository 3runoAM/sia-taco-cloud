package sia.tacos.repositories;

import org.springframework.data.repository.CrudRepository;
import sia.tacos.entities.TacoOrder;
import sia.tacos.entities.Usuario;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
    List<TacoOrder> findByDeliveryZip(String deliveryZip);
    List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);
    List<TacoOrder> findByUsuarioOrderByPlacedAtDesc(Usuario user, Pageable pageable);
}
