package com.interviewproject.demo.service._abstract;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class CrudService<EntityType, IdType> {

    protected abstract PagingAndSortingRepository<EntityType, IdType> getRepository();

    public Page<EntityType> getAll(){
        return getRepository().findAll(PageRequest.of(0, 50));
    }

    public EntityType getByID(IdType id) {
        return getRepository().findById(id).orElse(null);
    }

    public EntityType editByID(IdType id, EntityType newEntity) {
        return getRepository().findById(id).map(oldEntity -> {
            BeanUtils.copyProperties(newEntity, oldEntity, "id");
            return getRepository().save(oldEntity);
        }).orElseGet(() -> getRepository().save(newEntity));
    }

    public EntityType addNew(EntityType newEntity) {
        return getRepository().save(newEntity);
    }
}
