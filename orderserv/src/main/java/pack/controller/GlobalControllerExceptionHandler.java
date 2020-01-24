package pack.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pack.entity.ErrorMessageDto;
import pack.entity.exceptions.DataIntegrityViolationException;

@ControllerAdvice
class GlobalControllerExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity handleConflict() {
        return new ResponseEntity<Object>(
                new ErrorMessageDto("Provided data is incorrect"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity handleItemNotFound() {
        return new ResponseEntity<Object>(
                new ErrorMessageDto("Item not found"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}