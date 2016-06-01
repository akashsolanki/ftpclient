package org.springframework.security.samples;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;

@ComponentScan
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer implements WebApplicationInitializer  {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
    	System.setProperty("java.awt.headless", "false");
    	
    	if(check()==null)
    	{
    	SwingControlDemo  swingControlDemo = new SwingControlDemo();   
		swingControlDemo.args = args;
		swingControlDemo.showTextFieldDemo();
    	}
    	else
    	{
    		SpringApplication springApplication = new SpringApplication(new Object[]{Application.class});
            springApplication.run(args);
    	}
    }
    private static String check() {
    	Properties prop = new Properties();
    	InputStream input = null;
    	String val = null;

    	try {

    		input = new FileInputStream("application.properties");
    		prop.load(input);
    		// set the properties value
    		val = prop.getProperty("property.root.path");
    		
    	} catch (IOException io) {
    		io.printStackTrace();
    	} finally {
    		if (input != null) {
    			try {
    				input.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}}
    	return val;
	}

	@Bean
    public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("50120KB");
        factory.setMaxRequestSize("50320KB");
        return factory.createMultipartConfig();
    }

}
