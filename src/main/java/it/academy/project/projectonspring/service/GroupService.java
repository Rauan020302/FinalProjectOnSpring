package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.Group;
import it.academy.project.projectonspring.model.GroupModel;

import java.util.List;

public interface GroupService {
    List<Group> getAllGroups();
    Group getGroupById(Long id);
    Group deleteGroupById(Long id);
    Group updateGroupById(GroupModel groupModel, Long id);
    Group saveGroup(Group group);
    Group saveGroup(GroupModel groupModel);
    List<Group> findAllByKinderGarden_Id(Long id);
}
