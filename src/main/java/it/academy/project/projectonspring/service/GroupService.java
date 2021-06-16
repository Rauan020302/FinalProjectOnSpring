package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.Group;
import it.academy.project.projectonspring.exception.ObjectsNotFoundException;
import it.academy.project.projectonspring.model.GroupModel;

import java.util.List;

public interface GroupService {
    List<Group> getAllGroups();
    Group getGroupById(Long id) throws ObjectsNotFoundException;
    Group deleteGroupById(Long id) throws ObjectsNotFoundException;
    Group updateGroupById(GroupModel groupModel, Long id) throws ObjectsNotFoundException;
    Group saveGroup(Group group);
    Group saveGroup(GroupModel groupModel) throws ObjectsNotFoundException;
    List<Group> findAllByKinderGarden_Id(Long id);
}
