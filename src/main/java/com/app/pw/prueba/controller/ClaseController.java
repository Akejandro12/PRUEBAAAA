package com.app.pw.prueba.controller;

import com.app.pw.prueba.model.Clase;
import com.app.pw.prueba.service.ClaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ClaseController {

    @Autowired
    private ClaseService claseService;

    @GetMapping("/clases")
    public String listarClases(Model model) {
        model.addAttribute("clases", claseService.listarClases());
        return "clases";
    }
    @GetMapping("/clases/crearClase")
    public String mostrarFormulario(Model model) {
        model.addAttribute("clase", new Clase());
        return "crearClase";
    }

    @PostMapping("/clases")
    public String guardarClase(@Valid @ModelAttribute Clase clase, BindingResult result, Model model) {
            claseService.guardarClase(clase);
        return "redirect:/clases";
    }

    @GetMapping("/clases/editar/{id}")
    public String mostrarEditarClase(@PathVariable Long id, Model model) {
        model.addAttribute("clase", claseService.obtenerClase(id));
        return "editarClase";
    }

    @PostMapping("/clases/{id}")
    public String actualizarClase(@PathVariable Long id,@ModelAttribute("clase") Clase clase, Model model) {
        Clase claseActual = claseService.obtenerClase(id);
        claseActual.setId(id);
        claseActual.setNombre(clase.getNombre());
        claseActual.setDescripcion(clase.getDescripcion());
        claseActual.setSalon(clase.getSalon());
        claseActual.setHorario(clase.getHorario());
        claseActual.setDocente(clase.getDocente());

        claseService.actualizarClase(claseActual);
        return "redirect:/clases";
    }

    @GetMapping("/clases/{id}")
    public String eliminarClase(@PathVariable Long id) {
        claseService.eliminarClase(id);
        return "redirect:/clases";
    }

}