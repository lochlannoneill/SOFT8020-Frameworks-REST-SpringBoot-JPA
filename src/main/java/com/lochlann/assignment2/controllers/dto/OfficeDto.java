package com.lochlann.assignment2.controllers.dto;

import com.lochlann.assignment2.entities.Department;
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
@Relation(collectionRelation = "offices", itemRelation = "office")
public class OfficeDto extends RepresentationModel<OfficeDto> {
    private int maxOccupancy;
    private int currentOccupancy;
    private Department department;
}
