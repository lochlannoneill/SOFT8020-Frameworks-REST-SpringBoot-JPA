package com.lochlann.assignment2.controllers.dto;

import com.lochlann.assignment2.controllers.webservices.DepartmentWebService;
import com.lochlann.assignment2.entities.Department;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class DepartmentDtoMapper extends RepresentationModelAssemblerSupport<Department, DepartmentDto> {
    public DepartmentDtoMapper() {
        super(DepartmentWebService.class, DepartmentDto.class);
    }

    @Override
    public DepartmentDto toModel(Department entity) {
        DepartmentDto departmentDto = new DepartmentDto(entity.getName(), entity.getEmail());
        departmentDto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DepartmentWebService.class).getById(entity.getId())).withSelfRel());
        return departmentDto;
    }

    @Override
    public CollectionModel<DepartmentDto> toCollectionModel(Iterable<? extends Department> entities) {
        return super.toCollectionModel(entities);
    }
}
