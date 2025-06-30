package com.ags.kata.application.port.in;

import com.ags.kata.application.dto.response.MarcheOffreResponseDto;

import java.util.Set;

public interface RecupererOffreUseCase {

    Set<MarcheOffreResponseDto> recupererOffresAgregioParMarche();
}
