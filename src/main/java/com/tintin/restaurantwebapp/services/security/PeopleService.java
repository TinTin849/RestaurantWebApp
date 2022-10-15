package com.tintin.restaurantwebapp.services.security;

import com.tintin.restaurantwebapp.models.security.Person;
import com.tintin.restaurantwebapp.repositories.security.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Optional<Person> loadUserByUsername(String username) {
        return peopleRepository.findByUsername(username);
    }
}
