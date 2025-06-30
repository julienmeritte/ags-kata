package com.ags.kata.infrastructure.adapter.web.parc;

import com.ags.kata.application.dto.request.CreationParcRequestDto;
import com.ags.kata.application.service.ParcCommandService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.ags.kata.utils.ParcUtils.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ParcController.class)
class ParcControllerTest {

    @MockitoBean
    private ParcCommandService parcCommandService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldReturnOk_WhenCreatingParc_GivenCreationParcRequestDto() throws Exception {

        when(parcCommandService.creerParc(any(CreationParcRequestDto.class)))
                .thenReturn(creerParcResponseDto());

        mockMvc.perform(post("/parc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creerCreationParcRequestDto())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(PARC_ID.id()))
                .andExpect(jsonPath("$.nom").value(PARC_NOM))
                .andExpect(jsonPath("$.type").value(PARC_TYPE.toString()))
                .andExpect(jsonPath("$.capaciteHoraireMW").value(PARC_CAPACITE_HORAIRE_MW));
    }
}