package com.ags.kata.utils;

import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

public final class EntityUtils {

    public static final String PARC_SEQ = "parc_seq";
    public static final String OFFRE_SEQ = "offre_seq";
    public static final String BLOC_SEQ = "bloc_seq";
    public static final String MARCHE_SEQ = "marche_seq";
    public static final String ALLOC_PARC_SEQ = "alloc_parc_seq";


    private EntityUtils() {
    }

    public static long prochainId(String nomSequence, TestEntityManager entityManager) {
        return (long) entityManager.getEntityManager()
                .createNativeQuery("SELECT NEXT VALUE FOR " + nomSequence)
                .getSingleResult();

    }
}
