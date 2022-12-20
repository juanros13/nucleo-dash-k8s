package com.nucleodash.followers.controller;


import com.nucleodash.followers.model.entity.FollowersEntity;
import com.nucleodash.followers.service.FollowersService;
import com.nucleodash.followers.model.dto.Followers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/api/followers")
public class FollowersController {


    @Autowired
    private FollowersService followersService;

    @GetMapping
    public Map<String, Object> list(
    ) {
        Map<String, Object> body = new HashMap<>();
        body.put("followers", followersService.list());
        return body;
    }

    @PostMapping(value = "/register")
    public ResponseEntity createFollower(@RequestBody FollowersEntity request) {
        log.info("Creating follower with {}", request.toString());
        FollowersEntity followers =  followersService.createFollower(request);
        return ResponseEntity.ok(followers);
    }

    @PutMapping("/editFollower/{id}")
    public ResponseEntity<?> editFollower(
            @Valid @RequestBody FollowersEntity followersEntity, BindingResult result,
            @PathVariable Long id

    ) {
        if (result.hasErrors()) {
            return validar(result);
        }

        Optional<FollowersEntity> o = followersService.byId(id);
        if (o.isPresent()) {
            FollowersEntity followerDB = o.get();

            followerDB.setNombres(followersEntity.getNombres());
            followerDB.setApellidoMaterno(followersEntity.getApellidoMaterno());
            followerDB.setApellidoPaterno(followersEntity.getApellidoPaterno());
            followerDB.setEmail(followersEntity.getEmail());
            followerDB.setCelular(followersEntity.getCelular());
            followerDB.setCurp(followersEntity.getCurp());
            followerDB.setSexo(followersEntity.getSexo());
            followerDB.setEstadoCivil(followersEntity.getEstadoCivil());
            followerDB.setFechaNacimiento(followersEntity.getFechaNacimiento());
            followerDB.setIne(followersEntity.getIne());
            followerDB.setResidenciaCalle(followersEntity.getResidenciaCalle());
            followerDB.setResidenciaCp(followersEntity.getResidenciaCp());
            followerDB.setResidenciaColonia(followersEntity.getResidenciaColonia());
            followerDB.setNacimientoEntidadId(followersEntity.getNacimientoEntidadId());
            followerDB.setResidenciaMunicipioId(followersEntity.getResidenciaMunicipioId());
            followerDB.setResidenciaEntidadId(followersEntity.getResidenciaEntidadId());
            followerDB.setResidenciaNumeroExterior(followersEntity.getResidenciaNumeroExterior());
            followerDB.setResidenciaNumeroInterior(followersEntity.getResidenciaNumeroInterior());
            followerDB.setRolFamiliar(followersEntity.getRolFamiliar());

            return ResponseEntity.status(HttpStatus.CREATED).body(followersService.save(followerDB));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteFollower/{id}")
    public ResponseEntity<?> deleteFollower(
            @PathVariable Long id
    ) {
        Optional<FollowersEntity> o = followersService.byId(id);
        if (o.isPresent()) {
            followersService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<Map<String, String>> validar(
            BindingResult result
    ) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(
            @PathVariable Long id
    ) {
        Optional<FollowersEntity> followerOptional = followersService.byId(id);
        if (followerOptional.isPresent()) {
            return ResponseEntity.ok(followerOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
}
