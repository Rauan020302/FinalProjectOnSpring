package it.academy.project.projectonspring.controller;

import it.academy.project.projectonspring.entity.Image;
import it.academy.project.projectonspring.exception.ObjectsNotFoundException;
import it.academy.project.projectonspring.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping
    public List<Image> getAllImage(){
        return imageService.getAllImage();
    }
    @PostMapping
    public Image createImage(@RequestParam(name = "file") MultipartFile multipartFile) throws IOException, ObjectsNotFoundException {
        return imageService.createImage(multipartFile);
    }

    @GetMapping("/{id}")
    public Image getImageById(@PathVariable Long id) throws ObjectsNotFoundException {
        return imageService.getImageById(id);
    }
}
