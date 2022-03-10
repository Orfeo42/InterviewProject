package com.interviewproject.demo.repository;

import com.interviewproject.demo.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Long>, JpaSpecificationExecutor<User> {}
