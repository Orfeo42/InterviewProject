package com.interviewproject.demo.controller._interface;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface CrudController<Entity, ID> {

    PagingAndSortingRepository<Entity, ID> getRepository();

    @GetMapping
    default ResponseEntity<Page<Entity>> getList(){
        return new ResponseEntity<>(getRepository().findAll(PageRequest.of(0, 50)), HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    default ResponseEntity<Entity> getByID(@PathVariable ID id) {
        return new ResponseEntity<>(getRepository().findById(id).orElse(null), HttpStatus.OK) ;
    }

    @PutMapping(value = "{id}")
    default ResponseEntity<Entity> editByID(@PathVariable ID id, @RequestBody @Valid Entity newEntity) {
        Entity result = getRepository().findById(id).map(oldEntity -> {
            BeanUtils.copyProperties(newEntity, oldEntity, "id");
            return getRepository().save(oldEntity);
        }).orElseGet(() -> getRepository().save(newEntity));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    default ResponseEntity<Entity> addNew(@RequestBody @Valid Entity newEntity) {
        return new ResponseEntity<>(getRepository().save(newEntity), HttpStatus.OK);
    }

}
