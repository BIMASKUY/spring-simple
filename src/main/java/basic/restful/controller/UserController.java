package basic.restful.controller;

import basic.restful.dto.CreateUserRequest;
import basic.restful.dto.SuccessReponse;
import basic.restful.dto.UserResponse;
import basic.restful.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
  private final UserService userService;

  @GetMapping()
  public ResponseEntity<SuccessReponse<List<UserResponse>>> getUsers() {
    var users = userService.getUsers();
    var response = new SuccessReponse<List<UserResponse>>(users);
    return ResponseEntity.ok(response);
  }

  @PostMapping()
  public ResponseEntity<SuccessReponse<UserResponse>> createUser(@Valid @RequestBody CreateUserRequest dto) {
    var user = userService.createUser(dto);
    var response = new SuccessReponse<UserResponse>(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
