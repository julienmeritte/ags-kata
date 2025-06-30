package com.ags.kata.infrastructure.adapter.web.parc;

import com.ags.kata.application.dto.request.CreationParcRequestDto;
import com.ags.kata.application.dto.response.ParcResponseDto;
import com.ags.kata.application.service.ParcCommandService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parc")
public class ParcController {

    private final ParcCommandService parcCommandService;

    public ParcController(ParcCommandService parcCommandService) {
        this.parcCommandService = parcCommandService;
    }

    @PostMapping
    public ResponseEntity<ParcResponseDto> creerParc(@Valid @RequestBody CreationParcRequestDto creationParcRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(parcCommandService.creerParc(creationParcRequestDto));
    }
}
