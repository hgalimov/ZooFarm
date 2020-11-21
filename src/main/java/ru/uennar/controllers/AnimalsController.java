package ru.uennar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.uennar.dao.AnimalDAO;

@Controller
@RequestMapping("/animals")
public class AnimalsController {


    private final AnimalDAO animalDAO;

    @Autowired
    public AnimalsController(AnimalDAO animalDAO) {
        this.animalDAO = animalDAO;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("animals", animalDAO.animals());
        return "animals/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("animal", animalDAO.animal(id));
        return "animals/show";
    }
}
