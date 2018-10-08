package com.sda.smartCalendar.configuration;

import com.sda.smartCalendar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.h2.H2ConsoleProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//
@Configuration
public class SecurityConfigur extends WebSecurityConfigurerAdapter {
//
    @Autowired
    private UserService userService;

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/registration",
                        "/login",
                        "/").permitAll()
                .and()
                .authorizeRequests().antMatchers("/h2**").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll();
        http.csrf().disable();
        http.headers().frameOptions().disable();


        //Do uzuniecia
       // String path = this.console.getPath();
        //String antPattern = (path.endsWith("/") ? path + "**" : path + "/**");
      // HttpSecurity h2Console = http.antMatcher(antPattern);
//        h2Console.csrf().disable();
//        h2Console.httpBasic();
//        h2Console.headers().frameOptions().sameOrigin();
        // config as you like
        //http.authorizeRequests().anyRequest().permitAll();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER")
//                .and()
//                .withUser("manager").password("password").roles("MANAGER");
//    }
}





//    @Configuration
//    class SecurityConfigSecured extends WebSecurityConfigurerAdapter {
//        private UserService service;
//    }
//
//    @Configuration
//    class SecurityConfigUnsecured{}
