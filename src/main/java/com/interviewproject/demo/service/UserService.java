package com.interviewproject.demo.service;

import com.interviewproject.demo.component.PasswordEncoder;
import com.interviewproject.demo.entity.User;
import com.interviewproject.demo.repository.UserRepository;
import com.interviewproject.demo.service._abstract.CrudService;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends CrudService<User, Long> {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected PagingAndSortingRepository<User, Long> getRepository() {
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
