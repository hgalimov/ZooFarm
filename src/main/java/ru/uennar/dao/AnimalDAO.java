package ru.uennar.dao;

import org.springframework.stereotype.Component;
import ru.uennar.models.Animal;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnimalDAO {
    private int count;
    private List<Animal> animals;

    {
        animals = new ArrayList<>();
        animals.add(new Animal(++count, "fennec"));
        animals.add(new Animal(++count, "hamster"));
        animals.add(new Animal(++count, "gopher"));
    }

    public List<Animal> animals() {
        return animals;
    }

    public Animal animal(int id) {
        return animals.stream().filter(animal -> animal.getId() == id).findAny().orElse(null);
    }

    public void save(Animal animal) {
        animal.setId(++count);
        animals.add(animal);
    }

    public void update(int id, Animal animal) {
        Animal animalToUpd = animal(id);
        animalToUpd.setName(animal.getName());
    }

    public void delete(int id) {
        animals.removeIf(a -> a.getId() == id);
    }
}
