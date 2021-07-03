package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.Child;
import it.academy.project.projectonspring.model.ChildWithoutVisitModel;

import java.time.LocalDate;
import java.util.List;

public interface ChildService {
    List<Child> getChild();
    Child getChildById(Long id);
    Child deleteChildById(Long id);
    Child saveChild(Child child);
    Child saveChild(it.academy.project.projectonspring.model.ChildModel childModel);
    Child updateChild(it.academy.project.projectonspring.model.ChildModel childModel, Long id);
    List<Child> findAllByGroup_Id(Long id);
    List<Child> findAllByBirthDayAfter(LocalDate date);
    List<ChildWithoutVisitModel> findAllByGroupKinderGarden_Id(Long id);
}
