package com.example.serverForCA.exceptions;

import com.example.serverForCA.utils.constans.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandlerController {

  @ExceptionHandler(AuthenticationException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ResponseBody
  public ResponseEntity<Response> handleResourceAuthenticationException(Exception e) {
    Response response = new Response(e.getMessage());
    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ResponseEntity<Response> handleResourceUserNotFoundException(Exception e) {
    Response response = new Response(e.getMessage());
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ItemNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ResponseEntity<Response> handleResourceItemNotFoundException(Exception e) {
    Response response = new Response(e.getMessage());
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ResponseEntity<Response> handleResourceMethodArgumentNotValidException(Exception e) {
    Response response = new Response(getMessageFromException(e.getMessage()));
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }
  private String getMessageFromException(String message) {
    StringBuilder result = new StringBuilder();

    String[] errorMessages = new String[]{
            "age must not be empty",
            "name must not be empty",
            "email must not be empty",
            "incorrect email",
            "description must not be empty",
            "count must not be empty",
            "password must not be empty"
    };

    for (String error : errorMessages) {
      if (message.contains(error)) {
        result.append(error).append("||");
      }
    }

    return result.substring(0, result.length() - 2);
  }
}