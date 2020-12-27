package ru.uennar.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.uennar.models.Animal;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalMapper implements RowMapper<Animal> {
    @Override
    public Animal mapRow(ResultSet rs, int i) throws SQLException {
        Animal animal = new Animal();
        animal.setId(rs.getInt("id"));
        animal.setName(rs.getString("name"));
        animal.setAge(rs.getInt("age"));
        animal.setEmailNurseryMan(rs.getString("emailNurseryMan"));
        return animal;
    }
}
