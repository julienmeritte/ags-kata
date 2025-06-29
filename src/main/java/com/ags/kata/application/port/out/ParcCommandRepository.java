package com.ags.kata.application.port.out;

import com.ags.kata.domain.model.parc.Parc;

public interface ParcCommandRepository {

    Parc save(Parc parc);
}
