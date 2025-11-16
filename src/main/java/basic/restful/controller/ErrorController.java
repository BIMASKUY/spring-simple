package basic.restful.controller;

import basic.restful.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestControllerAdvice
public class ErrorController {
  // validation error
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse<List<String>>> validationException (MethodArgumentNotValidException exception) {
    var errors = exception
      .getBindingResult()
      .getAllErrors()
      .stream()
      .map(error -> error.getDefaultMessage())
      .toList();

    var response = new ErrorResponse<List<String>>(errors);
    return ResponseEntity.badRequest().body(response);
  }

  // custom error
  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<ErrorResponse<String>> customException (ResponseStatusException exception) {
    var error = exception.getReason();
    var response = new ErrorResponse<String>(error);
    return ResponseEntity.status(exception.getStatusCode()).body(response);
  }
}
