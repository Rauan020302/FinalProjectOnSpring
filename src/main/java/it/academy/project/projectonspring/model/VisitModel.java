package it.academy.project.projectonspring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VisitModel {

    private LocalDate date;

    private Boolean visit;

    private Long childId;

    private Long groupId;
}
