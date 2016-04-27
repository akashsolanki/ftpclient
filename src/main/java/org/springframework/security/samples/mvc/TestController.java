package org.springframework.security.samples.mvc;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class TestController {
	
	@RequestMapping
    public String sayHelloAgain(ModelMap model) {

		model.addAttribute("greeting", "Hello World Again, from Spring 4 MVC");
        return "user";
    }
	@RequestMapping(value="/check",method=RequestMethod.GET)
    public String check(Authentication authentication) {
        Object p = authentication.getCredentials();
        Object o =SecurityContextHolder.getContext().getAuthentication();
        return "admin1";
    }
}
