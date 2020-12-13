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
        PreparedStatement pstmt = null;
        Animal animal = null;
        try {
            pstmt = connection.prepareStatement("SELECT * FROM Animal WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            animal = new Animal();
            animal.setId(rs.getInt("id"));
            animal.setName(rs.getString("name"));
            animal.setAge(rs.getInt("age"));
            animal.setEmailNurseryMan(rs.getString("emailNurseryMan"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return animal;
    }

    public void save(Animal animal) {
        try {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Animal VALUES(?, ?, ?, ?)");
            pstmt.setInt(1, ++count);
            pstmt.setString(2, animal.getName());
            pstmt.setInt(3, animal.getAge());
            pstmt.setString(4, animal.getEmailNurseryMan());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(int id, Animal animal) {
        try {
            PreparedStatement pstmt = connection.prepareStatement("UPDATE Animal SET name=?, age=?, " +
                    "emailNurseryMan=? WHERE id=?");
            pstmt.setString(1, animal.getName());
            pstmt.setInt(2, animal.getAge());
            pstmt.setString(3, animal.getEmailNurseryMan());
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE Animal WHERE id=?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
