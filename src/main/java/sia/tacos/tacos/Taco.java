package sia.tacos.tacos;

import lombok.Data;
import sia.tacos.tacos.Ingredients.Ingredient;

import java.util.List;

@Data
public class Taco {
    private String name;
    private List<Ingredient> ingredientsOrdered;
    public void addIngredient(Ingredient ingredient){
        ingredientsOrdered.add(ingredient);
    }
}
