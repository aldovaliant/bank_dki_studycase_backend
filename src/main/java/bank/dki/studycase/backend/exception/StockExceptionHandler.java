package bank.dki.studycase.backend.exception;

import bank.dki.studycase.backend.dto.GlobalError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class StockExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<GlobalError> handleNotFoundException(final NotFoundException e) {
        GlobalError error = new GlobalError(e.getMessage(), "Not Found", 404);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
