package com.lochlann.assignment2.controllers.dto;

import com.lochlann.assignment2.controllers.webservices.OfficeWebService;
import com.lochlann.assignment2.entities.Office;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class OfficeDtoMapper extends RepresentationModelAssemblerSupport<Office, OfficeDto> {
    public OfficeDtoMapper() {
        super(OfficeWebService.class, OfficeDto.class);
    }

    @Override
    public OfficeDto toModel(Office entity) {
        OfficeDto officeDto = new OfficeDto(entity.getMaxOccupancy(), entity.getCurrentOccupancy(), entity.getDepartment());
        officeDto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OfficeWebService.class).getById(entity.getId())).withSelfRel());
        officeDto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OfficeWebService.class).changeDepartment(entity.getId(), null, null)).withRel("changeDepartment"));
        // TODO
        //  officeDto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OfficeWebService.class).getDepartmentById(entity.getGetDepartment().getId().withRel"department"));
        return officeDto;
    }

    @Override
    public CollectionModel<OfficeDto> toCollectionModel(Iterable<? extends Office> entities) {
        return super.toCollectionModel(entities);
    }
}
