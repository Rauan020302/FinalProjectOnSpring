package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    Image createImage(MultipartFile multipartFile) throws IOException;
    List<Image> getAllImage();
    Image getImageById(Long id);
}
