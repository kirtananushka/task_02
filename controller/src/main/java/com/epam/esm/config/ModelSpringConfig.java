package com.epam.esm.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Class ModelSpringConfig  for task 2.
 *
 * @author KIR TANANUSHKA
 * @version 1.0
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.epam.esm")
public class ModelSpringConfig implements WebMvcConfigurer {

}