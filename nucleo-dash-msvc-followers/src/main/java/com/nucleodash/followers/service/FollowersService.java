package com.nucleodash.followers.service;

import com.nucleodash.followers.model.entity.FollowersEntity;

import java.util.List;
import java.util.Optional;

public interface FollowersService {
    List<FollowersEntity> list();
    FollowersEntity createFollower(FollowersEntity followers);
    Optional<FollowersEntity> byId(Long id);
    FollowersEntity save(FollowersEntity followersEntity);
    void delete(Long id);
}
