package ru.uennar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.uennar.models.Animal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class AnimalDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AnimalDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private int count;

    public List<Animal> animals() {
        return jdbcTemplate.query("SELECT * FROM animal", new BeanPropertyRowMapper<>(Animal.class));
    }

    public Animal show(int id) {
        return jdbcTemplate.query("SELECT * FROM Animal WHERE id = ?", new Object[]{id}, new AnimalMapper())
                .stream().findAny().orElse(null);
    }

    public void save(Animal animal) {
        jdbcTemplate.update("INSERT INTO Animal VALUES(?, ?, ?, ?)", ++count, animal.getName(), animal.getAge(),
                animal.getEmailNurseryMan());
    }

    public void update(int id, Animal animal) {
        jdbcTemplate.update("UPDATE Animal SET name=?, age=?, " +
                        "emailNurseryMan=? WHERE id=?", animal.getName(), animal.getAge(),
                animal.getEmailNurseryMan(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE Animal WHERE id=?", id);
    }
}
