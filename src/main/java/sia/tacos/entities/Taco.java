package sia.tacos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import sia.tacos.ingredients.Ingredient;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createdAt = new Date();

    @NotNull
    @Size(min=3, message="O nome deve conter, no mínimo, três caracteres.")
    private String name;

    @Size(min=1, message="Você deve escolher pelo menos 1 ingrediente.")
    @ManyToMany()
    private List<Ingredient> ingredientsOrdered;

    public void addIngredient(Ingredient ingredient){
        ingredientsOrdered.add(ingredient);
    }
}