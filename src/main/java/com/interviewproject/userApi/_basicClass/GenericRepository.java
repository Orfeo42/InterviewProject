package com.interviewproject.userApi._basicClass;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface GenericRepository<EntityType,IdType> extends PagingAndSortingRepository<EntityType,IdType>, JpaSpecificationExecutor<EntityType> {}
