package sia.tacos.tacos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import sia.tacos.tacos.Ingredients.Ingredient;

import java.util.List;

@Data
public class Taco {
    @NotNull
    @Size(min=3, message="O nome deve conter, no mínimo, três caracteres.")
    private String name;

    @Size(min=1, message="Você deve escolher pelo menos 1 ingrediente.")
    private List<Ingredient> ingredientsOrdered;
    public void addIngredient(Ingredient ingredient){
        ingredientsOrdered.add(ingredient);
    }
}
