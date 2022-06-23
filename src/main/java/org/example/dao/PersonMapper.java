package org.example.dao;


import org.example.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
//Если все поля в базе имеют такие же названия как и в классе то используется new BeanPropertyRowMapper<>(Person.class)
public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();

        person.setId(resultSet.getInt("person_id"));
        person.setFullName(resultSet.getString("fullName"));
        person.setYearOfBirth(resultSet.getInt("yearOfBirth"));


        return person;
    }
}
