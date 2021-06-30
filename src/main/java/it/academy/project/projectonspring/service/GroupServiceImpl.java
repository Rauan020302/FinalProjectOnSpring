package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.*;
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
    @Autowired
    private ChildService childService;
    @Autowired
    private CourseGroupService courseGroupService;
    @Autowired
    private DailyRegimeService dailyRegimeService;
    @Autowired
    private VisitService visitService;

    @Override
    public Group deleteGroupById(Long id){
        List<CourseGroup> courseGroups = courseGroupService.findAllByGroup_Id(id);
        for (CourseGroup courseGroup : courseGroups){
            courseGroupService.deleteById(courseGroup.getId());
        }
        List<Visit> visits = visitService.findAllByGroup_Id(id);
        for (Visit visit : visits){
            visitService.deleteVisit(visit.getId());
        }
        List<DailyRegime> dailyRegimes = dailyRegimeService.findAllByGroup_Id(id);
        for (DailyRegime dailyRegime : dailyRegimes){
            dailyRegimeService.deleteDailyRegime(dailyRegime.getId());
        }
        List<Child> children =  childService.findAllByGroup_Id(id);
        for (Child child:children) {
            childService.deleteChildById(child.getId());
        }
        Group group = getGroupById(id);
        if (group != null){
            groupRepository.delete(group);
            return group;
        }
        return null;
    }

    @Override
    public Group updateGroupById(GroupModel groupModel, Long id){
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
    public Group saveGroup(GroupModel groupModel){
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
    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }
}

