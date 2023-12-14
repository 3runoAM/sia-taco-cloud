package sia.tacos.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * A classe WebConfig implementa a interface WebMvcConfigurer para personalizar a configuração baseada em Java para o Spring MVC.
 * A anotação @Configuration indica que a classe pode ser usada pelo contêiner IoC do Spring como fonte de definições de bean.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * Este método é usado para adicionar controladores de visualização ao registro.
     * A anotação @Override indica que este método está substituindo um método na superclasse.
     *
     * @param registry O registro de controladores de visualização.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login");
    }
}
