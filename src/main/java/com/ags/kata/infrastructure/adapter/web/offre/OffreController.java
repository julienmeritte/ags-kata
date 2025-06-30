package com.ags.kata.infrastructure.adapter.web.offre;

import com.ags.kata.application.dto.request.CreationOffreRequestDto;
import com.ags.kata.application.dto.response.OffreResponseDto;
import com.ags.kata.application.service.OffreCommandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/offre")
public class OffreController {

    private final OffreCommandService offreCommandService;

    @Autowired
    public OffreController(OffreCommandService offreCommandService) {
        this.offreCommandService = offreCommandService;
    }

    @PostMapping
    public ResponseEntity<OffreResponseDto> creerOffre(@RequestBody @Valid CreationOffreRequestDto creationOffreRequestDto) {

        var offreCreee = offreCommandService.creerOffre(creationOffreRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(offreCreee);
    }
}
