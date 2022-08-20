package SpaceProg.teamwork.dao;


import SpaceProg.teamwork.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.validation.Valid;
import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

//    @Query(value = "SELECT user_id2 FROM public.users_colleagues where user_id1 =:id union all SELECT user_id1 FROM public.users_colleagues where user_id2 =:id", nativeQuery = true)
//    Page<User> findUserColleaguesById(Pageable pageable, @Param("id") Long id);

@Query(value = "select user_id, login, password, email, name, surname from users join users_colleagues uc on user_id1 = :id and user_id2 = user_id", nativeQuery = true)
    Page<User> findUserFirstColleaguesById(Pageable pageable, @Param("id") Long id);

    @Query(value = "select user_id, login, password, email, name, surname from users join users_colleagues uc on user_id2 = :id and user_id1 = user_id", nativeQuery = true)
    Page<User> findUserSecondColleaguesBuId(Pageable pageable, @Param("id") Long id);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
