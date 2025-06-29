package com.ags.kata.application.port.in;

import com.ags.kata.application.dto.request.CreationParcRequestDto;
import com.ags.kata.application.dto.response.ParcResponseDto;

public interface CreerParcUseCase {

    ParcResponseDto creerParc(CreationParcRequestDto creationParcRequestDto);
}
