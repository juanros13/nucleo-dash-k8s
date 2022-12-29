package com.nucleodash.followers.service;

import com.nucleodash.followers.model.dto.Followers;
import com.nucleodash.followers.model.entity.FollowersEntity;

import java.util.List;
import java.util.Optional;

public interface FollowersService {
    List<Followers> list(String authId);
    Followers save(Followers followers, String authId);
    Optional<Followers> byId(Long id, String authId);

    void delete(Long id);
}
