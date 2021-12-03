package com.sapient.credit.card.exercise.exception;

import com.sapient.credit.card.exercise.model.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CreditCardExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {

        List<String> errors = ex.getBindingResult().getAllErrors().
                stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());

        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation Failed",
               errors);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * Total Catch handler.
     *
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleAllUncaughtException(RuntimeException exception,
                                                                WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Internal Server Error",
                Arrays.asList(exception.getMessage()));

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
