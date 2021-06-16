package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.Group;
import it.academy.project.projectonspring.entity.Image;
import it.academy.project.projectonspring.entity.KinderGarden;
import it.academy.project.projectonspring.exception.ObjectsNotFoundException;
import it.academy.project.projectonspring.model.GroupModel;
import it.academy.project.projectonspring.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService{
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private KinderGardenService kinderGardenService;
    @Autowired
    private ImageService imageService;

    @Override
    public List<Group> findAllByKinderGarden_Id(Long id) {
        return groupRepository.findAllByKinderGarden_Id(id);
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public Group getGroupById(Long id) throws ObjectsNotFoundException {
//        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
//        Group group = groupRepository.findById(id).orElse(null);
//        UserRole userRole = userRoleService.findAllByUser_Id(user.getId());
//        if (!group.getKinderGarden().getUser().getUsername().equals(user.getUsername()) || !userRole.getRoleName().equals("ADMIN")) {
//            throw new ObjectsNotFoundException("not not");
//        }
        return groupRepository.findById(id)
                .orElseThrow(ObjectsNotFoundException::new);
    }

    @Override
    public Group deleteGroupById(Long id) throws ObjectsNotFoundException {
        Group group = getGroupById(id);
        if (group != null){
            groupRepository.delete(group);
            return group;
        }
        throw new ObjectsNotFoundException();
    }

    @Override
    public Group updateGroupById(GroupModel groupModel, Long id) throws ObjectsNotFoundException {
        KinderGarden kinderGarden = kinderGardenService.getKGById(groupModel.getKinderGardenId());
        Image image = imageService.getImageById(groupModel.getImageId());
        if(kinderGarden == null) throw new ObjectsNotFoundException();

        Group group = getGroupById(id);
        group.setName(groupModel.getName());
        group.setKinderGarden(kinderGarden);
        group.setImage(image);
        group.setInfo(groupModel.getInfo());
        group.setTeacherFullName(groupModel.getTeacherFullName());
        return saveGroup(group);
    }

    @Override
    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group saveGroup(GroupModel groupModel) throws ObjectsNotFoundException {
        KinderGarden kinderGarden = kinderGardenService.getKGById(groupModel.getKinderGardenId());
        Image image = imageService.getImageById(groupModel.getImageId());
        if(kinderGarden == null) throw new ObjectsNotFoundException();

        Group group = Group.builder()
                .image(image)
                .info(groupModel.getInfo())
                .teacherFullName(groupModel.getTeacherFullName())
                .name(groupModel.getName())
                .kinderGarden(kinderGarden).build();
        return saveGroup(group);
    }
}

