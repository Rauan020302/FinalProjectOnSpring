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
    public KinderGarden getKGById(Long id){
        return kinderGardenRepository.findById(id)
                .orElseThrow(() -> new ObjectsNotFoundException("not found filial by id - " + id));
    }

    @Override
    public KinderGarden saveKG(KinderGarden kinderGarden) {
        return kinderGardenRepository.save(kinderGarden);
    }

    @Override
    public KinderGarden saveKG(KinderGardenModel kinderGardenModel){
        try {
            User user = userService.getUserById(kinderGardenModel.getUserId());
            Image image = imageService.getImageById(kinderGardenModel.getImageId());
            if (user == null || image == null) throw new ObjectsNotFoundException();

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
        }
    }



    @Override
    public KinderGarden deleteKGById(Long id) {
        KinderGarden kinderGarden = getKGById(id);
        //try {
            if (kinderGarden != null) {
                kinderGardenRepository.delete(kinderGarden);
                return kinderGarden;
            }
            return null;
//            throw new ObjectsNotFoundException();
//        } catch (ObjectsNotFoundException e) {
//            throw new ObjectsNotFoundException("filial is not deleted");
//        }
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
}

