package com.trains.security;

import com.trains.model.dto.PassengerDTO;
import com.trains.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthProviderImpl implements AuthenticationProvider {
    private PassengerService passengerService;

    @Autowired
    public void setPassengerService(PassengerService passengerService) {
        this.passengerService = passengerService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName(); // получили логин, который ввели
        PassengerDTO passengerDTO = passengerService.getPassengerBylogin(login);
        if (passengerDTO==null) {
            throw new UsernameNotFoundException("User not found");
        }
        String password = authentication.getCredentials().toString(); // получили пароль, который ввели
        if(!password.equals(passengerDTO.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }

        List<GrantedAuthority> authorities = new ArrayList<>(); // задаем роли для пользователей

        authorities.add(new SimpleGrantedAuthority(passengerDTO.getUser()));

        UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(passengerDTO,null,authorities);

        return userToken;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
