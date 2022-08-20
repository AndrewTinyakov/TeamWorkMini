package SpaceProg.teamwork.dao;

import SpaceProg.teamwork.model.chat.ChatAbstract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatDao extends JpaRepository<ChatAbstract, Long> {
}
