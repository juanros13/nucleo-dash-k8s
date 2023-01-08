package com.nucleodash.geography.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "distritos_federales")
public class FederalDistrictEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private Integer nomDistFed;

    @NotBlank
    private Integer tipo;


    @NotBlank
    private Integer entidadId;

}
