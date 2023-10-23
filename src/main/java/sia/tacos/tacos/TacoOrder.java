package sia.tacos.tacos;

import lombok.Data;
import sia.tacos.tacos.Taco;

import java.util.ArrayList;
import java.util.List;

@Data // Anotação do Lombok que gera os métodos faltando (construtor, getters e setters, equals)
public class TacoOrder {
    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;
    private String ccNumber;
    private String ccExpiration;
    private String CVV;
    private List<Taco> tacosOrdered = new ArrayList<>();
    public void addTaco(Taco taco){
        tacosOrdered.add(taco);
    }
}
