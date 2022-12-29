package com.nucleodash.followers.service;


import com.nucleodash.followers.model.dto.Followers;
import com.nucleodash.followers.model.entity.FollowersEntity;
import com.nucleodash.followers.model.mapper.FollowerMapper;
import com.nucleodash.followers.model.repository.FollowersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class FollowersServiceImpl implements FollowersService {

    @Autowired
    private FollowersRepository followersRepository;

    private final FollowerMapper followerMapper = new FollowerMapper();



    public Followers save(Followers followers, String authId) {

        FollowersEntity followersEntity = followerMapper.convertToEntity(followers, authId);
        followersEntity.setAuthId(authId);
        return followerMapper.convertToDto(followersRepository.save(followersEntity));

    }

    @Override
    public Optional<Followers> byId(Long id, String authId) {
        return  Optional.of(followerMapper.convertToDto(followersRepository.findByIdAndAuthId(id, authId).orElseThrow(EntityNotFoundException::new)));
    }



    @Override
    public void delete(Long id) {
        followersRepository.deleteById(id);
    }

    @Override
    public List<Followers> list(String authId) {
        return new ArrayList<>(followerMapper.convertToDto(followersRepository.findByAuthId(authId)));
    }

}
