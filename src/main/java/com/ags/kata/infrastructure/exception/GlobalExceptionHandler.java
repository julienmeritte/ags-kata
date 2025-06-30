package com.ags.kata.infrastructure.exception;

import com.ags.kata.application.dto.response.BasicMessageResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MarcheNonTrouveException.class)
    public ResponseEntity<BasicMessageResponseDto> handleParcNonTrouve(MarcheNonTrouveException ex) {
        return ResponseEntity.status(404)
                .body(new BasicMessageResponseDto(ex.getMessage()));
    }
}
