package com.bcatraining.helper.handler;

import com.bcatraining.constant.Constants;
import com.bcatraining.helper.dto.ApiResponse;
import com.bcatraining.helper.dto.CommonStatus;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<ProblemDetail>> handleBookNotFound(CustomException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(
                new ApiResponse<>(
                        CommonStatus.FAILED,
                        createProblemDetail(ex.getHttpStatus(),
                                ex.getTitle(),
                                ex.getMessage(),
                                null)
                )
        );
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiResponse> handleException(Exception ex) {
//        return ResponseEntity.badRequest().body(
//                new ApiResponse<>(
//                        CommonStatus.FAILED,
//                        createProblemDetail(HttpStatus.INTERNAL_SERVER_ERROR,
//                                Constants.ERROR_500,
//                                Constants.ERROR_MSG_500,
//                                null)
//                )
//        );
//    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> violations = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String field = ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            violations.put(field, errorMessage);
        });
        return ResponseEntity.badRequest().body(
                new ApiResponse<>(
                        CommonStatus.FAILED,
                        createProblemDetail(HttpStatus.BAD_REQUEST,
                                Constants.ERROR_400,
                                "Argument Not Valid",
                                violations)
                )
        );
    }

    private ProblemDetail createProblemDetail(HttpStatus httpStatus, String title, String detail, Map<String, String> violations) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, detail);
        problemDetail.setTitle(title);
        if (violations != null && !violations.isEmpty()) {
            problemDetail.setProperty("errors", violations);
        }
        return problemDetail;
    }
}
