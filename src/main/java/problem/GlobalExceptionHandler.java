package problem;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;


import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    ProblemDetail handleNotFoundException(NotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getDetail());
        problemDetail.setTitle(e.getTitle());

        if (e.getParameters() != null) {
            for (Map.Entry<String, Object> entry : e.getParameters().entrySet()) {
                problemDetail.setProperty(entry.getKey(), entry.getValue());
            }
        }
        return problemDetail;
    }

    @ExceptionHandler(BadRequestException.class)
    ProblemDetail handleBadRequestException(BadRequestException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getDetail());
        if (!(StringUtils.hasText(e.getTitle()) || StringUtils.hasText(e.getDetail())) && StringUtils.hasText(e.getMessage())) {
            problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        problemDetail.setTitle(e.getTitle());

        if (e.getParameters() != null) {
            for (Map.Entry<String, Object> entry : e.getParameters().entrySet()) {
                problemDetail.setProperty(entry.getKey(), entry.getValue());
            }
        }
        return problemDetail;
    }