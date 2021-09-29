package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Класс конфигурации. Позволяет достучаться до css файла, то есть /css/** -> classpath:/static/css/.
 */
@Configuration
@EnableWebMvc
public class MVCConfig implements WebMvcConfigurer {
    /**
     * Хранит регистрации обработчиков ресурсов для обслуживания статических ресурсов, таких как изображения, файлы css и другие, с помощью Spring MVC
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/");
    }
}