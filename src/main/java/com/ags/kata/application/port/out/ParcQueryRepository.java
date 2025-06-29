package com.ags.kata.application.port.out;

import com.ags.kata.domain.model.parc.ParcId;

public interface ParcQueryRepository {

    ParcId prochainId();
}
