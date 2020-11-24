package ru.uennar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.uennar.dao.AnimalDAO;
import ru.uennar.models.Animal;

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

    @GetMapping("/new")
    public String newAnimal(Model model){
        model.addAttribute("animal", new Animal());
        return "/animals/new";
    }

    @PostMapping
    public String create(@ModelAttribute("animal") Animal animal){
        animalDAO.save(animal);
        return "redirect:/animals";
    }
    @PostMapping("/crt")
    public String crt(@RequestParam("name_o") String name){
        Animal animal = new Animal();
        animal.setName(name);
        animalDAO.save(animal);
        return "redirect:/animals";
    }
}
