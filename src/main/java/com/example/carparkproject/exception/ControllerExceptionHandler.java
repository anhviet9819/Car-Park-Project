package com.example.carparkproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ErrorMessage resourceNotFoundExceptions(ResourceNotFoundException ex, WebRequest request) {
    ErrorMessage message = new ErrorMessage(
        HttpStatus.NOT_FOUND.value(),
        new Date(),
        ex.getMessage(),
        request.getDescription(false));
//    System.out.println("1");
    return message;
  }

  @ExceptionHandler(DuplicateIdException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorMessage duplicateIdException(DuplicateIdException ex, WebRequest request) {
    ErrorMessage message = new ErrorMessage(
            HttpStatus.BAD_REQUEST.value(),
            new Date(),
            ex.getMessage(),
            request.getDescription(false));
//    System.out.println("1");
    return message;
  }

  @ExceptionHandler(FullSlotException.class)
  @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
  public ErrorMessage handleFullSlotException(FullSlotException ex, WebRequest request) {
    ErrorMessage message = new ErrorMessage(
            HttpStatus.NOT_ACCEPTABLE.value(),
            new Date(),
            ex.getMessage(),
            request.getDescription(false));
//    System.out.println("1");
    return message;
  }

  @ExceptionHandler(BindException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)  // Nếu validate fail thì trả về 400
  public ErrorMessage handleBindException(BindException ex, WebRequest request) {
    // Trả về message của lỗi đầu tiên
//    String errorMessage = "Request không hợp lệ";
//    if (e.getBindingResult().hasErrors())
//      e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
    ErrorMessage message = new ErrorMessage(
            HttpStatus.BAD_REQUEST.value(),
            new Date(),
            ex.getMessage(),
            request.getDescription(false));
    return message;
  }

  @ExceptionHandler(InvalidRequestBodyException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)  // Nếu validate fail thì trả về 400
  public ErrorMessage handleInvalidRequestBodyException(InvalidRequestBodyException ex, WebRequest request) {
    // Trả về message của lỗi đầu tiên
//    String errorMessage = "Request không hợp lệ";
//    if (e.getBindingResult().hasErrors())
//      e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
    ErrorMessage message = new ErrorMessage(
            HttpStatus.BAD_REQUEST.value(),
            new Date(),
            ex.getMessage(),
            request.getDescription(false));
    return message;
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorMessage globalExceptionHandlers(Exception ex, WebRequest request) {
//    System.out.println("2");
    ErrorMessage message = new ErrorMessage(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        new Date(),
        ex.getMessage(),
        request.getDescription(false));
    return message;
  }
}
