package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.Child;
import it.academy.project.projectonspring.entity.Group;
import it.academy.project.projectonspring.entity.Image;
import it.academy.project.projectonspring.exception.ContactException;
import it.academy.project.projectonspring.exception.ObjectsNotFoundException;
import it.academy.project.projectonspring.model.ChildModel;
import it.academy.project.projectonspring.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildServiceImpl implements ChildService {
    @Autowired
    private ChildRepository childRepository;
    @Autowired
    private GroupService groupService;
    @Autowired
    private ImageService imageService;

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
    public Child deleteChildById(Long id){
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
}

