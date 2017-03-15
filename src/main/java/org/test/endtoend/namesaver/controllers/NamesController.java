package org.test.endtoend.namesaver.controllers;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.test.endtoend.namesaver.entities.Names;
import org.test.endtoend.namesaver.util.HibernateUtil;

@Controller
public class NamesController {

    public static final String NAME_APIS = "/names";

	@RequestMapping(value=NAME_APIS, method=RequestMethod.GET)
    public String getSurnames(Model model) {
		
		model.addAttribute("allsaved", HibernateUtil.getNames());
		
        return "existing";
    }
	
	@RequestMapping(value=NAME_APIS, method=RequestMethod.POST)
    public String postSurname(@ModelAttribute Names names) {
		
		HibernateUtil.saveName(names);
		
        return "saved";
    }
}
