package com.ags.kata.application.port.in;

import com.ags.kata.application.dto.request.BlocRequestDto;
import com.ags.kata.application.dto.response.ParcResponseDto;
import com.ags.kata.domain.model.marche.MarcheId;
import com.ags.kata.domain.model.parc.ParcAvecCapacite;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface RecupererParcUseCase {

    List<ParcAvecCapacite> recupererParcsAvecCapaciteDisponiblesPeriode(BlocRequestDto bloc, LocalDate jour);

    Set<ParcResponseDto> recupererParcsVendantParMarche(MarcheId marcheId);
}
