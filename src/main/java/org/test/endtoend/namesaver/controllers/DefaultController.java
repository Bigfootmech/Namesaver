package org.test.endtoend.namesaver.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.test.endtoend.namesaver.entities.Names;

@Controller
public class DefaultController {

    public static final String DEFAULT_API = "/";

	@RequestMapping(value=DEFAULT_API, method=RequestMethod.GET)
    public String greetingForm(Model model) {
		
        model.addAttribute("names", new Names());
        
        return "form";
    }

}