package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.Child;
import it.academy.project.projectonspring.exception.ObjectsNotFoundException;
import it.academy.project.projectonspring.model.ChildModel;

import java.util.List;

public interface ChildService {
    List<Child> getChild();
    Child getChildById(Long id) throws ObjectsNotFoundException;
    Child deleteChildById(Long id) throws ObjectsNotFoundException;
    Child saveChild(Child child);
    Child saveChild(ChildModel childModel) throws ObjectsNotFoundException;
    Child updateChild(ChildModel childModel,Long id) throws ObjectsNotFoundException;
    List<Child> findAllByGroup_Id(Long id);
}
