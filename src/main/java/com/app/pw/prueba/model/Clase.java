package com.app.pw.prueba.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 20)
    private String nombre;

    @NotNull
    private Integer horario;

    @NotNull
    @Size(max = 100)
    private String docente;

    private String salon;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private EstadoClase estado;


}