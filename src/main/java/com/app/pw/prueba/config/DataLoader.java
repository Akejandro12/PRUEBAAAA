package com.app.pw.prueba.config;

import com.app.pw.prueba.model.EstadoClase;
import com.app.pw.prueba.repository.EstadoClaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private EstadoClaseRepository estadoClaseRepository;

    @Override
    public void run(String... args) throws Exception {
        if (estadoClaseRepository.count() == 0) {
            estadoClaseRepository.save(new EstadoClase("Aplazada"));
            estadoClaseRepository.save(new EstadoClase("En curso"));
            estadoClaseRepository.save(new EstadoClase("Cancelada"));
            estadoClaseRepository.save(new EstadoClase("Programada"));
        }
    }
}
