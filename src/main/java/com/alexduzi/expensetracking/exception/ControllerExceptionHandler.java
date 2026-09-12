package com.alexduzi.expensetracking.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.jspecify.annotations.Nullable;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<?> databaseError(DatabaseException ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemType problemType = ProblemType.DATABASE_EXCEPTION;

        ProblemDetailError detail = createProblemDetailError(ex.getMessage(), status, problemType, ex, request);

        return handleExceptionInternal(ex,
                detail,
                new HttpHeaders(),
                status,
                request);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<?> entityAlreadyExistsException(EntityAlreadyExistsException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.ENTITY_ALREADY_EXISTS_EXCEPTION;

        ProblemDetailError detail = createProblemDetailError(ex.getMessage(), status, problemType, ex, request);

        return handleExceptionInternal(ex,
                detail,
                new HttpHeaders(),
                status,
                request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> entityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.ENTITY_NOT_FOUND_EXCEPTION;

        ProblemDetailError detail = createProblemDetailError(ex.getMessage(), status, problemType, ex, request);

        return handleExceptionInternal(ex,
                detail,
                new HttpHeaders(),
                status,
                request);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, String>> handleValidationError(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }

    @Override
    protected @Nullable ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                                       @Nullable Object body,
                                                                       HttpHeaders headers,
                                                                       HttpStatusCode statusCode,
                                                                       WebRequest request) {


        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    private ProblemDetailError createProblemDetailError(String message, HttpStatus status, ProblemType problemType,
                                                        RuntimeException e, WebRequest request) {
        return new ProblemDetailError(
                Instant.now(),
                status.value(),
                message,
                problemType.getTitle(),
                problemType.getUri(),
                e.getMessage(),
                request.getContextPath()
        );
    }

    // Alternative: Using RFC 7807/9457
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ProblemDetail handleValidationException(MethodArgumentNotValidException ex) {
//        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation failed for request payload");
//
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
//
//        problemDetail.setProperty("errors", errors);
//        return problemDetail;
//    }
}
