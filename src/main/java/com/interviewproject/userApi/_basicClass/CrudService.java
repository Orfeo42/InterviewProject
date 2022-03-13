package com.interviewproject.userApi._basicClass;

import com.interviewproject.userApi.api.exception.ApiRequestException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.transaction.Transactional;

@Service
public abstract class CrudService<EntityType, IdType, RequestType extends BasicRequest> {

    protected abstract GenericRepository<EntityType, IdType> getRepository();

    public Page<EntityType> getAll(RequestType request){
        return getRepository().findAll(
            new GenericSpecification<>(request),
            PageRequest.of(request.getPageNo(), request.getPageSize(), request.getSort())
        );
    }

    public EntityType getByID(IdType id) throws ApiRequestException {
        return getRepository().findById(id).orElseThrow(() -> new ApiRequestException("Nessun elemento trovato per l'id selezionato"));
    }

    @Transactional
    public EntityType editByID(IdType id, EntityType newEntity) {
        return getRepository().findById(id).map(oldEntity -> {
            BeanUtils.copyProperties(newEntity, oldEntity, "id");
            return getRepository().save(oldEntity);
        }).orElseGet(() -> getRepository().save(newEntity));
    }

    @Transactional
    public EntityType addNew(EntityType newEntity) {
        return getRepository().save(newEntity);
    }
}
