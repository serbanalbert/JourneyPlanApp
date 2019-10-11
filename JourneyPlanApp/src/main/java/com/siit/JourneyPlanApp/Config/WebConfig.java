package com.siit.JourneyPlanApp.Config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/webjars/**",
                "/img/**",
                "/css/**",
                "/js/**",
                "/fonts/**")
                .addResourceLocations(
                        "classpath:/META-INF/resources/webjars/",
                        "classpath:/static/img/",
                        "classpath:/static/css/",
                        "classpath:/static/js/",
                        "classpath:/static/fonts/");
    }

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/addCar").setViewName("addCar");
        registry.addViewController("/newCar").setViewName("newCar");
        registry.addViewController("/addRoute").setViewName("addRoute");
        registry.addViewController("/newRoute").setViewName("newRoute");
        registry.addViewController("/addReservation").setViewName("addReservation");
        registry.addViewController("/newReservation").setViewName("newReservation");
        registry.addViewController("/addAllocation").setViewName("addAllocation");
        registry.addViewController("/listReservation").setViewName("listReservation");
        registry.addViewController("/pdf").setViewName("pdf");
        registry.addViewController("/generatePDF").setViewName("generatePDF");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
