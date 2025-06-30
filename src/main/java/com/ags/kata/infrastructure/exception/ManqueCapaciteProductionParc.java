package com.ags.kata.infrastructure.exception;

public class ManqueCapaciteProductionParc extends RuntimeException {
    public ManqueCapaciteProductionParc() {
        super("Capacité de production des parcs insuffisante pour la période");
    }
}
