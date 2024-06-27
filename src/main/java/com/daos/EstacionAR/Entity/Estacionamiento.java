package com.daos.EstacionAR.Entity;

import jakarta.persistence.Id;

public class Estacionamiento {
    @Id
    private Long id;

    @ManyToOne
    private User usuario;

    @OneToOne
    private Vehiculo vehiculo;

    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;


    private boolean disponible;


}
