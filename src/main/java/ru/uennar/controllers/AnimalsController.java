package ru.uennar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.uennar.dao.AnimalDAO;
import ru.uennar.models.Animal;

import javax.validation.Valid;

@Controller
@RequestMapping("/animals")
public class AnimalsController {


    private final AnimalDAO animalDAO;

    @Autowired
    public AnimalsController(AnimalDAO animalDAO) {
        this.animalDAO = animalDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("animals", animalDAO.animals());
        return "animals/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("animal", animalDAO.animal(id));
        return "animals/show";
    }

    @GetMapping("/new")
    public String newAnimal(Model model) {
        model.addAttribute("animal", new Animal());
        return "/animals/new";
    }

    @PostMapping
    public String create(@ModelAttribute("animal") @Valid Animal animal,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "/animals/new";
        }
        animalDAO.save(animal);
        return "redirect:/animals";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("animal", animalDAO.animal(id));
        return "animals/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("animal") @Valid Animal animal, BindingResult bindingResult,
                         @PathVariable("id") int id){
        if (bindingResult.hasErrors()){
            return "animals/edit";
        }
        animalDAO.update(id, animal);
        return "redirect:/animals";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        animalDAO.delete(id);
        return "redirect:/animals";
    }
}
