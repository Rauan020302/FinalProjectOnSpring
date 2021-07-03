package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.Child;
import it.academy.project.projectonspring.entity.Group;
import it.academy.project.projectonspring.entity.Image;
import it.academy.project.projectonspring.entity.Visit;
import it.academy.project.projectonspring.exception.ContactException;
import it.academy.project.projectonspring.exception.ObjectsNotFoundException;
import it.academy.project.projectonspring.model.ChildModel;
import it.academy.project.projectonspring.model.ChildWithoutVisitModel;
import it.academy.project.projectonspring.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChildServiceImpl implements ChildService {
    @Autowired
    private ChildRepository childRepository;
    @Autowired
    private GroupService groupService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private VisitService visitService;

    @Override
    public Child saveChild(ChildModel childModel){
        Group group = groupService.getGroupById(childModel.getGroupId());
        Image image = imageService.getImageById(childModel.getImageId());
        try {
            if (group == null) throw new ObjectsNotFoundException();
            if (childModel.getContact().toString().length() != 9) throw new ContactException();

            Child child = Child.builder()
                    .fullName(childModel.getFullName())
                    .birthDay(childModel.getBirthDay())
                    .gender(childModel.getGender())
                    .image(image)
                    .contact(childModel.getContact())
                    .parent(childModel.getParent())
                    .group(group).build();
            saveChild(child);
            return saveChild(child);

        }catch (ObjectsNotFoundException e){
            throw new ObjectsNotFoundException("group not found");
        }catch (ContactException e){
            throw new ContactException("bad number");
        }
    }

    @Override
    public Child updateChild(ChildModel childModel, Long id){
        Group group = groupService.getGroupById(childModel.getGroupId());
        Image image = imageService.getImageById(childModel.getImageId());
        try {
            if (group == null || image == null) throw new ObjectsNotFoundException();

            Child child = getChildById(id);
            child.setFullName(childModel.getFullName());
            child.setBirthDay(childModel.getBirthDay());
            child.setGender(childModel.getGender());
            child.setImage(image);
            child.setContact(childModel.getContact());
            child.setParent(childModel.getParent());
            child.setGroup(group);
            return saveChild(child);
        }catch (ObjectsNotFoundException e){
            throw new ObjectsNotFoundException("not found child by id - " + id);
        }
    }

    @Override
    public Child deleteChildById(Long id){
        List<Visit> visits = visitService.findAllByChild_Id(id);
        for (Visit visit :visits) {
            childRepository.deleteById(visit.getId());
        }
        Child child = getChildById(id);
        if (child != null){
            childRepository.delete(child);
            return child;
        }
        return null;
    }

    @Override
    public Child saveChild(Child child) {
        return childRepository.save(child);
    }

    @Override
    public List<Child> findAllByBirthDayAfter(LocalDate date) {
        return childRepository.findAllByBirthDayAfter(date);
    }

    @Override
    public List<Child> findAllByGroup_Id(Long id) {
        return childRepository.findAllByGroup_Id(id);
    }

    @Override
    public List<Child> getChild() {
        return childRepository.findAll();
    }

    @Override
    public Child getChildById(Long id){
        return childRepository.findById(id)
                .orElseThrow(() -> new ObjectsNotFoundException("not found child by id - " + id));
    }

    @Override
    public List<ChildWithoutVisitModel> findAllByGroupKinderGarden_Id(Long id) {
        List<Child> childList = childRepository.findAllByGroupKinderGarden_Id(id);
        List<ChildWithoutVisitModel> childWithoutVisitModelList = new ArrayList<>();

        for (Child child : childList){
            ChildWithoutVisitModel childWithoutVisitModel = ChildWithoutVisitModel.builder()
                    .id(child.getId())
                    .fullName(child.getFullName())
                    .gender(child.getGender())
                    .contact(child.getContact())
                    .image(child.getImage())
                    .birthDay(child.getBirthDay())
                    .group(child.getGroup())
                    .parent(child.getParent())
                    .build();
            childWithoutVisitModelList.add(childWithoutVisitModel);
        }

        return childWithoutVisitModelList;
    }
}

