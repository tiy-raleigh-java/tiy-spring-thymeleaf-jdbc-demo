package com.theironyard.jdbc_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by doug on 9/11/17.
 */
@Controller
public class PersonsController {

    private final PersonsRepository personsRepository;

    @Autowired
    public PersonsController(PersonsRepository personsRepository) {
        this.personsRepository = personsRepository;
    }

    @GetMapping("/")
    public String listPersons(Model model, String firstName, String lastName){

        if(firstName != null || lastName != null) {
            model.addAttribute("persons", personsRepository.listPersons(firstName, lastName));
        } else {
            model.addAttribute("persons", personsRepository.listPersons());
        }

        return "persons";
    }

    @GetMapping("/addPerson")
    public String addPerson(String firstName, String lastName){

        personsRepository.addPerson(firstName, lastName);

        return "redirect:/";
    }

    @GetMapping("/editPerson")
    public String addPerson(Integer id, String firstName, String lastName){

        personsRepository.updatePerson(id, firstName, lastName);

        return "redirect:/";
    }

}
