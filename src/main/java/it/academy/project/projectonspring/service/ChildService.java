package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.Child;
import it.academy.project.projectonspring.model.ChildModel;
import java.util.List;

public interface ChildService {
    List<Child> getChild();
    Child getChildById(Long id);
    Child deleteChildById(Long id);
    Child saveChild(Child child);
    Child saveChild(ChildModel childModel);
    Child updateChild(ChildModel childModel,Long id);
    List<Child> findAllByGroup_Id(Long id);
    Child findByGroup_Id(Long id);}
