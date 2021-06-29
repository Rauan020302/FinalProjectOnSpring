package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.Group;
import it.academy.project.projectonspring.entity.Image;
import it.academy.project.projectonspring.entity.KinderGarden;
import it.academy.project.projectonspring.entity.User;
import it.academy.project.projectonspring.exception.ContactException;
import it.academy.project.projectonspring.exception.ObjectsNotFoundException;
import it.academy.project.projectonspring.model.KinderGardenModel;
import it.academy.project.projectonspring.repository.KinderGardenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KinderGardenServiceImpl implements KinderGardenService {
    @Autowired
    private KinderGardenRepository kinderGardenRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private GroupService groupService;

    @Override
    public KinderGarden saveKG(KinderGardenModel kinderGardenModel){
        User user = userService.getUserById(kinderGardenModel.getUserId());
        Image image = imageService.getImageById(kinderGardenModel.getImageId());
        try {
            if (user == null || image == null) throw new ObjectsNotFoundException();
            if (kinderGardenModel.getContact().toString().length() != 9) throw new ContactException();

            KinderGarden kinderGarden = KinderGarden.builder()
                    .name(kinderGardenModel.getName())
                    .description(kinderGardenModel.getDescription())
                    .address(kinderGardenModel.getAddress())
                    .contact(kinderGardenModel.getContact())
                    .user(user)
                    .image(image).build();
            return saveKG(kinderGarden);
        }catch (ObjectsNotFoundException e){
            throw new ObjectsNotFoundException("user or image not found");
        }catch (ContactException e){
            throw new ContactException("contact number is not correct");
        }
    }

    @Override
    public KinderGarden deleteKGById(Long id) {
        List<Group> groupList = groupService.findAllByKinderGarden_Id(id);
        for (Group group :groupList) {
            groupService.deleteGroupById(group.getId());
        }
        KinderGarden kinderGarden = getKGById(id);
        if (kinderGarden != null) {
            kinderGardenRepository.delete(kinderGarden);
            return kinderGarden;
        }
        return null;
    }

    @Override
    public KinderGarden updateKGById(KinderGardenModel kinderGardenModel, Long id) {
        User user = userService.getUserById(kinderGardenModel.getUserId());
        Image image = imageService.getImageById(kinderGardenModel.getImageId());
        try {
            if (user == null || image == null) throw new ObjectsNotFoundException();

            KinderGarden kinderGarden = getKGById(id);
            kinderGarden.setName(kinderGardenModel.getName());
            kinderGarden.setAddress(kinderGardenModel.getAddress());
            kinderGarden.setDescription(kinderGardenModel.getDescription());
            kinderGarden.setContact(kinderGardenModel.getContact());
            kinderGarden.setUser(user);
            kinderGarden.setImage(image);
            return saveKG(kinderGarden);
        }catch (ObjectsNotFoundException e){
            throw new ObjectsNotFoundException("not found filial by id - " + id);
        }
    }

    @Override
    public List<KinderGarden> getAllKG() {
        return kinderGardenRepository.findAll();
    }

    @Override
    public KinderGarden getKGById(Long id){
        return kinderGardenRepository.findById(id)
                .orElseThrow(() -> new ObjectsNotFoundException("not found filial by id - " + id));
    }

    @Override
    public KinderGarden saveKG(KinderGarden kinderGarden) {
        return kinderGardenRepository.save(kinderGarden);
    }
}

