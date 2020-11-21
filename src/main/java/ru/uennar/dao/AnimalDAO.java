package ru.uennar.dao;

import org.springframework.stereotype.Component;
import ru.uennar.models.Animal;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnimalDAO {
    private List<Animal> animals;
    {
        animals = new ArrayList<>();
        animals.add(new Animal(1, "fennec"));
        animals.add(new Animal(2, "hamster"));
        animals.add(new Animal(3, "gopher"));
    }
    public List<Animal> animals(){
        return animals;
    }
    public Animal animal(int id){
        return animals.stream().filter(animal -> animal.getId() == id).findAny().orElse(null);
    }
}
