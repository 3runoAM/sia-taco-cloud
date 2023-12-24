package sia.tacos.properties;

import jakarta.validation.constraints.Max;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="taco.orders")
@Data
// Pode receber validação como qualquer outra classe
public class OrderProperties {
    @Max(value=25, message="must be between 5 and 25")
    private int pageSize = 20;
}