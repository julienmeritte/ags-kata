package com.ags.kata.infrastructure.adapter.persistence.parc;

public interface ParcCapaciteProjection {
    ParcEntity getParc();

    Integer getCapaciteRestante();
}
