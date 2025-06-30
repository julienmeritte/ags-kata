package com.ags.kata.application.port.in;

import com.ags.kata.application.dto.request.CreationOffreRequestDto;
import com.ags.kata.application.dto.response.OffreResponseDto;

public interface CreerOffreUseCase {

    OffreResponseDto creerOffre(CreationOffreRequestDto creationOffreRequestDto);
}
