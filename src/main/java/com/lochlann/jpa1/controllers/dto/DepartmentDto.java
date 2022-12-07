package com.lochlann.jpa1.controllers.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Relation(collectionRelation = "departments", itemRelation = "department")
// ! ERROR - Entity 'com.lochlann.jpa1.controllers.dto.DepartmentDto' has no identifier (every '@Entity' class must declare or inherit at least one '@Id' or '@EmbeddedId' property)
public class DepartmentDto extends RepresentationModel<DepartmentDto>  {
        private String name;
        private String email;
}
