package pw.react.backend.reactbackend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Before;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import pw.react.backend.reactbackend.controllers.UsersController;
import pw.react.backend.reactbackend.errors.ErrorResponse;
import pw.react.backend.reactbackend.errors.UserAlreadyExistsException;
import pw.react.backend.reactbackend.errors.UserNotFoundException;
import pw.react.backend.reactbackend.models.User;
import pw.react.backend.reactbackend.repositories.UsersRepository;
import pw.react.backend.reactbackend.services.UsersService;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReactBackendApplicationTests {

	@Test
	public void contextLoads() {
	}

}
