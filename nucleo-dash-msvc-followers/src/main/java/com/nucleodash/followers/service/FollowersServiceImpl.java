package com.nucleodash.followers.service;


import com.nucleodash.followers.model.entity.FollowersEntity;
import com.nucleodash.followers.model.repository.FollowersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class FollowersServiceImpl implements FollowersService {

    @Autowired
    private FollowersRepository followersRepository;


    public FollowersEntity createFollower(FollowersEntity followers) {

        FollowersEntity saveFollowers = new FollowersEntity();
        saveFollowers.setNombres(followers.getNombres());
        saveFollowers.setApellidoMaterno(followers.getApellidoMaterno());
        saveFollowers.setApellidoPaterno(followers.getApellidoPaterno());
        saveFollowers.setEmail(followers.getEmail());
        saveFollowers.setCelular(followers.getCelular());
        saveFollowers.setCurp(followers.getCurp());
        saveFollowers.setSexo(followers.getSexo());
        saveFollowers.setEstadoCivil(followers.getEstadoCivil());
        saveFollowers.setFechaNacimiento(followers.getFechaNacimiento());
        saveFollowers.setIne(followers.getIne());
        saveFollowers.setResidenciaCalle(followers.getResidenciaCalle());
        saveFollowers.setResidenciaCp(followers.getResidenciaCp());
        saveFollowers.setResidenciaColonia(followers.getResidenciaColonia());
        saveFollowers.setNacimientoEntidadId(followers.getNacimientoEntidadId());
        saveFollowers.setResidenciaMunicipioId(followers.getResidenciaMunicipioId());
        saveFollowers.setResidenciaEntidadId(followers.getResidenciaEntidadId());
        saveFollowers.setResidenciaNumeroExterior(followers.getResidenciaNumeroExterior());
        saveFollowers.setResidenciaNumeroInterior(followers.getResidenciaNumeroInterior());
        saveFollowers.setRolFamiliar(followers.getRolFamiliar());
        saveFollowers.setStatus(1);

    return followersRepository.save(saveFollowers);

    }

    @Override
    public Optional<FollowersEntity> byId(Long id) {
        return  followersRepository.findById(id);
    }


    @Override
    public FollowersEntity save(FollowersEntity followersEntity) {
        return followersRepository.save(followersEntity);
    }

    @Override
    public void delete(Long id) {
        followersRepository.deleteById(id);
    }

    @Override
    public List<FollowersEntity> list() {
        return new ArrayList<>(followersRepository.findAll());
    }

}
