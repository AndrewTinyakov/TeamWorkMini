package SpaceProg.teamwork.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    void changeImage(MultipartFile file) throws IOException;

    void deleteImageById(Long id);
}
