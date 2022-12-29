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
            @RequestHeader("X-Auth-Id") String authIdHeader
    ) {
        Map<String, Object> body = new HashMap<>();
        body.put("followers", followersService.list(authIdHeader));
        return body;
    }

    @PostMapping
    public ResponseEntity create(
            @Valid @RequestBody Followers request, BindingResult result,
            @RequestHeader("X-Auth-Id") String authIdHeader
    ) {
        if (result.hasErrors()) {
            return validar(result);
        }

        log.info("Creating follower with {}", request.toString());
        Followers followers =  followersService.save(request, authIdHeader);
        return ResponseEntity.ok(followers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editFollower(
            @Valid @RequestBody Followers follower, BindingResult result,
            @PathVariable Long id,
            @RequestHeader("X-Auth-Id") String authIdHeader

    ) {
        if (result.hasErrors()) {
            return validar(result);
        }

        Optional<Followers> o = followersService.byId(id,authIdHeader);
        if (o.isPresent()) {
            Followers followerDB = o.get();

            followerDB.setNombres(follower.getNombres());
            followerDB.setApellidoMaterno(follower.getApellidoMaterno());
            followerDB.setApellidoPaterno(follower.getApellidoPaterno());
            followerDB.setEmail(follower.getEmail());
            followerDB.setCelular(follower.getCelular());
            followerDB.setCurp(follower.getCurp());
            followerDB.setSexo(follower.getSexo());
            followerDB.setEstadoCivil(follower.getEstadoCivil());
            followerDB.setFechaNacimiento(follower.getFechaNacimiento());
            followerDB.setIne(follower.getIne());
            followerDB.setResidenciaCalle(follower.getResidenciaCalle());
            followerDB.setResidenciaCp(follower.getResidenciaCp());
            followerDB.setResidenciaColonia(follower.getResidenciaColonia());
            followerDB.setNacimientoEntidadId(follower.getNacimientoEntidadId());
            followerDB.setResidenciaMunicipioId(follower.getResidenciaMunicipioId());
            followerDB.setResidenciaEntidadId(follower.getResidenciaEntidadId());
            followerDB.setResidenciaNumeroExterior(follower.getResidenciaNumeroExterior());
            followerDB.setResidenciaNumeroInterior(follower.getResidenciaNumeroInterior());
            followerDB.setRolFamiliar(follower.getRolFamiliar());

            return ResponseEntity.status(HttpStatus.CREATED).body(followersService.save(followerDB,authIdHeader));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFollower(
            @PathVariable Long id,
            @RequestHeader("X-Auth-Id") String authIdHeader
    ) {
        Optional<Followers> o = followersService.byId(id,authIdHeader);
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
            @PathVariable Long id,
            @RequestHeader("X-Auth-Id") String authIdHeader
    ) {
        Optional<Followers> followerOptional = followersService.byId(id,authIdHeader);
        if (followerOptional.isPresent()) {
            return ResponseEntity.ok(followerOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
}
