package com.daos.EstacionAR;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;

import com.daos.EstacionAR.Entity.AbonoComercio;
import com.daos.EstacionAR.Repository.IAbonoComercioRepository;

public class DataTest {

	@Autowired
    private IAbonoComercioRepository abonoComercioRepository;

    public void init() {
        AbonoComercio abono1 = new AbonoComercio(1L, LocalDate.of(2024, 6, 1), LocalDate.of(2024, 6, 30), 950.00f);
        AbonoComercio abono2 = new AbonoComercio(2L, LocalDate.of(2024, 6, 1), LocalDate.of(2024, 6, 30), 850.00f);
        AbonoComercio abono3 = new AbonoComercio(1L, LocalDate.of(2024, 5, 1), LocalDate.of(2024, 5, 31), 1000.00f);

        abonoComercioRepository.saveAll(Arrays.asList(abono1, abono2, abono3));
    }
}
