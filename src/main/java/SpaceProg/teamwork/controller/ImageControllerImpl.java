package SpaceProg.teamwork.controller;

import SpaceProg.teamwork.service.ImageService;
import SpaceProg.teamwork.service.ImageServiceSimple;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/image")
public class ImageControllerImpl {
    private final ImageService imageService;

    public ImageControllerImpl(ImageService imageService) {
        this.imageService = imageService;
    }

    @PutMapping
    public void changeImage(MultipartFile file) throws IOException {
        imageService.changeImage(file);
    }

    @DeleteMapping("/{id}")
    public void deleteFile(@PathVariable Long id){
        imageService.deleteImageById(id);
    }






}
