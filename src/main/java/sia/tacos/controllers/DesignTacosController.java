package sia.tacos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sia.tacos.tacos.Ingredients.Ingredient;
import sia.tacos.tacos.Ingredients.Type;
import sia.tacos.tacos.TacoOrder;
import sia.tacos.tacos.Taco;


/* Anotação do Lombok usada para criar uma Simple Logging Facade for Java (SLF4J). Uma abstração de um Log, algo como
uma lista de acontecimentos/mensagens sobre o funcionamento do sistema em tempo de execução, útil para debugging;*/
@Slf4j

/*Marca essa classe como um controller*/
@Controller

/*Define que todas as requisições para /desing serão processadas por esse controller;*/
@RequestMapping("/design")

/*Marca o objeto TacoOrder -- Pedido de Taco -- como um atributo da seção fazendo com que o pedido continue sendo perpe-
tuada na seção entre diferentes requisições. Assim o usuário pode adicionar vários tacos ao pedido*/
@SessionAttributes("tacoOrder")
public class DesignTacosController {

    /*Essa anotação indica que esse método deve ser executado antes de qualquer outro método de mapeamento, ele recebe
      uma instância de Model e a edita*/
    @ModelAttribute
    /*Recebe uma instância de Model, que é uma interface utilizada para transportar dados entre o controller e a camada
       de visualização. Nesse método criamos uma lista de Ingredients e o adiiconamos ao Model.*/
    public void addIngredientsToModel(Model model) {
        /*Criamos uma lista contendo os ingredientes que podem ser escolhidos na criação do taco*/
        List<Ingredient> ingredients = List.of(
                new Ingredient("FLTO", "Tortilha de Farinha", Type.WRAP),
                new Ingredient("COTO", "Tortilha de Milho", Type.WRAP),
                new Ingredient("GRBF", "Carne Moída", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Tomates Picados", Type.VEGGIES),
                new Ingredient("LETC", "Alface", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Molho de Tomate", Type.SAUCE),
                new Ingredient("SRCR", "Creme Azedo", Type.SAUCE)
        );
        /*Criamos uma lista que contém todo os valores de Type, ou seja, todos os tipos de ingredientes que existem*/
        Type[] types = Type.values();
        /*Editamos o Model, adicionando atributos ao modelo. Para isso iteramos sobre a lista de tipos de ingredientes.
        Cada tipo de ingrediente (WRAP, PROTEIN, VEGGIES) será um NOME DE ATRIBUTO no model e associado a cada nome de
         atributo teremos uma lista de ingrediente cadastrados, que é o retorno do método filterByType. Os atributos do
         model ficariam: "wrap": lista de wraps cadastrados [Flour Tortilla, Corn Tortilla] e assim por diante*/
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }
    /*Essa anotação indica que esse método deve ser executado antes de qualquer outro método de mapeamento e o retorno
    desse método deve ser adicionado ao Model como atributo sob nome passado em Name de tacoOrder*/
    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    /*Cria um atributo em Model chamado taco*/
    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    // Método a ser chamado quando DesignTacosController receber uma requisição GET
    @GetMapping
    public String showDesignForm(){
        return "design";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredientsListed, Type typeToCompare){
        return ingredientsListed.stream().filter(x -> x.getType().equals(typeToCompare)).collect(Collectors.toList());
    }

    @PostMapping
    public String processTaco(Taco taco, @ModelAttribute TacoOrder tacoOrder) {
        tacoOrder.addTaco(taco);
        log.info("Processando o taco: {}", taco);
        return "redirect:/orders/current";
    }
}
