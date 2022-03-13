package com.interviewproject.userApi.dto.user;

import com.interviewproject.userApi._basicClass.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends GenericRepository<User,Long> {}
