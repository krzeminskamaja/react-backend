package pw.react.backend.reactbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pw.react.backend.reactbackend.models.User;

import java.util.List;

@Repository
public interface UsersRepo extends JpaRepository<User, Long> {
    User findById(int Id);
    List<User> findByLogin(String login);
}