package SpaceProg.teamwork.service;

import SpaceProg.teamwork.dao.ImageDao;
import SpaceProg.teamwork.model.Image;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
public class ImageServiceSimple implements ImageService{

    private final UserService userService;
    private final ImageDao imageDao;

    public ImageServiceSimple(UserService userService, ImageDao imageDao) {
        this.userService = userService;
        this.imageDao = imageDao;
    }

    @Override
    public void changeImage(MultipartFile file) throws IOException {
        Image image = new Image(file, userService.getCurrentUser());
        imageDao.save(image);
    }

    @Override
    public void deleteImageById(Long id) {
        imageDao.deleteById(id);
    }
}
