package dianafriptuleac.u5w2d4viaggi.exceptions;

import dianafriptuleac.u5w2d4viaggi.payloads.ErrorsResponceDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsResponceDTO handleBadrequestEx(BadRequestException ex) {
        return new ErrorsResponceDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsResponceDTO handleNotFoundEx(NotFoundException ex) {
        return new ErrorsResponceDTO(ex.getMessage(), LocalDateTime.now());


    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsResponceDTO handleGeneriEx(Exception ex) {
        ex.printStackTrace();
        return new ErrorsResponceDTO("Problema lato server!", LocalDateTime.now());
    }
}
