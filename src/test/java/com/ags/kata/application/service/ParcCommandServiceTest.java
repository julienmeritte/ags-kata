package com.ags.kata.application.service;

import com.ags.kata.application.dto.request.CreationParcRequestDto;
import com.ags.kata.application.port.out.ParcCommandRepository;
import com.ags.kata.application.port.out.ParcQueryRepository;
import com.ags.kata.domain.model.parc.Parc;
import com.ags.kata.infrastructure.adapter.web.parc.ParcWebMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.ags.kata.utils.ParcUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParcCommandServiceTest {

    @Mock
    private ParcQueryRepository parcQueryRepository;

    @Mock
    private ParcCommandRepository parcCommandRepository;

    @Mock
    private ParcWebMapper parcWebMapper;

    @InjectMocks
    private ParcCommandService parcCommandService;

    @Test
    void shouldReturnParcCree_WhenCreatingParc_GivenParcCreationRequest() {
        when(parcCommandRepository.save(any(Parc.class)))
                .thenReturn(creerParc());
        when(parcWebMapper.toResponseDto(any(Parc.class)))
                .thenReturn(creerParcResponseDto());

        var creationParcRequestDto = new CreationParcRequestDto(PARC_NOM, PARC_TYPE, PARC_CAPACITE_HORAIRE_MW);

        var result = parcCommandService.creerParc(creationParcRequestDto);

        assertNotNull(result);
        assertEquals(creerParcResponseDto(), result);
    }
}