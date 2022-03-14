package com.interviewproject.userApi.service;

import com.interviewproject.userApi._basicClass.CrudService;
import com.interviewproject.userApi._util.component.PasswordEncoder;
import com.interviewproject.userApi.api.exception.ApiRequestException;
import com.interviewproject.userApi.api.user.UserRequest;
import com.interviewproject.userApi.dto.user.User;
import com.interviewproject.userApi.dto.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends CrudService<User, Long, UserRequest> {

    @Autowired
    private UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected UserRepository getRepository() {
        return repository;
    }

    @Override
    public User editById(Long id, User newEntity) {
        newEntity.setPassword(passwordEncoder.encode(newEntity.getPassword()));
        return super.editById(id,newEntity);
    }

    @Override
    public User addNew(User newEntity) throws ApiRequestException {
        newEntity.setPassword(passwordEncoder.encode(newEntity.getPassword()));
        return super.addNew(newEntity);
    }
}
