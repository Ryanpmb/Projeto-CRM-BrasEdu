package com.brasedu.crm.braseducrm.services;

import org.springframework.stereotype.Service;

import com.brasedu.crm.braseducrm.entities.UserEntity;
import com.brasedu.crm.braseducrm.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService 
{
    private final UserRepository userRepository;

    public UserEntity store(UserEntity userEntity)
    {
        return userRepository.save(userEntity);
    }

    public UserEntity findById(String id)
    {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteById(String id)
    {
        userRepository.deleteById(id);
    }

    public UserEntity update(UserEntity newUser, String id)
    {
        UserEntity olderUser = findById(id);

        if(olderUser == null) {
            throw new RuntimeException("User not found");
        }

        olderUser.setName(newUser.getName());
        olderUser.setEmail(newUser.getEmail());
        olderUser.setPhone(newUser.getPhone());
        olderUser.setBirthDate(newUser.getBirthDate());

        return userRepository.save(olderUser);
    }
}
