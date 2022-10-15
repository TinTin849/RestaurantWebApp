package com.tintin.restaurantwebapp.util;

import com.tintin.restaurantwebapp.models.security.Person;
import com.tintin.restaurantwebapp.services.security.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (peopleService.loadUserByUsername(person.getUsername()).isPresent()) {
            errors.rejectValue("name", "", "Человек с такими ником уже существует!");
        }
    }
}
