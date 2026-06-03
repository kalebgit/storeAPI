package com.kal.auth_service.service;

import com.kal.auth_service.repository.RepoUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final RepoUser repoUser;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        //TODO: falta usar un try and catch para el gateway cuando auth-service regresa este tipo de respuestas
        return repoUser.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("Usuario no encontrado: " + email));
    }
}
