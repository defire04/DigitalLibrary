package org.example.util;


import org.example.dao.PersonDao;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class PersonValidator implements Validator {

    private final PersonDao personDao;

    @Autowired
    public PersonValidator(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        if (personDao.show(person.getFullName()).isPresent()) {
            Person returnedPerson = personDao.show(person.getFullName()).get();
            if (returnedPerson.getId() != person.getId()) {
                errors.rejectValue("fullName", "", "Пользователь с таким именем уже существует");
            }
        }
    }
}
