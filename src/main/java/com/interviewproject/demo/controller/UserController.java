package com.interviewproject.demo.controller;

import com.interviewproject.demo.controller._interface.CrudController;
import com.interviewproject.demo.entity.User;
import com.interviewproject.demo.service.UserService;
import com.interviewproject.demo.service._abstract.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController implements CrudController<User, Long> {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @Override
    public CrudService<User, Long> getService() {
        return service;
    }
}