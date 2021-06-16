package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.Image;
import it.academy.project.projectonspring.entity.KinderGarden;
import it.academy.project.projectonspring.entity.User;
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

    @Override
    public List<KinderGarden> getAllKG() {
        return kinderGardenRepository.findAll();
    }

    @Override
    public KinderGarden getKGById(Long id) throws ObjectsNotFoundException {
        return kinderGardenRepository.findById(id)
                .orElseThrow(ObjectsNotFoundException::new);
    }

    @Override
    public KinderGarden saveKG(KinderGarden kinderGarden) {
        return kinderGardenRepository.save(kinderGarden);
    }

    @Override
    public KinderGarden saveKG(KinderGardenModel kinderGardenModel) throws ObjectsNotFoundException {
        User user = userService.getUserById(kinderGardenModel.getUserId());
        Image image = imageService.getImageById(kinderGardenModel.getImageId());
        if (user == null || image == null)throw new ObjectsNotFoundException();

        KinderGarden kinderGarden = KinderGarden.builder()
                .name(kinderGardenModel.getName())
                .description(kinderGardenModel.getDescription())
                .number(kinderGardenModel.getNumber())
                .address(kinderGardenModel.getAddress())
                .contact(kinderGardenModel.getContact())
                .user(user)
                .image(image).build();
        return saveKG(kinderGarden);
    }



    @Override
    public KinderGarden deleteKGById(Long id) throws ObjectsNotFoundException {
        KinderGarden kinderGarden = getKGById(id);
        if (kinderGarden != null){
            kinderGardenRepository.delete(kinderGarden);
            return kinderGarden;
        }
        throw new ObjectsNotFoundException();
    }

    @Override
    public KinderGarden updateKGById(KinderGardenModel kinderGardenModel, Long id) throws ObjectsNotFoundException {
        User user = userService.getUserById(kinderGardenModel.getUserId());
        Image image = imageService.getImageById(kinderGardenModel.getImageId());
        if (user == null || image == null)throw new ObjectsNotFoundException();

        KinderGarden kinderGarden = getKGById(id);
        kinderGarden.setName(kinderGardenModel.getName());
        kinderGarden.setAddress(kinderGardenModel.getAddress());
        kinderGarden.setDescription(kinderGardenModel.getDescription());
        kinderGarden.setNumber(kinderGardenModel.getNumber());
        kinderGarden.setContact(kinderGardenModel.getContact());
        kinderGarden.setUser(user);
        kinderGarden.setImage(image);
        return saveKG(kinderGarden);
    }
}

