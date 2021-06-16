package it.academy.project.projectonspring.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import it.academy.project.projectonspring.entity.Image;
import it.academy.project.projectonspring.exception.ObjectsNotFoundException;
import it.academy.project.projectonspring.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

@Service
public class ImageServiceImpl implements ImageService {

    private static final String CLOUDINARY_URL =
            "cloudinary://591228673175129:y07UvTqfHK7FThy_Kxl1c7lrxqU@bishkek-it-academy-kg/";

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Image createImage(MultipartFile multipartFile) throws IOException, ObjectsNotFoundException {
        Image image = new Image();

        File file;

        try {
            file = Files.createTempFile(System.currentTimeMillis() + "",
                    multipartFile.getOriginalFilename()
                            .substring(multipartFile.getOriginalFilename().length() - 4))
                    .toFile();
            multipartFile.transferTo(file);

            Cloudinary cloudinary = new Cloudinary(CLOUDINARY_URL);
            Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            image.setName((String) uploadResult.get("public_id"));
            image.setUrl((String) uploadResult.get("url"));
            image.setFormat((String) uploadResult.get("format"));

            return imageRepository.save(image);
        }catch (IOException e){
            System.out.println("ImageService createImage " + e.getMessage());
            throw new ObjectsNotFoundException();
        }
    }

    @Override
    public List<Image> getAllImage() {
        return imageRepository.findAll();
    }

    @Override
    public Image getImageById(Long id) throws ObjectsNotFoundException {
        return imageRepository.findById(id).orElse(null);
    }
}
