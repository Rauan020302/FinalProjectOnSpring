package it.academy.project.projectonspring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonthModel {

    private Long groupId;

    private LocalDate startDate;

    private LocalDate endDate;
}
