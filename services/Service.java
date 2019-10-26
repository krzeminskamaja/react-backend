package pw.react.backend.reactbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.react.backend.reactbackend.models.User;
import pw.react.backend.reactbackend.repositories.UsersRepo;

import java.util.List;

@Service
public class Service {
    private UsersRepo usersRepo;

    @Autowired
    public Service(UsersRepo usersRepository) {
        this.usersRepo = usersRepository;
    }

    public List<User> findAll() {
        return usersRepo.findAll();
    }

    public List<User> findByLogin(String login) {
        return usersRepo.findByLogin(login);
    }

    public User save(User user) {
        return usersRepo.save(user);
    }

    public boolean exists(User user) {
        List<User> result = usersRepo.findByLogin(user.getLogin());
        return result != null && !result.isEmpty();
    }
}