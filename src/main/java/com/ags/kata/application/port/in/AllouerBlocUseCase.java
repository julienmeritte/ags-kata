package com.ags.kata.application.port.in;

import com.ags.kata.application.dto.request.BlocRequestDto;
import com.ags.kata.domain.model.bloc.Bloc;

import java.time.LocalDate;
import java.util.Set;

public interface AllouerBlocUseCase {

    Set<Bloc> recupererEtAllouerBlocs(Set<BlocRequestDto> blocs, LocalDate jour);
}
