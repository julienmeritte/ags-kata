package com.ags.kata.infrastructure.adapter.persistence.parc;

import com.ags.kata.application.port.out.ParcCommandRepository;
import com.ags.kata.domain.model.parc.Parc;
import com.ags.kata.domain.model.parc.ParcId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static com.ags.kata.utils.ParcUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@Import({ParcCommandJpaAdapter.class, ParcMapperImpl.class})
class ParcCommandJpaAdapterTest {

    @Autowired
    private ParcCommandRepository parcCommandRepository;

    @Autowired
    private ParcJpaRepository parcJpaRepository;

    @Test
    void shouldReturnParc_WhenSaving_GivenValidParc() {
        var prochainId = parcJpaRepository.prochainId();
        var parcACreer = new Parc(new ParcId(prochainId), PARC_NOM, PARC_TYPE, PARC_CAPACITE_HORAIRE_MW);
        var result = parcCommandRepository.save(parcACreer);
        assertEquals(parcACreer, result);
    }
}