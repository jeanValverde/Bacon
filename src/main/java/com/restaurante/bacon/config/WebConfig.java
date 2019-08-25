/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.config;

/**
 *
 * @author jean
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    //Carga los archivos estaticos del proyecto 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/css/**",
                "/fonts/**",
                "/images/**",
                "/js/**",
                "/scss/**",
                "/vendors/**",
                "/custom/**")
                .addResourceLocations(
                        "classpath:/static/css/",
                        "classpath:/static/fonts/",
                        "classpath:/static/images/",
                        "classpath:/static/js/",
                        "classpath:/static/scss/",
                        "classpath:/static/vendors/",
                        "classpath:/static/custom/");
    }
}
