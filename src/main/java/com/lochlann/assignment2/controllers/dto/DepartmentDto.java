package com.lochlann.assignment2.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Relation(collectionRelation = "departments", itemRelation = "department")
public class DepartmentDto extends RepresentationModel<DepartmentDto>  {
        private String name;
        private String email;
}
