package com.interviewproject.userApi._basicClass;

import com.interviewproject.userApi.api.exception.ApiRequestException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public abstract class CrudService<EntityType extends BasicEntity<IdType>, IdType, RequestType extends BasicRequest> {

    protected abstract GenericRepository<EntityType, IdType> getRepository();

    public List<EntityType> getAll(){
        return (List<EntityType>) getRepository().findAll();
    }
    public Page<EntityType> getAllByRequest(RequestType request){
        return getRepository().findAll(
            new GenericSpecification<>(request),
            PageRequest.of(request.getPageNo(), request.getPageSize(), request.getSort())
        );
    }

    public EntityType getById(IdType id) throws ApiRequestException {
        return getRepository().findById(id).orElseThrow(() -> new ApiRequestException("Nessun elemento trovato per l'id selezionato"));
    }

    @Transactional
    public EntityType editById(IdType id, EntityType newEntity) {
        return getRepository().findById(id).map(oldEntity -> {
            BeanUtils.copyProperties(newEntity, oldEntity, "id");
            return getRepository().save(oldEntity);
        }).orElseGet(() -> getRepository().save(newEntity));
    }

    @Transactional
    public EntityType addNew(EntityType newEntity) throws ApiRequestException {
        if (newEntity.getId() != null) throw new ApiRequestException("id non può essere valorizzato per l'inserimento");
        return getRepository().save(newEntity);
    }

    @Transactional
    public void deleteById(IdType id) throws ApiRequestException {
        EntityType item = getRepository().findById(id).orElseThrow(() -> new ApiRequestException("Nessun elemento trovato per l'id selezionato"));
        getRepository().delete(item);
    }
}
