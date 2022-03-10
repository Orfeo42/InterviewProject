package com.interviewproject.demo.db.repository;

import com.interviewproject.demo.db.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Long>, JpaSpecificationExecutor<User> {}
