package basic.restful.service;

import basic.restful.dto.CreateUserRequest;
import basic.restful.dto.UserResponse;
import basic.restful.entity.User;
import basic.restful.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public List<UserResponse> getUsers() {
    List<User> users = userRepository.findAll();
    var formattedUsers = users
      .stream()
      .map(user -> new UserResponse(user.getEmail(), user.getName()))
      .toList();

    return formattedUsers;
  }

  public UserResponse createUser(CreateUserRequest dto) {
    var isUserExist = userRepository.existsById(dto.getEmail());
    if (isUserExist) throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");

    User user = new User(dto.getEmail(), dto.getPassword(), dto.getName());
    userRepository.save(user);

    UserResponse formattedUser = new UserResponse(user.getEmail(), user.getName());
    return formattedUser;
  }
}
