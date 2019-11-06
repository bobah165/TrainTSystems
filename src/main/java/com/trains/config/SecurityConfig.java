package com.trains.config;

import com.trains.security.AuthProviderImpl;
import com.trains.security.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
@ComponentScan("com.trains.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private AuthProviderImpl authProvider;
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    public void setMyAuthenticationSuccessHandler(MyAuthenticationSuccessHandler myAuthenticationSuccessHandler) {
        this.myAuthenticationSuccessHandler = myAuthenticationSuccessHandler;
    }

    @Autowired
    public void setAuthProvider(AuthProviderImpl authProvider) {
        this.authProvider = authProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/registration","/login").anonymous()
               //  .antMatchers("/").authenticated()
                 .antMatchers("/pass/").hasAuthority("passenger")
                .antMatchers("/empl/","/train/add/","/train/edit/","/train/",
                        "/station/add/","station/edit/","/station/").hasAuthority("employee")
                .and().csrf().disable()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login/process/")
                .usernameParameter("login")
                .successHandler(myAuthenticationSuccessHandler)
                .and().logout();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

       auth.authenticationProvider(authProvider);
    }

}
