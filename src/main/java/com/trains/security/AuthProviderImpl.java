package com.trains.security;

import com.trains.model.dto.PassengerDTO;
import com.trains.model.entity.User;
import com.trains.model.entity.UserRole;
import com.trains.service.PassengerService;
import com.trains.service.UserService;
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
    private UserService userService;
    private PassengerService passengerService;

    @Autowired
    public void setPassengerService(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
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
        return new UsernamePasswordAuthenticationToken(passengerDTO,null,authorities);
    }

//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String login = authentication.getName(); // получили логин, который ввели
//        User user = userService.getByName(login);
//        if (user==null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        String password = authentication.getCredentials().toString(); // получили пароль, который ввели
//        if(!password.equals(user.getPassword())) {
//            throw new BadCredentialsException("Bad credentials");
//        }
//
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for (UserRole role: user.getUserRoles()) {
//            authorities.add(new SimpleGrantedAuthority(role.getRole().getName()));
//        }
//        // задаем роли для пользователей
//        UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(user,null,authorities);
//        return userToken;
//    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
