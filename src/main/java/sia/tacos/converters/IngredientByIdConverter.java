package sia.tacos.converters;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import sia.tacos.tacos.Ingredients.*;


@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
    private Map<String, Ingredient> ingredientMap = new HashMap<>();

    public IngredientByIdConverter() {
        ingredientMap.put("FLTO", new Ingredient("FLTO", "Tortilha de Farinha", Type.WRAP));
        ingredientMap.put("COTO", new Ingredient("COTO", "Tortilha de Milho", Type.WRAP));
        ingredientMap.put("GRBF", new Ingredient("GRBF", "Carne Moída", Type.PROTEIN));
        ingredientMap.put("CARN", new Ingredient("CARN", "Carnitas", Type.PROTEIN));
        ingredientMap.put("TMTO", new Ingredient("TMTO", "Tomates Picados", Type.VEGGIES));
        ingredientMap.put("LETC", new Ingredient("LETC", "Alface", Type.VEGGIES));
        ingredientMap.put("CHED", new Ingredient("CHED", "Cheddar", Type.CHEESE));
        ingredientMap.put("JACK", new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
        ingredientMap.put("SLSA", new Ingredient("SLSA", "Molho de Tomate", Type.SAUCE));
        ingredientMap.put("SRCR", new Ingredient("SRCR", "Creme Azedo", Type.SAUCE));
    };

    @Override
    public Ingredient convert(String id) {
        return ingredientMap.get(id);
    }
}