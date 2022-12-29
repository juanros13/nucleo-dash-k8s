package com.nucleodash.followers.model.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "followers")
public class FollowersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombres;

    @NotBlank
    private String apellidoPaterno;

    @NotBlank
    private String apellidoMaterno;

    @NotBlank
    private String curp;

    private String ine;

    @NotBlank
    private String sexo;

    @NotNull
    private Date fechaNacimiento;

    @NotBlank
    private String celular;

    private Integer status;

    private String email;

    @NotBlank
    private String estadoCivil;

    @NotNull
    private Integer nacimientoEntidadId;
    @NotNull
    private Integer residenciaEntidadId;
    @NotNull
    private Integer residenciaMunicipioId;
    @NotNull
    private Integer residenciaColonia;
    @NotNull
    private String residenciaCalle;

    private String residenciaNumeroExterior;
    private String residenciaNumeroInterior;
    @NotNull
    private String residenciaCp;

    private String rolFamiliar;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @NotBlank
    private String authId;


}
