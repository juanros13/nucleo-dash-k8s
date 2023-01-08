package com.nucleodash.geography.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "localidades")
public class LocalitiesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private Integer nomSeccion;

    @NotBlank
    private Integer distritoFederalId;

    @NotBlank
    private Integer distritoLocalId;

}
