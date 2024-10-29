package com.app.pw.prueba.service;

import com.app.pw.prueba.model.Clase;
import com.app.pw.prueba.repository.ClaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaseService implements ClaseServiceImpl{

    @Autowired
    private ClaseRepository claseRepository;

    public List<Clase> getAllClases() {
        return claseRepository.findAll();
    }
    public void saveClase(Clase clase) {
        claseRepository.save(clase);
    }

    @Override
    public List<Clase> listarClases() {
        return claseRepository.findAll();
    }

    @Override
    public Clase guardarClase(Clase clase) {
        return claseRepository.save(clase);
    }

    @Override
    public Clase obtenerClase(Long id) {
        return claseRepository.findById(id).get();
    }

    @Override
    public Clase actualizarClase(Clase clase) {
        return claseRepository.save(clase);
    }

    @Override
    public void eliminarClase(Long id) {
        claseRepository.deleteById(id);

    }
}