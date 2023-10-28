package sia.tacos.Reposiroty;

import org.springframework.data.repository.CrudRepository;
import sia.tacos.tacos.Ingredients.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
