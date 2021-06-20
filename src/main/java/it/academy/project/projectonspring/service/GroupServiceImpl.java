package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.Group;
import it.academy.project.projectonspring.entity.Image;
import it.academy.project.projectonspring.entity.KinderGarden;
import it.academy.project.projectonspring.entity.User;
import it.academy.project.projectonspring.exception.ObjectsNotFoundException;
import it.academy.project.projectonspring.model.GroupModel;
import it.academy.project.projectonspring.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Autowired
    private UserService userService;


    @Override
    public List<Group> findAllByKinderGarden_Id(Long id) {
        return groupRepository.findAllByKinderGarden_Id(id);
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public Group getGroupById(Long id){
        return groupRepository.findById(id)
                .orElseThrow(() -> new ObjectsNotFoundException("not found group by id " + id));
    }

    @Override
    public Group deleteGroupById(Long id){
        Group group = getGroupById(id);
        if (group != null){
            groupRepository.delete(group);
            return group;
        }
        return null;
    }

    @Override
    public Group updateGroupById(GroupModel groupModel, Long id) throws ObjectsNotFoundException {
        KinderGarden kinderGarden = kinderGardenService.getKGById(groupModel.getKinderGardenId());
        Image image = imageService.getImageById(groupModel.getImageId());
        try{
            if(kinderGarden == null || image == null) throw new ObjectsNotFoundException();

            Group group = getGroupById(id);
            group.setName(groupModel.getName());
            group.setKinderGarden(kinderGarden);
            group.setImage(image);
            group.setInfo(groupModel.getInfo());
            group.setTeacherFullName(groupModel.getTeacherFullName());
            return saveGroup(group);

        }catch (ObjectsNotFoundException e){
            throw new ObjectsNotFoundException("not found group by id "+ id);
        }
    }

    @Override
    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group saveGroup(GroupModel groupModel) throws ObjectsNotFoundException {
        KinderGarden kinderGarden = kinderGardenService.getKGById(groupModel.getKinderGardenId());
        Image image = imageService.getImageById(groupModel.getImageId());
        try {
            if (kinderGarden == null || image == null) throw new ObjectsNotFoundException();

            Group group = Group.builder()
                    .image(image)
                    .info(groupModel.getInfo())
                    .teacherFullName(groupModel.getTeacherFullName())
                    .name(groupModel.getName())
                    .kinderGarden(kinderGarden).build();

            return saveGroup(group);
        }catch (ObjectsNotFoundException e ){
            throw new ObjectsNotFoundException("filial or image not found ");
        }
    }
}

