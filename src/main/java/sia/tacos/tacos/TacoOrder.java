package sia.tacos.tacos;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import sia.tacos.tacos.Taco;

import java.util.ArrayList;
import java.util.List;

@Data // Anotação do Lombok que gera os métodos faltando (construtor, getters e setters, equals)
public class TacoOrder {
    @NotBlank(message="Você deve preencher esse campo.")
    private String deliveryName;
    @NotBlank(message="Você deve preencher esse campo.")
    private String deliveryStreet;
    @NotBlank(message="Você deve preencher esse campo.")
    private String deliveryCity;
    @NotBlank(message="Você deve preencher esse campo.")
    private String deliveryState;
    @NotBlank(message="Você deve preencher esse campo.")
    private String deliveryZip;
    @CreditCardNumber(message="Número de cartão inválido.")
    private String ccNumber;
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message="O formato da data deve ser MM/AA.")
    private String ccExpiration;
    @Digits(integer=3, fraction=0, message="CVV inválido.")
    private String CVV;


    private List<Taco> tacosOrdered = new ArrayList<>();
    public void addTaco(Taco taco){
        tacosOrdered.add(taco);
    }
}
