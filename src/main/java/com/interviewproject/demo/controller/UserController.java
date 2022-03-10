package com.interviewproject.demo.controller;

import com.interviewproject.demo.controller._interface.CrudController;
import com.interviewproject.demo.entity.User;
import com.interviewproject.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController implements CrudController<User, Long> {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserRepository getRepository() {
        return repository;
    }

}