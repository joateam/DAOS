package com.daos.EstacionAR;

import com.daos.EstacionAR.Entity.Comercio;
import com.daos.EstacionAR.Entity.Recarga;
import com.daos.EstacionAR.Repository.IComercioRepository;
import com.daos.EstacionAR.Repository.IRecargaRepository;
import com.daos.EstacionAR.Service.RecargaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Component
public class DataTest implements CommandLineRunner {

    @Autowired
    private IRecargaRepository recargaRepository;

    @Autowired
    private IComercioRepository comercioRepository;

<<<<<<< HEAD
    @Override
    public void run(String... args) throws Exception {
        Random random = new Random();

        Comercio comercioE = comercioRepository.getOne(1l);
        if(comercioE == null) {

=======
    
	@Override
    public void run(String... args) throws Exception {
        Random random = new Random();

        
        /**
>>>>>>> mat-branch
            IntStream.range(1, 11).forEach(i -> {
                Comercio comercio = new Comercio();
                comercio.setComercioNr(i);
                comercio.setCuit(20000000000L + random.nextInt(1000000000));
                comercio.setDireccion("Direccion " + i);
                comercio.setEstado(random.nextBoolean() ? "autorizado" : "suspendido");

                
                comercioRepository.save(comercio);
            });
<<<<<<< HEAD
        }
=======
        	*/
>>>>>>> mat-branch

        IntStream.range(0, 10).forEach(i -> {
            Recarga recarga = new Recarga();
            recarga.setNroComercio((long) (random.nextInt(10) + 1));
<<<<<<< HEAD
            recarga.setDni(10000000L + random.nextInt(90000000));
            recarga.setPatente(generateRandomPatente(random));
            recarga.setImporte(random.nextInt(901) + 100);
=======
            recarga.setDni(10000000 + random.nextInt(90000000));
            recarga.setPatente(generateRandomPatente(random));
            recarga.setImporte(random.nextDouble(901) + 100);
>>>>>>> mat-branch
            recarga.setFecha(generateRandomDate(random));
            recarga.setAbonado(random.nextInt(2));

            recargaRepository.save(recarga);
        });
    }

    private String generateRandomPatente(Random random) {
        char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] numbers = "0123456789".toCharArray();
        return "" + letters[random.nextInt(letters.length)] + letters[random.nextInt(letters.length)] + letters[random.nextInt(letters.length)]
                + numbers[random.nextInt(numbers.length)] + numbers[random.nextInt(numbers.length)] + numbers[random.nextInt(numbers.length)];
    }

    private LocalDate generateRandomDate(Random random) {
        LocalDate startDate = LocalDate.of(2024, 6, 1);
        long days = ChronoUnit.DAYS.between(startDate, LocalDate.now());
        return startDate.plusDays(random.nextInt((int) days + 1));
    }
}
