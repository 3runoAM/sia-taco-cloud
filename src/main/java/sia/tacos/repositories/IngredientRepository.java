package sia.tacos.repositories;

import org.springframework.data.repository.CrudRepository;
import sia.tacos.entities.ingredients.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
