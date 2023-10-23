package sia.tacos.tacos.Ingredients;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredient {
    private String id;
    private String name;
    private Type type;
}
