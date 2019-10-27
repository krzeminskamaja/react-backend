package pw.react.backend.reactbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pw.react.backend.reactbackend.errors.Error;
import pw.react.backend.reactbackend.errors.UserExistsException;
import pw.react.backend.reactbackend.errors.NotFoundException;
import pw.react.backend.reactbackend.models.User;
import pw.react.backend.reactbackend.services.Service;
import java.util.Map;
import java.util.HashMap;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/spring-demo")
public class Controller {
    private Service service;

    @Autowired
    public Controller(Service usersService) {
        this.service = usersService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(@RequestParam(required = false) String login) {
        var result;
        if (login == null)
            result = service.findAll();

        else
            result = service.findByLogin(login);
        if (result == null) {
            throw new NotFoundException("Login: " + login);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/users/")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        if (service.exists(user)) {
            throw new UserExistsException("Login: " + user.getLogin());
        }

        var result = service.save(user);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "Id") int id) {
        var result = service.findById(id);
        if (result == null) {
            throw new UserNotFoundException("Id: " + id);
        }

        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable(value = "Id") int id) {
        var userToDelete = service.findById(id);
        if (userToDelete == null) {
            throw new UserNotFoundException("Id: " + id);
        }
        service.delete(userToDelete);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return ResponseEntity.ok(response);
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "Id") int id, @Valid @RequestBody User user) {
        User userToUpdate = service.findById(id);
        if (userToUpdate == null) {
            throw new UserNotFoundException("Id: " + id);
        }
        userToUpdate.setAllDetails(user.getLogin(), user.getFirstName(), user.getLastName(), user.getDateOfBirth(),
                user.getIsActive());
        final User updatedUser = service.save(userToUpdate);

        return ResponseEntity.ok(updatedUser);
    }


    @ExceptionHandler({UserExistsException.class})
    public ResponseEntity<Error> alreadyExists(UserExistsException ex) {
        return new ResponseEntity<>(
                new Error(ex.getMessage(), HttpStatus.BAD_REQUEST.value()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Error> notFound(NotFoundException ex) {
        return new ResponseEntity<>(
                new Error(ex.getMessage(), HttpStatus.NOT_FOUND.value()),
                HttpStatus.NOT_FOUND);
    }



}