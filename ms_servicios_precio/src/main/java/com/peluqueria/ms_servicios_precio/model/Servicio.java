package com.peluqueria.ms_servicios_precio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "servicio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private Integer idServicio;

    @NotBlank(message = "El nombre del servicio es obligatorio")
    @Size(max = 100, message = "El nombre del servicio no puede superar los 100 caracteres")
    @Column(name = "nombre_servicio", nullable = false, length = 100)
    private String nombreServicio;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser un número positivo")
    @Column(name = "precio", nullable = false)
    private Integer precio;

    @NotBlank(message = "La duración aproximada es obligatoria")
    @Size(max = 50, message = "La duración no puede superar los 50 caracteres")
    @Column(name = "duracion_minutos_aprox", nullable = false, length = 50)
    private String duracionMinutosAprox;

    @NotNull(message = "La categoría es obligatoria")
    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;
}
