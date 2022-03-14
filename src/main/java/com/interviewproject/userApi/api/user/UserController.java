package com.interviewproject.userApi.api.user;

import com.interviewproject.userApi._basicClass.CrudController;
import com.interviewproject.userApi._basicClass.CrudService;
import com.interviewproject.userApi.dto.user.User;
import com.interviewproject.userApi.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController implements CrudController<User, Long, UserRequest> {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @Override
    public CrudService<User, Long, UserRequest> getService() {
        return service;
    }
}