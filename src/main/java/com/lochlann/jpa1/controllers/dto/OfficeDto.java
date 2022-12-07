package com.lochlann.jpa1.controllers.dto;

import com.lochlann.jpa1.entities.Department;
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
@Relation(collectionRelation = "offices", itemRelation = "office")
public class OfficeDto extends RepresentationModel<OfficeDto> {
    private int maxOccupancy;
    private int currentOccupancy;
    private Department department;
}
