package com.interviewproject.demo.controller._interface;

import com.interviewproject.demo.service._abstract.CrudService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface CrudController<EntityType, IdType> {

    CrudService<EntityType, IdType> getService();

    @GetMapping
    default ResponseEntity<Page<EntityType>> getList(){
        return new ResponseEntity<>(getService().getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    default ResponseEntity<EntityType> getByID(@PathVariable IdType id) {
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
