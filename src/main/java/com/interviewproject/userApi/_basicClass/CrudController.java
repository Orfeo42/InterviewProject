package com.interviewproject.userApi._basicClass;

import com.interviewproject.userApi.api.exception.ApiRequestException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface CrudController<EntityType extends BasicEntity<IdType>, IdType, RequestType extends BasicRequest> {

    CrudService<EntityType, IdType, RequestType> getService();

    @GetMapping
    default ResponseEntity<Page<EntityType>> getList(@Valid @RequestBody RequestType request){
        return new ResponseEntity<>(getService().getAllByRequest(request), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    default ResponseEntity<EntityType> getById(@PathVariable IdType id) throws ApiRequestException {
        return new ResponseEntity<>(getService().getById(id), HttpStatus.OK) ;
    }

    @PutMapping(value = "/{id}")
    default ResponseEntity<EntityType> editById(@PathVariable IdType id, @Valid @RequestBody EntityType newEntity) {
        return new ResponseEntity<>(getService().editById(id,newEntity), HttpStatus.OK);
    }

    @PostMapping
    default ResponseEntity<EntityType> addNew(@RequestBody @Valid EntityType newEntity) throws ApiRequestException {
        return new ResponseEntity<>(getService().addNew(newEntity), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    default ResponseEntity<String> deleteById(@PathVariable IdType id) throws ApiRequestException {
        getService().deleteById(id);
        return new ResponseEntity<>("Elemento Eliminato Correttamente", HttpStatus.OK);
    }

}
