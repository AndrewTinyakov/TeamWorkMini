package SpaceProg.teamwork.dao;

import SpaceProg.teamwork.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PostDao extends JpaRepository<Post, Long> {

    @Query("select u from Post u where u.user.id=:id")
    Page<Post> findUserPostsPage(Pageable pageable, @Param("id") Long id);


}

