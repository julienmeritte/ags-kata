package com.ags.kata.infrastructure.adapter.web.offre;

import com.ags.kata.application.dto.request.CreationOffreRequestDto;
import com.ags.kata.application.service.OffreCommandService;
import com.ags.kata.infrastructure.exception.MarcheNonTrouveException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.ags.kata.utils.AllocationParcUtils.ALLOCATION_PARC_ID;
import static com.ags.kata.utils.AllocationParcUtils.ALLOCATION_PARC_QUANTITE;
import static com.ags.kata.utils.BlocUtils.*;
import static com.ags.kata.utils.MarcheUtils.MARCHE_ID;
import static com.ags.kata.utils.MarcheUtils.MARCHE_NOM;
import static com.ags.kata.utils.OffreUtils.*;
import static com.ags.kata.utils.ParcUtils.PARC_ID;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OffreController.class)
class OffreControllerTest {

    @MockitoBean
    private OffreCommandService offreCommandService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldReturnCreated_WhenCreatingOffre_GivenCreationOffreRequestDto() throws Exception {

        when(offreCommandService.creerOffre(any(CreationOffreRequestDto.class)))
                .thenReturn(creerOffreResponseDto());

        mockMvc.perform(post("/offre")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creerCreationOffreRequestDto())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(OFFRE_ID.id()))
                .andExpect(jsonPath("$.acteur").value(OFFRE_ACTEUR))
                .andExpect(jsonPath("$.marche").exists())
                .andExpect(jsonPath("$.marche.id").value(MARCHE_ID.id()))
                .andExpect(jsonPath("$.marche.nom").value(MARCHE_NOM))
                .andExpect(jsonPath("$.jour").value(OFFRE_JOUR.toString()))
                .andExpect(jsonPath("$.blocs").value(hasSize(1)))
                .andExpect(jsonPath("$.blocs[0].id").value(BLOC_ID.id()))
                .andExpect(jsonPath("$.blocs[0].quantiteEnergieMW").value(BLOC_QUANTITE_ENERGIE_MW))
                .andExpect(jsonPath("$.blocs[0].positionJournee").value(BLOC_POSITION_JOURNEE))
                .andExpect(jsonPath("$.blocs[0].prixPlancher").value(BLOC_PRIX_PLANCHER))
                .andExpect(jsonPath("$.blocs[0].allocations").value(hasSize(1)))
                .andExpect(jsonPath("$.blocs[0].allocations[0].id").value(ALLOCATION_PARC_ID.id()))
                .andExpect(jsonPath("$.blocs[0].allocations[0].parcId").value(PARC_ID.id()))
                .andExpect(jsonPath("$.blocs[0].allocations[0].quantite").value(ALLOCATION_PARC_QUANTITE));
    }

    @Test
    void shouldReturn404_WhenCreatingOffre_GivenInvalidMarcheId() throws Exception {

        when(offreCommandService.creerOffre(any(CreationOffreRequestDto.class)))
                .thenThrow(new MarcheNonTrouveException(MARCHE_ID));

        mockMvc.perform(post("/offre")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creerCreationOffreRequestDto())))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Marché non trouvé pour l'id -> " + MARCHE_ID.id()));
    }
}