package com.nucleodash.followers.model.repository;

import com.nucleodash.followers.model.entity.FollowersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface FollowersRepository extends JpaRepository<FollowersEntity, Long> {


    List<FollowersEntity> findByAuthId(String authId);

    Optional<FollowersEntity> findByIdAndAuthId(Long id, String authId);

}
