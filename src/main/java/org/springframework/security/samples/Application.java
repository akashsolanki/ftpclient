package org.springframework.security.samples;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
    	System.setProperty("java.awt.headless", "false");
		
		SwingControlDemo  swingControlDemo = new SwingControlDemo();   
		swingControlDemo.args = args;
		
		swingControlDemo.showTextFieldDemo();
    }
    @Bean
    public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("50120KB");
        factory.setMaxRequestSize("50320KB");
        return factory.createMultipartConfig();
    }

}
