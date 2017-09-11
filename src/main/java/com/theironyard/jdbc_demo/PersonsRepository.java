package com.theironyard.jdbc_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonsRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> listPersons(){
        return jdbcTemplate.query("SELECT * FROM persons", (resultSet, rowNum) -> new Person(
                resultSet.getInt("id"),
                resultSet.getString("firstname"),
                resultSet.getString("lastname")
                )
        );
    }

    public Object listPersons(String firstName, String lastName) {
        return jdbcTemplate.query("SELECT * FROM persons WHERE firstname = ? OR lastname = ?", (resultSet, rowNum) -> new Person(
                        resultSet.getInt("id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname")
                ), firstName, lastName
                );
    }

    public void addPerson(String firstName, String lastName){
        jdbcTemplate.update("INSERT INTO persons (firstname, lastname) VALUES (?, ?)", firstName, lastName);
    }

    public void updatePerson(Integer id, String firstName, String lastName) {
        jdbcTemplate.update("UPDATE persons SET firstname = ?, lastname = ? WHERE id = ?", firstName, lastName, id);
    }
}
