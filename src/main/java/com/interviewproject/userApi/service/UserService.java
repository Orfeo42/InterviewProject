package com.interviewproject.userApi.service;

import com.interviewproject.userApi._basicClass.CrudService;
import com.interviewproject.userApi._util.component.PasswordEncoder;
import com.interviewproject.userApi.api.user.UserRequest;
import com.interviewproject.userApi.dto.user.User;
import com.interviewproject.userApi.dto.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends CrudService<User, Long, UserRequest> {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected UserRepository getRepository() {
        return repository;
    }

    @Override
    public User editByID(Long id, User newEntity) {
        newEntity.setPassword(passwordEncoder.encode(newEntity.getPassword()));
        return super.editByID(id,newEntity);
    }

    @Override
    public User addNew(User newEntity) {
        newEntity.setPassword(passwordEncoder.encode(newEntity.getPassword()));
        return super.addNew(newEntity);
    }
}
