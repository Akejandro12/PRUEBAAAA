package com.app.pw.prueba.service;

import com.app.pw.prueba.model.Clase;

import java.util.List;

public interface ClaseServiceImpl {

    public List<Clase> listarClases();

    public Clase guardarClase(Clase clase);

    public Clase obtenerClase(Long id);

    public Clase actualizarClase(Clase clase);

    public void eliminarClase(Long id);

}