package com.thecat.api.exceptions.serverfallen;

import com.thecat.api.dtos.MainResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ServerFallenResponseHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServerFallen.class)
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    public ResponseEntity<MainResponseDto> serverFallen(ServerFallen serverFallen) {
        log.error("ServerFallenResponseHandler.class - serverFallen() -> Se lanzo excepcion por servidor de raza caido...!");
        return new ResponseEntity<>(serverFallen.getErrorResponse(), HttpStatus.GATEWAY_TIMEOUT);
    }
}
