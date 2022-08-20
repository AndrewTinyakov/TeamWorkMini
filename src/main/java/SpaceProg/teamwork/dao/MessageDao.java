package SpaceProg.teamwork.dao;

import SpaceProg.teamwork.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageDao extends JpaRepository<Message, Long> {
//    @EntityGraph(attributePaths = {"posts"})
//    Page<Message> findByChatId(Pageable pageable, @Param("chat_id") Long chatId);
}
