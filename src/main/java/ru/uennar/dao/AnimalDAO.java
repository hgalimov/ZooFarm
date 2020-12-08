package ru.uennar.dao;

import org.springframework.stereotype.Component;
import ru.uennar.models.Animal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class AnimalDAO {
    private int count;
    private static final String URL = "jdbc:postgresql://localhost:5433/zoofarm";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Animal> animals() {
        List<Animal> animals = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM animal");
            while (rs.next()) {
                Animal animal = new Animal();
                animal.setId(rs.getInt("id"));
                animal.setName(rs.getString("name"));
                animal.setAge(rs.getInt("age"));
                animal.setEmailNurseryMan(rs.getString("emailNurseryMan"));
                animals.add(animal);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return animals;
    }

    public Animal animal(int id) {
        return null;
    }

    public void save(Animal animal) {
        try (Statement st = connection.createStatement()) {
            count++;
            String SQL = "INSERT INTO Animal VALUES(" + count + ",'" + animal.getName() +
                    "'," + animal.getAge() + ",'" + animal.getEmailNurseryMan() + "')";

            st.executeUpdate(SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(int id, Animal animal) {
       /* Animal animalToUpd = animal(id);
        animalToUpd.setName(animal.getName());
        animalToUpd.setAge(animal.getAge());
        animalToUpd.setEmailNurseryMan(animal.getEmailNurseryMan());*/
    }

    public void delete(int id) {

        /*animals.removeIf(a -> a.getId() == id);*/
    }
}
