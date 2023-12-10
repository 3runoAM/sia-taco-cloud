package sia.tacos.reposiroty;

import org.springframework.data.repository.CrudRepository;
import sia.tacos.ingredients.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
