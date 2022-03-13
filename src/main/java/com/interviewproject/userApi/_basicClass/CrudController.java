package com.interviewproject.userApi._basicClass;

import com.interviewproject.userApi.api.exception.ApiRequestException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface CrudController<EntityType, IdType, RequestType extends BasicRequest> {

    CrudService<EntityType, IdType, RequestType> getService();

    @GetMapping
    default ResponseEntity<Page<EntityType>> getList(@Valid @RequestBody RequestType request){
        return new ResponseEntity<>(getService().getAll(request), HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    default ResponseEntity<EntityType> getByID(@PathVariable IdType id) throws ApiRequestException {
        return new ResponseEntity<>(getService().getByID(id), HttpStatus.OK) ;
    }

    @PutMapping(value = "{id}")
    default ResponseEntity<EntityType> editByID(@PathVariable IdType id, @Valid @RequestBody EntityType newEntity) {
        return new ResponseEntity<>(getService().editByID(id,newEntity), HttpStatus.OK);
    }

    @PostMapping
    default ResponseEntity<EntityType> addNew(@RequestBody @Valid EntityType newEntity) {
        return new ResponseEntity<>(getService().addNew(newEntity), HttpStatus.OK);
    }

}
