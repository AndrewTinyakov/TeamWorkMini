package SpaceProg.teamwork.dao;

import SpaceProg.teamwork.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageDao extends JpaRepository<Image, Long> {
}
